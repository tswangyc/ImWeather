package com.qingxu.imweather.bean;

import java.io.Serializable;

/**
 * Created by QingXu on 2016/5/30.
 */
public class Hourfc implements Serializable {
    private String time;
    private int wthr;
    private String shidu;
    private String wp;
    private String wd;
    private int type;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getWthr() {
        return wthr;
    }

    public void setWthr(int wthr) {
        this.wthr = wthr;
    }

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public String getWp() {
        return wp;
    }

    public void setWp(String wp) {
        this.wp = wp;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
