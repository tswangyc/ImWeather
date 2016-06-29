package com.qingxu.imweather.util;

import com.qingxu.imweather.bean.CityBean;
import com.qingxu.imweather.bean.CountyBean;
import com.qingxu.imweather.bean.Day;
import com.qingxu.imweather.bean.Evn;
import com.qingxu.imweather.bean.Forecast;
import com.qingxu.imweather.bean.Hourfc;
import com.qingxu.imweather.bean.Index;
import com.qingxu.imweather.bean.Meta;
import com.qingxu.imweather.bean.Night;
import com.qingxu.imweather.bean.Observe;
import com.qingxu.imweather.bean.ProvinceBean;
import com.qingxu.imweather.bean.WeatherBean;
import com.qingxu.imweather.bean.Xianhao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by QingXu on 2016/5/30.
 */
public class JsonParseUtil {

    public static WeatherBean parseWeather(String s) throws JSONException {
        WeatherBean weatherBean = new WeatherBean();
        JSONObject mainObject = new JSONObject(s);
        /**
         * Observe
         */
        JSONObject observeObject = mainObject.optJSONObject("observe");
        Observe observe = new Observe();
        observe.setShidu(observeObject.optString("shidu"));
        observe.setWp(observeObject.optString("wp"));
        observe.setTemp(observeObject.optInt("temp"));
        observe.setWd(observeObject.optString("wd"));
        observe.setTigan(observeObject.optString("tigan"));
        weatherBean.setObserve(observe);

        /**
         * Evn
         */
        JSONObject evnObject = mainObject.optJSONObject("evn");
        if (evnObject == null) {
            weatherBean.setXianhao(null);
        } else {
            Evn evn = new Evn();
            evn.setTime(evnObject.optString("time"));
            evn.setCo(evnObject.optInt("co"));
            evn.setMp(evnObject.optString("mp"));
            evn.setSo2(evnObject.optInt("so2"));
            evn.setO3(evnObject.optInt("o3"));
            evn.setNo2(evnObject.optInt("no2"));
            evn.setQuality(evnObject.optString("quality"));
            evn.setAqi(evnObject.optInt("aqi"));
            evn.setPm10(evnObject.optInt("pm10"));
            evn.setSuggest(evnObject.optString("suggest"));
            evn.setPm25(evnObject.optInt("pm25"));
            weatherBean.setEvn(evn);
        }


        /**
         * Forecast
         */
        JSONArray forecastArray = mainObject.optJSONArray("forecast15");
        ArrayList<Forecast> forecasts = new ArrayList<>();
        for (int i = 0; i < forecastArray.length(); i++) {
            JSONObject forecastObject = forecastArray.optJSONObject(i);
            Forecast forecast = new Forecast();
            forecast.setSunset(forecastObject.optString("sunset"));
            forecast.setSunrise(forecastObject.optString("sunrise"));
            forecast.setDate(forecastObject.optString("date"));
            forecast.setHigh(forecastObject.optInt("high"));
            forecast.setLow(forecastObject.optInt("low"));

            JSONObject dayObject = forecastObject.optJSONObject("day");
            Day day = new Day();
            day.setWthr(dayObject.optString("wthr"));
            day.setBgPic(dayObject.optString("bgPic"));
            day.setWd(dayObject.optString("wd"));
            day.setWp(dayObject.optString("wp"));
            day.setType(dayObject.optInt("type"));
            day.setNotice(dayObject.optString("notice"));
            forecast.setDay(day);

            JSONObject nightObject = forecastObject.optJSONObject("night");
            Night night = new Night();
            night.setWthr(nightObject.optString("wthr"));
            night.setBgPic(nightObject.optString("bgPic"));
            night.setWd(nightObject.optString("wd"));
            night.setWp(nightObject.optString("wp"));
            night.setType(nightObject.optInt("type"));
            night.setNotice(nightObject.optString("notice"));
            forecast.setNight(night);

            forecasts.add(forecast);
        }
        weatherBean.setForecast(forecasts);

        /**
         * Hourfc
         */
        JSONArray hourfcArray = mainObject.optJSONArray("hourfc");
        ArrayList<Hourfc> hourfcs = new ArrayList<>();
        for (int i = 0; i < hourfcArray.length(); i++) {
            JSONObject hourfcObject = hourfcArray.optJSONObject(i);
            Hourfc hourfc = new Hourfc();
            hourfc.setTime(hourfcObject.optString("time"));
            hourfc.setWthr(hourfcObject.optInt("wthr"));
            hourfc.setShidu(hourfcObject.optString("shidu"));
            hourfc.setWp(hourfcObject.optString("wp"));
            hourfc.setWd(hourfcObject.optString("wd"));
            hourfc.setType(hourfcObject.optInt("type"));
            hourfcs.add(hourfc);
        }
        weatherBean.setHourfc(hourfcs);

        /**
         * Xianhao
         */
        JSONArray xianhaoArray = mainObject.optJSONArray("xianhao");
        if (xianhaoArray == null) {
            weatherBean.setXianhao(null);
        } else {
            ArrayList<Xianhao> xianhaos = new ArrayList<>();
            for (int i = 0; i < xianhaoArray.length(); i++) {
                JSONObject xianhaoObject = xianhaoArray.optJSONObject(i);
                Xianhao xianhao = new Xianhao();
                xianhao.setTitle(xianhaoObject.optString("title"));
                xianhao.setF_date(xianhaoObject.optString("f_date"));
                xianhao.setShare_link(xianhaoObject.optString("share_link"));
                xianhao.setAction_type(xianhaoObject.optString("action_type"));
                xianhao.setItem_id(xianhaoObject.optString("item_id"));
                xianhao.setF_number(xianhaoObject.optString("f_number"));
                xianhao.setPost_id(xianhaoObject.optString("post_id"));
                xianhaos.add(xianhao);
            }
            weatherBean.setXianhao(xianhaos);
        }


        /**
         * Index
         */
        JSONArray indexArray = mainObject.optJSONArray("indexes");
        ArrayList<Index> indices = new ArrayList<>();
        for (int i = 0; i < indexArray.length(); i++) {
            JSONObject indexObject = indexArray.optJSONObject(i);
            Index index = new Index();
            index.setDesc(indexObject.optString("desc"));
            index.setName(indexObject.optString("name"));
            index.setValue(indexObject.optString("value"));
            indices.add(index);
        }
        weatherBean.setIndices(indices);

        JSONObject metaObject = mainObject.optJSONObject("meta");
        Meta meta = new Meta();
        meta.setHtml_url(metaObject.optString("html_url"));
        meta.setStatus(metaObject.optInt("status"));
        meta.setPost_count(metaObject.optInt("post_count"));
        meta.setCitykey(metaObject.optString("citykey"));
        meta.setUp_time(metaObject.optString("up_time"));
        meta.setPost_id(metaObject.optString("post_id"));
        meta.setCity(metaObject.optString("city"));
        weatherBean.setMeta(meta);

        return weatherBean;
    }


    public static ArrayList<ProvinceBean> parseCity(String s) throws JSONException {
        JSONObject mainObject = new JSONObject(s);
        JSONObject country = mainObject.optJSONObject("China");
        JSONArray provinces = country.optJSONArray("province");
        ArrayList<ProvinceBean> provinceBeens = new ArrayList<>();


        for (int i = 0; i < provinces.length(); i++) {
            ProvinceBean provinceBean = new ProvinceBean();
            JSONObject province = provinces.optJSONObject(i);
            String province_id = province.optString("-id");
            String province_name = province.optString("-name");

            JSONArray citys = province.optJSONArray("city");
            ArrayList<CityBean> cityBeens = new ArrayList<>();
            for (int j = 0; j < citys.length(); j++) {
                CityBean cityBeen = new CityBean();
                JSONObject city = citys.optJSONObject(j);
                String city_id = city.optString("-id");
                String city_name = city.optString("-name");
                JSONArray countys = city.optJSONArray("county");

                ArrayList<CountyBean> countyBeens = new ArrayList<>();
                for (int k = 0; k < countys.length(); k++) {
                    CountyBean countyBean = new CountyBean();
                    JSONObject county = countys.optJSONObject(k);
                    String county_id = county.optString("-id");
                    String county_name = county.optString("-name");
                    String county_code = county.optString("-weatherCode");

                    countyBean.setId(county_id);
                    countyBean.setName(county_name);
                    countyBean.setCode(county_code);
                    countyBeens.add(countyBean);
                }
                cityBeen.setId(city_id);
                cityBeen.setName(city_name);
                cityBeen.setCountyBeens(countyBeens);
                cityBeens.add(cityBeen);
            }
            provinceBean.setId(province_id);
            provinceBean.setName(province_name);
            provinceBean.setCityBeens(cityBeens);
            provinceBeens.add(provinceBean);
        }

        return provinceBeens;
    }


//    public static String parseRealtimeLocation(String s) throws JSONException {
//        String realtimeLocation = null;
//        JSONObject mainObject = new JSONObject(s);
//        String status = mainObject.optString("status");
//        if (status.equals("0")) {
//            JSONObject result = mainObject.optJSONObject("result");
//            JSONObject addressComponent = result.optJSONObject("addressComponent");
////            String province = addressComponent.optString("province");
//            realtimeLocation = addressComponent.optString("district");
//
//        }
//
//        return realtimeLocation;
//    }

//    public static RealtimeBean parseRealtimeBean(String s) throws JSONException {
//        RealtimeBean realtimeBean = new RealtimeBean();
//        JSONObject mainObject = new JSONObject(s);
//        String status = mainObject.optString("status");
//        if (status.equals("ok")) {
//            int tzshift = mainObject.optInt("tzshift");
//            realtimeBean.setTzshift(tzshift);
//
//            long server_time = mainObject.optLong("server_time");
//            realtimeBean.setServer_time(server_time);
//
//            JSONArray location = mainObject.optJSONArray("location");
//            double latitude = location.optDouble(0);
//            realtimeBean.setLatitude(latitude);
//
//            double longitude = location.optDouble(1);
//            realtimeBean.setLongitude(longitude);
//
//            JSONObject result = mainObject.optJSONObject("result");
//            String result_status = result.optString("status");
//            if (result_status.equals("ok")) {
//                int temperature = result.optInt("temperature");
//                realtimeBean.setTemperature(temperature);
//
//                double humidity = result.optDouble("humidity");
//                realtimeBean.setHumidity(humidity);
//
//                JSONObject wind = result.optJSONObject("wind");
//                double speed = wind.optDouble("speed");
//                realtimeBean.setSpeed(speed);
//
//                double direction = wind.optDouble("direction");
//                realtimeBean.setDirection(direction);
//
//                double cloudrate = result.optDouble("cloudrate");
//                realtimeBean.setCloudrate(cloudrate);
//
//                String skycon = result.optString("skycon");
//                realtimeBean.setSkycon(skycon);
//
//                int pm25 = result.optInt("pm25");
//                realtimeBean.setPm25(pm25);
//
//                JSONObject precipitation = result.optJSONObject("precipitation");
//                JSONObject local = precipitation.optJSONObject("local");
//                String local_status = local.optString("status");
//                if (local_status.equals("ok")) {
//                    double intensity = local.optDouble("intensity");
//                    realtimeBean.setIntensity(intensity);
//                }
//
//            }
//
//
//        }
//        return realtimeBean;
//    }

//
//    public static DailyForecastBean parseDailyForecastBean(String s) throws JSONException {
//
//        DailyForecastBean dailyForecastBean = new DailyForecastBean();
//        JSONObject mainObject = new JSONObject(s);
//        String status = mainObject.optString("status");
//        if (status.equals("ok")) {
//            JSONObject result = mainObject.optJSONObject("result");
//            JSONObject daily = result.optJSONObject("daily");
//            String status_daily = daily.optString("status");
//            if (status_daily.equals("ok")) {
//                JSONArray astro_daily = daily.optJSONArray("astro");
//                ArrayList<String> sunset_dailyList = new ArrayList<>();
//                ArrayList<String> sunrise_dailyList = new ArrayList<>();
//                ArrayList<String> date_dailyList = new ArrayList<>();
//                for (int i = 0; i < astro_daily.length(); i++) {
//
//                    String date_daily = astro_daily.optJSONObject(i).optString("date");
//                    String sunrise_daily = astro_daily.optJSONObject(i).optJSONObject("sunrise").optString("time");
//                    String sunset_daily = astro_daily.optJSONObject(i).optJSONObject("sunset").optString("time");
//
//                    date_dailyList.add(date_daily);
//                    sunrise_dailyList.add(sunrise_daily);
//                    sunset_dailyList.add(sunset_daily);
//
//                }
//                dailyForecastBean.setDate(date_dailyList);
//                dailyForecastBean.setSunrise_time(sunrise_dailyList);
//                dailyForecastBean.setSunset_time(sunset_dailyList);
//
//                JSONArray skycon_daily = daily.optJSONArray("skycon");
//                ArrayList<String> skycon_value_dailyList = new ArrayList<>();
//                for (int i = 0; i < skycon_daily.length(); i++) {
//                    String skycon_value_daily = skycon_daily.optJSONObject(i).optString("value");
//                    skycon_value_dailyList.add(skycon_value_daily);
//                }
//                dailyForecastBean.setSkycon(skycon_value_dailyList);
//
//                JSONArray precipitation_daily = daily.optJSONArray("precipitation");
//                ArrayList<Double> precipitation_avg_dailyList = new ArrayList<>();
//                for (int i = 0; i < precipitation_daily.length(); i++) {
//                    double precipitation_avg_daily = precipitation_daily.optJSONObject(i).optDouble("avg");
//                    precipitation_avg_dailyList.add(precipitation_avg_daily);
//                }
//                dailyForecastBean.setPrecipitation(precipitation_avg_dailyList);
//
//                JSONArray temperature_daily = daily.optJSONArray("temperature");
//                ArrayList<Integer> temperature_max_dailyList = new ArrayList<>();
//                ArrayList<Integer> temperature_min_dailyList = new ArrayList<>();
//                for (int i = 0; i < temperature_daily.length(); i++) {
//                    int temperature_max_daily = temperature_daily.optJSONObject(i).optInt("max");
//                    int temperature_min_daily = temperature_daily.optJSONObject(i).optInt("min");
//
//                    temperature_max_dailyList.add(temperature_max_daily);
//                    temperature_min_dailyList.add(temperature_min_daily);
//                }
//                dailyForecastBean.setTmp_max(temperature_max_dailyList);
//                dailyForecastBean.setTmp_min(temperature_min_dailyList);
//
//                JSONArray wind_daily = daily.optJSONArray("wind");
//                ArrayList<Double> wind_speed_dailyList = new ArrayList<>();
//                ArrayList<Double> wind_direction_dailyList = new ArrayList<>();
//                for (int i = 0; i < wind_daily.length(); i++) {
//                    double wind_speed_daily = wind_daily.optJSONObject(i).optJSONObject("avg").optDouble("speed");
//                    double wind_direction_daily = wind_daily.optJSONObject(i).optJSONObject("avg").optDouble("direction");
//
//                    wind_speed_dailyList.add(wind_speed_daily);
//                    wind_direction_dailyList.add(wind_direction_daily);
//                }
//                dailyForecastBean.setSpeed(wind_speed_dailyList);
//                dailyForecastBean.setDirection(wind_direction_dailyList);
//
//                JSONArray humidity_daily = daily.optJSONArray("humidity");
//                ArrayList<Double> humidity_avg_dailyList = new ArrayList<>();
//                for (int i = 0; i < humidity_daily.length(); i++) {
//                    double humidity_avg_daily = humidity_daily.optJSONObject(i).optDouble("avg");
//                    humidity_avg_dailyList.add(humidity_avg_daily);
//                }
//                dailyForecastBean.setHumidity(humidity_avg_dailyList);
//
//                JSONArray cloudrate_daily = daily.optJSONArray("cloudrate");
//                ArrayList<Double> cloudrate_avg_dailyList = new ArrayList<>();
//                for (int i = 0; i < cloudrate_daily.length(); i++) {
//                    double cloudrate_avg_daily = cloudrate_daily.optJSONObject(i).optDouble("avg");
//                    cloudrate_avg_dailyList.add(cloudrate_avg_daily);
//                }
//                dailyForecastBean.setCloudrate(cloudrate_avg_dailyList);
//
//                JSONArray aqi_daily = daily.optJSONArray("aqi");
//                ArrayList<Integer> aqi_avg_dailyList = new ArrayList<>();
//                for (int i = 0; i < aqi_daily.length(); i++) {
//                    int aqi_avg_daily = aqi_daily.optJSONObject(i).optInt("avg");
//                    aqi_avg_dailyList.add(aqi_avg_daily);
//                }
//                dailyForecastBean.setAqi(aqi_avg_dailyList);
//
//                JSONArray pm25_daily = daily.optJSONArray("pm25");
//                ArrayList<Integer> pm25_avg_dailyList = new ArrayList<>();
//                for (int i = 0; i < pm25_daily.length(); i++) {
//                    int pm25_avg_daily = pm25_daily.optJSONObject(i).optInt("avg");
//                    pm25_avg_dailyList.add(pm25_avg_daily);
//                }
//                dailyForecastBean.setPm25(pm25_avg_dailyList);
//
//                JSONArray ultraviolet_daily = daily.optJSONArray("ultraviolet");
//                ArrayList<Integer> ultraviolet_index_dailyList = new ArrayList<>();
//                ArrayList<String> ultraviolet_desc_dailyList = new ArrayList<>();
//                for (int i = 0; i < ultraviolet_daily.length(); i++) {
//                    int ultraviolet_index_daily = ultraviolet_daily.optJSONObject(i).optInt("index");
//                    String ultraviolet_desc_daily = ultraviolet_daily.optJSONObject(i).optString("desc");
//                    ultraviolet_index_dailyList.add(ultraviolet_index_daily);
//
//                    ultraviolet_desc_dailyList.add(ultraviolet_desc_daily);
//                }
//                dailyForecastBean.setUltraviolet_index(ultraviolet_index_dailyList);
//                dailyForecastBean.setUltraviolet_desc(ultraviolet_desc_dailyList);
//
//                JSONArray carWashing_daily = daily.optJSONArray("carWashing");
//                ArrayList<Integer> carWashing_index_dailyList = new ArrayList<>();
//                ArrayList<String> carWashing_desc_dailyList = new ArrayList<>();
//                for (int i = 0; i < carWashing_daily.length(); i++) {
//                    int carWashing_index_daily = carWashing_daily.optJSONObject(i).optInt("index");
//                    String carWashing_desc_daily = carWashing_daily.optJSONObject(i).optString("desc");
//                    carWashing_index_dailyList.add(carWashing_index_daily);
//
//                    carWashing_desc_dailyList.add(carWashing_desc_daily);
//                }
//                dailyForecastBean.setCarWashing_index(carWashing_index_dailyList);
//                dailyForecastBean.setCarWashing_desc(carWashing_desc_dailyList);
//
//                JSONArray dressing_daily = daily.optJSONArray("dressing");
//                ArrayList<Integer> dressing_index_dailyList = new ArrayList<>();
//                ArrayList<String> dressing_desc_dailyList = new ArrayList<>();
//                for (int i = 0; i < dressing_daily.length(); i++) {
//                    int dressing_index_daily = dressing_daily.optJSONObject(i).optInt("index");
//                    String dressing_desc_daily = dressing_daily.optJSONObject(i).optString("desc");
//                    dressing_index_dailyList.add(dressing_index_daily);
//
//                    dressing_desc_dailyList.add(dressing_desc_daily);
//                }
//                dailyForecastBean.setCarWashing_index(dressing_index_dailyList);
//                dailyForecastBean.setCarWashing_desc(dressing_desc_dailyList);
//
//                JSONArray coldRisk_daily = daily.optJSONArray("coldRisk");
//                ArrayList<Integer> coldRisk_index_dailyList = new ArrayList<>();
//                ArrayList<String> coldRisk_desc_dailyList = new ArrayList<>();
//                for (int i = 0; i < coldRisk_daily.length(); i++) {
//                    int coldRisk_index_daily = coldRisk_daily.optJSONObject(i).optInt("index");
//                    String coldRisk_desc_daily = coldRisk_daily.optJSONObject(i).optString("desc");
//                    coldRisk_index_dailyList.add(coldRisk_index_daily);
//
//                    coldRisk_desc_dailyList.add(coldRisk_desc_daily);
//                }
//                dailyForecastBean.setCarWashing_index(coldRisk_index_dailyList);
//                dailyForecastBean.setCarWashing_desc(coldRisk_desc_dailyList);
//
//            }
//
//        }
//
//
//        return dailyForecastBean;
//    }

//    public static WeatherBean parseBean(String jsonResult) throws JSONException {
//
//        WeatherBean weatherBean = new WeatherBean();
//
//        JSONObject mainObject = new JSONObject(jsonResult);
//        String jsonTitle = mainObject.optString("HeWeather data service 3.0");
//
//        JSONArray jsonObjs;
//        jsonObjs = new JSONArray(jsonTitle);
//
//
//        for (int i = 0; i < jsonObjs.length(); i++) {
//            JSONObject jsonObj = jsonObjs.optJSONObject(i);
//
//            /**
//             * 1.解析并填充aqi
//             */
//            JSONObject jsonAqi = jsonObj.optJSONObject("aqi");
//            JSONObject jsonCity = jsonAqi.optJSONObject("city");
//
//            String aqi_aqi = jsonCity.optString("aqi");
//            String co_aqi = jsonCity.optString("co");
//            String no2_aqi = jsonCity.optString("no2");
//            String o3_aqi = jsonCity.optString("o3");
//            String pm10_aqi = jsonCity.optString("pm10");
//            String pm25_aqi = jsonCity.optString("pm25");
//            String qlty_aqi = jsonCity.optString("qlty");
//            String so2_aqi = jsonCity.optString("so2");
//
//            AqiBean aqiBean = new AqiBean();
//            aqiBean.setAqi(aqi_aqi);
//            aqiBean.setCo(co_aqi);
//            aqiBean.setNo2(no2_aqi);
//            aqiBean.setO3(o3_aqi);
//            aqiBean.setPm10(pm10_aqi);
//            aqiBean.setPm25(pm25_aqi);
//            aqiBean.setQlty(qlty_aqi);
//            aqiBean.setSo2(so2_aqi);
//            weatherBean.setAqiBean(aqiBean);
//
//
//            /**
//             * 2.解析并填充basic
//             */
//
//            BasicBean basicBean = new BasicBean();
//
//            JSONObject jsonBasic = jsonObj.optJSONObject("basic");//基本信息
//            String city_basic = jsonBasic.optString("city");//城市名称
//            String cnty_basic = jsonBasic.optString("cnty");//国家
//            String id_basic = jsonBasic.optString("id");//城市ID
//            String lat_basic = jsonBasic.optString("lat");//城市维度
//            String lon_basic = jsonBasic.optString("lon");//城市经度
//            JSONObject jsonUpdate = jsonBasic.optJSONObject("update");//更新时间
//            String loc_basic = jsonUpdate.optString("loc");//当地时间
//            String utc_basic = jsonUpdate.optString("utc");//UTC时间
//
//            basicBean.setCity(city_basic);
//            basicBean.setCnty(cnty_basic);
//            basicBean.setId(id_basic);
//            basicBean.setLat(lat_basic);
//            basicBean.setLon(lon_basic);
//            basicBean.setLoc(loc_basic);
//            basicBean.setUtc(utc_basic);
//
//            weatherBean.setBasicBean(basicBean);
//
//
//            /**
//             * 3.解析并填充now
//             */
//            JSONObject jsonNow = jsonObj.optJSONObject("now");//实况天气
//            JSONObject jsonCond = jsonNow.optJSONObject("cond");//天气状况
//            String code_now = jsonCond.optString("code");//天气状况代码
//            String txt_now = jsonCond.optString("txt");//天气状况描述
//            String fl_now = jsonNow.optString("fl");//体感温度
//            String hum_now = jsonNow.optString("hum") + "%";//相对湿度
//            String pcpn_now = jsonNow.optString("pcpn");//降水量
//            String pres_now = jsonNow.optString("pres");//气压
//            String tmp_now = jsonNow.optString("tmp");//温度
//            String vis_now = jsonNow.optString("vis");//能见度
//            JSONObject jsonWind = jsonNow.optJSONObject("wind");//风力风向
//            String deg_now = jsonWind.optString("deg");//风向（360度）
//            String dir_now = jsonWind.optString("dir");//风向
//            String sc_now = jsonWind.optString("sc") + "级";//风力
//            String spd_now = jsonWind.optString("spd");//风速（kmph）
//
//            NowBean nowBean = new NowBean();
//            nowBean.setCode(code_now);
//            nowBean.setTxt(txt_now);
//            nowBean.setFl(fl_now);
//            nowBean.setHum(hum_now);
//            nowBean.setPcpn(pcpn_now);
//            nowBean.setPres(pres_now);
//            nowBean.setTmp(tmp_now);
//            nowBean.setVis(vis_now);
//            nowBean.setDeg(deg_now);
//            nowBean.setDir(dir_now);
//            nowBean.setSc(sc_now);
//            nowBean.setSpd(spd_now);
//
//            weatherBean.setNowBean(nowBean);
//
//            /**
//             * 4.解析并填充daily
//             */
//            ArrayList<DailyBean> dailyBeens = new ArrayList<DailyBean>();
//            JSONArray jsonDailyForecast = jsonObj.optJSONArray("daily_forecast");
//
//            for (int j = 0; j < jsonDailyForecast.length(); j++) {
//                JSONObject jsonDaily = jsonDailyForecast.optJSONObject(j);
//
//
//                JSONObject jsonDailyAstro = jsonDaily.optJSONObject("astro");//天文数值
//                String sr_daily = jsonDailyAstro.optString("sr");//日出时间
//                String ss_daily = jsonDailyAstro.optString("ss");//日落时间
//
//                JSONObject jsonDailyCond = jsonDaily.optJSONObject("cond");//天气状况
//                String code_d_daily = jsonDailyCond.optString("code_d");//白天天气状况代码
//                String code_n_daily = jsonDailyCond.optString("code_n");//夜间天气状况代码
//                String txt_d_daily = jsonDailyCond.optString("txt_d");//白天天气状况描述
//                String txt_n_daily = jsonDailyCond.optString("txt_n");//夜间天气状况描述
//
//                String date_daily = jsonDaily.optString("date");//预报日期
//                String hum_daily = jsonDaily.optString("hum");//相对湿度（%）
//                String pcpn_daily = jsonDaily.optString("pcpn");//降水量（mm）
//                String pop_daily = jsonDaily.optString("pop");//降水概率
//                String pres_daily = jsonDaily.optString("pres");//气压
//
//                JSONObject jsonDailyTmp = jsonDaily.optJSONObject("tmp");//温度
//                String max_daily = jsonDailyTmp.optString("max"); //最高温度
//                String min_daily = jsonDailyTmp.optString("min"); //最低温度
//                String vis_daily = jsonDaily.optString("vis");//能见度（km）
//
//                JSONObject jsonDailyWind = jsonDaily.optJSONObject("wind"); //风力风向
//                String deg_daily = jsonDailyWind.optString("deg");//风向（360度）
//                String dir_daily = jsonDailyWind.optString("dir"); //风向
//                String sc_daily = jsonDailyWind.optString("sc");//风力
//                String spd_daily = jsonDailyWind.optString("spd"); //风速（kmph）
//
//                DailyBean dailyBean = new DailyBean();
//
//                dailyBean.setSr(sr_daily);
//                dailyBean.setSs(ss_daily);
//                dailyBean.setCode_d(code_d_daily);
//                dailyBean.setCode_n(code_n_daily);
//                dailyBean.setTxt_d(txt_d_daily);
//                dailyBean.setTxt_n(txt_n_daily);
//                dailyBean.setDate(date_daily);
//                dailyBean.setHum(hum_daily);
//                dailyBean.setPcpn(pcpn_daily);
//                dailyBean.setPop(pop_daily);
//                dailyBean.setPres(pres_daily);
//                dailyBean.setMax(max_daily);
//                dailyBean.setMin(min_daily);
//                dailyBean.setVis(vis_daily);
//                dailyBean.setDeg(deg_daily);
//                dailyBean.setDir(dir_daily);
//                dailyBean.setSc(sc_daily);
//                dailyBean.setSpd(spd_daily);
//
//                dailyBeens.add(dailyBean);
//            }
//            weatherBean.setDailyBeans(dailyBeens);
//
//            /**
//             *5.解析并填充hourly
//             */
//            ArrayList<HourlyBean> hourlyBeens = new ArrayList<HourlyBean>();
//            JSONArray jsonHourlyFourcast = jsonObj.optJSONArray("hourly_forecast");
//
//            for (int j = 0; j < jsonHourlyFourcast.length(); j++) {
//                JSONObject jsonHourly = jsonHourlyFourcast.optJSONObject(j);
//                String date_hourly = jsonHourly.optString("date");
//                String hum_hourly = jsonHourly.optString("hum");
//                String pop_hourly = jsonHourly.optString("pop");
//                String pres_hourly = jsonHourly.optString("pres");
//                String tmp_hourly = jsonHourly.optString("tmp");
//                JSONObject jsonHoulyWind = jsonHourly.optJSONObject("wind");
//                String deg_hourly = jsonHoulyWind.optString("deg");
//                String dir_hourly = jsonHoulyWind.optString("dir");
//                String sc_hourly = jsonHoulyWind.optString("sc");
//                String spd_hourly = jsonHoulyWind.optString("spd");
//
//                HourlyBean hourlyBean = new HourlyBean();
//
//                hourlyBean.setDate(date_hourly);
//                hourlyBean.setHum(hum_hourly);
//                hourlyBean.setPop(pop_hourly);
//                hourlyBean.setPres(pres_hourly);
//                hourlyBean.setTmp(tmp_hourly);
//                hourlyBean.setDeg(deg_hourly);
//                hourlyBean.setDir(dir_hourly);
//                hourlyBean.setSc(sc_hourly);
//                hourlyBean.setSpd(spd_hourly);
//
//                hourlyBeens.add(hourlyBean);
//            }
//            weatherBean.setHourlyBeans(hourlyBeens);
//
//
//            //6.解析并填充status
//            String status = jsonObj.optString("status");
//            StatusBean statusBean = new StatusBean(status);
//            statusBean.setStatus(status);
//            weatherBean.setStatusBean(statusBean);
//            //7.解析并填充suggestion
//
//
//            JSONObject jsonSuggestion = jsonObj.optJSONObject("suggestion");
//            JSONObject jsonComf = jsonSuggestion.optJSONObject("comf");
//            String brf_comf = jsonComf.optString("brf");
//            String txt_comf = jsonComf.optString("txt");
//            JSONObject jsonCw = jsonSuggestion.optJSONObject("cw");
//            String brf_cw = jsonCw.optString("brf");
//            String txt_cw = jsonCw.optString("txt");
//            JSONObject jsonDrsg = jsonSuggestion.optJSONObject("drsg");
//            String brf_drsg = jsonDrsg.optString("brf");
//            String txt_drsg = jsonDrsg.optString("txt");
//            JSONObject jsonFlu = jsonSuggestion.optJSONObject("flu");
//            String brf_flu = jsonFlu.optString("brf");
//            String txt_flu = jsonFlu.optString("txt");
//            JSONObject jsonSport = jsonSuggestion.optJSONObject("sport");
//            String brf_sport = jsonSport.optString("brf");
//            String txt_sport = jsonSport.optString("txt");
//            JSONObject jsonTrav = jsonSuggestion.optJSONObject("trav");
//            String brf_trav = jsonTrav.optString("brf");
//            String txt_trav = jsonTrav.optString("txt");
//            JSONObject jsonUv = jsonSuggestion.optJSONObject("uv");
//            String brf_uv = jsonUv.optString("brf");
//            String txt_uv = jsonUv.optString("txt");
//
//            SuggestionBean suggestionBean = new SuggestionBean();
//            suggestionBean.setComf_brf(brf_comf);
//            suggestionBean.setComf_txt(txt_comf);
//            suggestionBean.setCw_brf(brf_cw);
//            suggestionBean.setCw_txt(txt_cw);
//            suggestionBean.setDrsg_brf(brf_drsg);
//            suggestionBean.setDrsg_txt(txt_drsg);
//            suggestionBean.setFlu_brf(brf_flu);
//            suggestionBean.setFlu_txt(txt_flu);
//            suggestionBean.setSport_brf(brf_sport);
//            suggestionBean.setSport_txt(txt_sport);
//            suggestionBean.setTrav_brf(brf_trav);
//            suggestionBean.setTrav_txt(txt_trav);
//            suggestionBean.setUv_brf(brf_uv);
//            suggestionBean.setUv_txt(txt_uv);
//
//            weatherBean.setSuggestionBean(suggestionBean);
//        }
//
//        return weatherBean;
//    }


}
