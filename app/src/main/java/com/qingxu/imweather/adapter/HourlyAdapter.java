package com.qingxu.imweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qingxu.imweather.R;
import com.qingxu.imweather.bean.Hourfc;
import com.qingxu.imweather.view.HourlyViewHolder;

import java.util.ArrayList;

/**
 * Created by QingXu on 2016/6/1.
 */
public class HourlyAdapter extends RecyclerView.Adapter<HourlyViewHolder> {

    private Context context;
    private ArrayList<Hourfc> hourfcs;

    public HourlyAdapter(Context context, ArrayList<Hourfc> hourfcs) {
        this.context = context;
        this.hourfcs = hourfcs;
    }

    @Override
    public HourlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HourlyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_hourly, parent, false));
    }

    @Override
    public void onBindViewHolder(HourlyViewHolder holder, int position) {

        String time = hourfcs.get(position).getTime().substring(8, 10) + ":" + hourfcs.get(position).getTime().substring(10, 12);
        holder.getHourly_item_time().setText(time);
        holder.getHourly_item_temp().setText(hourfcs.get(position).getWthr() + "°");
        holder.getHourly_item_hum().setText(hourfcs.get(position).getShidu());
//        holder.getHourly_item_wd().setText(hourfcs.get(position).getWd());
//        holder.getHourly_item_wp().setText(hourfcs.get(position).getWp());
    }

    @Override
    public int getItemCount() {
        return hourfcs.size();
    }
}
