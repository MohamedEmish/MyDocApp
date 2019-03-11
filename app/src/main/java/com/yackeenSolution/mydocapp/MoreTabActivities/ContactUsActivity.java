package com.yackeenSolution.mydocapp.MoreTabActivities;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils;

import java.util.Locale;

public class ContactUsActivity extends AppCompatActivity {

    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_contact_us);
        LinearLayout linearLayout = findViewById(R.id.contact_us_root);
        Utils.RTLSupport(this, linearLayout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        back = findViewById(R.id.contact_us_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
