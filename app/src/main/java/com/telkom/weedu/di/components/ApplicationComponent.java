package com.telkom.weedu.di.components;

import android.app.Application;
import android.content.Context;

import com.telkom.weedu.base.BaseApplication;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.di.ApplicationContext;
import com.telkom.weedu.di.modules.ApiModule;
import com.telkom.weedu.di.modules.ApplicationModule;
import com.telkom.weedu.di.modules.DbModule;
import com.telkom.weedu.di.modules.PreferenceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@Singleton
@Component (modules = {ApplicationModule.class, ApiModule.class, DbModule.class, PreferenceModule.class})
public interface ApplicationComponent {

    void inject(BaseApplication baseApplication);

    DataManager getDataManager();


    @ApplicationContext
    Context getContext();

    Application getApplication();

}
