package com.example.ljh.vr.setting;

import android.content.Context;

import com.example.ljh.vr._base.BaseCallback;
import com.socks.library.KLog;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SettingModel implements SettingContract.SettingModel{
    private long size;

    @Override
    public void getCacheSize(Context context, final BaseCallback callback) {
        size = 0;
        File file = new File(context.getCacheDir().getPath());
        File[] files = file.listFiles();
        Observable.fromArray(files)
//                .flatMap(new Function<File, ObservableSource<File>>() {
//                    @Override
//                    public ObservableSource<File> apply(File file) throws Exception {
//                        return Observable.fromArray(file.listFiles());
//                    }
//                })
                .map(new Function<File, Long>() {
                    @Override
                    public Long apply(File file) throws Exception {
                        return size += file.length();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.i("aaa",e+"");
                        callback.onFailed(e+"");
                    }

                    @Override
                    public void onComplete() {
                        callback.onSuccess(Double.parseDouble((size/1024/1024)+""));
                    }
                });
    }

    @Override
    public void clearCache(Context context,final BaseCallback callback) {
        File files[] = new File(context.getCacheDir().getPath()).listFiles();
        for(File file : files){
            file.delete();
        }
        callback.onSuccess("");
    }
}
