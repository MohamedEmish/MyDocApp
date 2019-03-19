package com.yackeenSolution.mydocapp.ActivitiesAndFragments.MoreTabActivities;

import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Adapters.FamilyMembersAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

public class FamilyMembersViewer extends AppCompatActivity {

    RecyclerView memberRecycleView;
    FamilyMembersAdapter familyMembersAdapter;
    FloatingActionButton fab;
    ImageView back;
    DataViewModel dataViewModel;
    LinearLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_family_members_viewer);
        ConstraintLayout constraintLayout = findViewById(R.id.family_member_viewer_root);
        Utils.RTLSupport(this, constraintLayout);

        back = findViewById(R.id.family_member_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progress = findViewById(R.id.family_member_viewer_progress_bar_layout);

        memberRecycleView = findViewById(R.id.family_member_viewer_recycler);
        memberRecycleView.setLayoutManager(new LinearLayoutManager(this));
        memberRecycleView.setHasFixedSize(true);
        familyMembersAdapter = new FamilyMembersAdapter();
        memberRecycleView.setAdapter(familyMembersAdapter);

        familyMembersAdapter.setOnItemClickListener(new FamilyMembersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FamilyMember familyMember) {
                Intent intent = new Intent(FamilyMembersViewer.this, AddNewFamilyMember.class);
                intent.putExtra("type", "edit");
                intent.putExtra("id", String.valueOf(familyMember.getId()));
                startActivity(intent);
            }
        });

        fab = findViewById(R.id.family_member_viewer_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FamilyMembersViewer.this, AddNewFamilyMember.class);
                intent.putExtra("type", "add");
                startActivity(intent);
            }
        });

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpData();
    }

    private void setUpData() {
        dataViewModel.getMyFamilyMembersList(Integer.parseInt(SaveSharedPreference.getUserId(this))).observe(this, new Observer<List<FamilyMember>>() {
            @Override
            public void onChanged(List<FamilyMember> familyMembers) {
                memberRecycleView.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                    familyMembersAdapter.submitList(familyMembers);
            }
        });
    }
}
