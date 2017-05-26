package com.telkom.weedu.view.register;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.data.api.model.request.RegisterRequest;
import com.telkom.weedu.utils.AppMessages;
import com.telkom.weedu.utils.CommonUtils;
import com.telkom.weedu.utils.ValidateUtils;
import com.telkom.weedu.view.emailconfirmation.EmailConfirmationActivity;
import com.telkom.weedu.view.login.LoginActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;

public class RegisterActivity extends BaseActivity implements RegisterView, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.edt_fullname)
    EditText edtFullname;
    @BindView(R.id.edt_phonenumber)
    EditText edtPhonenumber;
    @BindView(R.id.edt_dateofbirth)
    EditText edtDateofbirth;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.cb_toc)
    CheckBox cbToc;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.ln_main_login)
    LinearLayout lnMainLogin;
    @BindView(R.id.ln_login)
    LinearLayout lnLogin;

    @BindColor(R.color.colorPrimary)
    int colorPrimary;

    EditText[] requiredFields;

    @Inject
    IRegisterPresenter<RegisterView> registerPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        registerPresenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initAction() {
        lnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        edtDateofbirth.setOnClickListener(this);
    }

    @Override
    protected void initProcess() {
        requiredFields = new EditText[]{edtPassword, edtDateofbirth, edtEmail, edtFullname, edtPhonenumber};
    }

    @Override
    public void toLoginActivity() {
        LoginActivity.start(this);
    }

    @Override
    public void toMainActivity() {

    }

    @Override
    public void toEmailConfirmationActivity(RegisterRequest request) {
        EmailConfirmationActivity.start(this, request);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ln_login:
                registerPresenter.openLoginActivity();
                break;

            case R.id.btn_register:
                doRegister();
                break;
            case R.id.edt_dateofbirth:
                showDatePickerDialog();
        }
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        edtDateofbirth.setText(year + "-" + String.format("%02d", monthOfYear+1) + "-" + String.format("%02d", dayOfMonth));
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RegisterActivity.class);
        context.startActivity(starter);
    }

    private void doRegister() {

        if (ValidateUtils.validate(AppMessages.FIELD_REQUIRED, requiredFields)) {
            RegisterRequest request = new RegisterRequest();

            String fullName = edtFullname.getText().toString();
            String phoneNumber = edtPhonenumber.getText().toString();
            String dateOfBirth = edtDateofbirth.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            request.setFirstName(CommonUtils.getFirstName(fullName));
            request.setLastName(CommonUtils.getLastName(fullName));
            request.setPhone(phoneNumber);
            request.setMethod("email");
            request.setEmail(email);
            request.setBirthday(dateOfBirth);
            request.setPassword(password);

            if (!validateEmail()) return;

            if (!validatePassword()) return;

            if (!cbToc.isChecked()) {
                showAlert(AppMessages.INFO_TERMS_CONDITION);
                return;
            }

            registerPresenter.registerRequest(request);
        }

    }

    private boolean validatePassword() {
        String password = edtPassword.getText().toString();
        if (CommonUtils.isPasswordStrongEnough(password)) {
            return true;
        } else {
            edtPassword.setError(AppMessages.PASSWORD_NOT_VALID);
            return false;
        }
    }

    private boolean validateEmail() {
        String email = edtEmail.getText().toString();
        if (CommonUtils.isEmailValid(email)) {
            return true;
        } else {
            edtEmail.setError(AppMessages.EMAIL_NOT_VALID);
            return false;
        }
    }


    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog;
        datePickerDialog = DatePickerDialog.newInstance(this
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setAccentColor(colorPrimary);
        datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.onDetach();
    }
}
