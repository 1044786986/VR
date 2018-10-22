package com.example.ljh.vr._base;

import java.util.List;

public class BaseResListBean<T>{
    private int responseCode;
    private List<T> data;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
