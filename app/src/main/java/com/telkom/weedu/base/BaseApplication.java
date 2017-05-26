package com.telkom.weedu.base;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.crashlytics.android.Crashlytics;
import com.telkom.weedu.BuildConfig;
import com.telkom.weedu.R;
import com.telkom.weedu.data.mapper.edumail.Mail;
import com.telkom.weedu.di.components.ApplicationComponent;
import com.telkom.weedu.di.components.DaggerApplicationComponent;
import com.telkom.weedu.di.modules.ApplicationModule;
import com.telkom.weedu.utils.ContextProvider;

import java.util.LinkedList;

import io.fabric.sdk.android.Fabric;
import io.smooch.core.Smooch;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ghiyatshanif on 2/21/17.
 */

public class BaseApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    public LinkedList<Mail> listInbox, listSent;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
        ContextProvider.initialize(getApplicationContext());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);

        Smooch.init(BaseApplication.this, BuildConfig.SMOOCH_TOKEN);

        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("gotham-book.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent mApplicationComponent) {
        this.mApplicationComponent = mApplicationComponent;
    }
}
