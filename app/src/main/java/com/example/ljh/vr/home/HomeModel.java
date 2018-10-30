package com.example.ljh.vr.home;

import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.RetrofitUtils;
import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.HomeModel{
    @Override
    public void getData(String id, final MyRetrofitCallback callback) {
        RetrofitUtils.getInstance().getIRetrofitRx2Gson()
                .getRecommendData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeResBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeResBean value) {
                        if(value.getCode() == 0){
                            callback.onSuccess(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.i("aaa",e+"");
                        callback.onFailed(e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
