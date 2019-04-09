package com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfAppointmentTab;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Adapters.AppointmentSmallAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentCompletedFrag extends Fragment {

    private static final int STATUS_COMPLETED = 4;
    private static final int STATUS_CANCELLED = 3;

    private RecyclerView recycleView;
    private AppointmentSmallAdapter adapter;
    private DataViewModel dataViewModel;
    private LinearLayout progress, noData;

    @Override
    public void onResume() {
        super.onResume();
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;


        View rootView = inflater.inflate(R.layout.appointment_completed_frag, nullParent);

        progress = rootView.findViewById(R.id.completed_appointment_frag_progress_bar_layout);
        noData = rootView.findViewById(R.id.app_completed_no_data);

        recycleView = rootView.findViewById(R.id.app_completed_recycler);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setHasFixedSize(true);
        adapter = new AppointmentSmallAdapter();
        recycleView.setAdapter(adapter);


        adapter.setOnItemCallClickListener(new AppointmentSmallAdapter.OnItemCallClickListener() {
            @Override
            public void onItemClick(Appointment appointment) {
                Utils.performCall(appointment.getPhoneNumber(), getContext());
            }
        });

        adapter.setOnItemDirectionClickListener(new AppointmentSmallAdapter.OnItemDirectionClickListener() {
            @Override
            public void onItemClick(Appointment appointment) {
                Utils.googleLocation(appointment.getClinicLocation(), getContext(), "");
            }
        });
        return rootView;
    }

    private void setUpData() {
        dataViewModel.getMyAppointments(Integer.parseInt(SaveSharedPreference.getAppointmentId(getActivity())), STATUS_COMPLETED, getContext()).observe(this, new Observer<List<Appointment>>() {
            @Override
            public void onChanged(List<Appointment> appointments) {
                progress.setVisibility(View.GONE);
                if (appointments.size() > 0) {
                    recycleView.setVisibility(View.VISIBLE);
                    adapter.submitList(appointments);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}