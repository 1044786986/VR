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

public class LiveService extends Service{
    public static final int SERVICE_ID = 1001;
    public static final String ID = "LiveService";
    public static final String NAME = "Logistics物流";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("aaa","LiveService.onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("aaa","LiveService.onStartCommand");
        Notification.Builder builder = new Notification.Builder(this);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(ID,NAME,importance);
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId(ID);
            startForeground(SERVICE_ID,builder.build());
            startService(new Intent(this,LiveService2.class));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
//            builder.setSmallIcon(R.mipmap.ic_launcher);
            startForeground(SERVICE_ID,builder.build());
            startService(new Intent(this,LiveService2.class));
        }
        return START_NOT_STICKY;
    }
}
