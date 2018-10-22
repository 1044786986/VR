package com.example.ljh.vr.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.EventBusBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.MainView,View.OnClickListener ,
        LauncherFragment.LauncherFinish{
    private MainPresenter mMainPresenter;

    @BindView(R.id.linearLayout_home)
    protected LinearLayout linearLayout_home;
    @BindView(R.id.linearLayout_find)
    protected LinearLayout linearLayout_find;
    @BindView(R.id.linearLayout_VR)
    protected LinearLayout linearLayout_VR;
    @BindView(R.id.linearLayout_foot)
    protected LinearLayout linearLayout_foot;
    @BindView(R.id.linearLayout_personal)
    protected LinearLayout linearLayout_personal;
    @BindView(R.id.linearLayout_bottomNavigation)
    protected LinearLayout bottomNavigation;

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

    @BindView(R.id.tvHome)
    protected TextView tvHome;
    @BindView(R.id.tvFind)
    protected TextView tvFind;
    @BindView(R.id.tvFoot)
    protected TextView tvFoot;
    @BindView(R.id.tvPersonal)
    protected TextView tvPersonal;

    @Override
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

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
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
        return null;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showNullTip() {

    }

    @Override
    public void hideNullTip() {

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
                ivHome.setImageResource(R.drawable.home_select);
                tvHome.setTextColor(getResources().getColor(R.color.bottom_text_selected));
                break;
            case 1:
                ivFind.setImageResource(R.drawable.home_select);
                tvFind.setTextColor(getResources().getColor(R.color.bottom_text_selected));
                break;
            case 2:
                ivFoot.setImageResource(R.drawable.home_select);
                tvFoot.setTextColor(getResources().getColor(R.color.bottom_text_selected));
                break;
            case 3:
                ivPersonal.setImageResource(R.drawable.home_select);
                tvPersonal.setTextColor(getResources().getColor(R.color.bottom_text_selected));
                break;
        }
    }

    @Override
    public void setAllNormal() {
        ivHome.setImageResource(R.drawable.home_normal);
        ivFind.setImageResource(R.drawable.earth_normal);
        ivFoot.setImageResource(R.drawable.foot_normal);
        ivPersonal.setImageResource(R.drawable.app_normal);

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
    public FragmentManager getMyFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public void onLauncherFinish() {
        mMainPresenter.removeLauncherFragment();
    }
}
