package com.example.ljh.vr._base;

public class BaseBean{
    private int responseCode;
//    private T data;
    private Object data;

//    public SearchLogisticsBean getData() {
//        return data;
//    }
//
//    public void setData(SearchLogisticsBean data) {
//        this.data = data;
//    }
        public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

//    public T getData() {
//        return data;
//    }

//    public void setData(T data) {
//        this.data = data;
//    }
}
