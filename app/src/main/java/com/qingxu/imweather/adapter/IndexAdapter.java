package com.qingxu.imweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qingxu.imweather.R;
import com.qingxu.imweather.bean.Index;
import com.qingxu.imweather.view.IndexViewHolder;

import java.util.ArrayList;

/**
 * Created by QingXu on 2016/6/7.
 */
public class IndexAdapter extends RecyclerView.Adapter<IndexViewHolder> {
    private Context context;
    private ArrayList<Index> indices;

    public IndexAdapter(Context context, ArrayList<Index> indices) {
        this.context = context;
        this.indices = indices;
    }

    @Override
    public IndexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndexViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_index, parent, false));
    }

    @Override
    public void onBindViewHolder(IndexViewHolder holder, int position) {

        holder.getTv_index_name().setText(indices.get(position).getName());
        holder.getTv_index_value().setText(indices.get(position).getValue());
        holder.getTv_index_desc().setText(indices.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return indices.size();
    }
}
