package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Fade;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments.ClinicInfoFragment;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments.ProfileFragment;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FavouriteDoctor;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorDetailsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ImageView back;
    Button request;
    String doctorId;
    List<Integer> favouriteDoctorList = new ArrayList<>();
    DataViewModel dataViewModel;
    ConstraintLayout dataLayout;
    LinearLayout progress;
    CircleImageView mail, call, proPic, fav, share;
    TextView name, location;
    ImageView locationImage;
    DoctorResult mainDoctorResult;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_doctor_details);
        ConstraintLayout constraintLayout = findViewById(R.id.doctor_detail_root);
        Utils.RTLSupport(this, constraintLayout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        doctorId = intent.getStringExtra("doctorId");

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        progress = findViewById(R.id.doctor_detail_progress_bar_layout);
        dataLayout = findViewById(R.id.doctor_detail_data_layout);
        name = findViewById(R.id.doctor_details_name);
        location = findViewById(R.id.doctor_detail_location_text);
        locationImage = findViewById(R.id.doctor_detail_location_image);

        mail = findViewById(R.id.doctor_detail_mail);
        call = findViewById(R.id.doctor_detail_call);
        proPic = findViewById(R.id.facility_details_profile_image);
        fav = findViewById(R.id.doctor_detail_fav);
        share = findViewById(R.id.doctor_detail_share);

        frameLayout = findViewById(R.id.doctor_details_frame_layout);
        scrollView = findViewById(R.id.doctor_detail_scroll);
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                frameLayout.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        tabLayout = findViewById(R.id.doctor_details_tabs_layout);
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
                        String details = mainDoctorResult.getName() + "\n" + mainDoctorResult.getTitle() + "\n" + mainDoctorResult.getQualification();
                        Bundle profile = new Bundle();
                        profile.putString("profileInfo", details);
                        ProfileFragment profileFragment = new ProfileFragment();
                        profileFragment.setArguments(profile);
                        FragmentTransaction(profileFragment);
                        break;
                    case 1:
                        String detail = mainDoctorResult.getClinicInfo() + "\n";
                        Bundle clinic = new Bundle();
                        clinic.putString("clinicInfo", detail);
                        ClinicInfoFragment clinicInfoFragment = new ClinicInfoFragment();
                        clinicInfoFragment.setArguments(clinic);
                        FragmentTransaction(clinicInfoFragment);
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
        setUp();
    }

    private void setUp() {
        dataViewModel.getMyFavDoctorsList(Integer.parseInt(SaveSharedPreference.getUserId(this))).observe(this, new Observer<List<FavouriteDoctor>>() {
            @Override
            public void onChanged(List<FavouriteDoctor> favouriteDoctors) {
                for (FavouriteDoctor f : favouriteDoctors) {
                    favouriteDoctorList.add(f.getId());
                    setUpData();
                }
            }
        });
    }

    private void setUpData() {
        dataViewModel.getSpecificDoctorData(Integer.parseInt(doctorId)).observe(this, new Observer<List<DoctorResult>>() {
            @Override
            public void onChanged(final List<DoctorResult> doctorResult) {
                progress.setVisibility(View.GONE);
                dataLayout.setVisibility(View.VISIBLE);
                name.setText(doctorResult.get(0).getName());
                location.setText(doctorResult.get(0).getAddress());
                mainDoctorResult = doctorResult.get(0);
                for (int id : favouriteDoctorList) {
                    if (doctorResult.get(0).getId() == id) {
                        doctorResult.get(0).setFav(true);
                    }
                }
                if (doctorResult.get(0).isFav()) {
                    fav.setImageDrawable(getResources().getDrawable(R.drawable.favorite));
                } else {
                    fav.setImageDrawable(getResources().getDrawable(R.drawable.un_favorite));
                }

                mail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.sendMail(doctorResult.get(0).getMail(), DoctorDetailsActivity.this);
                    }
                });

                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.performCall(doctorResult.get(0).getPhoneNumber(), DoctorDetailsActivity.this);
                    }
                });

                Uri uri;
                String imageUri = doctorResult.get(0).getImageUrl();
                if (imageUri.equals("http://yakensolution.cloudapp.net/doctoryadmin/")) {
                    uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
                } else {
                    uri = Uri.parse(imageUri);
                }
                Picasso.get().load(uri).into(proPic);

                fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FavClick(doctorResult.get(0));
                    }
                });

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoShare(doctorResult.get(0));
                    }
                });
                String details = mainDoctorResult.getName() + "\n" + mainDoctorResult.getTitle() + "\n" + mainDoctorResult.getQualification();
                Bundle profile = new Bundle();
                profile.putString("profileInfo", details);
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(profile);
                FragmentTransaction(profileFragment);

                locationImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String loc = doctorResult.get(0).getFacilityLocation();
                        Utils.googleLocation(loc, DoctorDetailsActivity.this, mainDoctorResult.getImageUrl());
                    }
                });
            }
        });
    }

    private void GoShare(DoctorResult doctorResult) {
        // TODO :: what to share?
    }

    private void FavClick(DoctorResult doctorResult) {
        // TODO :: post to ((API)) fav state change

        if (doctorResult.isFav()) {
            doctorResult.setFav(false);
            fav.setImageDrawable(getResources().getDrawable(R.drawable.un_favorite));
        } else {
            doctorResult.setFav(true);
            fav.setImageDrawable(getResources().getDrawable(R.drawable.favorite));
        }
    }

    private void FragmentTransaction(Fragment fragment) {

        fragment.setEnterTransition(new Fade(Fade.IN));
        fragment.setExitTransition(new Fade(Fade.OUT));

        fragmentManager = DoctorDetailsActivity.this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.doctor_details_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}