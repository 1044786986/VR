package com.example.ljh.vr.info;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr.ui.SlideBack;

import butterknife.BindView;

public class AlbumActivity extends BaseActivity implements AlbumContract.AlbumView {
    private AlbumPresenter mAlbumPresenter;

    private RecyclerView mRvNormal;
    private RecyclerView mRvVr;
    private RecyclerView mRvVideo;

    @BindView(R.id.tbAlbum)
    protected TabLayout mTbAlbum;
    @BindView(R.id.vpAlbum)
    protected ViewPager mVpAlbum;
    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;
//    @BindView(R.id.ivNothing)
//    protected ViewStub mIvNothing;
    @BindView(R.id.ivBack)
    protected ImageView ivBack;


    @Override
    public Context getMyContext() {
        return this;
    }

//    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showNullTip() {
//        mIvNothing.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNullTip() {
//        mIvNothing.setVisibility(View.GONE);
    }

    @Override
    public void myFinish() {
        finish();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_album;
    }

    @Override
    public void initView() {
        mRvNormal = new RecyclerView(this);
        mRvVr = new RecyclerView(this);
        mRvVideo = new RecyclerView(this);

        mAlbumPresenter.getIntent(getIntent());
        mAlbumPresenter.initRvAdapter(mRvNormal,mRvVr,mRvVideo);
        mAlbumPresenter.initVpAdapter(mVpAlbum,mTbAlbum);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter bindPresenter() {
        mAlbumPresenter = new AlbumPresenter(this);
        return mAlbumPresenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
