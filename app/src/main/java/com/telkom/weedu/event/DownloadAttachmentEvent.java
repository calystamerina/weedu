package com.telkom.weedu.event;

import com.telkom.weedu.data.mapper.edumail.Attachment;

/**
 * Created by sidiqpermana on 5/14/17.
 */

public class DownloadAttachmentEvent extends BaseEvent {
    private Attachment attachment;

    public DownloadAttachmentEvent(Attachment attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment() {
        return attachment;
    }
}
