package com.hth.overlaydrawlayout.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

/**
 *
 * DrawerLayout 隐藏被拉出来的布局容器
 * Created by hth on 2017/6/30.
 */

public class SliderBarLinearLayout extends LinearLayout {
    private float mMaxOffset = 100;
    private View mSelectView;

    public SliderBarLinearLayout(Context context) {
        this(context, null);
    }

    public SliderBarLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SliderBarLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTouchY(float touchY, float offset) {
        boolean isOpen = offset == 1;
        int childCount = getChildCount();
        if(childCount < 1){ return; }
        for(int i = 0; i<childCount; i++){
            View child = getChildAt(i);
            child.setPressed(false);
            boolean isHove = isOpen && touchY > child.getTop() && touchY < child.getBottom();
            if(isHove){
                child.setPressed(true);
                // callback
                mSelectView = child;
            }//end if
            transformChild(getParent(), child, touchY, offset);
        }
    }

    private void transformChild(ViewParent parent, View child, float touchY, float offset) {
        float distance = Math.abs(touchY - child.getTop() - child.getHeight() /2);
        float scale = distance / getHeight() * 3;
        Log.d("SliderBarLinearLayout", "transformChild " + scale);
        child.setTranslationX(mMaxOffset - mMaxOffset * scale);
    }

    public void onMotionUp() {

        if(mSelectView != null){
            mSelectView.performClick();
            mSelectView.setPressed(false);
            mSelectView = null;
        }
    }
}
