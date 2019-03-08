package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yackeenSolution.mydocapp.Fragments.NonMainFragments.ClinicInfoFragment;
import com.yackeenSolution.mydocapp.Fragments.NonMainFragments.ProfileFragment;

public class DoctorDetailsFragmentAdapter extends FragmentPagerAdapter {
    public DoctorDetailsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ProfileFragment();
        } else {
            return new ClinicInfoFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Profile";
        } else {
            return "Clinic info";
        }
    }
}
