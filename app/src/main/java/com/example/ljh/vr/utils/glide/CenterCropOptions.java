package com.example.ljh.vr.utils.glide;

import com.bumptech.glide.request.RequestOptions;

public class CenterCropOptions implements IGlideOptions{
    @Override
    public RequestOptions getOptions() {
        RequestOptions requestOptions = DefaultGlideOptionsFactory.getInstance().getGlideOptions().getOptions()
                .centerCrop();
        return requestOptions;
    }
}
