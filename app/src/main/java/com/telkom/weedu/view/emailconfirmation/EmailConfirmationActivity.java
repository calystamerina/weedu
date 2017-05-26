package com.telkom.weedu.view.emailconfirmation;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.data.api.model.request.RegisterRequest;
import com.telkom.weedu.utils.AppMessages;
import com.telkom.weedu.utils.BundleKeys;
import com.telkom.weedu.utils.EmailSuggestionGenerator;
import com.telkom.weedu.utils.ValidateUtils;
import com.telkom.weedu.view.login.LoginActivity;
import com.telkom.weedu.view.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class EmailConfirmationActivity extends BaseActivity implements EmailConfirmationView, View.OnClickListener,
        AdapterView.OnItemClickListener{

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.lv_email_suggestion)
    ListView lvEmailSuggestion;
    @BindView(R.id.ln_email_suggestion)
    LinearLayout lnEmailSuggestion;
    @BindView(R.id.btn_edumail)
    Button btnEdumail;
    @BindView(R.id.tv_ask_for_other_email_option)
    TextView tvAskForOtherEmailOption;
    @BindView(R.id.tv_edumail_domain)
    TextView tvEdumailDomain;

    private RegisterRequest registerRequest;

    @Inject
    IEmailConfirmationPresenter<EmailConfirmationView> confirmationPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_email_confirmation;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        confirmationPresenter.onAttach(this);
    }

    @Override
    protected void initIntent() {
        registerRequest = new RegisterRequest();
        registerRequest = getIntent().getParcelableExtra(BundleKeys.KEY_REGISTER_REQUEST);
    }

    @Override
    protected void initUI() {

        tvEdumailDomain.setText(R.string.label_email_edumail);
        setupToolbar(getString(R.string.activity_title_confirmation), true);
    }

    @Override
    protected void initAction() {
        btnEdumail.setOnClickListener(this);
        tvAskForOtherEmailOption.setOnClickListener(this);

    }

    @Override
    protected void initProcess() {
        EmailSuggestionGenerator suggestionGenerator = new EmailSuggestionGenerator(registerRequest);
        lvEmailSuggestion.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, suggestionGenerator.getUsernames()));
        lvEmailSuggestion.setOnItemClickListener(this);
    }

    @Override
    public void toMainActivity() {
        MainActivity.start(this);
        finishActivity();
    }

    @Override
    public void toLoginActivity() {
        LoginActivity.start(this);
        finishActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ask_for_other_email_option:
                break;

            case R.id.btn_edumail:
                doRegister();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(Context context, RegisterRequest request) {
        Intent starter = new Intent(context, EmailConfirmationActivity.class);
        starter.putExtra(BundleKeys.KEY_REGISTER_REQUEST, request);
        context.startActivity(starter);
    }

    private void doRegister() {
        if (ValidateUtils.validate(AppMessages.FIELD_REQUIRED, edtEmail)) {
            String username = edtEmail.getText().toString();
            registerRequest.setUsername(username);
            confirmationPresenter.postRegister(registerRequest);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        confirmationPresenter.onDetach();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        edtEmail.setText((String)lvEmailSuggestion.getAdapter().getItem(i));
    }
}
