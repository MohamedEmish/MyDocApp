package com.yackeenSolution.mydocapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.MenuItem;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Fragments.MainFragments.AppointmentFragment;
import com.yackeenSolution.mydocapp.Fragments.MainFragments.FavoritesFragment;
import com.yackeenSolution.mydocapp.Fragments.MainFragments.MoreFragment;
import com.yackeenSolution.mydocapp.Fragments.MainFragments.PromotionFragment;
import com.yackeenSolution.mydocapp.Fragments.MainFragments.SearchFragment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        navigation = findViewById(R.id.navigation);

        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        if (source != null) {
            if (source.equals("more")) {
                setNavigatorLayout(R.id.navigation_more);
            }
        } else {
            setNavigatorLayout(R.id.navigation_search);
        }
    }

    public void setNavigatorLayout(int id) {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_search);
    }
}
