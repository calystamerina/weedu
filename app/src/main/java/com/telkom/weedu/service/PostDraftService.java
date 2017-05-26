package com.telkom.weedu.service;

import android.content.Intent;

import com.telkom.weedu.base.BaseService;
import com.telkom.weedu.data.api.model.request.PostmailRequest;
import com.telkom.weedu.utils.BundleKeys;

/**
 * Created by sidiqpermana on 5/2/17.
 */

public class PostDraftService extends BaseService {
    private PostmailRequest postmailRequest;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        postmailRequest = intent.getParcelableExtra(BundleKeys.KEY_DRAFT_EMAIL);

        return START_STICKY;
    }
}
