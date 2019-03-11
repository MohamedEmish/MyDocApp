package com.yackeenSolution.mydocapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;


public class Utils {


    private static int STORAGE_READ_PERMISSION = 1;
    private static int STORAGE_WRITE_PERMISSION = 2;

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

    public static boolean isOldUser(EditText mail) {
        // TODO : mail existence check ((API))

        // TODO : true to test after test set false
        return true;
    }

    public static boolean isValueSet(EditText text, String error) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError(error);
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }

    static String userPassword(EditText mail) {
        String password = "";
        String email;
        email = mail.getText().toString().trim();
        getPassWord();
        return password;
    }

    private static void getPassWord() {
        // TODO : get user password ((APT))
    }

    public static boolean hasReadPermission(Context context, Activity activity) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestReadStoragePermission(context, activity);
        }
        return false;
    }

    private static void requestReadStoragePermission(Context context, final Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(context)
                    .setTitle("Read permission needed")
                    .setMessage("Needed to get your image")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_READ_PERMISSION);

                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_READ_PERMISSION);
        }
    }

    public static boolean hasWritePermission(Context context, Activity activity) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestWriteStoragePermission(context, activity);
        }
        return false;
    }

    private static void requestWriteStoragePermission(Context context, final Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(context)
                    .setTitle("Write permission needed")
                    .setMessage("Needed to save your image")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_WRITE_PERMISSION);

                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_WRITE_PERMISSION);
        }
    }


    public static void setLocale(Context mContext) {
        String language = SaveSharedPreference.getLanguage(mContext);
        Locale locale = new Locale(language);
        Configuration config = mContext.getApplicationContext().getResources().getConfiguration();
        config.locale = locale;
        mContext.getApplicationContext().getResources().updateConfiguration(config, mContext.getApplicationContext().getResources().getDisplayMetrics());
    }

    public static void RTLSupport(Context mContext, View view) {
        if (SaveSharedPreference.getLanguage(mContext).equals("ar")) {
            ViewCompat.setLayoutDirection(view, ViewCompat.LAYOUT_DIRECTION_RTL);
        } else if (SaveSharedPreference.getLanguage(mContext).equals("en")) {
            ViewCompat.setLayoutDirection(view, ViewCompat.LAYOUT_DIRECTION_LTR);
        }
    }
}
