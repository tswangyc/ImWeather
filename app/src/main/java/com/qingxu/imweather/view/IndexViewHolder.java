package com.qingxu.imweather.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qingxu.imweather.R;

/**
 * Created by QingXu on 2016/6/7.
 */
public class IndexViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_index_name, tv_index_value, tv_index_desc;

    public IndexViewHolder(View itemView) {
        super(itemView);
        tv_index_name = (TextView) itemView.findViewById(R.id.tv_index_name);
        tv_index_value = (TextView) itemView.findViewById(R.id.tv_index_value);
        tv_index_desc = (TextView) itemView.findViewById(R.id.tv_index_desc);

    }

    public TextView getTv_index_name() {
        return tv_index_name;
    }

    public void setTv_index_name(TextView tv_index_name) {
        this.tv_index_name = tv_index_name;
    }

    public TextView getTv_index_value() {
        return tv_index_value;
    }

    public void setTv_index_value(TextView tv_index_value) {
        this.tv_index_value = tv_index_value;
    }

    public TextView getTv_index_desc() {
        return tv_index_desc;
    }

    public void setTv_index_desc(TextView tv_index_desc) {
        this.tv_index_desc = tv_index_desc;
    }
}
