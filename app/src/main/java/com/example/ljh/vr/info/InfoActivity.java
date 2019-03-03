package com.example.ljh.vr.info;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr.ui.SlideBack;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;

public class InfoActivity extends BaseActivity implements InfoContract.InfoView,View.OnClickListener{
    private InfoPresenter mInfoPresenter;

    @BindView(R.id.viewPagerInfo)
    protected ViewPager mViewPagerInfo;
    @BindView(R.id.linearLayout_header)
    protected LinearLayout mLinearLayout_header;
    @BindView(R.id.linearLayout_introduce)
    protected LinearLayout mLinearLayout_introduce;
    @BindView(R.id.ivBack)
    protected ImageView mIvBack;
    @BindView(R.id.ivHot)
    protected ImageView mIvHot;
    @BindView(R.id.tvName)
    protected TextView mTvName;
    @BindView(R.id.tvLocation)
    protected TextView mTvLocation;
    @BindView(R.id.tvCurImage)
    protected TextView mTvCurImage;
    @BindView(R.id.tvCountImage)
    protected TextView mTvCountImage;
    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.scrollViewInfo)
    protected ScrollView mScrollViewInfo;

    @BindView(R.id.relativeLayout)
    protected RelativeLayout relativeLayout;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_info;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        SlideBack slideBack = new SlideBack(this);
        slideBack.attach();
        mInfoPresenter.initVpAdapter(mViewPagerInfo);
        mScrollViewInfo.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                mInfoPresenter.scrollViewListener(i1,i3);
            }
        });
    }

    @Override
    public void initData() {
        mInfoPresenter.getData(getIntent());
    }

    @Override
    public BasePresenter bindPresenter() {
        mInfoPresenter = new InfoPresenter(this);
        return mInfoPresenter;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

//    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void myFinish() {
        finish();
    }

    @Override
    public void showData(InfoBean infoBean) {
        mTvName.setText(infoBean.getName());
        mTvLocation.setText(infoBean.getProvince()+infoBean.getCity()+infoBean.getDistrict()+infoBean.getDetail());
        mTvCurImage.setText(1+"");
        mTvCountImage.setText(infoBean.getImgUrls().size()+"");
        /**
         * 判断热度
         */
        int hot = infoBean.getHot();
//        if(hot < 0){
//            hot = 0;
//        }else if(hot > 5){
//            hot = 5;
//        }
        int hotId = getResources().getIdentifier("hot" + hot,"drawable",getPackageName());
        mIvHot.setImageResource(hotId);
    }

    @Override
    public void addIntroduce(View view) {
        mLinearLayout_introduce.addView(view);
    }

    @Override
    public void addViewPager(View view) {
        mViewPagerInfo.addView(view);
    }

    @Override
    public void setPager(int pos) {
        mTvCurImage.setText(pos+1+"");
    }

    @Override
    public int getViewPagerWidth() {
        return mViewPagerInfo.getWidth();
    }

    @Override
    public int getViewPagerHeight() {
        return mViewPagerInfo.getHeight();
    }

    @Override
    public LinearLayout getHeader() {
        return mLinearLayout_header;
    }


    @Override
    @OnClick({R.id.ivBack,R.id.viewPagerInfo,R.id.relativeLayout})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivBack:
                finish();
                break;
            case R.id.viewPagerInfo:
                KLog.i("aaa","viewPagerInfo");
                mInfoPresenter.toAlbum();
                break;
            case R.id.relativeLayout:
                KLog.i("aaa","relativeLayout");
                break;
        }
    }
}
