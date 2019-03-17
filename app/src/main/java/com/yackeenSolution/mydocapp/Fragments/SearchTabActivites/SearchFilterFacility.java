package com.yackeenSolution.mydocapp.Fragments.SearchTabActivites;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

public class SearchFilterFacility extends AppCompatActivity {

    Button search;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_search_filter_facility);
        LinearLayout linearLayout = findViewById(R.id.facility_filter_root);
        Utils.RTLSupport(this, linearLayout);

        search = findViewById(R.id.doctor_filter_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchFilterFacility.this, SearchResultsFacilityActivity.class);
                // TODO : attach new uri
                startActivity(intent);
            }
        });

        back = findViewById(R.id.facility_filter_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
