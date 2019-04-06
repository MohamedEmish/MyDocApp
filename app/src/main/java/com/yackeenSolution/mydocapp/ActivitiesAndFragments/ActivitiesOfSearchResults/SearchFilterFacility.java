package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class SearchFilterFacility extends AppCompatActivity {

    Button search;
    ImageView back;
    int specialityId;
    String insuranceId,
            areaId;
    Spinner areaSpinner,
            insuranceSpinner,
            facilityTypeSpinner;
    DataViewModel dataViewModel;
    List<String> stringsInsurance,
            stringsArea,
            stringsFacilityType;
    List<MyArea> mainAreaList;
    List<Insurance> mainInsuranceList;
    List<Speciality> mainFacilityTypeList;
    TextView clear;
    LinearLayout dataLayout, progress;
    boolean insuranceDone = false,
            facilityTypeDone = false,
            areaDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_search_filter_facility);
        LinearLayout linearLayout = findViewById(R.id.facility_filter_root);
        Utils.RTLSupport(this, linearLayout);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        Intent intent = getIntent();
        specialityId = Integer.parseInt(intent.getStringExtra("specialityId"));
        insuranceId = intent.getStringExtra("insuranceId");
        areaId = intent.getStringExtra("areaId");

        search = findViewById(R.id.doctor_filter_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFilter();
            }
        });

        dataLayout = findViewById(R.id.search_facility_data_layout);
        progress = findViewById(R.id.search_facility_progress_layout);

        back = findViewById(R.id.facility_filter_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        areaSpinner = findViewById(R.id.search_filter_area_spinner);
        insuranceSpinner = findViewById(R.id.search_filter_insurance_spinner);
        facilityTypeSpinner = findViewById(R.id.search_filter_facility_type_spinner);

        clear = findViewById(R.id.search_facility_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });
        setupDate();
    }

    private void clearAll() {
        areaSpinner.setSelection(0);
        insuranceSpinner.setSelection(0);
        facilityTypeSpinner.setSelection(0);
    }

    private void doFilter() {

        String areaId = "null";
        String facilityTypeId = "null";
        String insuranceId = "null";

        if (areaSpinner.getSelectedItemId() != 0) {
            String area = stringsArea.get(areaSpinner.getSelectedItemPosition());
            for (MyArea mArea : mainAreaList) {
                if (mArea.getName().equals(area)) {
                    areaId = String.valueOf(mArea.getId());
                    break;
                }
            }
        }
        if (facilityTypeSpinner.getSelectedItemId() != 0) {
            String facilityType = stringsFacilityType.get(facilityTypeSpinner.getSelectedItemPosition());
            for (Speciality mSpeciality : mainFacilityTypeList) {
                if (mSpeciality.getName().equals(facilityType)) {
                    facilityTypeId = String.valueOf(mSpeciality.getId());
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
        Intent intent = new Intent(SearchFilterFacility.this, SearchResultsFacilityActivity.class);

        intent.putExtra("specialityId", String.valueOf(specialityId));
        intent.putExtra("insuranceId", insuranceId);
        intent.putExtra("areaId", areaId);
        intent.putExtra("FacilityTypeId", facilityTypeId);

        startActivity(intent);
    }

    private void checkDone() {
        if (insuranceDone && facilityTypeDone && areaDone) {
            progress.setVisibility(View.GONE);
            dataLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setupDate() {
        dataViewModel.getMyInsuranceList(this).observe(this, new Observer<List<Insurance>>() {
            @Override
            public void onChanged(List<Insurance> insurances) {
                mainInsuranceList = insurances;
                stringsInsurance = new ArrayList<>();
                stringsInsurance.add(SearchFilterFacility.this.getResources().getString(R.string.select_insurance));
                if (insurances.size() > 0) {
                    for (Insurance insurance : insurances) {
                        stringsInsurance.add(insurance.getName());
                    }
                    Utils.setupSpinner(SearchFilterFacility.this, stringsInsurance, insuranceSpinner);
                    insuranceDone = true;
                    checkDone();
                }
            }
        });

        dataViewModel.getMyAreaList(this).observe(this, new Observer<List<MyArea>>() {
            @Override
            public void onChanged(List<MyArea> areas) {
                mainAreaList = areas;
                stringsArea = new ArrayList<>();
                stringsArea.add(SearchFilterFacility.this.getResources().getString(R.string.select_area));
                if (areas.size() > 0) {
                    for (MyArea area : areas) {
                        stringsArea.add(area.getName());
                    }
                    Utils.setupSpinner(SearchFilterFacility.this, stringsArea, areaSpinner);
                    areaDone = true;
                    checkDone();
                }
            }
        });

        dataViewModel.getMyFacilityTypes(this).observe(this, new Observer<List<Speciality>>() {
            @Override
            public void onChanged(List<Speciality> specialities) {
                mainFacilityTypeList = specialities;
                stringsFacilityType = new ArrayList<>();
                stringsFacilityType.add(SearchFilterFacility.this.getResources().getString(R.string.select_fac_type));
                if (specialities.size() > 0) {
                    for (Speciality facilityType : specialities) {
                        stringsFacilityType.add(facilityType.getName());
                    }
                    Utils.setupSpinner(SearchFilterFacility.this, stringsFacilityType, facilityTypeSpinner);
                    facilityTypeDone = true;
                    checkDone();
                }
            }
        });
    }
}