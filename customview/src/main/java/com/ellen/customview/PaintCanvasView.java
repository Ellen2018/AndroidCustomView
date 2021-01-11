package com.ellen.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintCanvasView extends View {

    public PaintCanvasView(Context context) {
        super(context);
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        testCanvasScreen(canvas);
    }

    /**
     * 绘制一个圆
     * @param canvas
     */
    private void drawCircle(Canvas canvas){
        //画笔设置
        Paint paint = new Paint();
        paint.setColor(Color.RED);//设置画笔颜色为红色
        paint.setStyle(Paint.Style.STROKE);//设置画笔的填充样式为:仅描边
        paint.setStrokeWidth(50);//设置边部宽度大小

        //使用Canvas绘制一个圆,坐标是(190,200)，半径为150
        canvas.drawCircle(190,200,150,paint);

        //使用Canvas绘制一个圆，坐标(190,200)，半径为100
        paint.setColor(0x7EFFFF00);//重新设置画笔颜色为:7EFFFF00
        canvas.drawCircle(190,200,100,paint);


    }

    /**
     * 关于Canvas的Api
     * @param canvas
     */
    private void canvasApi(Canvas canvas){
        //设置画布背景颜色,3种方法

        //方法1
        canvas.drawColor(Color.RED);

        //方法2
        //canvas.drawARGB();

        //方法3
        //canvas.drawRGB();

        //使用canvas绘制各种图形

        //绘制直线
        //canvas.drawLine();

        //绘制点
        //canvas.drawPoint();

        //绘制矩形,需要借助RectF类
        //canvas.drawRect();

        //Canvas的变换

        //平移
        //canvas.translate();

        //旋转
        //canvas.rotate();

        //......

        //画布的裁剪
        //canvas.clipRect()
        //canvas.clipOutPath()
        //canvas.clipOutRect()
        //......

        //Canvas的保存与恢复

        //画布的保存
        canvas.save();

        //画布的恢复
        canvas.restore();
    }

    /**
     * 关于Path的Api
     * @param canvas
     */
    private void pathApi(Canvas canvas){

        Paint paint = new Paint();

        Path path = new Path();

        //设置路径起点
        //path.moveTo();

        //绘制直线
        //path.lineTo();

        //绘制圆弧,需要借助RectF
        //path.arcTo();

        //形成闭环
        path.close();

        //使用Canvas绘制路径
        canvas.drawPath(path,paint);
    }

    /**
     * 关于Region的Api
     * @param canvas
     */
    private void regionApi(Canvas canvas){
        Rect rect1 = new Rect(100,100,400,200);
        Rect rect2 = new Rect(200,0,300,300);

        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);

        Region region = new Region();
        region.op(region1,region2, Region.Op.INTERSECT);

        drawRegion(canvas,region,paint);
    }

    /**
     * 绘制Region
     * @param canvas
     * @param region
     * @param paint
     */
    private void drawRegion(Canvas canvas,Region region,Paint paint){
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();

        while (regionIterator.next(rect)){
            canvas.drawRect(rect,paint);
        }
    }

    private void testCanvasScreen(Canvas canvas){
        Rect rect = new Rect(0,0,100,100);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);

        canvas.drawRect(rect,paint);

        //如果这里进行平移，是不会让上面的矩形进行平移的
        //由此可见Canvas的平移不会平移整个屏幕，它只是对绘制的坐标进行了改变
        canvas.translate(30,30);
    }
}
