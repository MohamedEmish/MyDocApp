package com.yackeenSolution.mydocapp.Fragments.MainFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.yackeenSolution.mydocapp.MoreTabActivities.MyAccount;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SearchTabActivites.SearchResultDoctorActivity;
import com.yackeenSolution.mydocapp.SearchTabActivites.SearchResultsFacilityActivity;
import com.yackeenSolution.mydocapp.Utils;

public class SearchFragment extends Fragment {
    private RadioButton doctor;
    private RadioGroup type;
    private Button search;
    private RadioButton facility;
    private LinearLayout doctorLayout, facilityLayout;
    private Spinner specialitySpinner, areaSpinner, insuranceSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        View rootView = inflater.inflate(R.layout.search_frag, nullParent);

        search = rootView.findViewById(R.id.search_frag_button);
        type = rootView.findViewById(R.id.search_type_radio_group);
        doctor = rootView.findViewById(R.id.search_doctor_radio_button);
        doctor.setChecked(true);

        facility = rootView.findViewById(R.id.search_facility_radio_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchResults();
            }
        });

        doctorLayout = rootView.findViewById(R.id.search_doctor_radio_layout);
        doctorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctor.setChecked(true);
            }
        });
        facilityLayout = rootView.findViewById(R.id.search_facility_radio_layout);
        facilityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facility.setChecked(true);
            }
        });

        specialitySpinner = rootView.findViewById(R.id.search_speciality_spinner);
        String[] speciality = getContext().getResources().getStringArray(R.array.array_speciality_options);
        Utils.setupSpinner(getContext(), speciality, specialitySpinner);

        insuranceSpinner = rootView.findViewById(R.id.search_insurance_spinner);
        String[] insurance = getContext().getResources().getStringArray(R.array.array_insurance_options);
        Utils.setupSpinner(getContext(), insurance, insuranceSpinner);

        areaSpinner = rootView.findViewById(R.id.search_area_spinner);
        String[] area = getContext().getResources().getStringArray(R.array.array_area_options);
        Utils.setupSpinner(getContext(), area, areaSpinner);

        return rootView;
    }

    private void openSearchResults() {
        if (facility.isChecked()) {
            Intent intent = new Intent(getContext(), SearchResultsFacilityActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getContext(), SearchResultDoctorActivity.class);
            startActivity(intent);
        }
    }
}
