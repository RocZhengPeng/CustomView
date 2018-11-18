package com.example.roczheng.customview.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * author : roczheng
 * e-mail : 306608923@qq.com
 * time   : 2018/11/16
 * desc   : 外部拦截法
 */


public class BadViewPage extends ViewPager {
    public static final String TAG = "BadViewPager";

/*    private int mLastXIntercept;
    private int mLastYIntercept;*/

    public BadViewPage(Context context) {
        super(context);
    }

    public BadViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
      /*  boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId;
                super.onInterceptTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                //横坐标卫衣增量
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }

        mLastXIntercept = x;
        mLastYIntercept = y;*/

        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_DOWN) {

            super.onInterceptTouchEvent(ev);

            return false;
        }

        return true;
    }
}
