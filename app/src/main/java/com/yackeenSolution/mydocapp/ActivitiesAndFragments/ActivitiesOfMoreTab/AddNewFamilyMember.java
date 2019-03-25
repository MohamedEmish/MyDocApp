package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.yackeenSolution.mydocapp.Objects.FamilyMemberToUpload;
import com.yackeenSolution.mydocapp.Objects.FamilyRelation;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.BottomSheet;
import com.yackeenSolution.mydocapp.Utils.ImageFilePath;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddNewFamilyMember extends AppCompatActivity implements BottomSheet.BottomSheetListener {

    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 321;

    public static final int PICK_IMAGE_FROM_GALLERY = 100;
    public static final int PICK_IMAGE_FROM_CAMERA = 200;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private static final String TAG = AddNewFamilyMember.class.getCanonicalName();
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener datePicker;
    private Uri mImageUri = null;
    private CircleImageView mAddFamilyMemberImage;
    private Spinner mAddFamilyMemberRelationSpinner;
    private Spinner mAddFamilyMemberGenderSpinner;
    private EditText mAddFamilyMemberDate;
    private Button mAddFamilyMemberButton;
    private EditText mAddFamilyMemberFirstName;
    private EditText mAddFamilyMemberMobile;
    private EditText mAddFamilyMemberLastName;
    private ImageView back;
    private DataViewModel dataViewModel;
    private String type;
    private Integer id;
    private String path;
    private String name, DOB, gender, mobile, relation, image;
    private LinearLayout progress, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_add_new_family_member);
        LinearLayout linearLayout = findViewById(R.id.add_family_member_root);
        Utils.RTLSupport(this, linearLayout);

        // hide on start keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Data view model initialization
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        mAddFamilyMemberButton = findViewById(R.id.add_family_member_button);
        mAddFamilyMemberButton.setClickable(false);

        // Get intent Data
        TextView tvTitle = findViewById(R.id.tvTitle);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if (type.equals("add")) {
            tvTitle.setText(R.string.add_new_member);
            mAddFamilyMemberButton.setText(R.string.add_new_member);
        } else {
            tvTitle.setText(R.string.edit_family_member);
            mAddFamilyMemberButton.setText(R.string.save);
            id = Integer.valueOf(intent.getStringExtra("id").trim());
            name = intent.getStringExtra("name");
            DOB = intent.getStringExtra("DOB");
            gender = intent.getStringExtra("gender");
            mobile = intent.getStringExtra("mobile");
            relation = intent.getStringExtra("relation");
            image = intent.getStringExtra("image");
        }

        // EditText change indicator
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mAddFamilyMemberButton.setClickable(true);
                mAddFamilyMemberButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        back = findViewById(R.id.add_new_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Spinners data loading
        mAddFamilyMemberGenderSpinner = findViewById(R.id.add_family_member_gender_spinner);
        String[] gender = AddNewFamilyMember.this.getResources().getStringArray(R.array.array_gender_options);
        Utils.setupSpinner(this, gender, mAddFamilyMemberGenderSpinner);

        mAddFamilyMemberRelationSpinner = findViewById(R.id.add_family_member_relation_spinner);
        setUpSpinnersData();

        // Image selection
        mAddFamilyMemberImage = findViewById(R.id.add_family_member_image);
        mAddFamilyMemberImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "Example");
            }
        });

        progress = findViewById(R.id.add_family_progress_bar_layout);
        data = findViewById(R.id.add_family_member_data_layout);

        // Adding text watcher
        mAddFamilyMemberFirstName = findViewById(R.id.add_family_member_first_name);
        mAddFamilyMemberFirstName.addTextChangedListener(textWatcher);
        mAddFamilyMemberLastName = findViewById(R.id.add_family_member_last_name);
        mAddFamilyMemberLastName.addTextChangedListener(textWatcher);
        mAddFamilyMemberMobile = findViewById(R.id.add_family_member_mobile);
        mAddFamilyMemberMobile.addTextChangedListener(textWatcher);
        mAddFamilyMemberDate = findViewById(R.id.add_family_member_date);
        mAddFamilyMemberDate.addTextChangedListener(textWatcher);
        mAddFamilyMemberDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                pickDate();
            }
        });

        // Uploading Data
        mAddFamilyMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUploadData();
            }
        });
    }

    private void setUpOldData() {

        String[] splitName = name.split("\\s+");
        mAddFamilyMemberFirstName.setText(splitName[0]);
        mAddFamilyMemberLastName.setText(splitName[1]);
        mAddFamilyMemberMobile.setText(mobile);
        mAddFamilyMemberDate.setText(Utils.dateAppFormat(DOB));
        if (gender.equals("true")) {
            mAddFamilyMemberGenderSpinner.setSelection(1);
        } else {
            mAddFamilyMemberGenderSpinner.setSelection(2);
        }

        int relationId = Integer.parseInt(relation);
        mAddFamilyMemberRelationSpinner.setSelection(relationId);

        Uri uri;

        if (image != null && !image.isEmpty()) {
            uri = Uri.parse(image.replace("//U", "/U"));
        } else {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
        }
        if (!image.contains("http://yakensolution.cloudapp.net/doctoryadmin//UploadedImages")) {
            uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
        }
        Picasso.get().load(uri).into(mAddFamilyMemberImage);
        mImageUri = uri;
        mAddFamilyMemberButton.setClickable(false);
        mAddFamilyMemberButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
    }

    private void checkUploadData() {
        boolean isAllOk = true;
        // check firstName Entry
        if (!Utils.isValueSet(mAddFamilyMemberFirstName, AddNewFamilyMember.this.getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
            return;
        }

        // check lastName Entry
        if (!Utils.isValueSet(mAddFamilyMemberLastName, AddNewFamilyMember.this.getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
            return;
        }


        // check phone number Entry and Validity
        if (!Utils.isValueSet(mAddFamilyMemberMobile, AddNewFamilyMember.this.getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
            return;
        } else if (!Utils.isValidNumber(mAddFamilyMemberMobile, AddNewFamilyMember.this.getResources().getString(R.string.invalid_number_error))) {
            isAllOk = false;
            return;
        }


        // check data Entry
        if (!Utils.isValueSet(mAddFamilyMemberDate, AddNewFamilyMember.this.getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
            return;
        }


        // check genderSpinner selection
        if (mAddFamilyMemberGenderSpinner.getSelectedItemId() == 0) {
            Toast.makeText(this, AddNewFamilyMember.this.getResources().getString(R.string.gender_is_must), Toast.LENGTH_SHORT).show();
            isAllOk = false;
            return;
        }

        // check relationSpinner selection
        if (mAddFamilyMemberRelationSpinner.getSelectedItemId() == 0) {
            Toast.makeText(this, AddNewFamilyMember.this.getResources().getString(R.string.relation_is_must), Toast.LENGTH_SHORT).show();
            isAllOk = false;
            return;
        }

        // check genderSpinner correctness
        if (mAddFamilyMemberRelationSpinner.getSelectedItemId() == 1 ||
                mAddFamilyMemberRelationSpinner.getSelectedItemId() == 4 ||
                mAddFamilyMemberRelationSpinner.getSelectedItemId() == 6) {
            if (mAddFamilyMemberGenderSpinner.getSelectedItemId() == 2) {
                Toast.makeText(this, AddNewFamilyMember.this.getResources().getString(R.string.gender_correct), Toast.LENGTH_SHORT).show();
                isAllOk = false;
                return;
            }
        } else {
            if (mAddFamilyMemberRelationSpinner.getSelectedItemId() == 2 ||
                    mAddFamilyMemberRelationSpinner.getSelectedItemId() == 3 ||
                    mAddFamilyMemberRelationSpinner.getSelectedItemId() == 5) {
                if (mAddFamilyMemberGenderSpinner.getSelectedItemId() == 1) {
                    Toast.makeText(this, AddNewFamilyMember.this.getResources().getString(R.string.gender_correct), Toast.LENGTH_SHORT).show();
                    isAllOk = false;
                    return;
                }
            }
        }

        // check imageUrl correctness
        if (mImageUri == null) {
            Toast.makeText(this, AddNewFamilyMember.this.getResources().getString(R.string.set_image), Toast.LENGTH_SHORT).show();
            isAllOk = false;
            return;
        }

        if (isAllOk) {
            imageUrlToUpload();
        } else {
            Toast.makeText(this, AddNewFamilyMember.this.getResources().getString(R.string.edit_text_error), Toast.LENGTH_SHORT).show();
        }
    }

    public void imageUrlToUpload() {

        if (type.equals("add")) {
            File file = new File(path);

            final RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("InternTest", file.getName(), requestBody);

            RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");

            dataViewModel.uploadedImageUrlString(fileToUpload, description).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Forward(s);
                }
            });
        } else {
            Forward(image);
        }


    }

    private void Forward(String imageUrl) {
        FamilyMemberToUpload familyMember = new FamilyMemberToUpload();
        if (!type.equals("add")) {
            familyMember.setFamilyMemberId(id);
        } else {
            familyMember.setFamilyMemberId(null);
        }
        familyMember.setFirstName(mAddFamilyMemberFirstName.getText().toString().trim());
        familyMember.setLastName(mAddFamilyMemberLastName.getText().toString().trim());
        familyMember.setDate(Utils.dateToApiFormat(mAddFamilyMemberDate.getText().toString().trim()));
        familyMember.setPhoneNumber(mAddFamilyMemberMobile.getText().toString().trim());

        long id = mAddFamilyMemberGenderSpinner.getSelectedItemId();
        Boolean g = null;
        if (id == 1) {
            g = true;
        } else if (id == 2) {
            g = false;
        }
        familyMember.setGender(g);
        familyMember.setUserId(Integer.valueOf(SaveSharedPreference.getUserId(this)));

        familyMember.setImageUrl(mImageUri.toString().trim());

        int relation = mAddFamilyMemberRelationSpinner.getSelectedItemPosition();
        familyMember.setEmail(null);
        familyMember.setRelationId(relation);
        String img = imageUrl.replace("http://yakensolution.cloudapp.net/doctoryadmin/", "")
                .replace("http://yakensolution.cloudapp.net/doctoryadmin//", "")
                .replace("\"", "");

        familyMember.setImageUrl(img);

        dataViewModel.addEditFamilyMember(familyMember);
        if (type.equals("add")) {
            Toast.makeText(this, AddNewFamilyMember.this.getResources().getString(R.string.added_successfully), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, AddNewFamilyMember.this.getResources().getString(R.string.done), Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(AddNewFamilyMember.this, FamilyMembersViewer.class);
        startActivity(intent);

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void pickDate() {

        myCalendar = Calendar.getInstance();

        datePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String myFormat = "dd/MM/YYYY"; //In which you need put here
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());

                myCalendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                myCalendar.set(Calendar.YEAR, datePicker.getYear());
                myCalendar.set(Calendar.MONTH, datePicker.getMonth());

                mAddFamilyMemberDate.setText(format.format(myCalendar.getTime()));
                mAddFamilyMemberDate.setTextColor(getResources().getColor(R.color.colorGray));
            }
        };
        // start picker with pre-chosen date
        DatePickerDialog dialog = new DatePickerDialog(AddNewFamilyMember.this,
                R.style.Date_Picker,
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

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

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        path = image.getAbsolutePath();
        return image;
    }

    private void openCamera() {
        // TODO :: last proplem here CAM INTENT
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.d(TAG, "onClick: " + ex.getMessage());
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                        "com.yackeenSolution.mydocapp.provider", photoFile);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePicture, PICK_IMAGE_FROM_CAMERA);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        if (requestCode == PICK_IMAGE_FROM_GALLERY && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                mImageUri = resultData.getData();
                mAddFamilyMemberImage.setImageURI(mImageUri);
                path = ImageFilePath.getPath(this, mImageUri);
            }

        } else if (requestCode == PICK_IMAGE_FROM_CAMERA && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                Picasso.get().load(path).into(mAddFamilyMemberImage);
            }
        }

    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public boolean checkPermissionWrite_EXTERNAL_STORAGE(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context, final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{permission},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    @Override
    public void onButtonClicked(int request) {
        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            if (request == PICK_IMAGE_FROM_GALLERY) {
                openGallery();
            } else if (request == PICK_IMAGE_FROM_CAMERA) {
                if (checkPermissionWrite_EXTERNAL_STORAGE(this)) {
                    openCamera();
                }
            }
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
                    progress.setVisibility(View.GONE);
                    data.setVisibility(View.VISIBLE);
                    if (!type.equals("add")) {
                        setUpOldData();
                    }
                }

            }
        });

    }

}