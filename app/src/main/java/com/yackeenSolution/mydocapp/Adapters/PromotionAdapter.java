package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import android.net.Uri;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.R;

public class PromotionAdapter extends ListAdapter<Promotion, PromotionAdapter.PromotionViewHolder> {
    private static final DiffUtil.ItemCallback<Promotion> DIFF_CALLBACK = new DiffUtil.ItemCallback<Promotion>() {
        @Override
        public boolean areItemsTheSame(Promotion oldItem, Promotion newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Promotion oldItem, Promotion newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getImageUrl().equals(newItem.getImageUrl())
                    && oldItem.getId() == newItem.getId();
        }
    };
    private PromotionAdapter.OnItemClickListener listener;


    public PromotionAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.promotion_item_layout, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, int position) {
        String mainText = getItem(position).getName();
        String subText = getItem(position).getDescription();
        String imageUri = getItem(position).getImageUrl();
        Uri uri;
        if (!imageUri.equals("")) {
            uri = Uri.parse(imageUri);
            Picasso.get().load(uri).into(holder.image);
        }

        holder.mainText.setText(mainText);
        holder.subText.setText(Html.fromHtml(subText));
    }

    public Promotion getMemberAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(PromotionAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(Promotion promotion);
    }


    public class PromotionViewHolder extends RecyclerView.ViewHolder {

        public TextView mainText;
        public TextView subText;
        public ImageView image;
        public Button book;

        public PromotionViewHolder(View itemView) {
            super(itemView);

            mainText = itemView.findViewById(R.id.promotion_main_text);
            subText = itemView.findViewById(R.id.promotion_sub_text);
            image = itemView.findViewById(R.id.promotion_image);
            book = itemView.findViewById(R.id.promotion_book_button);
            book.setOnClickListener(new View.OnClickListener() {
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