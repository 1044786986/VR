package com.example.ljh.vr.utils.glide;

public class EnableDiskCacheFactory implements IGetGlideOptions{
    @Override
    public IGlideOptions getGlideOptions() {
        return new EnableDiskCacheOptions();
    }
}
