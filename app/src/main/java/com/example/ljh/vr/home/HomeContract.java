package com.example.ljh.vr.home;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.INullTip;
import com.example.ljh.vr._base.MyRetrofitCallback;

public class HomeContract {
    interface HomeView extends BaseView,INullTip {
        void setHeaderImage(Bitmap bitmap);
        void setCurCity(String city);
        void setTvCity(String city);
        void getCityData(String city);
        void showProgressBar();
        void hideProgressBar();
    }

    interface HomePresenter{
        void initRv(RecyclerView recyclerView);
        void getData();
        void getRecommendData();
        void getCityData();
        void setCurCity(String city);
    }

    interface HomeModel{
        void getRecommendData(MyRetrofitCallback callback);
        void getCityData(String city,MyRetrofitCallback callback);
    }
}
