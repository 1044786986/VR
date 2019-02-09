package com.example.ljh.vr.home;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

public class HomeContract {
    interface HomeView extends BaseView{
        void setHeaderImage(Bitmap bitmap);
        void setTvCity(String city);
        void getCityData(String city);
        void showProgressBar();
        void hideProgressBar();
        void showNullTip();
        void hideNullTip();
    }

    interface HomePresenter{
        void initRv(RecyclerView recyclerView);
        void getRecommendData();
        void getCityData(String city);
    }

    interface HomeModel{
        void getRecommendData(MyRetrofitCallback callback);
        void getCityData(String city,MyRetrofitCallback callback);
    }
}
