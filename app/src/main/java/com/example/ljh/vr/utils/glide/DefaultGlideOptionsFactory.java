package com.example.ljh.vr.utils.glide;

public class DefaultGlideOptionsFactory implements IGetGlideOptions{
    static DefaultGlideOptionsFactory mDefaultGlideOptionsFactory;

    public static DefaultGlideOptionsFactory getInstance(){
        if(mDefaultGlideOptionsFactory == null){
            synchronized (DefaultGlideOptionsFactory.class){
                if(mDefaultGlideOptionsFactory == null){
                    mDefaultGlideOptionsFactory = new DefaultGlideOptionsFactory();
                }
            }
        }
        return mDefaultGlideOptionsFactory;
    }

    @Override
    public IGlideOptions getGlideOptions() {
        return new DefaultGlideOptions();
    }
}
