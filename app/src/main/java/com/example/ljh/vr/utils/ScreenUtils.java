package com.example.ljh.vr.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.example.ljh.vr.R;
import com.example.ljh.vr._application.MyApplication;


public class ScreenUtils {
    private Context context;
    private DisplayMetrics dm;
    static ScreenUtils mScreenUtils;

    public ScreenUtils(){
        this.context = MyApplication.getInstance();
        this.dm = context.getResources().getDisplayMetrics();
    }

    public static ScreenUtils getInstance(){
        if(mScreenUtils == null){
            synchronized (ScreenUtils.class){
                if(mScreenUtils == null){
                    mScreenUtils = new ScreenUtils();
                }
            }
        }
        return mScreenUtils;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public int getScreenWidth(){
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public int getScreenHeight(){
        return dm.heightPixels;
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight(){
        int id = context.getResources().getIdentifier("status_bar_height","dimen","android");
        if(id > 0){
            return context.getResources().getDimensionPixelSize(id);
        }
        return 0;
    }

    /**
     * 获取主页底部导航栏高度
     * @return
     */
    public int getBottomBarHeight(){
        int height = (int) context.getResources().getDimension(R.dimen.dp_50);
        return height;
    }
}
