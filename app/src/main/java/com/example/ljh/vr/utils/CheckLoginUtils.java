package com.example.ljh.vr.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr.login.LoginActivity;
import com.example.ljh.vr.login.LoginBean;

public class CheckLoginUtils {
    private static CheckLoginUtils mCheckLoginUtils;

    public static CheckLoginUtils getInstance(){
        if(mCheckLoginUtils == null){
            synchronized(CheckLoginUtils.class){
                mCheckLoginUtils = new CheckLoginUtils();
            }
        }
        return mCheckLoginUtils;
    }

    public interface GetCurUserInfoCallback{
        void onSuccess(LoginBean.LoginResponse loginResponse);
        void onFailed();
    }

    public void getCurUserInfo(Context context, GetCurUserInfoCallback callback){
        SharedPreferences sp = context.getSharedPreferences(KeyApp.SP_NAME,Context.MODE_PRIVATE);
        boolean loginStatus = sp.getBoolean(KeyApp.LOGIN_STATUS,false);
        int id = sp.getInt(KeyApp.CUR_USER_ID,0);
        String username = sp.getString(KeyApp.CUR_USER_NAME,"");
        String userRoot = sp.getString(KeyApp.CUR_USER_ROOT,"");
        if(!loginStatus || id == 0 || username == "" || userRoot == ""){
            callback.onFailed();
            return;
        }
        LoginBean.LoginResponse loginResponse = new LoginBean.LoginResponse();
        loginResponse.setStatus(loginStatus);
        loginResponse.setId(id);
        loginResponse.setUsername(username);
        loginResponse.setUserRoot(userRoot);
        callback.onSuccess(loginResponse);
    }

    public LoginBean.LoginResponse getCurUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences(KeyApp.SP_NAME,Context.MODE_PRIVATE);
        boolean loginStatus = sp.getBoolean(KeyApp.LOGIN_STATUS,false);
        int id = sp.getInt(KeyApp.CUR_USER_ID,0);
        String username = sp.getString(KeyApp.CUR_USER_NAME,"");
        String userRoot = sp.getString(KeyApp.CUR_USER_ROOT,"");
        if(!loginStatus || id == 0 || username == "" || userRoot == ""){
            return null;
        }
        LoginBean.LoginResponse loginResponse = new LoginBean.LoginResponse();
        loginResponse.setStatus(loginStatus);
        loginResponse.setId(id);
        loginResponse.setUsername(username);
        loginResponse.setUserRoot(userRoot);
        return loginResponse;
    }

    public String getCurUserName(Context context){
        SharedPreferences sp = context.getSharedPreferences(KeyApp.SP_NAME,Context.MODE_PRIVATE);
        String username = sp.getString(KeyApp.CUR_USER_NAME,"");
        return username;
    }

    public boolean checkLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences(KeyApp.SP_NAME,Context.MODE_PRIVATE);
        if(sp.getBoolean(KeyApp.LOGIN_STATUS,false) && !sp.getString(KeyApp.CUR_USER_NAME,"").equals("")){
            return true;
        }
        return false;
    }

    public void toLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
