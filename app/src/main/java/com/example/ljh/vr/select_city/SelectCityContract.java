package com.example.ljh.vr.select_city;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

import java.util.List;

public class SelectCityContract {
    interface SelectCityView extends BaseView{
        void addHotCityView(List<String> list);
        void addRecentlyCityView(List<String> list);
        void addView(List<String> list, LinearLayout linearLayout);
        void addCitySelectListener(TextView tv,String city);
    }

    interface SelectCityPresenter{
        void initRvAdapter(RecyclerView recyclerView, View v1,View v2);
        void moveToPos(int pos,RecyclerView recyclerView);
        void getHotCity();
        void getRecentlyCity();
    }

    interface SelectCityModel{
        void getHotCity(MyRetrofitCallback callback);
    }

    public interface OnSelectCityListener {
        void onSelect(String city);
        void onScrollLetter(int pos);
    }
}
