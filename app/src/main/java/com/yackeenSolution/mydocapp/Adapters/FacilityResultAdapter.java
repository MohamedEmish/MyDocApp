package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.graphics.Color;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.yackeenSolution.mydocapp.Objects.FacilityResult;
import com.yackeenSolution.mydocapp.R;

public class FacilityResultAdapter extends ListAdapter<FacilityResult, FacilityResultAdapter.FacilityResultViewHolder> {
    private static final DiffUtil.ItemCallback<FacilityResult> DIFF_CALLBACK = new DiffUtil.ItemCallback<FacilityResult>() {
        @Override
        public boolean areItemsTheSame(FacilityResult oldItem, FacilityResult newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(FacilityResult oldItem, FacilityResult newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getId() == newItem.getId();
        }
    };
    private FacilityResultAdapter.OnItemFavClickListener favListener;
    private FacilityResultAdapter.OnItemAllClickListener allListener;
    private FacilityResultAdapter.OnItemCallClickListener callListener;
    private FacilityResultAdapter.OnItemWebClickListener webListener;
    private FacilityResultAdapter.OnItemLocationClickListener locationListener;


    public FacilityResultAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FacilityResultAdapter.FacilityResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.facility_search_result_layout, parent, false);
        return new FacilityResultAdapter.FacilityResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityResultAdapter.FacilityResultViewHolder holder, int position) {
        String name = getItem(position).getName();
        String area = getMemberAt(position).getArea();

        Uri favUri;
        boolean isFav = getMemberAt(position).isFav();
        if (isFav) {
            favUri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/fav2");
        } else {
            favUri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/unfavorite2");
        }
        Picasso.get().load(favUri).into(holder.favoriteButton);

        String imageUri = getItem(position).getImageUrl();
        Uri uri;
        if (imageUri.equals("") || imageUri.equals("http://yakensolution.cloudapp.net/doctoryadmin/")) {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/hospital_default");
        } else {
            uri = Uri.parse(imageUri);
        }
        Picasso.get().load(uri).into(holder.FacilityImageVew);

        String phone = getMemberAt(position).getPhoneNumber();
        if (phone == null || phone.isEmpty()) {
            holder.callText.setTextColor(Color.GRAY);
        }
        String web = getMemberAt(position).getWebSite();
        if (web == null || web.isEmpty()) {
            holder.webText.setTextColor(Color.GRAY);
        }
        String loc = getMemberAt(position).getLocation();
        if (loc == null || loc.isEmpty()) {
            holder.locText.setTextColor(Color.GRAY);
        }
        holder.nameTextView.setText(name);
        holder.areaTextView.setText(area);

    }

    private FacilityResult getMemberAt(int position) {
        return getItem(position);
    }

    public void setOnItemFavClickListener(FacilityResultAdapter.OnItemFavClickListener listener) {
        this.favListener = listener;
    }

    public void setOnItemCallClickListener(FacilityResultAdapter.OnItemCallClickListener listener) {
        this.callListener = listener;
    }

    public void setOnItemAllClickListener(FacilityResultAdapter.OnItemAllClickListener allListener) {
        this.allListener = allListener;
    }

    public void setOnItemWebClickListener(FacilityResultAdapter.OnItemWebClickListener webListener) {
        this.webListener = webListener;
    }

    public void setOnItemLocationClickListener(FacilityResultAdapter.OnItemLocationClickListener locationListener) {
        this.locationListener = locationListener;
    }

    public interface OnItemFavClickListener {
        void onItemClick(FacilityResult facilityResult);
    }

    public interface OnItemCallClickListener {
        void onItemClick(FacilityResult facilityResult);
    }

    public interface OnItemAllClickListener {
        void onItemClick(FacilityResult facilityResult);
    }

    public interface OnItemWebClickListener {
        void onItemClick(FacilityResult facilityResult);
    }

    public interface OnItemLocationClickListener {
        void onItemClick(FacilityResult facilityResult);
    }

    public class FacilityResultViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView areaTextView;
        ImageView FacilityImageVew;
        ImageView favoriteButton;
        LinearLayout call, location, web;
        public ConstraintLayout item;
        TextView callText, locText, webText;

        FacilityResultViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.result_facility_name);
            areaTextView = itemView.findViewById(R.id.result_facility_area);
            FacilityImageVew = itemView.findViewById(R.id.result_facility_image);

            callText = itemView.findViewById(R.id.result_facility_call_text);
            locText = itemView.findViewById(R.id.result_facility_location_text);
            webText = itemView.findViewById(R.id.result_facility_website_text);

            call = itemView.findViewById(R.id.result_facility_call);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (callListener != null && position != RecyclerView.NO_POSITION) {
                        callListener.onItemClick(getItem(position));
                    }
                }
            });
            location = itemView.findViewById(R.id.result_facility_location);
            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (locationListener != null && position != RecyclerView.NO_POSITION) {
                        locationListener.onItemClick(getItem(position));
                    }
                }
            });
            web = itemView.findViewById(R.id.result_facility_website);
            web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (webListener != null && position != RecyclerView.NO_POSITION) {
                        webListener.onItemClick(getItem(position));
                    }
                }
            });
            item = itemView.findViewById(R.id.facility_result_layout);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (allListener != null && position != RecyclerView.NO_POSITION) {
                        allListener.onItemClick(getItem(position));
                    }
                }
            });

            favoriteButton = itemView.findViewById(R.id.facility_result_favorite_indicator);
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (favListener != null && position != RecyclerView.NO_POSITION) {
                        favListener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
}