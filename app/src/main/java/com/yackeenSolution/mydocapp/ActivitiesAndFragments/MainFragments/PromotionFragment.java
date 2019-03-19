package com.yackeenSolution.mydocapp.ActivitiesAndFragments.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.Adapters.PromotionAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.R;

import java.util.ArrayList;
import java.util.List;

public class PromotionFragment extends Fragment {

    RecyclerView recycleView;
    PromotionAdapter adapter;
    List<Promotion> data = new ArrayList<>();
    private DataViewModel dataViewModel;
    private LinearLayout progress;
    private FrameLayout dataLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        View rootView = inflater.inflate(R.layout.promotion_frag, nullParent);

        progress = rootView.findViewById(R.id.promotion_frag_bar_layout);
        dataLayout = rootView.findViewById(R.id.promotion_frame);

        recycleView = rootView.findViewById(R.id.promotion_recycler);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setHasFixedSize(true);
        adapter = new PromotionAdapter();
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PromotionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Promotion promotion) {
                bookPromotion(promotion.getId());
            }
        });

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        dataViewModel.getMyPolicyLiveData();
        setUpData();

        return rootView;
    }

    private void setUpData() {

        dataViewModel.getAllPromotionList().observe(this, new Observer<List<Promotion>>() {
            @Override
            public void onChanged(List<Promotion> promotions) {

                List<Promotion> promotionList = new ArrayList<>();

                if (promotions.size() > 0) {
                    progress.setVisibility(View.GONE);
                    dataLayout.setVisibility(View.VISIBLE);

                    adapter.submitList(promotions);
                }
            }
        });
    }


    private void bookPromotion(int id) {
        // TODO : book promotion
        Toast.makeText(getContext(), "This is promotion no: " + id + "\n what next?!!", Toast.LENGTH_SHORT).show();
    }
}