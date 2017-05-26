package com.telkom.weedu.view.home;

import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.Product;
import com.telkom.weedu.data.api.model.QBacaBook;
import com.telkom.weedu.data.api.model.Recomendation;

import java.util.ArrayList;
import java.util.LinkedList;

public interface HomeView extends IBaseView {
    void showRecommendationResult(LinkedList<Recomendation> recomendations);
    void showEmptyRecommendationResult(String message);
    void showErrorReqcommendationResult(String message);
    void showUnreadMailSuccess(int count);
    void showUnreadMailFailed(String message);

    void toDetailQBacaBook(QBacaBook book);
    void toDetailProduct(Product product);
}
