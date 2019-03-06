package com.yackeenSolution.mydocapp.SearchTabActivites;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.yackeenSolution.mydocapp.R;

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
    private TextView mAppointRequestDate;
    private TextView mAppointRequestTime;
    private TextView mSearchResultTitle;
    private TextView mAppointRequestDoctorSpeciality;
    private Spinner mAppointRequestUserNameSpinner;
    private TextView mAppointRequestDoctorClinic;
    private EditText mAppointRequestUserNationalId;
    private Button mRequestButton;
    String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_request);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        if (intent.hasExtra("source")) {
            source = intent.getStringExtra("source");
        } else {
            source = "";
        }


        mAppointRequestBack = findViewById(R.id.appoint_request_back);
        mAppointRequestUserMobile = findViewById(R.id.appoint_request_user_mobile);
        mAppointRequestDoctorVisitTypeSpinner = findViewById(R.id.appoint_request_doctor_visit_type_spinner);
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
        // TODO: check date,time,visit type
        Intent intent = new Intent(AppointmentRequestActivity.this, ConfirmationActivity.class);
        // TODO: doctorData to be attached
        startActivity(intent);
    }

    private void goBack() {
        finish();
    }

    private void pickTime() {
        myCalendar = Calendar.getInstance();

        timePicker = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String myFormat = "HH:MM";
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());

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
                String myFormat = "DD/MM/YYYY"; //In which you need put here
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());
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


}
