package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.MyArea;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SearchFilterDoctor extends AppCompatActivity {

    private ImageView mFilterBack;
    private Button mFilterButton;

    int specialityId;
    String searchDate;
    String insuranceId, areaId;
    List<String> stringsInsurance, stringsArea, stringsQualifications, stringsNationalities, stringsLanguages;
    Boolean gender;
    List<MyArea> mainAreaList;
    List<Insurance> mainInsuranceList;
    List<Speciality> mainQualificationList, mainNationalitiesList, mainLanguagesList;
    TextView clear;
    boolean insuranceDone = false, qualificationsDone = false, nationalitiesDone = false, areaDone = false, languageDone = false;

    RadioButton maleButton, femaleButton;

    Spinner qualificationSpinner, areaSpinner, langSpinner, nationalitySpinner, insuranceSpinner;
    DataViewModel dataViewModel;
    LinearLayout dataLayout, progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_search_filter_doctor);
        LinearLayout linearLayout = findViewById(R.id.doctor_filter_root);
        Utils.RTLSupport(this, linearLayout);

        Intent intent = getIntent();
        specialityId = Integer.parseInt(intent.getStringExtra("specialityId"));
        insuranceId = intent.getStringExtra("insuranceId");
        areaId = intent.getStringExtra("areaId");
        searchDate = intent.getStringExtra("searchDate");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        dataLayout = findViewById(R.id.search_doctor_data_layout);
        progress = findViewById(R.id.search_doctor_progress_layout);

        qualificationSpinner = findViewById(R.id.search_filter_qualification_spinner);
        areaSpinner = findViewById(R.id.search_filter_area_spinner);
        langSpinner = findViewById(R.id.search_filter_language_spinner);
        nationalitySpinner = findViewById(R.id.search_filter_nationality_spinner);
        insuranceSpinner = findViewById(R.id.search_filter_insurance_spinner);


        maleButton = findViewById(R.id.search_filter_male_radio);
        femaleButton = findViewById(R.id.search_filter_female_radio);

        mFilterBack = findViewById(R.id.facility_filter_back);
        mFilterButton = findViewById(R.id.doctor_filter_button);

        mFilterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goForSearch();
            }
        });

        clear = findViewById(R.id.search_doctor_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFilter();
            }
        });

        setupDate();
    }

    private void clearFilter() {
        qualificationSpinner.setSelection(0);
        nationalitySpinner.setSelection(0);
        langSpinner.setSelection(0);
        insuranceSpinner.setSelection(0);
        areaSpinner.setSelection(0);
        maleButton.setChecked(false);
        femaleButton.setChecked(false);
    }

    private void checkDone() {
        if (insuranceDone && qualificationsDone && areaDone && languageDone && nationalitiesDone) {
            progress.setVisibility(View.GONE);
            dataLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setupDate() {
        dataViewModel.getMyInsuranceList().observe(this, new Observer<List<Insurance>>() {
            @Override
            public void onChanged(List<Insurance> insurances) {
                mainInsuranceList = insurances;
                stringsInsurance = new ArrayList<>();
                stringsInsurance.add(SearchFilterDoctor.this.getResources().getString(R.string.select_insurance_op));
                if (insurances.size() > 0) {
                    for (Insurance insurance : insurances) {
                        stringsInsurance.add(insurance.getName());
                    }
                    Utils.setupSpinner(SearchFilterDoctor.this, stringsInsurance, insuranceSpinner);
                    insuranceDone = true;
                    checkDone();
                }
            }
        });

        dataViewModel.getMyAreaList().observe(this, new Observer<List<MyArea>>() {
            @Override
            public void onChanged(List<MyArea> areas) {
                mainAreaList = areas;
                stringsArea = new ArrayList<>();
                stringsArea.add(SearchFilterDoctor.this.getResources().getString(R.string.select_area));
                if (areas.size() > 0) {
                    for (MyArea area : areas) {
                        stringsArea.add(area.getName());
                    }
                    Utils.setupSpinner(SearchFilterDoctor.this, stringsArea, areaSpinner);
                    areaDone = true;
                    checkDone();
                }
            }
        });

        dataViewModel.getQualifications().observe(this, new Observer<List<Speciality>>() {
            @Override
            public void onChanged(List<Speciality> specialities) {
                mainQualificationList = specialities;
                stringsQualifications = new ArrayList<>();
                stringsQualifications.add(SearchFilterDoctor.this.getResources().getString(R.string.select_qualify));
                if (specialities.size() > 0) {
                    for (Speciality qualify : specialities) {
                        stringsQualifications.add(qualify.getName());
                    }
                    Utils.setupSpinner(SearchFilterDoctor.this, stringsQualifications, qualificationSpinner);
                    qualificationsDone = true;
                    checkDone();
                }
            }
        });

        dataViewModel.getNationalities().observe(this, new Observer<List<Speciality>>() {
            @Override
            public void onChanged(List<Speciality> specialities) {
                mainNationalitiesList = specialities;
                stringsNationalities = new ArrayList<>();
                stringsNationalities.add(SearchFilterDoctor.this.getResources().getString(R.string.select_nationality));
                if (specialities.size() > 0) {
                    for (Speciality nationality : specialities) {
                        stringsNationalities.add(nationality.getName());
                    }
                    Utils.setupSpinner(SearchFilterDoctor.this, stringsNationalities, nationalitySpinner);
                    nationalitiesDone = true;
                    checkDone();
                }
            }
        });

        dataViewModel.getLanguages().observe(this, new Observer<List<Speciality>>() {
            @Override
            public void onChanged(List<Speciality> specialities) {
                mainLanguagesList = specialities;
                stringsLanguages = new ArrayList<>();
                stringsLanguages.add(SearchFilterDoctor.this.getResources().getString(R.string.select_lang));
                if (specialities.size() > 0) {
                    for (Speciality language : specialities) {
                        stringsLanguages.add(language.getName());
                    }
                    Utils.setupSpinner(SearchFilterDoctor.this, stringsLanguages, langSpinner);
                    languageDone = true;
                    checkDone();
                }
            }
        });
    }

    private void goForSearch() {

        String areaId = "null";
        String qualificationsId = "null";
        String insuranceId = "null";
        String nationalityId = "null";
        String languageId = "null";
        String genderId = "null";


        if (areaSpinner.getSelectedItemId() != 0) {
            String area = stringsArea.get(areaSpinner.getSelectedItemPosition());
            for (MyArea mArea : mainAreaList) {
                if (mArea.getName().equals(area)) {
                    areaId = String.valueOf(mArea.getId());
                    break;
                }
            }
        }
        if (qualificationSpinner.getSelectedItemId() != 0) {
            String qualification = stringsQualifications.get(qualificationSpinner.getSelectedItemPosition());
            for (Speciality mSpeciality : mainQualificationList) {
                if (mSpeciality.getName().equals(qualification)) {
                    qualificationsId = String.valueOf(mSpeciality.getId());
                    break;
                }
            }
        }
        if (insuranceSpinner.getSelectedItemId() != 0) {
            String insurance = stringsInsurance.get(insuranceSpinner.getSelectedItemPosition());
            for (Insurance mInsurance : mainInsuranceList) {
                if (mInsurance.getName().equals(insurance)) {
                    insuranceId = String.valueOf(mInsurance.getId());
                    break;
                }
            }
        }

        if (nationalitySpinner.getSelectedItemId() != 0) {
            String nationality = stringsNationalities.get(nationalitySpinner.getSelectedItemPosition());
            for (Speciality mSpeciality : mainNationalitiesList) {
                if (mSpeciality.getName().equals(nationality)) {
                    nationalityId = String.valueOf(mSpeciality.getId());
                    break;
                }
            }
        }
        if (langSpinner.getSelectedItemId() != 0) {
            String language = stringsLanguages.get(langSpinner.getSelectedItemPosition());
            for (Speciality speciality : mainLanguagesList) {
                if (speciality.getName().equals(language)) {
                    languageId = String.valueOf(speciality.getId());
                    break;
                }
            }
        }
        if (maleButton.isChecked()) {
            genderId = "male";
        }
        if (femaleButton.isChecked()) {
            genderId = "female";
        }


        Intent intent = new Intent(SearchFilterDoctor.this, SearchResultDoctorActivity.class);
        intent.putExtra("specialityId", String.valueOf(specialityId));
        intent.putExtra("insuranceId", insuranceId);
        intent.putExtra("areaId", areaId);
        intent.putExtra("searchDate", searchDate);

        intent.putExtra("qualificationId", qualificationsId);
        intent.putExtra("languageId", languageId);
        intent.putExtra("genderId", genderId);
        intent.putExtra("nationalityId", nationalityId);

        startActivity(intent);

    }
}
