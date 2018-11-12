package com.example.roczheng.customview.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;


/**
 * Created by Administrator on 2018/8/14.
 */

public class StateView extends View {

    final float radius = dpToPixel(80);//圆的半径

    int progress = 0;//进度

    RectF arcRectF = new RectF();

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public StateView(Context context) {
        super(context);
    }

    public StateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(dpToPixel(40));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;

        if (progress > 360) {
            paint.setColor(Color.parseColor("#E91E63"));
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawCircle(centerX, centerY, radius, paint);

            paint.setColor(Color.parseColor("#000000"));
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeCap(Paint.Cap.ROUND);
            float reduce = dpToPixel(progress - 360);
            canvas.drawCircle(centerX, centerY, radius - reduce, paint);

            if (progress == 440) {
                Path path = new Path();
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.WHITE);
                paint.setPathEffect(new CornerPathEffect(5));
                path.moveTo(centerX - dpToPixel(20), centerY - dpToPixel(10));
                path.lineTo(centerX - dpToPixel(10), centerY + dpToPixel(10)); // 由当前位置 (0, 0) 向 (100, 100) 画一条直线
                path.rLineTo(dpToPixel(40), -dpToPixel(40));
                canvas.drawPath(path, paint);
            }

        } else {
            paint.setColor(Color.parseColor("#E91E63"));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(dpToPixel(15));


            arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
            canvas.drawArc(arcRectF, 0, progress, false, paint);


            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText((int) progress + "%", centerX, centerY - (paint.ascent() + paint.descent()) / 2, paint);
        }

    }

    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
}
