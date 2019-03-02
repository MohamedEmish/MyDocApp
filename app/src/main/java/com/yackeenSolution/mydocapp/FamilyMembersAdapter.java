package com.yackeenSolution.mydocapp;

import android.net.Uri;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FamilyMembersAdapter extends ListAdapter<FamilyMember, FamilyMembersAdapter.FamilyMemberViewHolder> {
    private static final DiffUtil.ItemCallback<FamilyMember> DIFF_CALLBACK = new DiffUtil.ItemCallback<FamilyMember>() {
        @Override
        public boolean areItemsTheSame(FamilyMember oldItem, FamilyMember newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(FamilyMember oldItem, FamilyMember newItem) {
            return oldItem.getFirstName().equals(newItem.getFirstName())
                    && oldItem.getLastName().equals(newItem.getLastName())
                    && oldItem.getDate() == newItem.getDate();
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
        String name = getItem(position).getFirstName();
        String relation = getItem(position).getRelation();
        String imageUri = getItem(position).getImageUri();

        Uri uri = Uri.parse(imageUri);

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
            memberImageVew = itemView.findViewById(R.id.family_member_image);
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