package com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfMainScreen;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfAppointmentTab.AppointmentCompletedFrag;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfAppointmentTab.AppointmentConfirmedFrag;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfAppointmentTab.AppointmentPendingFrag;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class AppointmentFragment extends Fragment {


    private TabLayout tabLayout;
    private String name;
    private TextView msg;
    private Spinner spinner;
    private DataViewModel dataViewModel;
    private List<FamilyMember> mainFamilyList;
    private List<String> stringsFamily;

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

        name = SaveSharedPreference.getUserId(getActivity());
        if (name != null && !name.isEmpty()) {
            AppointmentPendingFrag appointmentPendingFrag = new AppointmentPendingFrag();
            FragmentTransaction(appointmentPendingFrag);
        } else {
            msg.setVisibility(View.VISIBLE);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    if (name != null && !name.isEmpty()) {
                        PendingRecycler();
                    } else {
                        msg.setVisibility(View.VISIBLE);
                    }
                } else if (tabLayout.getSelectedTabPosition() == 1) {
                    if (name != null && !name.isEmpty()) {
                        ConfirmedRecycler();
                    } else {
                        msg.setVisibility(View.VISIBLE);
                    }
                } else if (tabLayout.getSelectedTabPosition() == 2) {
                    if (name != null && !name.isEmpty()) {
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

        SaveSharedPreference.setAppointmentId(getActivity(), SaveSharedPreference.getUserId(getActivity()));

        spinner = rootView.findViewById(R.id.appoint_family_spinner);

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpSpinnersData();
        return rootView;
    }

    private void setUpSpinnersData() {
        if (name != null && !name.isEmpty()) {
            dataViewModel.getMyFamilyMembersList(Integer.parseInt(name), getContext()).observe(this, new Observer<List<FamilyMember>>() {
                @Override
                public void onChanged(List<FamilyMember> familyMembers) {
                    if (familyMembers.size() > 0) {
                        mainFamilyList = familyMembers;
                        stringsFamily = new ArrayList<>();
                        stringsFamily.add(SaveSharedPreference.getUserName(getActivity()));
                        for (FamilyMember member : familyMembers) {
                            stringsFamily.add(member.getName());
                        }
                        Utils.setupSpinnerFirstSelected(getContext(), stringsFamily, spinner);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                ((TextView) view).setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.colorGray));
                                ((TextView) parent.getChildAt(0)).setTextSize(12);
                                if (position != 0) {
                                    String selectedName = stringsFamily.get(position);
                                    String appointmentId = null;
                                    for (FamilyMember member : mainFamilyList) {
                                        if (member.getName().equals(selectedName)) {
                                            appointmentId = String.valueOf(member.getId());
                                        }
                                    }
                                    SaveSharedPreference.setAppointmentId(getActivity(), appointmentId);
                                    AppointmentPendingFrag appointmentPendingFrag = new AppointmentPendingFrag();
                                    FragmentTransaction(appointmentPendingFrag);
                                } else {
                                    SaveSharedPreference.setAppointmentId(getActivity(), SaveSharedPreference.getUserId(getActivity()));
                                    AppointmentPendingFrag appointmentPendingFrag = new AppointmentPendingFrag();
                                    FragmentTransaction(appointmentPendingFrag);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }

            });
        }
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

