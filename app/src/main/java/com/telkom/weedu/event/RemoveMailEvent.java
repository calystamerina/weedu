package com.telkom.weedu.event;

/**
 * Created by sidiqpermana on 4/23/17.
 */

public class RemoveMailEvent extends BaseEvent {
    private String mailId;

    public RemoveMailEvent(String mailId) {
        this.mailId = mailId;
    }

    public String getMailId() {
        return mailId;
    }
}
