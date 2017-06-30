package com.hth.overlaydrawlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 *
 * 使用贝塞尔曲线画出背景效果
 *
 * Created by hth on 2017/6/30.
 */

public class BezierView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPath = new Path();
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 1、设置贝塞尔曲线控制点<br／>
     *     2、拼接绘制的path<br/>
     *     3、绘制path
     * @param y 手指移动Y坐标
     * @param percent DrawerLayout滑出的百分比
     */
    public void setTouchY(float y, float percent){
        mPath.reset();
        float width = getWidth() * percent;
        float transX = width / 2;
        float height = getHeight();
        float offsetY = height / 8;
        float startY = - offsetY;
        mPath.lineTo(transX, startY);
        float controlX = width *3/2;
        float controlY = y;
        float endY = height + offsetY;
        mPath.quadTo(controlX, controlY, transX, endY);
        mPath.lineTo(0, height);
        Log.d("BezierView", "setTouchY controlX: "+ controlX + " controlY: " + controlY + " startY: " + startY + " endY: " + endY);
        mPath.close();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    public void setBackgroundColor(int color){
        mPaint.setColor(color);
    }
}
