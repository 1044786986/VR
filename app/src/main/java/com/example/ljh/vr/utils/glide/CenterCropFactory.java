package com.example.ljh.vr.utils.glide;

public class CenterCropFactory implements IGetGlideOptions{
    @Override
    public IGlideOptions getGlideOptions() {
        return new CenterCropOptions();
    }
}
