package com.example.ljh.vr.share;

import java.io.Serializable;
import java.util.List;

public class ShareBean implements Serializable {
    private String id;
    private String username;
    private String name;
    private String headerUrl;
    private String title;
    private String content;
    private List<Urls> imgUrls;
    private List<Urls> videoUrls;
    private String province;
    private String city;
    private String district;
    private String detail;
    private String date;
    private String latitude;
    private String longitude;

    class Urls implements Serializable{
        private String type;
        private String smallUrl;
        private String normalUrl;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSmallUrl() {
            return smallUrl;
        }

        public void setSmallUrl(String smallUrl) {
            this.smallUrl = smallUrl;
        }

        public String getNormalUrl() {
            return normalUrl;
        }

        public void setNormalUrl(String normalUrl) {
            this.normalUrl = normalUrl;
        }
    }

    public List<Urls> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<Urls> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<Urls> getVideoUrls() {
        return videoUrls;
    }

    public void setVideoUrls(List<Urls> videoUrls) {
        this.videoUrls = videoUrls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
