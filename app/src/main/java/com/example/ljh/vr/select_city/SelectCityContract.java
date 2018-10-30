package com.example.ljh.vr.select_city;

import android.support.v7.widget.RecyclerView;

import com.example.ljh.vr._base.BaseView;

public class SelectCityContract {
    interface SelectCityView extends BaseView{

    }

    interface SelectCityPresenter{
        void initRvAdapter(RecyclerView recyclerView);
        void movePos(int pos,String city);
    }

    interface SelectCityModel{

    }

    public interface OnSelectCityListener {
        void onSelect(String city);
    }
}
