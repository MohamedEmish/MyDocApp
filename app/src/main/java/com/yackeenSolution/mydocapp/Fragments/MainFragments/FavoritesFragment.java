package com.yackeenSolution.mydocapp.Fragments.MainFragments;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Fragments.FavoriteFragments.FavoriteDoctorFrag;
import com.yackeenSolution.mydocapp.Fragments.FavoriteFragments.FavoriteFacilityFrag;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;


public class FavoritesFragment extends Fragment {

    TabLayout tabLayout;
    String name;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        View rootView = inflater.inflate(R.layout.favorites_frag, nullParent);

        tabLayout = rootView.findViewById(R.id.favorites_tabs_layout);
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
            FavoriteDoctorFrag favoriteDoctorFrag = new FavoriteDoctorFrag();
            FragmentTransaction(favoriteDoctorFrag);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    if (!name.equals("")) {
                        DoctorRecycler();
                    }
                } else if (tabLayout.getSelectedTabPosition() == 1) {
                    if (!name.equals("")) {
                        FacilityRecycler();
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

    private void FacilityRecycler() {
        FavoriteFacilityFrag favoriteFacilityFrag = new FavoriteFacilityFrag();
        FragmentTransaction(favoriteFacilityFrag);
    }

    private void DoctorRecycler() {
        FavoriteDoctorFrag favoriteDoctorFrag = new FavoriteDoctorFrag();
        FragmentTransaction(favoriteDoctorFrag);
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