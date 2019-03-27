package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.R;

public class NotificationAdapter extends ListAdapter<MyNotification, NotificationAdapter.NotificationViewHolder> {
    private static final DiffUtil.ItemCallback<MyNotification> DIFF_CALLBACK = new DiffUtil.ItemCallback<MyNotification>() {
        @Override
        public boolean areItemsTheSame(MyNotification oldItem, MyNotification newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(MyNotification oldItem, MyNotification newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription());
        }
    };
    public NotificationAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notification_layout, parent, false);
        return new NotificationAdapter.NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationViewHolder holder, int position) {
        String title = getItem(position).getTitle();
        String description = getItem(position).getDescription();


        holder.titleTextView.setText(title);
        holder.descriptionTextView.setText(Html.fromHtml(description));

    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView descriptionTextView;

        NotificationViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.notification_title);
            descriptionTextView = itemView.findViewById(R.id.notification_description);
        }
    }

}