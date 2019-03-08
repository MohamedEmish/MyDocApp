package com.yackeenSolution.mydocapp.SearchTabActivites;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;

import java.util.Locale;

public class SearchFilterDoctor extends AppCompatActivity {

    private ImageView mFilterBack;
    private Button mFilterButton;
    private AppCompatRadioButton mSearchFilterFemaleRadio;
    private TextView mSearchResultTitle;
    private Spinner mSearchFilterJobTitleSpinner;
    private RadioGroup mSignUpGenderRadioGroup;
    private AppCompatRadioButton mSearchFilterMaleRadio;
    private Spinner mSearchFilterLanguageSpinner;
    private Spinner mSearchFilterNationalitySpinner;
    private Spinner mSearchFilterAreaSpinner;
    private Spinner mSearchFilterInsuranceSpinner;

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
        setContentView(R.layout.activity_search_filter_doctor);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mFilterBack = findViewById(R.id.facility_details_back);
        mFilterButton = findViewById(R.id.filter_button);
        mSearchFilterFemaleRadio = findViewById(R.id.search_filter_female_radio);
        mSearchResultTitle = findViewById(R.id.facility_details_title);
        mSearchFilterJobTitleSpinner = findViewById(R.id.search_filter_job_title_spinner);
        mSignUpGenderRadioGroup = findViewById(R.id.sign_up_gender_radio_group);
        mSearchFilterMaleRadio = findViewById(R.id.search_filter_male_radio);
        mSearchFilterLanguageSpinner = findViewById(R.id.search_filter_language_spinner);
        mSearchFilterNationalitySpinner = findViewById(R.id.search_filter_nationality_spinner);
        mSearchFilterAreaSpinner = findViewById(R.id.search_filter_area_spinner);
        mSearchFilterInsuranceSpinner = findViewById(R.id.search_filter_insurance_spinner);

        mFilterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO :: get filter doctorData
                Intent intent = new Intent(SearchFilterDoctor.this, SearchResultDoctorActivity.class);
                // TODO : attach new url
                startActivity(intent);
            }
        });
    }
}
