package com.yackeenSolution.mydocapp.Adapters;

import android.net.Uri;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.R;

public class DoctorResultAdapter extends ListAdapter<DoctorResult, DoctorResultAdapter.DoctorResultViewHolder> {
    private static final DiffUtil.ItemCallback<DoctorResult> DIFF_CALLBACK = new DiffUtil.ItemCallback<DoctorResult>() {
        @Override
        public boolean areItemsTheSame(DoctorResult oldItem, DoctorResult newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(DoctorResult oldItem, DoctorResult newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getJobTitle().equals(newItem.getJobTitle())
                    && oldItem.getWorkPlace().equals(newItem.getWorkPlace())
                    && oldItem.getId() == newItem.getId();
        }
    };
    private OnItemFavClickListener favListener;
    private OnItemReqClickListener reqListener;
    private OnItemClickListener listener;


    public DoctorResultAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public DoctorResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.doctor_search_result_layout, parent, false);
        return new DoctorResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctorResultViewHolder holder, int position) {
        String name = getItem(position).getName();
        String jobTitle = getItem(position).getJobTitle();
        String area = getMemberAt(position).getArea();
        String workPlace = getMemberAt(position).getWorkPlace();

        Uri favUri;
        boolean isFav = getMemberAt(position).getFavorite();
        if (isFav) {
            favUri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/fav2");
        } else {
            favUri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/unfavorite2");
        }
        Picasso.get().load(favUri).into(holder.favoriteButton);


        String imageUri = getItem(position).getImageUrl();
        Uri uri;
        if (imageUri.equals("")) {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
        } else {
            uri = Uri.parse(imageUri);
        }
        Picasso.get().load(uri).into(holder.doctorImageVew);


        holder.nameTextView.setText(name);
        holder.jobTitleTextView.setText(jobTitle);
        holder.workPlaceTextView.setText(workPlace);
        holder.areaTextView.setText(area);


    }

    public DoctorResult getMemberAt(int position) {
        return getItem(position);
    }

    public void setOnItemFavClickListener(OnItemFavClickListener listener) {
        this.favListener = listener;
    }

    public void setOnItemReqClickListener(OnItemReqClickListener reqListener) {
        this.reqListener = reqListener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemFavClickListener {
        void onItemClick(DoctorResult DoctorResult);
    }

    public interface OnItemReqClickListener {
        void onItemClick(DoctorResult DoctorResult);
    }

    public interface OnItemClickListener {
        void onItemClick(DoctorResult DoctorResult);
    }

    public class DoctorResultViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView jobTitleTextView;
        public TextView areaTextView;
        public TextView workPlaceTextView;
        public ImageView doctorImageVew;
        public ImageView favoriteButton;
        public Button request;
        ConstraintLayout layout;

        public DoctorResultViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.result_doctor_name);
            jobTitleTextView = itemView.findViewById(R.id.result_doctor_job_title);
            areaTextView = itemView.findViewById(R.id.result_doctor_work_area);
            workPlaceTextView = itemView.findViewById(R.id.result_doctor_work_place);
            doctorImageVew = itemView.findViewById(R.id.result_doctor_image);

            favoriteButton = itemView.findViewById(R.id.appointment_small_call);
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (favListener != null && position != RecyclerView.NO_POSITION) {
                        favListener.onItemClick(getItem(position));
                    }
                }
            });
            request = itemView.findViewById(R.id.doctor_search_request);
            request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (reqListener != null && position != RecyclerView.NO_POSITION) {
                        reqListener.onItemClick(getItem(position));
                    }
                }
            });

            layout = itemView.findViewById(R.id.result_doctor_layout);
            layout.setOnClickListener(new View.OnClickListener() {
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