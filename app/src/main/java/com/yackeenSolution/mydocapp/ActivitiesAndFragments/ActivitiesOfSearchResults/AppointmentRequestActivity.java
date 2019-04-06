package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.Objects.AppointmentToUpload;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.Objects.UserData;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AppointmentRequestActivity extends AppCompatActivity {

    private Calendar myCalendar;
    private TextView
            mAppointRequestUserMobile,
            mAppointRequestDoctorName,
            mAppointRequestDoctorSpeciality,
            mAppointRequestDoctorClinic;
    private Spinner
            mAppointRequestDoctorVisitTypeSpinner,
            mAppointRequestUserNameSpinner;
    private EditText mAppointRequestDate,
            mAppointRequestUserNationalId,
            mAppointRequestTime;

    private ScrollView dataLayout;
    private LinearLayout progress;
    private DataViewModel dataViewModel;
    private String userFullName;
    private boolean
            familyMembersDone = false,
            visitTypeDone = false;
    private String
            doctorId,
            facilityId,
            specialityId;
    private List<String> familyMembersStrings;
    private List<FamilyMember> mainFamilyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_appointment_request);
        LinearLayout linearLayout = findViewById(R.id.appoint_request_root);
        Utils.RTLSupport(this, linearLayout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Intent intent = getIntent();
        doctorId = intent.getStringExtra("doctorId");
        facilityId = intent.getStringExtra("facilityId");
        specialityId = intent.getStringExtra("speciality");

        mAppointRequestDoctorVisitTypeSpinner = findViewById(R.id.appoint_request_doctor_visit_type_spinner);
        ImageView mAppointRequestBack = findViewById(R.id.appoint_request_back);
        mAppointRequestUserMobile = findViewById(R.id.appoint_request_user_mobile);
        mAppointRequestDoctorName = findViewById(R.id.appoint_request_doctor_name);
        mAppointRequestDate = findViewById(R.id.appoint_request_date);
        mAppointRequestTime = findViewById(R.id.appoint_request_time);
        mAppointRequestDoctorSpeciality = findViewById(R.id.appoint_request_doctor_speciality);
        mAppointRequestUserNameSpinner = findViewById(R.id.appoint_request_user_name_spinner);
        mAppointRequestDoctorClinic = findViewById(R.id.appoint_request_doctor_clinic);
        mAppointRequestUserNationalId = findViewById(R.id.appoint_request_user_national_id);
        Button mRequestButton = findViewById(R.id.appoint_request_button);
        dataLayout = findViewById(R.id.appoint_request_data_layout);
        progress = findViewById(R.id.appoint_request_progress_bar_layout);

        mAppointRequestDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                pickDate();
            }
        });

        mAppointRequestTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                pickTime();
            }
        });

        mAppointRequestBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        mRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmation();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        getUserData();
    }

    private void confirmation() {
        if (isAllDataOk()) {
            AppointmentToUpload appointmentToUpload = new AppointmentToUpload();
            if (mAppointRequestUserNameSpinner.getSelectedItemId() == 0) {
                int id = Integer.parseInt(SaveSharedPreference.getUserId(this));
                appointmentToUpload.setPatientId(id);
            } else {
                for (FamilyMember member : mainFamilyList) {
                    if (familyMembersStrings.get(mAppointRequestUserNameSpinner.getSelectedItemPosition()).equals(member.getName())) {
                        int fId = member.getId();
                        appointmentToUpload.setPatientId(fId);
                    }
                }
            }
            progress.setVisibility(View.VISIBLE);
            appointmentToUpload.setPatientPhone(mAppointRequestUserMobile.getText().toString().trim());
            if (mAppointRequestUserNationalId.getText().toString().isEmpty()) {
                appointmentToUpload.setNationalId(null);
            } else {
                appointmentToUpload.setNationalId(mAppointRequestUserNationalId.getText().toString().trim());
            }
            appointmentToUpload.setDoctorFacilityId(Integer.parseInt(facilityId));
            appointmentToUpload.setSpecialtyId(Integer.parseInt(specialityId));
            appointmentToUpload.setTime(mAppointRequestTime.getText().toString().trim());
            appointmentToUpload.setRequestType(mAppointRequestDoctorVisitTypeSpinner.getSelectedItemPosition());
            appointmentToUpload.setDoctorId(Integer.parseInt(doctorId));
            appointmentToUpload.setPromoCode(null);
            appointmentToUpload.setData(Utils.dateToApiFormat(mAppointRequestDate.getText().toString().trim()));

            dataViewModel.requestAppointment(appointmentToUpload, this).observe(this, new Observer<Appointment>() {
                @Override
                public void onChanged(Appointment appointment) {
                    if (appointment != null) {
                        int id = appointment.getId();
                        Intent intent = new Intent(AppointmentRequestActivity.this, ConfirmationActivity.class);
                        intent.putExtra("appointmentId", String.valueOf(id));
                        progress.setVisibility(View.GONE);
                        startActivity(intent);
                    }
                }
            });

        }

    }

    private void getUserData() {
        dataViewModel.getUserAccountData(Integer.parseInt(SaveSharedPreference.getUserId(this)), this).observe(this, new Observer<UserData>() {
                    @Override
                    public void onChanged(UserData userData) {
                        userFullName = userData.getFirstName() + " " + userData.getLastName();
                        mAppointRequestUserMobile.setText(userData.getMobileNumber());
                        setUpData();
                    }
                }
        );

        dataViewModel.getSpecificDoctorData(Integer.parseInt(doctorId), this).observe(this, new Observer<List<DoctorResult>>() {
            @Override
            public void onChanged(List<DoctorResult> doctorResults) {
                mAppointRequestDoctorName.setText(doctorResults.get(0).getName());
                mAppointRequestDoctorSpeciality.setText(doctorResults.get(0).getSpeciality());
                mAppointRequestDoctorClinic.setText(doctorResults.get(0).getFacilityName());
            }
        });
    }

    private void setUpData() {
        dataViewModel.getMyFamilyMembersList(Integer.parseInt(SaveSharedPreference.getUserId(this)), this).observe(this, new Observer<List<FamilyMember>>() {
            @Override
            public void onChanged(List<FamilyMember> familyMembers) {
                mainFamilyList = familyMembers;
                familyMembersStrings = new ArrayList<>();
                familyMembersStrings.add(userFullName);
                for (FamilyMember member : familyMembers) {
                    familyMembersStrings.add(member.getName());
                }
                Utils.setupSpinner(AppointmentRequestActivity.this, familyMembersStrings, mAppointRequestUserNameSpinner);
                String[] visitType = AppointmentRequestActivity.this.getResources().getStringArray(R.array.visit_type);
                Utils.setupSpinner(AppointmentRequestActivity.this, visitType, mAppointRequestDoctorVisitTypeSpinner);
                familyMembersDone = true;
                visitTypeDone = true;
                checkDone();
            }
        });
    }

    private void checkDone() {
        if (familyMembersDone && visitTypeDone) {
            progress.setVisibility(View.GONE);
            dataLayout.setVisibility(View.VISIBLE);
        }
    }

    private boolean isAllDataOk() {

        boolean isAllOk = true;

        // Check time entry
        if (!Utils.isValueSet(mAppointRequestTime, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        }

        // Check date entry
        if (!Utils.isValueSet(mAppointRequestDate, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        }
        // Check visit type
        if (mAppointRequestDoctorVisitTypeSpinner.getSelectedItem().equals("Visit type")) {
            Toast.makeText(this, R.string.please_choose_visit_type, Toast.LENGTH_SHORT).show();
            isAllOk = false;
        }
        return isAllOk;
    }


    private void goBack() {
        finish();
    }

    private void pickTime() {
        myCalendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timePicker = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String myFormat = "HH:mm";
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());


                if (Build.VERSION.SDK_INT >= 23) {
                    myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalendar.set(Calendar.MINUTE, minute);
                } else {
                    myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalendar.set(Calendar.MINUTE, minute);
                }

                mAppointRequestTime.setText(format.format(myCalendar.getTime()));
                mAppointRequestTime.setTextColor(getResources().getColor(R.color.colorGray));
            }
        };

        TimePickerDialog dialog = new TimePickerDialog(AppointmentRequestActivity.this,
                R.style.Date_Picker,
                timePicker,
                myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE),
                false);


        Objects.requireNonNull(dialog.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void pickDate() {

        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener datePicker1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String myFormat = "dd/MM/YYYY"; //In which you need put here
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());

                myCalendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                myCalendar.set(Calendar.YEAR, datePicker.getYear());
                myCalendar.set(Calendar.MONTH, datePicker.getMonth());

                mAppointRequestDate.setText(format.format(myCalendar.getTime()));
                mAppointRequestDate.setTextColor(getResources().getColor(R.color.colorGray));
            }
        };
        // start picker with pre-chosen date
        DatePickerDialog dialog = new DatePickerDialog(AppointmentRequestActivity.this,
                R.style.Date_Picker,
                datePicker1,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMinDate(myCalendar.getTimeInMillis());

        Objects.requireNonNull(dialog.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
