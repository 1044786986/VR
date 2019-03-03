package com.example.ljh.vr._application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.example.ljh.vr.R;
import com.socks.library.KLog;

public class MyApplication extends Application{
    public static final int SHARE_HEADER_SIZE = 20; //分享页面头像图片尺寸
    public static final int NORMAL_NORMAL_PICTURE_SIZE = 200;
    public static final int NORMAL_SMALL_PICTURE_SIZE = 50;
    public static final int GLIDE_LOAD_FAILED_IMG = R.mipmap.load_img_failed_gray_300;
    public static final int GLIDE_LOADING_IMG = R.mipmap.image_loading_gray_300;

    private static Application application;
    public static int activityCount = 0;
    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {
            activityCount++;
//            Log.i("aaa","MyApplication.onActivityStart()");
        }

        @Override
        public void onActivityResumed(Activity activity) {
//            Log.i("aaa","MyApplication.onActivityResumed()");
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
//            Log.i("aaa","MyApplication.onActivityStop()");
            activityCount--;
            if(activityCount == 0){

            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        // Normal app init code...
    }

//    public static boolean isForeground1(){
//        return isForeground;
//    }

    /**
     * 防止fragment中getContext返回null对象，导致程序崩溃
     * 此方法有内存溢出的风险
     */
    public static Application getInstance(){
        return application;
    }
}
