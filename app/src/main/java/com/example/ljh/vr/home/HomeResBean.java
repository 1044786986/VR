package com.example.ljh.vr.home;

import java.util.List;

public class HomeResBean {
    private int code;
    private String error;
    private String imgUrl;
    private List<HomeRvBean> data;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<HomeRvBean> getData() {
        return data;
    }

    public void setData(List<HomeRvBean> data) {
        this.data = data;
    }
}
