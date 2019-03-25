package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.R;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class FamilyMembersAdapter extends ListAdapter<FamilyMember, FamilyMembersAdapter.FamilyMemberViewHolder> {
    private static final DiffUtil.ItemCallback<FamilyMember> DIFF_CALLBACK = new DiffUtil.ItemCallback<FamilyMember>() {
        @Override
        public boolean areItemsTheSame(FamilyMember oldItem, FamilyMember newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(FamilyMember oldItem, FamilyMember newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getRelationship().equals(newItem.getRelationship())
                    && oldItem.getBirthDate().equals(newItem.getBirthDate());
        }
    };
    private OnItemClickListener listener;

    public FamilyMembersAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public FamilyMemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.family_member_layout, parent, false);
        return new FamilyMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FamilyMemberViewHolder holder, int position) {
        String name = getItem(position).getName();
        String relation = getItem(position).getRelationship();
        String imageUri = getItem(position).getImageUrl();
        Uri uri;

        if (imageUri != null && !imageUri.isEmpty()) {
            uri = Uri.parse(imageUri.replace("//U", "/U"));
        } else {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
        }
        if (!imageUri.contains("http://yakensolution.cloudapp.net/doctoryadmin//UploadedImages")) {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
        }
        holder.nameTextView.setText(name);
        holder.relationTextView.setText(relation);
        Picasso.get().load(uri).into(holder.memberImageVew);


    }

    public FamilyMember getMemberAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(FamilyMember familyMember);
    }

    public class FamilyMemberViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView relationTextView;
        public ImageView memberImageVew;
        public ImageView editButton;

        public FamilyMemberViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.family_member_name);
            relationTextView = itemView.findViewById(R.id.family_member_relation);
            memberImageVew = itemView.findViewById(R.id.family_member_circle_image);
            editButton = itemView.findViewById(R.id.family_member_edit);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
}