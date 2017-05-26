package com.telkom.weedu.event;

/**
 * Created by sidiqpermana on 4/23/17.
 */

public class RefreshMailboxItemEvent extends BaseEvent {
    private String mailType;
    private String mailId;

    public RefreshMailboxItemEvent(String mailType, String mailId) {
        this.mailType = mailType;
        this.mailId = mailId;
    }

    public String getMailType() {
        return mailType;
    }

    public String getMailId() {
        return mailId;
    }
}
