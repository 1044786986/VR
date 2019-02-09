package com.example.ljh.vr.vr_picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.socks.library.KLog;

import butterknife.BindView;

public class VrPictureActivity extends BaseActivity implements VrPictureContract.VrPictureView {
    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.vrPanoramaView)
    protected VrPanoramaView mVrPanoramaView;

    private VrPicturePresenter mVrPicturePresenter;
    private VrPanoramaView.Options mOptions;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_vr_picture;
    }

    @Override
    public void initView() {
        mOptions = new VrPanoramaView.Options();
        mOptions.inputType = VrPanoramaView.Options.TYPE_MONO;
        mVrPanoramaView.setFullscreenButtonEnabled (false); //隐藏全屏模式按钮
        mVrPanoramaView.setInfoButtonEnabled(false); //设置隐藏最左边信息的按钮
        mVrPanoramaView.setEventListener(new VrPanoramaViewEventListener());
    }

    @Override
    public void initData() {
        mVrPicturePresenter.getData(getIntent());
    }

    @Override
    public BasePresenter bindPresenter() {
        mVrPicturePresenter = new VrPicturePresenter(this);
        return mVrPicturePresenter;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void myFinish() {
        finish();
    }

    @Override
    public void showData(Bitmap bitmap) {
        mVrPanoramaView.loadImageFromBitmap(bitmap,mOptions);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class VrPanoramaViewEventListener extends VrPanoramaEventListener {
        @Override
        public void onLoadSuccess() {//图片加载成功
            KLog.i("onLoadSuccess()");
        }


        @Override
        public void onLoadError(String errorMessage) {//图片加载失败
            KLog.i("onLoadError() = " + errorMessage);
        }

        @Override
        public void onClick() {//当我们点击了VrPanoramaView 时候触发            super.onClick();
//            ShowTipUtils.toastShort(Test2Activity.this,"点击了VrPanoramaView");
            KLog.i("点击了VrPanoramaView");
        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            super.onDisplayModeChanged(newDisplayMode);
            KLog.i("onDisplayModeChanged()");
        }
    }
}
