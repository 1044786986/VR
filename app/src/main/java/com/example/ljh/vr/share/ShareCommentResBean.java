package com.example.ljh.vr.share;

import com.example.ljh.vr._base.BaseResBean;

import java.util.List;

public class ShareCommentResBean extends BaseResBean {
    private List<ShareCommentBean> data;

    public List<ShareCommentBean> getData() {
        return data;
    }

    public void setData(List<ShareCommentBean> data) {
        this.data = data;
    }
}
