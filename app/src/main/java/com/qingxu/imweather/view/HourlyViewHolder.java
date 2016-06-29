package com.qingxu.imweather.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qingxu.imweather.R;

/**
 * Created by QingXu on 2016/6/1.
 */
public class HourlyViewHolder extends RecyclerView.ViewHolder {

    private TextView hourly_item_time, hourly_item_temp, hourly_item_hum, hourly_item_wd, hourly_item_wp;

    public HourlyViewHolder(View itemView) {
        super(itemView);

        hourly_item_time = (TextView) itemView.findViewById(R.id.hourly_item_time);
        hourly_item_temp = (TextView) itemView.findViewById(R.id.hourly_item_temp);
        hourly_item_hum = (TextView) itemView.findViewById(R.id.hourly_item_hum);
//        hourly_item_wd = (TextView) itemView.findViewById(R.id.hourly_item_wd);
//        hourly_item_wp = (TextView) itemView.findViewById(R.id.hourly_item_wp);
    }

    public TextView getHourly_item_time() {
        return hourly_item_time;
    }

    public void setHourly_item_time(TextView hourly_item_time) {
        this.hourly_item_time = hourly_item_time;
    }

    public TextView getHourly_item_temp() {
        return hourly_item_temp;
    }

    public void setHourly_item_temp(TextView hourly_item_temp) {
        this.hourly_item_temp = hourly_item_temp;
    }

    public TextView getHourly_item_hum() {
        return hourly_item_hum;
    }

    public void setHourly_item_hum(TextView hourly_item_hum) {
        this.hourly_item_hum = hourly_item_hum;
    }

    public TextView getHourly_item_wd() {
        return hourly_item_wd;
    }

    public void setHourly_item_wd(TextView hourly_item_wd) {
        this.hourly_item_wd = hourly_item_wd;
    }

    public TextView getHourly_item_wp() {
        return hourly_item_wp;
    }

    public void setHourly_item_wp(TextView hourly_item_wp) {
        this.hourly_item_wp = hourly_item_wp;
    }
}
