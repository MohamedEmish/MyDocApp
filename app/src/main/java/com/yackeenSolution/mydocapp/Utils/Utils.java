package com.yackeenSolution.mydocapp.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults.GoogleMapsActivity;
import com.yackeenSolution.mydocapp.R;

import java.util.List;
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

    public static String dateAppFormat(String oldFormat) {

        // Dividing date string into date and time
        String[] onSplitTextPhaseOne = oldFormat.split("T");
        String date = onSplitTextPhaseOne[0];
        String[] onSplitTextPhaseTwo = date.split("-");
        String year = onSplitTextPhaseTwo[0];
        String month = onSplitTextPhaseTwo[1];
        String day = onSplitTextPhaseTwo[2];

        return day + "/" + month + "/" + year;
    }

    public static String dateToApiFormat(String oldFormat) {
        // Dividing date string into date and time
        String[] onSplitTextPhaseOne = oldFormat.split("/");
        String day = onSplitTextPhaseOne[0];
        String month = onSplitTextPhaseOne[1];
        String year = onSplitTextPhaseOne[2];

        return month + "-" + day + "-" + year;
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

    public static String userPassword(EditText mail) {
        String password = "";
        String email;
        email = mail.getText().toString().trim();
        getPassWord();
        return password;
    }

    public static void getPassWord() {
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

    public static void requestReadStoragePermission(Context context, final Activity activity) {
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

    public static void requestWriteStoragePermission(Context context, final Activity activity) {
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

    public static void setupSpinner(final Context context, List<String> array, Spinner spinner) {

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, array) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        spinnerAdapter.setDropDownViewResource(R.layout.my_spinner_layout);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(context.getResources().getColor(R.color.colorGray));
                ((TextView) view).setTextSize(context.getResources().getDimension(R.dimen.spinner_text_size));
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public static void setupSpinner(final Context context, String[] array, Spinner spinner) {

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, array) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        spinnerAdapter.setDropDownViewResource(R.layout.my_spinner_layout);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(context.getResources().getColor(R.color.colorGray));
                ((TextView) view).setTextSize(context.getResources().getDimension(R.dimen.spinner_text_size));
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public static void openBrowser(String webString, Context context) {
        if (webString != null && !webString.isEmpty()) {
            if (webString.contains("https://") || webString.contains("http://")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webString));
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + webString));
                context.startActivity(intent);
            }
        }
    }

    public static void performCall(String callNumber, Context context) {
        if (callNumber != null && !callNumber.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", callNumber, null));
            context.startActivity(intent);
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.no_phone_available), Toast.LENGTH_SHORT).show();
        }
    }

    public static void googleLocation(String location, Context context, String imageUri) {
        if (location != null && !location.isEmpty()) {
            String[] onSplitTextPhaseOne = location.split(",");
            String v = onSplitTextPhaseOne[0];
            String v1 = onSplitTextPhaseOne[1];
            Intent intent = new Intent(context, GoogleMapsActivity.class);
            intent.putExtra("v", v);
            intent.putExtra("v1", v1);
            intent.putExtra("image", imageUri);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.no_loc_available), Toast.LENGTH_SHORT).show();
        }
    }

    public static void sendMail(String eMail, Context context) {
        if (eMail != null && !eMail.isEmpty()) {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + eMail));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }

        } else {
            Toast.makeText(context, context.getResources().getString(R.string.no_mail_available), Toast.LENGTH_SHORT).show();
        }
    }

    private void shareDirection(String direction, Context context) {
        if (direction != null && !direction.isEmpty()) {
            Toast.makeText(context, context.getResources().getString(R.string.no_direction_available), Toast.LENGTH_SHORT).show();
        } else {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = direction;
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Location");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
    }

}

