package com.hth.overlaydrawlayout.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 只可代码调用
 *
 * 由于 SliderBarLinearLayout 可能不能铺满整个区域，
 * 所以使用一个MATCH_PARENT的RelativeLayout使用BezierView作背景来包装它
 *
 * Created by hth on 2017/6/30.
 */

public class MagicRelativeLayout extends RelativeLayout {

    private SliderBarLinearLayout mSliderBarLayout;
    private BezierView mBezierView;

    public MagicRelativeLayout(SliderBarLinearLayout view) {
        super(view.getContext());
        mSliderBarLayout = view;
        init();
    }

    private void init(){
        setLayoutParams(mSliderBarLayout.getLayoutParams());
        mBezierView = new BezierView(getContext());
        Drawable background = mSliderBarLayout.getBackground();
        if(background != null && background instanceof ColorDrawable){
            mBezierView.setBackgroundColor(((ColorDrawable) background).getColor());
        }else{
            mBezierView.setBackgroundColor(Color.GRAY);
        }
        addView(mBezierView, 0,
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mSliderBarLayout.setBackgroundColor(Color.TRANSPARENT);
        addView(mSliderBarLayout,
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setTouchY(float touchY, float offset) {
        mBezierView.setTouchY(touchY, offset);
        mSliderBarLayout.setTouchY(touchY, offset);
    }
}
