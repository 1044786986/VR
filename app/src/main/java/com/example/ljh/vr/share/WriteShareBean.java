package com.example.ljh.vr.share;

import java.util.List;

public class WriteShareBean {
    private String username;
    private String title;
    private String content;
    private String province;
    private String city;
    private String district;
    private String detail;
    private List<Img> imgs;

    class Img{
        private byte[] smallImg;
        private byte[] normalImg;

        public byte[] getSmallImg() {
            return smallImg;
        }

        public void setSmallImg(byte[] smallImg) {
            this.smallImg = smallImg;
        }

        public byte[] getNormalImg() {
            return normalImg;
        }

        public void setNormalImg(byte[] normalImg) {
            this.normalImg = normalImg;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<Img> getImgs() {
        return imgs;
    }

    public void setImgs(List<Img> imgs) {
        this.imgs = imgs;
    }
}
