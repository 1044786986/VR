package com.example.ljh.vr.info;

import com.example.ljh.vr._base.BaseResBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AlbumResUrlBean extends BaseResBean implements Serializable {
    private List<AlbumUrlBean> data;

    public List<AlbumUrlBean> getData() {
        return data;
    }

    public void setData(List<AlbumUrlBean> data) {
        this.data = data;
    }
}
