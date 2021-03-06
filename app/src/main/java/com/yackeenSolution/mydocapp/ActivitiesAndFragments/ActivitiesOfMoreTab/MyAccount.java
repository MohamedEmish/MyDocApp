package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

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

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.UserData;
import com.yackeenSolution.mydocapp.Objects.UserDataToUpload;
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
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyAccount extends AppCompatActivity implements BottomSheet.BottomSheetListener {

    public static final int PICK_IMAGE_FROM_GALLERY = 100;
    public static final int PICK_IMAGE_FROM_CAMERA = 200;
    public static final int PICK_IMAGE_FROM_GALLERY_FOR_INSURANCE = 300;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 321;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private static final String TAG = MyAccount.class.getCanonicalName();

    private Calendar myCalendar;
    private UserData mUserData;
    private Spinner
            genderSpinner,
            insuranceSpinner;
    private ImageView
            personalInfoExpand,
            insuranceInfoExpand,
            changePassExpand;
    private int personalInfoIndicator = 0;
    private int insuranceInfoIndicator = 0;
    private int changePassIndicator = 0;

    private LinearLayout personalInfoLayout;
    private LinearLayout insuranceInfoLayout;
    private LinearLayout changePassLayout;
    private LinearLayout progress;

    private CircleImageView profilePic;
    private Uri mInsuranceImageUri;
    private String
            oldUri,
            proPicUrl,
            path;
    private Button
            changePass,
            saveChanges;

    private EditText
            newPassword;
    private EditText newFirstName;
    private EditText newLastName;
    private EditText newDate;
    private EditText newMobile;
    private EditText email;

    private DataViewModel dataViewModel;

    private TextView
            firstName,
            lastName;

    @Override
    protected void onResume() {
        super.onResume();
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setupData(Integer.parseInt(SaveSharedPreference.getUserId(this)));
        setUpSpinnerData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        SaveSharedPreference.getLanguage(this);
        Utils.setLocale(this);
        setContentView(R.layout.activity_my_account);
        ConstraintLayout constraintLayout = findViewById(R.id.my_account_root);
        Utils.RTLSupport(this, constraintLayout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ImageView newInsuranceImage = findViewById(R.id.my_account_insurance_card_image);
        newInsuranceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(PICK_IMAGE_FROM_GALLERY_FOR_INSURANCE);
            }
        });

        genderSpinner = findViewById(R.id.my_account_gender_spinner);
        String[] gender = MyAccount.this.getResources().getStringArray(R.array.array_gender_options);
        Utils.setupSpinner(this, gender, genderSpinner);

        progress = findViewById(R.id.my_account_progress_bar_layout);

        insuranceSpinner = findViewById(R.id.my_account_insurance_spinner);

        firstName = findViewById(R.id.my_account_first_name_Text_view);
        lastName = findViewById(R.id.my_account_last_name_text_view);

        ImageView back = findViewById(R.id.my_account_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profilePic = findViewById(R.id.my_account_image_view);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "Example");
            }
        });

        ConstraintLayout personalInfoMainLayout = findViewById(R.id.my_account_personal_info_main_layout);
        personalInfoLayout = findViewById(R.id.my_account_personal_info_layout);
        personalInfoExpand = findViewById(R.id.my_account_personal_info_arrow);
        personalInfoMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personalInfoIndicator == 0) {
                    personalInfoIndicator = 1;
                    personalInfoExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_down_arrow));
                    personalInfoLayout.setVisibility(View.VISIBLE);
                } else {
                    personalInfoIndicator = 0;
                    personalInfoExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_arrow));
                    personalInfoLayout.setVisibility(View.GONE);
                }

                if (insuranceInfoIndicator == 1) {
                    insuranceInfoIndicator = 0;
                    insuranceInfoExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_arrow));
                    insuranceInfoLayout.setVisibility(View.GONE);
                }


                if (changePassIndicator == 1) {
                    changePassIndicator = 0;
                    changePassExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_arrow));
                    changePassLayout.setVisibility(View.GONE);
                }
            }
        });

        ConstraintLayout insuranceInfoMainLayout = findViewById(R.id.my_account_insurance_main_layout);
        insuranceInfoLayout = findViewById(R.id.my_account_insurance_layout);
        insuranceInfoExpand = findViewById(R.id.my_account_insurance_info_arrow);
        insuranceInfoMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insuranceInfoIndicator == 0) {
                    insuranceInfoIndicator = 1;
                    insuranceInfoExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_down_arrow));
                    insuranceInfoLayout.setVisibility(View.VISIBLE);
                } else {
                    insuranceInfoIndicator = 0;
                    insuranceInfoExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_arrow));
                    insuranceInfoLayout.setVisibility(View.GONE);
                }

                if (personalInfoIndicator == 1) {
                    personalInfoIndicator = 0;
                    personalInfoExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_arrow));
                    personalInfoLayout.setVisibility(View.GONE);
                }

                if (changePassIndicator == 1) {
                    changePassIndicator = 0;
                    changePassExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_arrow));
                    changePassLayout.setVisibility(View.GONE);
                }
            }
        });

        ConstraintLayout changePassMainLayout = findViewById(R.id.my_account_change_pass_main_layout);
        changePassLayout = findViewById(R.id.my_account_change_pass_layout);
        changePassExpand = findViewById(R.id.my_account_change_pass_info_arrow);
        changePassMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changePassIndicator == 0) {
                    changePassIndicator = 1;
                    changePassExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_down_arrow));
                    changePassLayout.setVisibility(View.VISIBLE);
                } else {
                    changePassIndicator = 0;
                    changePassExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_arrow));
                    changePassLayout.setVisibility(View.GONE);
                }

                if (personalInfoIndicator == 1) {
                    personalInfoIndicator = 0;
                    personalInfoExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_arrow));
                    personalInfoLayout.setVisibility(View.GONE);
                }

                if (insuranceInfoIndicator == 1) {
                    insuranceInfoIndicator = 0;
                    insuranceInfoExpand.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_arrow));
                    insuranceInfoLayout.setVisibility(View.GONE);
                }
            }
        });

        changePass = findViewById(R.id.my_account_change_pass_button);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewPass();
            }
        });
        newPassword = findViewById(R.id.my_account_new_password);
        newPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 6) {
                    changePass.setClickable(true);
                    changePass.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                }

                if (s.length() <= 6) {
                    changePass.setClickable(false);
                    changePass.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorDivider)));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        saveChanges = findViewById(R.id.my_account_save_button);
        saveChanges.setClickable(false);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUrlToUpload();
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                saveChanges.setClickable(true);
                saveChanges.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        newFirstName = findViewById(R.id.my_account_first_name_edit_text);
        newFirstName.addTextChangedListener(textWatcher);

        newLastName = findViewById(R.id.my_account_last_name_edit_text);
        newLastName.addTextChangedListener(textWatcher);

        newMobile = findViewById(R.id.my_account_mobile_edit_text);
        newMobile.addTextChangedListener(textWatcher);

        EditText newNationalId = findViewById(R.id.my_account_national_id_edit_text);
        newNationalId.addTextChangedListener(textWatcher);

        newDate = findViewById(R.id.my_account_date_edit_text);
        newDate.addTextChangedListener(textWatcher);
        newDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                pickDate();
            }
        });

        email = findViewById(R.id.my_account_email_edit_text);
    }

    private void setNewPass() {
        UserDataToUpload user = new UserDataToUpload();
        user.setPassword(newPassword.getText().toString().trim());
        user.setId(Integer.parseInt(SaveSharedPreference.getUserId(this)));
        String app = mUserData.getAppointmentReminder();
        if (app.equals("true")) {
            user.setAppointmentReminder(true);
        } else {
            user.setAppointmentReminder(false);
        }
        String notify = mUserData.getEnableNotification();
        if (notify.equals("true")) {
            user.setEnableNotification(true);
        } else {
            user.setEnableNotification(false);
        }
        user.setDoctorId(null);
        user.setEmail(mUserData.getEmail());
        user.setLastName(mUserData.getLastName());
        String img = mUserData.getImageUri().
                replace("http://yakensolution.cloudapp.net/doctoryadmin/", "")
                .replace("http://yakensolution.cloudapp.net/doctoryadmin//", "")
                .replace("\"", "");
        user.setImageUri(img);
        user.setDateOfBirth(mUserData.getBirthDate());
        user.setPhoneNumber(mUserData.getMobileNumber());
        user.setFirstName(mUserData.getFirstName());
        user.setInsuranceCompanyId(mUserData.getInsuranceId());
        user.setInsuranceCompanyImageUrl(mUserData.getInsuranceImage());
        String g = mUserData.getGender();
        if (g.equals("true")) {
            user.setGender(true);
        } else {
            user.setGender(false);
        }

        dataViewModel.editUserData(user, this);
        Toast.makeText(this, MyAccount.this.getResources().getString(R.string.done), Toast.LENGTH_SHORT).show();
        MyAccount.this.finish();
    }

    private void updateProfile(String image) {
        final UserDataToUpload user = new UserDataToUpload();
        user.setId(Integer.parseInt(SaveSharedPreference.getUserId(this)));
        user.setFirstName(newFirstName.getText().toString().trim());
        user.setLastName(newLastName.getText().toString().trim());
        user.setDateOfBirth(Utils.dateToApiFormat(newDate.getText().toString().trim()));
        user.setEmail(email.getText().toString().trim());
        user.setPassword(mUserData.getPassword());
        String name = user.getFirstName() + " " + user.getLastName();
        SaveSharedPreference.setUserName(this, name);

        String app = mUserData.getAppointmentReminder();
        if (app.equals("true")) {
            user.setAppointmentReminder(true);
        } else {
            user.setAppointmentReminder(false);
        }
        String notify = mUserData.getEnableNotification();
        if (notify.equals("true")) {
            user.setEnableNotification(true);
        } else {
            user.setEnableNotification(false);
        }
        user.setDoctorId(null);
        image = image.replace("http://yakensolution.cloudapp.net/doctoryadmin/", "")
                .replace("http://yakensolution.cloudapp.net/doctoryadmin//", "")
                .replace("\"", "");

        user.setImageUri(image);
        user.setPhoneNumber(mUserData.getMobileNumber());
        user.setInsuranceCompanyId(mUserData.getInsuranceId());
        user.setInsuranceCompanyImageUrl(mUserData.getInsuranceImage());

        long id = genderSpinner.getSelectedItemId();
        boolean g;
        g = id == 1;
        user.setGender(g);
        user.setPhoneNumber(newMobile.getText().toString().trim());

        String imageUrl = mUserData.getImageUri().replace("http://yakensolution.cloudapp.net/doctoryadmin/", "")
                .replace("http://yakensolution.cloudapp.net/doctoryadmin//", "")
                .replace("\"", "");

        user.setImageUri(imageUrl);

        int inId = insuranceSpinner.getSelectedItemPosition();
        user.setInsuranceCompanyId(inId);

        String insuranceString;
        if (mInsuranceImageUri != null) {
            insuranceString = mInsuranceImageUri.toString().trim();
        } else {
            insuranceString = null;
        }
        user.setInsuranceCompanyImageUrl(insuranceString);
        dataViewModel.editUserData(user, this).observe(this, new Observer<UserDataToUpload>() {
            @Override
            public void onChanged(UserDataToUpload userDataToUpload) {
                Toast.makeText(MyAccount.this, MyAccount.this.getResources().getString(R.string.done), Toast.LENGTH_SHORT).show();
                MyAccount.this.finish();
            }
        });
    }

    private void setUpSpinnerData() {
        dataViewModel.getMyInsuranceList(this).observe(this, new Observer<List<Insurance>>() {
            @Override
            public void onChanged(List<Insurance> insurances) {

                List<String> strings = new ArrayList<>();
                strings.add(MyAccount.this.getResources().getString(R.string.select_insurance));
                if (insurances.size() > 0) {
                    for (Insurance insurance : insurances) {
                        strings.add(insurance.getName());
                    }
                    Utils.setupSpinner(MyAccount.this, strings, insuranceSpinner);
                }
            }
        });
    }

    private void setupData(int id) {
        dataViewModel.getUserAccountData(id, this).observe(this, new Observer<UserData>() {
            @Override
            public void onChanged(UserData userData) {

                if (userData != null) {

                    mUserData = userData;
                    proPicUrl = userData.getImageUri();
                    oldUri = proPicUrl;
                    Uri uri;
                    if (proPicUrl != null && !proPicUrl.isEmpty()) {
                        uri = Uri.parse(proPicUrl.replace("//U", "/U"));
                    } else {
                        uri = Uri.parse("android.resource://com.yackeenSolution.mydocapp/drawable/doctor_default");
                    }
                    Picasso.get().load(uri).into(profilePic);

                    newFirstName.setText(userData.getFirstName());
                    firstName.setText(userData.getFirstName());
                    newLastName.setText(userData.getLastName());
                    lastName.setText(userData.getLastName());
                    String defaultDateFormat = userData.getBirthDate();
                    newDate.setText(Utils.dateAppFormat(defaultDateFormat));
                    String gender = userData.getGender();
                    switch (gender) {
                        case "true":
                            genderSpinner.setSelection(1);
                            break;
                        case "false":
                            genderSpinner.setSelection(2);
                            break;
                        default:
                            genderSpinner.setSelection(0);
                            break;
                    }
                    newMobile.setText(userData.getMobileNumber());
                    email.setText(userData.getEmail());
                    insuranceSpinner.setSelection(userData.getInsuranceId());
                    saveChanges.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
                    progress.setVisibility(View.GONE);
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

        DatePickerDialog.OnDateSetListener mPicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String myFormat = "dd/MM/YYYY"; //In which you need put here
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());

                myCalendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                myCalendar.set(Calendar.YEAR, datePicker.getYear());
                myCalendar.set(Calendar.MONTH, datePicker.getMonth());

                newDate.setText(format.format(myCalendar.getTime()));
                newDate.setTextColor(getResources().getColor(R.color.colorGray));
            }
        };
        // start picker with pre-chosen date
        DatePickerDialog dialog = new DatePickerDialog(MyAccount.this,
                R.style.Date_Picker,
                mPicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMaxDate(myCalendar.getTimeInMillis());

        Objects.requireNonNull(dialog.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void openGallery(int id) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), id);
    }

    private void openCamera() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.d(TAG, "onClick: " + ex.getMessage());
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                        "com.yackeenSolution.mydocapp.provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent, PICK_IMAGE_FROM_CAMERA);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir);

        path = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        Uri mImageUri;
        if (requestCode == PICK_IMAGE_FROM_GALLERY && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                mImageUri = resultData.getData();
                profilePic.setImageURI(mImageUri);
                path = ImageFilePath.getPath(this, mImageUri);
            }

        } else if (requestCode == PICK_IMAGE_FROM_CAMERA && resultCode == Activity.RESULT_OK) {

            Glide.with(this).load(path).into(profilePic);

        } else if (requestCode == PICK_IMAGE_FROM_GALLERY_FOR_INSURANCE && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                mInsuranceImageUri = resultData.getData();
            }
        } else {
            Toast.makeText(this, "unsupported request", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onButtonClicked(int request) {
        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            if (request == PICK_IMAGE_FROM_GALLERY) {
                openGallery(PICK_IMAGE_FROM_GALLERY);
            } else if (request == PICK_IMAGE_FROM_CAMERA) {
                if (checkPermissionWrite_EXTERNAL_STORAGE(this)) {
                    openCamera();
                }
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

    public void imageUrlToUpload() {
        progress.setVisibility(View.VISIBLE);
        if (!proPicUrl.equals(oldUri)) {
            File file = new File(path);

            final RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("InternTest", file.getName(), requestBody);

            RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");

            dataViewModel.uploadedImageUrlString(fileToUpload, description, this).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    oldUri = "::";
                    updateProfile(s);
                }
            });
        } else {
            updateProfile(oldUri);
        }
    }

}