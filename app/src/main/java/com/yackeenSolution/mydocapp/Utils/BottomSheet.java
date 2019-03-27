package com.yackeenSolution.mydocapp.Utils;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.yackeenSolution.mydocapp.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BottomSheet extends BottomSheetDialogFragment {

    private static final int PICK_IMAGE_FROM_GALLERY = 100;
    private static final int PICK_IMAGE_FROM_CAMERA = 200;
    private BottomSheetListener bottomSheetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet, container, false);

        ImageView mGalleryImage = v.findViewById(R.id.gallery_image);
        ImageView mCameraImage = v.findViewById(R.id.camera_image);
        Button mCameraButton = v.findViewById(R.id.camera_Button);
        LinearLayout mGalleryLayout = v.findViewById(R.id.gallery_layout);
        Button mGalleryButton = v.findViewById(R.id.gallery_button);
        LinearLayout mCameraLayout = v.findViewById(R.id.camera_layout);

        mGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetListener.onButtonClicked(PICK_IMAGE_FROM_GALLERY);
                dismiss();
            }
        });
        mGalleryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetListener.onButtonClicked(PICK_IMAGE_FROM_GALLERY);
                dismiss();
            }
        });
        mGalleryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetListener.onButtonClicked(PICK_IMAGE_FROM_GALLERY);
                dismiss();
            }
        });

        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetListener.onButtonClicked(PICK_IMAGE_FROM_CAMERA);
                dismiss();
            }
        });
        mCameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetListener.onButtonClicked(PICK_IMAGE_FROM_CAMERA);
                dismiss();
            }
        });
        mCameraLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetListener.onButtonClicked(PICK_IMAGE_FROM_CAMERA);
                dismiss();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            bottomSheetListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement bottomSheetListener");
        }
    }

    public interface BottomSheetListener {
        void onButtonClicked(int request);
    }
}
