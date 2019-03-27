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

import com.yackeenSolution.mydocapp.Adapters.DoctorFavoriteAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.FavouriteDoctor;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults.AppointmentRequestActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults.DoctorDetailsActivity;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;

import java.util.List;

public class FavoriteDoctorFrag extends Fragment {

    private RecyclerView doctorResultRecycleView;
    private DoctorFavoriteAdapter doctorFavoriteAdapter;
    private LinearLayout progress;
    private DataViewModel dataViewModel;
    private LinearLayout noData;

    @Override
    public void onResume() {
        super.onResume();
        progress.setVisibility(View.VISIBLE);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;


        View rootView = inflater.inflate(R.layout.favorites_doc_frag, nullParent);

        progress = rootView.findViewById(R.id.fav_doc_frag_progress_bar_layout);
        noData = rootView.findViewById(R.id.fav_doc_no_data);

        doctorResultRecycleView = rootView.findViewById(R.id.fav_doctor_recycler);
        doctorResultRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        doctorResultRecycleView.setHasFixedSize(true);
        doctorFavoriteAdapter = new DoctorFavoriteAdapter();
        doctorResultRecycleView.setAdapter(doctorFavoriteAdapter);


        doctorFavoriteAdapter.setOnItemReqClickListener(new DoctorFavoriteAdapter.OnItemReqClickListener() {
            @Override
            public void onItemClick(FavouriteDoctor favouriteDoctor) {
                Intent intent = new Intent(getContext(), AppointmentRequestActivity.class);
                intent.putExtra("doctorId", String.valueOf(favouriteDoctor.getId()));
                intent.putExtra("facilityId", String.valueOf(favouriteDoctor.getFacilityId()));
                intent.putExtra("speciality", String.valueOf(favouriteDoctor.getSpecialityId()));
                startActivity(intent);
            }
        });

        doctorFavoriteAdapter.setOnItemClickListener(new DoctorFavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FavouriteDoctor favouriteDoctor) {
                Intent intent = new Intent(getContext(), DoctorDetailsActivity.class);
                intent.putExtra("doctorId", String.valueOf(favouriteDoctor.getId()));
                intent.putExtra("facilityId", String.valueOf(favouriteDoctor.getFacilityId()));
                intent.putExtra("speciality", String.valueOf(favouriteDoctor.getSpecialityId()));
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void setUpData() {
        dataViewModel.getMyFavDoctorsList(Integer.parseInt(SaveSharedPreference.getUserId(getActivity()))).observe(this, new Observer<List<FavouriteDoctor>>() {
            @Override
            public void onChanged(List<FavouriteDoctor> favouriteDoctors) {
                progress.setVisibility(View.GONE);
                if (favouriteDoctors.size() > 0) {
                    for (FavouriteDoctor favouriteDoctor : favouriteDoctors) {
                        favouriteDoctor.setIsFav(true);
                    }
                    doctorResultRecycleView.setVisibility(View.VISIBLE);
                    doctorFavoriteAdapter.submitList(favouriteDoctors);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
