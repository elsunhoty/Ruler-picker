package com.elsunhoty.rulerpicker.lib;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

class RulerScroller extends HorizontalScrollView {

    private onScrolled mListener = null;

    public RulerScroller(Context context) {
        super(context);
        addMyView();
    }

    public RulerScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        addMyView();

    }

    public RulerScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addMyView();
    }


    private void addMyView() {
        final BarView draw = new BarView(getContext());
        addView(draw);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        // TODO Auto-generated method stub
        //      Log.e("Scrollinggg", "X from ["+oldl+"] to ["+l+"]");
//        int y = (l+rulerMinValue) / indicatorDistance;
        if (mListener != null)
//            mListener.onclick(y);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setOnScrollingListener(onScrolled onScrolled) {
        mListener = onScrolled;
    }

    public interface onScrolled {
        void onclick(int value);
    }
}
