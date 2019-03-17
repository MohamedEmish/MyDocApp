package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

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
    private NotificationAdapter.OnItemClickListener listener;

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
        holder.descriptionTextView.setText(Html.fromHtml(description));


    }

    public void setOnItemClickListener(NotificationAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(MyNotification notification);
    }

    public MyNotification getMemberAt(int position) {
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