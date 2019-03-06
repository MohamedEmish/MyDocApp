package com.yackeenSolution.mydocapp.MoreTabActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup languageGroup;
    RadioButton ar, en;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
                } else {
                    SaveSharedPreference.setLanguage(SettingsActivity.this, "en");
                }
            }
        });

    }
}