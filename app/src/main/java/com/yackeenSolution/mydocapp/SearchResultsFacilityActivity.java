package com.yackeenSolution.mydocapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.Adapters.DoctorResultAdapter;
import com.yackeenSolution.mydocapp.Adapters.FacilityResultAdapter;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FacilityResult;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsFacilityActivity extends AppCompatActivity {

    RecyclerView facilityResultRecycleView;
    FacilityResultAdapter facilityResultAdapter;

    List<FacilityResult> data = new ArrayList<>();

    ImageView back;
    TextView filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_facility);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        back = findViewById(R.id.search_results_facility_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        filter = findViewById(R.id.search_result_facility_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFilter();
            }
        });

        facilityResultRecycleView = findViewById(R.id.search_results_facility_recycler);
        facilityResultRecycleView.setLayoutManager(new LinearLayoutManager(this));
        facilityResultRecycleView.setHasFixedSize(true);
        facilityResultAdapter = new FacilityResultAdapter();
        facilityResultRecycleView.setAdapter(facilityResultAdapter);

        data.add(new FacilityResult(
                "Health",
                "Cairo",
                77828555,
                "",
                "",
                "",
                false)
        );

        data.add(new FacilityResult(
                "Health",
                "Cairo",
                77828555,
                "",
                "",
                "",
                false)
        );

        facilityResultAdapter.submitList(data);

        facilityResultAdapter.setOnItemFavClickListener(new FacilityResultAdapter.OnItemFavClickListener() {
            @Override
            public void onItemClick(FacilityResult FacilityResult) {
                if (FacilityResult.isFavorite()) {
                    FacilityResult.setFavorite(false);
                    facilityResultAdapter.notifyDataSetChanged();
                } else {
                    FacilityResult.setFavorite(true);
                    facilityResultAdapter.notifyDataSetChanged();
                }
            }
        });

        facilityResultAdapter.setOnItemCallClickListener(new FacilityResultAdapter.OnItemCallClickListener() {
            @Override
            public void onItemClick(FacilityResult FacilityResult) {
                int number = FacilityResult.getPhone();
                phoneCall(number);
            }
        });

        facilityResultAdapter.setOnItemWebClickListener(new FacilityResultAdapter.OnItemWebClickListener() {
            @Override
            public void onItemClick(FacilityResult facilityResult) {
                String web = facilityResult.getWebUri();
                openWeb(web);
            }
        });

        facilityResultAdapter.setOnItemLocationClickListener(new FacilityResultAdapter.OnItemLocationClickListener() {
            @Override
            public void onItemClick(FacilityResult facilityResult) {
                String location = facilityResult.getLocationUri();
                seeLocation(location);
            }
        });

        facilityResultAdapter.setOnItemAllClickListener(new FacilityResultAdapter.OnItemAllClickListener() {
            @Override
            public void onItemClick(FacilityResult facilityResult) {
                int id = facilityResult.getId();
                openFacilityPage(id);
            }
        });
    }

    private void openFacilityPage(int id) {
        // TODO : detail intent
        Toast.makeText(this, "this All", Toast.LENGTH_SHORT).show();
    }

    private void seeLocation(String location) {
        // TODO : location
        Toast.makeText(this, "This is location", Toast.LENGTH_SHORT).show();
    }

    private void openWeb(String web) {
        // TODO : web
        Toast.makeText(this, "this is web", Toast.LENGTH_SHORT).show();
    }

    private void phoneCall(int number) {
        // TODO : call
        Toast.makeText(this, "this is call", Toast.LENGTH_SHORT).show();
    }

    private void goBack() {
        Intent intent = new Intent(SearchResultsFacilityActivity.this, MainScreen.class);
        startActivity(intent);
    }


    private void doFilter() {
        Intent intent = new Intent(SearchResultsFacilityActivity.this, SearchFilterFacility.class);
        startActivity(intent);
    }

}
