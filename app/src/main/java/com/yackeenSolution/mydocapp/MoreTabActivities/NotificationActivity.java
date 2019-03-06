package com.yackeenSolution.mydocapp.MoreTabActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yackeenSolution.mydocapp.Adapters.NotificationAdapter;
import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {


    RecyclerView notificationRecycleView;
    NotificationAdapter notificationAdapter;
    List<MyNotification> data = new ArrayList<>();

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        back = findViewById(R.id.notification_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        notificationRecycleView = findViewById(R.id.notification_recycler);
        notificationRecycleView.setLayoutManager(new LinearLayoutManager(this));
        notificationRecycleView.setHasFixedSize(true);
        notificationRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notificationAdapter = new NotificationAdapter();
        notificationRecycleView.setAdapter(notificationAdapter);

        data.add(new MyNotification(
                "ali",
                "fares"));
        data.add(new MyNotification(
                "ali",
                "fares"));

        notificationAdapter.submitList(data);

    }
}
