package com.yackeenSolution.mydocapp.Data;

import com.yackeenSolution.mydocapp.Objects.Advice;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DocApi {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //GETS

    // Spinners Data
    @GET("api/Areas/GetAll")
    Call<List<MyArea>> getAllAreas();

    @GET("api/Collection/GetAllInsuranceCompanies")
    Call<List<Insurance>> getAllInsurances();

    @GET("api/Specialities/GetAll")
    Call<List<Speciality>> getAllSpecialities();

    @GET("api/Collection/GetAllRelations")
    Call<List<FamilyRelation>> getAllRelations();

    ///////////////////////////////////////////////////////////////////////

    @GET("api/AboutUs/GetAboutUs")
    Call<List<MyAboutUs>> getAboutUs();

    @GET("api/AboutUs/GetPolicy")
    Call<List<MyAboutUs>> getPolicy();

    @GET("api/Promotions/GetAll")
    Call<List<Promotion>> getAllPromotions();

    @GET("api/AboutUs/GetSocialAccounts")
    Call<List<MyAboutUs>> getSocialAccounts();

    @GET("api/Notifications/GetAll")
    Call<List<MyNotification>> getAllNotifications();

    @GET("api/User/GetAccount")
    Call<UserData> userAccountData(@Query("UserId") int id);

    @GET("api/Family/GetMyFamily")
    Call<List<FamilyMember>> getMyFamilyMembers(@Query("UserId") int id);

    @GET("api/Doctors/GetFavoriteDoctors")
    Call<List<DoctorResult>> getMyFavDoctors(@Query("UserId") int id);

    @GET("api/Family/Get/{id}")
    Call<FamilyMember> getSpecificFamilyMembers(
            @Query("UserId") int id,
            @Path("id") int memberId);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // POSTS :)

    @POST("api/AboutUs/ContactUs")
    Call<Advice> postAdvice(@Body Advice advice);

    @POST("api/User/Login")
    Call<UserData> userLogin(@Body HashMap<String, String> user);

    @POST("api/User/EditProfile")
    Call<UserData> editUserProfile(@Body HashMap<String, String> user);

    @POST("api/Family/AddEditFamily")
    Call<FamilyMember> addEditFamilyMember(@Body HashMap<String, String> familyMember);
}