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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup languageGroup;
    RadioButton ar, en;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        updateResources(this, SaveSharedPreference.getLanguage(this));

        languageGroup = findViewById(R.id.settings_lang_radio_group);
        ar = findViewById(R.id.settings_ar_radio_button);
        en = findViewById(R.id.settings_en_radio_button);

        back = findViewById(R.id.settings_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String lang = SaveSharedPreference.getLanguage(this);
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
                    finish();
                    startActivity(new Intent(group.getContext(), SettingsActivity.class));
                } else {
                    SaveSharedPreference.setLanguage(SettingsActivity.this, "en");
                    finish();
                    startActivity(new Intent(group.getContext(), SettingsActivity.class));
                }
            }
        });

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
}