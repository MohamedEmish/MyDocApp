package com.yackeenSolution.mydocapp.MoreTabActivities;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;

public class AboutUsActivity extends AppCompatActivity {

    TextView aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.forget_pass_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.about);

        aboutUs = findViewById(R.id.about_us_text);
    }
}
