package com.telkom.weedu.view.searchmail;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.data.mapper.edumail.Mail;

import java.util.LinkedList;

import javax.inject.Inject;

/**
 * Created by sidiqpermana on 5/1/17.
 */

public class SearchMailPresenter<V extends SearchMailView> extends BasePresenter<V> implements ISearchMailPresenter<V>,
        ApiCallback.OnPostSearchMailRequestCallback{

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_LIMIT_PER_PAGE = 50;

    @Inject
    public SearchMailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadMail(String keyword) {
        getView().showInitialLoading();
        MailRequest mailRequest = new MailRequest();
        mailRequest.setKey(keyword);
        mailRequest.setEdumailToken(getDataManager().getUserPreference().getEdumailToken());
        mailRequest.setPage(DEFAULT_PAGE);
        mailRequest.setPerPage(DEFAULT_LIMIT_PER_PAGE);

        getDataManager().postSearchMail(mailRequest, this);
    }

    @Override
    public void openMailDetailActivity(Mail mail) {
        getView().toMailDetailActivity(mail);
    }

    @Override
    public void onPostSearchRequestSuccess(LinkedList<Mail> mails) {
        getView().hideInitialLoading();
        getView().showSearchResultList(mails);
    }

    @Override
    public void onPostSearchRequestFailed(String message) {
        getView().hideInitialLoading();
        getView().showErrorResult(message);
    }

    @Override
    public void onPostSearchRequestEmpty(String message) {
        getView().hideInitialLoading();
        getView().showEmptyResult(message);
    }
}
