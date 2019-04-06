package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.content.Context;
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
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.R;

public class SpecialitySmallAdapter extends ListAdapter<Speciality, SpecialitySmallAdapter.SpecialityViewHolder> {
    private Context mContext;
    private static final DiffUtil.ItemCallback<Speciality> DIFF_CALLBACK = new DiffUtil.ItemCallback<Speciality>() {
        @Override
        public boolean areItemsTheSame(Speciality oldItem, Speciality newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Speciality oldItem, Speciality newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getId() == newItem.getId();
        }
    };

    private SpecialitySmallAdapter.OnItemClickListener listener;

    public SpecialitySmallAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    public void setOnItemClickListener(SpecialitySmallAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialitySmallAdapter.SpecialityViewHolder holder, int position) {
        String name = getItem(position).getName();
        String imageUri = getItem(position).getImageUri();
        Uri uri;
        if (imageUri.equals("")) {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/speciality");
        } else {
            uri = Uri.parse(imageUri);
        }
        Picasso.get().load(uri).into(holder.specialityImageVew);
        if (!getItem(position).isSelected()) {
            holder.specialityImageVew.setColorFilter(mContext.getResources().getColor(R.color.colorGray));
        } else {
            holder.specialityImageVew.setColorFilter(mContext.getResources().getColor(R.color.colorAccent));
        }
        holder.nameTextView.setText(name);
    }

    @NonNull
    @Override
    public SpecialitySmallAdapter.SpecialityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.speciality_small_item, parent, false);
        return new SpecialitySmallAdapter.SpecialityViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(Speciality speciality);
    }

    class SpecialityViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView specialityImageVew;
        LinearLayout layout;

        SpecialityViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.speciality_small_item_name);
            specialityImageVew = itemView.findViewById(R.id.speciality_small_item_image);
            layout = itemView.findViewById(R.id.speciality_small_item_layout);

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