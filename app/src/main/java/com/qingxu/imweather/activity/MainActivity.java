package com.qingxu.imweather.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.qingxu.imweather.R;
import com.qingxu.imweather.adapter.HourlyAdapter;
import com.qingxu.imweather.adapter.IndexAdapter;
import com.qingxu.imweather.app.MyApplication;
import com.qingxu.imweather.bean.CityBean;
import com.qingxu.imweather.bean.CountyBean;
import com.qingxu.imweather.bean.Forecast;
import com.qingxu.imweather.bean.Hourfc;
import com.qingxu.imweather.bean.Index;
import com.qingxu.imweather.bean.ProvinceBean;
import com.qingxu.imweather.bean.WeatherBean;
import com.qingxu.imweather.util.FileUtil;
import com.qingxu.imweather.util.HttpPicUtil;
import com.qingxu.imweather.util.JsonParseUtil;
import com.qingxu.imweather.util.NetUtil;
import com.qingxu.imweather.util.RequestUtil;
import com.qingxu.imweather.view.CustomProgressBar;

import org.json.JSONException;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.view.WindowManager.LayoutParams;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    protected static final int SWIPEREFRESH = 1;
    private LinearLayout ll_vtoolbar;
    private TextView tv_now_tmp,
            tv_basic_city,
            tv_now_cond_txt,
            tv_now_tmp_tigan,
            tv_now_wind_dir,
            tv_now_wind_sc,
            tv_now_hum,
            tv_aqi_qlty,
            tv_daily_forecast_today_cond_txt_d,
            tv_daily_forecast_tomorrow_cond_txt_d,
            tv_daily_forecast_after_tomorrow_cond_txt_d,
            tv_daily_forecast_today_tmp_max,
            tv_daily_forecast_tomorrow_tmp_max,
            tv_daily_forecast_after_tomorrow_tmp_max,
            tv_daily_forecast_today_tmp_min,
            tv_daily_forecast_tomorrow_tmp_min,
            tv_daily_forecast_after_tomorrow_tmp_min;
    private ImageView iv_daily_forecast_today_cond_code_d,
            iv_daily_forecast_tomorrow_cond_code_d,
            iv_daily_forecast_after_tomorrow_cond_code_d;

    private SwipeRefreshLayout swipeRefreshLayout;
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

    private PopupWindow mPopupWindow;
    private SharedPreferences preferences;
    private MyApplication app;
    private String jsonWeatherResult;
    private WeatherBean weatherBean;
    private String cityID, location;
    private ImageView iv_vtoolbar;
    private TextView tv_vtoolbar;
    private ImageView rl_now;
    private Bitmap bitmap;
    private RecyclerView recyclerview_hourly, recyclerview_index;
    private ArrayList<Hourfc> hourfcs;
    private ArrayList<Index> indices;
    private NestedScrollView nestedScrollView;
    private TextView tv_aqi_pm25;
    private TextView tv_aqi_pm10;
    private TextView tv_aqi_no2;
    private TextView tv_aqi_so2;
    private TextView tv_aqi_co;
    private TextView tv_aqi_o3;
    private CustomProgressBar progressBar_aqi;
    private CustomProgressBar progressBar_mp;
    private TextView tv_aqi_qlty_desc;
    private TextView tv_aqi_time;
    private TextView tv_aqi_suggest;
    private LinearLayout ll_aqi;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SWIPEREFRESH://可以执行UI操作
                    swipeRefreshLayout.setRefreshing(false);
                    if (NetUtil.isNetworkAvailable(MainActivity.this)) {
                        loadWeatherData();
                    }

                    break;
            }
        }
    };
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            /**
             * 并移出swipeRefreshLayout.setRefreshing(false)
             * 避免出现Only the original thread that created a view hierarchy can touch its views;
             */
            //TODU 执行耗时任务
            preferences = getSharedPreferences("com.qingxu.imweather.sharedpreferences", Activity.MODE_PRIVATE);
            cityID = preferences.getString("cityID", "");
            location = preferences.getString("location", "");

            if (NetUtil.isNetworkAvailable(MainActivity.this)) {
                jsonWeatherResult = RequestUtil.weatherRequest(cityID);

                //移至此处
                try {
                    weatherBean = JsonParseUtil.parseWeather(jsonWeatherResult);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                bitmap = HttpPicUtil.getHttpPic(weatherBean.getForecast().get(1).getDay().getBgPic());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = Message.obtain();
            msg.what = SWIPEREFRESH;
            mHandler.sendMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (MyApplication) getApplication();//获得MyApplication

        InitWidgets();
        Refresh();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);//正在刷新
                sendMessage();
            }
        });

    }

    /**
     * 下拉刷新
     */

    private void Refresh() {

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                sendMessage();
            }
        });

    }

    public void open_daily_forecast(View v) {
        if (NetUtil.isNetworkAvailable(MainActivity.this)) {
            Intent intent = new Intent(MainActivity.this, DailyActivity.class);
            //TUDU
            ArrayList<Forecast> forecasts = weatherBean.getForecast();
            Bundle bundle = new Bundle();
            bundle.putSerializable("forecasts", (Serializable) forecasts);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }


    }

    private void sendMessage() {
        Thread mThread = new Thread(mRunnable);
        mThread.start();

    }

    /**
     * 初始化控件
     */

    private void InitWidgets() {
        //InitTextView
        tv_now_tmp = (TextView) findViewById(R.id.tv_now_tmp);
        tv_basic_city = (TextView) findViewById(R.id.tv_basic_city);
        tv_now_cond_txt = (TextView) findViewById(R.id.tv_now_cond_txt);
        tv_now_tmp_tigan = (TextView) findViewById(R.id.tv_now_tmp_tigan);
        tv_now_wind_dir = (TextView) findViewById(R.id.tv_now_wind_dir);
        tv_now_wind_sc = (TextView) findViewById(R.id.tv_now_wind_sc);
        tv_now_hum = (TextView) findViewById(R.id.tv_now_hum);
        tv_aqi_qlty = (TextView) findViewById(R.id.tv_aqi_qlty);

        tv_daily_forecast_today_cond_txt_d = (TextView) findViewById(R.id.tv_daily_forecast_today_cond_txt_d);
        tv_daily_forecast_today_tmp_max = (TextView) findViewById(R.id.tv_daily_forecast_today_tmp_max);
        tv_daily_forecast_today_tmp_min = (TextView) findViewById(R.id.tv_daily_forecast_today_tmp_min);
        tv_daily_forecast_tomorrow_cond_txt_d = (TextView) findViewById(R.id.tv_daily_forecast_tomorrow_cond_txt_d);
        tv_daily_forecast_tomorrow_tmp_max = (TextView) findViewById(R.id.tv_daily_forecast_tomorrow_tmp_max);
        tv_daily_forecast_tomorrow_tmp_min = (TextView) findViewById(R.id.tv_daily_forecast_tomorrow_tmp_min);
        tv_daily_forecast_after_tomorrow_cond_txt_d = (TextView) findViewById(R.id.tv_daily_forecast_after_tomorrow_cond_txt_d);
        tv_daily_forecast_after_tomorrow_tmp_max = (TextView) findViewById(R.id.tv_daily_forecast_after_tomorrow_tmp_max);
        tv_daily_forecast_after_tomorrow_tmp_min = (TextView) findViewById(R.id.tv_daily_forecast_after_tomorrow_tmp_min);

        //InitImageView
        iv_daily_forecast_today_cond_code_d = (ImageView) findViewById(R.id.iv_daily_forecast_today_cond_code_d);
        iv_daily_forecast_tomorrow_cond_code_d = (ImageView) findViewById(R.id.iv_daily_forecast_tomorrow_cond_code_d);
        iv_daily_forecast_after_tomorrow_cond_code_d = (ImageView) findViewById(R.id.iv_daily_forecast_after_tomorrow_cond_code_d);

        //InitLayout
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);//获取下拉刷新控件

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_light, R.color.holo_red_light, R.color.holo_orange_light, R.color.holo_green_light);
        }


        ll_vtoolbar = (LinearLayout) findViewById(R.id.ll_vtoolbar);
        iv_vtoolbar = (ImageView) findViewById(R.id.iv_vtoobar);
        tv_vtoolbar = (TextView) findViewById(R.id.tv_vtoolbar);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedscrollview);


        if (nestedScrollView != null) {
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY == 0) {
                        swipeRefreshLayout.setEnabled(true);
                        ll_vtoolbar.setAlpha(0.0f);
                    } else {
                        swipeRefreshLayout.setEnabled(false);
                        ll_vtoolbar.setAlpha(1.0f * scrollY / (rl_now.getHeight() - ll_vtoolbar.getHeight()));
                    }

                }
            });
        }

        rl_now = (ImageView) findViewById(R.id.iv_now);
        recyclerview_hourly = (RecyclerView) findViewById(R.id.recyclerview_hourly);

        ll_aqi = (LinearLayout) findViewById(R.id.ll_aqi);
        tv_aqi_qlty_desc = (TextView) findViewById(R.id.tv_aqi_qlty_desc);
        tv_aqi_time = (TextView) findViewById(R.id.tv_aqi_time);

        progressBar_aqi = (CustomProgressBar) findViewById(R.id.customProgressbar_aqi);
        progressBar_mp = (CustomProgressBar) findViewById(R.id.customProgressbar_mp);

        tv_aqi_pm25 = (TextView) findViewById(R.id.tv_aqi_pm25);
        tv_aqi_pm10 = (TextView) findViewById(R.id.tv_aqi_pm10);
        tv_aqi_no2 = (TextView) findViewById(R.id.tv_aqi_no2);
        tv_aqi_so2 = (TextView) findViewById(R.id.tv_aqi_so2);
        tv_aqi_co = (TextView) findViewById(R.id.tv_aqi_co);
        tv_aqi_o3 = (TextView) findViewById(R.id.tv_aqi_o3);

        tv_aqi_suggest = (TextView) findViewById(R.id.tv_aqi_suggest);

        //Index指数
        recyclerview_index = (RecyclerView) findViewById(R.id.recyclerview_index);
    }

    private void loadWeatherData() {

        //填充当前天气
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date curDate = new Date(System.currentTimeMillis());//获取当前日期
        long curTime = curDate.getTime();
        String dateStr = sdf.format(curDate);//2016-05-30

        String sunriseStr = dateStr + " " + weatherBean.getForecast().get(1).getSunrise();//04:49
        String sunsetStr = dateStr + " " + weatherBean.getForecast().get(1).getSunset();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);//格式如：2016-06-02 15:59

        long sunriseTime = 0;
        long sunsetTime = 0;
        try {
            sunriseTime = format.parse(sunriseStr).getTime();
            sunsetTime = format.parse(sunsetStr).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (curTime >= sunriseTime && curTime <= sunsetTime) {
            tv_now_cond_txt.setText(weatherBean.getForecast().get(1).getDay().getWthr());

        } else {
            tv_now_cond_txt.setText(weatherBean.getForecast().get(1).getNight().getWthr());
        }

        tv_now_tmp.setText(weatherBean.getObserve().getTemp() + "°");
        tv_basic_city.setText(location);

        tv_now_tmp_tigan.setText(weatherBean.getObserve().getTigan() + "°");
        tv_now_wind_dir.setText(weatherBean.getObserve().getWd());
        tv_now_wind_sc.setText(weatherBean.getObserve().getWp());
        tv_now_hum.setText(weatherBean.getObserve().getShidu());
        if (weatherBean.getEvn() == null) {
            tv_aqi_qlty.setText("N/A");

        } else {
            tv_aqi_qlty.setText(weatherBean.getEvn().getQuality());
        }
        //填充vtoolbar
        iv_vtoolbar.setImageResource(app.getIconMap().get(tv_now_cond_txt.getText().toString()));
        tv_vtoolbar.setText(tv_basic_city.getText().toString() + " " + tv_now_tmp.getText().toString());
        //填充背景图
        rl_now.setImageBitmap(bitmap);

        //填充3天天气预报
        tv_daily_forecast_today_cond_txt_d.setText(weatherBean.getForecast().get(1).getDay().getWthr());
        tv_daily_forecast_today_tmp_max.setText(String.valueOf(weatherBean.getForecast().get(1).getHigh()));
        tv_daily_forecast_today_tmp_min.setText(String.valueOf(weatherBean.getForecast().get(1).getLow()));
        iv_daily_forecast_today_cond_code_d.setImageResource(app.getIconMap().get(weatherBean.getForecast().get(1).getDay().getWthr()));

        tv_daily_forecast_tomorrow_cond_txt_d.setText(weatherBean.getForecast().get(2).getDay().getWthr());
        tv_daily_forecast_tomorrow_tmp_max.setText(String.valueOf(weatherBean.getForecast().get(2).getHigh()));
        tv_daily_forecast_tomorrow_tmp_min.setText(String.valueOf(weatherBean.getForecast().get(2).getLow()));
        iv_daily_forecast_tomorrow_cond_code_d.setImageResource(app.getIconMap().get(weatherBean.getForecast().get(2).getDay().getWthr()));

        tv_daily_forecast_after_tomorrow_cond_txt_d.setText(weatherBean.getForecast().get(3).getDay().getWthr());
        tv_daily_forecast_after_tomorrow_tmp_max.setText(String.valueOf(weatherBean.getForecast().get(3).getHigh()));
        tv_daily_forecast_after_tomorrow_tmp_min.setText(String.valueOf(weatherBean.getForecast().get(3).getLow()));
        iv_daily_forecast_after_tomorrow_cond_code_d.setImageResource(app.getIconMap().get(weatherBean.getForecast().get(3).getDay().getWthr()));

        //填充24小时预报
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview_hourly.setLayoutManager(mLayoutManager);
        recyclerview_hourly.setHasFixedSize(true);
        hourfcs = weatherBean.getHourfc();
        HourlyAdapter hourlyAdapter = new HourlyAdapter(this, hourfcs);
        recyclerview_hourly.setAdapter(hourlyAdapter);

        //填充空气质量
        if (weatherBean.getEvn() != null) {
            ll_aqi.setVisibility(View.VISIBLE);
            int aqi = weatherBean.getEvn().getAqi();

            //判断首要污染物
            int PM25 = weatherBean.getEvn().getPm25();
            int PM10 = weatherBean.getEvn().getPm10();
            int NO2 = weatherBean.getEvn().getNo2();
            int SO2 = weatherBean.getEvn().getSo2();
            int CO = weatherBean.getEvn().getCo();
            int O3 = weatherBean.getEvn().getO3();

            Map<Integer, String> map = new HashMap<>();
            map.put(PM25, "PM2.5");
            map.put(PM10, "PM10");
            map.put(NO2, "NO2");
            map.put(SO2, "SO2");
            map.put(CO, "CO");
            map.put(O3, "O3");
            int[] numbers = {PM25, PM10, NO2, SO2, CO, O3};
            int temp;
            for (int i = 0; i < numbers.length - 1; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    if (numbers[i] < numbers[j]) {
                        temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                    }
                }
            }

            int mp = numbers[0];

            progressBar_aqi.setmProgress(aqi);
            progressBar_mp.setmProgress(mp);

            progressBar_aqi.setmTxtName("AQI");
            progressBar_mp.setmTxtName(map.get(mp));
            progressBar_aqi.setmTxtTitle("空气质量指数");
            progressBar_mp.setmTxtTitle("首要污染物");


            tv_aqi_qlty_desc.setText(weatherBean.getEvn().getQuality());
            tv_aqi_time.setText(weatherBean.getEvn().getTime() + "发布");
            tv_aqi_pm25.setText(PM25 + "");
            tv_aqi_pm10.setText(PM10 + "");
            tv_aqi_no2.setText(NO2 + "");
            tv_aqi_so2.setText(SO2 + "");
            tv_aqi_co.setText(CO + "");
            tv_aqi_o3.setText(O3 + "");

            tv_aqi_suggest.setText("TIPS：" + weatherBean.getEvn().getSuggest());
        } else {
            ll_aqi.setVisibility(View.GONE);
        }

        //Index指数
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview_index.setLayoutManager(layoutManager);
        recyclerview_index.setHasFixedSize(true);
        indices = weatherBean.getIndices();
        IndexAdapter indexAdapter = new IndexAdapter(this, indices);
        recyclerview_index.setAdapter(indexAdapter);
        recyclerview_index.setNestedScrollingEnabled(false);//禁止recyclerview滚动

        Snackbar.make(nestedScrollView, "您选择的城市为：" + preferences.getString("location", "") + "，更新时间为：" + weatherBean.getMeta().getUp_time() + "。", Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    public void select(View view) {

        showPopupWindow();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        swipeRefreshLayout.setAlpha(0.45f);
    }

    private void showPopupWindow() {


        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_select, null);
        mPopupWindow = new PopupWindow(contentView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
        View rootView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        mPopupWindow.showAtLocation(rootView, Gravity.TOP, 0, 0);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                swipeRefreshLayout.setAlpha(1.0f);
            }
        });
        provinceSpinner = (Spinner) contentView.findViewById(R.id.spinner_province);
        citySpinner = (Spinner) contentView.findViewById(R.id.spinner_city);
        countySpinner = (Spinner) contentView.findViewById(R.id.spinner_county);

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
                cityAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.custom_spinner_item, cityBeens);
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
                countyAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.custom_spinner_item, countyBeens);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void enter_main(View view) {

        SharedPreferences preferences = getSharedPreferences("com.qingxu.imweather.sharedpreferences", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("cityID", countyBean.getCode());
        editor.putString("location", countyBean.getName());

        editor.apply();

        mPopupWindow.dismiss();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Refresh();//弹窗关闭后刷新

    }

}
