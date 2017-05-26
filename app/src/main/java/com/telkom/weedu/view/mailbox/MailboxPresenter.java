package com.telkom.weedu.view.mailbox;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.data.mapper.edumail.Mail;
import com.telkom.weedu.utils.AppConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;

import javax.inject.Inject;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public class MailboxPresenter<V extends MailboxView> extends BasePresenter<V> implements IMailboxPresenter<V>,
        ApiCallback.OnGetMailRequestCallback, ApiCallback.OnPostRemoveMailPermanentlyRequestCallback, ApiCallback.OnPostRemoveMailToTrashCallback{

    //// TODO: 4/12/17 Replace with the fix edumail token from server
    //public static String edumailToken = "5Pyi3qc4ZQxveY-UgDuoLedeJ5U6MTQ5MjI3MzUxODMyMjA5NTg3MQ==";
    public static String edumailToken = null;

    @Inject
    public MailboxPresenter(DataManager dataManager) {
        super(dataManager);
        edumailToken = getDataManager().getUserPreference().getEdumailToken();
        //edumailToken = AppConstants.EDUMAIL_MOCK_TOKEN;
    }

    @Override
    public void openComposeActivity() {
        getView().toComposeActivity();
    }

    @Override
    public void loadMail(String mailBoxType, int page) {
        getView().showInitialLoading();
        MailRequest mailRequest = new MailRequest(mailBoxType,
                page, AppConstants.DEFAULT_PER_PAGE_COUNT, edumailToken);

        getDataManager().loadEmail(mailRequest, this);
    }

    @Override
    public void openMailDetailActivity(Mail mail) {
        getView().toMailDetailActivity(mail);
    }

    @Override
    public void removeMailToTrash(String mailId) {
        getView().showLoading();
        MailRequest mailRequest = MailRequest.getMailRequest(mailId, edumailToken);
        getDataManager().postRemoveMailToTrash(mailRequest, this);
    }

    @Override
    public void removeMailPermanently(String mailId) {
        getView().showLoading();
        MailRequest mailRequest = MailRequest.getMailRequest(mailId, edumailToken);
        getDataManager().postRemoveMailPermanently(mailRequest, this);
    }

    @Override
    public void onGetMailRequestSuccess(LinkedList<Mail> list) {
        getView().hideInitialLoading();
        getView().showInboxList(list);
    }

    @Override
    public void onGetMailRequestFailed(String message) {
        getView().hideInitialLoading();
        getView().showErrorInInbox(message);
    }

    @Override
    public void onGetMailRequestEmpty(String message) {
        getView().hideInitialLoading();
        getView().showEmptyInbox(message);
    }

    @Override
    public void onPostRemoveMailToTrashRequestSuccess(BaseEdumailResponse baseEdumailResponse, String mailId) {
        getView().hideLoading();
        getView().onRemoveMailToTrashSuccess(baseEdumailResponse.getStatus(), mailId);
    }

    @Override
    public void onPostRemoveMailToTrashRequestFailed(String message) {
        getView().hideLoading();
        getView().onRemoveMailToTrashFailed(message);
    }

    @Override
    public void onPostRemoveMailPermanentlySuccess(BaseEdumailResponse baseEdumailResponse, String mailId) {
        getView().hideLoading();
        getView().onRemoveMailPermanentlySuccess(baseEdumailResponse.getStatus(), mailId);
    }

    @Override
    public void onPostRemoveMailPermanentlyFailed(String message) {
        getView().hideLoading();
        getView().onRemoveMailPermanentlyFailed(message);
    }
}
