package com.example.ljh.vr.select_city;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr.ui.AZSideBarView;

import butterknife.BindView;

public class SelectCityActivity extends BaseActivity implements SelectCityContract.SelectCityView{
    @BindView(R.id.azSideBarView)
    protected AZSideBarView mAZSizeBarView;
    @BindView(R.id.tvLetter)
    protected TextView mTvLetter;

    private SelectCityPresenter mSelectCityPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_select_city;
    }

    @Override
    public void initView() {
        mAZSizeBarView.setOnTouchLetterListener(new AZSideBarView.OnTouchLetterListener() {
            @Override
            public void onLetterChange(int pos, String s) {
                if(mTvLetter.getVisibility() != View.VISIBLE){
                    mTvLetter.setVisibility(View.VISIBLE);
                }
                mTvLetter.setText(s);
            }

            @Override
            public void onLetterSelect(int pos, String s) {
                mTvLetter.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter bindPresenter() {
        mSelectCityPresenter = new SelectCityPresenter(this);
        return mSelectCityPresenter;
    }

    @Override
    public Context getMyContext() {
        return this;
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
}
