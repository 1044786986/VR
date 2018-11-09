package com.example.ljh.vr.setting;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.BaseCallback;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.EventBusBean;
import com.example.ljh.vr.utils.CheckLoginUtils;
import com.example.ljh.vr.utils.ShowTipUtils;

import org.greenrobot.eventbus.EventBus;

public class SettingPresenter extends BasePresenter implements SettingContract.SettingPresenter{
    private SettingContract.SettingView settingView;
    private SettingContract.SettingModel settingModel;

    SettingPresenter(BaseView baseView){
        super(baseView);
        this.settingView = (SettingContract.SettingView) baseView;
        settingModel = new SettingModel();
    }

    @Override
    public void recycle() {
        settingView = null;
    }

    @Override
    public boolean checkViewNull() {
        return settingView == null;
    }

    @Override
    public void quitLogin() {
        if(!CheckLoginUtils.getInstance().checkLogin(settingView.getMyContext())){
            ShowTipUtils.toastShort(settingView.getMyContext(),"请先登录");
            return;
        }
        SharedPreferences sp = settingView.getMyContext()
                .getSharedPreferences(KeyApp.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KeyApp.CUR_USER_ID,0);
        editor.putString(KeyApp.CUR_USER_NAME,"");
        editor.putString(KeyApp.CUR_PASS_WORD,"");
        editor.putString(KeyApp.CUR_USER_ROOT,"");
        editor.putString(KeyApp.LOGIN_PASSWORD,"");
        editor.putBoolean(KeyApp.LOGIN_STATUS,false);
        editor.putBoolean(KeyApp.AUTO_LOGIN,false);
        editor.commit();
        EventBusBean bean = new EventBusBean();
        bean.setType(KeyApp.EB_LOGOUT);
        EventBus.getDefault().post(bean);
        settingView.myFinish();
    }

    @Override
    public void getCacheSize() {
        ShowTipUtils.showProgressDialog(settingView.getMyContext(),"正在计算缓存...");
//        settingModel.getCacheSize(settingView.getMyContext(),new BaseCallback() {
//            @Override
//            public void onSuccess(Object o) {
//                ShowTipUtils.dismissProgressDialog();
//                if((double)o == 0.0){
//                    ShowTipUtils.toastShort(settingView.getMyContext(),"当前应用程序很干净");
//                    return;
//                }

//                ShowTipUtils.showAlertDialog(settingView.getMyContext(), "确定要清除缓存吗("+o+"M)",
//                        2, new ShowTipUtils.AlertDialogCallback() {
//                            @Override
//                            public void positive() {
//                                ShowTipUtils.showProgressDialog(settingView.getMyContext(),
//                                        "正在清除缓存...");
//                                settingModel.clearCache(settingView.getMyContext(), new BaseCallback() {
//                                    @Override
//                                    public void onSuccess(Object o) {
//                                        ShowTipUtils.dismissProgressDialog();
//                                    }
//
//                                    @Override
//                                    public void onFailed(String error) {
//                                        ShowTipUtils.dismissProgressDialog();
//                                    }
//                                });
//                            }

//                            @Override
//                            public void negative() {}
//                        });
//            }

//            @Override
//            public void onFailed(String error) {
//                ShowTipUtils.dismissProgressDialog();
//                ShowTipUtils.toastShort(settingView.getMyContext(),error);
//            }
//        });
    }

    @Override
    public void clearCache() {

    }
}
