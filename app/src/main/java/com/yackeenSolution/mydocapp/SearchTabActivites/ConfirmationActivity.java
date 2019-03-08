package com.yackeenSolution.mydocapp.SearchTabActivites;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.MainScreen;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;

import java.util.Locale;

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

    private Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        return context.createConfigurationContext(config);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            super.attachBaseContext(updateResources(newBase, PreferenceManager.getDefaultSharedPreferences(newBase).getString("lang", "en")));
        } else {
            super.attachBaseContext(newBase);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(this, SaveSharedPreference.getLanguage(this));
        setContentView(R.layout.activity_confirmation);
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
