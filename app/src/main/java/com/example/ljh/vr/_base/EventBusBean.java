package com.example.ljh.vr._base;

public class EventBusBean<T>{
    private String type;
    private T t;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
