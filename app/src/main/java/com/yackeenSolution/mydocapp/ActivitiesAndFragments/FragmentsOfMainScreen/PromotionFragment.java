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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog.SignInActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults.DoctorDetailsActivity;
import com.yackeenSolution.mydocapp.Adapters.PromotionAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;

import java.util.List;
import java.util.Objects;

public class PromotionFragment extends Fragment {

    private PromotionAdapter adapter;
    private DataViewModel dataViewModel;
    private LinearLayout progress;
    private FrameLayout dataLayout;

    @Override
    public void onResume() {
        super.onResume();
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        View rootView = inflater.inflate(R.layout.promotion_frag, nullParent);

        progress = rootView.findViewById(R.id.promotion_frag_bar_layout);
        dataLayout = rootView.findViewById(R.id.promotion_frame);

        RecyclerView recycleView = rootView.findViewById(R.id.promotion_recycler);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setHasFixedSize(true);
        adapter = new PromotionAdapter();
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PromotionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Promotion promotion) {
                bookPromotion(promotion.getId());
            }
        });

        return rootView;
    }

    private void setUpData() {

        dataViewModel.getAllPromotionList().observe(this, new Observer<List<Promotion>>() {
            @Override
            public void onChanged(List<Promotion> promotions) {

                if (promotions.size() > 0) {
                    progress.setVisibility(View.GONE);
                    dataLayout.setVisibility(View.VISIBLE);
                    adapter.submitList(promotions);
                }
            }
        });
    }


    private void bookPromotion(int promoId) {

        String id = SaveSharedPreference.getUserId(getContext());
        if (id != null && !id.isEmpty()) {
            // TODO : book promotion
            Toast.makeText(getContext(), "This is promotion no: " + promoId + "\n what next?!!", Toast.LENGTH_SHORT).show();
        } else {
            showLogInDialog(Objects.requireNonNull(getContext()));
        }
    }

    private void showLogInDialog(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.logout_dialog, nullParent, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setView(view);

        TextView textView = view.findViewById(R.id.alert_dialog_text);
        textView.setText(context.getResources().getString(R.string.u_must_login));
        Button yes = view.findViewById(R.id.alert_dialog_yes);
        yes.setText(context.getResources().getString(R.string.log_in));
        Button no = view.findViewById(R.id.alert_dialog_no);
        no.setText(context.getResources().getString(R.string.cancel));
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
                alertDialog.cancel();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

            }
        });
    }

    private void logIn() {
        Intent intent = new Intent(getContext(), SignInActivity.class);
        startActivity(intent);
    }
}