package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.net.Uri;

import androidx.annotation.NonNull;
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
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.R;

public class InsuranceSmallAdapter extends ListAdapter<Insurance, InsuranceSmallAdapter.InsuranceViewHolder> {
    private static final DiffUtil.ItemCallback<Insurance> DIFF_CALLBACK = new DiffUtil.ItemCallback<Insurance>() {
        @Override
        public boolean areItemsTheSame(Insurance oldItem, Insurance newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Insurance oldItem, Insurance newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getId() == newItem.getId();
        }
    };
    private InsuranceSmallAdapter.OnItemClickListener listener;


    public InsuranceSmallAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public InsuranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.insurance_small_item, parent, false);
        return new InsuranceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InsuranceViewHolder holder, int position) {
        String name = getItem(position).getName();
        String imageUri = getItem(position).getImageUri();
        Uri uri;
        if (imageUri.equals("")) {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/insurance");
        } else {
            uri = Uri.parse(imageUri);
        }
        Picasso.get().load(uri).into(holder.insuranceImageVew);


        holder.nameTextView.setText(name);
    }

    public void setOnItemClickListener(InsuranceSmallAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(Insurance insurance);
    }


    public class InsuranceViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView insuranceImageVew;
        public LinearLayout linearLayout;

        InsuranceViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.insurance_small_item_name);
            insuranceImageVew = itemView.findViewById(R.id.insurance_small_item_image);
            linearLayout = itemView.findViewById(R.id.insurance_small_item_layout);
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