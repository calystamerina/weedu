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
import com.telkom.weedu.data.api.model.History;
import com.telkom.weedu.utils.CommonUtils;
import com.telkom.weedu.utils.DateUtils;
import com.telkom.weedu.utils.ImageUtils;
import com.telkom.weedu.view.detailhistory.DetailHistoryActivity;

import java.util.LinkedList;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Created by ghiyatshanif on 4/25/17.
 */
public class HistoryAdapter extends BaseRecyclerAdapter<History, BaseItemViewHolder<History>> {
    private static final int TYPE_PROGRESS = 1;
    private static final int TYPE_DESCRIPTION = 2;


    public HistoryAdapter(Context context) {
        super(context);
    }

    public HistoryAdapter(Context context, LinkedList<History> data) {
        super(context, data);
    }

    @Override
    public int getItemViewType(int position) {
        return getDatas().get(position).getData().getDescription() == null ? TYPE_PROGRESS : TYPE_DESCRIPTION;
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        switch (viewType) {
            case TYPE_PROGRESS:
                return R.layout.item_progress_dashboard;
            case TYPE_DESCRIPTION:
                return R.layout.item_dashboard_last_viewed;
            default:
                return 0;
        }
    }

    @Override
    public BaseItemViewHolder<History> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PROGRESS:
                return new ProgressViewHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
            case TYPE_DESCRIPTION:
                return new DescriptionViewHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
            default:
                return null;
        }

    }

    class ProgressViewHolder extends BaseItemViewHolder<History> {
        @BindView(R.id.img_history)
        ImageView imgHistory;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_progress_status)
        TextView tvProgressStatus;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_source)
        TextView tvSource;

        History currentItem;

        @BindDrawable(R.drawable.bg_rounded_orange_solid)
        Drawable onProgressDrawable;
        @BindDrawable(R.drawable.bg_rounded_green_solid)
        Drawable completedDrawable;

        public ProgressViewHolder(Context mContext, View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }


        @Override
        public void bind(History history) {
            ImageUtils.setImageUrl(mContext, history.getData().getImage(), imgHistory);
            tvTitle.setText(history.getContentTitle());
            tvProgressStatus.setText(history.getStatus());
            tvSource.setText(history.getProductName());
            tvTime.setText(CommonUtils.getReadableHour(history.getData().getStart()));
            tvDate.setText(DateUtils.getLocalDayName(history.getCreatedAt()) + ", " + DateUtils.getReadableDate(history.getCreatedAt()));

            switch (history.getStatus()) {
                case "Complete":
                    setStatusBackground(completedDrawable);
                    break;
                case "On Going":
                    setStatusBackground(onProgressDrawable);
                    break;

            }

            currentItem = history;
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

        @Override
        public void onClick(View v) {
            super.onClick(v);

            DetailHistoryActivity.start(mContext, currentItem);
        }
    }


    class DescriptionViewHolder extends BaseItemViewHolder<History> {

        @BindView(R.id.img_history)
        ImageView imgHistory;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_source)
        TextView tvSource;

        History currentItem;

        public DescriptionViewHolder(Context mContext, View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }

        @Override
        public void bind(History history) {
            ImageUtils.setImageUrl(mContext, history.getData().getImage(), imgHistory);
            tvDescription.setText(history.getData().getDescription());
            tvTitle.setText(history.getContentTitle());
            tvSource.setText(history.getProductName());
            this.currentItem = history;
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            DetailHistoryActivity.start(mContext, currentItem);

        }
    }
}