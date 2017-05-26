package com.telkom.weedu.utils;

import com.telkom.weedu.R;

/**
 * Created by sidiqpermana on 4/5/17.
 */

public final class AppMessages {
    //ALL API ERRORS HERE
    public static final String ERROR_NO_INTERNET = ContextProvider.get().getString(R.string.message_no_internet);
    public static final String ERROR_UNABLE_TO_CONNECT_SERVER = ContextProvider.get().getString(R.string.message_unable_to_connect);
    public static final String ERROR_INVALID_RESPONSE = ContextProvider.get().getString(R.string.message_invalid_response);
    public static final String ERROR_NO_DATA = ContextProvider.get().getString(R.string.message_no_data);
    public static final String ERROR_FILE_NOT_VALID = ContextProvider.get().getString(R.string.error_file_not_valid);

    public static final String ERROR_EDUMAIL_EMPTY_DATA = "error pagination";

    public static final String FIELD_REQUIRED = ContextProvider.get().getString(R.string.message_field_required);
    public static final String PASSWORD_NOT_VALID = ContextProvider.get().getString(R.string.message_password_not_valid);
    public static final String EMAIL_NOT_VALID = ContextProvider.get().getString(R.string.message_valid_email);
    public static final String INFO_TERMS_CONDITION = ContextProvider.get().getString(R.string.message_terms_condition);
    public static final String NEW_PASSWORD_NOT_IDENTICAL = ContextProvider.get().getString(R.string.new_password_not_identical);
    public static final String CHANGE_PASSWORD_SUCCESS = ContextProvider.get().getString(R.string.message_change_password_success);
    public static final String SEND_CONTACT_US_SUCCESS = ContextProvider.get().getString(R.string.message_contact_us_message_sent);
}
