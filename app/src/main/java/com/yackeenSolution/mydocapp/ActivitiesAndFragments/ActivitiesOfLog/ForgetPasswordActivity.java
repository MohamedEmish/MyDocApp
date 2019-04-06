package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfLog;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.PasswordToken;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText
            mail,
            code,
            newPass,
            confirmPass;
    private Button confirmCode,
            resetPass;
    private String
            emailText,
            codeText,
            passText,
            newPassText;
    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_forget_password);
        LinearLayout linearLayout = findViewById(R.id.forget_root);
        Utils.RTLSupport(this, linearLayout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ImageView back = findViewById(R.id.forget_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        mail = findViewById(R.id.forget_pass_email);
        emailText = mail.getText().toString().trim();

        code = findViewById(R.id.forget_pass_code);
        code.setEnabled(false);
        codeText = code.getText().toString().trim();

        newPass = findViewById(R.id.forget_new_pass);
        newPass.setEnabled(false);
        passText = newPass.getText().toString().trim();

        confirmPass = findViewById(R.id.forget_new_pass_confirm);
        confirmPass.setEnabled(false);
        newPassText = confirmPass.getText().toString().trim();

        confirmCode = findViewById(R.id.forget_confirm_button);
        resetPass = findViewById(R.id.forget_reset_button);

        confirmCode.setEnabled(false);
        resetPass.setEnabled(false);

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewPass(emailText, codeText, newPassText);
            }
        });

        Button forgetButton = findViewById(R.id.forget_button);
        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isValidEmail(mail, getResources().getString(R.string.invalid_mail_error))) {
                    getNewPass(emailText);
                }
            }
        });

        confirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNewPass(emailText, codeText);
            }
        });
    }

    private void setNewPass(final String emailText, String codeText, final String newPassText) {
        if (newPassText.equals(passText)) {
            PasswordToken token = new PasswordToken();
            token.setEmail(emailText);
            token.setCode(codeText);
            token.setNewPassword(newPassText);
            dataViewModel.resetPassword(token, this).observe(this, new Observer<PasswordToken>() {
                @Override
                public void onChanged(PasswordToken token) {
                    Intent intent = new Intent(ForgetPasswordActivity.this, SignInActivity.class);
                    intent.putExtra("emailText", emailText);
                    intent.putExtra("password", newPassText);
                    startActivity(intent);
                }
            });
        } else {
            Utils.isValueSet(newPass, getResources().getString(R.string.passwords_no_match));
        }

    }

    private void sendNewPass(String email, String codeText) {
        PasswordToken token = new PasswordToken();
        token.setEmail(email);
        token.setCode(codeText);
        dataViewModel.resetPassword(token, this).observe(this, new Observer<PasswordToken>() {
            @Override
            public void onChanged(PasswordToken token) {
                activateResetPass();
            }
        });
    }

    private void activateResetPass() {
        newPass.setEnabled(true);
        confirmPass.setEnabled(true);
        resetPass.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        resetPass.setEnabled(true);
    }

    private void activateConfirmCode() {
        code.setEnabled(true);
        confirmCode.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        confirmCode.setEnabled(true);
    }

    private void getNewPass(String email) {
        PasswordToken token = new PasswordToken();
        token.setEmail(email);
        dataViewModel.forgetPassword(token, this).observe(this, new Observer<PasswordToken>() {
            @Override
            public void onChanged(PasswordToken token) {
                activateConfirmCode();
            }
        });
    }
}
