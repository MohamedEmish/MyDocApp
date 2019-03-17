package com.yackeenSolution.mydocapp.Fragments.MoreTabActivities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.Utils.BottomSheet;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddNewFamilyMember extends AppCompatActivity implements BottomSheet.BottomSheetListener {

    public static final int PICK_IMAGE_FROM_GALLERY = 100;
    public static final int PICK_IMAGE_FROM_CAMERA = 200;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener mPicker;
    Uri mImageUri;
    private CircleImageView mAddFamilyMemberImage;
    private Spinner mAddFamilyMemberRelationSpinner;
    private Spinner mAddFamilyMemberGenderSpinner;
    private EditText mAddFamilyMemberDate;
    private Button mAddFamilyMemberButton;
    private EditText mAddFamilyMemberFirstName;
    private EditText mAddFamilyMemberMobile;
    private EditText mAddFamilyMemberLastName;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_add_new_family_member);
        LinearLayout linearLayout = findViewById(R.id.add_family_member_root);
        Utils.RTLSupport(this, linearLayout);

        TextView tvTitle = findViewById(R.id.tvTitle);
        Intent intent = getIntent();

        String type = intent.getStringExtra("type");
        if (type.equals("add")) {
            tvTitle.setText(R.string.add_new_member);
        } else {
            tvTitle.setText(R.string.edit_family_member);
            int id = Integer.valueOf(intent.getStringExtra("id").trim());
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        back = findViewById(R.id.add_new_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAddFamilyMemberGenderSpinner = findViewById(R.id.add_family_member_gender_spinner);
        String[] gender = AddNewFamilyMember.this.getResources().getStringArray(R.array.array_gender_options);
        Utils.setupSpinner(this, gender, mAddFamilyMemberGenderSpinner);
        mAddFamilyMemberRelationSpinner = findViewById(R.id.add_family_member_relation_spinner);
        String[] relation = AddNewFamilyMember.this.getResources().getStringArray(R.array.array_family_members_options);
        Utils.setupSpinner(this, relation, mAddFamilyMemberRelationSpinner);

        mAddFamilyMemberImage = findViewById(R.id.add_family_member_image);
        mAddFamilyMemberImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "Example");
            }
        });


        mAddFamilyMemberButton = findViewById(R.id.add_family_member_button);
        mAddFamilyMemberFirstName = findViewById(R.id.add_family_member_first_name);
        mAddFamilyMemberLastName = findViewById(R.id.add_family_member_last_name);
        mAddFamilyMemberMobile = findViewById(R.id.add_family_member_mobile);

        mAddFamilyMemberDate = findViewById(R.id.add_family_member_date);
        mAddFamilyMemberDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                pickDate();
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void pickDate() {

        myCalendar = Calendar.getInstance();

        mPicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String myFormat = "DD/MM/YYYY"; //In which you need put here
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());

                mAddFamilyMemberDate.setText(format.format(myCalendar.getTime()));
            }
        };
        // start picker with pre-chosen date
        DatePickerDialog dialog = new DatePickerDialog(AddNewFamilyMember.this,
                R.style.Date_Picker,
                mPicker,
                2000, 0, 1);

        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMaxDate(myCalendar.getTimeInMillis());

        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_FROM_GALLERY);
    }

    private void openCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, PICK_IMAGE_FROM_CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        if (requestCode == PICK_IMAGE_FROM_GALLERY && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                mImageUri = resultData.getData();
                mAddFamilyMemberImage.setImageURI(mImageUri);
            }

        } else if (requestCode == PICK_IMAGE_FROM_CAMERA && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                mAddFamilyMemberImage.setImageBitmap(photo);
            }

        } else {
            Toast.makeText(this, "unsupported request", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onButtonClicked(int request) {
        if (request == PICK_IMAGE_FROM_GALLERY) {
            openGallery();
        } else if (request == PICK_IMAGE_FROM_CAMERA) {
            openCamera();
        }
    }


}
