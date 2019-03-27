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

public class GeneralInfoFragment extends Fragment {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;
        View rootView = inflater.inflate(R.layout.general_info_frag, nullParent);
        assert getArguments() != null;
        String info = getArguments().getString("generalInfo");

        final TextView textView = rootView.findViewById(R.id.general_info_frag_text);

        if (info != null && !info.isEmpty()) {
            textView.setText(info);
        } else {
            textView.setText(getResources().getString(R.string.general_info));
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
