package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog.SignInActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments.AboutUsFragment;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments.GeneralInfoFragment;
import com.yackeenSolution.mydocapp.Adapters.DoctorSmallAdapter;
import com.yackeenSolution.mydocapp.Adapters.InsuranceSmallAdapter;
import com.yackeenSolution.mydocapp.Adapters.SpecialitySmallAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FacilityResult;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.NewFavFacility;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class FacilityDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FrameLayout frameLayout;
    private TextView
            facilityName,
            facilityAddress;

    private CircleImageView
            web,
            call,
            proPic,
            fav,
            share;

    private ConstraintLayout dataLayout;
    private LinearLayout progress;

    private FacilityResult mainFacilityResult;
    private DataViewModel dataViewModel;

    GoogleMap mMap;
    private Double
            v = 0.0,
            v1 = 0.1;
    private String facilityId;

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
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

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
        ScrollView scrollView = findViewById(R.id.facility_detail_scroll);
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                frameLayout.getParent().requestDisallowInterceptTouchEvent(false);
                v.performClick();
                return false;
            }
        });

        dataLayout = findViewById(R.id.facility_detail_data_layout);
        progress = findViewById(R.id.facility_detail_progress_bar_layout);

        TabLayout tabLayout = findViewById(R.id.facility_details_tabs_layout);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (tab != null) {
            tab.select();
        }

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
                        String detail;
                        if (mainFacilityResult.getAddress() != null && !mainFacilityResult.getAddress().isEmpty()) {
                            detail = Html.fromHtml(mainFacilityResult.getAddress()) + "\n";
                        } else {
                            detail = null;
                        }
                        Bundle aboutUs = new Bundle();
                        aboutUs.putString("aboutUs", detail);
                        AboutUsFragment aboutUsFragment = new AboutUsFragment();
                        aboutUsFragment.setArguments(aboutUs);
                        FragmentTransaction(aboutUsFragment);
                        break;
                    case 1:
                        String details;
                        if (mainFacilityResult.getGeneralInfo() != null && !mainFacilityResult.getGeneralInfo().isEmpty()) {
                            details = Html.fromHtml(mainFacilityResult.getGeneralInfo()) + "\n";
                        } else {
                            details = null;
                        }
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

        ImageView back = findViewById(R.id.facility_filter_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setUpData();
    }

    private void setUpData() {
        String name = SaveSharedPreference.getUserId(this);
        int id;
        if (name != null && !name.isEmpty()) {
            id = Integer.parseInt(name);
        } else {
            id = 0;
        }
        dataViewModel.getSpecificFacilityData(Integer.parseInt(facilityId), id).observe(this, new Observer<FacilityResult>() {
            @Override
            public void onChanged(final FacilityResult facilityResult) {
                if (facilityResult != null) {
                    progress.setVisibility(View.GONE);
                    dataLayout.setVisibility(View.VISIBLE);

                    if (facilityResult.getName() != null && !facilityResult.getName().isEmpty()) {
                        facilityName.setText(facilityResult.getName().trim());
                    }
                    if (facilityResult.getAddress() != null && !facilityResult.getAddress().isEmpty()) {
                        facilityAddress.setText(facilityResult.getAddress());
                    }

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

                    Uri uri;
                    String imageUri = facilityResult.getImageUrl();
                    if (imageUri != null && !imageUri.isEmpty()) {
                        if (imageUri.equals("http://yakensolution.cloudapp.net/doctoryadmin/")) {
                            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/hospital_default");
                        } else {
                            uri = Uri.parse(imageUri);
                        }
                        Picasso.get().load(uri).into(proPic);
                    } else {
                        uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/hospital_default");
                        Picasso.get().load(uri).into(proPic);
                    }

                    if (facilityResult.isFav()) {
                        fav.setImageDrawable(getResources().getDrawable(R.drawable.favorite));
                    } else {
                        fav.setImageDrawable(getResources().getDrawable(R.drawable.un_favorite));
                    }
                    fav.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FavClick(facilityResult);
                        }
                    });

                    if (facilityResult.getLocation() != null && !facilityResult.getLocation().isEmpty()) {
                        String loc = facilityResult.getLocation();
                        if (loc != null) {
                            String[] values = loc.split(",");
                            v = Double.valueOf(values[0]);
                            v1 = Double.valueOf(values[1]);
                            LatLng latLng = new LatLng(v, v1);
                            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(latLng, latLng));
                            LatLng markLocation = new LatLng(v, v1);
                            mMap.addMarker(new MarkerOptions()
                                    .position(markLocation)
                                    .draggable(true));
                        }
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

                    String detail;
                    if (mainFacilityResult.getAddress() != null && !mainFacilityResult.getAddress().isEmpty()) {
                        detail = mainFacilityResult.getAddress() + "\n";
                    } else {
                        detail = null;
                    }
                    Bundle aboutUs = new Bundle();
                    aboutUs.putString("aboutUs", detail);
                    AboutUsFragment aboutUsFragment = new AboutUsFragment();
                    aboutUsFragment.setArguments(aboutUs);
                    FragmentTransaction(aboutUsFragment);

                    setUpDoctorData(facilityResult.getDoctorsList());
                    setUpInsuranceData(facilityResult.getInsuranceList());
                    setUpSpecialityData(facilityResult.getSpecialityList());
                }
            }
        });
    }

    private void FavClick(FacilityResult facilityResult) {

        String id = SaveSharedPreference.getUserId(this);
        if (id != null && !id.isEmpty()) {
            if (facilityResult.isFav()) {
                NewFavFacility facility = new NewFavFacility();
                facility.setUserId(Integer.parseInt(SaveSharedPreference.getUserId(FacilityDetailsActivity.this)));
                facility.setFacilityId(facilityResult.getId());
                facility.setFav(false);
                dataViewModel.setFacilityFavState(facility);
                fav.setImageDrawable(getResources().getDrawable(R.drawable.un_favorite));
            } else {
                NewFavFacility facility = new NewFavFacility();
                facility.setUserId(Integer.parseInt(SaveSharedPreference.getUserId(FacilityDetailsActivity.this)));
                facility.setFacilityId(facilityResult.getId());
                facility.setFav(true);
                dataViewModel.setFacilityFavState(facility);
                fav.setImageDrawable(getResources().getDrawable(R.drawable.favorite));
            }
        } else {
            showLogInDialog(this);
        }
    }

    private void setUpInsuranceData(List<Insurance> insuranceList) {

        RecyclerView insuranceRecycle = findViewById(R.id.facility_detail_insurance_recycler);
        insuranceRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        insuranceRecycle.setHasFixedSize(true);
        InsuranceSmallAdapter insuranceSmallAdapter = new InsuranceSmallAdapter();
        insuranceRecycle.setAdapter(insuranceSmallAdapter);
        insuranceSmallAdapter.submitList(insuranceList);
    }

    private void setUpSpecialityData(List<Speciality> specialityList) {

        RecyclerView specialityRecycle = findViewById(R.id.facility_detail_speciality_recycler);
        specialityRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        specialityRecycle.setHasFixedSize(true);
        SpecialitySmallAdapter specialitySmallAdapter = new SpecialitySmallAdapter();
        specialityRecycle.setAdapter(specialitySmallAdapter);
        specialitySmallAdapter.submitList(specialityList);

    }

    private void setUpDoctorData(List<DoctorResult> doctorsList) {

        RecyclerView doctorRecycle = findViewById(R.id.facility_detail_doctor_recycler);
        doctorRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        doctorRecycle.setHasFixedSize(true);
        DoctorSmallAdapter doctorSmallAdapter = new DoctorSmallAdapter();
        doctorRecycle.setAdapter(doctorSmallAdapter);
        doctorSmallAdapter.submitList(doctorsList);
        doctorSmallAdapter.setOnItemClickListener(new DoctorSmallAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DoctorResult doctorResult) {
                Intent intent = new Intent(FacilityDetailsActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("doctorId", String.valueOf(doctorResult.getId()));
                startActivity(intent);
            }
        });

    }

    private void GoShare(FacilityResult facilityResult) {
        Utils.shareDirection(facilityResult.getAddress(), this);
    }

    private void FragmentTransaction(Fragment fragment) {

        fragment.setEnterTransition(new Fade(Fade.IN));
        fragment.setExitTransition(new Fade(Fade.OUT));

        FragmentManager fragmentManager = FacilityDetailsActivity.this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
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
        mMap.addMarker(new MarkerOptions()
                .position(markLocation)
                .draggable(true));
    }

    private void showLogInDialog(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.logout_dialog, nullParent, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setView(view);

        TextView textView = view.findViewById(R.id.alert_dialog_text);
        textView.setText(context.getResources().getString(R.string.u_must_login));
        Button yes = view.findViewById(R.id.alert_dialog_yes);
        yes.setText(context.getResources().getString(R.string.log_in));
        Button no = view.findViewById(R.id.alert_dialog_no);
        no.setText(context.getResources().getString(R.string.cancel));
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
                alertDialog.cancel();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

            }
        });
    }

    private void logIn() {
        Intent intent = new Intent(FacilityDetailsActivity.this, SignInActivity.class);
        startActivity(intent);
    }
}