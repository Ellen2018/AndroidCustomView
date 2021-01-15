package com.ellen.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

/**
 * 1.双缓冲机制
 * 2.可在子线程中进行刷新，不占用主线程资源
 * 3.适用于被动刷新，频繁的刷新，例如:游戏画面 & 视频播放 & 频繁的动画效果等
 *
 * SurfaceView有关的三个成员:Surface,SurfaceView,SurfaceHolder
 *
 * SurfaceView存在多少画布？:至少2块，一块前端画布和最少一个的后端画布
 * 前端画布:指的就是显示在用户面前的
 * 后端画布:也叫缓存画布，是不显示在用户面前的
 *
 * 它的原理就是:
 * 调用surfaceHolder.lockCanvas()获得后端画布，并且上锁，然后对其进行各种操作，操作的环境可发生在任意线程环境，当
 * 操作完成后调用surfaceHolder.unlockCanvasAndPost(canvas)，将当前操作的后端画布进行解锁，并且与前端画布进行对调，
 * 也就是之前的前端画布成为了后端画布，之前的后端画布成为了前端画布。
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Paint mPaint;
    private Path mPath;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        //表示重绘时此控件也需要重绘，onDraw会被调用
        setWillNotDraw(false);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);

        mPath = new Path();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //Surface对象被创建时调用
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        //Surface对象被改变时调用
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        //Surface对象被销毁时调用
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mPath.moveTo(x,y);
            return true;
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            mPath.lineTo(x,y);
        }
        //drawCanvasByUiThread();
        drawCanvasByNewThread();
        return super.onTouchEvent(event);
    }

    /**
     * 在UI线程中完成绘制操作
     */
    private void drawCanvasByUiThread() {
        SurfaceHolder surfaceHolder = getHolder();

        //添加监听
        surfaceHolder.addCallback(this);

        //获取缓存画布并且加锁
        Canvas canvas = surfaceHolder.lockCanvas();

        canvas.drawPath(mPath,mPaint);

        //完成了画布的有关操作并且解锁
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    /**
     * 在子线程中完成绘制操作
     */
    private void drawCanvasByNewThread(){
        new Thread(){
            @Override
            public void run() {
                SurfaceHolder surfaceHolder = getHolder();

                //添加监听
                surfaceHolder.addCallback(MySurfaceView.this);

                //获取缓存画布并且加锁，为什么要加锁？其目的很简单，就是为了线程安全
                Canvas canvas = surfaceHolder.lockCanvas();

                canvas.drawPath(mPath,mPaint);

                //完成了画布的有关操作并且解锁
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }.start();
    }

}
