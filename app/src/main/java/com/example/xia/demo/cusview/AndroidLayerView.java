package com.example.xia.demo.cusview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 类名称：AndroidLayerView
 * 类描述：
 * 创建人：lei.zhang
 * 创建时间：on 2018/10/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */


public class AndroidLayerView extends View {

    public AndroidLayerView(Context context) {
        this(context, null);
    }

    public AndroidLayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AndroidLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        canvas.drawCircle(150, 150, 100, mPaint);

        canvas.saveLayerAlpha(0, 0, 300, 300, 127, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(200, 200, 100, mPaint);
        canvas.restore();
    }
}
