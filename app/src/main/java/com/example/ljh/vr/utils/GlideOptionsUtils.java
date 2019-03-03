package com.example.ljh.vr.utils;

import android.content.Context;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.ljh.vr.R;
import com.example.ljh.vr._application.MyApplication;

public class GlideOptionsUtils {
    private static GlideOptionsUtils mGlideOptionsUtils;

    public static GlideOptionsUtils getInstance(){
        if(mGlideOptionsUtils == null){
            synchronized (GlideOptionsUtils.class){
                if(mGlideOptionsUtils == null){
                    mGlideOptionsUtils = new GlideOptionsUtils();
                }
            }
        }
        return mGlideOptionsUtils;
    }

    public RequestOptions centerAndRound(String type,int round){
        return defaultGlideOptions().centerCrop().transform(new RoundTransformation(type,round));
    }

    public RequestOptions defaultGlideOptions(){
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(MyApplication.GLIDE_LOAD_FAILED_IMG)
                .placeholder(MyApplication.GLIDE_LOADING_IMG);
        return requestOptions;
    }

    public static RequestOptions DisableDiskCache = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(MyApplication.GLIDE_LOAD_FAILED_IMG)
            .placeholder(MyApplication.GLIDE_LOADING_IMG);

    public static RequestOptions EnableDiskCache = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.mipmap.load_img_failed_gray_300);
}
