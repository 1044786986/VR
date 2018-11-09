package com.example.ljh.vr.info;

import java.io.Serializable;

public class AlbumUrlBean implements Serializable {

    private String smallImg;
    private String vagueImg;
    private String normalImg;

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getVagueImg() {
        return vagueImg;
    }

    public void setVagueImg(String vagueImg) {
        this.vagueImg = vagueImg;
    }

    public String getNormalImg() {
        return normalImg;
    }

    public void setNormalImg(String normalImg) {
        this.normalImg = normalImg;
    }
}
