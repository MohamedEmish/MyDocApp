package com.yackeenSolution.mydocapp.Data;

import android.app.Application;

import com.yackeenSolution.mydocapp.Objects.Insurance;
import com.yackeenSolution.mydocapp.Objects.MyAboutUs;
import com.yackeenSolution.mydocapp.Objects.MyArea;
import com.yackeenSolution.mydocapp.Objects.MyNotification;
import com.yackeenSolution.mydocapp.Objects.Promotion;
import com.yackeenSolution.mydocapp.Objects.Speciality;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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


    public DataViewModel(@NonNull Application application) {
        super(application);
    }

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

    public LiveData<List<Promotion>> getAllPromotionList() {

        if (mAllPromotionList == null) {
            mAllPromotionList = retrofitClass.getAllPromotions();
        }
        return mAllPromotionList;
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

}
