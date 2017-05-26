package com.telkom.weedu.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;
import com.telkom.weedu.R;
import com.telkom.weedu.base.adapter.BaseListAdapter;
import com.telkom.weedu.base.adapter.viewholder.BaseListViewHolder;
import com.telkom.weedu.data.mapper.edumail.Attachment;
import com.telkom.weedu.data.mapper.edumail.Conversation;
import com.telkom.weedu.event.DownloadAttachmentEvent;
import com.telkom.weedu.event.MailDetailMenuItemEvent;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.utils.ImageUtils;
import com.telkom.weedu.utils.listener.CustomOnItemClickListener;
import com.telkom.weedu.utils.listener.CustomViewClickListener;
import com.telkom.weedu.view.custom.CircleImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sidiqpermana on 4/13/17.
 */

public class MailDetailAdapter extends BaseListAdapter<Conversation, MailDetailAdapter.MailDetailViewHolder>{
    public MailDetailAdapter(Context context, List<Conversation> data) {
        super(context, data);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mail_detail;
    }

    @Override
    public MailDetailViewHolder onCreateViewHolder(View itemView) {
        return new MailDetailViewHolder(mContext, itemView);
    }

    public static class MailDetailViewHolder extends BaseListViewHolder<Conversation>{
        @BindView(R.id.img_user_photo)
        CircleImageView imgUserPhoto;
        @BindView(R.id.tv_sender_name)
        TextView tvSenderName;
        @BindView(R.id.tv_receiver_email)
        TextView tvReceiverEmail;
        @BindView(R.id.tv_sender_date)
        TextView tvSenderDate;
        @BindView(R.id.btn_reply)
        ImageView btnReply;
        @BindView(R.id.btn_more)
        ImageView btnMore;
        @BindView(R.id.tv_email_body)
        WebView tvEmailBody;
        @BindView(R.id.ln_attachments)
        LinearLayout lnAttachments;

        public MailDetailViewHolder(Context mContext, View itemView) {
            super(mContext, itemView);
        }

        @Override
        public void bind(final Conversation convMessageItem) {
            tvEmailBody.getSettings().setJavaScriptEnabled(true);

            ImageUtils.setImageUrl(mContext, convMessageItem.getSenderPhotoUrl(),
                    R.drawable.edumail_default_avatar,
                    R.drawable.edumail_default_avatar,
                    imgUserPhoto);

            tvSenderName.setText(convMessageItem.getSenderName());
            tvReceiverEmail.setText(convMessageItem.getReceiverNames());
            tvSenderDate.setText(convMessageItem.getDate());
            tvEmailBody.loadData(convMessageItem.getEmailBody(), "text/html; charset=utf-8", "utf-8");

            btnMore.setOnClickListener(new CustomViewClickListener(convMessageItem, new CustomViewClickListener.OnViewClickCallback() {
                @Override
                public void onViewClicked(View view, Conversation conversation) {
                    generateSubMenu(mContext, btnMore);
                }
            }));

            btnReply.setOnClickListener(new CustomViewClickListener(convMessageItem, new CustomViewClickListener.OnViewClickCallback() {
                @Override
                public void onViewClicked(View view, Conversation conversation) {
                    EventBus.getDefault().post(new MailDetailMenuItemEvent(AppConstants.COMPOSE_TYPE_REPLY));
                }
            }));

            lnAttachments.setVisibility(View.GONE);
            if (convMessageItem.getAttachments().size() > 0){
                lnAttachments.setVisibility(View.VISIBLE);
                for (int i = 0; i < convMessageItem.getAttachments().size(); i++){
                    Attachment attachment = convMessageItem.getAttachments().get(i);
                    View view = LayoutInflater.from(mContext).inflate(R.layout.item_attachment, null);
                    TextView tvName = (TextView)view.findViewById(R.id.tv_item_name);
                    TextView tvStatus = (TextView)view.findViewById(R.id.tv_item_status);
                    ImageView btnDownload = (ImageView) view.findViewById(R.id.btn_download);
                    btnDownload.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnViewClickCallback() {
                        @Override
                        public void onViewClicked(View view, int position) {
                            EventBus.getDefault().post(new DownloadAttachmentEvent(convMessageItem.getAttachments().get(position)));
                        }
                    }));

                    tvName.setText(attachment.getName());
                    tvStatus.setText("Buka atau Download Attachment ini");

                    lnAttachments.addView(view);
                }
            }
        }

        private void generateSubMenu(Context context, ImageView btnSubMenu){
            DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(context, btnSubMenu);
            DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.menu_sub_mail_detail)
                    .triggerOnAnchorClick(false)
                    .setOnClick(new DroppyClickCallbackInterface() {
                        @Override
                        public void call(View v, int id) {
                            postSubMenuEvent(id);
                        }
                    })
                    .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                        @Override
                        public void call() {

                        }
                    }).setPopupAnimation(new DroppyFadeInAnimation())
                    .setXOffset(5)
                    .setYOffset(5)
                    .build();
            droppyMenu.show();
        }

        private void postSubMenuEvent(int id) {
            String type = null;
            switch (id){
                case R.id.action_reply:
                    type = AppConstants.COMPOSE_TYPE_REPLY;
                    break;

                case R.id.action_reply_all:
                    type = AppConstants.COMPOSE_TYPE_REPLY_ALL;
                    break;

                case R.id.action_forward:
                    type = AppConstants.COMPOSE_TYPE_FORWARD;
                    break;
            }

            EventBus.getDefault().post(new MailDetailMenuItemEvent(type));
        }
    }
}
