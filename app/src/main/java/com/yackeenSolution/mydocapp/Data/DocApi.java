package com.yackeenSolution.mydocapp.Data;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

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
import com.yackeenSolution.mydocapp.Objects.SmallAppointment;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.Objects.UserData;
import com.yackeenSolution.mydocapp.Objects.UserDataToUpload;
import com.yackeenSolution.mydocapp.Objects.UserToken;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface DocApi {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //GETS

    // Spinners Data
    @GET("api/Areas/GetAll")
    Call<List<MyArea>> getAllAreas(@Query("lang") String language);

    @GET("api/Collection/GetAllInsuranceCompanies")
    Call<List<Insurance>> getAllInsurances(@Query("lang") String language);

    @GET("api/Specialities/GetAll")
    Call<List<Speciality>> getAllSpecialities(@Query("lang") String language);

    @GET("api/Collection/GetAllRelations")
    Call<List<FamilyRelation>> getAllRelations(@Query("lang") String language);

    @GET("api/Collection/GetAllQualifications")
    Call<List<Speciality>> getAllQualifications(@Query("lang") String language);

    @GET("api/Collection/GetAllNationalities")
    Call<List<Speciality>> getAllNationalities(@Query("lang") String language);

    @GET("api/Collection/GetAllLanguages")
    Call<List<Speciality>> getAllLanguages(@Query("lang") String language);

    @GET("api/Collection/GetAllFacilityTypes")
    Call<List<Speciality>> getAllFacilityType(@Query("lang") String language);

    ///////////////////////////////////////////////////////////////////////

    // More Tab Activities GETS
    @GET("api/AboutUs/GetAboutUs")
    Call<List<MyAboutUs>> getAboutUs(@Query("lang") String language);

    @GET("api/AboutUs/GetPolicy")
    Call<List<MyAboutUs>> getPolicy(@Query("lang") String language);

    @GET("api/AboutUs/GetSocialAccounts")
    Call<List<MyAboutUs>> getSocialAccounts();

    @GET("api/User/GetAccount")
    Call<UserData> userAccountData(@Query("UserId") int id,
                                   @Query("Lang") String language);

    @GET("api/Family/GetMyFamily")
    Call<List<FamilyMember>> getMyFamilyMembers(@Query("UserId") int id);

    @GET("api/Notifications/GetAll")
    Call<List<MyNotification>> getAllNotifications(@Query("lang") String language);

    ///////////////////////////////////////////////////////////////////////

    // Promotion Tab
    @GET("api/Promotions/GetAll")
    Call<List<Promotion>> getAllPromotions(@Query("lang") String language);

    ///////////////////////////////////////////////////////////////////////

    // Favourites
    @GET("api/Doctors/GetFavoriteDoctors")
    Call<List<FavouriteDoctor>> getMyFavDoctors(@Query("UserId") int id,
                                                @Query("Lang") String language);

    @GET("api/Facilities/GetFavoriteFacilities")
    Call<List<FacilityResult>> getMyFavFacilities(@Query("UserId") int id,
                                                  @Query("Lang") String language);

    ///////////////////////////////////////////////////////////////////////

    // Appointment Tab
    @GET("api/Appointments/GetMyAppointments")
    Call<List<Appointment>> getMyAppointments(
            @Query("UserId") int userId,
            @Query("StatusId") int statusId,
            @Query("Lang") String language
    );

    @GET("api/Appointments/GetDoctorAppointment")
    Call<Appointment> getSpecificApointment(@Query("AppointmentId") int appointmentId,
                                            @Query("Lang") String language);

    ///////////////////////////////////////////////////////////////////////

    // Details
    @GET("api/Doctors/GetDoctorInfo")
    Call<List<DoctorResult>> getSpecificDoctorData(@Query("DoctorId") int doctorId,
                                                   @Query("Lang") String language);

    @GET("api/Facilities/GetFacilityDetails")
    Call<FacilityResult> getSpecificFacilityData(
            @Query("FacilityId") int facilityId,
            @Query("UserId") int userId,
            @Query("Lang") String language
    );

    ///////////////////////////////////////////////////////////////////////

    // Search Results
    @GET("api/Doctors/GetAll")
    Call<List<DoctorResult>> getSearchForDoctorResult(
            @Query("SpecialtyId") int id,
            @Query("fromDate") String fromDate,
            @Query("toDate") String toDate,
            @Query("AreaId") Integer areaId,
            @Query("InsuranceId") Integer insuranceId,
            @Query("QualificationId") Integer qualificationId,
            @Query("LanguageId") Integer languageId,
            @Query("NationalityId") Integer nationalityId,
            @Query("gender") Boolean genderState,
            @Query("Lang") String language

    );

    @GET("api/Facilities/GetAll")
    Call<List<FacilityResult>> getSearchForFacilityResult(
            @Query("SpecialtyId") int id,
            @Query("AreaId") Integer areaId,
            @Query("InsuranceId") Integer insuranceId,
            @Query("FacilityTypeId") Integer facilityTypeId,
            @Query("Lang") String language
    );

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // POSTS :)

    @POST("api/AboutUs/ContactUs")
    Call<Advice> postAdvice(@Body Advice advice);

    @POST("api/User/Login")
    Call<UserData> userLogin(@Body HashMap<String, String> user);

    @POST("api/User/EditProfile")
    Call<UserDataToUpload> editUserProfile(@Body UserDataToUpload user);

    @POST("api/Family/AddEditFamily")
    Call<FamilyMemberToUpload> addEditFamilyMember(@Body FamilyMemberToUpload familyMember);

    @Multipart
    @POST("api/Collection/UploadImage")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file, @Part("name") RequestBody requestBody);

    @POST("api/Doctors/FavoriteDoctor")
    Call<NewFavDoctor> setFavDoctorState(@Body NewFavDoctor doctor);

    @POST("api/Facilities/FavoriteFacility")
    Call<NewFavFacility> setFavFacilityState(@Body NewFavFacility facility);

    @POST("api/User/Register")
    Call<UserDataToUpload> addNewUser(@Body UserDataToUpload user);

    @POST("api/User/LogOut")
    Call<UserToken> logOut(@Body UserToken token);

    @POST("api/User/ForgetPassword")
    Call<PasswordToken> forgetPassword(@Body PasswordToken token);

    @POST("api/User/ResetPassword")
    Call<PasswordToken> resetPassword(@Body PasswordToken token);

    @POST("api/Appointments/RequestAppointment")
    Call<Appointment> requestAppointment(@Body AppointmentToUpload appointment);

    @POST("api/Appointments/UpdateScheduledAppointmentStatus")
    Call<ResponseBody> deleteAppointment(@Body SmallAppointment appointment);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // PUTS :)


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // DELETES :)
}