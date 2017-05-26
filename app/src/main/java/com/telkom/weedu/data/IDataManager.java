package com.telkom.weedu.data;

import com.telkom.weedu.data.api.IApiManager;
import com.telkom.weedu.data.db.IDbManager;
import com.telkom.weedu.data.preference.IPreferenceManager;

/**
 * Created by ghiyatshanif on 2/28/17.
 */

public interface IDataManager extends IPreferenceManager, IApiManager, IDbManager {

//    sample functions
    void saveUserSession(String accessToken, String username);
}
