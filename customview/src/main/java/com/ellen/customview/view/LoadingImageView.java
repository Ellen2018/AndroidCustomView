package com.ellen.customview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.ellen.customview.R;
import com.ellen.customview.evaluator.MyEvaluator;
import com.ellen.customview.interpolator.MyInterpolator;

/**
 * 加载视图View
 */
@SuppressLint("AppCompatCustomView")
public class LoadingImageView extends ImageView {

    private int mTop;
    private int mCurImgIndex = 0;
    private static int mImgCount = 3;

    public LoadingImageView(Context context) {
        super(context,null);
        init();
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTop = top;
    }

    private void init() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100,0);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        //设置估值器
        valueAnimator.setEvaluator(new MyEvaluator());
        //设置插值器
        valueAnimator.setInterpolator(new MyInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer dx = (Integer) animation.getAnimatedValue();
                setTop(mTop - dx);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                setImageDrawable(getResources().getDrawable(R.mipmap.pic_1));
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                mCurImgIndex++;
                switch (mCurImgIndex % mImgCount){
                    case 0:
                        setImageDrawable(getResources().getDrawable(R.mipmap.pic_1));
                        break;
                    case 1:
                        setImageDrawable(getResources().getDrawable(R.mipmap.pic_2));
                        break;
                    case 2:
                        setImageDrawable(getResources().getDrawable(R.mipmap.pic_3));
                        break;
                }
            }
        });
        valueAnimator.start();
    }
}
