package com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.profile_frag, null);

        String info = getArguments().getString("profileInfo");
        TextView textView = rootView.findViewById(R.id.profile_frag_text);
        if (!info.equals(null) || !info.equals("null")) {
            textView.setText(info);
        } else {
            textView.setText(getContext().getResources().getString(R.string.profile_text));
        }

        return rootView;
    }
}
