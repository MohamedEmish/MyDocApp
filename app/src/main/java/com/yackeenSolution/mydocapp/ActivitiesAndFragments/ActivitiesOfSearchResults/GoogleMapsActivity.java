package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.Locale;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView back;
    private ImageView share;
    private Button getDirection;
    private Double v = 29.9719740;
    private Double v1 = 31.2677420;
    CircleImageView proPic;
    private String imageUri = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_google_maps);
        ConstraintLayout constraintLayout = findViewById(R.id.google_maps_root);
        Utils.RTLSupport(this, constraintLayout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if (intent.hasExtra("v")) {
            v = Double.valueOf(intent.getStringExtra("v"));
            v1 = Double.valueOf(intent.getStringExtra("v1"));
            imageUri = intent.getStringExtra("image");
        }

        Uri uri;
        proPic = findViewById(R.id.google_maps_image_view);
        if (imageUri.equals("http://yakensolution.cloudapp.net/doctoryadmin/") || imageUri.equals("")) {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
        } else {
            uri = Uri.parse(imageUri);
        }
        Picasso.get().load(uri).into(proPic);

        back = findViewById(R.id.google_maps_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        share = findViewById(R.id.google_maps_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDirection();
            }
        });

        getDirection = findViewById(R.id.google_maps_get_direction);
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goForDirection(v, v1);
            }
        });

    }

    private void goForDirection(Double v, Double v1) {
        String uri = String.format(Locale.getDefault(), "geo:%f,%f", v, v1);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);

    }

    private void shareDirection() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "http://maps.google.com?q=" + v + "," + v1;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Location");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(v, v1);

        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(v, v1));
        CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(location, 18);
        mMap.animateCamera(center);
        mMap.animateCamera(zoom);

        LatLng markLocation = new LatLng(v, v1);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(markLocation)
                .draggable(true));
    }
}
