package com.example.ljh.vr.login;

import android.content.Context;
import android.os.CountDownTimer;

import com.example.ljh.vr._application.RegexApp;
import com.example.ljh.vr._application.TipApp;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.EncodeUtils;
import com.example.ljh.vr.utils.ShowTipUtils;
import com.example.ljh.vr.utils.TransformationUtils;
import com.socks.library.KLog;

public class RegisterPresenter implements RegisterContract.RegisterPresenter{
    private RegisterContract.RegisterView registerView;
    private RegisterContract.RegisterModel registerModel;

    private CountDownTimer mTimer;
    private boolean isStartTimer = false;

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
        String msgCode = registerRequestBean.getCode();

        /**
         * 检查输入格式
         */
        if(username == "" || username == null){
            ShowTipUtils.toastShort(context, "用户名为不能为空!");
        }else if(password == "" || password == null){
            ShowTipUtils.toastShort(context, "密码不能为空!");
        }else if(!username.matches(RegexApp.USERNAME)){
            ShowTipUtils.toastShort(context,"用户名格式错误!");
        }else if(!password.matches(RegexApp.PASSWORD)) {
            ShowTipUtils.toastShort(context, "密码格式错误!");
        } else if(msgCode.equals("") || msgCode == null) {
            ShowTipUtils.toastShort(context, "请输入验证码");
        }else if(msgCode.length() != 6){
            ShowTipUtils.toastShort(context, "请输入正确的验证码");
        }else{
            getView().showProgressBar();
            registerRequestBean.setPassword(EncodeUtils.md5(password));//对密码进行加密
            registerModel.register(registerRequestBean, new MyRetrofitCallback() {
                @Override
                public void onSuccess(Object o) {
                    if(registerView == null){
                        return;
                    }
                    getView().hideProgressBar();
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
//                    RegisterResponseBean bean = (RegisterResponseBean) o;
//                    //注册成功
//                    if(bean.isStatus()){
//                        ShowTipUtils.showAlertDialog(context,"注册成功", 1,
//                                new ShowTipUtils.AlertDialogCallback() {
//                                    @Override
//                                    public void positive() {
//                                        registerView.finishThis();
//                                    }
//                                    @Override
//                                    public void negative() {
//                                    }
//                                });
//                    }
//                    //注册失败
//                    else{
//                        int responseCode = bean.getResponseCode();
//                        if(responseCode == 0){  //用户名已被注册
//                            ShowTipUtils.toastShort(context,"用户名已被注册");
//                        }else if(responseCode == 1){//邮箱已被注册
//                            ShowTipUtils.toastShort(context,"邮箱已被注册");
//                        }
//                    }
                }

                @Override
                public void onFailed(String error) {
                    if(registerView == null){
                        return;
                    }
                    KLog.i("aaa","error = " + error);
                    getView().hideProgressBar();
                    ShowTipUtils.toastShort(context,error);
                }
            });
        }

    }

    @Override
    public void getMsgCode(String username) {
        if(username.equals("") || username.length() != 11){
            ShowTipUtils.toastShort(registerView.getContext(),"请输入正确的手机号码");
            return;
        }
        if(isStartTimer){
            return;
        }
        startTimer();
        registerModel.getMsgCode(username, new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if(registerView == null){
                    return;
                }
                ShowTipUtils.toastShort(registerView.getContext(),"短信已发送");
            }

            @Override
            public void onFailed(String error) {
                if(registerView == null){
                    return;
                }
                ShowTipUtils.toastShort(registerView.getContext(),error);
            }
        });
    }

    @Override
    public void startTimer() {
        if(mTimer != null){
            mTimer.cancel();
        }
//        if(mTimer == null){
        mTimer = new CountDownTimer(60*1000,1000) {
            @Override
            public void onTick(long l) {
                if(registerView != null){
                    registerView.setTimerText(TransformationUtils.second2min(l/1000).substring(3));
                }
            }

            @Override
            public void onFinish() {
                if(registerView == null){
                    return;
                }
                isStartTimer = false;
                registerView.timerFinish();
            }
        };
//        }
        isStartTimer = true;
        mTimer.start();
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
