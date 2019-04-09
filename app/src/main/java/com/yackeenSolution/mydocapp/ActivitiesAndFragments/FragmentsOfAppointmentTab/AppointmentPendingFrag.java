package com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfAppointmentTab;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Adapters.AppointmentSmallAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.Objects.SmallAppointment;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentPendingFrag extends Fragment {

    private static final int STATUS_PENDING = 1;
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
        View rootView = inflater.inflate(R.layout.appointment_pending_frag, nullParent);
        progress = rootView.findViewById(R.id.pending_appointment_frag_progress_bar_layout);
        noData = rootView.findViewById(R.id.app_pending_no_data);

        recycleView = rootView.findViewById(R.id.app_pending_recycler);
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
                cancel(appointment.getId(), appointment.getDateTime());
            }
        });
        return rootView;
    }

    private void setUpData() {
        dataViewModel.getMyAppointments(Integer.parseInt(SaveSharedPreference.getAppointmentId(getActivity())), STATUS_PENDING, getContext()).observe(this, new Observer<List<Appointment>>() {
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

    private void cancel(int id, String date) {
        showLogOutDialog(Objects.requireNonNull(getContext()), id, date);
    }

    private void showLogOutDialog(Context context, final int id, final String date) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.logout_dialog, nullParent, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setView(view);

        Button yes = view.findViewById(R.id.alert_dialog_yes);
        Button no = view.findViewById(R.id.alert_dialog_no);
        TextView text = view.findViewById(R.id.alert_dialog_text);
        text.setText(context.getResources().getString(R.string.cancel_appointment_msg));
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmallAppointment appointment = new SmallAppointment();
                appointment.setAppointmentId(id);
                appointment.setStatusId(STATUS_CANCELLED);
                appointment.setDate(date);
                appointment.setDetailedStatusId(0);
                appointment.setSlotId(0);
                dataViewModel.deleteAppointment(appointment, getContext());
                adapter.notifyDataSetChanged();
                alertDialog.cancel();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

            }
        });
    }

}

