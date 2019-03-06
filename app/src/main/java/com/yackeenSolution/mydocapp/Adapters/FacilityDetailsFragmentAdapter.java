package com.yackeenSolution.mydocapp.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yackeenSolution.mydocapp.Fragments.NonMainFragments.AboutUsFragment;
import com.yackeenSolution.mydocapp.Fragments.NonMainFragments.GeneralInfoFragment;

public class FacilityDetailsFragmentAdapter extends FragmentPagerAdapter {
    public FacilityDetailsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AboutUsFragment();
        } else {
            return new GeneralInfoFragment();
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
            return "About us";
        } else {
            return "General info";
        }
    }
}
