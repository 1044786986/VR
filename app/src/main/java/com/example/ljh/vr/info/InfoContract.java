package com.example.ljh.vr.info;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

import java.util.List;

public interface InfoContract {
    interface InfoView extends BaseView{
        void showData(InfoBean infoBean);
        void addIntroduce(View view);
        void addViewPager(View view);
        void setPager(int pos);
        int getViewPagerWidth();
        int getViewPagerHeight();
        LinearLayout getHeader();
        void hideProgressBar();
    }

    interface InfoPresenter{
        void initVpAdapter(ViewPager viewPager);
        void getData(Intent intent);
        void analysisIntroduce(String introduce);
        void scrollViewListener(int newY,int oldY);
        void toAlbum();
//        void getIntent(Intent intent);
    }

    interface InfoModel{
        void getData(String id, MyRetrofitCallback callback);
    }

}
