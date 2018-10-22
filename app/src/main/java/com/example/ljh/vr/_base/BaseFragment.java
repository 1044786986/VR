package com.example.ljh.vr._base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment{
    private BasePresenter presenter = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutResId(),container,false);
        presenter = bindPresenter();
        ButterKnife.bind(this,view);
        initView(view);
        initData();
        return view;
    }

    public abstract int getLayoutResId();
    public abstract BasePresenter bindPresenter();
    public abstract void initView(View view);
    public abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter != null){
            presenter.recycle();
            presenter = null;
        }
    }
}
