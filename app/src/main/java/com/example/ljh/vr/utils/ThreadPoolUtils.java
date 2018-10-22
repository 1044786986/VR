package com.example.ljh.vr.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {
    private final int CORE_POOL_SIZE = 10;
    private final int MAX_POOL_SIZE = 10;
    private final int TIME_OUT = 60;
    private final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static ThreadPoolUtils mThreadPoolUtils;
    private ThreadPoolExecutor mThreadPoolExecutor;

    ThreadPoolUtils(){
        mThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,MAX_POOL_SIZE,TIME_OUT,TIME_UNIT,
                new LinkedBlockingQueue<Runnable>());

    }

    public static ThreadPoolUtils getInstance(){
        if(mThreadPoolUtils == null){
            synchronized (ThreadPoolUtils.class){
                mThreadPoolUtils = new ThreadPoolUtils();
            }
        }
        return mThreadPoolUtils;
    }

    public void execute(Runnable runnable){
        if(runnable != null){
            mThreadPoolExecutor.execute(runnable);
        }
    }

    public void remove(Runnable runnable){
        if(runnable != null){
            mThreadPoolExecutor.execute(runnable);
        }
    }
}
