package com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;

public class GeneralInfoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.general_info_frag, null);
        TextView textView = rootView.findViewById(R.id.general_info_frag_text);
        textView.setText(getResources().getString(R.string.general_info));

        return rootView;
    }
}
