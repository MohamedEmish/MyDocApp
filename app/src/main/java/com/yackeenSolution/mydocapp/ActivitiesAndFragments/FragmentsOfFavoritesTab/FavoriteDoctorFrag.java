package com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfFavoritesTab;

import android.content.Intent;
import android.os.Bundle;

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

    RecyclerView doctorResultRecycleView;
    DoctorFavoriteAdapter doctorFavoriteAdapter;
    LinearLayout progress;

    DataViewModel dataViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;


        View rootView = inflater.inflate(R.layout.favorites_doc_frag, nullParent);

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        progress = rootView.findViewById(R.id.fav_doc_frag_progress_bar_layout);

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
                startActivity(intent);
            }
        });

        doctorFavoriteAdapter.setOnItemClickListener(new DoctorFavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FavouriteDoctor favouriteDoctor) {
                Intent intent = new Intent(getContext(), DoctorDetailsActivity.class);
                intent.putExtra("doctorId", String.valueOf(favouriteDoctor.getId()));
                startActivity(intent);
            }
        });
        setUpData();


        return rootView;
    }

    private void setUpData() {
        dataViewModel.getMyFavDoctorsList(Integer.parseInt(SaveSharedPreference.getUserId(getActivity()))).observe(this, new Observer<List<FavouriteDoctor>>() {
            @Override
            public void onChanged(List<FavouriteDoctor> favouriteDoctors) {
                if (favouriteDoctors.size() > 0) {
                    progress.setVisibility(View.GONE);
                    for (FavouriteDoctor favouriteDoctor : favouriteDoctors) {
                        favouriteDoctor.setIsFav(true);
                    }
                    doctorResultRecycleView.setVisibility(View.VISIBLE);
                    doctorFavoriteAdapter.submitList(favouriteDoctors);
                }
            }
        });
    }
}
