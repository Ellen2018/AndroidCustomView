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
 * 支付宝支付成功动画效果
 */
public class AliPayView extends View {

    private Path mCirclePath, mDstPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private float mCurAnimValue;

    public AliPayView(Context context) {
        super(context);
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);

        mDstPath = new Path();
        mCirclePath = new Path();

        int mCenterX = 100, mCenterY = 100, mRadius = 50;

        mCirclePath.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CW);

        mCirclePath.moveTo(mCenterX - mRadius / 2, mCenterY);
        mCirclePath.lineTo(mCenterX, mCenterY + mRadius / 2);
        mCirclePath.lineTo(mCenterX + mRadius / 2, mCenterY - mRadius / 3);

        mPathMeasure = new PathMeasure(mCirclePath,false);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,2);
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

    public AliPayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        if(mCurAnimValue < 1){
            float stop = mPathMeasure.getLength() * mCurAnimValue;
            mPathMeasure.getSegment(0,stop,mDstPath,true);
        }else if(mCurAnimValue == 1){
            mPathMeasure.getSegment(0,mPathMeasure.getLength(),mDstPath,true);
            mPathMeasure.nextContour();
        }else {
            float stop = mPathMeasure.getLength() * (mCurAnimValue-1);
            mPathMeasure.getSegment(0,stop,mDstPath,true);
        }
        canvas.drawPath(mDstPath,mPaint);
    }
}
