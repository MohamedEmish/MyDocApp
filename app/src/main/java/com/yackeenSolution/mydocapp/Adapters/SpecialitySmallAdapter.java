package com.yackeenSolution.mydocapp.Adapters;

import android.net.Uri;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.R;

public class SpecialitySmallAdapter extends ListAdapter<Speciality, SpecialitySmallAdapter.SpecialityViewHolder> {
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


    public SpecialitySmallAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public SpecialitySmallAdapter.SpecialityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.speciality_small_item, parent, false);
        return new SpecialitySmallAdapter.SpecialityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpecialitySmallAdapter.SpecialityViewHolder holder, int position) {
        String name = getItem(position).getName();
        String imageUri = getItem(position).getImageUri();
        Uri uri;
        if (imageUri.equals("")) {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/speciality");
        } else {
            uri = Uri.parse(imageUri);
        }
        Picasso.get().load(uri).into(holder.SpecialityImageVew);


        holder.nameTextView.setText(name);
    }

    public Speciality getMemberAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(SpecialitySmallAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(Speciality Speciality);
    }


    public class SpecialityViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public ImageView SpecialityImageVew;

        public SpecialityViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.speciality_small_item_name);
            SpecialityImageVew = itemView.findViewById(R.id.speciality_small_item_image);

        }
    }
}