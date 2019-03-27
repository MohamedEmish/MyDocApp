package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Adapters.NotificationAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

public class NotificationActivity extends AppCompatActivity {


    private RecyclerView notificationRecycleView;
    private NotificationAdapter notificationAdapter;

    private DataViewModel dataViewModel;
    private LinearLayout progress, noData;

    @Override
    protected void onResume() {
        super.onResume();
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_notification);
        LinearLayout linearLayout = findViewById(R.id.notification_root);
        Utils.RTLSupport(this, linearLayout);

        ImageView back = findViewById(R.id.notification_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progress = findViewById(R.id.notification_progressbar_layout);
        noData = findViewById(R.id.notification_no_data);

        notificationRecycleView = findViewById(R.id.notification_recycler);
        notificationRecycleView.setLayoutManager(new LinearLayoutManager(this));
        notificationRecycleView.setHasFixedSize(true);
        notificationRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notificationAdapter = new NotificationAdapter();
        notificationRecycleView.setAdapter(notificationAdapter);
    }

    private void setUpData() {

        dataViewModel.getNotificationsList().observe(this, new Observer<List<MyNotification>>() {
            @Override
            public void onChanged(List<MyNotification> notifications) {
                progress.setVisibility(View.GONE);
                if (notifications.size() > 0) {
                    notificationRecycleView.setVisibility(View.VISIBLE);
                    notificationAdapter.submitList(notifications);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
