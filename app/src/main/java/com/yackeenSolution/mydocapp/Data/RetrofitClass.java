package com.yackeenSolution.mydocapp.Data;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yackeenSolution.mydocapp.ActivitiesAndFragments.ActivitiesOfMoreTab.AddNewFamilyMember;
import com.yackeenSolution.mydocapp.Objects.Advice;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FacilityResult;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.Objects.FamilyMemberToUpload;
import com.yackeenSolution.mydocapp.Objects.FamilyRelation;
import com.yackeenSolution.mydocapp.Objects.FavouriteDoctor;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.MyAboutUs;
import com.yackeenSolution.mydocapp.Objects.MyArea;
import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.Objects.NewFavDoctor;
import com.yackeenSolution.mydocapp.Objects.NewFavFacility;
import com.yackeenSolution.mydocapp.Objects.PasswordToken;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.Objects.UserData;
import com.yackeenSolution.mydocapp.Objects.UserDataToUpload;
import com.yackeenSolution.mydocapp.Objects.UserToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public static DocApi getDocApi() {
        return getRetrofitInstance().create(DocApi.class);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //GETS

    // Spinners Data

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

    public LiveData<List<Speciality>> getQualificationLive() {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllQualifications().enqueue(new Callback<List<Speciality>>() {
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

    public LiveData<List<Speciality>> getNationalitiesLive() {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllNationalities().enqueue(new Callback<List<Speciality>>() {
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

    public LiveData<List<Speciality>> getLanguagesLive() {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllLanguages().enqueue(new Callback<List<Speciality>>() {
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

    public LiveData<List<Speciality>> getFacilityTypeLive() {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllFacilityType().enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(Call<List<Speciality>> call, Response<List<Speciality>> response) {
                Log.d(TAG, "onResponse: FacilityTypes " + response);

                List<Speciality> policy = response.body();
                SpecialityMutableLiveData.setValue(policy);

            }

            @Override
            public void onFailure(Call<List<Speciality>> call, Throwable t) {
                Log.d(TAG, "onFailure: FacilityTypes" + t.getMessage());

            }
        });

        return SpecialityMutableLiveData;
    }

    ///////////////////////////////////////////////////////////////////////

    // More Tab Activities GETS
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

    public LiveData<FamilyMember> getSpecificFamilyMember(int memberId) {

        final MutableLiveData<FamilyMember> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSpecificFamilyMembers(memberId).enqueue(new Callback<FamilyMember>() {
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

    ///////////////////////////////////////////////////////////////////////

    // Promotion Tab
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

    ///////////////////////////////////////////////////////////////////////

    // Favourites
    public LiveData<List<FavouriteDoctor>> getMyFavDoctors(int id) {

        final MutableLiveData<List<FavouriteDoctor>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getMyFavDoctors(id).enqueue(new Callback<List<FavouriteDoctor>>() {
            @Override
            public void onResponse(Call<List<FavouriteDoctor>> call, Response<List<FavouriteDoctor>> response) {
                Log.d(TAG, "onResponse: MyFavDoctors " + response);

                List<FavouriteDoctor> familyMembers = response.body();
                listMutableLiveData.setValue(familyMembers);

            }


            @Override
            public void onFailure(Call<List<FavouriteDoctor>> call, Throwable t) {
                Log.d(TAG, "onFailure: MyFavDoctors" + t.getMessage());

            }
        });

        return listMutableLiveData;

    }

    public LiveData<List<FacilityResult>> getMyFavFacilities(int id) {

        final MutableLiveData<List<FacilityResult>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getMyFavFacilities(id).enqueue(new Callback<List<FacilityResult>>() {
            @Override
            public void onResponse(Call<List<FacilityResult>> call, Response<List<FacilityResult>> response) {
                Log.d(TAG, "onResponse: MyFavDoctors " + response);

                List<FacilityResult> facilityResults = response.body();
                listMutableLiveData.setValue(facilityResults);

            }

            @Override
            public void onFailure(Call<List<FacilityResult>> call, Throwable t) {
                Log.d(TAG, "onFailure: MyFavDoctors" + t.getMessage());

            }
        });

        return listMutableLiveData;

    }

    ///////////////////////////////////////////////////////////////////////

    // Appointment Tab
    public LiveData<List<Appointment>> getMyAppointments(int userId, int statusId) {

        final MutableLiveData<List<Appointment>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getMyAppointments(userId, statusId).enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                Log.d(TAG, "onResponse: Appointment " + response);

                List<Appointment> appointments = response.body();
                listMutableLiveData.setValue(appointments);

            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {
                Log.d(TAG, "onFailure: MyFavDoctors" + t.getMessage());

            }
        });

        return listMutableLiveData;

    }


    ///////////////////////////////////////////////////////////////////////

    // Details
    public LiveData<List<DoctorResult>> getSpecificDoctorData(int doctorId) {

        final MutableLiveData<List<DoctorResult>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSpecificDoctorData(doctorId).enqueue(new Callback<List<DoctorResult>>() {
            @Override
            public void onResponse(Call<List<DoctorResult>> call, Response<List<DoctorResult>> response) {
                Log.d(TAG, "onResponse: SpecificDoctorResultData " + response);

                List<DoctorResult> favouriteDoctor = response.body();
                listMutableLiveData.setValue(favouriteDoctor);
            }

            @Override
            public void onFailure(Call<List<DoctorResult>> call, Throwable t) {
                Log.d(TAG, "onFailure: SpecificDoctorResultData" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    public LiveData<FacilityResult> getSpecificFacilityData(int facilityId, int userId) {

        final MutableLiveData<FacilityResult> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSpecificFacilityData(facilityId, userId).enqueue(new Callback<FacilityResult>() {
            @Override
            public void onResponse(Call<FacilityResult> call, Response<FacilityResult> response) {
                Log.d(TAG, "onResponse: SpecificFacilityResultData " + response);

                FacilityResult facilityResult = response.body();
                listMutableLiveData.setValue(facilityResult);
            }

            @Override
            public void onFailure(Call<FacilityResult> call, Throwable t) {
                Log.d(TAG, "onFailure: SpecificFacilityResultData" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    ///////////////////////////////////////////////////////////////////////

    // Search Results
    public LiveData<List<DoctorResult>> getSearchForDoctorResults(
            int specialityId,
            String fromDate,
            String toDate,
            Integer areaId,
            Integer insuranceId,
            Integer qualificationId,
            Integer languageId,
            Integer nationalityId,
            Boolean gender) {

        final MutableLiveData<List<DoctorResult>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSearchForDoctorResult(
                specialityId,
                fromDate,
                toDate,
                areaId,
                insuranceId,
                qualificationId,
                languageId,
                nationalityId, gender).enqueue(new Callback<List<DoctorResult>>() {
            @Override
            public void onResponse(Call<List<DoctorResult>> call, Response<List<DoctorResult>> response) {
                Log.d(TAG, "onResponse: SearchForDoctorResultData " + response);

                List<DoctorResult> doctorResults = response.body();
                listMutableLiveData.setValue(doctorResults);
            }

            @Override
            public void onFailure(Call<List<DoctorResult>> call, Throwable t) {
                Log.d(TAG, "onFailure:SearchForDoctorResultData" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    public LiveData<List<FacilityResult>> getSearchForFacilityResults(
            int specialityId,
            Integer areaId,
            Integer insuranceId,
            Integer facilityTyprId) {

        final MutableLiveData<List<FacilityResult>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSearchForFacilityResult(
                specialityId,
                areaId,
                insuranceId,
                facilityTyprId).enqueue(new Callback<List<FacilityResult>>() {
            @Override
            public void onResponse(Call<List<FacilityResult>> call, Response<List<FacilityResult>> response) {
                Log.d(TAG, "onResponse: SearchForFacilityResultData " + response);

                List<FacilityResult> facilityResult = response.body();
                listMutableLiveData.setValue(facilityResult);
            }

            @Override
            public void onFailure(Call<List<FacilityResult>> call, Throwable t) {
                Log.d(TAG, "onFailure:SearchForFacilityResultData" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // POSTS :)

    public LiveData<UserData> userLogin(HashMap<String, String> fields) {

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

    public LiveData<UserDataToUpload> editUserData(UserDataToUpload user) {
        final MutableLiveData<UserDataToUpload> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.editUserProfile(user).enqueue(new Callback<UserDataToUpload>() {
            @Override
            public void onResponse(Call<UserDataToUpload> call, Response<UserDataToUpload> response) {
                Log.d(TAG, "onResponse: EditUserData " + response);
                UserDataToUpload userData = response.body();
                listMutableLiveData.setValue(userData);
            }

            @Override
            public void onFailure(Call<UserDataToUpload> call, Throwable t) {
                Log.d(TAG, "onFailure: EditUserData" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    public LiveData<UserDataToUpload> addNewUser(UserDataToUpload user) {
        final MutableLiveData<UserDataToUpload> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.addNewUser(user).enqueue(new Callback<UserDataToUpload>() {
            @Override
            public void onResponse(Call<UserDataToUpload> call, Response<UserDataToUpload> response) {
                Log.d(TAG, "onResponse: NewUserData " + response);
                UserDataToUpload userData = response.body();
                listMutableLiveData.setValue(userData);
            }

            @Override
            public void onFailure(Call<UserDataToUpload> call, Throwable t) {
                Log.d(TAG, "onFailure: NewUserData" + t.getMessage());

            }
        });
        return listMutableLiveData;
    }

    public void addEditFamilyMemberData(FamilyMemberToUpload familyMember) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.addEditFamilyMember(familyMember).enqueue(new Callback<FamilyMemberToUpload>() {
            @Override
            public void onResponse(Call<FamilyMemberToUpload> call, Response<FamilyMemberToUpload> response) {
                Log.d(TAG, "onResponse: FamilyMemberToUpload " + response);
            }

            @Override
            public void onFailure(Call<FamilyMemberToUpload> call, Throwable t) {
                Log.d(TAG, "onFailure: FamilyMemberToUpload" + t.getMessage());

            }
        });
    }

    public void postAdvice(Advice advice) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.postAdvice(advice).enqueue(new Callback<Advice>() {
            @Override
            public void onResponse(Call<Advice> call, Response<Advice> response) {
                Log.d(TAG, "onResponse: Advice " + response);
            }

            @Override
            public void onFailure(Call<Advice> call, Throwable t) {
                Log.d(TAG, "onFailure: Advice" + t.getMessage());

            }
        });
    }

    public void logOut(UserToken token) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.logOut(token).enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                Log.d(TAG, "onResponse: Logout " + response);
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                Log.d(TAG, "onFailure: Logout" + t.getMessage());

            }
        });
    }

    public LiveData<PasswordToken> forgetPassword(PasswordToken token) {
        final MutableLiveData<PasswordToken> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.forgetPassword(token).enqueue(new Callback<PasswordToken>() {
            @Override
            public void onResponse(Call<PasswordToken> call, Response<PasswordToken> response) {
                Log.d(TAG, "onResponse: ForgotPassword " + response);
                PasswordToken token = response.body();
                listMutableLiveData.setValue(token);
            }

            @Override
            public void onFailure(Call<PasswordToken> call, Throwable t) {
                Log.d(TAG, "onFailure: ForgotPassword" + t.getMessage());
            }
        });
        return listMutableLiveData;
    }

    public LiveData<PasswordToken> resetPassword(PasswordToken token) {
        final MutableLiveData<PasswordToken> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.resetPassword(token).enqueue(new Callback<PasswordToken>() {
            @Override
            public void onResponse(Call<PasswordToken> call, Response<PasswordToken> response) {
                Log.d(TAG, "onResponse: ResetPassword " + response);
                PasswordToken token = response.body();
                listMutableLiveData.setValue(token);
            }

            @Override
            public void onFailure(Call<PasswordToken> call, Throwable t) {
                Log.d(TAG, "onFailure: ResetPassword" + t.getMessage());
            }
        });
        return listMutableLiveData;
    }

    public void setDoctorFavState(NewFavDoctor doctor) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.setFavDoctorState(doctor).enqueue(new Callback<NewFavDoctor>() {
            @Override
            public void onResponse(Call<NewFavDoctor> call, Response<NewFavDoctor> response) {
                Log.d(TAG, "onResponse: SetDoctorFavState " + response);
            }

            @Override
            public void onFailure(Call<NewFavDoctor> call, Throwable t) {
                Log.d(TAG, "onFailure: SetDoctorFavState" + t.getMessage());

            }
        });
    }

    public void setFacilityFavState(NewFavFacility facility) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.setFavFacilityState(facility).enqueue(new Callback<NewFavFacility>() {
            @Override
            public void onResponse(Call<NewFavFacility> call, Response<NewFavFacility> response) {
                Log.d(TAG, "onResponse: SetFacilityFavState " + response);
            }

            @Override
            public void onFailure(Call<NewFavFacility> call, Throwable t) {
                Log.d(TAG, "onFailure: SetFacilityFavState" + t.getMessage());

            }
        });
    }

    public LiveData<String> uploadImage(MultipartBody.Part file, RequestBody description) {
        final MutableLiveData<String> listMutableLiveData = new MutableLiveData<>();
        DocApi docApi = RetrofitClass.getDocApi();
        docApi.uploadImage(file, description).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: imageUrlToUpload :: " + response);
                try {
                    listMutableLiveData.setValue(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: imageUrlToUpload :: " + t.getMessage());

            }
        });
        return listMutableLiveData;
    }
}