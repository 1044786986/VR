package com.example.ljh.vr.login;

import android.content.Context;
import android.util.Log;

import com.example.ljh.vr._application.RegexApp;
import com.example.ljh.vr._application.TipApp;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.EncodeUtils;
import com.example.ljh.vr.utils.ShowTipUtils;

public class RegisterPresenter implements RegisterContract.RegisterPresenter{
    private RegisterContract.RegisterView registerView;
    private RegisterContract.RegisterModel registerModel;

    public RegisterPresenter(RegisterContract.RegisterView registerView){
        this.registerView = registerView;
        registerModel = new RegisterModel();
    }

    /**
     * 注册
     */
    @Override
    public void register() {
        RegisterRequestBean registerRequestBean = getView().getRegisterInfo();
        final Context context = getView().getContext();
        String username = registerRequestBean.getUsername();
        String password = registerRequestBean.getPassword();
        String email = registerRequestBean.getEmail();
        /**
         * 检查输入格式
         */
        if(username == "" || username == null){
            ShowTipUtils.toastShort(context, "用户名为不能为空!");
        }else if(password == "" || password == null){
            ShowTipUtils.toastShort(context, "密码不能为空!");
        }else if(email == "" || email == null){
            ShowTipUtils.toastShort(context, "邮箱不能为空!");
        }else if(!username.matches(RegexApp.USERNAME)){
            ShowTipUtils.toastShort(context,"用户名格式错误!");
        }else if(!password.matches(RegexApp.PASSWORD)){
            ShowTipUtils.toastShort(context, "密码格式错误!");
        }else if(!email.matches(RegexApp.EMAIL)){
            ShowTipUtils.toastShort(context,"邮箱格式错误!");
        }else{
            getView().showProgressBar();
            registerRequestBean.setPassword(EncodeUtils.ShaEncode(password));//对密码进行加密
            registerModel.register(registerRequestBean, new MyRetrofitCallback() {
                @Override
                public void onSuccess(Object o) {
                    getView().hideProgressBar();
                    RegisterResponseBean bean = (RegisterResponseBean) o;
                    //注册成功
                    if(bean.isStatus()){
                        ShowTipUtils.showAlertDialog(context,"注册成功", 1,
                                new ShowTipUtils.AlertDialogCallback() {
                                    @Override
                                    public void positive() {
                                        registerView.finishThis();
                                    }
                                    @Override
                                    public void negative() {
                                    }
                                });
                    }
                    //注册失败
                    else{
                        int responseCode = bean.getResponseCode();
                        if(responseCode == 0){  //用户名已被注册
                            ShowTipUtils.toastShort(context,"用户名已被注册");
                        }else if(responseCode == 1){//邮箱已被注册
                            ShowTipUtils.toastShort(context,"邮箱已被注册");
                        }
                    }
                }

                @Override
                public void onFailed(String error) {
                    Log.i("aaa","error = " + error);
                    getView().hideProgressBar();
                    ShowTipUtils.toastShort(context, TipApp.CONN_ERROR);
                }
            });
        }

    }

    @Override
    public RegisterContract.RegisterView getView() {
        if(registerView == null){
            throw new IllegalStateException(TipApp.VIEW_NULL);
        }else {
            return registerView;
        }
    }

    @Override
    public void recycle() {
        registerView = null;
    }
}
