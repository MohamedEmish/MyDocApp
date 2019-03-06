package com.yackeenSolution.mydocapp.Fragments.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yackeenSolution.mydocapp.R;

public class PromotionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;


        View rootView = inflater.inflate(R.layout.promotion_frag, nullParent);

        return rootView;
    }
}