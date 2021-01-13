package com.ellen.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 演示PathMeasure的用法
 */
public class PathMeasureView extends View {
    public PathMeasureView(Context context) {
        super(context);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathMeasureApi3(canvas);
    }

    /**
     * PathMeasure Api用法
     * @param canvas
     */
    private void pathMeasureApi1(Canvas canvas){

        canvas.translate(100,100);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

        Path path = new Path();

        path.moveTo(0,0);
        path.lineTo(0,100);
        path.lineTo(100,100);
        path.lineTo(100,0);

        PathMeasure pathMeasure1 = new PathMeasure(path,false);
        PathMeasure pathMeasure2 = new PathMeasure(path,true);

        Log.e("Ellen2021","forceClosed = false------>" + pathMeasure1.getLength());
        Log.e("Ellen2021","forceClosed = true------>" + pathMeasure2.getLength());

        canvas.drawPath(path,paint);
    }

    /**
     * 演示PathMeasure的nextContour方法
     * @param canvas
     */
    private void pathMeasureApi2(Canvas canvas){
        canvas.translate(300,300);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

        Path path = new Path();

        path.addRect(-50,-50,50,50,Path.Direction.CW);
        path.addRect(-100,-100,100,100,Path.Direction.CW);
        path.addRect(-150,-150,150,150,Path.Direction.CW);

        canvas.drawPath(path,paint);

        PathMeasure pathMeasure = new PathMeasure(path,false);

        do{
            float len = pathMeasure.getLength();
            Log.e("Ellen2021","len = "+len);
        }while (pathMeasure.nextContour());
    }

    private void pathMeasureApi3(Canvas canvas){
        canvas.translate(300,300);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

        Path path = new Path();

        path.addRect(-50,-50,50,50,Path.Direction.CW);

        Path dst = new Path();
        dst.lineTo(10,100);

        PathMeasure pathMeasure = new PathMeasure(path,false);
        //截取0~150这段
        pathMeasure.getSegment(0,150,dst,false);
        canvas.drawPath(dst,paint);
    }
}
