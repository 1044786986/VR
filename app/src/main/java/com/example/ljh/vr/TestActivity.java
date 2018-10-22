package com.example.ljh.vr;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.vrPanoramaView)
    protected VrPanoramaView vrPanoramaView;
    @BindView(R.id.button)
    protected Button button;
//    @BindView(R.id.gvrView)
//    protected GvrView gvrView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        ButterKnife.bind(this);
        VrPanoramaView.Options options = new VrPanoramaView.Options();
//        options.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        options.inputType = VrPanoramaView.Options.TYPE_MONO;
        vrPanoramaView.setFullscreenButtonEnabled (false); //隐藏全屏模式按钮
        vrPanoramaView.setInfoButtonEnabled(false); //设置隐藏最左边信息的按钮
//        vrPanoramaView.setStereoModeButtonEnabled(false); //设置隐藏立体模型的按钮
        vrPanoramaView.setEventListener(new ActivityEventListener()); //设置监听
        //加载本地的图片源
        RecyclerView recyclerView;

//        vrPanoramaView.loadImageFromBitmap(CompressUtils.getInstance(this)
//                .compress(BitmapFactory.decodeResource(getResources(),R.drawable.vr2),
//                        0, (int) getResources().getDimension(R.dimen.dp_250)),options);
        vrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.vr2),options);
//        Bitmap bitmap = CompressUtils.getInstance(this)
//                .compress(BitmapFactory.decodeResource(getResources(),R.drawable.vr2),
//                        0, (int) getResources().getDimension(R.dimen.dp_250));
//        KLog.i(bitmap.getByteCount()+"");
//        gvrView.setTransitionViewEnabled(true);
//        gvrView.setStereoModeEnabled(true);
//        HeadTransform
//        WindowManager windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
//        ImageView imageView = new ImageView(getApplicationContext());
//        imageView.setImageResource(R.mipmap.ic_launcher);
//        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
//        lpWindow.width = 10;
//        lpWindow.height = 10;
////        lpWindow.type = WindowManager.LayoutParams.TYPE_TOAST;
//        lpWindow.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
//        lpWindow.alpha = 1;
//        lpWindow.format = PixelFormat.TRANSLUCENT;
//        ViewGroup.LayoutParams lpImage = new ViewGroup.LayoutParams(20,20);
////        lpImage.height = 10;
////        lpImage.width = 10;
//        imageView.setLayoutParams(lpImage);
//        windowManager.addView(imageView,lpWindow);
    }

    @Override
    @OnClick(R.id.button)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                KLog.i("button.click()");
                break;
        }
    }

    private class ActivityEventListener extends VrPanoramaEventListener {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mHandler.removeCallbacksAndMessages(null);
        vrPanoramaView.setEventListener(null);
    }
}
