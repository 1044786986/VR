package com.example.ljh.vr.login;

import android.content.Context;

import com.example.ljh.vr._base.MyRetrofitCallback;

public class RegisterContract {

    public interface RegisterView{
        Context getContext();
        void showProgressBar();
        void hideProgressBar();
        void showToast(String string);
        void finishThis();                      //关闭页面
        RegisterRequestBean getRegisterInfo();  //获取注册信息
    }

    public interface RegisterPresenter{
        void register();
        RegisterView getView();
        void recycle();
    }

    public interface RegisterModel{
        void register(RegisterRequestBean registerRequestBean, MyRetrofitCallback callBack);
    }
}
