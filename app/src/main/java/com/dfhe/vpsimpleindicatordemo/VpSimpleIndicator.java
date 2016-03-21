package com.dfhe.vpsimpleindicatordemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 项目名称：VpSimpleIndicatorDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2016/3/21 10:19
 * 修改人：Administrator
 * 修改时间：2016/3/21 10:19
 * 修改备注：
 *
 * @param
 */
public class VpSimpleIndicator extends LinearLayout {

    private Paint mPaint;
    private Path mPath;//需要画的路径
    private int mTriangleWidth;//三角形边长
    private int mTriangleHeight;//三角形高度
    private int mInitTranslation;//初始坐标位置
    private int mTranslationX;//手指滑动的距离
    private static final float RADION_TRIANGLE_WIDTH = 1/6F;//三角形相对于tab的宽度

    public VpSimpleIndicator(Context context) {
        this(context, null);
    }

    public VpSimpleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VpSimpleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//设置抗锯齿
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));//给三角形的三个角设置弧度
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w/3 * RADION_TRIANGLE_WIDTH);
        mInitTranslation = w/3/2 - mTriangleWidth/2;

        initTriangle();
    }


    /**
     * 这个方法在onDraw方法执行之后执行，让子控件去绘制
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mInitTranslation + mTranslationX, getHeight() + 2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    /**
     * 初始化路径
     */
    private void initTriangle() {
        mTriangleHeight = mTriangleWidth/2;
        mPath = new Path();
        mPath.moveTo(0, 0);//移动初始化的位置
        mPath.lineTo(mTriangleWidth, 0);//画到第一个点的位置
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);//第二个点的位置
        mPath.close();//让三角形闭合

    }


    /**
     * 三角形跟着ViewPager的滑动改变
     * @param position
     * @param positionOffset
     */
    public void scroll(int position, float positionOffset) {
        //获取控件的宽度
        int translate = getWidth() /3;
        //改变手指滑动的距离
        mTranslationX = (int) (translate * (position + positionOffset));
        //刷新当前的view
        invalidate();
    }
}
