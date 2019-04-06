package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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

    private FacilityResultAdapter facilityResultAdapter;
    private DataViewModel dataViewModel;
    private List<Integer> favoriteIdList = new ArrayList<>();

    private int specialityId;
    private String
            insuranceId,
            areaId,
            facilityTypeId;
    private LinearLayout
            progress,
            noData;

    private RecyclerView facilityResultRecycleView;

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

        progress = findViewById(R.id.facility_search_result_progress_bar_layout);
        noData = findViewById(R.id.search_results_facility_no_data);


        ImageView back = findViewById(R.id.search_results_facility_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        TextView filter = findViewById(R.id.search_result_facility_filter);
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

    }

    @Override
    protected void onResume() {
        progress.setVisibility(View.VISIBLE);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpData();
        super.onResume();
    }

    private void setUpData() {
        String id = SaveSharedPreference.getUserId(this);
        if (id != null && !id.isEmpty()) {
            dataViewModel.getMyFavFacilitiesList(Integer.parseInt(id), this).observe(this, new Observer<List<FacilityResult>>() {
                @Override
                public void onChanged(List<FacilityResult> facilityResults) {
                    for (FacilityResult favFacility : facilityResults) {
                        favoriteIdList.add(favFacility.getId());
                    }
                    setUp();
                }
            });
        } else {
            setUp();
        }
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
                facilityType,
                this).observe(this, new Observer<List<FacilityResult>>() {
            @Override
            public void onChanged(final List<FacilityResult> facilityResults) {
                progress.setVisibility(View.GONE);
                if (facilityResults.size() > 0) {
                    facilityResultRecycleView.setVisibility(View.VISIBLE);
                    for (FacilityResult favRes : facilityResults) {
                        for (int id : favoriteIdList) {
                            if (favRes.getId() == id) {
                                favRes.setFav(true);
                                break;
                            } else {
                                favRes.setFav(false);
                            }
                        }
                    }

                    facilityResultAdapter.submitList(facilityResults);
                    final List<FacilityResult> filteredList = new ArrayList<>();
                    TextWatcher textWatcher = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (s.length() > 0) {
                                filteredList.clear();
                                for (FacilityResult facility : facilityResults) {
                                    if (facility.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                                        filteredList.add(facility);
                                        facilityResultAdapter.submitList(filteredList);
                                        facilityResultAdapter.notifyDataSetChanged();
                                    }
                                }
                            } else {
                                facilityResultAdapter.submitList(facilityResults);
                            }
                        }
                    };
                    EditText filter = findViewById(R.id.search_facility_filter_text);
                    filter.addTextChangedListener(textWatcher);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }

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
