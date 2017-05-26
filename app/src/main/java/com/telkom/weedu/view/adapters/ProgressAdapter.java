package com.telkom.weedu.view.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.base.adapter.viewholder.BaseItemViewHolder;
import com.telkom.weedu.data.api.model.Progress;
import com.telkom.weedu.utils.ImageUtils;

import java.util.LinkedList;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Created by ghiyatshanif on 3/30/17.
 */
public class ProgressAdapter extends BaseRecyclerAdapter<Progress, ProgressAdapter.ProgressViewHolder> {
    int haha;

    public ProgressAdapter(Context context) {
        super(context);
    }

    public ProgressAdapter(Context context, LinkedList<Progress> data) {
        super(context, data);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_progress_dashboard;
    }

    @Override
    public ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProgressViewHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }

    public class ProgressViewHolder extends BaseItemViewHolder<Progress> {

        @BindView(R.id.img_history)
        ImageView imgProgress;
        @BindView(R.id.tv_title)
        TextView tvProgressTitle;
        @BindView(R.id.tv_progress_status)
        TextView tvProgressStatus;
        @BindView(R.id.tv_date)
        TextView tvProgressDate;
        @BindView(R.id.tv_time)
        TextView tvProgressTime;
        @BindView(R.id.tv_source)
        TextView tvProgressSource;

        @BindDrawable(R.drawable.bg_rounded_orange_solid)
        Drawable onProgressDrawable;
        @BindDrawable(R.drawable.bg_rounded_green_solid)
        Drawable completedDrawable;

        public ProgressViewHolder(Context mContext, View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }

        @Override
        public void bind(Progress data) {
            ImageUtils.setImage(mContext, data.getImage(), imgProgress);
            tvProgressTitle.setText(data.getTitle());
            tvProgressDate.setText(data.getDate());
            tvProgressTime.setText(data.getTime());
            tvProgressSource.setText(data.getSource());

            switch (data.getProgress()) {
                case 0:
                    setStatusBackground(onProgressDrawable);
                    tvProgressStatus.setText(R.string.label_on_progress);
                    break;
                case 1:
                    setStatusBackground(completedDrawable);
                    tvProgressStatus.setText(R.string.label_completed);
                    break;

            }
        }

        @SuppressWarnings("deprecation")
        protected void setStatusBackground(Drawable backgroundDrawable) {

            int lP = tvProgressStatus.getPaddingLeft();
            int rP = tvProgressStatus.getPaddingRight();
            int tP = tvProgressStatus.getPaddingTop();
            int bP = tvProgressStatus.getPaddingBottom();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                tvProgressStatus.setBackgroundDrawable(backgroundDrawable);
            } else {
                tvProgressStatus.setBackground(backgroundDrawable);
            }

            tvProgressStatus.setPadding(lP, tP, rP, bP);
        }
    }
}