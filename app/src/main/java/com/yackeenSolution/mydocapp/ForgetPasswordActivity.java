package com.yackeenSolution.mydocapp;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Locale;

public class ForgetPasswordActivity extends AppCompatActivity {

    ImageView back;
    EditText mail, code, newPass, confirmPass;
    Button forgetButton, confirmCode, resetPass;
    String emailText, codeText, passText, newPassText;

    private Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        return context.createConfigurationContext(config);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            super.attachBaseContext(updateResources(newBase, PreferenceManager.getDefaultSharedPreferences(newBase).getString("lang", "en")));
        } else {
            super.attachBaseContext(newBase);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateResources(this, SaveSharedPreference.getLanguage(this));
        setContentView(R.layout.activity_forget_password);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        back = findViewById(R.id.forget_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mail = findViewById(R.id.forget_pass_email);
        emailText = mail.getText().toString().trim();

        code = findViewById(R.id.forget_pass_code);
        codeText = code.getText().toString().trim();

        newPass = findViewById(R.id.forget_new_pass);
        passText = newPass.getText().toString().trim();

        confirmPass = findViewById(R.id.forget_new_pass_confirm);
        newPassText = confirmPass.getText().toString().trim();

        confirmCode = findViewById(R.id.forget_confirm_button);
        resetPass = findViewById(R.id.forget_reset_button);

        forgetButton = findViewById(R.id.forget_button);
        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isValidEmail(mail, getResources().getString(R.string.invalid_mail_error))) {
                    getNewPass(emailText);
                    activateConfirmCode();
                }
            }
        });

        confirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : check code with ((API))
                activateResetPass();
                sendNewPass(emailText);

            }
        });
    }

    private void sendNewPass(String email) {
        // TODO : set new pass to ((API)
    }

    private void activateResetPass() {
        newPass.setFocusable(true);
        confirmPass.setFocusable(true);
        resetPass.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
    }

    private void activateConfirmCode() {
        code.setFocusable(true);
        confirmCode.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
    }

    private void getNewPass(String email) {
        // TODO : send mail to ((API)) to solve the problem
    }
}
