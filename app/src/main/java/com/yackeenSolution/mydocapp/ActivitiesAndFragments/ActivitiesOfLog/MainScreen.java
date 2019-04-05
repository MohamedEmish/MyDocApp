package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.transition.Fade;
import android.view.MenuItem;

import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfMainScreen.AppointmentFragment;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfMainScreen.FavoritesFragment;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfMainScreen.MoreFragment;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfMainScreen.PromotionFragment;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfMainScreen.SearchFragment;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

public class MainScreen extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        String id = SaveSharedPreference.getUserId(this);
        if (id != null && !id.isEmpty()) {
            moveTaskToBack(true);
        } else {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }
    }

    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_appointments:

                    AppointmentFragment appointmentFragment = new AppointmentFragment();
                    FragmentTransaction(appointmentFragment);
                    return true;

                case R.id.navigation_favorites:

                    FavoritesFragment favoritesFragment = new FavoritesFragment();
                    FragmentTransaction(favoritesFragment);
                    return true;

                case R.id.navigation_search:
                    SearchFragment searchFragment = new SearchFragment();
                    FragmentTransaction(searchFragment);
                    return true;

                case R.id.navigation_promotions:
                    PromotionFragment promotionFragment = new PromotionFragment();
                    FragmentTransaction(promotionFragment);
                    return true;

                case R.id.navigation_more:
                    MoreFragment moreFragment = new MoreFragment();
                    FragmentTransaction(moreFragment);
                    return true;
            }
            return false;
        }
    };

    private void FragmentTransaction(Fragment fragment) {

        fragment.setEnterTransition(new Fade(Fade.IN));
        fragment.setExitTransition(new Fade(Fade.OUT));

        FragmentManager fragmentManager = MainScreen.this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.main_screen_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_main_screen);
        ConstraintLayout constraintLayout = findViewById(R.id.container);
        Utils.RTLSupport(this, constraintLayout);


        navigation = findViewById(R.id.navigation);
        setNavigatorLayout(R.id.navigation_search);
    }

    public void setNavigatorLayout(int id) {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(id);
    }
}