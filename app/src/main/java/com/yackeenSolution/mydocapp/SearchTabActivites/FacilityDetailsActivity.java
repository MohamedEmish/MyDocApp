package com.yackeenSolution.mydocapp.SearchTabActivites;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;

import com.google.android.material.tabs.TabLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Adapters.DoctorSmallAdapter;
import com.yackeenSolution.mydocapp.Adapters.FacilityDetailsFragmentAdapter;
import com.yackeenSolution.mydocapp.Adapters.InsuranceSmallAdapter;
import com.yackeenSolution.mydocapp.Adapters.SpecialitySmallAdapter;
import com.yackeenSolution.mydocapp.GoogleMapsActivity;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils;

import java.util.ArrayList;
import java.util.List;

public class FacilityDetailsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView back;

    RecyclerView doctorRecycle, insuranceRecycle, specialityRecycle;

    List<DoctorResult> doctorData = new ArrayList<>();
    List<Insurance> insuranceData = new ArrayList<>();
    List<Speciality> specialityData = new ArrayList<>();

    DoctorSmallAdapter doctorsmallAdapter;
    InsuranceSmallAdapter insuranceSmallAdapter;
    SpecialitySmallAdapter specialitySmallAdapter;

    ImageView gMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_facility_details);
        ConstraintLayout constraintLayout = findViewById(R.id.facility_detail_root);
        Utils.RTLSupport(this, constraintLayout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        tabLayout = findViewById(R.id.facility_details_tabs_layout);
        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.colorGray));
            drawable.setSize(1, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

        gMap = findViewById(R.id.facility_detail_location_image);
        gMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacilityDetailsActivity.this, GoogleMapsActivity.class);
                startActivity(intent);
            }
        });

        viewPager = findViewById(R.id.facility_details_view_pager);

        FacilityDetailsFragmentAdapter myFragsAdapter = new FacilityDetailsFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragsAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

        setUpDoctorData();
        setUpInsuranceData();
        setUpSpecialityData();

        back = findViewById(R.id.facility_filter_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setUpInsuranceData() {
        insuranceData.add(new Insurance(
                "Bupa",
                ""
        ));
        insuranceData.add(new Insurance(
                "Bupa",
                ""
        ));
        insuranceData.add(new Insurance(
                "Bupa",
                ""
        ));
        insuranceData.add(new Insurance(
                "Bupa",
                ""
        ));
        insuranceData.add(new Insurance(
                "Bupa",
                ""
        ));

        insuranceData.add(new Insurance(
                "Bupa",
                ""
        ));
        insuranceData.add(new Insurance(
                "Bupa",
                ""
        ));
        insuranceData.add(new Insurance(
                "Bupa",
                ""
        ));

        insuranceRecycle = findViewById(R.id.facility_detail_insurance_recycler);
        insuranceRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        insuranceRecycle.setHasFixedSize(true);
        insuranceSmallAdapter = new InsuranceSmallAdapter();
        insuranceRecycle.setAdapter(insuranceSmallAdapter);

        insuranceSmallAdapter.submitList(insuranceData);


    }

    private void setUpSpecialityData() {

        specialityData.add(new Speciality(
                "Bupa",
                ""
        ));
        specialityData.add(new Speciality(
                "Bupa",
                ""
        ));
        specialityData.add(new Speciality(
                "Bupa",
                ""
        ));
        specialityData.add(new Speciality(
                "Bupa",
                ""
        ));
        specialityData.add(new Speciality(
                "Bupa",
                ""
        ));
        specialityData.add(new Speciality(
                "Bupa",
                ""
        ));
        specialityData.add(new Speciality(
                "Bupa",
                ""
        ));
        specialityData.add(new Speciality(
                "Bupa",
                ""
        ));

        specialityRecycle = findViewById(R.id.facility_detail_speciality_recycler);
        specialityRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        specialityRecycle.setHasFixedSize(true);
        specialitySmallAdapter = new SpecialitySmallAdapter();
        specialityRecycle.setAdapter(specialitySmallAdapter);

        specialitySmallAdapter.submitList(specialityData);

    }

    private void setUpDoctorData() {

        doctorData.add(new DoctorResult(
                "Ahmed Alaa",
                "Cairo",
                "Android developer",
                "Yackeen Solution",
                "",
                true)
        );

        doctorData.add(new DoctorResult(
                "Ahmed Alaa",
                "Cairo",
                "Android developer",
                "Yackeen Solution",
                "",
                false)
        );

        doctorData.add(new DoctorResult(
                "Ahmed Alaa",
                "Cairo",
                "Android developer",
                "Yackeen Solution",
                "",
                false)
        );


        doctorData.add(new DoctorResult(
                "Ahmed Alaa",
                "Cairo",
                "Android developer",
                "Yackeen Solution",
                "",
                false)
        );

        doctorRecycle = findViewById(R.id.facility_detail_doctor_recycler);
        doctorRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        doctorRecycle.setHasFixedSize(true);
        doctorsmallAdapter = new DoctorSmallAdapter();
        doctorRecycle.setAdapter(doctorsmallAdapter);

        doctorsmallAdapter.submitList(doctorData);
        doctorsmallAdapter.setOnItemClickListener(new DoctorSmallAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DoctorResult doctorResult) {
                // TODO : attach doctor id
                Intent intent = new Intent(FacilityDetailsActivity.this, DoctorDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}
