package com.yackeenSolution.mydocapp;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    View splashImage, splashText;

    SharedPreferences loginPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                    if (SaveSharedPreference.getUserName(MainActivity.this).length() == 0) {
                        startSignInIntent();
                    } else if (SaveSharedPreference.getUserName(MainActivity.this).length() != 0) {
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
