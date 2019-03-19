package com.yackeenSolution.mydocapp.ActivitiesAndFragments.SearchTabActivites;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Adapters.FacilityResultAdapter;
import com.yackeenSolution.mydocapp.Objects.FacilityResult;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

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
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_search_results_facility);
        LinearLayout linearLayout = findViewById(R.id.search_result_facility_root);
        Utils.RTLSupport(this, linearLayout);
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

//        data.add(new FacilityResult(
//                "Health",
//                "Cairo",
//                77828555,
//                "",
//                "",
//                "",
//                false)
//        );
//
//        data.add(new FacilityResult(
//                "Health",
//                "Cairo",
//                77828555,
//                "",
//                "",
//                "",
//                false)
//        );

        facilityResultAdapter.submitList(data);

        facilityResultAdapter.setOnItemFavClickListener(new FacilityResultAdapter.OnItemFavClickListener() {
            @Override
            public void onItemClick(FacilityResult facilityResult) {
                if (facilityResult.isFav()) {
                    facilityResult.setFav(false);
                    facilityResultAdapter.notifyDataSetChanged();
                } else {
                    facilityResult.setFav(true);
                    facilityResultAdapter.notifyDataSetChanged();
                }
            }
        });

        facilityResultAdapter.setOnItemCallClickListener(new FacilityResultAdapter.OnItemCallClickListener() {
            @Override
            public void onItemClick(FacilityResult facilityResult) {
                String number = facilityResult.getPhoneNumber();
                phoneCall(number);
            }
        });

        facilityResultAdapter.setOnItemWebClickListener(new FacilityResultAdapter.OnItemWebClickListener() {
            @Override
            public void onItemClick(FacilityResult facilityResult) {
                String web = facilityResult.getWebSite();
                openWeb(web);
            }
        });

        facilityResultAdapter.setOnItemLocationClickListener(new FacilityResultAdapter.OnItemLocationClickListener() {
            @Override
            public void onItemClick(FacilityResult facilityResult) {
                String location = facilityResult.getLocation();
                seeLocation(location, facilityResult.getImageUrl());
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
        // TODO : attach facility id
        Intent intent = new Intent(SearchResultsFacilityActivity.this, FacilityDetailsActivity.class);
        startActivity(intent);
    }

    private void seeLocation(String location, String imageUrl) {
        Utils.googleLocation(location, SearchResultsFacilityActivity.this, imageUrl);
    }

    private void openWeb(String web) {
        Utils.openBrowser(web, SearchResultsFacilityActivity.this);
    }

    private void phoneCall(String number) {
        Utils.performCall(number, SearchResultsFacilityActivity.this);
    }

    private void goBack() {
        finish();
    }


    private void doFilter() {
        Intent intent = new Intent(SearchResultsFacilityActivity.this, SearchFilterFacility.class);
        startActivity(intent);
    }

}
