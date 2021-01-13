package com.ellen.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintView extends View {

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseLineX = 40;
        int baseLineY = 200;

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawLine(baseLineX,baseLineY,3000,baseLineY,paint);

        //写文字
        paint.setColor(Color.GREEN);
        paint.setTextSize(120);
        canvas.drawText("harvic\'s blog",baseLineX,baseLineY,paint);

    }
}
