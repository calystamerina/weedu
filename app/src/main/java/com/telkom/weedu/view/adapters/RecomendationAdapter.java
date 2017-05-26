package com.telkom.weedu.view.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.base.adapter.viewholder.BaseItemViewHolder;
import com.telkom.weedu.data.api.model.Recomendation;
import com.telkom.weedu.utils.ImageUtils;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public class RecomendationAdapter
        extends BaseRecyclerAdapter<Recomendation, RecomendationAdapter.RecomendationViewHolder> {


    public RecomendationAdapter(Context context) {
        super(context);
    }

    public RecomendationAdapter(Context context, LinkedList<Recomendation> data) {
        super(context, data);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_recomendation;
    }

    @Override
    public RecomendationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecomendationViewHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }

    public class RecomendationViewHolder extends BaseItemViewHolder<Recomendation> {

        @BindView(R.id.img_recomendation)
        ImageView imgRecomendation;
        @BindView(R.id.tv_recomendation_title)
        TextView tvRecomendationTitle;
        @BindView(R.id.tv_recomendation_source)
        TextView tvRecomendationSource;

        public RecomendationViewHolder(Context mContext, View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }

        @Override
        public void bind(Recomendation recomendation) {
            tvRecomendationSource.setText(recomendation.getSource());
            tvRecomendationTitle.setText(recomendation.getTitle());
            ImageUtils.setImageUrl(mContext, recomendation.getImage(),imgRecomendation);

        }
    }
}
