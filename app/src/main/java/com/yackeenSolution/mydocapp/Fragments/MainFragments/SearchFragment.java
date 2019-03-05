package com.yackeenSolution.mydocapp.Fragments.MainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SearchTabActivites.SearchResultDoctorActivity;
import com.yackeenSolution.mydocapp.SearchTabActivites.SearchResultsFacilityActivity;

public class SearchFragment extends Fragment {
    RadioButton doctor;
    RadioGroup type;
    Button search;
    private RadioButton facility;

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
