package com.qingxu.imweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qingxu.imweather.R;
import com.qingxu.imweather.app.MyApplication;
import com.qingxu.imweather.bean.Forecast;
import com.qingxu.imweather.view.DailyViewHolder;

import java.util.ArrayList;

/**
 * Created by QingXu on 2016/6/1.
 */
public class DailyAdapter extends RecyclerView.Adapter<DailyViewHolder> {

    private Context context;
    private ArrayList<Forecast> forecasts;

    public DailyAdapter(Context context, ArrayList<Forecast> forecasts) {
        this.context = context;
        this.forecasts = forecasts;
    }

    @Override
    public DailyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DailyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_daily, parent, false));
    }

    @Override
    public void onBindViewHolder(DailyViewHolder holder, int position) {
        String date = forecasts.get(position).getDate().substring(4, 6) + "月" + forecasts.get(position).getDate().substring(6, 8) + "日";
        holder.getTv_daily_today().setText(date);
        holder.getIv_daily_today_cond_code_d().setImageResource(((MyApplication) context.getApplicationContext()).getIconMap().get(forecasts.get(position).getDay().getWthr()));
        holder.getTv_daily_today_cond_txt_d().setText(forecasts.get(position).getDay().getWthr());
        holder.getTv_daily_today_tmp_max().setText(forecasts.get(position).getHigh() + "°");
        holder.getTv_daily_today_tmp_min().setText(forecasts.get(position).getLow() + "°");
        holder.getTv_daily_today_wind_dir().setText(forecasts.get(position).getDay().getWd());
        holder.getTv_daily_today_wind_sc().setText(forecasts.get(position).getDay().getWp());
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

}
