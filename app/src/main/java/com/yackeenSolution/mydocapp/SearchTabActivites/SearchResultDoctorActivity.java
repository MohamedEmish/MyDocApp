package com.yackeenSolution.mydocapp.SearchTabActivites;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Adapters.DoctorResultAdapter;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchResultDoctorActivity extends AppCompatActivity {


    RecyclerView doctorResultRecycleView;
    DoctorResultAdapter doctorResultAdapter;

    List<DoctorResult> data = new ArrayList<>();

    ImageView back;
    TextView filter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Localization
        String language = SaveSharedPreference.getLanguage(this);
        Locale locale = new Locale(language);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

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
            public void onItemClick(DoctorResult doctorResult) {
                if (doctorResult.getFavorite()) {
                    doctorResult.setFavorite(false);
                    doctorResultAdapter.notifyDataSetChanged();
                } else {
                    doctorResult.setFavorite(true);
                    doctorResultAdapter.notifyDataSetChanged();
                }
            }
        });

        doctorResultAdapter.setOnItemReqClickListener(new DoctorResultAdapter.OnItemReqClickListener() {
            @Override
            public void onItemClick(DoctorResult doctorResult) {
                Intent intent = new Intent(SearchResultDoctorActivity.this, AppointmentRequestActivity.class);
                startActivity(intent);
            }
        });

        doctorResultAdapter.setOnItemClickListener(new DoctorResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DoctorResult doctorResult) {
                // TODO : attach doctor id
                Intent intent = new Intent(SearchResultDoctorActivity.this, DoctorDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void goBack() {
        finish();
    }

    private void doFilter() {
        Intent intent = new Intent(SearchResultDoctorActivity.this, SearchFilterDoctor.class);
        startActivity(intent);
    }

}
