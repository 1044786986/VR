package com.example.ljh.vr.vr_picture;

import android.content.Intent;
import android.graphics.Bitmap;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

public class VrPictureContract {
    interface VrPictureView extends BaseView {
        void showData(Bitmap bitmap);
    }

    interface VrPicturePresenter{
        void getData(Intent intent);
    }

    interface VrPictureModel{
        void getData(String url, MyRetrofitCallback callback);
    }
}
