package com.qingxu.imweather.bean;

/**
 * Created by QingXu on 2016/6/1.
 */
public class Meta {

    /**
     * "html_url":"http://yun.rili.cn/tianqi/1o4zw4.html",
     * "status":1000,
     * "post_count":3547,
     * "up_time":"18:25",
     * "citykey":"101010100",
     * "post_id":"501391",
     * "city":"北京"
     */

    private String html_url;
    private int status;
    private int post_count;
    private String up_time;
    private String citykey;
    private String post_id;
    private String city;

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getCitykey() {
        return citykey;
    }

    public void setCitykey(String citykey) {
        this.citykey = citykey;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
