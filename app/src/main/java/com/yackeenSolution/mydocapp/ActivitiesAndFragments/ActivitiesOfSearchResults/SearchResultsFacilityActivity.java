package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Adapters.FacilityResultAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.FacilityResult;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsFacilityActivity extends AppCompatActivity {

    RecyclerView facilityResultRecycleView;
    FacilityResultAdapter facilityResultAdapter;
    DataViewModel dataViewModel;
    ImageView back;
    TextView filter;
    List<Integer> favoriteIdList = new ArrayList<>();

    int specialityId;
    String insuranceId, areaId, facilityTypeId;
    LinearLayout progress, dataLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_search_results_facility);
        LinearLayout linearLayout = findViewById(R.id.search_result_facility_root);
        Utils.RTLSupport(this, linearLayout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();

        specialityId = Integer.parseInt(intent.getStringExtra("specialityId"));
        insuranceId = intent.getStringExtra("insuranceId");
        areaId = intent.getStringExtra("areaId");

        if (intent.hasExtra("facilityTypeId")) {
            facilityTypeId = intent.getStringExtra("facilityTypeId");
        } else {
            facilityTypeId = "null";
        }

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        progress = findViewById(R.id.facility_search_result_progress_bar_layout);
        dataLayout = findViewById(R.id.facility_search_result_data_layout);


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

        setUpData();
    }

    private void setUpData() {
        dataViewModel.getMyFavFacilitiesList(Integer.parseInt(SaveSharedPreference.getUserId(this))).observe(this, new Observer<List<FacilityResult>>() {
            @Override
            public void onChanged(List<FacilityResult> facilityResults) {
                for (FacilityResult favFacility : facilityResults) {
                    favoriteIdList.add(favFacility.getId());
                }
                setUp();
            }
        });
    }


    private void setUp() {

        Integer area, insurance, facilityType;

        if (areaId.equals("null")) {
            area = null;
        } else {
            area = Integer.parseInt(areaId);
        }

        if (insuranceId.equals("null")) {
            insurance = null;
        } else {
            insurance = Integer.parseInt(insuranceId);
        }

        if (!facilityTypeId.equals("null")) {
            facilityType = Integer.parseInt(facilityTypeId);
        } else {
            facilityType = null;
        }

        dataViewModel.getSearchForFacilityResults(
                specialityId,
                area,
                insurance,
                facilityType).observe(this, new Observer<List<FacilityResult>>() {
            @Override
            public void onChanged(List<FacilityResult> facilityResults) {
                progress.setVisibility(View.GONE);
                dataLayout.setVisibility(View.VISIBLE);
                for (FacilityResult favRes : facilityResults) {
                    for (int id : favoriteIdList) {
                        if (favRes.getId() != id) {
                            favRes.setFav(false);
                        } else {
                            favRes.setFav(true);
                        }
                    }
                }
                facilityResultAdapter.submitList(facilityResults);
            }
        });
    }


    private void openFacilityPage(int id) {
        Intent intent = new Intent(SearchResultsFacilityActivity.this, FacilityDetailsActivity.class);
        intent.putExtra("facilityId", String.valueOf(id));
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
        intent.putExtra("specialityId", String.valueOf(specialityId));
        intent.putExtra("insuranceId", insuranceId);
        intent.putExtra("areaId", areaId);
        startActivity(intent);
    }

}
