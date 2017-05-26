package com.telkom.weedu.view.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.base.adapter.viewholder.BaseItemViewHolder;
import com.telkom.weedu.data.api.model.LastViewed;
import com.telkom.weedu.utils.ImageUtils;

import java.util.LinkedList;

import butterknife.BindView;

/**
 * Created by ghiyatshanif on 3/30/17.
 */
public class LastViewedAdapter extends BaseRecyclerAdapter<LastViewed, LastViewedAdapter.LastViewedViewHolder> {

    public LastViewedAdapter(Context context) {
        super(context);
    }

    public LastViewedAdapter(Context context, LinkedList<LastViewed> data) {
        super(context, data);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_dashboard_last_viewed;
    }

    @Override
    public LastViewedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LastViewedViewHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }

    public class LastViewedViewHolder extends BaseItemViewHolder<LastViewed> {

        @BindView(R.id.img_history)
        ImageView imgProgress;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_source)
        TextView tvSource;

        public LastViewedViewHolder(Context mContext, View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }

        @Override
        public void bind(LastViewed data) {
            ImageUtils.setImage(mContext, data.getImage(), imgProgress);
            tvTitle.setText(data.getTitle());
            tvDescription.setText(data.getDescription());
            tvSource.setText(data.getSource());
        }
    }
}