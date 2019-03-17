package com.yackeenSolution.mydocapp.MoreTabActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Adapters.NotificationAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NotificationActivity extends AppCompatActivity {


    RecyclerView notificationRecycleView;
    NotificationAdapter notificationAdapter;
    ImageView back;

    private DataViewModel dataViewModel;
    private LinearLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_notification);
        LinearLayout linearLayout = findViewById(R.id.notification_root);
        Utils.RTLSupport(this, linearLayout);

        back = findViewById(R.id.notification_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progress = findViewById(R.id.notification_progressbar_layout);

        notificationRecycleView = findViewById(R.id.notification_recycler);
        notificationRecycleView.setLayoutManager(new LinearLayoutManager(this));
        notificationRecycleView.setHasFixedSize(true);
        notificationRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notificationAdapter = new NotificationAdapter();
        notificationRecycleView.setAdapter(notificationAdapter);

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        dataViewModel.getMyPolicyLiveData();
        setUpData();
    }

    private void setUpData() {

        dataViewModel.getNotificationsList().observe(this, new Observer<List<MyNotification>>() {
            @Override
            public void onChanged(List<MyNotification> notifications) {

                if (notifications.size() > 0) {
                    progress.setVisibility(View.GONE);
                    notificationRecycleView.setVisibility(View.VISIBLE);

                    notificationAdapter.submitList(notifications);
                }
            }
        });
    }

}
