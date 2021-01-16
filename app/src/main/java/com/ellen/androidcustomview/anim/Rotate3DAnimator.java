package com.ellen.androidcustomview.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Rotate3DAnimator extends Animation {

    private final float mFromDegress;
    private final float mEndDegress;

    private float mDepthZ = 400;
    private float mCenterX,mCenterY;
    private Camera mCamera;
    private final boolean mReverse;

    public Rotate3DAnimator(float mFromDegress,float mEndDegress,boolean reverse){
        this.mFromDegress = mFromDegress;
        this.mEndDegress = mEndDegress;
        this.mReverse = reverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);

        mCenterX = width/2;
        mCenterY = height/2;
        mCamera = new Camera();

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        float degress = mFromDegress + (mEndDegress - mFromDegress) * interpolatedTime;
        mCamera.save();
        float z;
        if(mReverse){
            z = mDepthZ * interpolatedTime;
        }else {
            z = mDepthZ * (1f - interpolatedTime);
        }
        mCamera.translate(0f,0f,z);

        final Matrix matrix = t.getMatrix();
        mCamera.rotateY(degress);
        mCamera.getMatrix(matrix);
        mCamera.restore();

        matrix.preTranslate(-mCenterX,-mCenterY);
        matrix.postTranslate(mCenterX,mCenterY);
        super.applyTransformation(interpolatedTime, t);
    }
}
