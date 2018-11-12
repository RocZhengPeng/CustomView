package com.example.roczheng.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/8/14.
 */

public class BaseView extends View {

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        //Shader shader=new LinearGradient(100,100,500,500,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"),Shader.TileMode.CLAMP);
        Shader shader = new RadialGradient(300, 300, 200, Color.parseColor("#e91e63"), Color.parseColor("#2196f3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(300, 300, 200, paint);

        Shader shader1 = new RadialGradient(800, 800, 100, Color.parseColor("#e91e63"), Color.parseColor("#2196f3"), Shader.TileMode.REPEAT);
        paint.setShader(shader1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(800, 800, 200, paint);
    }
}
