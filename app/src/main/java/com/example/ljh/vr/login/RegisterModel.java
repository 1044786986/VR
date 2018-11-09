package com.example.ljh.vr.login;

import com.example.ljh.vr._base.BaseBean;
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
                .register(registerRequestBean.getUsername(), registerRequestBean.getPassword(), registerRequestBean.getCode())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean value) {
                        if(value.getCode() == 0){
                            callBack.onSuccess("");
                        }else{
                            callBack.onFailed(value.getData()+"");
                        }
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

    @Override
    public void getMsgCode(String username,final MyRetrofitCallback callback) {
        RetrofitUtils.getInstance().getRetrofitRx2Gson(RetrofitUtils.BASE_URL)
                .create(IRetrofit.class)
                .sendVerificationCode(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean value) {
                        callback.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailed(e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
