package com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfFavoritesTab;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Adapters.FacilityResultAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.FacilityResult;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults.FacilityDetailsActivity;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

public class FavoriteFacilityFrag extends Fragment {
    private RecyclerView facilityResultRecycleView;
    private FacilityResultAdapter facilityResultAdapter;
    private DataViewModel dataViewModel;
    private LinearLayout progress;
    private LinearLayout noData;

    @Override
    public void onResume() {
        super.onResume();
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;
        View rootView = inflater.inflate(R.layout.favorites_facility_frag, nullParent);


        progress = rootView.findViewById(R.id.fav_facility_frag_progress_bar_layout);
        noData = rootView.findViewById(R.id.fav_facility_no_data);

        facilityResultRecycleView = rootView.findViewById(R.id.fav_facility_recycler);
        facilityResultRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        facilityResultRecycleView.setHasFixedSize(true);
        facilityResultAdapter = new FacilityResultAdapter();
        facilityResultRecycleView.setAdapter(facilityResultAdapter);

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
        return rootView;

    }

    private void openFacilityPage(int id) {
        Intent intent = new Intent(getContext(), FacilityDetailsActivity.class);
        intent.putExtra("facilityId", String.valueOf(id));
        startActivity(intent);
    }

    private void seeLocation(String location, String imageUrl) {
        Utils.googleLocation(location, getContext(), imageUrl);
    }

    private void openWeb(String web) {
        Utils.openBrowser(web, getContext());
    }

    private void phoneCall(String number) {
        Utils.performCall(number, getContext());
    }

    private void setUpData() {
        dataViewModel.getMyFavFacilitiesList(Integer.parseInt(SaveSharedPreference.getUserId(getActivity()))).observe(this, new Observer<List<FacilityResult>>() {
            @Override
            public void onChanged(List<FacilityResult> facilityResults) {
                progress.setVisibility(View.GONE);
                if (facilityResults.size() > 0) {
                    for (FacilityResult facilityResult : facilityResults) {
                        facilityResult.setFav(true);
                    }
                    facilityResultRecycleView.setVisibility(View.VISIBLE);
                    facilityResultAdapter.submitList(facilityResults);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
