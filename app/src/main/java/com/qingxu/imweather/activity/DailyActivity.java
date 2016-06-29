package com.qingxu.imweather.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.qingxu.imweather.R;
import com.qingxu.imweather.adapter.DailyAdapter;
import com.qingxu.imweather.bean.Forecast;

import java.util.ArrayList;

/**
 * Created by QingXu on 2016/5/19.
 */
public class DailyActivity extends AppCompatActivity {

    private RecyclerView daily_recyclerview;
    private ArrayList<Forecast> forecasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        Intent intent = getIntent();
        forecasts = (ArrayList<Forecast>) intent.getSerializableExtra("forecasts");

        InitWidgets();
        LoadDailyData();
    }

    private void InitWidgets() {
        daily_recyclerview = (RecyclerView) findViewById(R.id.daily_recyclerview);
    }

    private void LoadDailyData() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        daily_recyclerview.setLayoutManager(mLayoutManager);
        daily_recyclerview.setHasFixedSize(true);
        DailyAdapter dailyAdapter = new DailyAdapter(this, forecasts);
        daily_recyclerview.setAdapter(dailyAdapter);
    }

    public void return_main(View v) {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
