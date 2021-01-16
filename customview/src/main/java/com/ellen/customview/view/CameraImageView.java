package com.ellen.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.ellen.customview.R;

@SuppressLint("AppCompatCustomView")
public class CameraImageView extends ImageView {

    private Bitmap mBitmap;
    private Paint mPaint;
    private Camera mCamera = new Camera();
    private Matrix mMatrix = new Matrix();
    private int mProgress = 30;

    public CameraImageView(Context context) {
        super(context);
        init();
    }

    public CameraImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CameraImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.cat);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public void setProgress(int mProgress) {
        this.mProgress = mProgress;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mCamera.save();
        canvas.save();

        mPaint.setAlpha(100);
        canvas.drawBitmap(mBitmap,0,0,mPaint);
        mCamera.rotateY(mProgress);

        int centerX = getWidth()/2/72;
        int centerY = getHeight()/2/72;

        //调整Camera的中心点位置
        mCamera.setLocation(centerX,centerY,mCamera.getLocationZ());

        mCamera.getMatrix(mMatrix);

        canvas.setMatrix(mMatrix);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        super.onDraw(canvas);
        canvas.restore();
    }
}
