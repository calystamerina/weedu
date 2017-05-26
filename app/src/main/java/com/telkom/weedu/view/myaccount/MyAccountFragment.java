package com.telkom.weedu.view.myaccount;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.event.ProfileUpdatedEvent;
import com.telkom.weedu.utils.ImageUtils;
import com.telkom.weedu.view.custom.CircleImageView;
import com.telkom.weedu.view.editprofile.EditProfileActivity;
import com.telkom.weedu.view.login.LoginActivity;
import com.telkom.weedu.view.resetpassword.ResetPasswordActivity;
import com.telkom.weedu.view.topup.TopUpActivity;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import io.smooch.core.Smooch;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends BaseFragment implements MyAccountView {


    @BindView(R.id.img_avatar)
    CircleImageView imgAvatar;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_account_balance)
    TextView tvAccountBalance;
    @BindView(R.id.tv_account_point)
    TextView tvAccountPoint;
    @BindView(R.id.btn_edit_account)
    LinearLayout btnEditAccount;
    @BindView(R.id.btn_change_password)
    LinearLayout btnChangePassword;
    @BindView(R.id.btn_topup_balance)
    LinearLayout btnTopupBalance;
    @BindView(R.id.btn_redeem)
    LinearLayout btnRedeem;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    @Inject
    IMyAccountPresenter<MyAccountView> presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my_account;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        registerEventBusSubscriber(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
    }

    @Override
    protected void initAction() {
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openResetPasswordActivity();
            }
        });

        btnEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openEditProfileActivity();
            }
        });

        btnTopupBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openTopupActivity();
            }
        });

        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openRedeemActivity();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
            }
        });
    }

    @Override
    protected void initProcess() {
        presenter.getUserProfile();
    }

    public static MyAccountFragment newInstance() {

        Bundle args = new Bundle();

        MyAccountFragment fragment = new MyAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void toEditProfileActivity() {
        EditProfileActivity.start(getActivity());
    }

    @Override
    public void toResetPasswordActivity() {
        ResetPasswordActivity.start(getActivity());
    }

    @Override
    public void toTopupActivity() {
        TopUpActivity.start(getActivity());
    }

    @Override
    public void toRedeemActivity() {

    }

    @Override
    public void setProfile(Profile profile) {
        tvAccountName.setText(profile.getFirstName() + " " + profile.getLastName());
        tvEmail.setText(profile.getEdumail());
    }

    @Override
    public void showProfilePicture(String profilePicture) {
        ImageUtils.setImageUrl(getActivity(), profilePicture, imgAvatar);
    }

    @Override
    public void logoutAccount() {
        LoginActivity.start(getActivity());
        Smooch.logout();
        finishActivity();
    }

    @Subscribe
    public void onEvent(ProfileUpdatedEvent event) {
        presenter.getUserProfile();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
        unregisterEventBusSubscriber(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
