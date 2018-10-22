package com.example.ljh.vr.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

public class LruCacheUtils {
    private static LruCacheUtils mLruCacheUtils;
    public LruCache mLruCache;

    LruCacheUtils(){
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mLruCache = new LruCache<Object,Object>(cacheSize){
            @Override
            protected int sizeOf(Object key, Object value) {
                if(value instanceof Bitmap){
                    return ((Bitmap) value).getByteCount();
                }
                return 0;
            }
        };
    }

    public static LruCacheUtils getInstance(){
        if(mLruCacheUtils == null){
            synchronized (LruCacheUtils.class){
                mLruCacheUtils = new LruCacheUtils();
            }
        }
        return mLruCacheUtils;
    }

    public void put(Object o1,Object o2){
        mLruCache.put(o1,o2);
    }

    public Object get(Object key){
        return mLruCache.get(key);
    }

    public void remove(Object key){
        mLruCache.remove(key);
    }
}
