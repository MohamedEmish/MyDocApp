package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.net.Uri;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.R;

public class DoctorSmallAdapter extends ListAdapter<DoctorResult, DoctorSmallAdapter.DoctorSmallViewHolder> {
    private static final DiffUtil.ItemCallback<DoctorResult> DIFF_CALLBACK = new DiffUtil.ItemCallback<DoctorResult>() {
        @Override
        public boolean areItemsTheSame(DoctorResult oldItem, DoctorResult newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(DoctorResult oldItem, DoctorResult newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getQualification().equals(newItem.getQualification())
                    && oldItem.getAddress().equals(newItem.getAddress())
                    && oldItem.getId() == newItem.getId();
        }
    };
    private DoctorSmallAdapter.OnItemClickListener listener;


    public DoctorSmallAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public DoctorSmallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.doctor_small_item, parent, false);
        return new DoctorSmallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctorSmallViewHolder holder, int position) {
        String name = getItem(position).getName();
        String jobTitle = getItem(position).getTitle();
        String imageUri = getItem(position).getImageUrl();
        Uri uri;
        if (imageUri.equals("http://yakensolution.cloudapp.net/doctoryadmin/")) {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
        } else {
            uri = Uri.parse(imageUri);
        }
        Picasso.get().load(uri).into(holder.doctorImageVew);

        holder.nameTextView.setText(name);
        holder.jobTitleTextView.setText(jobTitle);
    }

    public DoctorResult getMemberAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(DoctorSmallAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(DoctorResult DoctorResult);
    }


    public class DoctorSmallViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView jobTitleTextView;
        public ImageView doctorImageVew;
        public LinearLayout linearLayout;

        public DoctorSmallViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.doctor_small_item_name);
            jobTitleTextView = itemView.findViewById(R.id.doctor_small_item_job_title);
            doctorImageVew = itemView.findViewById(R.id.doctor_small_item_image);
            linearLayout = itemView.findViewById(R.id.doctor_small_layout);
            linearLayout.setOnClickListener(new View.OnClickListener() {
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