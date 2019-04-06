package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog;
 /*
   Last edit :: March 26,2019
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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.UserDataToUpload;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.BottomSheet;
import com.yackeenSolution.mydocapp.Utils.ImageFilePath;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegistrationActivity extends AppCompatActivity implements BottomSheet.BottomSheetListener {

    public static final int PICK_IMAGE_FROM_GALLERY = 100;
    public static final int PICK_IMAGE_FROM_CAMERA = 200;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 321;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private static final String TAG = RegistrationActivity.class.getCanonicalName();

    private Calendar myCalendar;

    int eyeVisibility = 1;
    private ImageView mSignUpEye;
    private DataViewModel dataViewModel;
    private LinearLayout progress, data;

    private CircleImageView mSignUpUserImage;
    private String path;
    private EditText
            mSignUpFirstName,
            mSignUpLastName,
            mSignUpEmail,
            mSignUpPassword,
            mSignUpDate,
            mSignUpMobile;

    private TextView checkText;
    private RadioGroup mSignUpGenderRadioGroup;
    private AppCompatRadioButton mSignUpMaleRadio, mSignUpFemaleRadio;
    private AppCompatCheckBox mSignUpTermsCheck;
    private String fbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_registration);
        ScrollView scrollView = findViewById(R.id.sign_up_root);
        Utils.RTLSupport(this, scrollView);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.reg_action_bar);
        }

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        mSignUpEmail = findViewById(R.id.sign_up_email);
        progress = findViewById(R.id.register_progress_bar_layout);
        data = findViewById(R.id.register_data);

        Intent intent = getIntent();
        if (intent.hasExtra("mail")) {
            String mEmail = intent.getStringExtra("mail");
            fbId = intent.getStringExtra("fbId");
            mSignUpEmail.setText(mEmail);
        }

        checkText = findViewById(R.id.sign_up_terms_text);

        mSignUpGenderRadioGroup = findViewById(R.id.sign_up_gender_radio_group);
        mSignUpMaleRadio = findViewById(R.id.sign_up_male_radio);
        mSignUpFemaleRadio = findViewById(R.id.sign_up_female_radio);

        mSignUpFirstName = findViewById(R.id.sign_up_first_name);
        mSignUpLastName = findViewById(R.id.sign_up_last_name);

        mSignUpTermsCheck = findViewById(R.id.sign_up_terms_check);
        mSignUpTermsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkText.setTextColor(getResources().getColor(R.color.colorGray));
                }
            }
        });

        mSignUpMobile = findViewById(R.id.sign_up_mobile);
        mSignUpPassword = findViewById(R.id.sign_up_password);

        mSignUpDate = findViewById(R.id.sign_up_date);
        mSignUpUserImage = findViewById(R.id.sign_up_user_image);

        Button mSignUpCreateAccount = findViewById(R.id.sign_up_create_account);

        mSignUpEye = findViewById(R.id.sign_up_eye);
        mSignUpEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eyeAction();
            }
        });

        mSignUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                pickDate();
            }
        });

        mSignUpUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.hasReadPermission(RegistrationActivity.this, RegistrationActivity.this) &&
                        Utils.hasWritePermission(RegistrationActivity.this, RegistrationActivity.this)) {
                    BottomSheet bottomSheet = new BottomSheet();
                    bottomSheet.show(getSupportFragmentManager(), "Example");
                }
            }
        });

        mSignUpCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllDataOk();
            }
        });
    }

    public void imageUrlToUpload() {
        if (path != null && !path.isEmpty()) {
            progress.setVisibility(View.VISIBLE);
            data.setVisibility(View.GONE);
            File file = new File(path);

            final RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("InternTest", file.getName(), requestBody);

            RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");

            dataViewModel.uploadedImageUrlString(fileToUpload, description, this).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    createNewAccount(s);
                }
            });
        } else {
            createNewAccount(null);
        }
    }


    private void createNewAccount(String image) {
        UserDataToUpload user = new UserDataToUpload();
        user.setPhoneNumber(mSignUpMobile.getText().toString().trim());
        user.setPassword(mSignUpPassword.getText().toString().trim());
        user.setEmail(mSignUpEmail.getText().toString().trim());
        user.setDateOfBirth(Utils.dateToApiFormat(mSignUpDate.getText().toString().trim()));
        user.setFirstName(mSignUpFirstName.getText().toString().trim());
        user.setLastName(mSignUpLastName.getText().toString().trim());

        if (image != null && !image.isEmpty()) {
            image = image.replace("http://yakensolution.cloudapp.net/doctoryadmin/", "")
                    .replace("http://yakensolution.cloudapp.net/doctoryadmin//", "")
                    .replace("\"", "");
            user.setImageUri(image);
        } else {
            user.setImageUri(null);
        }

        Boolean mGender;
        if (mSignUpGenderRadioGroup.getCheckedRadioButtonId() == R.id.sign_up_male_radio) {
            mGender = true;
        } else if (mSignUpGenderRadioGroup.getCheckedRadioButtonId() == R.id.sign_up_female_radio) {
            mGender = false;
        } else {
            mGender = null;
        }

        if (mSignUpMaleRadio.isChecked()) {
            mGender = true;
        }
        if (mSignUpFemaleRadio.isChecked()) {
            mGender = false;
        }
        user.setGender(mGender);

        if (fbId != null && !fbId.isEmpty()) {
            user.setFbId(fbId);
        } else {
            user.setFbId(null);
        }

        user.setLang(null);
        user.setAddress(null);
        user.setAppointmentReminder(true);
        user.setDoctorId(null);
        user.setInsuranceCompanyId(null);
        user.setInsuranceCompanyImageUrl(null);
        user.setEnableNotification(true);

        dataViewModel.addNewUser(user, this).observe(this, new Observer<UserDataToUpload>() {
            @Override
            public void onChanged(UserDataToUpload userDataToUpload) {
                Intent intent = new Intent(RegistrationActivity.this, SignInActivity.class);
                intent.putExtra("emailText", mSignUpEmail.getText().toString().trim());
                intent.putExtra("password", mSignUpPassword.getText().toString().trim());
                startActivity(intent);
                Toast.makeText(RegistrationActivity.this, getResources().getString(R.string.welcome), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void pickDate() {

        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener mPicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String myFormat = "dd/MM/YYYY";
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());

                myCalendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                myCalendar.set(Calendar.YEAR, datePicker.getYear());
                myCalendar.set(Calendar.MONTH, datePicker.getMonth());

                mSignUpDate.setText(format.format(myCalendar.getTime()));
            }
        };
        // start picker with pre-chosen date
        DatePickerDialog dialog = new DatePickerDialog(RegistrationActivity.this,
                R.style.Date_Picker,
                mPicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMaxDate(myCalendar.getTimeInMillis());

        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));

    }

    private void eyeAction() {
        if (eyeVisibility == 1) {
            eyeVisibility = 0;
            mSignUpPassword.setTransformationMethod(null);
            mSignUpEye.setImageDrawable(getResources().getDrawable(R.drawable.eye_visible));
        } else {
            eyeVisibility = 1;
            mSignUpPassword.setTransformationMethod(new PasswordTransformationMethod());
            mSignUpEye.setImageDrawable(getResources().getDrawable(R.drawable.eye_invisible));
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_FROM_GALLERY);
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
                mSignUpUserImage.setImageURI(mImageUri);
                path = ImageFilePath.getPath(this, mImageUri);
            }

        } else if (requestCode == PICK_IMAGE_FROM_CAMERA && resultCode == Activity.RESULT_OK) {

            Glide.with(this).load(path).into(mSignUpUserImage);

        } else {
            Toast.makeText(this, "Request cancelled", Toast.LENGTH_SHORT).show();
        }
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

    private void isAllDataOk() {

        // Check first name entry
        if (!Utils.isValueSet(mSignUpFirstName, getResources().getString(R.string.edit_text_error))) {
            return;
        }

        // Check last name entry
        if (!Utils.isValueSet(mSignUpLastName, getResources().getString(R.string.edit_text_error))) {
            return;
        }

        // Check date entry
        if (!Utils.isValueSet(mSignUpDate, getResources().getString(R.string.edit_text_error))) {
            return;
        }

        // Check gender selection
        if (!mSignUpMaleRadio.isChecked() && !mSignUpFemaleRadio.isChecked()) {
            Toast.makeText(this, getResources().getString(R.string.gender_is_must), Toast.LENGTH_SHORT).show();
            return;
        }

        // Check mobile number entry and validity
        if (!Utils.isValueSet(mSignUpMobile, getResources().getString(R.string.edit_text_error))) {
            return;
        } else if (Utils.isValidNumber(mSignUpMobile, getResources().getString(R.string.invalid_number_error))) {
            return;
        }

        // Check emailText entry , validity and existence
        if (!Utils.isValueSet(mSignUpEmail, getResources().getString(R.string.edit_text_error))) {
            return;
        } else if (!Utils.isValidEmail(mSignUpEmail, getResources().getString(R.string.invalid_mail_error))) {
            return;
        }

        // Check password entry and validity
        if (!Utils.isValueSet(mSignUpPassword, getResources().getString(R.string.edit_text_error))) {
            return;
        } else if (Utils.isValidPassword(mSignUpPassword, getResources().getString(R.string.invalid_password_error))) {
            return;
        }

        // Check terms acceptance
        if (!mSignUpTermsCheck.isChecked()) {
            checkText.setError(getResources().getString(R.string.You_must_accept));
            checkText.setTextColor(getResources().getColor(R.color.colorError));
            return;
        }
        imageUrlToUpload();
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat.requestPermissions(
                            (Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
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
                        (Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat.requestPermissions(
                            (Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
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
                        ActivityCompat.requestPermissions((Activity) context, new String[]{permission},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
}