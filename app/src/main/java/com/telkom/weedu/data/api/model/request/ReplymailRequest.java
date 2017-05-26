package com.telkom.weedu.data.api.model.request;

import com.telkom.weedu.data.api.model.edumail.ReplyData;

/**
 * Created by sidiqpermana on 4/24/17.
 */

public class ReplymailRequest extends PostmailRequest {
    private String replyTo;
    private String references;

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public static ReplymailRequest getReplyMailRequest(String token, String subject, String body, ReplyData replyData){
        ReplymailRequest replymailRequest = new ReplymailRequest();
        replymailRequest.setToken(token);
        replymailRequest.setReplyTo(replyData.getReplyTo());
        replymailRequest.setReferences(replyData.getReferences());
        replymailRequest.setRecepient(replyData.getTo());
        replymailRequest.setSubject(subject);
        replymailRequest.setBody(body);

        return replymailRequest;
    }
}
