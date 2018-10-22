package com.example.ljh.vr._base;

public interface MyRetrofitCallback<T>{
    void onSuccess(T t);
    void onFailed(String error);
}
