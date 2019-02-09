package com.example.ljh.vr.select_city;

import java.util.List;

public class HotCityBean {
//    private int code;
//    private String error;
//    private List<String> data;

//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getError() {
//        return error;
//    }
//
//    public void setError(String error) {
//        this.error = error;
//    }
//
//    public List<String> getData() {
//        return data;
//    }
//
//    public void setData(List<String> data) {
//        this.data = data;
//    }
    private Integer id;

    private String province;

    private String city;

    private Integer hot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}
