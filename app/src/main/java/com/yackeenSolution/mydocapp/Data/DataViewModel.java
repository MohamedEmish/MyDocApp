package com.yackeenSolution.mydocapp.Data;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.app.Application;
import android.content.Context;

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

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DataViewModel extends AndroidViewModel {

    private RetrofitClass retrofitClass = new RetrofitClass();

    public DataViewModel(@NonNull Application application) {
        super(application);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //GETS

    // Spinners Data
    public LiveData<List<Speciality>> getSpecialities(Context context) {
        return retrofitClass.getSpecialityLive(context);
    }

    public LiveData<List<Insurance>> getMyInsuranceList(Context context) {
        return retrofitClass.getInsuranceLive(context);
    }

    public LiveData<List<MyArea>> getMyAreaList(Context context) {
        return retrofitClass.getAreaLive(context);
    }

    public LiveData<List<FamilyRelation>> getFamilyRelations(Context context) {
        return retrofitClass.getFamilyRelationsLive(context);
    }

    public LiveData<List<Speciality>> getQualifications(Context context) {
        return retrofitClass.getQualificationLive(context);
    }

    public LiveData<List<Speciality>> getNationalities(Context context) {
        return retrofitClass.getNationalitiesLive(context);
    }

    public LiveData<List<Speciality>> getLanguages(Context context) {
        return retrofitClass.getLanguagesLive(context);
    }

    public LiveData<List<Speciality>> getMyFacilityTypes(Context context) {
        return retrofitClass.getFacilityTypeLive(context);
    }


    ///////////////////////////////////////////////////////////////////////

    // More Tab Activities GETS
    public LiveData<List<MyAboutUs>> getMyAboutUsLive(Context context) {
        return retrofitClass.getAboutUsLive(context);
    }

    public LiveData<List<MyAboutUs>> getMyPolicyLiveData(Context context) {
        return retrofitClass.getPolicyLive(context);
    }

    public LiveData<List<MyAboutUs>> getSocialAccounts(Context context) {
        return retrofitClass.getSocialAccounts(context);
    }

    public LiveData<List<MyNotification>> getNotificationsList(Context context) {
        return retrofitClass.getNotifications(context);
    }

    public LiveData<UserData> getUserAccountData(int id, Context context) {
        return retrofitClass.getUserAccountData(id, context);
    }

    public LiveData<List<FamilyMember>> getMyFamilyMembersList(int id, Context context) {
        return retrofitClass.getMyFamilyMembers(id, context);
    }

    ///////////////////////////////////////////////////////////////////////

    // Promotion Tab
    public LiveData<List<Promotion>> getAllPromotionList(Context context) {
        return retrofitClass.getAllPromotions(context);
    }

    ///////////////////////////////////////////////////////////////////////

    // Favourites
    public LiveData<List<FavouriteDoctor>> getMyFavDoctorsList(int id, Context context) {
        return retrofitClass.getMyFavDoctors(id, context);
    }

    public LiveData<List<FacilityResult>> getMyFavFacilitiesList(int id, Context context) {
        return retrofitClass.getMyFavFacilities(id, context);
    }


    ///////////////////////////////////////////////////////////////////////

    // Appointments Tab
    public LiveData<List<Appointment>> getMyAppointments(int userId, int statusId, Context context) {
        return retrofitClass.getMyAppointments(userId, statusId, context);
    }

    public LiveData<Appointment> getSpecificAppointment(int appointmentId, Context context) {
        return retrofitClass.getSpecificAppointment(appointmentId, context);
    }

    ///////////////////////////////////////////////////////////////////////

    // Details
    public LiveData<List<DoctorResult>> getSpecificDoctorData(int doctorId, Context context) {
        return retrofitClass.getSpecificDoctorData(doctorId, context);
    }

    public LiveData<FacilityResult> getSpecificFacilityData(int facilityId, int userId, Context context) {
        return retrofitClass.getSpecificFacilityData(facilityId, userId, context);
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
            Boolean gender,
            Context context) {
        return retrofitClass.getSearchForDoctorResults(
                specialityId,
                fromDate,
                toDate,
                areaId,
                insuranceId,
                qualificationId,
                languageId,
                nationalityId,
                gender,
                context
        );
    }

    public LiveData<List<FacilityResult>> getSearchForFacilityResults(
            int specialityId,
            Integer areaId,
            Integer insuranceId,
            Integer facilityTypeId,
            Context context) {
        return retrofitClass.getSearchForFacilityResults(
                specialityId,
                areaId,
                insuranceId,
                facilityTypeId,
                context
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // POSTS :)


    public LiveData<UserData> userLogin(HashMap<String, String> fields, Context context) {
        return retrofitClass.userLogin(fields, context);
    }

    public LiveData<String> uploadedImageUrlString(MultipartBody.Part file, RequestBody description, Context context) {
        return retrofitClass.uploadImage(file, description, context);
    }

    public LiveData<UserDataToUpload> editUserData(UserDataToUpload user, Context context) {
        return retrofitClass.editUserData(user, context);
    }

    public LiveData<UserDataToUpload> addNewUser(UserDataToUpload user, Context context) {
        return retrofitClass.addNewUser(user, context);
    }

    public void addEditFamilyMember(FamilyMemberToUpload familyMember, Context context) {
        retrofitClass.addEditFamilyMemberData(familyMember, context);
    }

    public void postAdvice(Advice advice, Context context) {
        retrofitClass.postAdvice(advice, context);
    }

    public void logout(UserToken token, Context context) {
        retrofitClass.logOut(token, context);
    }

    public void setDoctorFavState(NewFavDoctor doctor, Context context) {
        retrofitClass.setDoctorFavState(doctor, context);
    }

    public void setFacilityFavState(NewFavFacility facility, Context context) {
        retrofitClass.setFacilityFavState(facility, context);
    }

    public LiveData<PasswordToken> forgetPassword(PasswordToken token, Context context) {
        return retrofitClass.forgetPassword(token, context);
    }

    public LiveData<PasswordToken> resetPassword(PasswordToken token, Context context) {
        return retrofitClass.resetPassword(token, context);
    }

    public LiveData<Appointment> requestAppointment(AppointmentToUpload appointment, Context context) {
        return retrofitClass.requestAppointment(appointment, context);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // POSTS :)
    public void deleteAppointment(SmallAppointment appointment, Context context) {
        retrofitClass.deleteAppointment(appointment, context);
    }

}
