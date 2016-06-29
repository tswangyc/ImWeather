package com.qingxu.imweather.util;

import android.content.Context;

import com.qingxu.imweather.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by QingXu on 2016/5/29.
 */
public class FileUtil {

    public static String readFile(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.cityid);
        StringBuilder sb = new StringBuilder();
        String cityid = "";
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String temp = "";
            while ((temp = bf.readLine()) != null) {
                sb.append(temp);
                sb.append("\r\n");
            }
            bf.close();
            cityid = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityid;
    }
}
