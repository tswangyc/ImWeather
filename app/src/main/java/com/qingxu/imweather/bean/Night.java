package com.qingxu.imweather.bean;

import java.io.Serializable;

/**
 * Created by QingXu on 2016/5/30.
 */
public class Night implements Serializable {
    private String wthr;

    private String bgPic;

    private String wp;

    private String notice;

    private int type;

    private String wd;

    public String getWthr() {
        return wthr;
    }

    public void setWthr(String wthr) {
        this.wthr = wthr;
    }

    public String getBgPic() {
        return bgPic;
    }

    public void setBgPic(String bgPic) {
        this.bgPic = bgPic;
    }

    public String getWp() {
        return wp;
    }

    public void setWp(String wp) {
        this.wp = wp;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }
}
