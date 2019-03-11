package com.yackeenSolution.mydocapp.SearchTabActivites;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;

import com.google.android.material.tabs.TabLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Adapters.DoctorDetailsFragmentAdapter;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils;

import java.util.Locale;

public class DoctorDetailsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView back;
    Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_doctor_details);
        ConstraintLayout constraintLayout = findViewById(R.id.doctor_detail_root);
        Utils.RTLSupport(this, constraintLayout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        tabLayout = findViewById(R.id.doctor_details_tabs_layout);
        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.colorGray));
            drawable.setSize(1, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

        viewPager = findViewById(R.id.doctor_details_view_pager);

        DoctorDetailsFragmentAdapter myFragsAdapter = new DoctorDetailsFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragsAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

        back = findViewById(R.id.doctor_details_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        request = findViewById(R.id.doctor_detail_request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorDetailsActivity.this, AppointmentRequestActivity.class);
                startActivity(i);
            }
        });
    }
}
