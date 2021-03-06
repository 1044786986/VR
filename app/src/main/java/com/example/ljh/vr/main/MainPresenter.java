package com.example.ljh.vr.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr.home.HomeFragment;
import com.example.ljh.vr.personal.PersonalFragment;
import com.example.ljh.vr.share.ShareFragment;
import com.example.ljh.vr.utils.ShowTipUtils;

public class MainPresenter extends BasePresenter implements MainContract.MainPresenter{
    private MainContract.MainView mMainView;
    private MainContract.MainModel mMainModel;

    private LauncherFragment fragmentLauncher;
    private HomeFragment fragmentHome;
    private ShareFragment fragmentShare;
    private Fragment fragmentFoot;
    private Fragment fragmentPersonal;

    public static String curUser = "";
    public static boolean isPermission = false;

    public final int FRAME_LAYOUT = R.id.frameLayout_main;

    private int curPage = -1;
    private long oldKeyDownTime = 0;

    MainPresenter(BaseView baseView) {
        super(baseView);
        mMainModel = new MainModel();
        mMainView = (MainContract.MainView) baseView;
    }

    @Override
    public void selectPage(final int pos) {
        if(pos == curPage){
            return;
        }
        showFragment(pos);
        mMainView.changeColor(pos);
        curPage = pos;
    }

    @Override
    public void showFragment(int pos) {
        FragmentTransaction ft = mMainView.getMyFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (pos){
            case 0:
                if(fragmentHome == null){
                    fragmentHome = new HomeFragment();
                    ft.add(FRAME_LAYOUT, fragmentHome,"HomeFragment");
                }else{
                    ft.show(fragmentHome);
                }
                break;
            case 1:
                if(fragmentShare == null){
                    fragmentShare = new ShareFragment();
                    ft.add(FRAME_LAYOUT,fragmentShare,"ShareFragment");
                }
                ft.show(fragmentShare);
                break;
            case 2:
                break;
            case 3:
                if(fragmentPersonal == null){
                    fragmentPersonal = new PersonalFragment();
                    ft.add(FRAME_LAYOUT,fragmentPersonal,"PersonalFragment");
                }
                ft.show(fragmentPersonal);
                break;
        }
    }

    @Override
    public void hideFragment(FragmentTransaction ft) {
        if(fragmentHome != null){
            ft.hide(fragmentHome);
        }
        if(fragmentShare != null){
            ft.hide(fragmentShare);
        }
        if(fragmentFoot != null){
            ft.hide(fragmentFoot);
        }
        if(fragmentPersonal != null){
            ft.hide(fragmentPersonal);
        }
        ft.commit();
    }

    @Override
    public void showLauncherFragment() {
        FragmentTransaction ft = mMainView.getMyFragmentManager().beginTransaction();
        fragmentLauncher = new LauncherFragment();
        ft.add(FRAME_LAYOUT, fragmentLauncher,"fragmentLauncher");
        ft.commit();
    }

    @Override
    public void removeLauncherFragment() {
        if(fragmentLauncher != null){
            FragmentTransaction ft = mMainView.getMyFragmentManager().beginTransaction();
            ft.remove(fragmentLauncher);
            ft.commit();
            fragmentLauncher = null;
        }
        mMainView.showBottomNavigation();
    }

    @Override
    public void startServiceLive() {

    }

    @Override
    public void permissionSuccess() {
        isPermission = true;
        if(fragmentLauncher != null && fragmentLauncher.isTimerFinish()){
            removeLauncherFragment();
        }
    }

    @Override
    public void sureQuit(int code) {
        if(code == KeyEvent.KEYCODE_BACK){
            long nowKeyDownTime = System.currentTimeMillis();
            if(nowKeyDownTime - oldKeyDownTime <= 2000){
                mMainView.myFinish();
            }
            ShowTipUtils.toastShort(mMainView.getMyContext(),"再次点击返回键退出程序");
            oldKeyDownTime = nowKeyDownTime;
        }
    }

    @Override
    public void reLoadView() {

    }

    @Override
    public void setHomeCity(String city) {
        fragmentHome.setCurCity(city);
        fragmentHome.setTvCity(city);
        fragmentHome.getCityData(city);
    }
}
