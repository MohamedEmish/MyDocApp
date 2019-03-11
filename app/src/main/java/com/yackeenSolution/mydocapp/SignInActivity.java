package com.yackeenSolution.mydocapp;
 /*
   Last edit :: March 7,2019
   ALL DONE :)
 */

import android.content.Intent;
import android.content.res.Configuration;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

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
                goForMainScreen();
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
                forgetPassword();
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
                goForRegister();
            }
        });

        signIn = findViewById(R.id.sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn(mEmailEditText, mPasswordEditText);
            }
        });
    }

    private void logIn(EditText email, EditText password) {
        boolean isAllOk = true;

        // Email set,validation and existence check

        if (!Utils.isValueSet(email, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!Utils.isValidEmail(email, getResources().getString(R.string.invalid_mail_error))) {
            isAllOk = false;
        } else if (!Utils.isOldUser(email)) {
            email.setError(getResources().getString(R.string.user_not_exist_error));
            isAllOk = false;
        }

        // Password set,validation and correctness check

        if (!Utils.isValueSet(password, getResources().getString(R.string.edit_text_error))) {
            isAllOk = false;
        } else if (!Utils.isValidPassword(password, getResources().getString(R.string.invalid_password_error))) {
            isAllOk = false;
        } else if (Utils.isOldUser(email)) {
            String pass = Utils.userPassword(email);
            if (!pass.equals(password.getText().toString().trim())) {
                // TODO : true to test after test set false
                password.setError(getResources().getString(R.string.incorrect_password));
                isAllOk = true;
            }
        }

        if (isAllOk) {
            Intent intent = new Intent(SignInActivity.this, MainScreen.class);
            SaveSharedPreference.setUserEmail(this, email.getText().toString().trim());
            startActivity(intent);
        }

    }

    private void goForMainScreen() {
        Intent intent = new Intent(SignInActivity.this, MainScreen.class);
        startActivity(intent);
    }

    private void goForRegister(String email) {
        Intent intent = new Intent(SignInActivity.this, RegistrationActivity.class);
        intent.putExtra("mail", email);
        startActivity(intent);
    }

    private void forgetPassword() {
        Intent intent = new Intent(SignInActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    private void goForRegister() {
        Intent intent = new Intent(SignInActivity.this, RegistrationActivity.class);
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