package com.yackeenSolution.mydocapp.Data;

import android.content.Context;
import android.util.Log;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.Objects.FamilyRelation;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.MyAboutUs;
import com.yackeenSolution.mydocapp.Objects.MyArea;
import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.Objects.UserData;

import java.util.HashMap;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClass {


    private static final String TAG = RetrofitClass.class.getCanonicalName();

    private static final String BASE_URL = "http://yakensolution.cloudapp.net/DoctoryIntern/";

    private static Retrofit getRetrofitInstance() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
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

    public LiveData<UserData> getUserData(HashMap<String, String> fields) {

        final MutableLiveData<UserData> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.userLogin(fields).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                Log.d(TAG, "onResponse: UserData " + response);

                UserData userData = response.body();
                listMutableLiveData.setValue(userData);
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d(TAG, "onFailure: UserData" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    public LiveData<UserData> getUserAccountData(int id) {

        final MutableLiveData<UserData> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.userAccountData(id).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                Log.d(TAG, "onResponse: UserAccountData " + response);

                UserData userData = response.body();
                listMutableLiveData.setValue(userData);
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d(TAG, "onFailure: UserAccountData" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    public LiveData<UserData> editUserData(HashMap<String, String> fields) {

        final MutableLiveData<UserData> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.userLogin(fields).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                Log.d(TAG, "onResponse: EditUserData " + response);

                UserData userData = response.body();
                listMutableLiveData.setValue(userData);
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d(TAG, "onFailure: EditUserData" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    public LiveData<List<FamilyMember>> getMyFamilyMembers(int id) {

        final MutableLiveData<List<FamilyMember>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getMyFamilyMembers(id).enqueue(new Callback<List<FamilyMember>>() {
            @Override
            public void onResponse(Call<List<FamilyMember>> call, Response<List<FamilyMember>> response) {
                Log.d(TAG, "onResponse: AllFamilyMembers " + response);

                List<FamilyMember> familyMembers = response.body();
                listMutableLiveData.setValue(familyMembers);

            }

            @Override
            public void onFailure(Call<List<FamilyMember>> call, Throwable t) {
                Log.d(TAG, "onFailure: AllFamilyMembers" + t.getMessage());

            }
        });

        return listMutableLiveData;

    }

    public LiveData<FamilyMember> getSpecificFamilyMember(int userId, int memberId) {

        final MutableLiveData<FamilyMember> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSpecificFamilyMembers(userId, memberId).enqueue(new Callback<FamilyMember>() {
            @Override
            public void onResponse(Call<FamilyMember> call, Response<FamilyMember> response) {
                Log.d(TAG, "onResponse: SpecificFamilyMember " + response);

                FamilyMember familyMembers = response.body();
                listMutableLiveData.setValue(familyMembers);

            }

            @Override
            public void onFailure(Call<FamilyMember> call, Throwable t) {
                Log.d(TAG, "onFailure: SpecificFamilyMember" + t.getMessage());

            }
        });

        return listMutableLiveData;
    }

    public LiveData<FamilyMember> addEditFamilyMemberData(HashMap<String, String> fields) {

        final MutableLiveData<FamilyMember> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.addEditFamilyMember(fields).enqueue(new Callback<FamilyMember>() {
            @Override
            public void onResponse(Call<FamilyMember> call, Response<FamilyMember> response) {
                Log.d(TAG, "onResponse: AddEditFamilyMember " + response);

                FamilyMember familyMember = response.body();
                listMutableLiveData.setValue(familyMember);
            }

            @Override
            public void onFailure(Call<FamilyMember> call, Throwable t) {
                Log.d(TAG, "onFailure: AddEditFamilyMember" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    public LiveData<List<FamilyRelation>> getFamilyRelationsLive() {
        final MutableLiveData<List<FamilyRelation>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllRelations().enqueue(new Callback<List<FamilyRelation>>() {
            @Override
            public void onResponse(Call<List<FamilyRelation>> call, Response<List<FamilyRelation>> response) {
                Log.d(TAG, "onResponse: FamilyRelations " + response);

                List<FamilyRelation> policy = response.body();
                SpecialityMutableLiveData.setValue(policy);

            }

            @Override
            public void onFailure(Call<List<FamilyRelation>> call, Throwable t) {
                Log.d(TAG, "onFailure: FamilyRelations" + t.getMessage());

            }
        });

        return SpecialityMutableLiveData;
    }

    public LiveData<List<DoctorResult>> getMyFavDoctors(int id) {

        final MutableLiveData<List<DoctorResult>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getMyFavDoctors(id).enqueue(new Callback<List<DoctorResult>>() {
            @Override
            public void onResponse(Call<List<DoctorResult>> call, Response<List<DoctorResult>> response) {
                Log.d(TAG, "onResponse: MyFavDoctors " + response);

                List<DoctorResult> familyMembers = response.body();
                listMutableLiveData.setValue(familyMembers);

            }

            @Override
            public void onFailure(Call<List<DoctorResult>> call, Throwable t) {
                Log.d(TAG, "onFailure: MyFavDoctors" + t.getMessage());

            }
        });

        return listMutableLiveData;

    }


}


