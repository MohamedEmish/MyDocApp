package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog.MainScreen;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView
            mConfirmationDoctorName,
            mConfirmationPatientName,
            mConfirmationSpeciality,
            mConfirmationDate,
            mConfirmationBookingNumber,
            mConfirmationClinicName,
            mConfirmationTime;
    private String id;

    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_confirmation);
        LinearLayout linearLayout = findViewById(R.id.confirmation_root);
        Utils.RTLSupport(this, linearLayout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        id = intent.getStringExtra("appointmentId");

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        Button mConfirmationButton = findViewById(R.id.confirmation_button);

        mConfirmationDoctorName = findViewById(R.id.confirmation_doctor_name);
        mConfirmationPatientName = findViewById(R.id.confirmation_patient_name);
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

        setupData();
    }

    private void setupData() {
        dataViewModel.getSpecificAppointment(Integer.parseInt(id), this).observe(this, new Observer<Appointment>() {
            @Override
            public void onChanged(Appointment appointment) {
                if (appointment != null) {
                    if (appointment.getId() != null && !appointment.getTime().isEmpty()) {
                        mConfirmationBookingNumber.setText(String.valueOf(appointment.getId()));
                    }
                    if (appointment.getPatientName() != null && !appointment.getPatientName().isEmpty()) {
                        mConfirmationPatientName.setText(appointment.getPatientName().trim());
                    }
                    if (appointment.getDoctorName() != null && !appointment.getDoctorName().isEmpty()) {
                        mConfirmationDoctorName.setText(appointment.getDoctorName().trim());
                    }
                    if (appointment.getClinicName() != null && !appointment.getClinicName().isEmpty()) {
                        mConfirmationClinicName.setText(appointment.getClinicName().trim());
                    }
                    if (appointment.getSpeciality() != null && !appointment.getSpeciality().isEmpty()) {
                        mConfirmationSpeciality.setText(appointment.getSpeciality().trim());
                    }
                    if (appointment.getDateTime() != null && !appointment.getDateTime().isEmpty()) {
                        mConfirmationDate.setText(Utils.dateAppFormat(appointment.getDateTime().trim()));
                    }
                    if (appointment.getTime() != null && !appointment.getTime().isEmpty()) {
                        mConfirmationTime.setText(appointment.getTime().trim());
                    }
                }
            }
        });
    }
}
