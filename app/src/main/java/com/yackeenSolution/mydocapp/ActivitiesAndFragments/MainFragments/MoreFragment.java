package com.yackeenSolution.mydocapp.ActivitiesAndFragments.MainFragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.ActivitiesAndFragments.MoreTabActivities.AboutUsActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.MoreTabActivities.ContactUsActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.MoreTabActivities.FamilyMembersViewer;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.MoreTabActivities.MyAccount;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.MoreTabActivities.NotificationActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.MoreTabActivities.PrivacyPolicyActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.MoreTabActivities.SettingsActivity;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.LogActivities.SignInActivity;

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
    private TextView logInOut;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        SaveSharedPreference.setUserEmail(getContext(), "ali");
        SaveSharedPreference.setUserName(getContext(), "ali");


        View rootView = inflater.inflate(R.layout.more_frag, nullParent);


        logInOut = rootView.findViewById(R.id.more_log_in_out);

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
                    showLogOutDialog(getActivity());
                }
            }
        });
        return rootView;
    }

    private void logOut() {
        //TODO: Logout Function
    }

    private void logIn() {
        Intent intent = new Intent(getContext(), SignInActivity.class);
        startActivity(intent);
    }

    private void showLogOutDialog(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.logout_dialog, null, false);

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
