package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Data.DocApi;
import com.yackeenSolution.mydocapp.Data.RetrofitClass;
import com.yackeenSolution.mydocapp.Objects.Advice;
import com.yackeenSolution.mydocapp.Objects.MyAboutUs;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity {

    ImageView back;
    public static final String TAG = ContactUsActivity.class.getCanonicalName();
    Button button;
    EditText messageEditText;
    String message;
    DataViewModel dataViewModel;
    String fbURL, tweeterURL, instegramURL, phoneNumber, webUrl;
    ImageView fb, tweet, instegram;
    TextView web;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_contact_us);
        LinearLayout linearLayout = findViewById(R.id.contact_us_root);
        Utils.RTLSupport(this, linearLayout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        web = findViewById(R.id.contact_us_web);
        fb = findViewById(R.id.contact_us_fb);
        tweet = findViewById(R.id.contact_us_tweeter);
        instegram = findViewById(R.id.contact_us_instgram);

        back = findViewById(R.id.contact_us_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        messageEditText = findViewById(R.id.contact_us_message_text);
        message = messageEditText.getText().toString().trim();

        button = findViewById(R.id.contact_us_send_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(message);
            }
        });
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        dataViewModel.getMyPolicyLiveData();
        setUpData();

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openBrowser(fbURL, ContactUsActivity.this);
            }
        });

        tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openBrowser(tweeterURL, ContactUsActivity.this);
            }
        });

        instegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openBrowser(instegramURL, ContactUsActivity.this);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openBrowser(webUrl, ContactUsActivity.this);
            }
        });

    }

    private void setUpData() {

        dataViewModel.getSocialAccounts().observe(this, new Observer<List<MyAboutUs>>() {
            @Override
            public void onChanged(List<MyAboutUs> policyList) {

                if (policyList.size() > 0) {
                    int x = policyList.size();
                    for (int i = 0; i <= policyList.size() - 1; i++) {
                        switch (policyList.get(i).getTitle()) {
                            case "fb":
                                fbURL = policyList.get(i).getContent();
                                break;
                            case "instagram":
                                instegramURL = policyList.get(i).getContent();
                                break;
                            case "twitter":
                                tweeterURL = policyList.get(i).getContent();
                                break;
                            case "contactNumber":
                                phoneNumber = policyList.get(i).getContent();
                                break;
                            case "WebSite":
                                webUrl = policyList.get(i).getContent();
                                web.setText(webUrl);
                                break;
                        }
                    }
                }
            }
        });
    }


    private void sendMessage(String message) {
        Advice advice = new Advice();
        advice.setMessage(message);
        advice.setMail(SaveSharedPreference.getUserEmail(this));
        advice.setPhone(null);
        // TODO :: NEVER POST ADVICE
        dataViewModel.postAdvice(advice);
        Toast.makeText(ContactUsActivity.this, ContactUsActivity.this.getResources().getString(R.string.sent_successfully), Toast.LENGTH_SHORT).show();
        ContactUsActivity.this.finish();

    }

}
