package com.ellen.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 加载进度条
 */
public class GetSegmentView extends View {

    private Paint mPaint;
    private Path mDstPath;
    private Path mCirclePath;
    private PathMeasure mPathMeasure;
    private float mCurAnimValue;

    public GetSegmentView(Context context) {
        super(context);
    }

    public GetSegmentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setLayerType(LAYER_TYPE_SOFTWARE,null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);

        mDstPath = new Path();
        mCirclePath = new Path();
        mCirclePath.addCircle(100,100,50,Path.Direction.CW);

        mPathMeasure = new PathMeasure(mCirclePath,true);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurAnimValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    public GetSegmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.WHITE);
//
//        float stop = mPathMeasure.getLength() * mCurAnimValue;
//
//        mDstPath.reset();
//        mPathMeasure.getSegment(0,stop,mDstPath,true);
//        canvas.drawPath(mDstPath,mPaint);

        float length = mPathMeasure.getLength();
        float stop = length * mCurAnimValue;
        float start = (float) (stop - ((0.5 - Math.abs(mCurAnimValue - 0.5))) * length);
        mDstPath.reset();
        canvas.drawColor(Color.WHITE);
        mPathMeasure.getSegment(start,stop,mDstPath,true);
        canvas.drawPath(mDstPath,mPaint);
    }
}
