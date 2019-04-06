package com.yackeenSolution.mydocapp.ActivitiesAndFragments.FragmentsOfMainScreen;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults.SearchResultDoctorActivity;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfSearchResults.SearchResultsFacilityActivity;
import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.MyArea;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SearchFragment extends Fragment {
    private RadioButton doctor;
    private RadioButton facility;
    private boolean areaDone = false, insuranceDone = false, specialityDone = false;
    private Spinner specialitySpinner, areaSpinner, insuranceSpinner;
    private Calendar myCalendar;
    private DataViewModel dataViewModel;
    private EditText date;
    private LinearLayout dataLayout, progressbar;
    private List<Speciality> mainSpecialityList;
    private List<MyArea> mainAreaList;
    private List<Insurance> mainInsuranceList;
    private List<String> stringsSpeciality;
    private List<String> stringsInsurance;
    private List<String> stringsArea;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;
        View rootView = inflater.inflate(R.layout.search_frag, nullParent);

        dataLayout = rootView.findViewById(R.id.search_frag_data_layout);
        progressbar = rootView.findViewById(R.id.search_frag_progress_bar_layout);

        Button search = rootView.findViewById(R.id.search_frag_button);
        doctor = rootView.findViewById(R.id.search_doctor_radio_button);
        doctor.setChecked(true);

        facility = rootView.findViewById(R.id.search_facility_radio_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchResults();
            }
        });

        LinearLayout doctorLayout = rootView.findViewById(R.id.search_doctor_radio_layout);
        doctorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctor.setChecked(true);
            }
        });
        LinearLayout facilityLayout = rootView.findViewById(R.id.search_facility_radio_layout);
        facilityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facility.setChecked(true);
            }
        });

        specialitySpinner = rootView.findViewById(R.id.search_speciality_spinner);
        insuranceSpinner = rootView.findViewById(R.id.search_insurance_spinner);
        areaSpinner = rootView.findViewById(R.id.search_area_spinner);

        date = rootView.findViewById(R.id.search_date_text);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        setUpSpinnersData();

        return rootView;
    }

    private void checkDone() {
        if (areaDone && insuranceDone && specialityDone) {
            progressbar.setVisibility(View.GONE);
            dataLayout.setVisibility(View.VISIBLE);
        }
    }
    private void setUpSpinnersData() {

        dataViewModel.getSpecialities(getContext()).observe(this, new Observer<List<Speciality>>() {
            @Override
            public void onChanged(List<Speciality> specialities) {

                mainSpecialityList = specialities;
                stringsSpeciality = new ArrayList<>();
                stringsSpeciality.add(Objects.requireNonNull(getContext()).getResources().getString(R.string.select_speciality));
                if (specialities.size() > 0) {
                    for (Speciality speciality : specialities) {
                        stringsSpeciality.add(speciality.getName());
                    }
                    Utils.setupSpinner(getContext(), stringsSpeciality, specialitySpinner);
                    specialityDone = true;
                    checkDone();
                }
            }
        });

        dataViewModel.getMyInsuranceList(getContext()).observe(this, new Observer<List<Insurance>>() {
            @Override
            public void onChanged(List<Insurance> insurances) {

                mainInsuranceList = insurances;
                stringsInsurance = new ArrayList<>();
                stringsInsurance.add(Objects.requireNonNull(getContext()).getResources().getString(R.string.select_insurance_op));
                if (insurances.size() > 0) {
                    for (Insurance insurance : insurances) {
                        stringsInsurance.add(insurance.getName());
                    }
                    Utils.setupSpinner(getContext(), stringsInsurance, insuranceSpinner);
                    insuranceDone = true;
                    checkDone();
                }
            }
        });

        dataViewModel.getMyAreaList(getContext()).observe(this, new Observer<List<MyArea>>() {
            @Override
            public void onChanged(List<MyArea> areas) {

                mainAreaList = areas;
                stringsArea = new ArrayList<>();
                stringsArea.add(Objects.requireNonNull(getContext()).getResources().getString(R.string.select_area_o));
                if (areas.size() > 0) {
                    for (MyArea area : areas) {
                        stringsArea.add(area.getName());
                    }
                    Utils.setupSpinner(getContext(), stringsArea, areaSpinner);
                    areaDone = true;
                    checkDone();
                }
            }
        });
    }

    private void openSearchResults() {
        if (specialitySpinner.getSelectedItemId() == 0) {
            Toast.makeText(getContext(), Objects.requireNonNull(getContext()).getResources().getString(R.string.speciality_must_be_selected), Toast.LENGTH_SHORT).show();
        } else {
            String areaId = "null";
            String specialityId = "null";
            String insuranceId = "null";
            String searchDate = "";

            if (areaSpinner.getSelectedItemId() != 0) {
                String area = stringsArea.get(areaSpinner.getSelectedItemPosition());
                for (MyArea mArea : mainAreaList) {
                    if (mArea.getName().equals(area)) {
                        areaId = String.valueOf(mArea.getId());
                        break;
                    }
                }
            }
            if (specialitySpinner.getSelectedItemId() != 0) {
                String speciality = stringsSpeciality.get(specialitySpinner.getSelectedItemPosition());
                for (Speciality mSpeciality : mainSpecialityList) {
                    if (mSpeciality.getName().equals(speciality)) {
                        specialityId = String.valueOf(mSpeciality.getId());
                        break;
                    }
                }
            }
            if (insuranceSpinner.getSelectedItemId() != 0) {
                String insurance = stringsInsurance.get(insuranceSpinner.getSelectedItemPosition());
                for (Insurance mInsurance : mainInsuranceList) {
                    if (mInsurance.getName().equals(insurance)) {
                        insuranceId = String.valueOf(mInsurance.getId());
                        break;
                    }
                }
            }

            if (facility.isChecked()) {
                // Facility Search Activity
                if (!date.getText().toString().isEmpty()) {
                    searchDate = Utils.dateToApiFormat(date.getText().toString().trim());
                }
                Intent intent = new Intent(getContext(), SearchResultsFacilityActivity.class);
                intent.putExtra("specialityId", specialityId);
                intent.putExtra("insuranceId", String.valueOf(insuranceId));
                intent.putExtra("areaId", String.valueOf(areaId));
                intent.putExtra("searchDate", searchDate);
                startActivity(intent);
            } else {
                // Doctor Search Activity
                if (!date.getText().toString().isEmpty()) {
                    searchDate = Utils.dateToApiFormat(date.getText().toString().trim());
                }
                Intent intent = new Intent(getContext(), SearchResultDoctorActivity.class);
                intent.putExtra("specialityId", specialityId);
                intent.putExtra("insuranceId", String.valueOf(insuranceId));
                intent.putExtra("areaId", String.valueOf(areaId));
                intent.putExtra("searchDate", searchDate);

                startActivity(intent);
            }
        }
    }

    private void pickDate() {

        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener mPicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String myFormat = "dd/MM/YYYY";
                SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());

                myCalendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                myCalendar.set(Calendar.YEAR, datePicker.getYear());
                myCalendar.set(Calendar.MONTH, datePicker.getMonth());

                date.setText(format.format(myCalendar.getTime()));
            }
        };
        // start picker with pre-chosen date
        DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getContext()),
                R.style.Date_Picker,
                mPicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMinDate(myCalendar.getTimeInMillis());

        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
    }
}
