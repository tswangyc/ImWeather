package com.qingxu.imweather.bean;

import java.util.ArrayList;

/**
 * Created by QingXu on 2016/5/30.
 */
public class WeatherBean {

    private Observe observe;

    private ArrayList<Hourfc> hourfc;

    private ArrayList<Index> indices;

    private ArrayList<Xianhao> xianhao;

    private Evn evn;

    private Meta meta;
    private ArrayList<Forecast> forecast;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(ArrayList<Forecast> forecast) {
        this.forecast = forecast;
    }

    public Observe getObserve() {
        return observe;
    }

    public void setObserve(Observe observe) {
        this.observe = observe;
    }

    public ArrayList<Hourfc> getHourfc() {
        return hourfc;
    }

    public void setHourfc(ArrayList<Hourfc> hourfc) {
        this.hourfc = hourfc;
    }

    public ArrayList<Index> getIndices() {
        return indices;
    }

    public void setIndices(ArrayList<Index> indices) {
        this.indices = indices;
    }

    public ArrayList<Xianhao> getXianhao() {
        return xianhao;
    }

    public void setXianhao(ArrayList<Xianhao> xianhao) {
        this.xianhao = xianhao;
    }

    public Evn getEvn() {
        return evn;
    }

    public void setEvn(Evn evn) {
        this.evn = evn;
    }


}
