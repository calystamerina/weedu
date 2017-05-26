package com.telkom.weedu.data.preference;

import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.data.api.model.User;

/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose : Interface for PreferenceManager, put all corresponding getters/setters method here
 */

public interface IPreferenceManager {
    void saveUserPreference(User user);

    User getUserPreference();

    void clearUserPreference();

    void setFirstTime(boolean firstTime);

    boolean isFirstTime();

    Profile getUserProfile();

    void updateProfile(Profile profile);

}
