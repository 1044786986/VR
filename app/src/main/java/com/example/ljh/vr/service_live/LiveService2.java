package com.example.ljh.vr.service_live;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ljh on 2018/4/2.
 */

public class LiveService2 extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("aaa","LiveService2.onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("aaa","LiveService2.onStartCommand");
        Notification.Builder builder = new Notification.Builder(this);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(LiveService.ID,LiveService.NAME,importance);
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId(LiveService.ID);
            startForeground(LiveService.SERVICE_ID,builder.build());
            try {
                Thread.sleep(500);
                stopForeground(true);
//                notificationManager =
//                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(LiveService.SERVICE_ID);
//                NotificationChannel notificationChannel1 = notificationManager.getNotificationChannel(LiveService.ID);
                notificationManager.deleteNotificationChannel(LiveService.ID);
                stopSelf();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            //发送与LiveService中ID相同的Notification，然后将其取消并取消自己的前台显示
//            Notification.Builder builder = new Notification.Builder(this);
//            builder.setSmallIcon(R.mipmap.ic_launcher);
            startForeground(LiveService.SERVICE_ID,builder.build());
            try {
                Thread.sleep(1);
                stopForeground(true);
//                NotificationManager notificationManager =
//                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(LiveService.SERVICE_ID);
                stopSelf();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return START_NOT_STICKY;
    }
}
