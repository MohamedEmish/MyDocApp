package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.MyAboutUs;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class AboutUsActivity extends AppCompatActivity {

    private TextView
            aboutUsTitle,
            aboutUsContent;
    private DataViewModel dataViewModel;
    private LinearLayout
            progress,
            data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_about_us);
        LinearLayout linearLayout = findViewById(R.id.about_us_root);
        Utils.RTLSupport(this, linearLayout);

        aboutUsTitle = findViewById(R.id.about_us_text_title);
        aboutUsContent = findViewById(R.id.about_us_text_content);
        progress = findViewById(R.id.about_us_progress_bar_layout);
        data = findViewById(R.id.about_us_data_layout);

        ImageView back = findViewById(R.id.about_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpData();
    }

    private void setUpData() {

        dataViewModel.getMyAboutUsLive(this).observe(this, new Observer<List<MyAboutUs>>() {
            @Override
            public void onChanged(List<MyAboutUs> myAboutUses) {

                if (myAboutUses.size() > 0) {
                    progress.setVisibility(View.GONE);
                    data.setVisibility(View.VISIBLE);
                    String content = myAboutUses.get(0).getContent();
                    if (content != null && !content.isEmpty()) {
                        aboutUsContent.setText(content);
                    }
                    String title = myAboutUses.get(0).getTitle();
                    if (title != null && !title.isEmpty()) {
                        aboutUsTitle.setText(title.toUpperCase());
                    }
                } else {
                    progress.setVisibility(View.GONE);
                    data.setVisibility(View.VISIBLE);
                    aboutUsContent.setText(getResources().getString(R.string.about));
                    aboutUsTitle.setText(getResources().getString(R.string.about));
                }
            }
        });
    }
}
