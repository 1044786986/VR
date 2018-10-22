package com.example.ljh.vr.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._application.TipApp;
import com.example.ljh.vr._base.BaseBean;
import com.example.ljh.vr._base.IRetrofit;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.EncodeUtils;
import com.example.ljh.vr.utils.RetrofitUtils;
import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements LoginContract.LoginModel{

    @Override
    public void login(LoginBean loginBean, final MyRetrofitCallback callBack) {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        if(username == "" || username == null){
            callBack.onFailed("用户名不能为空!");
        }else if(password == "" || password == null){
            callBack.onFailed("密码不能为空!");
        }else{
            password = EncodeUtils.md5(password);
            KLog.i("md5-password = " + password);
            RetrofitUtils.getInstance().getRetrofitRx2Gson(RetrofitUtils.BASE_URL)
                    .create(IRetrofit.class)
                    .login(username,password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBean.LoginResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBean.LoginResponse value) {
                            callBack.onSuccess(value);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("aaa","error = " + e);
                            callBack.onFailed(TipApp.CONN_ERROR);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void loginCheckbox(Context context, LoginBean.LoginCheckBox checkBox,LoginBean loginBean) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(KeyApp.SP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KeyApp.LOGIN_NAME,loginBean.getUsername());
        if(checkBox.isAuto()){
            checkBox.setRemember(true);
            editor.putBoolean(KeyApp.AUTO_LOGIN,true);
            editor.putBoolean(KeyApp.REMEMBER_PASSWORD,true);
        }else{
            editor.putBoolean(KeyApp.AUTO_LOGIN,false);
        }

        if(checkBox.isRemember()){
            editor.putBoolean(KeyApp.REMEMBER_PASSWORD,true);
            editor.putString(KeyApp.LOGIN_PASSWORD,loginBean.getPassword());
        }else{
            editor.putBoolean(KeyApp.REMEMBER_PASSWORD,false);
            editor.putString(KeyApp.LOGIN_PASSWORD,"");
        }
        editor.commit();
    }

    @Override
    public void sendMsg(String username, final MyRetrofitCallback callback) {
        RetrofitUtils.getInstance().getRetrofitRx2Gson(RetrofitUtils.BASE_URL)
                .create(IRetrofit.class)
                .sendMsg(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.i(e+"");
                        callback.onFailed(e+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
