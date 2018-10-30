package com.example.ljh.vr.home;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

public class HomeContract {
    interface HomeView extends BaseView{
        void setHeaderImage(Bitmap bitmap);
        void setTvCity(String city);
    }

    interface HomePresenter{
        void initRv(RecyclerView recyclerView);
        void getData();
    }

    interface HomeModel{
        void getData(String id,MyRetrofitCallback callback);
    }
}
