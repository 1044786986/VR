package com.example.ljh.vr.home;

import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;

public class HomePresenter extends BasePresenter implements HomeContract.HomePresenter{
    private HomeContract.HomeView mHomeView;
    private HomeContract.HomeModel mHomeModel;

    public HomePresenter(BaseView baseView) {
        super(baseView);
        mHomeModel = new HomeModel();
        mHomeView = (HomeContract.HomeView) baseView;
    }


}
