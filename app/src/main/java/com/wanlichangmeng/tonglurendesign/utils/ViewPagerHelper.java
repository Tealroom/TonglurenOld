package com.wanlichangmeng.tonglurendesign.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * 作者：周才智
 * 时间：2018/05/18
 * 注释：主要是Mainacitivity使用，为了禁止ViewPager滑动。
 */
public class ViewPagerHelper extends ViewPager{
    private boolean isCanScroll = true;

    public ViewPagerHelper(Context context) {
        super(context);
    }

    public ViewPagerHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置其是否能滑动换页
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);

    }
}
