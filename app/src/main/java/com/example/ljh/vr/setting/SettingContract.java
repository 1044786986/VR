package com.example.ljh.vr.setting;

import android.content.Context;
import com.example.ljh.vr._base.BaseCallback;
import com.example.ljh.vr._base.BaseView;

public class SettingContract {
    interface SettingView extends BaseView {
        void myFinish();
        Context getMyContext();
    }

    interface SettingPresenter{
        void quitLogin();
        void getCacheSize();
        void clearCache();
    }

    interface SettingModel{
        void getCacheSize(Context context, BaseCallback callback);
        void clearCache(Context context, BaseCallback callback);
    }

//    interface GetCacheSizeCallback{
//        void onSuccess(long size);
//        void onFailed(String );
//    }
}
