package com.example.ljh.vr.utils.glide;

import com.bumptech.glide.request.RequestOptions;
import com.example.ljh.vr.utils.ScreenUtils;

public class ScaleSizeW2H1Options implements IGlideOptions{
    @Override
    public RequestOptions getOptions() {
        int width = ScreenUtils.getInstance().getScreenWidth();
        RequestOptions requestOptions = DefaultGlideOptionsFactory.getInstance().getGlideOptions().getOptions()
                .override(width,width/2)
                .centerCrop();
        return requestOptions;
    }
}
