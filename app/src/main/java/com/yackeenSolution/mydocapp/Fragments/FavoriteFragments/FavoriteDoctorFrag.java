package com.yackeenSolution.mydocapp.Fragments.FavoriteFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yackeenSolution.mydocapp.Adapters.DoctorResultAdapter;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SearchTabActivites.AppointmentRequestActivity;
import com.yackeenSolution.mydocapp.SearchTabActivites.DoctorDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDoctorFrag extends Fragment {

    RecyclerView doctorResultRecycleView;
    DoctorResultAdapter doctorResultAdapter;
    List<DoctorResult> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;


        View rootView = inflater.inflate(R.layout.favorites_doc_frag, nullParent);

        doctorResultRecycleView = rootView.findViewById(R.id.fav_doctor_recycler);
        doctorResultRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        doctorResultRecycleView.setHasFixedSize(true);
        doctorResultAdapter = new DoctorResultAdapter();
        doctorResultRecycleView.setAdapter(doctorResultAdapter);

        data.add(new DoctorResult(
                "Ahmed Alaa",
                "Cairo",
                "Android developer",
                "Yackeen Solution",
                "",
                true)
        );

        data.add(new DoctorResult(
                "Ahmed Alaa",
                "Cairo",
                "Android developer",
                "Yackeen Solution",
                "",
                false)
        );

        data.add(new DoctorResult(
                "Ahmed Alaa",
                "Cairo",
                "Android developer",
                "Yackeen Solution",
                "",
                true)
        );

        data.add(new DoctorResult(
                "Ahmed Alaa",
                "Cairo",
                "Android developer",
                "Yackeen Solution",
                "",
                false)
        );

        doctorResultAdapter.submitList(data);

        doctorResultAdapter.setOnItemFavClickListener(new DoctorResultAdapter.OnItemFavClickListener() {
            @Override
            public void onItemClick(DoctorResult DoctorResult) {
                if (DoctorResult.getFavorite()) {
                    DoctorResult.setFavorite(false);
                    doctorResultAdapter.notifyDataSetChanged();
                } else {
                    DoctorResult.setFavorite(true);
                    doctorResultAdapter.notifyDataSetChanged();
                }
            }
        });

        doctorResultAdapter.setOnItemReqClickListener(new DoctorResultAdapter.OnItemReqClickListener() {
            @Override
            public void onItemClick(DoctorResult DoctorResult) {
                Intent intent = new Intent(getContext(), AppointmentRequestActivity.class);
                startActivity(intent);
            }
        });

        doctorResultAdapter.setOnItemClickListener(new DoctorResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DoctorResult DoctorResult) {
                Intent intent = new Intent(getContext(), DoctorDetailsActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}
