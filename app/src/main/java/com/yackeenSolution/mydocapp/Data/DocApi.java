package com.yackeenSolution.mydocapp.Data;

import com.yackeenSolution.mydocapp.Objects.Advice;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.MyAboutUs;
import com.yackeenSolution.mydocapp.Objects.MyArea;
import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.Objects.Speciality;

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

    @GET("api/AboutUs/GetAboutUs")
    Call<List<MyAboutUs>> getAboutUs();

    @GET("api/AboutUs/GetPolicy")
    Call<List<MyAboutUs>> getPolicy();

    @GET("api/Specialities/GetAll")
    Call<List<Speciality>> getAllSpecialities();

    @GET("api/Areas/GetAll")
    Call<List<MyArea>> getAllAreas();

    @GET("api/Collection/GetAllInsuranceCompanies")
    Call<List<Insurance>> getAllInsurances();

    @GET("api/Promotions/GetAll")
    Call<List<Promotion>> getAllPromotions();

    @GET("api/AboutUs/GetSocialAccounts")
    Call<List<MyAboutUs>> getSocialAccounts();

    @GET("api/Notifications/GetAll")
    Call<List<MyNotification>> getAllNotifications();

    @POST("api/AboutUs/ContactUs")
    Call<Advice> postAdvice(
            @Body Advice advice
    );
}