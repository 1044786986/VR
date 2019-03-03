package com.example.ljh.vr.utils.glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.ljh.vr._application.MyApplication;

public class DefaultGlideOptions implements IGlideOptions{
    @Override
    public RequestOptions getOptions() {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(MyApplication.GLIDE_LOAD_FAILED_IMG)
                .placeholder(MyApplication.GLIDE_LOADING_IMG);
        return requestOptions;
    }
}
