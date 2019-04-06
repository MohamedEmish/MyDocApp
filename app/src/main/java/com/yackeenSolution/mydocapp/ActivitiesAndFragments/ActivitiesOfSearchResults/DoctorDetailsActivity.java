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
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments.ClinicInfoFragment;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments.ProfileFragment;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FavouriteDoctor;
import com.yackeenSolution.mydocapp.Objects.NewFavDoctor;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorDetailsActivity extends AppCompatActivity {

    private Button request;
    private String doctorId;
    private List<Integer> favouriteDoctorList = new ArrayList<>();
    private DataViewModel dataViewModel;
    private ConstraintLayout dataLayout;
    private LinearLayout progress;
    private CircleImageView mail, call, proPic, fav, share;
    private TextView nameText, location;
    private DoctorResult mainDoctorResult;
    private GoogleMap mMap;
    private Double v = 0.0;
    private Double v1 = 0.1;
    private FrameLayout frameLayout;

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
        nameText = findViewById(R.id.doctor_details_name);
        location = findViewById(R.id.doctor_detail_location_text);

        mail = findViewById(R.id.doctor_detail_mail);
        call = findViewById(R.id.doctor_detail_call);
        proPic = findViewById(R.id.facility_details_profile_image);
        fav = findViewById(R.id.doctor_detail_fav);
        share = findViewById(R.id.doctor_detail_share);

        frameLayout = findViewById(R.id.doctor_details_frame_layout);
        ScrollView scrollView = findViewById(R.id.doctor_detail_scroll);
        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                frameLayout.getParent().requestDisallowInterceptTouchEvent(false);
                v.performClick();
                return false;
            }
        });

        TabLayout tabLayout = findViewById(R.id.doctor_details_tabs_layout);
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
                        String details;
                        if (mainDoctorResult.getInfo() != null && !mainDoctorResult.getInfo().isEmpty()) {
                            details = Html.fromHtml(mainDoctorResult.getInfo()) + "\n";
                        } else {
                            details = null;
                        }
                        Bundle profile = new Bundle();
                        profile.putString("profileInfo", details);
                        ProfileFragment profileFragment = new ProfileFragment();
                        profileFragment.setArguments(profile);
                        FragmentTransaction(profileFragment);
                        break;
                    case 1:
                        String detail;
                        if (mainDoctorResult.getClinicInfo() != null && !mainDoctorResult.getClinicInfo().isEmpty()) {
                            detail = Html.fromHtml(mainDoctorResult.getClinicInfo()) + "\n";
                        } else {
                            detail = null;
                        }
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

        ImageView back = findViewById(R.id.doctor_details_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        request = findViewById(R.id.doctor_detail_request);
        setUp();
    }

    private void setUp() {
        String id = SaveSharedPreference.getUserId(this);
        if (id != null && !id.isEmpty()) {
            dataViewModel.getMyFavDoctorsList(Integer.parseInt(id), this).observe(this, new Observer<List<FavouriteDoctor>>() {
                @Override
                public void onChanged(List<FavouriteDoctor> favouriteDoctors) {
                    for (FavouriteDoctor f : favouriteDoctors) {
                        favouriteDoctorList.add(f.getId());
                        setUpData();
                    }
                }
            });
        } else {
            setUpData();
        }
    }

    private void setUpData() {
        dataViewModel.getSpecificDoctorData(Integer.parseInt(doctorId), this).observe(this, new Observer<List<DoctorResult>>() {
            @Override
            public void onChanged(final List<DoctorResult> doctorResult) {

                if (doctorResult.size() > 0) {

                    mainDoctorResult = doctorResult.get(0);
                    G_maps();

                    if (doctorResult.get(0).getName() != null && !doctorResult.get(0).getName().isEmpty()) {
                        nameText.setText(doctorResult.get(0).getName());
                    }

                    if (doctorResult.get(0).getFacilityLocation() != null && !doctorResult.get(0).getFacilityLocation().isEmpty()) {
                        location.setText(doctorResult.get(0).getAddress());
                    }

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
                    if (imageUri != null && !imageUri.isEmpty()) {
                        if (imageUri.equals("http://yakensolution.cloudapp.net/doctoryadmin/")) {
                            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
                        } else {
                            uri = Uri.parse(imageUri);
                        }
                        Picasso.get().load(uri).into(proPic);
                    } else {
                        uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
                        Picasso.get().load(uri).into(proPic);
                    }

                    fav.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FavClick(doctorResult.get(0));
                        }
                    });

                    request.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id = SaveSharedPreference.getUserId(DoctorDetailsActivity.this);
                            if (id != null && !id.isEmpty()) {
                                Intent intent = new Intent(DoctorDetailsActivity.this, AppointmentRequestActivity.class);
                                intent.putExtra("doctorId", String.valueOf(doctorResult.get(0).getId()));
                                intent.putExtra("facilityId", String.valueOf(doctorResult.get(0).getFacilityId()));
                                intent.putExtra("speciality", String.valueOf(doctorResult.get(0).getSpecialityId()));
                                startActivity(intent);
                            } else {
                                showLogInDialog(DoctorDetailsActivity.this);
                            }
                        }
                    });

                    share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            GoShare(doctorResult.get(0));
                        }
                    });

                    String details;
                    if (mainDoctorResult.getInfo() != null && !mainDoctorResult.getInfo().isEmpty()) {
                        details = Html.fromHtml(mainDoctorResult.getInfo()) + "\n";
                    } else {
                        details = null;
                    }
                    Bundle profile = new Bundle();
                    profile.putString("profileInfo", details);
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setArguments(profile);
                    FragmentTransaction(profileFragment);

                    progress.setVisibility(View.GONE);
                    dataLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void GoShare(DoctorResult doctorResult) {
        Utils.shareDirection(doctorResult.getAddress(), this);
    }

    private void FavClick(DoctorResult doctorResult) {
        String id = SaveSharedPreference.getUserId(this);
        if (id != null && !id.isEmpty()) {
            if (doctorResult.isFav()) {
                NewFavDoctor doctor = new NewFavDoctor();
                doctor.setUserId(Integer.parseInt(SaveSharedPreference.getUserId(DoctorDetailsActivity.this)));
                doctor.setDoctorId(doctorResult.getId());
                doctor.setFacilityId(doctorResult.getFacilityId());
                doctor.setFav(false);
                dataViewModel.setDoctorFavState(doctor, this);
                fav.setImageDrawable(getResources().getDrawable(R.drawable.un_favorite));
            } else {
                NewFavDoctor doctor = new NewFavDoctor();
                doctor.setUserId(Integer.parseInt(SaveSharedPreference.getUserId(DoctorDetailsActivity.this)));
                doctor.setDoctorId(doctorResult.getId());
                doctor.setFacilityId(doctorResult.getFacilityId());
                doctor.setFav(true);
                dataViewModel.setDoctorFavState(doctor, this);
                fav.setImageDrawable(getResources().getDrawable(R.drawable.favorite));
            }
        } else {
            showLogInDialog(this);
        }
    }

    private void FragmentTransaction(Fragment fragment) {

        fragment.setEnterTransition(new Fade(Fade.IN));
        fragment.setExitTransition(new Fade(Fade.OUT));

        FragmentManager fragmentManager = DoctorDetailsActivity.this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.doctor_details_frame_layout, fragment);
        fragmentTransaction.commit();
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
        Intent intent = new Intent(DoctorDetailsActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    private void G_maps() {
        ((SupportMapFragment) Objects.requireNonNull(getSupportFragmentManager()
                .findFragmentById(R.id.doctor_detail_map))).getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(v, v1), 16));

                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng location = new LatLng(v, v1);

                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(v, v1));
                CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(location, 16);
                mMap.animateCamera(center);
                mMap.animateCamera(zoom);

                LatLng markLoc = new LatLng(v, v1);
                mMap.addMarker(new MarkerOptions()
                        .position(markLoc)
                        .draggable(true));

                if (mainDoctorResult != null) {
                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            Utils.googleLocation(mainDoctorResult.getFacilityLocation(), DoctorDetailsActivity.this, mainDoctorResult.getImageUrl());

                        }
                    });

                    if (mainDoctorResult.getFacilityLocation() != null && !mainDoctorResult.getFacilityLocation().isEmpty()) {
                        String loc = mainDoctorResult.getFacilityLocation();
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
                }
            }
        });
    }
}