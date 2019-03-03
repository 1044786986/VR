package com.example.ljh.vr.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._application.EventBusApp;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.EventBusBean;
import com.example.ljh.vr._base.PermissionManagerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends PermissionManagerActivity implements MainContract.MainView,View.OnClickListener ,
        LauncherFragment.LauncherFinish{
    @BindView(R.id.linearLayout_home)
    protected LinearLayout linearLayout_home;//底部导航栏 首页
    @BindView(R.id.linearLayout_find)
    protected LinearLayout linearLayout_find;//底部导航栏 发现
    @BindView(R.id.linearLayout_VR)
    protected LinearLayout linearLayout_VR;   //底部导航栏 VR
    @BindView(R.id.linearLayout_foot)
    protected LinearLayout linearLayout_foot;//底部导航栏 足迹
    @BindView(R.id.linearLayout_personal)
    protected LinearLayout linearLayout_personal;//底部导航栏 个人
    @BindView(R.id.linearLayout_bottomNavigation)
    protected LinearLayout bottomNavigation;    //底部导航栏
    /**
     * 导航栏图标
     */
    @BindView(R.id.ivHome)
    protected ImageView ivHome;
    @BindView(R.id.ivFind)
    protected ImageView ivFind;
    @BindView(R.id.ivVR)
    protected ImageView ivVR;
    @BindView(R.id.ivFoot)
    protected ImageView ivFoot;
    @BindView(R.id.ivPersonal)
    protected ImageView ivPersonal;
    /**
     * 导航栏文字
     */
    @BindView(R.id.tvHome)
    protected TextView tvHome;
    @BindView(R.id.tvFind)
    protected TextView tvFind;
    @BindView(R.id.tvFoot)
    protected TextView tvFoot;
    @BindView(R.id.tvPersonal)
    protected TextView tvPersonal;

    private MainPresenter mMainPresenter;

    private static boolean isFirstStart = true;//判断是否首次启动APP


    @Override
    @OnClick({R.id.linearLayout_home,R.id.linearLayout_foot,R.id.linearLayout_find,R.id.linearLayout_personal})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearLayout_home:
                changeColor(0);
                mMainPresenter.selectPage(0);
                break;
            case R.id.linearLayout_find:
                changeColor(1);
                mMainPresenter.selectPage(1);
                break;
            case R.id.linearLayout_foot:
                changeColor(2);
                mMainPresenter.selectPage(2);
                break;
            case R.id.linearLayout_personal:
                changeColor(3);
                mMainPresenter.selectPage(3);
                break;
            case R.id.ivVR:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventBusBean busBean){
        switch (busBean.getType()){

        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        if(!isFirstStart){
            return;
        }
        hideStatusBar();
        isFirstStart = false;
        mMainPresenter.selectPage(0);
        mMainPresenter.showLauncherFragment();
        applyNecessaryPermission(new PermissionResultCallback() {
            @Override
            public void onSuccess() {
                mMainPresenter.permissionSuccess();
            }

            @Override
            public void onFailed() {
                finish();
            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter bindPresenter() {
        mMainPresenter = new MainPresenter(this);
        return mMainPresenter;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void myFinish() {
        finish();
    }

    @Override
    public void changeColor(int pos) {
        setAllNormal();
        switch (pos){
            case 0:
                ivHome.setImageResource(R.mipmap.home_select);
                tvHome.setTextColor(getResources().getColor(R.color.bottom_text_selected));
                break;
            case 1:
                ivFind.setImageResource(R.mipmap.navigation_select);
                tvFind.setTextColor(getResources().getColor(R.color.bottom_text_selected));
                break;
            case 2:
                ivFoot.setImageResource(R.mipmap.foot_select);
                tvFoot.setTextColor(getResources().getColor(R.color.bottom_text_selected));
                break;
            case 3:
                ivPersonal.setImageResource(R.mipmap.app_select);
                tvPersonal.setTextColor(getResources().getColor(R.color.bottom_text_selected));
                break;
        }
    }

    @Override
    public void setAllNormal() {
        ivHome.setImageResource(R.mipmap.home_normal);
        ivFind.setImageResource(R.mipmap.navigation_normal);
        ivFoot.setImageResource(R.mipmap.foot_normal);
        ivPersonal.setImageResource(R.mipmap.app_normal);

        tvHome.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        tvFind.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        tvFoot.setTextColor(getResources().getColor(R.color.bottom_text_normal));
        tvPersonal.setTextColor(getResources().getColor(R.color.bottom_text_normal));
    }

    @Override
    public void showBottomNavigation() {
        bottomNavigation.setVisibility(View.VISIBLE);
    }

    @Override
    public void showStatusBar() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    @Override
    public void hideStatusBar() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public FragmentManager getMyFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public void onLauncherFinish() {
        mMainPresenter.removeLauncherFragment();
        showStatusBar();
        showBottomNavigation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){
            case KeyApp.RESULT_CODE_SELECT_CITY:
                mMainPresenter.setHomeCity(data.getStringExtra(KeyApp.RESULT_KEY_SELECT_CITY));
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mMainPresenter.sureQuit(keyCode);
        return true;
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        KLog.i("aaa","Main.onDestroy");
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        KLog.i("aaa","Main.onCreate");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        KLog.i("aaa","Main.onPause");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        KLog.i("aaa","Main.onStop");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        KLog.i("aaa","Main.onResume");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        KLog.i("aaa","Main.onRestart");
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        KLog.i("aaa","Main.onStart");
//    }

}
