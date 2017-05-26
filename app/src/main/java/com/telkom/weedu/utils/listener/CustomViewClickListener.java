package com.telkom.weedu.utils.listener;

import android.view.View;

import com.telkom.weedu.data.mapper.edumail.Conversation;

/**
 * Created by sidiqpermana on 4/23/17.
 */

public class CustomViewClickListener implements View.OnClickListener{
    private Conversation conversation;
    private OnViewClickCallback onViewClickCallback;

    public CustomViewClickListener(Conversation conversation, OnViewClickCallback onViewClickCallback) {
        this.conversation = conversation;
        this.onViewClickCallback = onViewClickCallback;
    }

    @Override
    public void onClick(View view) {
        onViewClickCallback.onViewClicked(view, conversation);
    }

    public interface OnViewClickCallback{
        void onViewClicked(View view, Conversation conversation);
    }
}
