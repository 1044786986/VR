package com.example.ljh.vr.home;

import com.example.ljh.vr._base.BaseResBean;

import java.util.List;

public class HomeResBean extends BaseResBean{
    private List<HomeRvBean> data;
//    private int code;

    public List<HomeRvBean> getData() {
        return data;
    }

    public void setData(List<HomeRvBean> data) {
        this.data = data;
    }


//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
}
