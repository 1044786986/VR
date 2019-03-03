package com.example.ljh.vr.select_city;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

import java.util.List;

public class SelectCityContract{
    interface SelectCityView extends BaseView{
        void onSelectCity(String city);
    }

    interface SelectCityPresenter{
        void initRvAdapter(RecyclerView recyclerView, View v1,View v2);
        void initGdAdapter(GridView gdHotCity,GridView gdRecently);
        void moveToPos(int pos,RecyclerView recyclerView);
        void getHotCity();
        void getRecentlyCity();
        void addRecentlyCity(String city);
    }

    interface SelectCityModel{
        void getHotCity(MyRetrofitCallback callback);
        void addRecentlyCity(String city);
    }

    public interface OnSelectCityListener {
        void onSelectCity(String city);
        void onScrollLetter(int pos);
    }
}
