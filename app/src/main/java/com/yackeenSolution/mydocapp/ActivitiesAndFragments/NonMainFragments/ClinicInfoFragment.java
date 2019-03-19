package com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;

public class ClinicInfoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.clinic_info_frag, null);

        String info = getArguments().getString("clinicInfo");
        TextView textView = rootView.findViewById(R.id.clinic_info_frag_text);
        if (!info.equals(null) || !info.equals("null")) {
            textView.setText(Html.fromHtml(info));
        } else {
            textView.setText(getContext().getResources().getString(R.string.clinic_info));
        }

        return rootView;
    }
}