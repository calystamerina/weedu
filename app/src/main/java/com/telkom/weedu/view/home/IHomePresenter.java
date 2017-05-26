package com.telkom.weedu.view.home;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.Product;
import com.telkom.weedu.data.api.model.QBacaBook;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public interface IHomePresenter <V extends IBaseView> extends IBasePresenter<V> {
    void loadRecomendation();
    void loadUnreadMail();
    void openQJurnalActivity();
    void openDetailBook(QBacaBook book);
    void openDetailProduct(Product product);

    void cancelRequest();

}
