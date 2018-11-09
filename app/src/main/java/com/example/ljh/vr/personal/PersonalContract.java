package com.example.ljh.vr.personal;

import android.content.Context;

import com.example.ljh.vr._base.BaseView;

public class PersonalContract {
    interface PersonalView extends BaseView {
        void showUsername(String username); //显示用户名
        void hideUsername();                //隐藏用户名
        void showTvLogin();                 //显示登录按钮
        void hideTvLogin();                 //隐藏登录按钮
        void checkLoginStatus();            //登录后刷新登录状态
        Context getMyContext();
    }

    interface PersonalPresenter{
        void checkLoginStatus();            //检查登录状态
    }

    interface PersonalModel{

    }
}
