package com.telkom.weedu.event;

/**
 * Created by sidiqpermana on 4/30/17.
 */

public class MailDetailMenuItemEvent extends BaseEvent{
    private String eventType;

    public MailDetailMenuItemEvent(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }
}
