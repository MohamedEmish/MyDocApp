package com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;

import java.util.Objects;

public class ClinicInfoFragment extends Fragment {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;
        View rootView = inflater.inflate(R.layout.clinic_info_frag, nullParent);

        String info = null;
        if (getArguments() != null) {
            info = getArguments().getString("clinicInfo");
        }
        final TextView textView = rootView.findViewById(R.id.clinic_info_frag_text);
        if (info != null) {
            if (!info.isEmpty()) {
                textView.setText(info);
            } else {
                textView.setText(Objects.requireNonNull(getContext()).getResources().getString(R.string.clinic_info));
            }
        }

        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                textView.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });

        return rootView;
    }
}