package com.example.ljh.vr.share;

import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.RetrofitUtils;
import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShareModel implements ShareContract.ShareModel {
    @Override
    public void getData(final MyRetrofitCallback callback) {
        RetrofitUtils.getInstance().getIRetrofitRx2Gson()
                .getShareData("getData")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShareResBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShareResBean value) {
                        if(value.getCode() == 0){
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
    public void loadData(String id,MyRetrofitCallback callback) {

    }

}
