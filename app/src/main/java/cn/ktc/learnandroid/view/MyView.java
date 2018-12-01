package cn.ktc.learnandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    Paint mPaint = new Paint();

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int getMysize(int defaultsize, int measureSpec) {
        int mySize = defaultsize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {
                //没有指定大小，设置为默认值
                mySize = defaultsize;
                break;
            }
            case MeasureSpec.AT_MOST: {
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {
                mySize = size;
                break;
            }
            default:
        }
        return mySize;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width =getMysize(100,widthMeasureSpec);
        int height =getMysize(100,heightMeasureSpec);
        if(width<height){
            height=width;
        }else {
            width =height;
        }
        setMeasuredDimension(width,height);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r =getMeasuredWidth()/2;
        int centerX =r;
        int centerY =r;
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(centerX,centerY,r,mPaint);
    }
}
