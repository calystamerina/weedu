package com.telkom.weedu.di.modules;

import com.telkom.weedu.data.preference.IPreferenceManager;
import com.telkom.weedu.data.preference.PreferenceManager;
import com.telkom.weedu.di.PreferenceInfo;
import com.telkom.weedu.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@Module
public class PreferenceModule {

    @PreferenceInfo
    @Provides
    String providePrefname() {
        return AppConstants.PREF_NAME;
    }

    @Singleton
    @Provides
    IPreferenceManager providePreferenceManager(PreferenceManager preferenceManager) {
        return preferenceManager;
    }
}
