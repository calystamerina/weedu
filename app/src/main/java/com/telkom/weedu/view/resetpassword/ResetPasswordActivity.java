package com.telkom.weedu.view.resetpassword;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.utils.AppMessages;
import com.telkom.weedu.utils.MessageFactory;
import com.telkom.weedu.utils.ValidateUtils;
import com.telkom.weedu.view.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;


public class ResetPasswordActivity extends BaseActivity implements ResetPasswordView {

    @Inject
    IResetPasswordPresenter<ResetPasswordView> presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_old_password)
    EditText edtOldPassword;
    @BindView(R.id.edt_new_password)
    EditText edtNewPassword;
    @BindView(R.id.edt_confirm_new_password)
    EditText edtConfirmNewPassword;
    @BindView(R.id.btn_reset_password)
    Button btnResetPassword;

    public static void start(Context context) {
        Intent starter = new Intent(context, ResetPasswordActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void initLib() {
        // TODO implements injection here
        getActivityComponent().inject(ResetPasswordActivity.this);
        presenter.onAttach(this);

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        setupToolbar(toolbar, getString(R.string.activity_title_change_password), true);
    }

    @Override
    protected void initAction() {
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateUtils.validate(AppMessages.FIELD_REQUIRED, edtConfirmNewPassword, edtNewPassword, edtOldPassword)) {
                    String oldPassword = edtOldPassword.getText().toString();
                    String newPassword = edtNewPassword.getText().toString();
                    String newPasswordConfirm = edtConfirmNewPassword.getText().toString();

                    if (newPassword.equals(newPasswordConfirm)) {
                        presenter.resetPassword(oldPassword, newPassword);
                    } else {
                        showToast(AppMessages.NEW_PASSWORD_NOT_IDENTICAL);
                    }

                }
            }
        });

    }

    @Override
    protected void initProcess() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showResetPasswordSuccessDialog() {
        MessageFactory.showAlertDialog(this, "Change Password Success", AppMessages.CHANGE_PASSWORD_SUCCESS, "Yes", false, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginActivity.start(ResetPasswordActivity.this);
                finishActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
