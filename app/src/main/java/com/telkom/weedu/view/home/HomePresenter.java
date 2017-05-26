package com.telkom.weedu.view.home;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.Product;
import com.telkom.weedu.data.api.model.QBacaBook;
import com.telkom.weedu.data.api.model.Recomendation;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.utils.AppConstants;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.inject.Inject;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public class HomePresenter <V extends HomeView> extends BasePresenter<V> implements IHomePresenter<V>,
        ApiCallback.OnQBacaRequestCallback, ApiCallback.OnGetUnreadMailRequestCallback{

    @Inject
    public HomePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadRecomendation() {
        getDataManager().getQBacaData(this);
    }

    @Override
    public void loadUnreadMail() {
        String edumailToken = getDataManager().getUserPreference().getEdumailToken();
        MailRequest mailRequest = new MailRequest(null, 0, 0, edumailToken);
        getDataManager().getUnreadMail(mailRequest, this);
    }

    @Override
    public void openQJurnalActivity() {

    }

    @Override
    public void openDetailBook(QBacaBook book) {
        getView().toDetailQBacaBook(book);
    }

    @Override
    public void openDetailProduct(Product product) {
        String urlRuangUji = "https://uji-dot-rg-rnd-projects.appspot.com/content/123?accessToken="+getDataManager().getUserPreference().getAccessToken()+"&from=weedu";
        if (product.getDescription().equalsIgnoreCase("Ruanguji")){
            product.setUrl(urlRuangUji);
        }
        getView().toDetailProduct(product);
    }

    @Override
    public void onQBacaRequestSuccess(LinkedList<QBacaBook> list) {
        LinkedList<Recomendation> recomendations = new LinkedList<>();
        for (QBacaBook qBacaBook : list){
            Recomendation recomendation = Recomendation.getRecommendation(qBacaBook);
            recomendations.add(recomendation);
        }
        getView().showRecommendationResult(recomendations);
    }

    @Override
    public void onQBacaRequestFailed(String message) {
        getView().showErrorReqcommendationResult(message);
    }

    @Override
    public void onQBacaRequestEmpty(String message) {
        getView().showEmptyRecommendationResult(message);
    }

    @Override
    public void cancelRequest() {
        getDataManager().cancelAllRequest();
    }

    @Override
    public void onGetUnreadSuccess(int count) {
        getView().showUnreadMailSuccess(count);
    }

    @Override
    public void onGetUnreadFailed(String message) {
        getView().showUnreadMailFailed(message);
    }
}
