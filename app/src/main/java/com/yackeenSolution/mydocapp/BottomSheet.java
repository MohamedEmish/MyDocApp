package com.yackeenSolution.mydocapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BottomSheet extends BottomSheetDialogFragment {

    public static final int PICK_IMAGE_FROM_GALLERY = 100;
    public static final int PICK_IMAGE_FROM_CAMERA = 200;
    private BottomSheetListener bottomSheetListener;
    private ImageView mGalleryImage;
    private ImageView mCameraImage;
    private Button mCameraButton;
    private LinearLayout mGalleryLayout;
    private Button mGalleryButton;
    private LinearLayout mCameraLayout;
    private Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet, container, false);

        mGalleryImage = v.findViewById(R.id.gallery_image);
        mCameraImage = v.findViewById(R.id.camera_image);
        mCameraButton = v.findViewById(R.id.camera_Button);
        mGalleryLayout = v.findViewById(R.id.gallery_layout);
        mGalleryButton = v.findViewById(R.id.gallery_button);
        mCameraLayout = v.findViewById(R.id.camera_layout);

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
    public void onAttach(Context context) {
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
