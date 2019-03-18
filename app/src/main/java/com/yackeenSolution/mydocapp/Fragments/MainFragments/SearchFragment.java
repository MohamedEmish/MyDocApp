package com.yackeenSolution.mydocapp.Fragments.MainFragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Data.DataViewModel;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.MyArea;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.R;
import com.yackeenSolution.mydocapp.Fragments.SearchTabActivites.SearchResultDoctorActivity;
import com.yackeenSolution.mydocapp.Fragments.SearchTabActivites.SearchResultsFacilityActivity;
import com.yackeenSolution.mydocapp.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment {
    private RadioButton doctor;
    private RadioGroup type;
    private Button search;
    private RadioButton facility;
    private LinearLayout doctorLayout, facilityLayout;
    private TextView date;
    private Spinner specialitySpinner, areaSpinner, insuranceSpinner;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener mPicker;
    private DataViewModel dataViewModel;
    LinearLayout dataLayout, progressbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup nullParent = null;

        View rootView = inflater.inflate(R.layout.search_frag, nullParent);

        dataLayout = rootView.findViewById(R.id.search_frag_data_layout);
        progressbar = rootView.findViewById(R.id.search_frag_progress_bar_layout);

        search = rootView.findViewById(R.id.search_frag_button);
        type = rootView.findViewById(R.id.search_type_radio_group);
        doctor = rootView.findViewById(R.id.search_doctor_radio_button);
        doctor.setChecked(true);

        facility = rootView.findViewById(R.id.search_facility_radio_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchResults();
            }
        });

        doctorLayout = rootView.findViewById(R.id.search_doctor_radio_layout);
        doctorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctor.setChecked(true);
            }
        });
        facilityLayout = rootView.findViewById(R.id.search_facility_radio_layout);
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

    private void setUpSpinnersData() {

        dataViewModel.getSpecialities().observe(this, new Observer<List<Speciality>>() {
            @Override
            public void onChanged(List<Speciality> specialities) {

                List<String> strings = new ArrayList<>();
                strings.add(getContext().getResources().getString(R.string.select_speciality));
                if (specialities.size() > 0) {
                    for (Speciality speciality : specialities) {
                        strings.add(speciality.getName());
                    }
                    Utils.setupSpinner(getContext(), strings, specialitySpinner);
                }

            }
        });

        dataViewModel.getMyInsuranceList().observe(this, new Observer<List<Insurance>>() {
            @Override
            public void onChanged(List<Insurance> insurances) {

                List<String> strings = new ArrayList<>();
                strings.add(getContext().getResources().getString(R.string.select_insurance_op));
                if (insurances.size() > 0) {
                    for (Insurance insurance : insurances) {
                        strings.add(insurance.getName());
                    }
                    Utils.setupSpinner(getContext(), strings, insuranceSpinner);
                }

            }
        });

        dataViewModel.getMyAreaList().observe(this, new Observer<List<MyArea>>() {
            @Override
            public void onChanged(List<MyArea> areas) {

                List<String> strings = new ArrayList<>();
                strings.add(getContext().getResources().getString(R.string.select_area));
                if (areas.size() > 0) {
                    progressbar.setVisibility(View.GONE);
                    dataLayout.setVisibility(View.VISIBLE);
                    for (MyArea area : areas) {
                        strings.add(area.getName());
                    }
                    Utils.setupSpinner(getContext(), strings, areaSpinner);
                }

            }
        });
    }

    private void openSearchResults() {
        if (facility.isChecked()) {
            Intent intent = new Intent(getContext(), SearchResultsFacilityActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getContext(), SearchResultDoctorActivity.class);
            startActivity(intent);
        }
    }

    private void pickDate() {

        myCalendar = Calendar.getInstance();

        mPicker = new DatePickerDialog.OnDateSetListener() {
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
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                R.style.Date_Picker,
                mPicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMaxDate(myCalendar.getTimeInMillis());

        dialog.show();
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
    }
}
