package com.example.ljh.vr.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.EventBusBean;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.ShowTipUtils;
import com.example.ljh.vr.utils.TransformationUtils;

import org.greenrobot.eventbus.EventBus;

public class LoginPresenter implements LoginContract.LoginPresenter{
    private LoginContract.LoginView loginView;
    private LoginContract.LoginModel loginModel;

    private CountDownTimer mTimer;
    private boolean isStartTimer = false;
    private boolean ableSendMsg = true;

    public LoginPresenter(LoginContract.LoginView loginView){
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    @Override
    public void login() {
        loginView.showProgressBar();
        final LoginBean loginBean = loginView.getInfo();
        loginModel.login(loginBean,new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if(loginView == null){
                    return;
                }
                loginView.hideProgressBar();
                LoginBean.LoginResponse value = (LoginBean.LoginResponse)o;
                if (value.isStatus()) {
                    value.setUsername(loginBean.getUsername());
                    //设置当前用户信息
                    saveCurUser(value);
                    //检查复选框状态
                    loginCheckbox(loginBean);
                    //发送消息到主页更新UI
                    EventBusBean eventBusBean = new EventBusBean();
                    eventBusBean.setType(KeyApp.EB_LOGIN_SUCCESS);
                    EventBus.getDefault().post(eventBusBean);   //MainActivity.register
                    loginView.toMain();
                } else {
                    int responseCode = value.getResponseCode();
                    if (responseCode == 0) {
                        loginView.showToast("用户名错误");
                    } else if (responseCode == 1) {
                        loginView.showToast("密码错误");
                    }
                }
            }

            @Override
            public void onFailed(String error) {
                if(loginView == null){
                    return;
                }
                loginView.hideProgressBar();
                loginView.showToast(error);
            }
        });
    }

    @Override
    public void msgLogin(final String username) {
        if(username == "" || username == null || username.length() != 11){
            ShowTipUtils.toastShort(loginView.getContext(),"请输入正确的用户名");
            return;
        }
        if(isStartTimer){
            return;
        }
        ShowTipUtils.showAlertDialog(loginView.getContext(), "确定要获取动态密码吗", 2,
                new ShowTipUtils.AlertDialogCallback() {
                    @Override
                    public void positive() {
                        startTimer();
                        loginView.showTimer();
                        loginModel.sendMsg(username, new MyRetrofitCallback() {
                            @Override
                            public void onSuccess(Object o) {

                            }

                            @Override
                            public void onFailed(String error) {
                                if(loginView == null){
                                    return;
                                }
                                ShowTipUtils.toastShort(loginView.getContext(),"短信发送失败");
                            }
                        });
                    }

                    @Override
                    public void negative() {

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
                    if(loginView != null){
                        loginView.setTimerText(TransformationUtils.second2min(l/1000).substring(3));
                    }
                }

                @Override
                public void onFinish() {
                    isStartTimer = false;
                    loginView.timerFinish();
                }
            };
//        }
        isStartTimer = true;
        mTimer.start();
    }

    @Override
    public void saveCurUser(LoginBean.LoginResponse response) {
        if(loginView == null){
            return;
        }
        SharedPreferences sharedPreferences = loginView.getContext().getSharedPreferences(KeyApp.SP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KeyApp.CUR_USER_NAME,response.getUsername());
        editor.putString(KeyApp.CUR_USER_ROOT,response.getUserRoot());
        editor.putInt(KeyApp.CUR_USER_ID,response.getId());
        editor.putBoolean(KeyApp.LOGIN_STATUS,response.isStatus());
        editor.commit();

//        MainPresenter.CUR_USER = response.getUsername();
    }

    @Override
    public void loginCheckbox(LoginBean loginBean) {
        loginModel.loginCheckbox(loginView.getContext(),loginView.getCheckBox(),loginBean);
    }

    /**
     * 初始化复选框和用户名、密码的状态
     */
    @Override
    public void init() {
        SharedPreferences sharedPreferences =
                loginView.getContext().getSharedPreferences(KeyApp.SP_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean(KeyApp.REMEMBER_PASSWORD,false)){
            loginView.setCbRemember();
        }
        if(sharedPreferences.getBoolean(KeyApp.AUTO_LOGIN,false)){
            loginView.setCbAuto();
        }
        loginView.setUsername(sharedPreferences.getString(KeyApp.LOGIN_NAME,""));
        loginView.setPassword(sharedPreferences.getString(KeyApp.LOGIN_PASSWORD,""));
    }

    @Override
    public void recycle() {
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
        loginView = null;
    }

}
