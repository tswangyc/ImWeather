package com.qingxu.imweather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by QingXu on 2016/5/12.
 */
public class RequestUtil {

    public static String weatherRequest(String cityId) {

        BufferedReader reader = null;
        String result = null;
        StringBuilder sbf = new StringBuilder();
        String httpUrl = "http://zhwnlapi.etouch.cn/Ecalender/api/v2/weather?citykey=" + cityId;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }

            reader.close();
            result = sbf.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static String reverseRequest(String httpArg) {

        BufferedReader reader;
        String result = null;
        StringBuilder sb = new StringBuilder();
        String httpUrl = "http://api.map.baidu.com/geocoder/v2/?ak=52465a5d977cfe407e4ed30205cb8f3a&callback=renderReverse&location=" + httpArg + "&output=json&pois=1";

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
                sb.append("\r\n");
            }

            reader.close();

            result = sb.toString();
            result = result.substring(29, result.length() - 1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}