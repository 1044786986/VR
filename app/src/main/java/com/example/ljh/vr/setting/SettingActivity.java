package com.example.ljh.vr.setting;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr.ui.SlideBack;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements SettingContract.SettingView,View.OnClickListener{
    @BindView(R.id.quitLogin)
    protected LinearLayout quitLogin;
    @BindView(R.id.linearLayout_clearCache)
    protected LinearLayout clearCache;

    private SettingPresenter settingPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        SlideBack slideBack = new SlideBack(this);
        slideBack.attach();
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter bindPresenter() {
        settingPresenter = new SettingPresenter(this);
        return settingPresenter;
    }

    @Override
    @OnClick({R.id.quitLogin,R.id.linearLayout_clearCache})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.quitLogin:
                settingPresenter.quitLogin();
                break;
            case R.id.linearLayout_clearCache:
                settingPresenter.getCacheSize();
                break;
        }
    }

    @Override
    public void myFinish() {
        finish();
    }

    @Override
    public Context getMyContext() {
        return this;
    }

//    @Override
//    public void hideProgressBar() {
//
//    }

}
