package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yackeenSolution.mydocapp.Adapters.FamilyMembersAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FamilyMembersViewer extends AppCompatActivity {

    private RecyclerView memberRecycleView;
    private FamilyMembersAdapter familyMembersAdapter;
    private FloatingActionButton fab;
    private DataViewModel dataViewModel;
    private LinearLayout progress, noData;

    @Override
    protected void onResume() {
        super.onResume();

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_family_members_viewer);
        ConstraintLayout constraintLayout = findViewById(R.id.family_member_viewer_root);
        Utils.RTLSupport(this, constraintLayout);

        ImageView back = findViewById(R.id.family_member_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progress = findViewById(R.id.family_member_viewer_progress_bar_layout);
        noData = findViewById(R.id.family_member_viewer_no_data);

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
                intent.putExtra("id", String.valueOf(familyMember.getFamilyMemberId()));
                intent.putExtra("name", familyMember.getName());
                intent.putExtra("DOB", familyMember.getBirthDate());
                intent.putExtra("gender", familyMember.getGender());
                intent.putExtra("mobile", familyMember.getPhoneNumber());
                intent.putExtra("relation", String.valueOf(familyMember.getRelationshipId()));
                intent.putExtra("image", familyMember.getImageUrl());
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

        memberRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    if (fab.isShown()) {
                        fab.hide();
                    }
                } else if (dy < 0) {
                    if (!fab.isShown()) {
                        fab.show();
                    }
                }
            }
        });

    }

    private void setUpData() {
        dataViewModel.getMyFamilyMembersList(Integer.parseInt(SaveSharedPreference.getUserId(this))).observe(this, new Observer<List<FamilyMember>>() {
            @Override
            public void onChanged(List<FamilyMember> familyMembers) {
                progress.setVisibility(View.GONE);
                if (familyMembers.size() > 0) {
                    memberRecycleView.setVisibility(View.VISIBLE);
                    familyMembersAdapter.submitList(familyMembers);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}