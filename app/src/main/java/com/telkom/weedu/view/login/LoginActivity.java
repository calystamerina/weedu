package com.telkom.weedu.view.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.data.api.model.request.LoginRequest;
import com.telkom.weedu.event.FcmTokenRefreshEvent;
import com.telkom.weedu.service.MyFirebaseInstanceIdService;
import com.telkom.weedu.utils.AppMessages;
import com.telkom.weedu.utils.ValidateUtils;
import com.telkom.weedu.view.main.MainActivity;
import com.telkom.weedu.view.register.RegisterActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_login_facebook)
    Button btnLoginFacebook;
    @BindView(R.id.btn_login_twitter)
    Button btnLoginTwitter;
    @BindView(R.id.btn_login_google)
    Button btnLoginGoogle;
    @BindView(R.id.btn_login_edumail)
    Button btnLoginEdumail;
    @BindView(R.id.ln_main_login)
    LinearLayout lnMainLogin;
    @BindView(R.id.ln_register)
    LinearLayout lnRegister;
    @BindView(R.id.pb_indicator)
    ProgressBar pbIndicator;

    @Inject
    ILoginPresenter<LoginView> loginPresenter;

    private String username, password, fcm;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(LoginActivity.this);
        loginPresenter.onAttach(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initAction() {
        btnLogin.setOnClickListener(this);
        lnRegister.setOnClickListener(this);
    }

    @Override
    protected void initProcess() {

    }

    @Override
    public void showLoading() {
        btnLogin.setVisibility(View.GONE);
        pbIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        btnLogin.setVisibility(View.VISIBLE);
        pbIndicator.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity() {
        MainActivity.start(this);
        finishActivity();
    }

    @Override
    public void toRegisterActivity() {
        RegisterActivity.start(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                prepareLogin();
                break;

            case R.id.ln_register:
                loginPresenter.openRegisterActivity();
                break;
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    private void prepareLogin() {
        if (ValidateUtils.validate(AppMessages.FIELD_REQUIRED, edtPassword, edtUsername)) {
            username = edtUsername.getText().toString();
            password = edtPassword.getText().toString();

            Intent intent = new Intent(this, MyFirebaseInstanceIdService.class);
            startService(intent);
        }
    }

    private void doLogin() {
        LoginRequest loginRequest = new LoginRequest(username, password, fcm);
        loginPresenter.postLoginRequest(loginRequest);
    }


    @Subscribe
    public void onFcmTokenRefreshEvent(FcmTokenRefreshEvent event) {
        this.fcm = event.getToken();
        doLogin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
