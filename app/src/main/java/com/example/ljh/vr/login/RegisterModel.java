package com.example.ljh.vr.login;

import com.example.ljh.vr._base.IRetrofit;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel implements RegisterContract.RegisterModel {
    @Override
    public void register(RegisterRequestBean registerRequestBean, final MyRetrofitCallback callBack) {
        RetrofitUtils.getInstance().getRetrofitRx2Gson(RetrofitUtils.BASE_URL)
                .create(IRetrofit.class)
                .register(registerRequestBean.getUsername(), registerRequestBean.getPassword(),
                        registerRequestBean.getEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponseBean value) {
                        callBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed(e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
