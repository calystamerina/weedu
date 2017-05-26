package com.telkom.weedu.view.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.base.adapter.viewholder.BaseItemViewHolder;
import com.telkom.weedu.data.api.model.TopupStep;

import java.util.LinkedList;

import butterknife.BindView;

/**
 * Created by ghiyatshanif on 4/4/17.
 */
public class TopUpAdapter extends BaseRecyclerAdapter<TopupStep, TopUpAdapter.TopUpViewHolder> {

    @BindView(R.id.tv_topup_merchant_name)
    TextView tvTopupMerchant;
    @BindView(R.id.tv_top_up_steps)
    TextView tvTopUpSteps;
    @BindView(R.id.btn_top_up_balance)
    Button btnTopUpBalance;

    public TopUpAdapter(Context context) {
        super(context);
    }

    public TopUpAdapter(Context context, LinkedList<TopupStep> data) {
        super(context, data);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_topup;
    }

    @Override
    public TopUpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopUpViewHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }

    public class TopUpViewHolder extends BaseItemViewHolder<TopupStep> {

        @BindView(R.id.tv_topup_merchant_name)
        TextView tvTopupMerchant;
        @BindView(R.id.tv_top_up_steps)
        TextView tvTopUpSteps;
        @BindView(R.id.btn_top_up_balance)
        Button btnTopUpBalance;
        @BindView(R.id.step_container)
        LinearLayout stepContainer;

        public TopUpViewHolder(Context mContext, View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }

        @Override
        public void bind(TopupStep data) {
            tvTopupMerchant.setText(data.getMerchantName());
            tvTopupMerchant.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            boolean isOpened = View.VISIBLE == stepContainer.getVisibility();
            int properDrawable;

            stepContainer.setVisibility(isOpened ? View.GONE : View.VISIBLE);
            properDrawable = isOpened ? R.drawable.arah_bawah : R.drawable.arah_atas;

            tvTopupMerchant.setCompoundDrawablesWithIntrinsicBounds(0, 0, properDrawable, 0);
        }
    }
}