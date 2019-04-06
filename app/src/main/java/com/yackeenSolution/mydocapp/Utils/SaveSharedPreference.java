package com.yackeenSolution.mydocapp.Utils;

/*
   Last edit :: March 27,2019
   ALL DONE :)
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    private static final String PREF_USER_NAME = "username";
    private static final String PREF_USER_LANGUAGE = "language";
    private static final String PREF_USER_EMAIL = "emailText";
    private static final String PREF_USER_ID = "ID";
    private static final String PREF_Appointment_ID = "appointmentId";

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.apply();
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, null);
    }

    public static void clearUserName(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, null);
        editor.apply();
    }

    public static void setAppointmentId(Context ctx, String id) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_Appointment_ID, id);
        editor.apply();
    }

    public static String getAppointmentId(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_Appointment_ID, null);
    }

    public static void clearAppointmentId(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_Appointment_ID, null);
        editor.apply();
    }

    public static void setUserId(Context ctx, String id) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, id);
        editor.apply();
    }

    public static String getUserId(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, null);
    }

    public static void clearUserId(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, null);
        editor.apply();
    }

    public static void setUserEmail(Context ctx, String userEmail) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, userEmail);
        editor.apply();
    }

    public static String getUserEmail(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL, null);
    }

    public static void clearUserEmail(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, null);
        editor.apply();
    }

    public static void setLanguage(Context ctx, String lang) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_LANGUAGE, lang);
        editor.apply();
    }

    public static String getLanguage(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_LANGUAGE, "en");
    }
}