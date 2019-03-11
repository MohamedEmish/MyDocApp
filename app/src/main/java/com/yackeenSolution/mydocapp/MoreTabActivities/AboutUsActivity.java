package com.yackeenSolution.mydocapp.MoreTabActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils;

import java.util.Locale;

public class AboutUsActivity extends AppCompatActivity {

    TextView aboutUs;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_about_us);
        LinearLayout linearLayout = findViewById(R.id.about_us_root);
        Utils.RTLSupport(this, linearLayout);

        aboutUs = findViewById(R.id.about_us_text);
        back = findViewById(R.id.about_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
