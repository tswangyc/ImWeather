package com.qingxu.imweather.app;

import android.app.Application;

import com.qingxu.imweather.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QingXu on 2016/5/20.
 */
public class MyApplication extends Application {

    private Map<String, Integer> iconMap;

    @Override
    public void onCreate() {
        super.onCreate();


        Map<String, Integer> iconMap = new HashMap<>();
        iconMap.put("晴", R.mipmap.d00);
        iconMap.put("多云", R.mipmap.d01);
        iconMap.put("阴", R.mipmap.d02);
        iconMap.put("阵雨", R.mipmap.d03);
        iconMap.put("雷阵雨", R.mipmap.d04);
        iconMap.put("雷阵雨伴有冰雹", R.mipmap.d05);
        iconMap.put("雨夹雪", R.mipmap.d06);
        iconMap.put("小雨", R.mipmap.d07);
        iconMap.put("中雨", R.mipmap.d08);
        iconMap.put("大雨", R.mipmap.d09);
        iconMap.put("暴雨", R.mipmap.d10);
        iconMap.put("大暴雨", R.mipmap.d11);
        iconMap.put("特大暴雨", R.mipmap.d12);
        iconMap.put("阵雪", R.mipmap.d13);
        iconMap.put("小雪", R.mipmap.d14);
        iconMap.put("中雪", R.mipmap.d15);
        iconMap.put("大雪", R.mipmap.d16);
        iconMap.put("暴雪", R.mipmap.d17);
        iconMap.put("雾", R.mipmap.d18);
        iconMap.put("冻雨", R.mipmap.d19);
        iconMap.put("沙尘暴", R.mipmap.d20);
        iconMap.put("小到中雨", R.mipmap.d21);
        iconMap.put("中到大雨", R.mipmap.d22);
        iconMap.put("大到暴雨", R.mipmap.d23);
        iconMap.put("暴雨到大暴雨", R.mipmap.d24);
        iconMap.put("大暴雨到特大暴雨", R.mipmap.d25);
        iconMap.put("小到中雪", R.mipmap.d26);
        iconMap.put("中到大雪", R.mipmap.d27);
        iconMap.put("大到暴雪", R.mipmap.d28);
        iconMap.put("浮尘", R.mipmap.d29);
        iconMap.put("扬沙", R.mipmap.d30);
        iconMap.put("强沙尘暴", R.mipmap.d31);
        iconMap.put("霾", R.mipmap.d53);
        iconMap.put("无", R.mipmap.d99);

        setIconMap(iconMap);//初始化全局变量
    }


    public Map<String, Integer> getIconMap() {
        return iconMap;
    }

    public void setIconMap(Map<String, Integer> iconMap) {
        this.iconMap = iconMap;
    }
}
