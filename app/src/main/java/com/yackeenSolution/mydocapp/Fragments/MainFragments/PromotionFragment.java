package com.yackeenSolution.mydocapp.Fragments.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yackeenSolution.mydocapp.Adapters.AppointmentSmallAdapter;
import com.yackeenSolution.mydocapp.Adapters.PromotionAdapter;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.R;

import java.util.ArrayList;
import java.util.List;

public class PromotionFragment extends Fragment {

    RecyclerView recycleView;
    PromotionAdapter adapter;
    List<Promotion> data = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;


        View rootView = inflater.inflate(R.layout.promotion_frag, nullParent);

        recycleView = rootView.findViewById(R.id.promotion_recycler);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setHasFixedSize(true);
        adapter = new PromotionAdapter();
        recycleView.setAdapter(adapter);

        data.add(new Promotion(
                "Yackeen Internship",
                "Maadi-Cairo",
                "")
        );

        data.add(new Promotion(
                "Yackeen Internship",
                "Maadi-Cairo",
                "")
        );
        data.add(new Promotion(
                "Yackeen Internship",
                "Maadi-Cairo",
                "")
        );
        data.add(new Promotion(
                "Yackeen Internship",
                "Maadi-Cairo",
                "")
        );
        data.add(new Promotion(
                "Yackeen Internship",
                "Maadi-Cairo",
                "")
        );

        adapter.submitList(data);
        adapter.setOnItemClickListener(new PromotionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Promotion promotion) {
                bookPromotion(promotion.getId());
            }
        });

        return rootView;
    }

    private void bookPromotion(int id) {
        // TODO : book promotion
    }
}