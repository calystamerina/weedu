package com.telkom.weedu.event;

import com.telkom.weedu.data.mapper.edumail.Mail;

/**
 * Created by sidiqpermana on 4/23/17.
 */

public class AddRemoveFavoriteMailEvent extends BaseEvent {
    private Mail mail;

    public AddRemoveFavoriteMailEvent(Mail mail) {
        this.mail = mail;
    }

    public Mail getMail() {
        return mail;
    }
}
