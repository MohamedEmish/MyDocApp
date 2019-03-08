package com.yackeenSolution.mydocapp;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.transition.Fade;
import android.view.MenuItem;

import com.yackeenSolution.mydocapp.Fragments.MainFragments.AppointmentFragment;
import com.yackeenSolution.mydocapp.Fragments.MainFragments.FavoritesFragment;
import com.yackeenSolution.mydocapp.Fragments.MainFragments.MoreFragment;
import com.yackeenSolution.mydocapp.Fragments.MainFragments.PromotionFragment;
import com.yackeenSolution.mydocapp.Fragments.MainFragments.SearchFragment;

import java.util.Locale;

public class MainScreen extends AppCompatActivity {

    BottomNavigationView navigation;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
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

        fragmentManager = MainScreen.this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.main_screen_frame, fragment);
        fragmentTransaction.commit();
    }

    private Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        return context.createConfigurationContext(config);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            super.attachBaseContext(updateResources(newBase, PreferenceManager.getDefaultSharedPreferences(newBase).getString("lang", "en")));
        } else {
            super.attachBaseContext(newBase);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(this, SaveSharedPreference.getLanguage(this));
        setContentView(R.layout.activity_main_screen);

        navigation = findViewById(R.id.navigation);
        setNavigatorLayout(R.id.navigation_search);
    }

    public void setNavigatorLayout(int id) {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_search);
    }
}
