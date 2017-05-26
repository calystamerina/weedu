package com.telkom.weedu.service.downloadattachment;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseService;
import com.telkom.weedu.data.mapper.edumail.Attachment;
import com.telkom.weedu.utils.BundleKeys;
import com.telkom.weedu.view.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by sidiqpermana on 5/14/17.
 */

public class DownloadAttachmentService extends BaseService implements DownloadAttachmentView{
    private Attachment attachment;

    @Inject
    IDownloadAttamentPresenter<DownloadAttachmentView> presenter;

    @Override
    public void onCreate() {
        super.onCreate();
        getServiceComponent().inject(this);
        presenter.onAttach(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        attachment = intent.getParcelableExtra(BundleKeys.KEY_ATTACHMENT);
        int id = (int)System.currentTimeMillis();
        attachment.setId(id);
        presenter.getAttachment(attachment);

        return START_STICKY;
    }

    private void sendNotification(int notificationId, String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Weedu")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @Override
    public void showStartDownloadNotification(Attachment attachment) {
        sendNotification(attachment.getId(), "Download "+attachment.getName());
    }

    @Override
    public void showDownloadSuccessNotification(Attachment attachment, String filePath) {
        sendNotification(attachment.getId(), "File "+attachment.getName()+" is successfully downloaded");
    }

    @Override
    public void showDownloadFailedNotification(Attachment attachment, String message) {
        sendNotification(attachment.getId(), "File "+attachment.getName()+" is failed downloaded");
    }
}
