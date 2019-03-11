package com.yackeenSolution.mydocapp.MoreTabActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yackeenSolution.mydocapp.MainScreen;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup languageGroup;
    RadioButton ar, en;
    ImageView back;
    String language;
    String lang;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language = SaveSharedPreference.getLanguage(this);
        Locale locale = new Locale(language);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_settings);

        linearLayout = findViewById(R.id.settings_root);
        Utils.RTLSupport(this, linearLayout);

        languageGroup = findViewById(R.id.settings_lang_radio_group);
        ar = findViewById(R.id.settings_ar_radio_button);
        en = findViewById(R.id.settings_en_radio_button);

        back = findViewById(R.id.settings_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLayout();
            }
        });

        lang = SaveSharedPreference.getLanguage(this);
        if (lang.equals("ar")) {
            ar.setChecked(true);
        } else {
            en.setChecked(true);
        }

        languageGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == ar.getId()) {
                    SaveSharedPreference.setLanguage(SettingsActivity.this, "ar");
                    lang = "ar";
                    finish();
                    startActivity(new Intent(group.getContext(), SettingsActivity.class));
                } else {
                    SaveSharedPreference.setLanguage(SettingsActivity.this, "en");
                    lang = "en";
                    finish();
                    startActivity(new Intent(group.getContext(), SettingsActivity.class));
                }
            }
        });

    }

    private void backLayout() {

        Intent intent = new Intent(SettingsActivity.this, MainScreen.class);
        startActivity(intent);

    }
}