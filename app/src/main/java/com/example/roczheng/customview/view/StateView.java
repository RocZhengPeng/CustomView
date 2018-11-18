package com.example.roczheng.customview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.roczheng.customview.R;


/**
 * Created by Administrator on 2018/8/14.
 */

public class StateView extends View {

    private int mCircularColor;

    private int mImageColor;

    private int mTextColor;

    private int mRadius;

    private int mState;

    float radius;//圆的半径

    int progress = 0;//进度

    RectF arcRectF = new RectF();

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public StateView(Context context) {
        super(context);
    }

    public StateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StateView);
        mCircularColor = typedArray.getInteger(R.styleable.StateView_circular_color, Color.RED);
        mImageColor = typedArray.getInteger(R.styleable.StateView_image_color, Color.WHITE);
        mTextColor = typedArray.getInteger(R.styleable.StateView_text_color, Color.WHITE);
        mRadius = typedArray.getInteger(R.styleable.StateView_radius, 80);
        mState = typedArray.getInteger(R.styleable.StateView_state, 1);
        typedArray.recycle();//回收
        radius = dpToPixel(mRadius);
    }

    public StateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(dpToPixel(40));
        //paint.setTextAlign(Paint.Align.CENTER);//设置文本居中对齐
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public void stareAnimal() {
        final ObjectAnimator animator = ObjectAnimator.ofInt(this, "progress", 0, 440);
        animator.setDuration(1500);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.start();
    }

    public void setState(int state) {
        this.mState = state;
    }

    /**
     * 为了使用动画来控制进度
     */
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    /**
     * 为了使用动画来控制进度
     */


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;

        if (progress > 360) {
            //画一个红色的圆在底部
            paint.setColor(mCircularColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawCircle(centerX, centerY, radius, paint);

            //画一个黑色的圆逐渐缩小
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeCap(Paint.Cap.ROUND);
            float reduce = dpToPixel(progress - 360);
            canvas.drawCircle(centerX, centerY, radius - reduce, paint);

            Path path = new Path();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(mImageColor);
            paint.setPathEffect(new CornerPathEffect(5));//画线的路径效果，CornerPathEffect可以将路径的转角变得圆滑

            if (progress == 440) {//画一个√
                switch (mState) {
                    case 0:
                        path.moveTo(centerX - dpToPixel(45), centerY - dpToPixel(0));
                        path.lineTo(centerX - dpToPixel(20), centerY + dpToPixel(30)); // 由当前位置 (0, 0) 向 (100, 100) 画一条直线
                        path.rLineTo(dpToPixel(60), -dpToPixel(50));
                        canvas.drawPath(path, paint);
                        break;
                    case 1:
                        //画×
                        canvas.drawLine(centerX - dpToPixel(30), centerY - dpToPixel(30), centerX + dpToPixel(30), centerY + dpToPixel(30), paint);
                        canvas.drawLine(centerX + dpToPixel(30), centerY - dpToPixel(30), centerX - dpToPixel(30), centerY + dpToPixel(30), paint);
                        break;
                    case 2:
                        //画！
                        canvas.drawLine(centerX, centerY - dpToPixel(40), centerX, centerY + dpToPixel(20), paint);
                        //画点
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        canvas.drawPoint(centerX, centerY + dpToPixel(50), paint);
                        break;
                }
            }

        } else {//360度以下

            //画一个圆弧
            paint.setColor(mCircularColor);
            paint.setStyle(Paint.Style.STROKE);//空心
            paint.setStrokeCap(Paint.Cap.ROUND);//设置画笔笔刷类型 影响画笔始末端
            paint.setStrokeWidth(dpToPixel(15));

            arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
            canvas.drawArc(arcRectF, 0, progress, false, paint);//画圆弧

            //绘制需要显示的文字
            paint.setColor(mTextColor);
            paint.setStyle(Paint.Style.FILL);
            // 计算Baseline绘制的起点X轴坐标 ，计算方式：画布宽度的一半 - 文字宽度的一半
            String text = (int) progress + "%";
            int baseX = (int) (canvas.getWidth() / 2 - paint.measureText(text) / 2);
            //int baseX = (int) (centerX);
            // 计算Baseline绘制的Y坐标 ，计算方式：画布高度的一半 - 文字总高度的一半
            int baseY = (int) (centerY - ((paint.descent() + paint.ascent()) / 2));

            canvas.drawText(text, baseX, baseY, paint);
        }

    }

    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
    //Canvas.DrawPaint(Paint p) – 用指定的Paint对象填充整个位图画布
}
