package com.example.xia.demo.cusview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.xia.demo.utils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;


public class GuaGuaKa extends View {

    /**
     * 绘制线条的Paint,即用户手指绘制Path
     */
    private Paint mOutterPaint = new Paint();
    /**
     * 记录用户绘制的Path
     */
    private Path mPath = new Path();
    /**
     * 内存中创建的Canvas
     */
    private Canvas mCanvas;
    /**
     * mCanvas绘制内容在其上
     */
    private Bitmap mBitmap;

//    private Bitmap mEraseBitmap;

    private int mLastX;
    private int mLastY;
    //    private Bitmap mBgBitmap;
    private boolean first = false;
    private int mWidth;
    private int mHeight;
    private Bitmap screenBmp;

    public GuaGuaKa(Context context) {
        this(context, null);
    }

    public GuaGuaKa(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuaGuaKa(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
//        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        mEraseBitmap = Bit
        // 设置画笔
//        Process.killProcess();
        pathmatrix = new Matrix();
        mOutterPaint.setColor(Color.RED);
        mOutterPaint.setAntiAlias(true);
        mOutterPaint.setDither(true);
        mOutterPaint.setStyle(Paint.Style.FILL);
        mOutterPaint.setStrokeJoin(Paint.Join.ROUND); // 圆角
        mOutterPaint.setStrokeCap(Paint.Cap.ROUND); // 圆角
        // 设置画笔宽度
        mOutterPaint.setStrokeWidth(20);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
// 初始化bitmap
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
//        mCanvas.drawColor(Color.parseColor("#c0c0c0"));

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (screenBmp != null) {

            Bitmap alterBitmap = Bitmap.createBitmap(screenBmp.getWidth() * 2, screenBmp.getHeight() * 2, screenBmp.getConfig());
            //1.准备一个画板  在上面放上准备好的 空白的位图
            Canvas screenBmpCanvas = new Canvas(alterBitmap);
            //2.准备一个画笔
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            //3.画画
            Matrix m = new Matrix();
            m.setScale(2.0f, 2.0f);
            screenBmpCanvas.drawBitmap(screenBmp, m, paint);
            screenBmp.recycle();//及时回收
            screenBmp = null;
            canvas.drawBitmap(alterBitmap, 0, 0, null);
        }
        mOutterPaint.setColor(Color.BLUE);
    }

    Matrix pathmatrix;
    List<Matrix> matrixList = new ArrayList<>();

    /**
     * 绘制线条
     */
    private void drawPath(Canvas canvas) {
        mPath.transform(pathmatrix);
//        mCanvas.drawPath(mPath, mOutterPaint);
//        Matrix matrix = new Matrix();
        canvas.drawPath(mPath, mOutterPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:

                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);

                if (dx > 3 || dy > 3)
                    mPath.lineTo(x, y);

                mLastX = x;
                mLastY = y;
                break;
        }
        invalidate();
        return true;
    }

    public void undo(View view) {
        screenBmp = BitmapUtils.getViewBitmap(view);
        invalidate();
//        Matrix matrix = new Matrix();
//        Matrix undoMatrix = matrixList.remove(matrixList.size() - 1);
//        undoMatrix.invert(undoMatrix);
//
////        mPath.reset();
//        pathmatrix.set(undoMatrix);
////        mPath.reset();
//        invalidate();
    }

    public void makeTrans() {
        mPath.moveTo(0, 0);
        mPath.lineTo(0, 100);

        pathmatrix.postTranslate(100, 50);
        Matrix m = new Matrix();
        m.set(pathmatrix);
        matrixList.add(m);

        pathmatrix.postTranslate(100, 50);
        Matrix m1 = new Matrix();
        m1.postTranslate(100, 50);
        matrixList.add(m1);

        pathmatrix.postTranslate(120, 80);
        Matrix m2 = new Matrix();
        m2.postTranslate(120, 80);
        matrixList.add(m2);

//        matrixList.add(pathmatrix);

        Matrix matrix = new Matrix();
        float[] val = new float[]{1, 0, 568, 0, 1, 44, 0, 0, 1};
        matrix.setValues(val);
        Matrix invertedMatrix = new Matrix();
        matrix.invert(invertedMatrix);
        invalidate();
    }
}

