package com.example.ljh.vr._base;

public abstract class BasePresenter{
    public BaseView baseView;

    public BasePresenter(BaseView baseView){
        this.baseView = baseView;
    }

    protected boolean checkViewNull(){
        return baseView == null;
    }

    protected void recycle(){
        baseView = null;
    }
}
