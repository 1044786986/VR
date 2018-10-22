package com.example.ljh.vr.login;

import android.content.Context;

import com.example.ljh.vr._base.MyRetrofitCallback;

public class LoginContract {
    public interface LoginView{
        Context getContext();
        LoginBean getInfo();                    //获取用户名和密码
        LoginBean.LoginCheckBox getCheckBox();  //获取记住密码和自动登录checkbox的状态
        void toRegister();                      //注册
        void toForgetPassWord();                //忘记密码
        void toMain();                          //主页
        void showToast(String string);
        void showProgressBar();
        void hideProgressBar();
        void setTimerText(String s);
        void timerFinish();
        void showTimer();
        void setCbRemember();                   //初始化checkbox的状态
        void setCbAuto();
        void setUsername(String username);     //初始化用户名和密码
        void setPassword(String password);
        boolean isRemember();
        boolean isAutoLogin();
    }

    public interface LoginPresenter{
        void login();
        void msgLogin(String username);
        void startTimer();
        void saveCurUser(LoginBean.LoginResponse response);
        void loginCheckbox(LoginBean loginBean);
        void init();
        void recycle();
    }

    public interface LoginModel{
        void login(LoginBean loginBean, MyRetrofitCallback callBack);   //登录
        void loginCheckbox(Context context, LoginBean.LoginCheckBox loginCheckBox, LoginBean loginBean);//成功登录后更新checkbox状态
        void sendMsg(String username, MyRetrofitCallback callback);
    }

}
