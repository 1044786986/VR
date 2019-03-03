package com.example.ljh.vr._base;

public class BaseBean<T>{
    private int code;
    private String error;
//    private T data;
    private T data;

    public BaseBean(T data){
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

        public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
