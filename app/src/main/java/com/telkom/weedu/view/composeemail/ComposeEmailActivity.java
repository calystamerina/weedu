package com.telkom.weedu.view.composeemail;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.data.api.model.edumail.AttachmentUploadStatus;
import com.telkom.weedu.data.api.model.edumail.AttachmentUploadedItem;
import com.telkom.weedu.data.api.model.edumail.ForwardData;
import com.telkom.weedu.data.api.model.edumail.Person;
import com.telkom.weedu.data.api.model.edumail.ReplyData;
import com.telkom.weedu.event.RefreshMailboxEvent;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.utils.AppMessages;
import com.telkom.weedu.utils.BundleKeys;
import com.telkom.weedu.utils.CommonUtils;
import com.telkom.weedu.utils.FileUtils;
import com.telkom.weedu.utils.MessageFactory;
import com.telkom.weedu.utils.ValidateUtils;
import com.telkom.weedu.utils.listener.CustomOnItemClickListener;
import com.telkom.weedu.view.custom.ContactsCompletionView;
import com.tokenautocomplete.TokenCompleteTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ComposeEmailActivity extends BaseActivity implements ComposeView, EasyPermissions.PermissionCallbacks{

    @Inject
    IComposePresenter<ComposeView> presenter;

    @BindView(R.id.tv_subject)
    EditText edtSubject;
    @BindView(R.id.edt_recipient)
    ContactsCompletionView edtRecipient;
    @BindView(R.id.edt_mail_body)
    EditText edtMailBody;
    @BindView(R.id.ln_attachments)
    LinearLayout lnAttachments;
    @BindView(R.id.edt_cc)
    ContactsCompletionView edtCc;
    @BindView(R.id.edt_bcc)
    ContactsCompletionView edtBcc;

    private String composeType = null;
    private ReplyData replyData = null;
    private ForwardData forwardData = null;
    private ArrayList<AttachmentUploadStatus> listTempAttachment;
    private ArrayList<AttachmentUploadedItem> listAttachmentItem;
    private ArrayList<Person> listRecepients, listCc, listBcc;

    private boolean isOnUploadingAttachment = false;

    public static int FILE_REQUEST_CODE = 100;
    private static final int RC_READ_AND_WRITE_STORAGE_PERM = 122;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_compose_email;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {
        composeType = getIntent().getStringExtra(BundleKeys.KEY_COMPOSE_TYPE);
        replyData = getIntent().getParcelableExtra(BundleKeys.KEY_REPLY_DATA);
        forwardData = getIntent().getParcelableExtra(BundleKeys.KEY_FORWARD_DATA);
    }

    @Override
    protected void initUI() {
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initAction() {
        edtRecipient.setTokenListener(new TokenCompleteTextView.TokenListener<Person>() {
            @Override
            public void onTokenAdded(Person token) {
                if (listRecepients == null){
                    listRecepients = new ArrayList<>();
                }
                listRecepients.add(token);
            }

            @Override
            public void onTokenRemoved(Person token) {
                listRecepients.remove(token);
            }
        });

        edtCc.setTokenListener(new TokenCompleteTextView.TokenListener<Person>() {
            @Override
            public void onTokenAdded(Person token) {
                if (listCc == null){
                    listCc = new ArrayList<>();
                }
                listCc.add(token);
            }

            @Override
            public void onTokenRemoved(Person token) {
                listCc.remove(token);
            }
        });

        edtBcc.setTokenListener(new TokenCompleteTextView.TokenListener<Person>() {
            @Override
            public void onTokenAdded(Person token) {
                if (listBcc == null){
                    listBcc = new ArrayList<>();
                }
                listBcc.add(token);
            }

            @Override
            public void onTokenRemoved(Person token) {
                listBcc.remove(token);
            }
        });
    }

    @Override
    protected void initProcess() {
        if (composeType.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_REPLY)){
            bindDataForReply();
        }

        if (composeType.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_REPLY_ALL)){
            bindDataForReplyAll();
        }

        if (composeType.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_FORWARD)){
            bindDataForForward();
        }
    }

    private void bindDataForReply() {
        String subject = "Re: "+replyData.getListSubject().get(0).toString();
        edtRecipient.addObject(new Person(null, replyData.getTo()));
        edtSubject.setText(subject);
        getSupportActionBar().setTitle(R.string.activity_title_reply);
    }

    private void bindDataForReplyAll() {
        String subject = "Re: "+replyData.getListSubject().get(0);
        edtRecipient.addObject(new Person(null, replyData.getTo()));
        if (!TextUtils.isEmpty(replyData.getCc())){
            if (replyData.getCc().contains(",")){
                String[] ccEmails = replyData.getCc().split(",");
                for (String s : ccEmails){
                    edtCc.addObject(new Person(null, s));
                }
            }else{
                edtCc.addObject(new Person(null, replyData.getCc()));
            }
        }
        edtSubject.setText(subject);
        getSupportActionBar().setTitle(R.string.activity_title_reply_all);
    }

    private void bindDataForForward() {
        String subject = "Fwd: "+forwardData.getListSubject().get(0);
        edtSubject.setText(subject);
        String body = "\n\n\n====== Forwarded Message ========\n"+forwardData.getTextBody();
        edtMailBody.setText(body);
        getSupportActionBar().setTitle(R.string.activity_title_forward);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compose_email, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        } else if (id == R.id.action_send) {
            sendMailAction();
        } else if (id == R.id.action_attach) {
            checkReadAndWritePermission();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showPostMailSuccessMessage(String message) {
        showToast(message);
        finishActivity();
    }

    @Override
    public void showPostMailFailedMessage(String message) {
        showToast(message);
    }

    private void sendMailAction() {
        if (!isOnUploadingAttachment){
            if (composeType.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_NEW)){
                postNewEmail();
            }

            if (composeType.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_REPLY)){
                postReplyEmail();
            }

            if (composeType.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_REPLY_ALL)){
                postReplyEmail();
            }

            if (composeType.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_FORWARD)){
                postNewEmail();
            }
        }else{
            showToast(getString(R.string.message_please_wait_while_uploading));
        }
    }

    @Override
    public void postNewEmail() {
        if (ValidateUtils.validate(AppMessages.FIELD_REQUIRED, edtMailBody, edtRecipient, edtSubject)){
            String subject = edtSubject.getText().toString().trim();
            String body = edtMailBody.getText().toString().trim();
            String recepient = CommonUtils.getRecepientEmails(listRecepients);
            String cc = CommonUtils.getRecepientEmails(listCc);
            String bcc = CommonUtils.getRecepientEmails(listBcc);

            presenter.postNewEmail(recepient, cc, bcc, subject, body, listAttachmentItem);
        }
    }

    @Override
    public void postReplyEmail() {
        if (ValidateUtils.validate(AppMessages.FIELD_REQUIRED, edtMailBody, edtRecipient, edtSubject)){
            String subject = edtSubject.getText().toString().trim();
            String body = edtMailBody.getText().toString().trim();
            String recepient = CommonUtils.getRecepientEmails(listRecepients);
            String cc = CommonUtils.getRecepientEmails(listCc);
            String bcc = CommonUtils.getRecepientEmails(listBcc);

            replyData.setTo(recepient);

            presenter.postReply(cc, bcc, subject, body, replyData, listAttachmentItem);
        }
    }

    private void openFileChooser(){
        if (!isOnUploadingAttachment){
            if (Build.VERSION.SDK_INT < 19) {
                Intent intent = new Intent();
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(intent, "Select Picture"),
                        FILE_REQUEST_CODE);

            } else {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent, FILE_REQUEST_CODE);
            }
        }else{
            showToast(getString(R.string.message_please_wait_while_uploading));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_REQUEST_CODE){
            proceedSelectedFile(data);
        }
    }

    private void proceedSelectedFile(Intent data) {
        if (data != null){
            Uri file = data.getData();
            if (file != null){
                String filePath = FileUtils.getPath(this, file);
                if (!TextUtils.isEmpty(filePath)){
                    Log.d("File", filePath);
                    isOnUploadingAttachment = true;
                    presenter.uploadAttachment(filePath);
                }
            }else{
                showToast(getString(R.string.error_unable_to_pick_file));
            }
        }else{
            showToast(getString(R.string.error_unable_to_pick_file));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_READ_AND_WRITE_STORAGE_PERM)
    private void checkReadAndWritePermission() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        if (EasyPermissions.hasPermissions(this, permissions)) {
            openFileChooser();
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, "Aplikasi ini akan melakukan akses ke media penyimpan untuk menjalankan fungsi ini",
                    RC_READ_AND_WRITE_STORAGE_PERM, permissions);
        }
    }

    @Override
    public void onReplyMailSuccess(String message) {
        showSuccesMessageAndCloseActivity(message);
    }

    private void showSuccesMessageAndCloseActivity(String message) {
        showToast(message);
        EventBus.getDefault().post(new RefreshMailboxEvent());
        finishActivity();
    }

    @Override
    public void onReplyMailFailed(String message) {
        showToast(message);
    }

    @Override
    public void onUploadAttachmentProgress(AttachmentUploadStatus attachmentUploadStatus) {
        if (listTempAttachment == null){
            listTempAttachment = new ArrayList<>();
        }

        listTempAttachment.add(attachmentUploadStatus);
        refreshAttachmentToView();

        if (listTempAttachment.size() > 0){
            lnAttachments.setVisibility(View.VISIBLE);
        }
    }

    private void refreshAttachmentToView(){
        removeAllAttachments();
        for (int i = 0 ; i < listTempAttachment.size(); i++){
            AttachmentUploadStatus s = listTempAttachment.get(i);
            View itemView = getLayoutInflater().inflate(R.layout.item_upload_attachment, null);
            TextView tvName = (TextView)itemView.findViewById(R.id.tv_item_name);
            TextView tvStatus = (TextView)itemView.findViewById(R.id.tv_item_status);
            ProgressBar progressBar = (ProgressBar)itemView.findViewById(R.id.progressbar);
            ImageView btnDeleteAttachment = (ImageView) itemView.findViewById(R.id.btn_delete);
            btnDeleteAttachment.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnViewClickCallback() {
                @Override
                public void onViewClicked(View view, int position) {
                    deleteSelectedAttachmentItem(position);
                }
            }));

            tvName.setText(s.getName());
            String status;
            if (s.getStatus().equalsIgnoreCase(AppConstants.EDUMAIL_ATTACHMENT_ON_UPLOAD_PROGRESS)){
                status = getString(R.string.label_status_uploading);
                progressBar.setVisibility(View.VISIBLE);
            }else if(s.getStatus().equalsIgnoreCase(AppConstants.EDUMAIL_ATTACHMENT_ON_UPLOAD_FINISH)){
                status = getString(R.string.label_status_ready_to_post);
                progressBar.setVisibility(View.GONE);
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            }else{
                status = getString(R.string.label_status_upload_failed);
                progressBar.setVisibility(View.GONE);
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.red600));
            }
            tvStatus.setText(status);

            lnAttachments.addView(itemView);
        }
    }

    private void deleteSelectedAttachmentItem(int position) {
        if (listAttachmentItem != null){
            if (listAttachmentItem.size() > 0){
                listAttachmentItem.remove(position);
            }
        }
        listTempAttachment.remove(position);
        refreshAttachmentToView();
    }

    private void removeAllAttachments(){
        lnAttachments.removeAllViews();
    }

    public void updateAttachments(String filename, String status){
        AttachmentUploadStatus attachmentUploadStatus = null;
        int position = 0;
        for (int i = 0; i < listTempAttachment.size(); i++){
            if (listTempAttachment.get(i).getName().equalsIgnoreCase(filename)){
                attachmentUploadStatus = listTempAttachment.get(i);
                position = i;
                break;
            }
        }

        attachmentUploadStatus.setStatus(status);
        listTempAttachment.set(position, attachmentUploadStatus);
        refreshAttachmentToView();
    }

    @Override
    public void onUploadAttachmentSuccess(String filename, AttachmentUploadedItem item) {
        isOnUploadingAttachment = false;
        addSuccessfulAttachmentToList(item);
        updateAttachments(filename, AppConstants.EDUMAIL_ATTACHMENT_ON_UPLOAD_FINISH);
    }

    private void addSuccessfulAttachmentToList(AttachmentUploadedItem item) {
        if (listAttachmentItem == null){
            listAttachmentItem = new ArrayList<>();
        }
        listAttachmentItem.add(item);
    }

    @Override
    public void onUploadAttachmentFailed(String filename, String message) {
        isOnUploadingAttachment = false;
        showToast(message);
        updateAttachments(filename, AppConstants.EDUMAIL_ATTACHMENT_FAILED_TO_UPLOAD);
    }

    @Override
    public void onSaveMessageToDraftSuccess(String message) {
        showToast(message);
        finishActivity();
    }

    @Override
    public void onSaveMessageToDraftFailed(String message) {
        showToast(message);
        finishActivity();
    }

    public static void start(Context context, String composeType) {
        Intent starter = new Intent(context, ComposeEmailActivity.class);
        starter.putExtra(BundleKeys.KEY_COMPOSE_TYPE, composeType);
        context.startActivity(starter);
    }

    public static void start(Context context, String composeType, ReplyData replyData) {
        Intent starter = new Intent(context, ComposeEmailActivity.class);
        starter.putExtra(BundleKeys.KEY_COMPOSE_TYPE, composeType);
        starter.putExtra(BundleKeys.KEY_REPLY_DATA, replyData);
        context.startActivity(starter);
    }

    public static void start(Context context, String composeType, ForwardData forwardData) {
        Intent starter = new Intent(context, ComposeEmailActivity.class);
        starter.putExtra(BundleKeys.KEY_COMPOSE_TYPE, composeType);
        starter.putExtra(BundleKeys.KEY_FORWARD_DATA, forwardData);
        context.startActivity(starter);
    }

    @Override
    public void onBackPressed() {
        showCloseComposeAlertDialog();
    }

    private void showCloseComposeAlertDialog() {
        MessageFactory.showAlertDialog(this, getString(R.string.dialog_title_confirmation), getString(R.string.dialog_question_cancel_compose),
                getString(R.string.dialog_answer_yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        saveMessageAsDraft();
                    }


                }, getString(R.string.dialog_answer_cancel), new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }

    private void saveMessageAsDraft() {
        if (ValidateUtils.validate(AppMessages.FIELD_REQUIRED, edtMailBody, edtRecipient, edtSubject)){
            String subject = edtSubject.getText().toString().trim();
            String body = edtMailBody.getText().toString().trim();
            String recepient = CommonUtils.getRecepientEmails(listRecepients);
            String cc = CommonUtils.getRecepientEmails(listCc);
            String bcc = CommonUtils.getRecepientEmails(listBcc);

            presenter.saveMessageToDraft(recepient, cc, bcc, subject, body);
        }else{
            finishActivity();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("Permission", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("Permission", "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }
}
