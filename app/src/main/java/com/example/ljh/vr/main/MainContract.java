package com.example.ljh.vr.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ljh.vr._base.BaseView;

public class MainContract {
    interface MainView extends BaseView{
        void changeColor(int pos);                  //改变底部导航栏颜色
        void setAllNormal();                        //初始化底部栏所有颜色
        void showBottomNavigation();
//        void hideBottomNavigation();
        FragmentManager getMyFragmentManager();
    }

    interface MainPresenter{
        void selectPage(int pos);                       //切换页面
        void showFragment(int pos);                     //展示fragment
        void hideFragment(FragmentTransaction ft);
        void showLauncherFragment();                    //显示启动页面
        void removeLauncherFragment();                  //隐藏启动页面
        void startServiceLive();                        //开启应用保活
        void permissionSuccess();                       //检查授权成功后调用
        void sureQuit(int code);                        //再次确定是否退出程序
        void reLoadView();                              //重新加载页面
    }

    interface MainModel{

    }
}
