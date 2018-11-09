package com.example.ljh.vr.share;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseFragment;
import com.example.ljh.vr._base.BasePresenter;

import butterknife.BindView;

public class ShareFragment extends BaseFragment implements ShareContract.ShareView {
    @BindView(R.id.rvShare)
    protected RecyclerView mRvShare;
    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.ivWrite)
    protected ImageView mIvWrite;
    @BindView(R.id.ivNothing)
    protected ImageView ivNothing;

    private SharePresenter mSharePresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_share;
    }

    @Override
    public BasePresenter bindPresenter() {
        mSharePresenter = new SharePresenter(this);
        return mSharePresenter;
    }

    @Override
    public void initView(View view) {
        mSharePresenter.initRvAdapter(mRvShare);

        mIvWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WriteShareActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        mSharePresenter.getData();
    }

    @Override
    public Context getMyContext() {
        return getContext();
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
}
