package com.yackeenSolution.mydocapp.Fragments.NonMainFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;

public class AboutUsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.about_us_frag, null);

        TextView textView = rootView.findViewById(R.id.about_us_frag_text);
        textView.setText(getResources().getString(R.string.about));

        return rootView;
    }
}
