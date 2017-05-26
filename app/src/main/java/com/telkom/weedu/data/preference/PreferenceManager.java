package com.telkom.weedu.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.data.api.model.User;
import com.telkom.weedu.di.ApplicationContext;
import com.telkom.weedu.di.PreferenceInfo;

import javax.inject.Inject;

/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose : Application preference class
 */

public class PreferenceManager implements IPreferenceManager {

    //    put all preference keys here

    private static final String KEY_PREF_FIRST_NAME = "PREF_FIRST_NAME";
    private static final String KEY_PREF_LAST_NAME = "PREF_LAST_NAME";
    private static final String KEY_USER_ID = "USER_ID";
    private static final String KEY_PREF_BIRTHDAY = "PREF_BIRTHDAY";
    private static final String KEY_PREF_PHONE_NUMBER = "PREF_PHONE_NUMBER";
    private static final String KEY_EMAIL = "PREF_EMAIL";
    private static final String KEY_EDUMAIL = "PREF_EDUMAIL";
    private static final String KEY_USERNAME = "PREF_USERNAME";
    private static final String KEY_PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static final String KEY_EDUMAIL_TOKEN = "EDUMAIL_TOKEN";

    private static final String KEY_IS_FIRST_TIME = "IS_FIRST_TIME";


    private SharedPreferences mPreferences;

    @Inject
    public PreferenceManager(@ApplicationContext Context context,
                             @PreferenceInfo String prefName) {
        mPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    public void setAccessToken(String accessToken) {
        mPreferences.edit().putString(KEY_PREF_ACCESS_TOKEN, accessToken).apply();
    }

    public String getAccessToken() {
        return mPreferences.getString(KEY_PREF_ACCESS_TOKEN, "");
    }

    public void setFirstName(String firstName) {
        mPreferences.edit().putString(KEY_PREF_FIRST_NAME, firstName).apply();
    }

    public String getFirstName() {
        return mPreferences.getString(KEY_PREF_FIRST_NAME, "");
    }

    public void setLastName(String lastName) {
        mPreferences.edit().putString(KEY_PREF_LAST_NAME, lastName).apply();
    }

    public String getLastName() {
        return mPreferences.getString(KEY_PREF_LAST_NAME, "");
    }

    public void setUserId(int userId) {
        mPreferences.edit().putInt(KEY_USER_ID, userId).apply();
    }

    public int getUserId() {
        return mPreferences.getInt(KEY_USER_ID, 0);
    }

    public void setBirthday(String birthday) {
        mPreferences.edit().putString(KEY_PREF_BIRTHDAY, birthday).apply();
    }

    public String getBirthday() {
        return mPreferences.getString(KEY_PREF_BIRTHDAY, "");
    }

    public void setPhoneNumber(String phoneNo) {
        mPreferences.edit().putString(KEY_PREF_PHONE_NUMBER, phoneNo).apply();
    }

    public String getPhoneNumber() {
        return mPreferences.getString(KEY_PREF_PHONE_NUMBER, "");
    }

    public String getEmail() {
        return mPreferences.getString(KEY_EMAIL, "");
    }

    public void setEmail(String email) {
        mPreferences.edit().putString(KEY_EMAIL, email).apply();
    }

    public String getEdumail() {
        return mPreferences.getString(KEY_EDUMAIL, "");
    }

    public void setEdumail(String edumail) {
        mPreferences.edit().putString(KEY_EDUMAIL, edumail).apply();
    }

    public String getUsername() {
        return mPreferences.getString(KEY_USERNAME, "");
    }

    public void setUsername(String username) {
        mPreferences.edit().putString(KEY_USERNAME, username).apply();
    }

    public void setEdumailToken(String edumailToken) {
        mPreferences.edit().putString(KEY_EDUMAIL_TOKEN, edumailToken).apply();
    }

    public String getEdumailToken() {
        return mPreferences.getString(KEY_EDUMAIL_TOKEN, "");
    }

    @Override
    public void setFirstTime(boolean firstTime) {
        mPreferences.edit().putBoolean(KEY_IS_FIRST_TIME, firstTime).apply();
    }

    @Override
    public boolean isFirstTime() {
        return mPreferences.getBoolean(KEY_IS_FIRST_TIME, true);
    }

    @Override
    public void saveUserPreference(User user) {
        setAccessToken(user.getAccessToken());
        setEdumailToken(user.getEdumailToken());
        setFirstName(user.getProfile().getFirstName());
        setLastName(user.getProfile().getLastName());
        setBirthday(user.getProfile().getBirthday());
        setPhoneNumber(user.getProfile().getPhoneNo());
        setUserId(user.getUserId());
        setEmail(user.getProfile().getEmail());
        setEdumail(user.getProfile().getEdumail());
        setUsername(user.getProfile().getUsername());
    }

    @Override
    public void updateProfile(Profile profile) {
        setFirstName(profile.getFirstName());
        setLastName(profile.getLastName());
        setBirthday(profile.getBirthday());
        setPhoneNumber(profile.getPhoneNo());
    }

    @Override
    public User getUserPreference() {
        User user = new User();
        Profile profile = new Profile();

        profile.setFirstName(getFirstName());
        profile.setLastName(getLastName());
        profile.setBirthday(getBirthday());
        profile.setPhoneNo(getPhoneNumber());
        profile.setUsername(getUsername());
        profile.setEdumail(getEdumail());
        profile.setEmail(getEmail());

        user.setUserId(getUserId());
        user.setAccessToken(getAccessToken());
        user.setEdumailToken(getEdumailToken());
        user.setProfile(profile);

        return user;
    }


    @Override
    public Profile getUserProfile() {
        Profile profile = new Profile();

        profile.setFirstName(getFirstName());
        profile.setLastName(getLastName());
        profile.setBirthday(getBirthday());
        profile.setPhoneNo(getPhoneNumber());
        profile.setUsername(getUsername());
        profile.setEdumail(getEdumail());
        profile.setEmail(getEmail());

        return profile;
    }

    @Override
    public void clearUserPreference() {
        mPreferences.edit().clear().apply();
        setFirstTime(false);
    }
}
