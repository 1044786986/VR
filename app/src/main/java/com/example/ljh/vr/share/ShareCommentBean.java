package com.example.ljh.vr.share;

import java.io.Serializable;
import java.util.List;

public class ShareCommentBean {
    private String id;
    private String shareId;
    private String username;
    private String name;
    private String headerUrl;
    private String content;
    private String date;
    private List<Urls> imgUrls;
    private List<Urls> videoUrls;

    class Urls implements Serializable {
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

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
