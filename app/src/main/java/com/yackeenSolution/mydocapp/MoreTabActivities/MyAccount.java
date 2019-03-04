package com.yackeenSolution.mydocapp.MoreTabActivities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.BottomSheet;
import com.yackeenSolution.mydocapp.MainScreen;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.RegistrationActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAccount extends AppCompatActivity implements BottomSheet.BottomSheetListener {

    public static final int PICK_IMAGE_FROM_GALLERY = 100;
    public static final int PICK_IMAGE_FROM_CAMERA = 200;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener mPicker;

    Spinner genderSpinner;
    Spinner insuranceSpinner;

    ImageView personalInfoExpand;
    ImageView insuranceInfoExpand;
    ImageView changePassExpand;

    int personalInfoIndicator = 0;
    int insuranceInfoIndicator = 0;
    int changePassIndicator = 0;

    LinearLayout personalInfoLayout;
    LinearLayout insuranceInfoLayout;
    LinearLayout changePassLayout;

    ConstraintLayout personalInfoMainLayout;
    ConstraintLayout insuranceInfoMainLayout;
    ConstraintLayout changePassMainLayout;

    CircleImageView profilePic;
    Uri mImageUri;
    ImageView back;

    EditText newPassword;
    Button changePass, saveChanges;

    EditText newFirstName, newLastName, newDate, newMobile, newNationalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        genderSpinner = findViewById(R.id.my_account_gender_spinner);
        setupGenderSpinner();

        insuranceSpinner = findViewById(R.id.my_account_insurance_spinner);
        setupInsuranceSpinner();

        back = findViewById(R.id.my_account_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccount.this, MainScreen.class);
                intent.putExtra("source", "more");
                startActivity(intent);
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

        personalInfoMainLayout = findViewById(R.id.my_account_personal_info_main_layout);
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

        insuranceInfoMainLayout = findViewById(R.id.my_account_insurance_main_layout);
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

        changePassMainLayout = findViewById(R.id.my_account_change_pass_main_layout);
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

        newNationalId = findViewById(R.id.my_account_national_id_edit_text);
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

                newDate.setText(format.format(myCalendar.getTime()));
            }
        };
        // start picker with pre-chosen date
        DatePickerDialog dialog = new DatePickerDialog(MyAccount.this,
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


    private void setupGenderSpinner() {

        ArrayAdapter notificationSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        notificationSpinnerAdapter.setDropDownViewResource(R.layout.my_spinner_layout);

        genderSpinner.setAdapter(notificationSpinnerAdapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Male")) {
                        // TODO: male choice
                    } else {
                        // TODO: male choice

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    private void setupInsuranceSpinner() {

        ArrayAdapter notificationSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_insurance_options, android.R.layout.simple_spinner_item);

        notificationSpinnerAdapter.setDropDownViewResource(R.layout.my_spinner_layout);

        insuranceSpinner.setAdapter(notificationSpinnerAdapter);

        insuranceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Select an insurance company")) {
                        // TODO: male choice
                    } else {
                        // TODO: male choice

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
                profilePic.setImageURI(mImageUri);
            }

        } else if (requestCode == PICK_IMAGE_FROM_CAMERA && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                Bitmap photo = (Bitmap) resultData.getExtras().get("data");
                profilePic.setImageBitmap(photo);
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
