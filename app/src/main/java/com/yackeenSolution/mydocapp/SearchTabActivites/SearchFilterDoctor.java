package com.yackeenSolution.mydocapp.SearchTabActivites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils;

public class SearchFilterDoctor extends AppCompatActivity {

    private ImageView mFilterBack;
    private Button mFilterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_search_filter_doctor);
        LinearLayout linearLayout = findViewById(R.id.doctor_filter_root);
        Utils.RTLSupport(this, linearLayout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mFilterBack = findViewById(R.id.facility_filter_back);
        mFilterButton = findViewById(R.id.doctor_filter_button);

        mFilterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO :: get filter doctorData
                Intent intent = new Intent(SearchFilterDoctor.this, SearchResultDoctorActivity.class);
                // TODO : attach new url
                startActivity(intent);
            }
        });
    }
}
