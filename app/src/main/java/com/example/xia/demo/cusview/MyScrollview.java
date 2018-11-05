package com.example.xia.demo.cusview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 类名称：MyScrollview
 * 类描述：
 * 创建人：lei.zhang
 * 创建时间：on 2018/10/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */


public class MyScrollview extends ScrollView {

    private float xLast;
    private float yLast;
    private float xDistance;
    private float yDistance;

    public MyScrollview(Context context) {
        this(context, null);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 2);
    }

    /**
     * X轴滑动距离大于Y轴滑动距离，也就是用户横向滑动时，返回false，ScrollView不处理这次事件，
     * 让子控件中的TouchEvent去处理，所以横向滑动的事件交由ViewPager处理，
     * ScrollView只处理纵向滑动事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                if (xDistance > yDistance) {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
