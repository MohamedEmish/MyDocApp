package com.yackeenSolution.mydocapp.Data;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.app.Application;

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
    public LiveData<List<Speciality>> getSpecialities() {
        return retrofitClass.getSpecialityLive();
    }

    public LiveData<List<Insurance>> getMyInsuranceList() {
        return retrofitClass.getInsuranceLive();
    }

    public LiveData<List<MyArea>> getMyAreaList() {
        return retrofitClass.getAreaLive();
    }

    public LiveData<List<FamilyRelation>> getFamilyRelations() {
        return retrofitClass.getFamilyRelationsLive();
    }

    public LiveData<List<Speciality>> getQualifications() {
        return retrofitClass.getQualificationLive();
    }

    public LiveData<List<Speciality>> getNationalities() {
        return retrofitClass.getNationalitiesLive();
    }

    public LiveData<List<Speciality>> getLanguages() {
        return retrofitClass.getLanguagesLive();
    }

    public LiveData<List<Speciality>> getMyFacilityTypes() {
        return retrofitClass.getFacilityTypeLive();
    }


    ///////////////////////////////////////////////////////////////////////

    // More Tab Activities GETS
    public LiveData<List<MyAboutUs>> getMyAboutUsLive() {
        return retrofitClass.getAboutUsLive();
    }

    public LiveData<List<MyAboutUs>> getMyPolicyLiveData() {
        return retrofitClass.getPolicyLive();
    }

    public LiveData<List<MyAboutUs>> getSocialAccounts() {
        return retrofitClass.getSocialAccounts();
    }

    public LiveData<List<MyNotification>> getNotificationsList() {
        return retrofitClass.getNotifications();
    }

    public LiveData<UserData> getUserAccountData(int id) {
        return retrofitClass.getUserAccountData(id);
    }

    public LiveData<List<FamilyMember>> getMyFamilyMembersList(int id) {
        return retrofitClass.getMyFamilyMembers(id);
    }

    ///////////////////////////////////////////////////////////////////////

    // Promotion Tab
    public LiveData<List<Promotion>> getAllPromotionList() {
        return retrofitClass.getAllPromotions();
    }

    ///////////////////////////////////////////////////////////////////////

    // Favourites
    public LiveData<List<FavouriteDoctor>> getMyFavDoctorsList(int id) {
        return retrofitClass.getMyFavDoctors(id);
    }

    public LiveData<List<FacilityResult>> getMyFavFacilitiesList(int id) {
        return retrofitClass.getMyFavFacilities(id);
    }


    ///////////////////////////////////////////////////////////////////////

    // Appointments Tab
    public LiveData<List<Appointment>> getMyAppointments(int userId, int statusId) {
        return retrofitClass.getMyAppointments(userId, statusId);
    }

    public LiveData<Appointment> getSpecificAppointment(int appointmentId) {
        return retrofitClass.getSpecificAppointment(appointmentId);
    }

    ///////////////////////////////////////////////////////////////////////

    // Details
    public LiveData<List<DoctorResult>> getSpecificDoctorData(int doctorId) {
        return retrofitClass.getSpecificDoctorData(doctorId);
    }

    public LiveData<FacilityResult> getSpecificFacilityData(int facilityId, int userId) {
        return retrofitClass.getSpecificFacilityData(facilityId, userId);
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
        return retrofitClass.getSearchForDoctorResults(
                specialityId,
                fromDate,
                toDate,
                areaId,
                insuranceId,
                qualificationId,
                languageId,
                nationalityId,
                gender
        );
    }

    public LiveData<List<FacilityResult>> getSearchForFacilityResults(
            int specialityId,
            Integer areaId,
            Integer insuranceId,
            Integer facilityTypeId) {
        return retrofitClass.getSearchForFacilityResults(
                specialityId,
                areaId,
                insuranceId,
                facilityTypeId
        );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // POSTS :)


    public LiveData<UserData> userLogin(HashMap<String, String> fields) {
        return retrofitClass.userLogin(fields);
    }

    public LiveData<String> uploadedImageUrlString(MultipartBody.Part file, RequestBody description) {
        return retrofitClass.uploadImage(file, description);
    }

    public LiveData<UserDataToUpload> editUserData(UserDataToUpload user) {
        return retrofitClass.editUserData(user);
    }

    public LiveData<UserDataToUpload> addNewUser(UserDataToUpload user) {
        return retrofitClass.addNewUser(user);
    }

    public void addEditFamilyMember(FamilyMemberToUpload familyMember) {
        retrofitClass.addEditFamilyMemberData(familyMember);
    }

    public void postAdvice(Advice advice) {
        retrofitClass.postAdvice(advice);
    }

    public void logout(UserToken token) {
        retrofitClass.logOut(token);
    }

    public void setDoctorFavState(NewFavDoctor doctor) {
        retrofitClass.setDoctorFavState(doctor);
    }

    public void setFacilityFavState(NewFavFacility facility) {
        retrofitClass.setFacilityFavState(facility);
    }

    public LiveData<PasswordToken> forgetPassword(PasswordToken token) {
        return retrofitClass.forgetPassword(token);
    }

    public LiveData<PasswordToken> resetPassword(PasswordToken token) {
        return retrofitClass.resetPassword(token);
    }

    public LiveData<Appointment> requestAppointment(AppointmentToUpload appointment) {
        return retrofitClass.requestAppointment(appointment);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // POSTS :)
    public void deleteAppointment(int appointmentId) {
        retrofitClass.deleteAppointment(appointmentId);
    }

}
