package com.example.ljh.vr.share;

import com.example.ljh.vr._base.BaseResBean;

import java.util.List;

public class ShareResBean extends BaseResBean {
    private List<ShareBean> data;

    public List<ShareBean> getData() {
        return data;
    }

    public void setData(List<ShareBean> data) {
        this.data = data;
    }
}
