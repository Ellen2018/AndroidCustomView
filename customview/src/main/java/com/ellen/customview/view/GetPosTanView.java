package com.ellen.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ellen.customview.R;

public class GetPosTanView extends View {

    private Path mCirclePath,mDstPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private float mCurAnimValue;
    private Bitmap mBitmap;
    private float[] pos = new float[2];
    private float[] tan = new float[2];

    public GetPosTanView(Context context) {
        super(context);
    }

    public GetPosTanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arraw);

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

    public GetPosTanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float length = mPathMeasure.getLength();
        float stop = length * mCurAnimValue;
        float start = (float) (stop - ((0.5 - Math.abs(mCurAnimValue - 0.5))) * length);
        mDstPath.reset();
        canvas.drawColor(Color.WHITE);
        mPathMeasure.getSegment(start,stop,mDstPath,true);
        canvas.drawPath(mDstPath,mPaint);

        mPathMeasure.getPosTan(stop,pos,tan);
        float degrees = (float) (Math.atan2(tan[1],tan[0]) * 180.0/Math.PI);
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        matrix.postTranslate(pos[0],pos[1]);
        canvas.drawBitmap(mBitmap,matrix,mPaint);

    }
}
