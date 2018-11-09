package com.example.ljh.vr.share;

import com.example.ljh.vr._base.BaseBean;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.main.MainPresenter;
import com.example.ljh.vr.utils.RetrofitUtils;
import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShareCommentModel implements ShareCommentContract.ShareCommentModel {
    @Override
    public void getData(String id, final MyRetrofitCallback callback) {
        RetrofitUtils.getInstance().getIRetrofitRx2Gson()
                .getShareComment("getShareComment",id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShareCommentResBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShareCommentResBean value) {
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
    public void loadData(String id, String commentId, MyRetrofitCallback callback) {

    }

    @Override
    public void sendComment(String share_id, String text,final MyRetrofitCallback callback) {
        RetrofitUtils.getInstance().getIRetrofitRx2Gson()
                .sendShareComment("sendShareComment",share_id,MainPresenter.curUser,text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean value) {
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
}
