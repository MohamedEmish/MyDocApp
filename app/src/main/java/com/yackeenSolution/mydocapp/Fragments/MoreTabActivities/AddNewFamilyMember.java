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

import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.Objects.FamilyRelation;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.Utils.BottomSheet;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
    DataViewModel dataViewModel;
    String type;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_add_new_family_member);
        LinearLayout linearLayout = findViewById(R.id.add_family_member_root);
        Utils.RTLSupport(this, linearLayout);

        mAddFamilyMemberButton = findViewById(R.id.add_family_member_button);
        int userId = Integer.parseInt(SaveSharedPreference.getUserId(this));

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        TextView tvTitle = findViewById(R.id.tvTitle);
        Intent intent = getIntent();

        type = intent.getStringExtra("type");
        if (type.equals("add")) {
            tvTitle.setText(R.string.add_new_member);
            mAddFamilyMemberButton.setText(R.string.add_new_member);
        } else {
            tvTitle.setText(R.string.edit_family_member);
            mAddFamilyMemberButton.setText(R.string.edit_family_member);
            id = Integer.valueOf(intent.getStringExtra("id").trim());
            setUpMemberData(userId, id);
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
        setUpSpinnersData();

        mAddFamilyMemberImage = findViewById(R.id.add_family_member_image);
        mAddFamilyMemberImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "Example");
            }
        });


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

        mAddFamilyMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });
    }

    private void uploadData() {
        HashMap<String, String> fields = new HashMap<>();
        if (!type.equals("add")) {
            fields.put("FamilyMemberId", String.valueOf(id));
        }
        String firstName = mAddFamilyMemberFirstName.getText().toString().trim();
        String lastName = mAddFamilyMemberLastName.getText().toString().trim();

        fields.put("Name", firstName + " " + lastName);
        fields.put("DOB", mAddFamilyMemberDate.getText().toString().trim());
        fields.put("PhoneNumber", mAddFamilyMemberMobile.getText().toString().trim());

        long id = mAddFamilyMemberGenderSpinner.getSelectedItemId();
        String g = "";
        if (id == 1) {
            g = "true";
        } else if (id == 2) {
            g = "false";
        }

        fields.put("Gender", g);
        fields.put("Image", mImageUri.toString().trim());
        fields.put("Id", SaveSharedPreference.getUserId(this));

        dataViewModel.addEditFamilyMember(fields);
    }

    private void setUpMemberData(int userId, int memberId) {

        dataViewModel.getSpecificFamilyMember(userId, memberId).observe(this, new Observer<FamilyMember>() {
            @Override
            public void onChanged(FamilyMember familyMember) {
                if (familyMember != null) {

                    String proPicUrl = familyMember.getImageUrl();
                    mImageUri = Uri.parse(proPicUrl);
                    Picasso.get().load(Uri.parse(proPicUrl)).into(mAddFamilyMemberImage);

                    mAddFamilyMemberFirstName.setText(familyMember.getName());
                    mAddFamilyMemberLastName.setVisibility(View.GONE);
                    mAddFamilyMemberDate.setText(Utils.dateNewFormat(familyMember.getBirthDate()));
                    String gender = familyMember.getGender();
                    if (gender.equals("true")) {
                        mAddFamilyMemberGenderSpinner.setSelection(1);
                    } else if (gender.equals("false")) {
                        mAddFamilyMemberGenderSpinner.setSelection(2);
                    } else {
                        mAddFamilyMemberGenderSpinner.setSelection(0);
                    }
                    mAddFamilyMemberMobile.setText(familyMember.getPhoneNumber());
                }
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

                myCalendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                myCalendar.set(Calendar.YEAR, datePicker.getYear());
                myCalendar.set(Calendar.MONTH, datePicker.getMonth());

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

    private void setUpSpinnersData() {

        dataViewModel.getFamilyRelations().observe(this, new Observer<List<FamilyRelation>>() {
            @Override
            public void onChanged(List<FamilyRelation> familyRelations) {

                List<String> strings = new ArrayList<>();
                strings.add(AddNewFamilyMember.this.getResources().getString(R.string.select_relation));
                if (familyRelations.size() > 0) {
                    for (FamilyRelation relation : familyRelations) {
                        strings.add(relation.getName());
                    }
                    Utils.setupSpinner(AddNewFamilyMember.this, strings, mAddFamilyMemberRelationSpinner);
                }

            }
        });

    }



}
