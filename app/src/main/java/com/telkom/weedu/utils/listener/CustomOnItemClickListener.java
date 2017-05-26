package com.telkom.weedu.utils.listener;

import android.view.View;

/**
 * Created by sidiqpermana on 4/30/17.
 */

public class CustomOnItemClickListener implements View.OnClickListener{
    private int position;
    private OnViewClickCallback onViewClickCallback;

    public CustomOnItemClickListener(int position, OnViewClickCallback onViewClickCallback) {
        this.position = position;
        this.onViewClickCallback = onViewClickCallback;
    }

    @Override
    public void onClick(View view) {
        onViewClickCallback.onViewClicked(view, position);
    }

    public interface OnViewClickCallback{
        void onViewClicked(View view, int position);
    }
}
