package com.example.xia.demo.cusview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class VerticalProgressBar extends ProgressBar {
    public VerticalProgressBar(Context context) {
        super(context);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
//        canvas.save();
        canvas.rotate(-90);// 坐标系逆时针旋转90度，将水平ProgressBar竖起来
        canvas.translate(-getHeight(), 0);//将经过旋转后得到的VerticalProgressBar移到正确的位置,负值代表坐标系向左平移
//        canvas.restore();
        super.onDraw(canvas);
    }

//    @Override
//    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
//        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());//互换宽高值
//    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldw, oldh);
    }
}