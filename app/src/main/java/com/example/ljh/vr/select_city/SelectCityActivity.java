package com.example.ljh.vr.select_city;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr.ui.AZSideBarView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SelectCityActivity extends BaseActivity implements SelectCityContract.SelectCityView,
        SelectCityContract.OnSelectCityListener {
    @BindView(R.id.azSideBarView)
    protected AZSideBarView mAZSizeBarView;//侧边字母栏
    @BindView(R.id.tvLetter)
    protected TextView mTvLetter;           //字母悬浮提示
    @BindView(R.id.rvSelectCity)
    protected RecyclerView mRvSelectCity;
    @BindView(R.id.ivBack)
    protected ImageView mIvBack;
    private SelectCityPresenter mSelectCityPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_select_city;
    }

    @Override
    public void initView() {
        View recentlyView = LayoutInflater.from(this).inflate(R.layout.view_select_city_recently,null,false);
        GridView gdRecently = recentlyView.findViewById(R.id.gdRecently);
        View hotCityView = LayoutInflater.from(this).inflate(R.layout.view_select_city_hot,null,false);
        GridView gdHotCity = hotCityView.findViewById(R.id.gdHotCity);
        mSelectCityPresenter.initRvAdapter(mRvSelectCity,recentlyView,hotCityView);
        mSelectCityPresenter.initGdAdapter(gdHotCity,gdRecently);

        /**
         * 字母栏设置触摸监听
         */
        mAZSizeBarView.setOnTouchLetterListener(new AZSideBarView.OnTouchLetterListener() {
            @Override
            public void onLetterChange(int pos, String s) {
                if(mTvLetter.getVisibility() != View.VISIBLE){
                    mTvLetter.setVisibility(View.VISIBLE);
                }
                mTvLetter.setText(s);
                mSelectCityPresenter.moveToPos(pos,mRvSelectCity);
            }

            @Override
            public void onLetterSelect(int pos, String s) {
                mTvLetter.setVisibility(View.GONE);
//                mSelectCityPresenter.moveToPos(pos,mRvSelectCity);
            }
        });

        /**
         *
         */
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        mSelectCityPresenter.getRecentlyCity();
        mSelectCityPresenter.getHotCity();
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

//    @Override
//    public void hideProgressBar() {
//
//    }


    @Override
    public void myFinish() {
        finish();
    }

    @Override
    public void onSelectCity(String city) {
        mSelectCityPresenter.addRecentlyCity(city);
        Intent intent = new Intent();
        intent.putExtra(KeyApp.RESULT_KEY_SELECT_CITY,city);
        setResult(KeyApp.RESULT_CODE_SELECT_CITY,intent);
        finish();
    }

    @Override
    public void onScrollLetter(int pos) {
        mAZSizeBarView.setCurPos(pos);
    }


}
