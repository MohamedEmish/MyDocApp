package com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfMainScreen;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab.AboutUsActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab.ContactUsActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab.FamilyMembersViewer;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab.MyAccount;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab.NotificationActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab.PrivacyPolicyActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab.SettingsActivity;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.UserToken;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog.SignInActivity;

import java.util.Objects;

public class MoreFragment extends Fragment {
    private TextView logInOut;
    private DataViewModel dataViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        View rootView = inflater.inflate(R.layout.more_frag, nullParent);


        logInOut = rootView.findViewById(R.id.more_log_in_out);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        if (SaveSharedPreference.getUserName(getContext()).length() == 0) {
            logInOut.setText(getResources().getText(R.string.log_in));
        } else if (SaveSharedPreference.getUserName(getContext()).length() != 0) {
            logInOut.setText(getResources().getText(R.string.log_out));
        }

        ConstraintLayout mMoreMyFamily = rootView.findViewById(R.id.more_my_family);
        ConstraintLayout mMoreSettings = rootView.findViewById(R.id.more_settings);
        ConstraintLayout mMorePrivacy = rootView.findViewById(R.id.more_privacy);
        ConstraintLayout mMoreShare = rootView.findViewById(R.id.more_share);
        ConstraintLayout mMoreRateUs = rootView.findViewById(R.id.more_rate_us);
        ConstraintLayout mMoreMyAccount = rootView.findViewById(R.id.more_my_account);
        ConstraintLayout mMoreLogInOutLayout = rootView.findViewById(R.id.more_log_in_out_layout);
        ConstraintLayout mMoreAboutUs = rootView.findViewById(R.id.more_about_us);
        ConstraintLayout mMoreNotification = rootView.findViewById(R.id.more_notification);
        ConstraintLayout mMoreContactUs = rootView.findViewById(R.id.more_contact_us);

        mMoreMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyAccount.class);
                startActivity(intent);
            }
        });

        mMoreMyFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FamilyMembersViewer.class);
                startActivity(intent);
            }
        });
        mMoreNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        mMorePrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

        mMoreAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        mMoreContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ContactUsActivity.class);
                startActivity(intent);
            }
        });

        mMoreSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        mMoreShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : Share function
                Toast.makeText(getContext(), "Shared", Toast.LENGTH_SHORT).show();
            }
        });

        mMoreRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : Rate us function
                Toast.makeText(getContext(), "Rated", Toast.LENGTH_SHORT).show();

            }
        });

        mMoreLogInOutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state = logInOut.getText().toString().trim();
                if (state.equals(getResources().getString(R.string.log_in))) {
                    logIn();
                } else {
                    showLogOutDialog(Objects.requireNonNull(getActivity()));
                }
            }
        });
        return rootView;
    }

    private void logIn() {
        Intent intent = new Intent(getContext(), SignInActivity.class);
        startActivity(intent);
    }

    private void showLogOutDialog(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.logout_dialog, nullParent, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setView(view);

        Button yes = view.findViewById(R.id.alert_dialog_yes);
        Button no = view.findViewById(R.id.alert_dialog_no);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserToken token = new UserToken();
                token.setId(Integer.parseInt(SaveSharedPreference.getUserId(getContext())));
                token.setEmail(SaveSharedPreference.getUserEmail(getContext()));
                dataViewModel.logout(token);
                SaveSharedPreference.clearUserName(getActivity());
                SaveSharedPreference.clearUserEmail(getActivity());
                SaveSharedPreference.clearUserId(getActivity());
                logInOut.setText(getResources().getText(R.string.log_in));
                alertDialog.cancel();
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

            }
        });
    }

}
