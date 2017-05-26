package com.telkom.weedu.view.splash;

import android.os.Handler;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;


/**
 * Created by ghiyatshanif on 2/23/17.
 */

public class SplashPresenter<V extends SplashView> extends BasePresenter<V> implements ISplashPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void onAttach(V view) {
        super.onAttach(view);

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                decideNextPage();
            }
        };

//        wait 3 seconds
        handler.postDelayed(r, 3000);
    }

    private void decideNextPage() {
        if (getDataManager().isFirstTime()) {
            getView().toIntroductionActivity();
        } else {
            if ("".equals(getDataManager().getUserPreference().getAccessToken())) {
                getView().toLoginActivity();
            } else {
                getView().toMainActivity();
            }
        }

        getView().finishActivity();
    }
}
