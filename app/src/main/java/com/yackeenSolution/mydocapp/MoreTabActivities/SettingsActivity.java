package com.yackeenSolution.mydocapp.MoreTabActivities;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup languageGroup;
    RadioButton ar, en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.forget_pass_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.settings);

        languageGroup = findViewById(R.id.settings_lang_radio_group);
        ar = findViewById(R.id.settings_ar_radio_button);
        en = findViewById(R.id.settings_en_radio_button);

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
                } else {
                    SaveSharedPreference.setLanguage(SettingsActivity.this, "en");
                }
            }
        });

    }
}