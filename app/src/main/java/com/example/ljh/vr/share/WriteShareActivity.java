package com.example.ljh.vr.share;

import android.content.Context;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;

public class WriteShareActivity extends BaseActivity implements WriteShareContract.WriteShareView {
    private WriteSharePresenter mWriteSharePresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_write_share;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter bindPresenter() {
        mWriteSharePresenter = new WriteSharePresenter(this);
        return mWriteSharePresenter;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

//    @Override
//    public void hideProgressBar() {
//
//    }

    @Override
    public void myFinish() {

    }
}
