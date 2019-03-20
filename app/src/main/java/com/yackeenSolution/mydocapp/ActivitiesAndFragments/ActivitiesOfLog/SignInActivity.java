package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog;
 /*
   Last edit :: March 7,2019
   ALL DONE :)
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.UserData;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SignInActivity extends AppCompatActivity {

    private static final String EMAIL = "emailText";
    private static final String PROFILE = "public_profile";

    ImageView eye;
    int eyeVisibility = 1;
    EditText mEmailEditText, mPasswordEditText;
    TextView createNew, forgetPassword, skip;
    CallbackManager callbackManager;
    LoginButton fbLogin;
    Button myFBLogin;
    DataViewModel dataViewModel;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        // animation
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_sign_in);
        ScrollView scrollView = findViewById(R.id.sign_in_root);
        Utils.RTLSupport(this, scrollView);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactions(MainScreen.class);
            }
        });

        mEmailEditText = findViewById(R.id.sign_in_email);
        mPasswordEditText = findViewById(R.id.sign_in_password);

        Intent intent = getIntent();
        if (intent.hasExtra("emailText")) {
            String mEmail = intent.getStringExtra("mail");
            mEmailEditText.setText(mEmail);
        }
        if (intent.hasExtra("password")) {
            String pass = intent.getStringExtra("password");
            mPasswordEditText.setText(pass);
        }

        eye = findViewById(R.id.sign_in_eye);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eyeAction();
            }
        });

        forgetPassword = findViewById(R.id.forgot_password);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactions(ForgetPasswordActivity.class);
            }
        });

        myFBLogin = findViewById(R.id.my_fb_login);
        myFBLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbLogin.performClick();
            }
        });

        fbLogin = findViewById(R.id.facebook_main_button);
        fbLogin.setReadPermissions(Arrays.asList(EMAIL, PROFILE));
        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String email = object.getString("emailText");
                                    goForRegister(email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,emailText");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(SignInActivity.this, "FaceBook Login cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(SignInActivity.this, "ops !! .. some error happened", Toast.LENGTH_SHORT).show();
            }
        });

        createNew = findViewById(R.id.create_new_account);
        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactions(RegistrationActivity.class);
            }
        });

        signIn = findViewById(R.id.sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(mEmailEditText, mPasswordEditText);
            }
        });
    }

    private void logIn(EditText email, EditText password) {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Email", email.getText().toString().trim());
        fields.put("Password", password.getText().toString().trim());
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setupData(fields);
    }

    private void setupData(HashMap<String, String> fields) {
        dataViewModel.userLogin(fields).observe(this, new Observer<UserData>() {
            @Override
            public void onChanged(UserData userData) {

                if (userData != null) {
                    SaveSharedPreference.setUserId(SignInActivity.this, String.valueOf(userData.getId()));
                    SaveSharedPreference.setUserEmail(SignInActivity.this, String.valueOf(userData.getEmail()));
                    SaveSharedPreference.setUserName(SignInActivity.this, String.valueOf(userData.getFirstName()));

                    Transactions(MainScreen.class);
                } else {
                    Toast.makeText(SignInActivity.this, "Email or Password Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // TODO :: after wrong entry it doesn't clear and get new data .. so how to clear ?? ((ALAA))
    }

    private void check(EditText email, EditText password) {
        boolean isAllOk = true;

        // TODO :: how to check existence ((ALAA))
        // Email set and validation check

        if (!Utils.isValueSet(email, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!Utils.isValidEmail(email, getResources().getString(R.string.invalid_mail_error))) {
            isAllOk = false;
        }
//        else if (!Utils.isOldUser(email)) {
//            email.setError(getResources().getString(R.string.user_not_exist_error));
//            isAllOk = false;
//        }

        // Password set,validation and correctness check

        if (!Utils.isValueSet(password, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!Utils.isValidPassword(password, getResources().getString(R.string.invalid_password_error))) {
            isAllOk = false;
        }
//        else if (Utils.isOldUser(email)) {
//            String pass = Utils.userPassword(email);
//            if (!pass.equals(password.getText().toString().trim())) {
//                // TODO : true to test after test set false
//                password.setError(getResources().getString(R.string.incorrect_password));
//                isAllOk = true;
//            }
//        }

        if (isAllOk) {
            logIn(email, password);
        }

    }

    private void Transactions(Class c) {
        Intent intent = new Intent(SignInActivity.this, c);
        startActivity(intent);
    }

    private void goForRegister(String email) {
        Intent intent = new Intent(SignInActivity.this, RegistrationActivity.class);
        intent.putExtra("mail", email);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        callbackManager.onActivityResult(requestCode, responseCode, data);
        super.onActivityResult(requestCode, responseCode, data);
    }

    private void eyeAction() {
        if (eyeVisibility == 1) {
            eyeVisibility = 0;
            mPasswordEditText.setTransformationMethod(null);
            eye.setImageDrawable(getResources().getDrawable(R.drawable.eye_visible));
        } else {
            eyeVisibility = 1;
            mPasswordEditText.setTransformationMethod(new PasswordTransformationMethod());
            eye.setImageDrawable(getResources().getDrawable(R.drawable.eye_invisible));
        }
    }
}