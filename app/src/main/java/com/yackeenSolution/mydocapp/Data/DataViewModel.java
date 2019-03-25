package com.yackeenSolution.mydocapp.Data;

import android.app.Application;

import com.yackeenSolution.mydocapp.Objects.Advice;
import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.Objects.DoctorResult;
import com.yackeenSolution.mydocapp.Objects.FamilyMemberToUpload;
import com.yackeenSolution.mydocapp.Objects.FavouriteDoctor;
import com.yackeenSolution.mydocapp.Objects.FacilityResult;
import com.yackeenSolution.mydocapp.Objects.FamilyMember;
import com.yackeenSolution.mydocapp.Objects.FamilyRelation;
import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.MyAboutUs;
import com.yackeenSolution.mydocapp.Objects.MyArea;
import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.Objects.NewFavDoctor;
import com.yackeenSolution.mydocapp.Objects.NewFavFacility;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.Objects.Speciality;
import com.yackeenSolution.mydocapp.Objects.UserData;
import com.yackeenSolution.mydocapp.Objects.UserDataToUpload;

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

        if (mySpecialityList == null) {
            mySpecialityList = retrofitClass.getSpecialityLive();
        }
        return mySpecialityList;
    }

    public LiveData<List<Insurance>> getMyInsuranceList() {

        if (myInsuranceList == null) {
            myInsuranceList = retrofitClass.getInsuranceLive();
        }
        return myInsuranceList;
    }

    public LiveData<List<MyArea>> getMyAreaList() {

        if (myAreaList == null) {
            myAreaList = retrofitClass.getAreaLive();
        }
        return myAreaList;
    }

    public LiveData<List<FamilyRelation>> getFamilyRelations() {

        if (myFamilyRelationsList == null) {
            myFamilyRelationsList = retrofitClass.getFamilyRelationsLive();
        }
        return myFamilyRelationsList;
    }

    public LiveData<List<Speciality>> getQualifications() {

        if (myQualificationsList == null) {
            myQualificationsList = retrofitClass.getQualificationLive();
        }
        return myQualificationsList;
    }

    public LiveData<List<Speciality>> getNationalities() {

        if (myNationalitiesList == null) {
            myNationalitiesList = retrofitClass.getNationalitiesLive();
        }
        return myNationalitiesList;
    }

    public LiveData<List<Speciality>> getLanguages() {

        if (myLanguagesList == null) {
            myLanguagesList = retrofitClass.getLanguagesLive();
        }
        return myLanguagesList;
    }

    public LiveData<List<Speciality>> getMyFacilityTypes() {

        if (myFacilityTypesList == null) {
            myFacilityTypesList = retrofitClass.getFacilityTypeLive();
        }
        return myFacilityTypesList;
    }



    ///////////////////////////////////////////////////////////////////////

    // More Tab Activities GETS
    public LiveData<List<MyAboutUs>> getMyAboutUsLive() {

        if (myAboutUsLiveData == null) {
            myAboutUsLiveData = retrofitClass.getAboutUsLive();
        }
        return myAboutUsLiveData;
    }

    public LiveData<List<MyAboutUs>> getMyPolicyLiveData() {

        if (myPolicyLiveData == null) {
            myPolicyLiveData = retrofitClass.getPolicyLive();
        }
        return myPolicyLiveData;
    }

    public LiveData<List<MyAboutUs>> getSocialAccounts() {

        if (mySocialAccounts == null) {
            mySocialAccounts = retrofitClass.getSocialAccounts();
        }
        return mySocialAccounts;
    }

    public LiveData<List<MyNotification>> getNotificationsList() {

        if (mNotificationsList == null) {
            mNotificationsList = retrofitClass.getNotifications();
        }
        return mNotificationsList;
    }

    public LiveData<UserData> getUserAccountData(int id) {

        if (mUserAccountData == null) {
            mUserAccountData = retrofitClass.getUserAccountData(id);
        }
        return mUserAccountData;
    }

    public LiveData<List<FamilyMember>> getMyFamilyMembersList(int id) {

        if (mMyFamilyMembersList == null) {
            mMyFamilyMembersList = retrofitClass.getMyFamilyMembers(id);
        }
        return mMyFamilyMembersList;
    }

    public LiveData<FamilyMember> getSpecificFamilyMember(int userId, int memberId) {

        if (mSpecificFamilyMember == null) {
            mSpecificFamilyMember = retrofitClass.getSpecificFamilyMember(memberId);
        }
        return mSpecificFamilyMember;
    }

    ///////////////////////////////////////////////////////////////////////

    // Promotion Tab
    public LiveData<List<Promotion>> getAllPromotionList() {

        if (mAllPromotionList == null) {
            mAllPromotionList = retrofitClass.getAllPromotions();
        }
        return mAllPromotionList;
    }

    ///////////////////////////////////////////////////////////////////////

    // Favourites
    public LiveData<List<FavouriteDoctor>> getMyFavDoctorsList(int id) {

        if (mMyFavDoctorsList == null) {
            mMyFavDoctorsList = retrofitClass.getMyFavDoctors(id);
        }
        return mMyFavDoctorsList;
    }

    public LiveData<List<FacilityResult>> getMyFavFacilitiesList(int id) {

        if (mMyFavFacilitiesList == null) {
            mMyFavFacilitiesList = retrofitClass.getMyFavFacilities(id);
        }
        return mMyFavFacilitiesList;
    }


    ///////////////////////////////////////////////////////////////////////

    // Appointments Tab
    public LiveData<List<Appointment>> getMyAppointments(int userId, int statusId) {

        if (mMyAppointments == null) {
            mMyAppointments = retrofitClass.getMyAppointments(userId, statusId);
        }
        return mMyAppointments;
    }

    ///////////////////////////////////////////////////////////////////////

    // Details
    public LiveData<List<DoctorResult>> getSpecificDoctorData(int doctorId) {

        if (mSpecificDoctorData == null) {
            mSpecificDoctorData = retrofitClass.getSpecificDoctorData(doctorId);
        }
        return mSpecificDoctorData;
    }

    public LiveData<FacilityResult> getSpecificFacilityData(int facilityId, int userId) {

        if (mSpecificFacilityData == null) {
            mSpecificFacilityData = retrofitClass.getSpecificFacilityData(facilityId, userId);
        }
        return mSpecificFacilityData;
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

        if (mDoctorSearchResultsList == null) {
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
        }
        return mDoctorSearchResultsList;
    }

    public LiveData<List<FacilityResult>> getSearchForFacilityResults(
            int specialityId,
            Integer areaId,
            Integer insuranceId,
            Integer facilityTypeId) {

        if (mFacilitySearchResultsList == null) {
            mFacilitySearchResultsList = retrofitClass.getSearchForFacilityResults(
                    specialityId,
                    areaId,
                    insuranceId,
                    facilityTypeId
            );
        }
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

    public void addEditFamilyMember(FamilyMemberToUpload familyMember) {
        retrofitClass.addEditFamilyMemberData(familyMember);
    }

    public void postAdvice(Advice advice) {
        retrofitClass.postAdvice(advice);
    }

    public void setDoctorFavState(NewFavDoctor doctor) {
        retrofitClass.setDoctorFavState(doctor);
    }

    public void setFacilityFavState(NewFavFacility facility) {
        retrofitClass.setFacilityFavState(facility);
    }

}
