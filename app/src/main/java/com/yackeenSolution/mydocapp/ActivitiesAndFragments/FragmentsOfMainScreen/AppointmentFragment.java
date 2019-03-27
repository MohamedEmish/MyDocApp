package com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfMainScreen;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfAppointmentTab.AppointmentCompletedFrag;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfAppointmentTab.AppointmentConfirmedFrag;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfAppointmentTab.AppointmentPendingFrag;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;

import java.util.Objects;

public class AppointmentFragment extends Fragment {


    private TabLayout tabLayout;
    private String name;
    private TextView msg;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        View rootView = inflater.inflate(R.layout.appointment_frag, nullParent);

        msg = rootView.findViewById(R.id.appoint_msg);

        tabLayout = rootView.findViewById(R.id.appointment_tabs_layout);
        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.colorGray));
            drawable.setSize(1, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

        name = SaveSharedPreference.getUserName(getActivity());
        if (!name.equals("")) {
            AppointmentPendingFrag appointmentPendingFrag = new AppointmentPendingFrag();
            FragmentTransaction(appointmentPendingFrag);
        } else {
            msg.setVisibility(View.VISIBLE);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    if (!name.equals("")) {
                        PendingRecycler();
                    } else {
                        msg.setVisibility(View.VISIBLE);
                    }
                } else if (tabLayout.getSelectedTabPosition() == 1) {
                    if (!name.equals("")) {
                        ConfirmedRecycler();
                    } else {
                        msg.setVisibility(View.VISIBLE);
                    }
                } else if (tabLayout.getSelectedTabPosition() == 2) {
                    if (!name.equals("")) {
                        CompletedRecycler();
                    } else {
                        msg.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return rootView;
    }

    private void PendingRecycler() {
        AppointmentPendingFrag appointmentPendingFrag = new AppointmentPendingFrag();
        FragmentTransaction(appointmentPendingFrag);
    }

    private void ConfirmedRecycler() {
        AppointmentConfirmedFrag appointmentConfirmedFrag = new AppointmentConfirmedFrag();
        FragmentTransaction(appointmentConfirmedFrag);
    }

    private void CompletedRecycler() {
        AppointmentCompletedFrag appointmentCompletedFrag = new AppointmentCompletedFrag();
        FragmentTransaction(appointmentCompletedFrag);
    }

    private void FragmentTransaction(Fragment fragment) {

        fragment.setEnterTransition(new Fade(Fade.IN));
        fragment.setExitTransition(new Fade(Fade.OUT));

        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.appointment_frame, fragment);
        fragmentTransaction.commit();
    }
}

