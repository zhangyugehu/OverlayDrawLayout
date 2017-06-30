package com.hth.overlaydrawlayout.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hth on 2017/6/30.
 */

public class OverlayDrawerLayout extends DrawerLayout implements DrawerLayout.DrawerListener {

    private MagicRelativeLayout mMagicSliderBar;
    private SliderBarLinearLayout mSliderBar;
    private View mContentView;
    private float mTouchY;
    private float  mSlideOffset;

    public OverlayDrawerLayout(Context context) {
        this(context, null);
    }

    public OverlayDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverlayDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        int childCount = getChildCount();
        if(childCount < 2){
            return;
        }
        for(int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
            if(childAt instanceof SliderBarLinearLayout){
                mSliderBar = (SliderBarLinearLayout) childAt;
            }else {
                mContentView = childAt;
            }
        }
        if(mSliderBar != null) { removeView(mSliderBar); }
        mMagicSliderBar = new MagicRelativeLayout(mSliderBar);
        addView(mMagicSliderBar);
        addDrawerListener(this);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mTouchY = ev.getY();
        if(ev.getAction() == MotionEvent.ACTION_UP){
            mSliderBar.onMotionUp();
            return super.dispatchTouchEvent(ev);
        }
        if(mSlideOffset < 1) {
            return super.dispatchTouchEvent(ev);
        }else{
            mMagicSliderBar.setTouchY(mTouchY, mSlideOffset);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void transContentView() {
        if(mContentView == null){ return; }
        float transX = mContentView.getWidth() * mSlideOffset / 2;
        mContentView.setTranslationX(transX);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        mSlideOffset = slideOffset;
        transContentView();
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
