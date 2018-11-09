package com.example.ljh.vr.normal_picture;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.example.ljh.vr._base.BaseView;

public class NormalPictureContract {

    interface NormalPictureView extends BaseView {

    }

    interface NormalPicturePresenter{
        void getIntent(Intent intent);
        void initVpAdapter(ViewPager viewPager);
    }

    interface NormalPictureModel{

    }

    interface OnProgressBarListener{
        void onShow();
        void onHide();
    }
}
