package com.qingxu.imweather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by QingXu on 2016/6/5.
 */
public class CustomProgressBar extends View {

    private static final String TAG = "CustomProgressBar";
    private final int mCircleLineStrokeWidth = 8;
    private final int mTxtStrokeWidth = 2;
    // 画圆所在的距形区域
    private final RectF mRectF;
    private final Paint mPaint;
    private final Context mContext;
    private int mMaxProgress = 500;
    private int mProgress = 0;
    private String mTxtName, mTxtTitle;


    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mRectF = new RectF();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }


        // 设置画笔相关属性
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.rgb(0xe9, 0xe9, 0xe9));
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        // 位置
        mRectF.left = mCircleLineStrokeWidth / 2; // 左上角x
        mRectF.top = mCircleLineStrokeWidth / 2; // 左上角y
        mRectF.right = width - mCircleLineStrokeWidth / 2; // 左下角x
        mRectF.bottom = height - mCircleLineStrokeWidth / 2; // 右下角y

        canvas.drawArc(mRectF, -225, 270, false, mPaint);


        if (mProgress >= 0 && mProgress <= 50) {
            mPaint.setColor(Color.rgb(0x00, 0xff, 0x00));//绿色
        } else if (mProgress >= 51 && mProgress <= 100) {
            mPaint.setColor(Color.rgb(0xad, 0xff, 0x2f));//黄色改绿黄色，ADFF2F
        } else if (mProgress >= 101 && mProgress <= 150) {
            mPaint.setColor(Color.rgb(0xff, 0xa5, 0x00));//橙色
        } else if (mProgress >= 151 && mProgress <= 200) {
            mPaint.setColor(Color.rgb(0xff, 0x00, 0x00));//红色
        } else if (mProgress >= 201 && mProgress <= 300) {
            mPaint.setColor(Color.rgb(0x80, 0x00, 0x80));//紫色
        } else if (mProgress >= 301 && mProgress <= 500) {
            mPaint.setColor(Color.rgb(0x8b, 0x00, 0x80));//褐红色
        }

        float sweepAngel = 0;
        if (mProgress / mMaxProgress <= 1) {
            sweepAngel = (float) 270 * mProgress / mMaxProgress;
        } else {
            sweepAngel = 270;
        }

        canvas.drawArc(mRectF, -225, sweepAngel, false, mPaint);

        //绘制数值
        mPaint.setStrokeWidth(mTxtStrokeWidth);
        String textProgress = mProgress + "";
        int textHeight = height / 4;
        mPaint.setTextSize(textHeight);
        int textWidth = (int) mPaint.measureText(textProgress, 0, textProgress.length());
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(textProgress, width / 2 - textWidth / 2, height / 2 + textHeight / 2, mPaint);

        //绘制名称
        if (!TextUtils.isEmpty(mTxtName)) {
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            mPaint.setColor(Color.argb(0x8A, 0x00, 0x00, 0x00));
            String textName = mTxtName;
            textHeight = height / 10;
            mPaint.setTextSize(textHeight);
            textWidth = (int) mPaint.measureText(textName, 0, textName.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(textName, width / 2 - textWidth / 2, 3 * height / 4 + textHeight / 2, mPaint);
        }

        if (!TextUtils.isEmpty(mTxtTitle)) {
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            mPaint.setColor(Color.argb(0x8A, 0x00, 0x00, 0x00));
            String textTitle = mTxtTitle;
            textHeight = height / 10;
            mPaint.setTextSize(textHeight);
            textWidth = (int) mPaint.measureText(textTitle, 0, textTitle.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(textTitle, width / 2 - textWidth / 2, height - textHeight / 2, mPaint);
        }
    }


    public int getmCircleLineStrokeWidth() {
        return mCircleLineStrokeWidth;
    }

    public int getmTxtStrokeWidth() {
        return mTxtStrokeWidth;
    }

    public RectF getmRectF() {
        return mRectF;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public Context getmContext() {
        return mContext;
    }

    public int getmMaxProgress() {
        return mMaxProgress;
    }

    public void setmMaxProgress(int mMaxProgress) {
        this.mMaxProgress = mMaxProgress;
    }

    public int getmProgress() {
        return mProgress;
    }

    public void setmProgress(int mProgress) {
        this.mProgress = mProgress;
        this.invalidate();//重绘
    }

    public String getmTxtName() {
        return mTxtName;
    }

    public void setmTxtName(String mTxtName) {
        this.mTxtName = mTxtName;
    }

    public String getmTxtTitle() {
        return mTxtTitle;
    }

    public void setmTxtTitle(String mTxtTitle) {
        this.mTxtTitle = mTxtTitle;
    }
}
