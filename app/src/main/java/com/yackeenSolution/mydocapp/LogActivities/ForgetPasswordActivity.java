package com.yackeenSolution.mydocapp.LogActivities;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

public class ForgetPasswordActivity extends AppCompatActivity {

    ImageView back;
    EditText mail, code, newPass, confirmPass;
    Button forgetButton, confirmCode, resetPass;
    String emailText, codeText, passText, newPassText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_forget_password);
        LinearLayout linearLayout = findViewById(R.id.forget_root);
        Utils.RTLSupport(this, linearLayout);

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
        // TODO : send mail to ((API)) to solve the problem
    }
}
