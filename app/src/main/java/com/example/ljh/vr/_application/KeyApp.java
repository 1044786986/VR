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
    public static final String EB_LOGIN_SUCCESS = "loginSuccess";
    public static final String EB_LOGOUT = "logout";
    public static final String EB_HANDLE_TEM_ORDER = "handleTemOrder";
    public static final String EB_UPDATE_ADDRESS_BOOK = "updateAddressBook";
    public static final String EB_MODIFY_ADDRESS_BOOK = "modifyAddressBook";

    /**
     * StartActivityForResult Code
     */
    //requestCode
    public static final int SA_SCAN_SEARCH_CODE = 1000;             //扫描查询
    public static final int SA_SCAN_TRANSFER_CODE = 1001;
    public static final int SA_SCAN_COLLECT_CODE = 1002;            //扫描收件
    public static final int SA_MAIL_SENDER_ADDRESS_CODE = 1003;     //寄件选择寄件地址
    public static final int SA_MAIL_ADDRESSEE_ADDRESS_CODE = 1004;  //寄件选择收件地址
    public static final int SA_EDIT_MAIL_INFO_SENDER_CODE = 1005;   //寄件信息编辑
    public static final int SA_EDIT_MAIL_INFO_ADDRESSEE_CODE = 1006;//收件信息编辑
    public static final int SA_EDIT_MAIL_INFO_SENDER_BOOK_CODE = 1007;   //寄件信息编辑
    public static final int SA_EDIT_MAIL_INFO_ADDRESSEE_BOOK_CODE = 1008;//收件信息编辑
    public static final int SA_EDIT_MAIL_INFO_RESULT_CODE = 1009;   //信息编辑结果
    public static final int SA_SCAN_FLOAT_MENU_CODE = 1010;         //悬浮球操作

    //intent Key
    public static final String SA_SCAN_TRANSFER = "scanCollect";    //扫码收货页面
    public static final String SA_SHOW_QR = "showQrCode";           //显示二维码
    public static final String SA_SEARCH_RESULT = "searchResult";   //扫描二维码搜索结果
    public static final String SA_MAIL_ADDRESS_KEY = "addressMail"; //寄件选择地址
    public static final String SA_ADDRESS_TYPE = "addressType";     //判断选择地址的类型（寄件or收件）
    public static final String SA_ADDRESS_FROM = "addressFrom";   //判断上一页面是什么
//    public static final String SA_EDIT_MAIL_INFO_TYPE = "editMailInfoType";     //寄件页面编辑寄件人或收件人信息
    public static final String SA_EDIT_MAIL_INFO_FROM = "editMailInfoFrom";     //判断上一页面是什么
    public static final String SA_EDIT_MAIL_INFO_KEY = "editMailInfoKey";
    public static final String SA_EDIT_MAIL_INFO_RESULT = "editMailInfoResult"; //寄件页面编辑寄件人或收件人信息结果
    public static final String SA_WEBVIEW_URL_KEY = "webViewUrl";
    public static final String SA_COLLECT_FACE_RESULT = "faceResult";
}
