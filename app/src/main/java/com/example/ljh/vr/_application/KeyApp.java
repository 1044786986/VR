package com.example.ljh.vr._application;

/**
 * 定义SharedPreferences的KEY和名称
 */
public class KeyApp {
    public static final String SP_NAME = "logistics";
    /**
     * SharedPreferences
     */
    public static final String REMEMBER_PASSWORD = "remember_password";     //记住密码
    public static final String AUTO_LOGIN = "auto_login";                   //自动登录
    public static final String LOGIN_NAME = "login_username";               //登录帐号
    public static final String LOGIN_PASSWORD = "login_password";           //登录密码

    public static final String LOGIN_STATUS = "login_status";
    public static final String CUR_USER_NAME = "cur_username";
    public static final String CUR_PASS_WORD = "cur_password";
    public static final String CUR_USER_ROOT = "cur_user_root";
    public static final String CUR_USER_ID = "cur_user_id";

    public static final String BLUE_TOOTH_ADDRESS = "bluetoothAddress";

    /**
     * EventBus
     */


    /**
     * StartActivityForResult Code
     */
    /**
     * requestCode
     */

    /**
     * resultCode
     */
    public static final int RESULT_CODE_SELECT_CITY = 20000;
    //

    //Request intent Key

    //Result Intent Key
    public static final String RESULT_KEY_SELECT_CITY = "city";
}
