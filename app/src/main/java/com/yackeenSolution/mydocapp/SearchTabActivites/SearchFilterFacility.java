package com.yackeenSolution.mydocapp.SearchTabActivites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yackeenSolution.mydocapp.R;

public class SearchFilterFacility extends AppCompatActivity {

    Button search;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_facility);

        search = findViewById(R.id.filter_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchFilterFacility.this, SearchResultsFacilityActivity.class);
                // TODO : attach new uri
                startActivity(intent);
            }
        });

        back = findViewById(R.id.facility_details_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchFilterFacility.this, SearchResultsFacilityActivity.class);
                startActivity(intent);
            }
        });
    }
}
