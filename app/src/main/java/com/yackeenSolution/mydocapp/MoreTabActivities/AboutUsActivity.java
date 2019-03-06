package com.yackeenSolution.mydocapp.MoreTabActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;

public class AboutUsActivity extends AppCompatActivity {

    TextView aboutUs;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

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
