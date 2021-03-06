package com.ellen.androidcustomview.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ellen.androidcustomview.R;
import com.ellen.customview.interpolator.CharEvaluator;
import com.ellen.customview.interpolator.FallingBallEvaluator;
import com.ellen.customview.view.FallingBallTextView;

public class AnimActivity extends AppCompatActivity {

    private FallingBallTextView tvTarget;
    private Button btStartAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        tvTarget = findViewById(R.id.tv_target);
        btStartAnim = findViewById(R.id.bt_start_anim);

        btStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //视图动画XML方式实现
                //Animation animation = AnimationUtils.loadAnimation(AnimActivity.this,R.anim.set_1);
                //tvTarget.startAnimation(animation);

                //codeAnim();

                //codeInterpolator();

                //codeValueAnimator();

                //codeCharObjectAnimator();

                //codeFallingBallAnimator();

                //codeObjectAnimator();

                //codeObjectAnimatorObject();

                codeMakeUpAnimator();
            }
        });
    }

    /**
     * 视图动画代码实现
     */
    private void codeAnim(){
        //声明动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.4f,0.0f,1.4f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //设置一次动画的经历时间
        scaleAnimation.setDuration(700);

        //关于Animation的Api

        //取消动画
        //scaleAnimation.cancel();

        //将控件重置到动画开始的状态
        //scaleAnimation.reset();

        //设置动画监听
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时调用
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时调用
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
               //动画重复再一次开始时调用
            }
        });

        //开启动画
        tvTarget.startAnimation(scaleAnimation);
    }

    /**
     * 插值器演示
     */
    private void codeInterpolator(){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.4f,0.0f,1.4f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillBefore(true);

        //设置均匀插值器
        //scaleAnimation.setInterpolator(new LinearInterpolator());

        //设置开始加速，最后变慢的插值器
        //scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        //设置一直加速的插值器
        //scaleAnimation.setInterpolator(new AccelerateInterpolator());

        //设置减速加速器
        //scaleAnimation.setInterpolator(new DecelerateInterpolator());

        //设置弹跳插值器(模拟球自由落地后的回弹效果)
        //scaleAnimation.setInterpolator(new BounceInterpolator());

        //设置初始偏移插值器，设置的偏移值为0.5f
        //scaleAnimation.setInterpolator(new AnticipateInterpolator(0.5f));

        //设置结束偏移插值器，设置偏移值为0.5f
        //scaleAnimation.setInterpolator(new OvershootInterpolator(0.5f));

        //设置初始 & 结束偏移插值器,AnticipateOvershootInterpolator是AnticipateInterpolator和OvershootInterpolator的结合
        //scaleAnimation.setInterpolator(new AnticipateOvershootInterpolator(0.5f,0.8f));

        //设置循环插值器,循环次数为2
        scaleAnimation.setInterpolator(new CycleInterpolator(2));

    tvTarget.startAnimation(scaleAnimation);
    }

    /**
     * 演示属性动画ValueAnimator的用法
     */
    private void codeValueAnimator(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (Integer) animation.getAnimatedValue();
                //重新设置TextView的位置
                tvTarget.layout(curValue,curValue,curValue+tvTarget.getWidth(),curValue+ tvTarget.getHeight());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
               //动画退出时调用
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束时调用
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
               //动画重复开始时调用
            }

            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始时调用
            }

            @Override
            public void onAnimationPause(Animator animation) {
                //动画暂停时调用
            }

            @Override
            public void onAnimationResume(Animator animation) {
                //动画恢复时调用
            }

            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                 //动画开始时调用isReverse
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                //动画结束时调用isReverse
            }
        });
        valueAnimator.start();
    }

    /**
     * 演示以Char值变化的属性动画ValueAnimator
     */
    private void codeCharObjectAnimator(){
        ValueAnimator valueAnimator =
                ValueAnimator.ofObject(new CharEvaluator(),new Character('A'),new Character('Z'));

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               char text = (char) animation.getAnimatedValue();
               tvTarget.setText(String.valueOf(text));
            }
        });

        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.start();
    }

    private Point mCurPoint;

    /**
     * 演示通过自定义估值器实现滑轴动画效果
     */
    private void codeFallingBallAnimator(){
        ValueAnimator valueAnimator =
                ValueAnimator.ofObject(new FallingBallEvaluator(),new Point(0,0),new Point(500,500));

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurPoint = (Point) animation.getAnimatedValue();
                tvTarget.layout(mCurPoint.x,mCurPoint.y,mCurPoint.x + tvTarget.getWidth(),mCurPoint.y + tvTarget.getHeight());

            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    /**
     * 演示ObjectAnimator的用法
     */
    private void codeObjectAnimator(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvTarget,"translationX",0,200,-200,0);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    /**
     * 演示ObjectAnimator的Object用法
     */
    private void codeObjectAnimatorObject(){
        ObjectAnimator objectAnimator =
                ObjectAnimator.ofObject(tvTarget,"fallingPos",
                        new FallingBallEvaluator(),new Point(0,0),new Point(500,500));

        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    /**
     * 演示组合动画
     */
    private void codeMakeUpAnimator(){
        ObjectAnimator oa1 =
                ObjectAnimator.ofInt(tvTarget,"BackgroundColor",0xffff00ff,0xffffff00,0xffff00ff);

        ObjectAnimator oa2 = ObjectAnimator.ofFloat(tvTarget,"translationY",0,400,0);

        ObjectAnimator oa3 = ObjectAnimator.ofFloat(tvTarget,"translationY",0,400,0);

        AnimatorSet animatorSet = new AnimatorSet();
        //playSequentially依次进行动画
        animatorSet.playSequentially(oa1,oa2,oa3);
        //playTogether并行进行动画
        //animatorSet.playTogether(oa1,oa2,oa3);
        animatorSet.setDuration(1000);
        animatorSet.start();
   }
}
