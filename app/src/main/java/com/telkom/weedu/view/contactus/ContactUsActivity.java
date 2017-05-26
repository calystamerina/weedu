package com.telkom.weedu.view.contactus;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.data.api.model.request.ContactUsRequest;
import com.telkom.weedu.utils.AppMessages;
import com.telkom.weedu.utils.MessageFactory;
import com.telkom.weedu.utils.ValidateUtils;

import javax.inject.Inject;

import butterknife.BindView;


public class ContactUsActivity extends BaseActivity implements ContactUsView {

    @Inject
    IContactUsPresenter<ContactUsView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_send_message)
    Button btnSendMessage;
    @BindView(R.id.spn_product)
    Spinner spnProduct;
    @BindView(R.id.edt_subject)
    EditText edtSubject;
    @BindView(R.id.edt_message)
    EditText edtMessage;

    ArrayAdapter<String> productAdapter;
    String[] products = new String[]{
            "Weedu", "Ruangguru", "RuangLes", "Qbaca", "QJurnal", "RuangLatihan", "RuangVideo", "Bahaso"
    };

    public static void start(Context context) {
        Intent starter = new Intent(context, ContactUsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_contact_us;
    }

    @Override
    protected void initLib() {
        // TODO implements injection here
        getActivityComponent().inject(ContactUsActivity.this);
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        setupToolbar(toolbar, getString(R.string.activity_title_contact_us), true);
        setupDummyProduct();
    }

    @Override
    protected void initAction() {
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComplain();
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    private void setupDummyProduct() {
        productAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, products);
        spnProduct.setAdapter(productAdapter);
    }

    @Override
    public void showAlert(String message) {
        MessageFactory.showAlertDialog(this, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishActivity();
            }
        });
    }

    @Override
    public void sendComplain() {
        if (ValidateUtils.validate(AppMessages.FIELD_REQUIRED, edtMessage, edtSubject)) {
//            String productId = products[spnProduct.getSelectedItemPosition()];
            String productId = "49280d71-b2ad-4788-800f-cd5113d765e3";
            String subject = edtSubject.getText().toString();
            String message = edtMessage.getText().toString();

            ContactUsRequest request = new ContactUsRequest(productId, subject, message);
            presenter.postContactUs(request);
        }
    }
}
