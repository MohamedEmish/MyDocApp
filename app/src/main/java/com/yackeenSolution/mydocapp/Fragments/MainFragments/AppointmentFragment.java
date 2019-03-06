package com.yackeenSolution.mydocapp.Fragments.MainFragments;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Fragments.AppointmentFragments.AppointmentCompletedFrag;
import com.yackeenSolution.mydocapp.Fragments.AppointmentFragments.AppointmentConfirmedFrag;
import com.yackeenSolution.mydocapp.Fragments.AppointmentFragments.AppointmentPendingFrag;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.SaveSharedPreference;

public class AppointmentFragment extends Fragment {


    TabLayout tabLayout;
    String name;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        View rootView = inflater.inflate(R.layout.appointment_frag, nullParent);

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
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    if (!name.equals("")) {
                        PendingRecycler();
                    }
                } else if (tabLayout.getSelectedTabPosition() == 1) {
                    if (!name.equals("")) {
                        ConfirmedRecycler();
                    }
                } else if (tabLayout.getSelectedTabPosition() == 2) {
                    if (!name.equals("")) {
                        CompletedRecycler();
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

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.appointment_frame, fragment);
        fragmentTransaction.commit();
    }
}

