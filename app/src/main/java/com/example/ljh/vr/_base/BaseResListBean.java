package com.example.ljh.vr._base;

import java.util.List;

public class BaseResListBean<T> extends BaseResBean{
//    private int code;
//    private String error;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
