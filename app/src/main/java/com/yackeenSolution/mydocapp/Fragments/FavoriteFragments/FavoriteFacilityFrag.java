package com.yackeenSolution.mydocapp.Fragments.FavoriteFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.Adapters.FacilityResultAdapter;
import com.yackeenSolution.mydocapp.Objects.FacilityResult;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SearchTabActivites.FacilityDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFacilityFrag extends Fragment {
    RecyclerView facilityResultRecycleView;
    FacilityResultAdapter facilityResultAdapter;
    List<FacilityResult> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;
        View rootView = inflater.inflate(R.layout.favorites_facility_frag, nullParent);

        facilityResultRecycleView = rootView.findViewById(R.id.fav_facility_recycler);
        facilityResultRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
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

        return rootView;

    }

    private void openFacilityPage(int id) {
        // TODO : attach facility id
        Intent intent = new Intent(getContext(), FacilityDetailsActivity.class);
        startActivity(intent);
    }

    private void seeLocation(String location) {
        // TODO : location
        Toast.makeText(getContext(), "This is location", Toast.LENGTH_SHORT).show();
    }

    private void openWeb(String web) {
        // TODO : web
        Toast.makeText(getContext(), "this is web", Toast.LENGTH_SHORT).show();
    }

    private void phoneCall(int number) {
        // TODO : call
        Toast.makeText(getContext(), "this is call", Toast.LENGTH_SHORT).show();
    }

}
