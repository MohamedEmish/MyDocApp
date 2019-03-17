package com.yackeenSolution.mydocapp.Fragments.SearchTabActivites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.LogActivities.MainScreen;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

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
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_confirmation);
        LinearLayout linearLayout = findViewById(R.id.confirmation_root);
        Utils.RTLSupport(this, linearLayout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // TODO : get appointment ID and fill data

        mConfirmationButton = findViewById(R.id.confirmation_button);

        mConfirmationDoctorName = findViewById(R.id.confirmation_doctor_name);
        mConfirmationPatientName = findViewById(R.id.confirmation_patient_name);
        mSearchResultTitle = findViewById(R.id.facility_details_title);
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
