package com.yackeenSolution.mydocapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

public class MoreFragment extends Fragment {
    private ConstraintLayout mMoreMyFamily;
    private ConstraintLayout mMoreSettings;
    private ConstraintLayout mMorePrivacy;
    private ConstraintLayout mMoreShare;
    private ConstraintLayout mMoreRateUs;
    private ConstraintLayout mMoreMyAccount;
    private ConstraintLayout mMoreLogInOutLayout;
    private ConstraintLayout mMoreAboutUs;
    private ConstraintLayout mMoreNotification;
    private ConstraintLayout mMoreContactUs;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        View rootView = inflater.inflate(R.layout.more_frag, nullParent);
        TextView logInOut = rootView.findViewById(R.id.more_log_in_out);

        if (SaveSharedPreference.getUserName(getContext()).length() == 0) {
            logInOut.setText(getResources().getText(R.string.log_in));
        } else if (SaveSharedPreference.getUserName(getContext()).length() != 0) {
            logInOut.setText(getResources().getText(R.string.log_out));
        }

        mMoreMyFamily = rootView.findViewById(R.id.more_my_family);
        mMoreSettings = rootView.findViewById(R.id.more_settings);
        mMorePrivacy = rootView.findViewById(R.id.more_privacy);
        mMoreShare = rootView.findViewById(R.id.more_share);
        mMoreRateUs = rootView.findViewById(R.id.more_rate_us);
        mMoreMyAccount = rootView.findViewById(R.id.more_my_account);
        mMoreLogInOutLayout = rootView.findViewById(R.id.more_log_in_out_layout);
        mMoreAboutUs = rootView.findViewById(R.id.more_about_us);
        mMoreNotification = rootView.findViewById(R.id.more_notification);
        mMoreContactUs = rootView.findViewById(R.id.more_contact_us);

        mMoreMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyAccount.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
