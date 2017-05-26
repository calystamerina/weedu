package com.telkom.weedu.data.mapper.edumail;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.telkom.weedu.BuildConfig;
import com.telkom.weedu.data.api.model.edumail.AttachmentItem;
import com.telkom.weedu.data.api.model.edumail.ConvMessageItem;
import com.telkom.weedu.data.api.model.edumail.ResultItem;
import com.telkom.weedu.data.api.model.edumail.SearchResultItem;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.utils.CommonUtils;
import com.telkom.weedu.utils.DateUtils;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class Mail implements Parcelable {
    private String mailId;
    private String sender;
    private String subject;
    private String date;
    private String body;
    private boolean isStarred;
    private boolean isUnread;
    private boolean isDeleted;
    private boolean isTrashed;
    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private ArrayList<Conversation> conversation;

    public Mail() {
    }

    public ArrayList<Conversation> getConversation() {
        return conversation;
    }

    public void setConversation(ArrayList<Conversation> conversation) {
        this.conversation = conversation;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public void setStarred(boolean starred) {
        isStarred = starred;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public boolean isUnread() {
        return isUnread;
    }

    public void setUnread(boolean unread) {
        isUnread = unread;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isTrashed() {
        return isTrashed;
    }

    public void setTrashed(boolean trashed) {
        isTrashed = trashed;
    }

    public static Mail getMail(ResultItem resultItem){

        int pos = resultItem.getListConvMessageItems().size() - 1;
        String latestImage = TextUtils.isEmpty(resultItem.getListConvMessageItems().get(pos).getContent().getTextBody()) ?
                resultItem.getListConvMessageItems().get(pos).getContent().getHeaders().getListFrom().get(0) :
                resultItem.getListConvMessageItems().get(pos).getContent().getTextBody();

        Mail mail = new Mail();
        mail.setSubject(resultItem.getSubject());
        mail.setBody(latestImage);
        mail.setDate(resultItem.getDate());
        mail.setDeleted(resultItem.isDeleted());
        mail.setTrashed(resultItem.isTrashed());
        mail.setStarred(resultItem.isStarred());
        mail.setUnread(resultItem.isUnread());

        if (resultItem.getListFromItem() != null){
            if (resultItem.getListFromItem().size() > 0){
                mail.setSender(resultItem.getListFromItem().get(0).getMailbox());
            }
        }

        mail.setStarred(resultItem.isStarred());
        mail.setMailId(resultItem.getId());

        ArrayList<Conversation> conversations = new ArrayList<>();

        for (ConvMessageItem i : resultItem.getListConvMessageItems()){
            Conversation conversation = new Conversation();
            conversation.setDate(DateUtils.getReadableEdumailDate(i.getCreated()));
            conversation.setEmailBody(TextUtils.isEmpty(i.getContent().getHtmlBody()) ?
                i.getContent().getTextBody() : i.getContent().getHtmlBody());
            conversation.setReceiverNames(CommonUtils.getReceiverEmails(i.getListTo(), i.getContent().getHeaders().getListTo()));

            String senderName, senderPhotoUrl;
            if (i.getUser() != null && !TextUtils.isEmpty(i.getUser().getFirstname())){
                senderName = i.getUser().getFirstname()+" "+i.getUser().getLastname();
                senderPhotoUrl = BuildConfig.EDUMAIL_URL+i.getUser().getAvatar();
            }else{
                senderName = i.getContent().getHeaders().getListFrom().get(0).split(" ")[0].replace("\"", "");
                senderPhotoUrl = AppConstants.EDUMAIL_IMAGE_DEFAULT;
            }

            Timber.d("PHOTO URL", senderPhotoUrl);

            conversation.setSenderPhotoUrl(senderPhotoUrl);
            conversation.setSenderName(senderName);

            ArrayList<Attachment> listAttachments = new ArrayList<>();
            if (i.getListAttachment().size() > 0){
                for (AttachmentItem attachmentItem : i.getListAttachment()){
                    listAttachments.add(new Attachment(attachmentItem.getFileName(), attachmentItem.getUrlPath()));
                }
            }
            conversation.setAttachments(listAttachments);
            conversations.add(conversation);
        }

        mail.setConversation(conversations);

        return mail;
    }

    public static Mail getMail(SearchResultItem searchResultItem){

        String mailBody = TextUtils.isEmpty(searchResultItem.getContent().getTextBody()) ?
                searchResultItem.getContent().getHtmlBody() :
                searchResultItem.getContent().getTextBody();

        String sender = searchResultItem.getFrom() != null ?
                searchResultItem.getFrom().getMailbox()+"@"+searchResultItem.getFrom().getDomain() :
                "Anonymous";

        Mail mail = new Mail();
        mail.setSubject(searchResultItem.getSubject());
        mail.setBody(mailBody);
        mail.setSender(sender);
        mail.setDate(searchResultItem.getCreated());
        mail.setDeleted(searchResultItem.isDeleted());
        mail.setTrashed(searchResultItem.isTrashed());
        mail.setStarred(searchResultItem.isStarred());
        mail.setUnread(searchResultItem.isUnread());
        mail.setType(searchResultItem.getType());
        mail.setStarred(searchResultItem.isStarred());
        mail.setMailId(searchResultItem.getConvId());

        return mail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mailId);
        dest.writeString(this.sender);
        dest.writeString(this.subject);
        dest.writeString(this.date);
        dest.writeString(this.body);
        dest.writeByte(this.isStarred ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isUnread ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDeleted ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTrashed ? (byte) 1 : (byte) 0);
        dest.writeString(this.type);
        dest.writeTypedList(this.conversation);
    }

    protected Mail(Parcel in) {
        this.mailId = in.readString();
        this.sender = in.readString();
        this.subject = in.readString();
        this.date = in.readString();
        this.body = in.readString();
        this.isStarred = in.readByte() != 0;
        this.isUnread = in.readByte() != 0;
        this.isDeleted = in.readByte() != 0;
        this.isTrashed = in.readByte() != 0;
        this.type = in.readString();
        this.conversation = in.createTypedArrayList(Conversation.CREATOR);
    }

    public static final Creator<Mail> CREATOR = new Creator<Mail>() {
        @Override
        public Mail createFromParcel(Parcel source) {
            return new Mail(source);
        }

        @Override
        public Mail[] newArray(int size) {
            return new Mail[size];
        }
    };
}
