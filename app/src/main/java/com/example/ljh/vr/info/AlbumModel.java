package com.example.ljh.vr.info;

import com.example.ljh.vr._base.BaseResListBean;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.RetrofitUtils;
import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AlbumModel implements AlbumContract.AlbumModel {

    @Override
    public void getNormalData(String id, final MyRetrofitCallback callback) {
        RetrofitUtils.getInstance().getIRetrofitRx2Gson()
                .getUrlData("getNormalData",id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AlbumResUrlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AlbumResUrlBean value) {
                        if(value.getCode() == 0) {
                            callback.onSuccess(value.getData());
                            return;
                        }
                        callback.onFailed(value.getError());
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

    @Override
    public void getVrData(String id, final MyRetrofitCallback callback) {
        RetrofitUtils.getInstance().getIRetrofitRx2Gson()
                .getUrlData("getVrData",id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AlbumResUrlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AlbumResUrlBean value) {
                        if(value.getCode() == 0) {
                            callback.onSuccess(value.getData());
                            return;
                        }
                        callback.onFailed(value.getError());
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

    @Override
    public void getVideoData(String id, MyRetrofitCallback callback) {

    }
}
