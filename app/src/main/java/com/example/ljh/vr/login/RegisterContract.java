package com.example.ljh.vr.login;

import android.content.Context;

import com.example.ljh.vr._base.MyRetrofitCallback;

public class RegisterContract {

    public interface RegisterView{
        void setTimerText(String s);
        void timerFinish();
        Context getContext();
        void showProgressBar();
        void hideProgressBar();
        void finishThis();                      //关闭页面
        RegisterRequestBean getRegisterInfo();  //获取注册信息
    }

    public interface RegisterPresenter{
        void register();
        void getMsgCode(String username);
        void startTimer();
        RegisterView getView();
        void recycle();
    }

    public interface RegisterModel{
        void register(RegisterRequestBean registerRequestBean, MyRetrofitCallback callBack);
        void getMsgCode(String username, MyRetrofitCallback callback);
    }
}
