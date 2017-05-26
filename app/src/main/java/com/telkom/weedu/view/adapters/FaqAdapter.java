package com.telkom.weedu.view.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.base.adapter.viewholder.BaseItemViewHolder;
import com.telkom.weedu.data.api.model.Faq;

import java.util.LinkedList;

import butterknife.BindView;

/**
 * Created by ghiyatshanif on 4/4/17.
 */
public class FaqAdapter extends BaseRecyclerAdapter<Faq, FaqAdapter.FaqViewHolder> {

    public FaqAdapter(Context context) {
        super(context);
    }

    public FaqAdapter(Context context, LinkedList<Faq> data) {
        super(context, data);
    }


    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_faq;
    }

    @Override
    public FaqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FaqViewHolder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }

    public class FaqViewHolder extends BaseItemViewHolder<Faq> {

        @BindView(R.id.tv_question)
        TextView tvQuestion;
        @BindView(R.id.tv_answer)
        TextView tvAnswer;

        public FaqViewHolder(Context mContext, View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }

        @Override
        public void bind(Faq data) {
            tvQuestion.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            switch (v.getId()) {
                case R.id.tv_question:
                    expandAnswer();
                    break;
            }
        }

        private void expandAnswer() {
            boolean isOpened = View.VISIBLE == tvAnswer.getVisibility();
            int properDrawable;

            tvAnswer.setVisibility(isOpened ? View.GONE : View.VISIBLE);
            properDrawable = isOpened ? R.drawable.arah_bawah : R.drawable.arah_atas;

            tvQuestion.setCompoundDrawablesWithIntrinsicBounds(0, 0, properDrawable, 0);
        }

    }
}