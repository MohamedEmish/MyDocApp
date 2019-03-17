package com.yackeenSolution.mydocapp.Data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.MyAboutUs;
import com.yackeenSolution.mydocapp.Objects.MyArea;
import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.Objects.Speciality;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {


    public static final String TAG = RetrofitClass.class.getCanonicalName();
    private static final String BASE_URL = "http://yakensolution.cloudapp.net/DoctoryIntern/";

    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static DocApi getDocApi() {
        return getRetrofitInstance().create(DocApi.class);
    }

    public LiveData<List<MyAboutUs>> getAboutUsLive() {
        final MutableLiveData<List<MyAboutUs>> myAboutUsMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAboutUs().enqueue(new Callback<List<MyAboutUs>>() {
            @Override
            public void onResponse(Call<List<MyAboutUs>> call, Response<List<MyAboutUs>> response) {
                Log.d(TAG, "onResponse: About US " + response);

                List<MyAboutUs> myAboutUs = response.body();
                myAboutUsMutableLiveData.setValue(myAboutUs);

            }

            @Override
            public void onFailure(Call<List<MyAboutUs>> call, Throwable t) {
                Log.d(TAG, "onFailure: About US " + t.getMessage());

            }
        });

        return myAboutUsMutableLiveData;
    }

    public LiveData<List<MyAboutUs>> getPolicyLive() {
        final MutableLiveData<List<MyAboutUs>> myAboutUsMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getPolicy().enqueue(new Callback<List<MyAboutUs>>() {
            @Override
            public void onResponse(Call<List<MyAboutUs>> call, Response<List<MyAboutUs>> response) {
                Log.d(TAG, "onResponse: Policy " + response);

                List<MyAboutUs> policy = response.body();
                myAboutUsMutableLiveData.setValue(policy);

            }

            @Override
            public void onFailure(Call<List<MyAboutUs>> call, Throwable t) {
                Log.d(TAG, "onFailure: Policy" + t.getMessage());

            }
        });

        return myAboutUsMutableLiveData;
    }

    public LiveData<List<Speciality>> getSpecialityLive() {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllSpecialities().enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {
                Log.d(TAG, "onResponse: Speciality " + response);

                List<Speciality> policy = response.body();
                SpecialityMutableLiveData.setValue(policy);

            }

            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {
                Log.d(TAG, "onFailure: Speciality" + t.getMessage());

            }
        });

        return SpecialityMutableLiveData;
    }

    public LiveData<List<Insurance>> getInsuranceLive() {
        final MutableLiveData<List<Insurance>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllInsurances().enqueue(new Callback<List<Insurance>>() {
            @Override
            public void onResponse(Call<List<Insurance>> call, Response<List<Insurance>> response) {
                Log.d(TAG, "onResponse: Insurance " + response);

                List<Insurance> insurances = response.body();
                listMutableLiveData.setValue(insurances);

            }

            @Override
            public void onFailure(Call<List<Insurance>> call, Throwable t) {
                Log.d(TAG, "onFailure: Insurance" + t.getMessage());

            }
        });

        return listMutableLiveData;
    }

    public LiveData<List<MyArea>> getAreaLive() {
        final MutableLiveData<List<MyArea>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllAreas().enqueue(new Callback<List<MyArea>>() {
            @Override
            public void onResponse(Call<List<MyArea>> call, Response<List<MyArea>> response) {
                Log.d(TAG, "onResponse: MyArea " + response);

                List<MyArea> areas = response.body();
                listMutableLiveData.setValue(areas);

            }

            @Override
            public void onFailure(Call<List<MyArea>> call, Throwable t) {
                Log.d(TAG, "onFailure: MyArea" + t.getMessage());

            }
        });

        return listMutableLiveData;
    }

    public LiveData<List<Promotion>> getAllPromotions() {

        final MutableLiveData<List<Promotion>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllPromotions().enqueue(new Callback<List<Promotion>>() {
            @Override
            public void onResponse(Call<List<Promotion>> call, Response<List<Promotion>> response) {
                Log.d(TAG, "onResponse: Promotion " + response);

                List<Promotion> promotions = response.body();
                listMutableLiveData.setValue(promotions);

            }

            @Override
            public void onFailure(Call<List<Promotion>> call, Throwable t) {
                Log.d(TAG, "onFailure: Promotion" + t.getMessage());

            }
        });

        return listMutableLiveData;
    }

    public LiveData<List<MyAboutUs>> getSocialAccounts() {

        final MutableLiveData<List<MyAboutUs>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSocialAccounts().enqueue(new Callback<List<MyAboutUs>>() {
            @Override
            public void onResponse(Call<List<MyAboutUs>> call, Response<List<MyAboutUs>> response) {
                Log.d(TAG, "onResponse: Accounts " + response);

                List<MyAboutUs> accounts = response.body();
                listMutableLiveData.setValue(accounts);

            }

            @Override
            public void onFailure(Call<List<MyAboutUs>> call, Throwable t) {
                Log.d(TAG, "onFailure: Accounts" + t.getMessage());

            }
        });

        return listMutableLiveData;

    }

    public LiveData<List<MyNotification>> getNotifications() {

        final MutableLiveData<List<MyNotification>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllNotifications().enqueue(new Callback<List<MyNotification>>() {
            @Override
            public void onResponse(Call<List<MyNotification>> call, Response<List<MyNotification>> response) {
                Log.d(TAG, "onResponse: MyNotification " + response);

                List<MyNotification> notifications = response.body();
                listMutableLiveData.setValue(notifications);

            }

            @Override
            public void onFailure(Call<List<MyNotification>> call, Throwable t) {
                Log.d(TAG, "onFailure: MyNotification" + t.getMessage());

            }
        });

        return listMutableLiveData;

    }

}
