package com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Adapters.DoctorResultAdapter;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FavouriteDoctor;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultDoctorActivity extends AppCompatActivity {

    RecyclerView doctorResultRecycleView;
    DoctorResultAdapter doctorResultAdapter;

    DataViewModel dataViewModel;
    List<Integer> favoriteIdList = new ArrayList<>();

    LinearLayout progress, dataLayout;
    ImageView back;
    TextView filter;

    int specialityId;
    String fromDate, toDate, searchDate;
    String insuranceId, areaId, qualificationId, languageId, nationalityId, genderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Localization
        Utils.setLocale(this);
        setContentView(R.layout.activity_search_result_doctor);
        LinearLayout linearLayout = findViewById(R.id.search_results_doctor_root);
        Utils.RTLSupport(this, linearLayout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();

        specialityId = Integer.parseInt(intent.getStringExtra("specialityId"));
        insuranceId = intent.getStringExtra("insuranceId");
        areaId = intent.getStringExtra("areaId");
        searchDate = intent.getStringExtra("searchDate");

        if (intent.hasExtra("qualificationId")) {
            qualificationId = intent.getStringExtra("qualificationId");
        } else {
            qualificationId = "null";
        }

        if (intent.hasExtra("languageId")) {
            languageId = intent.getStringExtra("languageId");
        } else {
            languageId = "null";
        }

        if (intent.hasExtra("genderId")) {
            genderId = intent.getStringExtra("genderId");
        } else {
            genderId = "null";
        }

        if (intent.hasExtra("nationalityId")) {
            nationalityId = intent.getStringExtra("nationalityId");
        } else {
            nationalityId = "null";
        }

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        progress = findViewById(R.id.doctor_search_result_progress_bar_layout);
        dataLayout = findViewById(R.id.doctor_search_result_data);

        back = findViewById(R.id.search_results_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        filter = findViewById(R.id.search_result_facility_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFilter();
            }
        });

        doctorResultRecycleView = findViewById(R.id.search_results_recycler);
        doctorResultRecycleView.setLayoutManager(new LinearLayoutManager(this));
        doctorResultRecycleView.setHasFixedSize(true);
        doctorResultAdapter = new DoctorResultAdapter();
        doctorResultRecycleView.setAdapter(doctorResultAdapter);

        doctorResultAdapter.setOnItemReqClickListener(new DoctorResultAdapter.OnItemReqClickListener() {
            @Override
            public void onItemClick(DoctorResult doctorResult) {
                Intent intent = new Intent(SearchResultDoctorActivity.this, AppointmentRequestActivity.class);
                intent.putExtra("doctorId", String.valueOf(doctorResult.getId()));
                startActivity(intent);
            }
        });

        doctorResultAdapter.setOnItemClickListener(
                new DoctorResultAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(DoctorResult doctorResult) {
                        Intent intent = new Intent(SearchResultDoctorActivity.this, DoctorDetailsActivity.class);
                        intent.putExtra("doctorId", String.valueOf(doctorResult.getId()));
                        startActivity(intent);
                    }
                });
        setUpData();
    }

    private void setUpData() {
        dataViewModel.getMyFavDoctorsList(Integer.parseInt(SaveSharedPreference.getUserId(this))).observe(this, new Observer<List<FavouriteDoctor>>() {
            @Override
            public void onChanged(List<FavouriteDoctor> favouriteDoctors) {
                for (FavouriteDoctor favDoc : favouriteDoctors) {
                    favoriteIdList.add(favDoc.getId());
                }
                setUp();
            }
        });
    }

    private void setUp() {
        if (searchDate.equals("")) {
            fromDate = getTodayDate();
            toDate = getTomorrowDate();
        } else {
            fromDate = getTodayDate();
            toDate = searchDate;
        }

        Integer area, insurance, qualification, language, nationality;
        Boolean gender;

        if (areaId.equals("null")) {
            area = null;
        } else {
            area = Integer.parseInt(areaId);
        }

        if (insuranceId.equals("null")) {
            insurance = null;
        } else {
            insurance = Integer.parseInt(insuranceId);
        }

        if (!qualificationId.equals("null")) {
            qualification = Integer.parseInt(qualificationId);
        } else {
            qualification = null;
        }

        if (languageId.equals("null")) {
            language = null;
        } else {
            language = Integer.parseInt(languageId);
        }

        if (nationalityId.equals("null")) {
            nationality = null;
        } else {
            nationality = Integer.parseInt(nationalityId);
        }
        if (genderId.equals("null")) {
            gender = null;
        } else if (genderId.equals("male")) {
            gender = true;
        } else if (genderId.equals("female")) {
            gender = false;
        } else {
            gender = null;
        }
        dataViewModel.getSearchForDoctorResults(
                specialityId,
                fromDate,
                toDate,
                area,
                insurance,
                qualification,
                language,
                nationality,
                gender).observe(this, new Observer<List<DoctorResult>>() {
            @Override
            public void onChanged(List<DoctorResult> doctorResults) {
                progress.setVisibility(View.GONE);
                dataLayout.setVisibility(View.VISIBLE);
                for (DoctorResult docRes : doctorResults) {
                    for (int id : favoriteIdList) {
                        if (docRes.getId() == id) {
                            docRes.setFav(true);
                        }
                    }
                }
                doctorResultAdapter.submitList(doctorResults);
            }
        });
    }

    private void goBack() {
        finish();
    }

    private void doFilter() {
        Intent intent = new Intent(SearchResultDoctorActivity.this, SearchFilterDoctor.class);
        intent.putExtra("specialityId", String.valueOf(specialityId));
        intent.putExtra("insuranceId", insuranceId);
        intent.putExtra("areaId", areaId);
        intent.putExtra("searchDate", searchDate);
        startActivity(intent);
    }

    private String getTodayDate() {
        Calendar myCalendar = Calendar.getInstance();

        String myFormat = "dd/MM/YYYY";
        SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());
        return Utils.dateToApiFormat(format.format(myCalendar.getTime()));
    }

    private String getTomorrowDate() {
        Calendar myCalendar = Calendar.getInstance();
        String myFormat = "dd/MM/YYYY";
        SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());
        myCalendar.add(Calendar.DAY_OF_MONTH, 1);
        return Utils.dateToApiFormat(format.format(myCalendar.getTime()));
    }
}
