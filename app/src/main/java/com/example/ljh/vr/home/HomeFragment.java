package com.example.ljh.vr.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseFragment;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.INullTip;
import com.example.ljh.vr._base.IProgressBar;
import com.example.ljh.vr.select_city.SelectCityActivity;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeContract.HomeView,
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
    @BindView(R.id.ivNothing)
    protected ImageView ivNothing;
    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.swipeRefreshLayoutHome)
    protected SwipeRefreshLayout mSwipeRefreshLayoutHome;

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
        mSwipeRefreshLayoutHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHomePresenter.getData();
            }
        });
    }

    @Override
    public void initData() {
        mHomePresenter.getRecommendData();
    }

    @Override
    @OnClick({R.id.linearLayout_city,R.id.svHome})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearLayout_city:
                Intent intent = new Intent(getMyContext(), SelectCityActivity.class);
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
        mSwipeRefreshLayoutHome.setRefreshing(false);
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
    public void setCurCity(String city) {
        mHomePresenter.setCurCity(city);
    }

    @Override
    public void setTvCity(String city) {
        mTvCity.setText(city);
    }

    @Override
    public void getCityData(String city) {
        mHomePresenter.getCityData();
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        KLog.i("aaa","fragment.onCreateView");
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        KLog.i("aaa","fragment.onAttach");
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        KLog.i("aaa","fragment.onCreate");
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        KLog.i("aaa","fragment.onActivityCreated");
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        KLog.i("aaa","fragment.onStart");
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        KLog.i("aaa","fragment.onResume");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        KLog.i("aaa","fragment.onPause");
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        KLog.i("aaa","fragment.onStop");
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        KLog.i("aaa","fragment.onDestroyView");
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        KLog.i("aaa","fragment.onDestroy");
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        KLog.i("aaa","fragment.onDetach");
//    }
}
