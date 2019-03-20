package com.yackeenSolution.mydocapp.ActivitiesAndFragments.NonMainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.R;

public class AboutUsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.about_us_frag, null);

        String info = getArguments().getString("aboutUs");

        final TextView textView = rootView.findViewById(R.id.about_us_frag_text);

        if (!info.equals(null) || !info.equals("null")) {
            textView.setText(Html.fromHtml(info));
        } else {
            textView.setText(getResources().getString(R.string.about));
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