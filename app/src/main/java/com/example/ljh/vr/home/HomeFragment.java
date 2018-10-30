package com.example.ljh.vr.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseFragment;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr.select_city.OnSelectCityActivity;
import com.example.ljh.vr.select_city.SelectCityContract;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeContract.HomeView,BaseView,
        View.OnClickListener,SelectCityContract.OnSelectCityListener{
    @BindView(R.id.linearLayout_city)
    protected LinearLayout mLinearLayout_city;
    @BindView(R.id.svHome)
    protected SearchView mSearchView;
    @BindView(R.id.tvCity)
    protected TextView mTvCity;
    @BindView(R.id.rvHome)
    protected RecyclerView mRvHome;
    @BindView(R.id.ivHeader)
    protected ImageView mIvHeader;
    @BindView(R.id.ivNothing)
    protected ImageView ivNothing;
    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;

    private HomePresenter mHomePresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public BasePresenter bindPresenter() {
        mHomePresenter = new HomePresenter(this);
        return mHomePresenter;
    }

    @Override
    public void initView(View view) {
        mHomePresenter.initRv(mRvHome);
    }

    @Override
    public void initData() {
        showProgressBar();
        mHomePresenter.getData();
    }

    @Override
    @OnClick({R.id.linearLayout_city,R.id.svHome})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearLayout_city:
                Intent intent = new Intent(getMyContext(), OnSelectCityActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.svHome:

                break;
        }
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNullTip() {
        ivNothing.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNullTip() {
        ivNothing.setVisibility(View.GONE);
    }

    @Override
    public void myFinish() {

    }

    @Override
    public void setHeaderImage(Bitmap bitmap) {
        mIvHeader.setImageBitmap(bitmap);
    }

    @Override
    public void setTvCity(String city) {
        mTvCity.setText(city);
    }

    @Override
    public void onSelect(String city) {
        setTvCity(city);
    }
}
