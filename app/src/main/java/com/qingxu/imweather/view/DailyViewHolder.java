package com.qingxu.imweather.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingxu.imweather.R;

/**
 * Created by QingXu on 2016/6/1.
 */
public class DailyViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_daily_today, tv_daily_today_cond_txt_d, tv_daily_today_tmp_max, tv_daily_today_tmp_min, tv_daily_today_wind_dir, tv_daily_today_wind_sc;
    private ImageView iv_daily_today_cond_code_d;

    public DailyViewHolder(View itemView) {
        super(itemView);
        tv_daily_today = (TextView) itemView.findViewById(R.id.tv_daily_today);
        tv_daily_today_cond_txt_d = (TextView) itemView.findViewById(R.id.tv_daily_today_cond_txt_d);
        tv_daily_today_tmp_max = (TextView) itemView.findViewById(R.id.tv_daily_today_tmp_max);
        tv_daily_today_tmp_min = (TextView) itemView.findViewById(R.id.tv_daily_today_tmp_min);
        tv_daily_today_wind_dir = (TextView) itemView.findViewById(R.id.tv_daily_today_wind_dir);
        tv_daily_today_wind_sc = (TextView) itemView.findViewById(R.id.tv_daily_today_wind_sc);
        iv_daily_today_cond_code_d = (ImageView) itemView.findViewById(R.id.iv_daily_today_cond_code_d);
    }

    public TextView getTv_daily_today() {
        return tv_daily_today;
    }

    public void setTv_daily_today(TextView tv_daily_today) {
        this.tv_daily_today = tv_daily_today;
    }

    public TextView getTv_daily_today_cond_txt_d() {
        return tv_daily_today_cond_txt_d;
    }

    public void setTv_daily_today_cond_txt_d(TextView tv_daily_today_cond_txt_d) {
        this.tv_daily_today_cond_txt_d = tv_daily_today_cond_txt_d;
    }

    public TextView getTv_daily_today_tmp_max() {
        return tv_daily_today_tmp_max;
    }

    public void setTv_daily_today_tmp_max(TextView tv_daily_today_tmp_max) {
        this.tv_daily_today_tmp_max = tv_daily_today_tmp_max;
    }

    public TextView getTv_daily_today_tmp_min() {
        return tv_daily_today_tmp_min;
    }

    public void setTv_daily_today_tmp_min(TextView tv_daily_today_tmp_min) {
        this.tv_daily_today_tmp_min = tv_daily_today_tmp_min;
    }

    public TextView getTv_daily_today_wind_dir() {
        return tv_daily_today_wind_dir;
    }

    public void setTv_daily_today_wind_dir(TextView tv_daily_today_wind_dir) {
        this.tv_daily_today_wind_dir = tv_daily_today_wind_dir;
    }

    public TextView getTv_daily_today_wind_sc() {
        return tv_daily_today_wind_sc;
    }

    public void setTv_daily_today_wind_sc(TextView tv_daily_today_wind_sc) {
        this.tv_daily_today_wind_sc = tv_daily_today_wind_sc;
    }

    public ImageView getIv_daily_today_cond_code_d() {
        return iv_daily_today_cond_code_d;
    }

    public void setIv_daily_today_cond_code_d(ImageView iv_daily_today_cond_code_d) {
        this.iv_daily_today_cond_code_d = iv_daily_today_cond_code_d;
    }
}
