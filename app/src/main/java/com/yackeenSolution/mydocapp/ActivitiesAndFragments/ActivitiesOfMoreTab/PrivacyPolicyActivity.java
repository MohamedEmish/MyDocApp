package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

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
        dataViewModel.getMyPolicyLiveData(this);
        setUpData();
    }

    private void setUpData() {

        dataViewModel.getMyPolicyLiveData(this).observe(this, new Observer<List<MyAboutUs>>() {
            @Override
            public void onChanged(List<MyAboutUs> policyList) {

                if (policyList.size() > 0) {
                    progress.setVisibility(View.GONE);
                    data.setVisibility(View.VISIBLE);

                    String contentHtml = policyList.get(0).getContent();
                    if (contentHtml != null && !contentHtml.isEmpty()) {
                        privacyTextContent.setText(Html.fromHtml(contentHtml));
                    }

                    String title = policyList.get(0).getTitle();
                    if (title != null && !title.isEmpty()) {
                        privacyTextTitle.setText(title.toUpperCase());
                    }
                }
            }
        });
    }
}
