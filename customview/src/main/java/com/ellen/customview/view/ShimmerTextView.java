package com.ellen.customview.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class ShimmerTextView extends TextView {

    private int mDx;
    private LinearGradient mLinearGradient;
    private Paint mPaint;

    public ShimmerTextView(Context context) {
        super(context);
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = getPaint();
        int length = (int) mPaint.measureText(getText().toString());
        createAnim(length);
        createLinearGradient(length);
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void createAnim(int length){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,2*length);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(4000);
        valueAnimator.start();
    }

    private void createLinearGradient(int length){
        mLinearGradient = new LinearGradient(-length,0,0,0,new
                int[]{getCurrentTextColor(),Color.RED,getCurrentTextColor()},
                new float[]{0,0.5f,1f}, Shader.TileMode.CLAMP
                );
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Matrix matrix = new Matrix();
        matrix.setTranslate(mDx,0);
        mLinearGradient.setLocalMatrix(matrix);
        mPaint.setShader(mLinearGradient);
        super.onDraw(canvas);

    }
}
