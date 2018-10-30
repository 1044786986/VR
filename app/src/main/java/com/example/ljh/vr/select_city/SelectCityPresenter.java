package com.example.ljh.vr.select_city;

import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;

public class SelectCityPresenter extends BasePresenter implements SelectCityContract.SelectCityPresenter{
    private SelectCityContract.SelectCityView mSelectCityView;
    private SelectCityContract.SelectCityModel mSelectCityModel;

    public SelectCityPresenter(BaseView baseView) {
        super(baseView);
        this.mSelectCityView = (SelectCityContract.SelectCityView) baseView;
        mSelectCityModel = new SelectCityModel();
    }

}
