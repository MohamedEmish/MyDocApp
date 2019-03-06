package com.yackeenSolution.mydocapp.Fragments.AppointmentFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yackeenSolution.mydocapp.Adapters.AppointmentSmallAdapter;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.R;

import java.util.ArrayList;
import java.util.List;

public class AppointmentPendingFrag extends Fragment {

    RecyclerView recycleView;
    AppointmentSmallAdapter adapter;
    List<Appointment> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;


        View rootView = inflater.inflate(R.layout.appointment_pending_frag, nullParent);

        recycleView = rootView.findViewById(R.id.app_pending_recycler);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setHasFixedSize(true);
        adapter = new AppointmentSmallAdapter();
        recycleView.setAdapter(adapter);

        data.add(new Appointment(
                "Ahmed Alaa",
                123,
                "Android developer",
                "Yackeen Solution",
                "Sat 3/1/2018",
                "10:02",
                123,
                "Cairo")
        );


        data.add(new Appointment(
                "Ahmed Alaa",
                123,
                "Android developer",
                "Yackeen Solution",
                "Sat 3/1/2018",
                "10:02",
                123,
                "Cairo")
        );

        data.add(new Appointment(
                "Ahmed Alaa",
                123,
                "Android developer",
                "Yackeen Solution",
                "Sat 3/1/2018",
                "10:02",
                123,
                "Cairo")
        );

        adapter.submitList(data);

        adapter.setOnItemCallClickListener(new AppointmentSmallAdapter.OnItemCallClickListener() {
            @Override
            public void onItemClick(Appointment Appointment) {
                call(Appointment.getPhoneNumber());
            }
        });

        adapter.setOnItemDirectionClickListener(new AppointmentSmallAdapter.OnItemDirectionClickListener() {
            @Override
            public void onItemClick(Appointment Appointment) {
                direction(Appointment.getDirection());
            }
        });


        adapter.setOnItemCancelClickListener(new AppointmentSmallAdapter.OnItemCancelClickListener() {
            @Override
            public void onItemClick(Appointment Appointment) {
                cancel(Appointment.getId());
            }
        });


        return rootView;
    }

    private void cancel(int id) {
        // TODO : cancel
    }

    private void direction(String direction) {
        // TODO : direction
    }

    private void call(int phoneNumber) {
        // TODO : call
    }
}

