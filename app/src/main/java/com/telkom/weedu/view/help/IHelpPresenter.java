package com.telkom.weedu.view.help;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public interface IHelpPresenter <V extends IBaseView> extends IBasePresenter<V> {
    void openChatActivity();
    void openContactActivity();
    void openFaqActivity();
    void openTncActivity();
    void openAboutActivity();
}
