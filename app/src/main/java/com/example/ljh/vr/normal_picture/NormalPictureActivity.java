package com.example.ljh.vr.normal_picture;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr.ui.SlideBack;

import butterknife.BindView;

public class NormalPictureActivity extends BaseActivity implements NormalPictureContract.NormalPictureView,
        NormalPictureContract.OnProgressBarListener {
    private NormalPicturePresenter  mNormalPicturePresenter;

    @BindView(R.id.vpNormalPicture)
    protected ViewPager mVpNormalPicture;
    @BindView(R.id.progressBar)
    protected ProgressBar progressBar;
    @BindView(R.id.ivBack)
    protected ImageView ivBack;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_normal_picture;
    }

    @Override
    public void initView() {
//        SlideBack slideBack = new SlideBack(this);
//        slideBack.attach();

        mNormalPicturePresenter.getIntent(getIntent());
        mNormalPicturePresenter.initVpAdapter(mVpNormalPicture);

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
        mNormalPicturePresenter = new NormalPicturePresenter(this);
        return mNormalPicturePresenter;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void myFinish() {
        finish();
    }

    @Override
    public void onShow() {
        showProgressBar();
    }

    @Override
    public void onHide() {
        hideProgressBar();
    }
}
