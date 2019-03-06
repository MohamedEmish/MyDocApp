package com.yackeenSolution.mydocapp.MoreTabActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;

public class PrivacyPolicyActivity extends AppCompatActivity {

    TextView privacyText;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        privacyText = findViewById(R.id.privacy_text);
        back = findViewById(R.id.privacy_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
