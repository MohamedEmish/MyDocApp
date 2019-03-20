package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.MyAboutUs;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

public class PrivacyPolicyActivity extends AppCompatActivity {

    TextView privacyTextTitle, privacyTextContent;
    ImageView back;

    private DataViewModel dataViewModel;
    private LinearLayout progress, data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_privacy_policy);
        LinearLayout linearLayout = findViewById(R.id.privacy_root);
        Utils.RTLSupport(this, linearLayout);

        privacyTextTitle = findViewById(R.id.privacy_text_title);
        privacyTextContent = findViewById(R.id.privacy_text_content);

        progress = findViewById(R.id.policy_progress_bar_layout);
        data = findViewById(R.id.policy_data_layout);

        back = findViewById(R.id.privacy_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        dataViewModel.getMyPolicyLiveData();
        setUpData();
    }

    private void setUpData() {

        dataViewModel.getMyPolicyLiveData().observe(this, new Observer<List<MyAboutUs>>() {
            @Override
            public void onChanged(List<MyAboutUs> policyList) {

                if (policyList.size() > 0) {
                    progress.setVisibility(View.GONE);
                    data.setVisibility(View.VISIBLE);

                    String contentHtml = policyList.get(0).getContent();
                    privacyTextContent.setText(Html.fromHtml(contentHtml));

                    String title = policyList.get(0).getTitle();
                    privacyTextTitle.setText(title.toUpperCase());
                }
            }
        });
    }
}
