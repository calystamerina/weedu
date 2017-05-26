package com.telkom.weedu.service.downloadattachment;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.mapper.edumail.Attachment;

import javax.inject.Inject;

/**
 * Created by sidiqpermana on 5/14/17.
 */

public class DownloadAttachmentPresenter<V extends DownloadAttachmentView> extends BasePresenter<V> implements IDownloadAttamentPresenter<V>,
        ApiCallback.OnDownloadAttachmentCallback{

    @Inject
    public DownloadAttachmentPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getAttachment(Attachment attachment) {
        getView().showStartDownloadNotification(attachment);
        getDataManager().getAttachmentFile(attachment, this);
    }

    @Override
    public void onDownloadSuccess(Attachment attachment, String downloadedFile) {
        getView().showDownloadSuccessNotification(attachment, downloadedFile);
    }

    @Override
    public void onDownloadFailed(Attachment attachment, String message) {
        getView().showDownloadFailedNotification(attachment, message);

    }
}
