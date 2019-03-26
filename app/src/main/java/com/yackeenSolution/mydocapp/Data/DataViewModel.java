package com.yackeenSolution.mydocapp.Data;

import android.app.Application;

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

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DataViewModel extends AndroidViewModel {

    private RetrofitClass retrofitClass = new RetrofitClass();
    private LiveData<List<MyAboutUs>> myAboutUsLiveData;
    private LiveData<List<MyAboutUs>> myPolicyLiveData;
    private LiveData<List<Speciality>> mySpecialityList;
    private LiveData<List<Insurance>> myInsuranceList;
    private LiveData<List<MyArea>> myAreaList;
    private LiveData<List<MyAboutUs>> mySocialAccounts;
    private LiveData<List<Promotion>> mAllPromotionList;
    private LiveData<List<MyNotification>> mNotificationsList;
    private LiveData<UserData> mUserData;
    private LiveData<UserData> mUserAccountData;
    private LiveData<List<FamilyMember>> mMyFamilyMembersList;
    private LiveData<FamilyMember> mSpecificFamilyMember;
    private FamilyMember mAddEditFamilyMember;
    private LiveData<List<FamilyRelation>> myFamilyRelationsList;
    private LiveData<List<FavouriteDoctor>> mMyFavDoctorsList;
    private LiveData<List<FacilityResult>> mMyFavFacilitiesList;
    private LiveData<List<Appointment>> mMyAppointments;
    private LiveData<List<DoctorResult>> mSpecificDoctorData;
    private LiveData<FacilityResult> mSpecificFacilityData;
    private LiveData<List<DoctorResult>> mDoctorSearchResultsList;
    private LiveData<List<FacilityResult>> mFacilitySearchResultsList;
    private LiveData<List<Speciality>> myQualificationsList, myNationalitiesList, myLanguagesList, myFacilityTypesList;
    private LiveData<String> uploadedImageUrlString;


    public DataViewModel(@NonNull Application application) {
        super(application);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //GETS

    // Spinners Data
    public LiveData<List<Speciality>> getSpecialities() {
        return mySpecialityList = retrofitClass.getSpecialityLive();
    }

    public LiveData<List<Insurance>> getMyInsuranceList() {
        return myInsuranceList = retrofitClass.getInsuranceLive();
    }

    public LiveData<List<MyArea>> getMyAreaList() {
        return myAreaList = retrofitClass.getAreaLive();
    }

    public LiveData<List<FamilyRelation>> getFamilyRelations() {
        return myFamilyRelationsList = retrofitClass.getFamilyRelationsLive();
    }

    public LiveData<List<Speciality>> getQualifications() {
        return myQualificationsList = retrofitClass.getQualificationLive();
    }

    public LiveData<List<Speciality>> getNationalities() {
        return myNationalitiesList = retrofitClass.getNationalitiesLive();
    }

    public LiveData<List<Speciality>> getLanguages() {
        return myLanguagesList = retrofitClass.getLanguagesLive();
    }

    public LiveData<List<Speciality>> getMyFacilityTypes() {
        return myFacilityTypesList = retrofitClass.getFacilityTypeLive();
    }


    ///////////////////////////////////////////////////////////////////////

    // More Tab Activities GETS
    public LiveData<List<MyAboutUs>> getMyAboutUsLive() {
        return myAboutUsLiveData = retrofitClass.getAboutUsLive();
    }

    public LiveData<List<MyAboutUs>> getMyPolicyLiveData() {
        return myPolicyLiveData = retrofitClass.getPolicyLive();
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

    public LiveData<FamilyMember> getSpecificFamilyMember(int userId, int memberId) {
        return retrofitClass.getSpecificFamilyMember(memberId);
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
            mDoctorSearchResultsList = retrofitClass.getSearchForDoctorResults(
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
        return mDoctorSearchResultsList;
    }

    public LiveData<List<FacilityResult>> getSearchForFacilityResults(
            int specialityId,
            Integer areaId,
            Integer insuranceId,
            Integer facilityTypeId) {
            mFacilitySearchResultsList = retrofitClass.getSearchForFacilityResults(
                    specialityId,
                    areaId,
                    insuranceId,
                    facilityTypeId
            );
        return mFacilitySearchResultsList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // POSTS :)


    public LiveData<UserData> userLogin(HashMap<String, String> fields) {
        return mUserData = retrofitClass.userLogin(fields);
    }

    public LiveData<String> uploadedImageUrlString(MultipartBody.Part file, RequestBody description) {
        return uploadedImageUrlString = retrofitClass.uploadImage(file, description);
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

}
