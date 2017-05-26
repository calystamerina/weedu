package com.telkom.weedu.service.downloadattachment;

import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.mapper.edumail.Attachment;

/**
 * Created by sidiqpermana on 5/14/17.
 */

public interface DownloadAttachmentView extends IBaseView {
    void showStartDownloadNotification(Attachment attachment);
    void showDownloadSuccessNotification(Attachment attachment, String filePath);
    void showDownloadFailedNotification(Attachment attachment, String message);
}
