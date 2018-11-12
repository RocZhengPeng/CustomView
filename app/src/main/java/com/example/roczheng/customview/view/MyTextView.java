package com.example.roczheng.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/11/12.
 */

public class MyTextView extends View {

    private Paint paint;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(Color.WHITE);

        paint.setTextSize(40);
        //paint.setTextAlign(Paint.Align.CENTER);//设置文本居中对齐
        String text = "今天很好";
        int baseX = (int) (canvas.getWidth() / 2 - paint.measureText(text) / 2);
        //int baseX = (int) (centerX);
        int baseY = (int) (canvas.getHeight()/2 - ((paint.descent() + paint.ascent()) / 2));

        canvas.drawText(text, baseX, baseY, paint);
    }
}
