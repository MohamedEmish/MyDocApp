package com.yackeenSolution.mydocapp;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {


    RecyclerView notificationRecycleView;
    NotificationAdapter notificationAdapter;
    List<Notification> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.forget_pass_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.notification);

        notificationRecycleView = findViewById(R.id.notification_recycler);
        notificationRecycleView.setLayoutManager(new LinearLayoutManager(this));
        notificationRecycleView.setHasFixedSize(true);
        notificationRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notificationAdapter = new NotificationAdapter();
        notificationRecycleView.setAdapter(notificationAdapter);

        data.add(new Notification(
                "ali",
                "fares"));
        data.add(new Notification(
                "ali",
                "fares"));

        notificationAdapter.submitList(data);

    }
}
