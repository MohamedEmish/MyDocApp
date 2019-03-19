package com.yackeenSolution.mydocapp.ActivitiesAndFragments.SearchTabActivites;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;

import com.google.android.material.tabs.TabLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.Adapters.DoctorSmallAdapter;
import com.yackeenSolution.mydocapp.Adapters.FacilityDetailsFragmentAdapter;
import com.yackeenSolution.mydocapp.Adapters.InsuranceSmallAdapter;
import com.yackeenSolution.mydocapp.Adapters.SpecialitySmallAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FacilityResult;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

public class FacilityDetailsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView back;

    TextView facilityName, facilityAddress;

    RecyclerView doctorRecycle, insuranceRecycle, specialityRecycle;
    CircleImageView web, call, proPic, fav, share;

    ConstraintLayout dataLayout;
    LinearLayout progress;

    DoctorSmallAdapter doctorsmallAdapter;
    InsuranceSmallAdapter insuranceSmallAdapter;
    SpecialitySmallAdapter specialitySmallAdapter;

    DataViewModel dataViewModel;

    ImageView gMap;
    String facilityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_facility_details);
        ConstraintLayout constraintLayout = findViewById(R.id.facility_detail_root);
        Utils.RTLSupport(this, constraintLayout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        facilityId = intent.getStringExtra("facilityId");

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        facilityName = findViewById(R.id.facility_details_title);
        facilityAddress = findViewById(R.id.facility_detail_location_text);
        web = findViewById(R.id.facility_detail_web);
        call = findViewById(R.id.facility_detail_call);
        proPic = findViewById(R.id.facility_details_profile_image);
        fav = findViewById(R.id.facility_detail_fav);
        share = findViewById(R.id.facility_detail_share);

        dataLayout = findViewById(R.id.facility_detail_data_layout);
        progress = findViewById(R.id.facility_detail_progress_bar_layout);

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

        viewPager = findViewById(R.id.facility_details_view_pager);

        FacilityDetailsFragmentAdapter myFragsAdapter = new FacilityDetailsFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragsAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

        setUpData();
        back = findViewById(R.id.facility_filter_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setUpData() {

        dataViewModel.getSpecificFacilityData(Integer.parseInt(facilityId), Integer.parseInt(SaveSharedPreference.getUserId(this))).observe(this, new Observer<FacilityResult>() {
            @Override
            public void onChanged(final FacilityResult facilityResult) {
                facilityName.setText(facilityResult.getName().trim());
                facilityAddress.setText(facilityResult.getAddress());
                progress.setVisibility(View.GONE);
                dataLayout.setVisibility(View.VISIBLE);
                web.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.openBrowser(facilityResult.getWebSite(), FacilityDetailsActivity.this);
                    }
                });
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.performCall(facilityResult.getPhoneNumber(), FacilityDetailsActivity.this);
                    }
                });

                Uri uri = Uri.parse(facilityResult.getImageUrl());
                Picasso.get().load(uri).into(proPic);

                if (facilityResult.isFav()) {
                    fav.setImageDrawable(getResources().getDrawable(R.drawable.favorite));
                } else {
                    fav.setImageDrawable(getResources().getDrawable(R.drawable.un_favorite));
                }

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoShare(facilityResult);
                    }
                });

                gMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.googleLocation(facilityResult.getLocation(), FacilityDetailsActivity.this, facilityResult.getImageUrl());
                    }
                });


                setUpDoctorData(facilityResult.getDoctorsList());
                setUpInsuranceData(facilityResult.getInsuranceList());
                setUpSpecialityData(facilityResult.getSpecialityList());
            }
        });


    }

    private void setUpInsuranceData(List<Insurance> insuranceList) {

        insuranceRecycle = findViewById(R.id.facility_detail_insurance_recycler);
        insuranceRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        insuranceRecycle.setHasFixedSize(true);
        insuranceSmallAdapter = new InsuranceSmallAdapter();
        insuranceRecycle.setAdapter(insuranceSmallAdapter);

        insuranceSmallAdapter.submitList(insuranceList);


    }

    private void setUpSpecialityData(List<Speciality> specialityList) {

        specialityRecycle = findViewById(R.id.facility_detail_speciality_recycler);
        specialityRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        specialityRecycle.setHasFixedSize(true);
        specialitySmallAdapter = new SpecialitySmallAdapter();
        specialityRecycle.setAdapter(specialitySmallAdapter);

        specialitySmallAdapter.submitList(specialityList);

    }

    private void setUpDoctorData(List<DoctorResult> doctorsList) {


        doctorRecycle = findViewById(R.id.facility_detail_doctor_recycler);
        doctorRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        doctorRecycle.setHasFixedSize(true);
        doctorsmallAdapter = new DoctorSmallAdapter();
        doctorRecycle.setAdapter(doctorsmallAdapter);

        doctorsmallAdapter.submitList(doctorsList);
        doctorsmallAdapter.setOnItemClickListener(new DoctorSmallAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DoctorResult doctorResult) {
                Intent intent = new Intent(FacilityDetailsActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("doctorId", String.valueOf(doctorResult.getId()));
                startActivity(intent);
            }
        });

    }

    private void GoShare(FacilityResult facilityResult) {
        // TODO :: what to share?
    }
}
