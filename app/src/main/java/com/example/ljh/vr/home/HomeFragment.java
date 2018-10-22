package com.example.ljh.vr.home;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeContract.HomeView,BaseView,
        View.OnClickListener{
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

    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.linearLayout_city,R.id.searchView})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearLayout_city:
                break;
            case R.id.searchView:

                break;
        }
    }

    @Override
    public Context getMyContext() {
        return getContext();
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

    }
}
