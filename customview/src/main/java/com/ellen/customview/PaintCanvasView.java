package com.ellen.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintCanvasView extends View {

    public PaintCanvasView(Context context) {
        super(context);
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
    }

    /**
     * 绘制一个圆
     * @param canvas
     */
    private void drawCircle(Canvas canvas){
        //画笔设置
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);

        //使用Canvas绘制一个圆
        canvas.drawCircle(190,200,150,paint);
    }
}
