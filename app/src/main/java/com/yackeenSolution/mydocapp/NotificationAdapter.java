package com.yackeenSolution.mydocapp;

import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotificationAdapter extends ListAdapter<Notification, NotificationAdapter.NotificationViewHolder> {
    private static final DiffUtil.ItemCallback<Notification> DIFF_CALLBACK = new DiffUtil.ItemCallback<Notification>() {
        @Override
        public boolean areItemsTheSame(Notification oldItem, Notification newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Notification oldItem, Notification newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription());
        }
    };
    private FamilyMembersAdapter.OnItemClickListener listener;

    public NotificationAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notification_layout, parent, false);
        return new NotificationAdapter.NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.NotificationViewHolder holder, int position) {
        String title = getItem(position).getTitle();
        String description = getItem(position).getDescription();


        holder.titleTextView.setText(title);
        holder.descriptionTextView.setText(description);


    }

    public Notification getMemberAt(int position) {
        return getItem(position);
    }


    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView descriptionTextView;

        public NotificationViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.notification_title);
            descriptionTextView = itemView.findViewById(R.id.notification_description);
        }
    }

}