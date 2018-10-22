package com.example.ljh.vr._base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

public abstract class BaseActivity extends PermissionManagerActivity {
    private BasePresenter presenter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        presenter = bindPresenter();
        initView();
        initData();
    }


    /**
     * 返回资源的布局
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 组件初始化操作
     */
    public abstract void initView();

    /**
     * 页面初始化页面数据，在initView之后调用
     */
    public abstract void initData();

    /**
     * 绑定presenter，主要用于销毁工作
     *
     * @return
     */
    public abstract BasePresenter bindPresenter();

//    public abstract Context getMyContext();

    /**
     * 如果重写了此方法，一定要调用super.onDestroy();
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.recycle();
            presenter = null;
            System.gc();
        }
    }
}
