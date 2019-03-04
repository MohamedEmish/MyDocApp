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

import com.yackeenSolution.mydocapp.Adapters.DoctorResultAdapter;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDoctorActivity extends AppCompatActivity {


    RecyclerView doctorResultRecycleView;
    DoctorResultAdapter doctorResultAdapter;

    List<DoctorResult> data = new ArrayList<>();

    ImageView back;
    TextView filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_doctor);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        back = findViewById(R.id.search_results_back);
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

        doctorResultRecycleView = findViewById(R.id.search_results_recycler);
        doctorResultRecycleView.setLayoutManager(new LinearLayoutManager(this));
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
                Intent intent = new Intent(SearchResultDoctorActivity.this, AppointmentRequestActivity.class);
                startActivity(intent);
            }
        });

    }

    private void goBack() {
        Intent intent = new Intent(SearchResultDoctorActivity.this, MainScreen.class);
        startActivity(intent);
    }

    private void doFilter() {
        Intent intent = new Intent(SearchResultDoctorActivity.this, SearchFilterDoctor.class);
        startActivity(intent);
    }

}
