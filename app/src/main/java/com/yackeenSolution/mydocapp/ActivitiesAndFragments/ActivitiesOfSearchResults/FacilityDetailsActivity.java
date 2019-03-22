package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.transition.Fade;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments.AboutUsFragment;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments.GeneralInfoFragment;
import com.yackeenSolution.mydocapp.Adapters.DoctorSmallAdapter;
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

public class FacilityDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    TabLayout tabLayout;
    ImageView back;
    FrameLayout frameLayout;

    ScrollView scrollView;

    TextView facilityName, facilityAddress;

    RecyclerView doctorRecycle, insuranceRecycle, specialityRecycle;
    CircleImageView web, call, proPic, fav, share;

    ConstraintLayout dataLayout;
    LinearLayout progress;

    FacilityResult mainFacilityResult;

    DoctorSmallAdapter doctorsmallAdapter;
    InsuranceSmallAdapter insuranceSmallAdapter;
    SpecialitySmallAdapter specialitySmallAdapter;

    DataViewModel dataViewModel;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    GoogleMap mMap;
    private Double v = 0.0;
    private Double v1 = 0.1;

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

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.facility_detail_map);
        mapFragment.getMapAsync(this);

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

        frameLayout = findViewById(R.id.facility_details_frame_layout);
        scrollView = findViewById(R.id.facility_detail_scroll);
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                frameLayout.getParent().requestDisallowInterceptTouchEvent(false);

                return false;
            }
        });

        dataLayout = findViewById(R.id.facility_detail_data_layout);
        progress = findViewById(R.id.facility_detail_progress_bar_layout);

        tabLayout = findViewById(R.id.facility_details_tabs_layout);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();

        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.colorGray));
            drawable.setSize(1, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        String detail = mainFacilityResult.getGeneralInfo() + "\n";
                        Bundle aboutUs = new Bundle();
                        aboutUs.putString("aboutUs", detail);
                        AboutUsFragment aboutUsFragment = new AboutUsFragment();
                        aboutUsFragment.setArguments(aboutUs);
                        FragmentTransaction(aboutUsFragment);
                        break;
                    case 1:
                        String details = mainFacilityResult.getName() + "\n\n" + mainFacilityResult.getAddress() + "\n\n" + mainFacilityResult.getArea();
                        Bundle generalInfo = new Bundle();
                        generalInfo.putString("generalInfo", details);
                        GeneralInfoFragment generalInfoFragment = new GeneralInfoFragment();
                        generalInfoFragment.setArguments(generalInfo);
                        FragmentTransaction(generalInfoFragment);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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

                String loc = facilityResult.getLocation();
                if (loc != null) {
                    String[] values = loc.split(",");
                    v = Double.valueOf(values[0]);
                    v1 = Double.valueOf(values[1]);
                    LatLng latLng = new LatLng(v, v1);
                    mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(latLng, latLng));
                    LatLng markLocation = new LatLng(v, v1);
                    Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(markLocation)
                            .draggable(true));
                }

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoShare(facilityResult);
                    }
                });

                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        Utils.googleLocation(facilityResult.getLocation(), FacilityDetailsActivity.this, facilityResult.getImageUrl());

                    }
                });

                mainFacilityResult = facilityResult;

                String detail = mainFacilityResult.getGeneralInfo() + "\n";
                Bundle aboutUs = new Bundle();
                aboutUs.putString("aboutUs", detail);
                AboutUsFragment aboutUsFragment = new AboutUsFragment();
                aboutUsFragment.setArguments(aboutUs);
                FragmentTransaction(aboutUsFragment);

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

    private void FragmentTransaction(Fragment fragment) {

        fragment.setEnterTransition(new Fade(Fade.IN));
        fragment.setExitTransition(new Fade(Fade.OUT));

        fragmentManager = FacilityDetailsActivity.this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.facility_details_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(v, v1);

        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(v, v1));
        CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(location, 15);
        mMap.animateCamera(center);
        mMap.animateCamera(zoom);

        LatLng markLocation = new LatLng(v, v1);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(markLocation)
                .draggable(true));
    }


}
