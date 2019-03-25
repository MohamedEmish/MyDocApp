package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog.MainScreen;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.UserData;
import com.yackeenSolution.mydocapp.Objects.UserDataToUpload;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup languageGroup;
    RadioButton ar, en;
    ImageView back;
    String language;
    String lang;
    LinearLayout linearLayout;
    DataViewModel dataViewModel;
    Switch appointSwitch, notifySwitch;
    UserData mUserData;
    TextView save;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SettingsActivity.this, MainScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language = SaveSharedPreference.getLanguage(this);
        Locale locale = new Locale(language);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_settings);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

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

        save = findViewById(R.id.settings_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });
        appointSwitch = findViewById(R.id.settings_appoint_switch);
        notifySwitch = findViewById(R.id.settings_notify_switch);

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
        setUpData();

    }

    private void saveUserData() {
        UserDataToUpload user = new UserDataToUpload();
        user.setId(Integer.parseInt(SaveSharedPreference.getUserId(this)));
        user.setAppointmentReminder(appointSwitch.isChecked());
        user.setEnableNotification(notifySwitch.isChecked());
        user.setEmail(mUserData.getEmail());
        user.setPassword(mUserData.getPassword());
        user.setDoctorId(null);
        dataViewModel.editUserData(user).observe(this, new Observer<UserDataToUpload>() {
            @Override
            public void onChanged(UserDataToUpload userDataToUpload) {
                Toast.makeText(SettingsActivity.this, getResources().getString(R.string.done), Toast.LENGTH_SHORT).show();
                backLayout();
            }
        });
    }

    private void setUpData() {
        dataViewModel.getUserAccountData(Integer.parseInt(SaveSharedPreference.getUserId(this))).observe(this, new Observer<UserData>() {
            @Override
            public void onChanged(UserData userData) {
                String appoint = userData.getAppointmentReminder();
                String notify = userData.getEnableNotification();
                mUserData = userData;
                if (appoint != null && !appoint.isEmpty()) {
                    if (appoint.equals("true")) {
                        appointSwitch.setChecked(true);
                    } else {
                        appointSwitch.setChecked(false);
                    }
                } else {
                    appointSwitch.setChecked(false);
                }

                if (notify != null && !notify.isEmpty()) {
                    if (notify.equals("true")) {
                        notifySwitch.setChecked(true);
                    } else {
                        notifySwitch.setChecked(false);
                    }
                } else {
                    notifySwitch.setChecked(false);
                }
            }
        });
    }

    private void backLayout() {

        Intent intent = new Intent(SettingsActivity.this, MainScreen.class);
        startActivity(intent);

    }
}