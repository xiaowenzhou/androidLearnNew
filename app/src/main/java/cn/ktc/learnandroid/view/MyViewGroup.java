package cn.ktc.learnandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount= getChildCount();
        if(childCount==0){
            setMeasuredDimension(0,0);
        }else {
            if (widthMode ==MeasureSpec.AT_MOST&& heightMode == MeasureSpec.AT_MOST){
                int height =getTotleHeight();
                int width= getMaxChildWidth();
                setMeasuredDimension(width,height);
            }else if (heightMode == MeasureSpec.AT_MOST){
                setMeasuredDimension(widthSize,getTotleHeight());
            }else if (widthMode ==MeasureSpec.AT_MOST){
                setMeasuredDimension(getMaxChildWidth(),heightSize);
            }
        }

    }

    private int getMaxChildWidth() {
        int childCount = getChildCount();
        int maxWidth =0;
        for (int i= 0;i<childCount;i++){
            View childView =getChildAt(i);
            if(childView.getMeasuredWidth()>maxWidth){
                maxWidth=childView.getMeasuredWidth();
            }
        }
        return maxWidth;
    }

    private int getTotleHeight() {
        int  childCount =getChildCount();
        int height =0;
        for (int i= 0; i<childCount;i++){
            View childView =getChildAt(i);
            height += childView.getMeasuredHeight();
        }
        return height;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int curHeight = t;
        for (int i = 0; i<childCount;i++){
            View childView =getChildAt(i);
            int height =childView.getMeasuredHeight();
            int width = childView.getMeasuredWidth();
            childView.layout(l,curHeight,r+width,curHeight+height);
            curHeight+=height;
        }

    }
}
