package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View splashImage, splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_main);

        splashImage = findViewById(R.id.open_splash_logo);
        splashText = findViewById(R.id.open_splash_msg);

        doYoYo(Techniques.DropOut, splashImage, 250);
    }

    @Override
    protected void onStart() {
        super.onStart();

        splashImage = findViewById(R.id.open_splash_logo);
        splashText = findViewById(R.id.open_splash_msg);

        doYoYo(Techniques.DropOut, splashImage, 250);
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
                    doYoYo(Techniques.FadeInUp, splashText, 250);
                } else if (view == splashText) {

                    if (!isNetworkAvailable()) {
                        startNoConnectionActivity();
                    } else {
                        String name = SaveSharedPreference.getUserId(MainActivity.this);
                        if (name != null && !name.isEmpty()) {
                            startMainScreenIntent();
                        } else {
                            startSignInIntent();
                        }
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

    private void startNoConnectionActivity() {
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent = new Intent(MainActivity.this, NoInternetConnection.class);
                                          startActivity(intent);
                                          finish();
                                      }
                                  }, 0
        );
    }

    private void startMainScreenIntent() {
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent = new Intent(MainActivity.this, MainScreen.class);
                                          startActivity(intent);
                                          finish();
                                      }
                                  }, 0
        );
    }

    private void startSignInIntent() {
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                                          startActivity(intent);
                                          finish();
                                      }
                                  }, 0
        );
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
