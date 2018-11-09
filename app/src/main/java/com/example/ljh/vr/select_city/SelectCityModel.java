package com.example.ljh.vr.select_city;

import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.RetrofitUtils;
import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SelectCityModel implements SelectCityContract.SelectCityModel{
    @Override
    public void getHotCity(final MyRetrofitCallback callback) {
        RetrofitUtils.getInstance().getIRetrofitRx2Gson()
                .getHotCity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotCityBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotCityBean value) {
                        callback.onSuccess(value.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailed(e+"");
                        KLog.i("aaa",e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
