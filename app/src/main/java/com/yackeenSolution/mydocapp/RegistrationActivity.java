package com.yackeenSolution.mydocapp;
 /*
   Last edit :: March 8,2019
   ALL DONE :)
 */
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
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
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.Objects.UserData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity implements BottomSheet.BottomSheetListener {

    public static final int PICK_IMAGE_FROM_GALLERY = 100;
    public static final int PICK_IMAGE_FROM_CAMERA = 200;

    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener mPicker;

    int eyeVisibility = 1;
    private ImageView mSignUpEye;

    private CircleImageView mSignUpUserImage;
    private Uri mImageUri;

    private String
            mEmail,
            mFirstName,
            mLastName,
            mMobile,
            mPassword,
            mDate,
            mImageString,
            mGender;

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

    private Button mSignUpCreateAccount;

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

        mSignUpEmail = findViewById(R.id.sign_up_email);
        Intent intent = getIntent();
        if (intent.hasExtra("mail")) {
            mEmail = intent.getStringExtra("mail");
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

        mSignUpCreateAccount = findViewById(R.id.sign_up_create_account);

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
                if (!isAllDataOk()) {
                    Toast.makeText(RegistrationActivity.this, "Check Errors", Toast.LENGTH_SHORT).show();
                } else {
                    createNewAccount();
                }
            }
        });
    }

    private void createNewAccount() {

        mMobile = mSignUpMobile.getText().toString().trim();
        mPassword = mSignUpPassword.getText().toString().trim();
        mDate = mSignUpDate.getText().toString().trim();
        mImageString = mImageUri.toString();
        mFirstName = mSignUpFirstName.getText().toString().trim();
        mLastName = mSignUpLastName.getText().toString().trim();
        if (mSignUpGenderRadioGroup.getCheckedRadioButtonId() == R.id.sign_up_male_radio) {
            mGender = "male";
        } else if (mSignUpGenderRadioGroup.getCheckedRadioButtonId() == R.id.sign_up_female_radio) {
            mGender = "female";
        } else {
            mGender = "";
        }

        if (mSignUpMaleRadio.isChecked()) {
            mGender = "male";
        }

        if (mSignUpFemaleRadio.isChecked()) {
            mGender = "female";
        }

        UserData user = new UserData(
                mFirstName,
                mLastName,
                mEmail,
                mDate,
                mMobile,
                mGender,
                mPassword,
                mImageString
        );
        // TODO : upload user to ((API))
        Intent intent = new Intent(RegistrationActivity.this, SignInActivity.class);
        intent.putExtra("emailText", mEmail);
        intent.putExtra("password", mPassword);
        startActivity(intent);
        Toast.makeText(this, "created", Toast.LENGTH_SHORT).show();
    }

    private void pickDate() {

        myCalendar = Calendar.getInstance();

        mPicker = new DatePickerDialog.OnDateSetListener() {
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
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
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
                mSignUpUserImage.setImageURI(mImageUri);
            }

        } else if (requestCode == PICK_IMAGE_FROM_CAMERA && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {

                Uri selectedImage = resultData.getData();
                Bitmap bitmap = (Bitmap) resultData.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                File destination = new File(Environment.getExternalStorageDirectory() + "/" +
                        getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String imgPath = destination.getAbsolutePath();
                mImageUri = Uri.parse(imgPath);
                mSignUpUserImage.setImageBitmap(bitmap);
            }

        } else {
            Toast.makeText(this, "Request cancelled", Toast.LENGTH_SHORT).show();
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

    private boolean isAllDataOk() {

        boolean isAllOk = true;

        // Check first name entry
        if (!Utils.isValueSet(mSignUpFirstName, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        }

        // Check last name entry
        if (!Utils.isValueSet(mSignUpLastName, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        }

        // Check emailText entry , validity and existence
        if (!Utils.isValueSet(mSignUpEmail, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!Utils.isValidEmail(mSignUpEmail, getResources().getString(R.string.invalid_mail_error))) {
            isAllOk = false;
        } else if (Utils.isOldUser(mSignUpEmail)) {
            mSignUpEmail.setError(getResources().getString(R.string.user_exist_error));
            isAllOk = false;
        }

        // Check password entry and validity
        if (!Utils.isValueSet(mSignUpPassword, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!Utils.isValidPassword(mSignUpPassword, getResources().getString(R.string.invalid_password_error))) {
            isAllOk = false;
        }

        // Check mobile number entry and validity
        if (!Utils.isValueSet(mSignUpMobile, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!Utils.isValidNumber(mSignUpMobile, getResources().getString(R.string.invalid_number_error))) {
            isAllOk = false;
        }

        // Check terms acceptance
        if (!mSignUpTermsCheck.isChecked()) {
            checkText.setError(getResources().getString(R.string.You_must_accept));
            checkText.setTextColor(getResources().getColor(R.color.colorError));
            return false;
        }
        return isAllOk;
    }

}

