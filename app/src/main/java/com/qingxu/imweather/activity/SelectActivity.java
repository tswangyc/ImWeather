package com.qingxu.imweather.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.qingxu.imweather.R;
import com.qingxu.imweather.bean.CityBean;
import com.qingxu.imweather.bean.CountyBean;
import com.qingxu.imweather.bean.ProvinceBean;
import com.qingxu.imweather.util.FileUtil;
import com.qingxu.imweather.util.JsonParseUtil;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by QingXu on 2016/5/29.
 */
public class SelectActivity extends AppCompatActivity {

    private ArrayAdapter<ProvinceBean> provinceAdapter = null;  //省级适配器
    private ArrayAdapter<CityBean> cityAdapter = null;    //地级适配器
    private ArrayAdapter<CountyBean> countyAdapter = null;    //县级适配器

    private Spinner provinceSpinner = null;  //省级（省、直辖市）
    private Spinner citySpinner = null;     //地级市
    private Spinner countySpinner = null;    //县级（区、县、县级市）

    private ArrayList<ProvinceBean> provinceBeans;
    private ArrayList<CityBean> cityBeens;
    private ArrayList<CountyBean> countyBeens;
    private CountyBean countyBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        provinceSpinner = (Spinner) findViewById(R.id.spinner_province);
        citySpinner = (Spinner) findViewById(R.id.spinner_city);
        countySpinner = (Spinner) findViewById(R.id.spinner_county);

        try {
            provinceBeans = JsonParseUtil.parseCity(FileUtil.readFile(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cityBeens = provinceBeans.get(0).getCityBeens();
        countyBeens = cityBeens.get(0).getCountyBeens();
        countyBean = countyBeens.get(0);

        provinceAdapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, provinceBeans);
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setSelection(0, true);

        cityAdapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, cityBeens);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setSelection(0, true);

        countyAdapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, countyBeens);
        countySpinner.setAdapter(countyAdapter);
        countySpinner.setSelection(0, true);


        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cityBeens = provinceBeans.get(position).getCityBeens();
                cityAdapter = new ArrayAdapter<>(SelectActivity.this, R.layout.custom_spinner_item, cityBeens);
                citySpinner.setAdapter(cityAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                countyBeens = cityBeens.get(position).getCountyBeens();
                countyAdapter = new ArrayAdapter<>(SelectActivity.this, R.layout.custom_spinner_item, countyBeens);
                countySpinner.setAdapter(countyAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countyBean = countyBeens.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void enter_main(View view) {
        SharedPreferences preferences = getSharedPreferences("com.qingxu.imweather.sharedpreferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirst", false);

        editor.putString("cityID", countyBean.getCode());
        editor.putString("location", countyBean.getName());

        editor.apply();

        Intent intent = new Intent(SelectActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
