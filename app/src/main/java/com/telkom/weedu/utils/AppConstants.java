package com.telkom.weedu.utils;


/**
 * Created by ghiyatshanif on 2/21/17.
 * purpose : put all constant variable here
 * note : separate all keys of classes by enter
 */

public final class AppConstants {

    // TODO: 3/3/17 change to your app need

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    //todo need to be replaced by real token
    public static final String EDUMAIL_MOCK_TOKEN = "ckR4p9Tt2DyhDus9Q9CAu1o8ebs6MTQ5MjczNjkyMjMzMTM3ODEyNA==";

    public static final String EDUMAIL_TYPE_INBOX = "inbox";
    public static final String EDUMAIL_TYPE_SENT = "sent";
    public static final String EDUMAIL_TYPE_STARRED = "starred";
    public static final String EDUMAIL_TYPE_DRAFT = "drafts";
    public static final String EDUMAIL_TYPE_TRASH = "trash";
    public static final String EDUMAIL_TYPE_SEARCH = "search";
    public static final int DEFAULT_PER_PAGE_COUNT = 10;
    public static final int EDUMAIL_API_STATUS_CODE_SUCCESS = 200;
    public static final String EDUMAIL_IMAGE_DEFAULT = "https://edumail.id/public/picture/default-avatar.png";
    public static final String EDUMAIL_TRASH_DIRECTORY = "trash";
    public static final String COMPOSE_TYPE_NEW = "compose_new";
    public static final String COMPOSE_TYPE_REPLY = "compose_reply";
    public static final String COMPOSE_TYPE_REPLY_ALL = "compose_reply_all";
    public static final String COMPOSE_TYPE_FORWARD = "compose_forward";
    public static final String EDUMAIL_ADD_REMOVE_FAVORITE = "add_remove_favorite";
    public static final String EDUMAIL_ATTACHMENT_ON_UPLOAD_PROGRESS = "attachment_on_upload_progress";
    public static final String EDUMAIL_ATTACHMENT_ON_UPLOAD_FINISH = "attachment_on_upload_finish";
    public static final String EDUMAIL_ATTACHMENT_FAILED_TO_UPLOAD = "attachment_failed_to_upload";


    public static final String TERMS_AND_CONDITION_URL = "http://weedu.nusantarabetastudio.com/term.html";
    public static final String FAQ_URL = "http://weedu.nusantarabetastudio.com/faq.html";


    public static final String DB_NAME = "myapp_prefs";
    public static final String PREF_NAME = "myapp_prefs";

    public static final long NULL_INDEX = -1L;

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static final String SOURCE_QBACA = "QBaca";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}