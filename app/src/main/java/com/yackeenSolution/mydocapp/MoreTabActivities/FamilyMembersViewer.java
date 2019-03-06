package com.yackeenSolution.mydocapp.MoreTabActivities;

import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yackeenSolution.mydocapp.Adapters.FamilyMembersAdapter;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.R;

import java.util.ArrayList;
import java.util.List;

public class FamilyMembersViewer extends AppCompatActivity {

    RecyclerView memberRecycleView;
    FamilyMembersAdapter familyMembersAdapter;
    FloatingActionButton fab;
    ImageView back;

    List<FamilyMember> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members_viewer);

        back = findViewById(R.id.family_member_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        memberRecycleView = findViewById(R.id.family_member_viewer_recycler);
        memberRecycleView.setLayoutManager(new LinearLayoutManager(this));
        memberRecycleView.setHasFixedSize(true);
        familyMembersAdapter = new FamilyMembersAdapter();
        memberRecycleView.setAdapter(familyMembersAdapter);

        data.add(new FamilyMember(
                "ali",
                "fares",
                "5/2/1996",
                1,
                "01010101",
                "papa",
                "android.resource://com.yackeenSolution.mydocapp/drawable/doctor"));
        data.add(new FamilyMember(
                "ali",
                "fares",
                "5/2/1996",
                1,
                "01010101",
                "papa",
                "android.resource://com.yackeenSolution.mydocapp/drawable/doctor"));
        familyMembersAdapter.submitList(data);

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
    }
}
