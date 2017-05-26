package com.telkom.weedu.service.downloadattachment;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.mapper.edumail.Attachment;

/**
 * Created by sidiqpermana on 5/14/17.
 */

public interface IDownloadAttamentPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void getAttachment(Attachment attachment);
}
