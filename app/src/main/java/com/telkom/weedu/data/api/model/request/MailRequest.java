package com.telkom.weedu.data.api.model.request;

import com.telkom.weedu.data.mapper.edumail.Mail;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class MailRequest {
    private String mailType;
    private int page;
    private int perPage;
    private String edumailToken;
    private String key;
    private String mailId;
    private boolean isStarred;
    private Mail mail;

    public MailRequest() {
    }

    public MailRequest(String mailType, int page, int perPage, String edumailToken) {
        this.mailType = mailType;
        this.page = page;
        this.perPage = perPage;
        this.edumailToken = edumailToken;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public void setStarred(boolean starred) {
        isStarred = starred;
    }

    public void setEdumailToken(String edumailToken) {
        this.edumailToken = edumailToken;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMailType() {
        return mailType;
    }

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public String getEdumailToken() {
        return edumailToken;
    }

    public static MailRequest getMailRequest(String mailId, String edumailToken){
        MailRequest mailRequest = new MailRequest();
        mailRequest.setMailId(mailId);
        mailRequest.setEdumailToken(edumailToken);

        return mailRequest;
    }
}
