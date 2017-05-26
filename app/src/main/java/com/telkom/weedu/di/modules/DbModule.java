package com.telkom.weedu.di.modules;

import com.telkom.weedu.data.db.DbManager;
import com.telkom.weedu.data.db.IDbManager;
import com.telkom.weedu.di.DatabaseInfo;
import com.telkom.weedu.utils.AppConstants;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@Module
public class DbModule {

    @DatabaseInfo
    @Provides
    String provideDbName(){
        return AppConstants.DB_NAME;
    }

    @Provides
    IDbManager provideDbManager(DbManager dbManager){
        return dbManager;
    }
}
