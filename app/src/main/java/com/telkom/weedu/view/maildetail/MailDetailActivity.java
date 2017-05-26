package com.telkom.weedu.view.maildetail;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.data.api.model.edumail.ForwardData;
import com.telkom.weedu.data.api.model.edumail.ReplyData;
import com.telkom.weedu.data.mapper.edumail.Mail;
import com.telkom.weedu.event.AddRemoveFavoriteMailEvent;
import com.telkom.weedu.event.DownloadAttachmentEvent;
import com.telkom.weedu.event.MailDetailMenuItemEvent;
import com.telkom.weedu.event.RefreshMailboxItemEvent;
import com.telkom.weedu.event.RefreshUnreadMailsEvent;
import com.telkom.weedu.event.RemoveMailEvent;
import com.telkom.weedu.service.downloadattachment.DownloadAttachmentService;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.utils.BundleKeys;
import com.telkom.weedu.utils.MessageFactory;
import com.telkom.weedu.view.adapters.MailDetailAdapter;
import com.telkom.weedu.view.composeemail.ComposeEmailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;

public class MailDetailActivity extends BaseActivity implements MailDetailView, View.OnClickListener {

    @Inject
    MailDetailPresenter<MailDetailView> presenter;

    @BindView(R.id.lv_detail)
    ListView lvDetail;
    @BindView(R.id.sw_layout_mail_detail)
    SwipeRefreshLayout swLayoutMailDetail;

    private ImageView imgStar;
    private LinearLayout lnReply, lnReplyAll, lnForward;
    private View header, footer;

    private MailDetailAdapter adapter;
    private Mail mailItem;
    private String mailType;
    private boolean isFavorite = false;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_mail_detail;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(MailDetailActivity.this);
        presenter.onAttach(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initIntent() {
        mailItem = getIntent().getParcelableExtra(BundleKeys.KEY_EMAIL_ITEM);
        mailType = getIntent().getParcelableExtra(BundleKeys.KEY_EMAIL_TYPE);
    }

    @Override
    protected void initUI() {
        setupToolbar("", true);
        swLayoutMailDetail.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.amber600),
                ContextCompat.getColor(this, R.color.red600),
                ContextCompat.getColor(this, R.color.colorAccent));
    }

    @Override
    protected void initAction() {
        swLayoutMailDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                removeHeaderAndFooter();
                presenter.loadMailDetail(mailItem.getMailId());
            }
        });
    }

    @Override
    protected void initProcess() {
        isFavorite = mailItem.isStarred();
        presenter.loadMailDetail(mailItem.getMailId());
    }

    public void showInitialLoading() {
        swLayoutMailDetail.setRefreshing(true);
        lvDetail.setVisibility(View.GONE);
    }

    public void hideInitialLoading() {
        swLayoutMailDetail.setRefreshing(false);
        lvDetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUpHeaderList(Mail mail) {
        header = getLayoutInflater().inflate(R.layout.header_mail_detail, null);
        TextView tvSubject = (TextView) header.findViewById(R.id.tv_subject);
        tvSubject.setText(mail.getSubject());
        imgStar = (ImageView)header.findViewById(R.id.img_star);
        imgStar.setOnClickListener(this);

        if (mailItem.isTrashed()) imgStar.setVisibility(View.GONE);

        setStarredIcon();

        lvDetail.addHeaderView(header);
    }

    @Override
    public void setUpFooterList() {
        footer = getLayoutInflater().inflate(R.layout.footer_mail_detail, null);
        lnReply = (LinearLayout) footer.findViewById(R.id.ln_reply);
        lnReplyAll = (LinearLayout) footer.findViewById(R.id.ln_reply_all);
        lnForward = (LinearLayout) footer.findViewById(R.id.ln_forward);

        lnReply.setOnClickListener(this);
        lnReplyAll.setOnClickListener(this);
        lnForward.setOnClickListener(this);

        lvDetail.addFooterView(footer);
    }

    private void removeHeaderAndFooter(){
        lvDetail.removeHeaderView(header);
        lvDetail.removeFooterView(footer);
    }

    @Override
    public void showMailDetail(Mail mail) {
        setUpHeaderList(mail);
        setUpFooterList();

        adapter = new MailDetailAdapter(this, mail.getConversation());
        lvDetail.setAdapter(adapter);

        postActivityEvent(new RefreshUnreadMailsEvent());
        postActivityEvent(new RefreshMailboxItemEvent(mailType, mail.getMailId()));
    }

    @Override
    public void showMailDetailError(String message) {
        swLayoutMailDetail.setRefreshing(false);
        lvDetail.setVisibility(View.VISIBLE);

        showMailDetail(mailItem);
    }

    @Override
    public void onRemoveMailToTrashSuccess(String message, String mailId) {
        showToast(message);
        postActivityEvent(new RemoveMailEvent(mailId));
        finishActivity();
    }

    @Override
    public void onRemoveMailToTrashFailed(String message) {
        showToast(message);
    }

    @Override
    public void onRemoveMailPermanentlySucess(String message, String mailId) {
        showToast(message);
        postActivityEvent(new RemoveMailEvent(mailId));
        finishActivity();
    }

    @Override
    public void onRemoveMailPermanentlyFailed(String message) {
        showToast(message);
    }

    @Override
    public void onAddRemoveFavoriteSuccess(String message, Mail mail) {
        isFavorite = mail.isStarred();
        hideLoading();
        showToast(message);

        setStarredIcon();

        EventBus.getDefault().post(new AddRemoveFavoriteMailEvent(mail));
    }

    private void setStarredIcon() {
        imgStar.setImageResource(R.drawable.ic_star_border);
        imgStar.setColorFilter(ContextCompat.getColor(MailDetailActivity.this, R.color.amber600));
        if (isFavorite){
            imgStar.setImageResource(R.drawable.ic_star_black_24dp);
        }
    }

    @Override
    public void onAddRemoveFavoriteFailed(String message) {
        showToast(message);
    }

    @Override
    public void openComposeActivityForReply(ReplyData replyData) {
        ComposeEmailActivity.start(MailDetailActivity.this, AppConstants.COMPOSE_TYPE_REPLY, replyData);
    }

    @Override
    public void onGetReplyReferenceFailed(String message) {
        showToast(message);
    }

    @Override
    public void openComposetActivityForReplyAll(ReplyData replyData) {
        ComposeEmailActivity.start(MailDetailActivity.this, AppConstants.COMPOSE_TYPE_REPLY_ALL, replyData);
    }

    @Override
    public void onGetReplyAllReferenceFailed(String message) {
        showToast(message);
    }

    @Override
    public void openComposeActivityForForward(ForwardData forwardData) {
        ComposeEmailActivity.start(MailDetailActivity.this, AppConstants.COMPOSE_TYPE_FORWARD, forwardData);
    }

    @Override
    public void onGetForwardReferenceFailed(String message) {
        showToast(message);
    }

    public static void start(Context context, String mailType, Mail mail) {
        Intent starter = new Intent(context, MailDetailActivity.class);
        starter.putExtra(BundleKeys.KEY_EMAIL_ITEM, mail);
        starter.putExtra(BundleKeys.KEY_EMAIL_TYPE, mailType);
        context.startActivity(starter);
    }

    public static void start(Context context, Mail mail) {
        Intent starter = new Intent(context, MailDetailActivity.class);
        starter.putExtra(BundleKeys.KEY_EMAIL_ITEM, mail);
        context.startActivity(starter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mailItem.isTrashed()){
            getMenuInflater().inflate(R.menu.menu_mail_detail, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_mail:
                showEmailDeleteConfirmation();
                break;

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showEmailDeleteConfirmation() {
        MessageFactory.showAlertDialog(MailDetailActivity.this, getString(R.string.dialog_title_delete),
                getString(R.string.dialog_question_delete_mail),
                getString(R.string.dialog_answer_yes),
                getString(R.string.dialog_answer_yes_permanently),
                getString(R.string.dialog_answer_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.removeMailToTrash(mailItem.getMailId());
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.removeMailPermanently(mailItem.getMailId());
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_star:
                postAddRemoveFavorite();
                break;

            case R.id.ln_reply:
                postReplyMail();
                break;

            case R.id.ln_reply_all:
                postReplyAllMail();
                break;

            case R.id.ln_forward:
                postForwardMail();
                break;
        }
    }

    private void postForwardMail() {
        presenter.getForwardMailPreference(mailItem.getMailId());
    }

    private void postReplyAllMail() {
        presenter.getReplyAllMailPreference(mailItem.getMailId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBusSubscriber(MailDetailActivity.this);
        presenter.onDetach();
    }


    private void postReplyMail() {
        presenter.getReplyMailPreference(mailItem.getMailId());
    }

    private void postAddRemoveFavorite() {
        boolean status = !isFavorite;
        presenter.favoriteMail(mailItem, status);
    }

    @Subscribe
    public void onMailDetailMenuItemEvent(MailDetailMenuItemEvent e){
        String type = e.getEventType();
        if (type.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_REPLY)){
            postReplyMail();
        }else if(type.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_FORWARD)){
            postForwardMail();
        }else if(type.equalsIgnoreCase(AppConstants.COMPOSE_TYPE_REPLY_ALL)){
            postReplyAllMail();
        }else if (type.equalsIgnoreCase(AppConstants.EDUMAIL_ADD_REMOVE_FAVORITE)){
            postAddRemoveFavorite();
        }
    }

    @Subscribe
    public void onDownloadAttachmentTriggered(DownloadAttachmentEvent e){

        startService(new Intent(this, DownloadAttachmentService.class)
            .putExtra(BundleKeys.KEY_ATTACHMENT, e.getAttachment()));
    }
}
