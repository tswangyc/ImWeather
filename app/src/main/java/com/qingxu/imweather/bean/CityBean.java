package com.qingxu.imweather.bean;

import java.util.ArrayList;

/**
 * Created by QingXu on 2016/5/29.
 */
public class CityBean {

    private String id;
    private String name;
    private ArrayList<CountyBean> countyBeens;

    public CityBean() {
    }

    public CityBean(String id, String name, ArrayList<CountyBean> countyBeens) {
        this.id = id;
        this.name = name;
        this.countyBeens = countyBeens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CountyBean> getCountyBeens() {
        return countyBeens;
    }

    public void setCountyBeens(ArrayList<CountyBean> countyBeens) {
        this.countyBeens = countyBeens;
    }

}
