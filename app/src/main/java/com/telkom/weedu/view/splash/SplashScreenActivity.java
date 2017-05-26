package com.telkom.weedu.view.splash;

import android.widget.ImageView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.utils.ImageUtils;
import com.telkom.weedu.view.intro.IntroductionActivity;
import com.telkom.weedu.view.login.LoginActivity;
import com.telkom.weedu.view.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class SplashScreenActivity extends BaseActivity implements SplashView {

    @Inject
    ISplashPresenter<SplashView> splashPresenter;

    @BindView(R.id.img_splash_logo)
    ImageView imgSplashLogo;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(SplashScreenActivity.this);
        splashPresenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        ImageUtils.setImage(SplashScreenActivity.this, R.drawable.weedu_logo, imgSplashLogo);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initProcess() {

    }

    @Override
    public void toMainActivity() {
        MainActivity.start(this);
    }

    @Override
    public void toLoginActivity() {
        LoginActivity.start(this);
    }

    @Override
    public void toIntroductionActivity() {
        IntroductionActivity.start(this);
    }
}