package com.telkom.weedu.view.editprofile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.telkom.weedu.BuildConfig;
import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.event.ProfileUpdatedEvent;
import com.telkom.weedu.utils.CommonUtils;
import com.telkom.weedu.utils.MessageFactory;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;


public class EditProfileActivity extends BaseActivity implements EditProfileView, DatePickerDialog.OnDateSetListener {

    @Inject
    IEditProfilePresenter<EditProfileView> presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_user_name)
    EditText edtUserName;
    @BindView(R.id.edt_phone_number)
    EditText edtPhoneNumber;
    @BindView(R.id.edt_date_of_birth)
    EditText edtDateOfBirth;
    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindColor(R.color.colorPrimary)
    int colorPrimary;
    @BindView(R.id.btn_edit_profile)
    Button btnEditProfile;

    public static void start(Context context) {
        Intent starter = new Intent(context, EditProfileActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void initLib() {
        // TODO implements injection here
        getActivityComponent().inject(EditProfileActivity.this);
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        setupToolbar(toolbar, getString(R.string.activity_title_edit_account), true);
        presenter.setupProfile();
    }

    @Override
    protected void initAction() {
        edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile edited = new Profile();
                edited.setBirthday(edtDateOfBirth.getText().toString().trim());
                edited.setFirstName(CommonUtils.getFirstName(edtUserName.getText().toString()));
                edited.setLastName(CommonUtils.getLastName(edtUserName.getText().toString()));
                edited.setPhoneNo(edtPhoneNumber.getText().toString());

                presenter.editProfile(edited);
            }
        });


    }

    @Override
    protected void initProcess() {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        edtDateOfBirth.setText(year + "-" + String.format("%02d", monthOfYear) + "-" + String.format("%02d", dayOfMonth));
    }

    private void setupDebugView() {
        if (BuildConfig.DEBUG) {
            edtUserName.setText(R.string.sample_name);
            edtEmail.setText(R.string.sample_email);
            edtPhoneNumber.setText(R.string.sample_phone_number);
            edtDateOfBirth.setText(R.string.sample_dob);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCurrentProfile(Profile profile) {
        edtUserName.setText(profile.getFirstName() + " " + profile.getLastName());
        edtEmail.setText(profile.getEmail());
        edtPhoneNumber.setText(profile.getPhoneNo());
        edtDateOfBirth.setText(CommonUtils.getReadableDate(profile.getBirthday()));
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
    public void showEditProfileSuccessDialog(String message) {
        MessageFactory.showAlertDialog(this, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishActivity();
            }
        });
    }

    @Override
    public void postUpdateEvent() {
        EventBus.getDefault().post(new ProfileUpdatedEvent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
