package com.example.ljh.vr.info;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

public class AlbumContract {
    interface AlbumView extends BaseView {
        void showProgressBar();
        void hideProgressBar();
        void showNullTip();
        void hideNullTip();
    }

    interface AlbumPresenter{
        void initVpAdapter(ViewPager viewPager, TabLayout tabLayout);
        void initRvAdapter(RecyclerView rvNormal,RecyclerView rvVr,RecyclerView rvVideo);
        void getNormalData(RecyclerView recyclerView);
        void getVrData(RecyclerView recyclerView);
        void getVideoData();
        void getIntent(Intent intent);
//        void recycleBitmapList(int type);
    }

    interface AlbumModel{
        void getNormalData(String id, MyRetrofitCallback callback);
        void getVrData(String id,MyRetrofitCallback callback);
        void getVideoData(String id,MyRetrofitCallback callback);
    }
}
