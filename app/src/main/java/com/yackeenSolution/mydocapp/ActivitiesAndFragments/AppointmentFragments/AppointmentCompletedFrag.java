package com.yackeenSolution.mydocapp.ActivitiesAndFragments.AppointmentFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class AppointmentCompletedFrag extends Fragment {

    RecyclerView recycleView;
    AppointmentSmallAdapter adapter;
    public static final int STATUS_COMPLETED = 3;
    DataViewModel dataViewModel;
    LinearLayout progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;


        View rootView = inflater.inflate(R.layout.appointment_completed_frag, nullParent);

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        progress = rootView.findViewById(R.id.completed_appointment_frag_progress_bar_layout);

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


        adapter.setOnItemCancelClickListener(new AppointmentSmallAdapter.OnItemCancelClickListener() {
            @Override
            public void onItemClick(Appointment appointment) {
                cancel(appointment.getId());
            }
        });

        setUpData();
        return rootView;
    }

    private void cancel(int id) {
        // TODO : cancel
    }

    private void setUpData() {
        dataViewModel.getMyAppointments(Integer.parseInt(SaveSharedPreference.getUserId(getActivity())), STATUS_COMPLETED).observe(this, new Observer<List<Appointment>>() {
            @Override
            public void onChanged(List<Appointment> appointments) {
                if (appointments.size() > 0) {
                    progress.setVisibility(View.GONE);
                    recycleView.setVisibility(View.VISIBLE);
                    adapter.submitList(appointments);
                }
            }
        });
    }

}