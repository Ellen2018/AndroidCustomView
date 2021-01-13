package com.ellen.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TouchLineView extends View {

    private Paint mPaint;
    private Path mPath;

    public TouchLineView(Context context) {
        super(context);
    }

    public TouchLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mPath = new Path();
    }

    public TouchLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float mPreX,mPreY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //旧的方式
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                mPath.lineTo(event.getX(),event.getY());
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                mPath.lineTo(event.getX(),event.getY());
//                postInvalidate();
//            default:
//                break;
//        }

        //使用quadTo的方式
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX + event.getX())/2;
                float endY = (mPreY + event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath,mPaint);
    }
}
