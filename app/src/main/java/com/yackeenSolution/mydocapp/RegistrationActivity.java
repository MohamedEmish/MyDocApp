package com.yackeenSolution.mydocapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity implements BottomSheet.BottomSheetListener {

    public static final int PICK_IMAGE_FROM_GALLERY = 100;
    public static final int PICK_IMAGE_FROM_CAMERA = 200;
    Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener mPicker;
    int eyeVisibility = 1;
    Uri mImageUri;
    String mEmail, mFirstName, mLastName, mMobile, mPassword;
    TextView checkText;
    private AppCompatRadioButton mSignUpMaleRadio;
    private AppCompatRadioButton mSignUpFemaleRadio;
    private EditText mSignUpFirstName;
    private EditText mSignUpLastName;
    private AppCompatCheckBox mSignUpTermsCheck;
    private EditText mSignUpMobile;
    private Button mSignUpCreateAccount;
    private EditText mSignUpEmail;
    private EditText mSignUpPassword;
    private EditText mSignUpDate;
    private CircleImageView mSignUpUserImage;
    private RadioGroup mSignUpGenderRadioGroup;
    private ImageView mSignUpEye;

    public static boolean isValidNumber(EditText text, String error) {
        if (!TextUtils.isEmpty(text.getText()) &&
                Patterns.PHONE.matcher(text.getText()).matches()) {
            text.setError(null);
            return true;
        } else {
            text.setError(error);
            return false;
        }
    }

    public static boolean isValidPassword(EditText text, String error) {
        if (!TextUtils.isEmpty(text.getText()) &&
                text.getText().length() > 6) {
            text.setError(null);
            return true;
        } else {
            text.setError(error);
            return false;
        }
    }

    public static boolean isValidEmail(EditText text, String description) {
        if (!TextUtils.isEmpty(text.getText()) &&
                Patterns.EMAIL_ADDRESS.matcher(text.getText()).matches()) {
            text.setError(null);
            return true;
        } else {
            text.setError("Please Enter " + description);
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.reg_action_bar);


        Intent intent = getIntent();
        mEmail = intent.getStringExtra("mail");
        mSignUpEmail = findViewById(R.id.sign_up_email);
        if (mEmail != null) {
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

                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "Example");
            }
        });

        mSignUpCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addNewUser()) {
                    Toast.makeText(RegistrationActivity.this, "Check Errors", Toast.LENGTH_SHORT).show();
                } else {
                    createNewAccount();
                }
            }
        });
    }

    private void createNewAccount() {
        Toast.makeText(this, "created", Toast.LENGTH_SHORT).show();
    }

    private void pickDate() {

        myCalendar = Calendar.getInstance();

        mPicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String myFormat = "DD/MM/YYYY"; //In which you need put here
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());

                mSignUpDate.setText(format.format(myCalendar.getTime()));
            }
        };
        // start picker with pre-chosen date
        DatePickerDialog dialog = new DatePickerDialog(RegistrationActivity.this,
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
                Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                mSignUpUserImage.setImageBitmap(photo);
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

    private boolean checkIfValueSet(EditText text, String error) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError(error);
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }

    private boolean addNewUser() {

        boolean isAllOk = true;

        // Check first name entry
        if (!checkIfValueSet(mSignUpFirstName, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        }

        // Check last name entry
        if (!checkIfValueSet(mSignUpLastName, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        }

        // Check email entry , validity and existence
        if (!checkIfValueSet(mSignUpEmail, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!isValidEmail(mSignUpEmail, getResources().getString(R.string.invalid_mail_error))) {
            isAllOk = false;
        } else if (isOldUser(mSignUpEmail.getText().toString().trim())) {
            mSignUpEmail.setError(getResources().getString(R.string.user_exist_error));
            isAllOk = false;
        }

        // Check password entry and validity
        if (!checkIfValueSet(mSignUpPassword, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!isValidPassword(mSignUpPassword, getResources().getString(R.string.invalid_password_error))) {
            isAllOk = false;
        }

        // Check mobile number entry and validity
        if (!checkIfValueSet(mSignUpMobile, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!isValidNumber(mSignUpMobile, getResources().getString(R.string.invalid_number_error))) {
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

    private boolean isOldUser(String mail) {
        // TODO : mail exitence check
        return false;
    }
}

