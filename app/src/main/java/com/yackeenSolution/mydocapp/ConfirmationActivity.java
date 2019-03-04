package com.yackeenSolution.mydocapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    private Button mConfirmationButton;
    private TextView mConfirmationDoctorName;
    private TextView mConfirmationPatientName;
    private TextView mSearchResultTitle;
    private TextView mConfirmationSpeciality;
    private TextView mConfirmationDate;
    private TextView mConfirmationBookingNumber;
    private TextView mConfirmationClinicName;
    private TextView mConfirmationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mConfirmationButton = findViewById(R.id.confirmation_button);
        mConfirmationDoctorName = findViewById(R.id.confirmation_doctor_name);
        mConfirmationPatientName = findViewById(R.id.confirmation_patient_name);
        mSearchResultTitle = findViewById(R.id.search_result_title);
        mConfirmationSpeciality = findViewById(R.id.confirmation_speciality);
        mConfirmationDate = findViewById(R.id.confirmation_date);
        mConfirmationBookingNumber = findViewById(R.id.confirmation_booking_number);
        mConfirmationClinicName = findViewById(R.id.confirmation_clinic_name);
        mConfirmationTime = findViewById(R.id.confirmation_time);

        mConfirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmationActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });
    }
}
