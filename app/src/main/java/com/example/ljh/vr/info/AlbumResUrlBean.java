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

//    public class AlbumUrlBean{
//        private String small_img;
//        private String vague_img;
//        private String normal_img;
//
//        public String getSmall_img() {
//            return small_img;
//        }
//
//        public void setSmall_img(String small_img) {
//            this.small_img = small_img;
//        }
//
//        public String getVague_img() {
//            return vague_img;
//        }
//
//        public void setVague_img(String vague_img) {
//            this.vague_img = vague_img;
//        }
//
//        public String getNormal_img() {
//            return normal_img;
//        }
//
//        public void setNormal_img(String normal_img) {
//            this.normal_img = normal_img;
//        }
//    }
}
