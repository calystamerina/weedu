package com.telkom.weedu.view.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.base.adapter.viewholder.BaseItemViewHolder;
import com.telkom.weedu.data.mapper.edumail.Mail;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.utils.DateUtils;
import com.telkom.weedu.utils.ImageUtils;
import com.telkom.weedu.view.custom.CircleImageView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.LinkedList;

import butterknife.BindView;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class InboxAdapter extends BaseRecyclerAdapter<Mail, InboxAdapter.InboxViewholder>{
    public InboxAdapter(Context context, LinkedList<Mail> data) {
        super(context, data);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_inbox;
    }

    @Override
    public InboxViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InboxViewholder(mContext, getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }

    //Todo replace in the future
    public void addFirst(LinkedList<Mail> items){
        int size = 10;
        for (int i = 0; i < size; i++) {
            if (!items.get(i).getMailId().equalsIgnoreCase(getDatas().get(i).getMailId())){
                getDatas().addFirst(items.get(i));
            }
        }

        notifyDataSetChanged();
    }

    public static class InboxViewholder extends BaseItemViewHolder<Mail>{
        @BindView(R.id.img_user_photo)
        CircleImageView imgUserPhoto;
        @BindView(R.id.tv_sender)
        TextView tvSender;
        @BindView(R.id.tv_subject)
        TextView tvSubject;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_message)
        HtmlTextView tvMessage;
        @BindView(R.id.item_mail_row)
        RelativeLayout rlItem;
        @BindView(R.id.img_item_star)
        ImageView imgStar;

        public InboxViewholder(Context mContext, View itemView,
                               BaseRecyclerAdapter.OnItemClickListener itemClickListener,
                               BaseRecyclerAdapter.OnLongItemClickListener longItemClickListener) {
            super(mContext, itemView, itemClickListener, longItemClickListener);
        }

        @Override
        public void bind(Mail mail) {
            ImageUtils.setImageUrl(mContext, AppConstants.EDUMAIL_IMAGE_DEFAULT, 0, imgUserPhoto);
            tvDate.setText(DateUtils.formatEdumailDate(mail.getDate()));
            tvMessage.setHtml(mail.getBody());
            tvSender.setText(mail.getSender());

            String subject = getSubject(mail);

            tvSubject.setText(subject);

            markAsReadMail();
            if (mail.isUnread()){
                markAsUnreadMail();
            }

            markAsUnFavorite();
            if (mail.isStarred()){
                markAsFavorite();
            }

            imgStar.setVisibility(View.VISIBLE);
            if (mail.isTrashed()){
                imgStar.setVisibility(View.GONE);
            }

        }

        @Nullable
        private String getSubject(Mail mail) {
            String subject = null;
            if (mail.getConversation() != null){
                if (mail.getConversation().size() > 0){
                    subject = mail.getSubject() + " ("+mail.getConversation().size()+")";
                }
            }else{
                subject = mail.getSubject();
            }
            return subject;
        }

        private void markAsUnFavorite() {
            imgStar.setImageResource(R.drawable.ic_star_border);
            imgStar.setColorFilter(ContextCompat.getColor(mContext, R.color.amber600));
        }

        private void markAsFavorite() {
            imgStar.setImageResource(R.drawable.ic_star_black_24dp);
            imgStar.setColorFilter(ContextCompat.getColor(mContext, R.color.amber600));
        }



        private void markAsUnreadMail() {
            if (Build.VERSION.SDK_INT > 22) {
                tvSubject.setTextAppearance(R.style.TextStyleBold);
                tvSender.setTextAppearance(R.style.TextStyleBold);
            }else{
                tvSubject.setTextAppearance(mContext, R.style.TextStyleBold);
                tvSender.setTextAppearance(mContext, R.style.TextStyleBold);
            }
        }

        private void markAsReadMail() {
            if (Build.VERSION.SDK_INT > 22) {
                tvSubject.setTextAppearance(R.style.TextStyleRegular);
                tvSender.setTextAppearance(R.style.TextStyleRegular);
            }else{
                tvSubject.setTextAppearance(mContext, R.style.TextStyleRegular);
                tvSender.setTextAppearance(mContext, R.style.TextStyleRegular);
            }
        }
    }
}
