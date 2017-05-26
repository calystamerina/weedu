package com.telkom.weedu.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.telkom.weedu.event.FcmTokenRefreshEvent;

import org.greenrobot.eventbus.EventBus;


public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: service called");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onCreate: " + refreshedToken);
        EventBus.getDefault().post(new FcmTokenRefreshEvent(refreshedToken));

        stopSelf();
    }

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        EventBus.getDefault().post(new FcmTokenRefreshEvent(refreshedToken));

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);

    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }
}