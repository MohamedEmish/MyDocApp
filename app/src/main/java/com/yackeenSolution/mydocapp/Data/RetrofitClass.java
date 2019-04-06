package com.yackeenSolution.mydocapp.Data;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yackeenSolution.mydocapp.Objects.Advice;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.Objects.AppointmentToUpload;
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
import com.yackeenSolution.mydocapp.Utils.SaveSharedPreference;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
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

class RetrofitClass {


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

    private static DocApi getDocApi() {
        return getRetrofitInstance().create(DocApi.class);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //GETS

    // Spinners Data

    LiveData<List<Speciality>> getSpecialityLive(final Context context) {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllSpecialities(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(@NonNull Call<List<Speciality>> call, @NonNull Response<List<Speciality>> response) {
                Log.d(TAG, "onResponse: SpecialityList " + response);
                List<Speciality> policy = response.body();
                SpecialityMutableLiveData.setValue(policy);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Speciality>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: SpecialityList" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return SpecialityMutableLiveData;
    }

    LiveData<List<Insurance>> getInsuranceLive(final Context context) {
        final MutableLiveData<List<Insurance>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllInsurances(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<Insurance>>() {
            @Override
            public void onResponse(@NonNull Call<List<Insurance>> call, @NonNull Response<List<Insurance>> response) {
                Log.d(TAG, "onResponse: Insurance " + response);
                List<Insurance> insurances = response.body();
                listMutableLiveData.setValue(insurances);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Insurance>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Insurance" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    LiveData<List<MyArea>> getAreaLive(final Context context) {
        final MutableLiveData<List<MyArea>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllAreas(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<MyArea>>() {
            @Override
            public void onResponse(@NonNull Call<List<MyArea>> call, @NonNull Response<List<MyArea>> response) {
                Log.d(TAG, "onResponse: MyArea " + response);
                List<MyArea> areas = response.body();
                listMutableLiveData.setValue(areas);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MyArea>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: MyArea" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    LiveData<List<FamilyRelation>> getFamilyRelationsLive(final Context context) {
        final MutableLiveData<List<FamilyRelation>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllRelations(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<FamilyRelation>>() {
            @Override
            public void onResponse(@NonNull Call<List<FamilyRelation>> call, @NonNull Response<List<FamilyRelation>> response) {
                Log.d(TAG, "onResponse: FamilyRelations " + response);
                List<FamilyRelation> policy = response.body();
                SpecialityMutableLiveData.setValue(policy);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<FamilyRelation>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: FamilyRelations" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return SpecialityMutableLiveData;
    }

    LiveData<List<Speciality>> getQualificationLive(final Context context) {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllQualifications(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(@NonNull Call<List<Speciality>> call, @NonNull Response<List<Speciality>> response) {
                Log.d(TAG, "onResponse: Speciality " + response);
                List<Speciality> policy = response.body();
                SpecialityMutableLiveData.setValue(policy);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Speciality>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Speciality" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return SpecialityMutableLiveData;
    }

    LiveData<List<Speciality>> getNationalitiesLive(final Context context) {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllNationalities(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(@NonNull Call<List<Speciality>> call, @NonNull Response<List<Speciality>> response) {
                Log.d(TAG, "onResponse: Speciality " + response);
                List<Speciality> policy = response.body();
                SpecialityMutableLiveData.setValue(policy);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Speciality>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Speciality" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return SpecialityMutableLiveData;
    }

    LiveData<List<Speciality>> getLanguagesLive(final Context context) {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllLanguages(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(@NonNull Call<List<Speciality>> call, @NonNull Response<List<Speciality>> response) {
                Log.d(TAG, "onResponse: Speciality " + response);
                List<Speciality> policy = response.body();
                SpecialityMutableLiveData.setValue(policy);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Speciality>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Speciality" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return SpecialityMutableLiveData;
    }

    LiveData<List<Speciality>> getFacilityTypeLive(final Context context) {
        final MutableLiveData<List<Speciality>> SpecialityMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllFacilityType(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(@NonNull Call<List<Speciality>> call, @NonNull Response<List<Speciality>> response) {
                Log.d(TAG, "onResponse: FacilityTypes " + response);
                List<Speciality> policy = response.body();
                SpecialityMutableLiveData.setValue(policy);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Speciality>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: FacilityTypes" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return SpecialityMutableLiveData;
    }

    ///////////////////////////////////////////////////////////////////////

    // More Tab Activities GETS
    LiveData<List<MyAboutUs>> getAboutUsLive(final Context context) {
        final MutableLiveData<List<MyAboutUs>> myAboutUsMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAboutUs(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<MyAboutUs>>() {
            @Override
            public void onResponse(@NonNull Call<List<MyAboutUs>> call, @NonNull Response<List<MyAboutUs>> response) {
                Log.d(TAG, "onResponse: About US " + response);
                List<MyAboutUs> myAboutUs = response.body();
                myAboutUsMutableLiveData.setValue(myAboutUs);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MyAboutUs>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: About US " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return myAboutUsMutableLiveData;
    }

    LiveData<List<MyAboutUs>> getPolicyLive(final Context context) {
        final MutableLiveData<List<MyAboutUs>> myAboutUsMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getPolicy(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<MyAboutUs>>() {
            @Override
            public void onResponse(@NonNull Call<List<MyAboutUs>> call, @NonNull Response<List<MyAboutUs>> response) {
                Log.d(TAG, "onResponse: Policy " + response);
                List<MyAboutUs> policy = response.body();
                myAboutUsMutableLiveData.setValue(policy);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MyAboutUs>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Policy" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return myAboutUsMutableLiveData;
    }

    LiveData<List<MyAboutUs>> getSocialAccounts(final Context context) {

        final MutableLiveData<List<MyAboutUs>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSocialAccounts().enqueue(new Callback<List<MyAboutUs>>() {
            @Override
            public void onResponse(@NonNull Call<List<MyAboutUs>> call, @NonNull Response<List<MyAboutUs>> response) {
                Log.d(TAG, "onResponse: Accounts " + response);
                List<MyAboutUs> accounts = response.body();
                listMutableLiveData.setValue(accounts);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MyAboutUs>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Accounts" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return listMutableLiveData;

    }

    LiveData<List<MyNotification>> getNotifications(final Context context) {

        final MutableLiveData<List<MyNotification>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllNotifications(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<MyNotification>>() {
            @Override
            public void onResponse(@NonNull Call<List<MyNotification>> call, @NonNull Response<List<MyNotification>> response) {
                Log.d(TAG, "onResponse: MyNotification " + response);
                List<MyNotification> notifications = response.body();
                listMutableLiveData.setValue(notifications);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MyNotification>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: MyNotification" + t.getMessage());

            }
        });

        return listMutableLiveData;

    }

    LiveData<UserData> getUserAccountData(int id, final Context context) {

        final MutableLiveData<UserData> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.userAccountData(id, SaveSharedPreference.getLanguage(context)).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                Log.d(TAG, "onResponse: UserAccountData " + response);
                UserData userData = response.body();
                listMutableLiveData.setValue(userData);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: UserAccountData" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    LiveData<List<FamilyMember>> getMyFamilyMembers(int id, final Context context) {

        final MutableLiveData<List<FamilyMember>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getMyFamilyMembers(id).enqueue(new Callback<List<FamilyMember>>() {
            @Override
            public void onResponse(@NonNull Call<List<FamilyMember>> call, @NonNull Response<List<FamilyMember>> response) {
                Log.d(TAG, "onResponse: AllFamilyMembers " + response);
                List<FamilyMember> familyMembers = response.body();
                listMutableLiveData.setValue(familyMembers);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<FamilyMember>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: AllFamilyMembers" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return listMutableLiveData;

    }

    ///////////////////////////////////////////////////////////////////////

    // Promotion Tab
    LiveData<List<Promotion>> getAllPromotions(final Context context) {

        final MutableLiveData<List<Promotion>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getAllPromotions(SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<Promotion>>() {
            @Override
            public void onResponse(@NonNull Call<List<Promotion>> call, @NonNull Response<List<Promotion>> response) {
                Log.d(TAG, "onResponse: Promotion " + response);
                List<Promotion> promotions = response.body();
                listMutableLiveData.setValue(promotions);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Promotion>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Promotion" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return listMutableLiveData;
    }

    ///////////////////////////////////////////////////////////////////////

    // Favourites
    LiveData<List<FavouriteDoctor>> getMyFavDoctors(int id, final Context context) {

        final MutableLiveData<List<FavouriteDoctor>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getMyFavDoctors(id, SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<FavouriteDoctor>>() {
            @Override
            public void onResponse(@NonNull Call<List<FavouriteDoctor>> call, @NonNull Response<List<FavouriteDoctor>> response) {
                Log.d(TAG, "onResponse: MyFavDoctors " + response);
                List<FavouriteDoctor> familyMembers = response.body();
                listMutableLiveData.setValue(familyMembers);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(@NonNull Call<List<FavouriteDoctor>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: MyFavDoctors" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return listMutableLiveData;

    }

    LiveData<List<FacilityResult>> getMyFavFacilities(int id, final Context context) {

        final MutableLiveData<List<FacilityResult>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getMyFavFacilities(id, SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<FacilityResult>>() {
            @Override
            public void onResponse(@NonNull Call<List<FacilityResult>> call, @NonNull Response<List<FacilityResult>> response) {
                Log.d(TAG, "onResponse: MyFavDoctors " + response);
                List<FacilityResult> facilityResults = response.body();
                listMutableLiveData.setValue(facilityResults);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<FacilityResult>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: MyFavDoctors" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return listMutableLiveData;

    }

    ///////////////////////////////////////////////////////////////////////

    // Appointment Tab
    LiveData<List<Appointment>> getMyAppointments(int userId, int statusId, final Context context) {

        final MutableLiveData<List<Appointment>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getMyAppointments(userId, statusId, SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(@NonNull Call<List<Appointment>> call, @NonNull Response<List<Appointment>> response) {
                Log.d(TAG, "onResponse: Appointments " + response);
                List<Appointment> appointments = response.body();
                listMutableLiveData.setValue(appointments);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Appointment>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Appointments" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return listMutableLiveData;

    }

    LiveData<Appointment> getSpecificAppointment(int appointmentId, final Context context) {
        final MutableLiveData<Appointment> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSpecificApointment(appointmentId, SaveSharedPreference.getLanguage(context)).enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(@NonNull Call<Appointment> call, @NonNull Response<Appointment> response) {
                Log.d(TAG, "onResponse: SpecificAppointment " + response);
                Appointment appointment = response.body();
                listMutableLiveData.setValue(appointment);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Appointment> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: SpecificAppointment" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return listMutableLiveData;
    }

    ///////////////////////////////////////////////////////////////////////
    // Details
    LiveData<List<DoctorResult>> getSpecificDoctorData(int doctorId, final Context context) {

        final MutableLiveData<List<DoctorResult>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSpecificDoctorData(doctorId, SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<DoctorResult>>() {
            @Override
            public void onResponse(@NonNull Call<List<DoctorResult>> call, @NonNull Response<List<DoctorResult>> response) {
                Log.d(TAG, "onResponse: SpecificDoctorResultData " + response);
                List<DoctorResult> favouriteDoctor = response.body();
                listMutableLiveData.setValue(favouriteDoctor);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DoctorResult>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: SpecificDoctorResultData" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    LiveData<FacilityResult> getSpecificFacilityData(int facilityId, int userId, final Context context) {

        final MutableLiveData<FacilityResult> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSpecificFacilityData(facilityId, userId, SaveSharedPreference.getLanguage(context)).enqueue(new Callback<FacilityResult>() {
            @Override
            public void onResponse(@NonNull Call<FacilityResult> call, @NonNull Response<FacilityResult> response) {
                Log.d(TAG, "onResponse: SpecificFacilityResultData " + response);
                FacilityResult facilityResult = response.body();
                listMutableLiveData.setValue(facilityResult);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FacilityResult> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: SpecificFacilityResultData" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    ///////////////////////////////////////////////////////////////////////
    // Search Results
    LiveData<List<DoctorResult>> getSearchForDoctorResults(
            int specialityId,
            String fromDate,
            String toDate,
            Integer areaId,
            Integer insuranceId,
            Integer qualificationId,
            Integer languageId,
            Integer nationalityId,
            Boolean gender,
            final Context context) {

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
                nationalityId,
                gender,
                SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<DoctorResult>>() {
            @Override
            public void onResponse(@NonNull Call<List<DoctorResult>> call, @NonNull Response<List<DoctorResult>> response) {
                Log.d(TAG, "onResponse: SearchForDoctorResultData " + response);
                List<DoctorResult> doctorResults = response.body();
                listMutableLiveData.setValue(doctorResults);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DoctorResult>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure:SearchForDoctorResultData" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    LiveData<List<FacilityResult>> getSearchForFacilityResults(
            int specialityId,
            Integer areaId,
            Integer insuranceId,
            Integer facilityTypeId,
            final Context context) {

        final MutableLiveData<List<FacilityResult>> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.getSearchForFacilityResult(
                specialityId,
                areaId,
                insuranceId,
                facilityTypeId,
                SaveSharedPreference.getLanguage(context)).enqueue(new Callback<List<FacilityResult>>() {
            @Override
            public void onResponse(@NonNull Call<List<FacilityResult>> call, @NonNull Response<List<FacilityResult>> response) {
                Log.d(TAG, "onResponse: SearchForFacilityResultData " + response);
                List<FacilityResult> facilityResult = response.body();
                listMutableLiveData.setValue(facilityResult);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<FacilityResult>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure:SearchForFacilityResultData" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // POSTS :)

    LiveData<UserData> userLogin(HashMap<String, String> fields, final Context context) {

        final MutableLiveData<UserData> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.userLogin(fields).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                Log.d(TAG, "onResponse: UserData " + response);
                UserData userData = response.body();
                listMutableLiveData.setValue(userData);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: UserData" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        return listMutableLiveData;
    }

    LiveData<UserDataToUpload> editUserData(UserDataToUpload user, final Context context) {
        final MutableLiveData<UserDataToUpload> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.editUserProfile(user).enqueue(new Callback<UserDataToUpload>() {
            @Override
            public void onResponse(@NonNull Call<UserDataToUpload> call, @NonNull Response<UserDataToUpload> response) {
                Log.d(TAG, "onResponse: EditUserData " + response);
                UserDataToUpload userData = response.body();
                listMutableLiveData.setValue(userData);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDataToUpload> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: EditUserData" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    LiveData<UserDataToUpload> addNewUser(UserDataToUpload user, final Context context) {
        final MutableLiveData<UserDataToUpload> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.addNewUser(user).enqueue(new Callback<UserDataToUpload>() {
            @Override
            public void onResponse(@NonNull Call<UserDataToUpload> call, @NonNull Response<UserDataToUpload> response) {
                Log.d(TAG, "onResponse: NewUserData " + response);
                UserDataToUpload userData = response.body();
                listMutableLiveData.setValue(userData);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDataToUpload> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: NewUserData" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    void addEditFamilyMemberData(FamilyMemberToUpload familyMember, final Context context) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.addEditFamilyMember(familyMember).enqueue(new Callback<FamilyMemberToUpload>() {
            @Override
            public void onResponse(@NonNull Call<FamilyMemberToUpload> call, @NonNull Response<FamilyMemberToUpload> response) {
                Log.d(TAG, "onResponse: FamilyMemberToUpload " + response);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FamilyMemberToUpload> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: FamilyMemberToUpload" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void postAdvice(Advice advice, final Context context) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.postAdvice(advice).enqueue(new Callback<Advice>() {
            @Override
            public void onResponse(@NonNull Call<Advice> call, @NonNull Response<Advice> response) {
                Log.d(TAG, "onResponse: Advice " + response);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Advice> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Advice" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void logOut(UserToken token, final Context context) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.logOut(token).enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(@NonNull Call<UserToken> call, @NonNull Response<UserToken> response) {
                Log.d(TAG, "onResponse: Logout " + response);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserToken> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: Logout" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    LiveData<PasswordToken> forgetPassword(PasswordToken token, final Context context) {
        final MutableLiveData<PasswordToken> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.forgetPassword(token).enqueue(new Callback<PasswordToken>() {
            @Override
            public void onResponse(@NonNull Call<PasswordToken> call, @NonNull Response<PasswordToken> response) {
                Log.d(TAG, "onResponse: ForgotPassword " + response);
                PasswordToken token = response.body();
                listMutableLiveData.setValue(token);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PasswordToken> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: ForgotPassword" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    LiveData<PasswordToken> resetPassword(PasswordToken token, final Context context) {
        final MutableLiveData<PasswordToken> listMutableLiveData = new MutableLiveData<>();
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.resetPassword(token).enqueue(new Callback<PasswordToken>() {
            @Override
            public void onResponse(@NonNull Call<PasswordToken> call, @NonNull Response<PasswordToken> response) {
                Log.d(TAG, "onResponse: ResetPassword " + response);
                PasswordToken token = response.body();
                listMutableLiveData.setValue(token);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PasswordToken> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: ResetPassword" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    void setDoctorFavState(NewFavDoctor doctor, final Context context) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.setFavDoctorState(doctor).enqueue(new Callback<NewFavDoctor>() {
            @Override
            public void onResponse(@NonNull Call<NewFavDoctor> call, @NonNull Response<NewFavDoctor> response) {
                Log.d(TAG, "onResponse: SetDoctorFavState " + response);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewFavDoctor> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: SetDoctorFavState" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setFacilityFavState(NewFavFacility facility, final Context context) {
        final DocApi docApi = RetrofitClass.getDocApi();
        docApi.setFavFacilityState(facility).enqueue(new Callback<NewFavFacility>() {
            @Override
            public void onResponse(@NonNull Call<NewFavFacility> call, @NonNull Response<NewFavFacility> response) {
                Log.d(TAG, "onResponse: SetFacilityFavState " + response);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewFavFacility> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: SetFacilityFavState" + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    LiveData<String> uploadImage(MultipartBody.Part file, RequestBody description, final Context context) {
        final MutableLiveData<String> listMutableLiveData = new MutableLiveData<>();
        DocApi docApi = RetrofitClass.getDocApi();
        docApi.uploadImage(file, description).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: imageUrlToUpload :: " + response);
                try {
                    if (response.body() != null) {
                        listMutableLiveData.setValue(response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: imageUrlToUpload :: " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    LiveData<Appointment> requestAppointment(AppointmentToUpload appointmentToUpload, final Context context) {
        final MutableLiveData<Appointment> listMutableLiveData = new MutableLiveData<>();
        DocApi docApi = RetrofitClass.getDocApi();
        docApi.requestAppointment(appointmentToUpload).enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(@NonNull Call<Appointment> call, @NonNull Response<Appointment> response) {
                Log.d(TAG, "onResponse: RequestAppointment :: " + response);
                listMutableLiveData.setValue(response.body());
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Appointment> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: RequestAppointment :: " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return listMutableLiveData;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // DELETES :)

    void deleteAppointment(int appointmentId, final Context context) {
        DocApi docApi = RetrofitClass.getDocApi();
        docApi.deleteAppointment(appointmentId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.d(TAG, "onResponse: DeleteAppointment :: " + response);
                if (response.code() != 200) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: DeleteAppointment :: " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}