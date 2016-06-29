package com.qingxu.imweather.bean;

import java.util.ArrayList;

/**
 * Created by QingXu on 2016/5/29.
 */
public class ProvinceBean {
    private String id;
    private String name;
    private ArrayList<CityBean> cityBeens;

    public ProvinceBean() {

    }

    public ProvinceBean(String id, String name, ArrayList<CityBean> cityBeens) {

        this.id = id;
        this.name = name;
        this.cityBeens = cityBeens;
    }

    public String toString() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CityBean> getCityBeens() {
        return cityBeens;
    }

    public void setCityBeens(ArrayList<CityBean> cityBeens) {
        this.cityBeens = cityBeens;
    }
}
