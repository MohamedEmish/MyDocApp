package com.yackeenSolution.mydocapp.SearchTabActivites;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AppointmentRequestActivity extends AppCompatActivity {

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener datePicker;
    TimePickerDialog.OnTimeSetListener timePicker;
    private ImageView mAppointRequestBack;
    private TextView mAppointRequestUserMobile;
    private Spinner mAppointRequestDoctorVisitTypeSpinner;
    private TextView mAppointRequestDoctorName;
    String mAppointmentType;
    private EditText mAppointRequestDate;
    private TextView mSearchResultTitle;
    private TextView mAppointRequestDoctorSpeciality;
    private Spinner mAppointRequestUserNameSpinner;
    private TextView mAppointRequestDoctorClinic;
    private EditText mAppointRequestUserNationalId;
    private Button mRequestButton;
    private EditText mAppointRequestTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_appointment_request);
        LinearLayout linearLayout = findViewById(R.id.appoint_request_root);
        Utils.RTLSupport(this, linearLayout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        mAppointRequestBack = findViewById(R.id.appoint_request_back);
        mAppointRequestUserMobile = findViewById(R.id.appoint_request_user_mobile);
        mAppointRequestDoctorVisitTypeSpinner = findViewById(R.id.appoint_request_doctor_visit_type_spinner);
        setupVisitTypeSpinner();
        mAppointRequestDoctorName = findViewById(R.id.appoint_request_doctor_name);
        mAppointRequestDate = findViewById(R.id.appoint_request_date);
        mAppointRequestTime = findViewById(R.id.appoint_request_time);
        mSearchResultTitle = findViewById(R.id.facility_details_title);
        mAppointRequestDoctorSpeciality = findViewById(R.id.appoint_request_doctor_speciality);
        mAppointRequestUserNameSpinner = findViewById(R.id.appoint_request_user_name_spinner);
        mAppointRequestDoctorClinic = findViewById(R.id.appoint_request_doctor_clinic);
        mAppointRequestUserNationalId = findViewById(R.id.appoint_request_user_national_id);
        mRequestButton = findViewById(R.id.appoint_request_button);

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

    private void confirmation() {
        if (isAllDataOk()) {
            Intent intent = new Intent(AppointmentRequestActivity.this, ConfirmationActivity.class);
            // TODO: appointment Data to be attached
            startActivity(intent);
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

        timePicker = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String myFormat = "HH:MM";
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());


                if (Build.VERSION.SDK_INT >= 23) {
                    myCalendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    myCalendar.set(Calendar.MINUTE, timePicker.getMinute());
                } else {
                    myCalendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    myCalendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
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


        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void pickDate() {

        myCalendar = Calendar.getInstance();

        datePicker = new DatePickerDialog.OnDateSetListener() {
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
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMinDate(myCalendar.getTimeInMillis());

        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void setupVisitTypeSpinner() {

        ArrayAdapter visitSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.visit_type, android.R.layout.simple_spinner_item);

        visitSpinnerAdapter.setDropDownViewResource(R.layout.my_spinner_layout);

        mAppointRequestDoctorVisitTypeSpinner.setAdapter(visitSpinnerAdapter);

        mAppointRequestDoctorVisitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("First time")) {
                        mAppointmentType = "First time";
                    } else if (selection.equals("Follow-up")) {
                        mAppointmentType = "Follow-up";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }



}
