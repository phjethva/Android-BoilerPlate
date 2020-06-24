package com.phjethva.boilerplate.java.data.services.firebase_messaging;

/*
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pjetapps.zyxwvuts.R;
import com.pjetapps.zyxwvuts.j.data.AppConst;
import com.pjetapps.zyxwvuts.j.ui.activities.main.MainActivity;
import com.pjetapps.zyxwvuts.j.utils.UtilSharedPref;

import java.util.Date;

public class FCMService extends FirebaseMessagingService {

    public static final String CHANNEL_ID = "com.phjethva.zyxwvuts";
    public static final String CHANNEL_NAME = "Zyxw Vuts";
    private NotificationManager notMgrOri;

    private FirebaseAnalytics fa;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        UtilSharedPref.putSPStr(getApplicationContext(), AppConst.FCM_TKN, token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        fa = FirebaseAnalytics.getInstance(this);
        fa.logEvent(AppConst.fcm_rcv, null);

        notMgrOri = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        boolean isAppOn = UtilSharedPref.getSPBol(getApplicationContext(), AppConst.APP_ON);
        String cat = remoteMessage.getData().get("fcm_param1");
        String sts = remoteMessage.getData().get("fcm_param2");
        sendNotification(cat, sts, isAppOn);
    }

    private void sendNotification(String cat, String sts, boolean isAppOn) {
        if (Build.VERSION.SDK_INT >= 26) {
            if (!isAppOn) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, notMgrOri.IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.setShowBadge(true);
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                notMgrOri.createNotificationChannel(notificationChannel);

                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(cat)
                        .setContentText(sts)
                        .setAutoCancel(true)
                        .setTicker(getString(R.string.app_name))
                        .setContentIntent(pendingIntent)
                        .setChannelId(CHANNEL_ID);
                Notification notification = builder.build();
                RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.lay_notification);
                contentView.setTextViewText(R.id.tv_ttl, cat);
                contentView.setTextViewText(R.id.tv_msg, sts);
                notification.contentView = contentView;
                if (Build.VERSION.SDK_INT >= 16) {
                    RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.lay_notification_ext);
                    expandedView.setTextViewText(R.id.tv_ttl_ext, cat);
                    expandedView.setTextViewText(R.id.tv_msg_ext, sts);
                    notification.bigContentView = expandedView;
                }
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), notification);
            } else {
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, notMgrOri.IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.setShowBadge(true);
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                notMgrOri.createNotificationChannel(notificationChannel);
                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(cat)
                        .setContentText(sts)
                        .setAutoCancel(true)
                        .setTicker(getString(R.string.app_name))
                        .setChannelId(CHANNEL_ID);
                Notification notification = builder.build();
                RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.lay_notification);
                contentView.setTextViewText(R.id.tv_ttl, cat);
                contentView.setTextViewText(R.id.tv_msg, sts);
                notification.contentView = contentView;
                if (Build.VERSION.SDK_INT >= 16) {
                    RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.lay_notification_ext);
                    expandedView.setTextViewText(R.id.tv_ttl_ext, cat);
                    expandedView.setTextViewText(R.id.tv_msg_ext, sts);
                    notification.bigContentView = expandedView;
                }
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), notification);
            }
        } else {
            if (!isAppOn) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(cat)
                        .setContentText(sts)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setTicker(getString(R.string.app_name));
                Notification notification = builder.build();
                RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.lay_notification);
                contentView.setTextViewText(R.id.tv_ttl, cat);
                contentView.setTextViewText(R.id.tv_msg, sts);
                notification.contentView = contentView;
                if (Build.VERSION.SDK_INT >= 16) {
                    RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.lay_notification_ext);
                    expandedView.setTextViewText(R.id.tv_ttl_ext, cat);
                    expandedView.setTextViewText(R.id.tv_msg_ext, sts);
                    notification.bigContentView = expandedView;
                }
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), notification);
            } else {
                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(cat)
                        .setContentText(sts)
                        .setAutoCancel(true)
                        .setTicker(getString(R.string.app_name));
                Notification notification = builder.build();
                RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.lay_notification);
                contentView.setTextViewText(R.id.tv_ttl, cat);
                contentView.setTextViewText(R.id.tv_msg, sts);
                notification.contentView = contentView;
                if (Build.VERSION.SDK_INT >= 16) {
                    RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.lay_notification_ext);
                    expandedView.setTextViewText(R.id.tv_ttl_ext, cat);
                    expandedView.setTextViewText(R.id.tv_msg_ext, sts);
                    notification.bigContentView = expandedView;
                }
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), notification);
            }
        }

    }

}
*/