package com.yackeenSolution.mydocapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_doctor);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mFilterBack = findViewById(R.id.filter_back);
        mFilterButton = findViewById(R.id.filter_button);
        mSearchFilterFemaleRadio = findViewById(R.id.search_filter_female_radio);
        mSearchResultTitle = findViewById(R.id.search_result_title);
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
                Intent intent = new Intent(SearchFilterDoctor.this, SearchResultDoctorActivity.class);
                startActivity(intent);
            }
        });

        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO :: get filter data
                Intent intent = new Intent(SearchFilterDoctor.this, SearchResultDoctorActivity.class);
                // TODO : attach new url
                startActivity(intent);
            }
        });
    }
}
