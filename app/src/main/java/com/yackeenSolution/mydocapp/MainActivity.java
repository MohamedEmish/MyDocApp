package com.yackeenSolution.mydocapp;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    View splashImage, splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        String language = SaveSharedPreference.getLanguage(this);
        Locale locale = new Locale(language);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);

        splashImage = findViewById(R.id.open_splash_logo);
        splashText = findViewById(R.id.open_splash_msg);

        doYoYo(Techniques.DropOut, splashImage, 500);
    }

    @Override
    protected void onStart() {
        super.onStart();

        splashImage = findViewById(R.id.open_splash_logo);
        splashText = findViewById(R.id.open_splash_msg);

        doYoYo(Techniques.DropOut, splashImage, 500);
    }

    public void doYoYo(Techniques techniques, final View view, int duration) {
        YoYo.with(techniques).duration(duration).interpolate(new LinearInterpolator()).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (view == splashImage) {
                    splashText.setVisibility(View.VISIBLE);
                    doYoYo(Techniques.FadeInUp, splashText, 2000);
                } else if (view == splashText) {

                    if (SaveSharedPreference.getUserEmail(MainActivity.this).length() == 0) {
                        startSignInIntent();
                    } else if (SaveSharedPreference.getUserEmail(MainActivity.this).length() != 0) {
                        startMainScreenIntent();
                    }

                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).playOn(view);
    }

    private void startMainScreenIntent() {
        Intent intent = new Intent(MainActivity.this, MainScreen.class);
        startActivity(intent);
    }

    private void startSignInIntent() {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
    }

}
