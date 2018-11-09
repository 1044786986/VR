package com.example.ljh.vr.vr_picture;

import android.content.Intent;
import android.graphics.Bitmap;

import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.CompressUtils;
import com.example.ljh.vr.utils.GetUrlImageUtils;

public class VrPicturePresenter extends BasePresenter implements VrPictureContract.VrPicturePresenter {
    private VrPictureContract.VrPictureView mVrPictureView;
    private VrPictureContract.VrPictureModel mVrPictureModel;

    public VrPicturePresenter(BaseView baseView) {
        super(baseView);
        mVrPictureView = (VrPictureContract.VrPictureView) baseView;
    }


    @Override
    public void getData(Intent intent) {
        String url = intent.getStringExtra(KeyApp.INTENT_KEY_VR_PICTURE);
        GetUrlImageUtils.getUrlImage(url, new GetUrlImageUtils.UrlImageCallback() {
            @Override
            public void onSuccess(byte[] bytes) {
                if(mVrPictureView == null){
                    return;
                }
                mVrPictureView.hideProgressBar();
                Bitmap bitmap = CompressUtils.getInstance(mVrPictureView.getMyContext()).compress(bytes,0,0,1000);
                mVrPictureView.showData(bitmap);
            }

            @Override
            public void onFailed(String error) {
                if(mVrPictureView == null){
                    return;
                }
                mVrPictureView.hideProgressBar();
            }
        });
//        mVrPictureModel.getRecommendData(url, new MyRetrofitCallback() {
//            @Override
//            public void onSuccess(Object o) {
//
//            }
//
//            @Override
//            public void onFailed(String error) {
//
//            }
//        });
    }
}
