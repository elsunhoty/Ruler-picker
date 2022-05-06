package com.elsunhoty.rulerpicker.lib;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

import com.elsunhoty.rulerpicker.R;

class RulerScroller extends HorizontalScrollView {
    int rulerMinValue = 0;
    int rulerMaxValue = Defaults.MAX_VALUE;
    float hashMarkDistance = Defaults.HASH_MARK_DISTANCE;
    private OnRulerEvent mListener;

    public RulerScroller(Context context) {
        super(context);
        setUpView(context, null);
    }

    public RulerScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView(context, attrs);

    }

    public RulerScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context, attrs);
    }


    private void setUpView(Context context, AttributeSet attrs) {
        setUpAttributes(attrs);
        final BarView draw = new BarView(context, attrs);
        addView(draw);
    }

    private void setUpAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RulerView, 0, 0
        );
        hashMarkDistance = typedArray.getDimension(R.styleable.RulerView_ruler_hash_mark_distance,
                Defaults.HASH_MARK_DISTANCE);
        rulerMaxValue = typedArray.getInt(R.styleable.RulerView_ruler_max_value,
                Defaults.MAX_VALUE);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        int currentRealValue = (int) ((l + rulerMinValue) / hashMarkDistance);
        if (mListener != null)
            mListener.onRulerValueChanges(currentRealValue);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    void setRulerEvent(OnRulerEvent onRulerEvent) {
        this.mListener = onRulerEvent;
    }

    int getCurrentValue() {
        int scrollX = getScrollX();
        return (int) ((scrollX + rulerMinValue) / hashMarkDistance);
    }

    public void setCurrentValue(int value) {
        if (value>rulerMaxValue ||value<rulerMinValue){
            String message = " The Value "+value
                    +" is not between Max Value "+rulerMaxValue
                    +" and min  Value "+rulerMinValue;
            throw new IllegalArgumentException(message);
        }else {
            int scrollValue = (int) ((value * hashMarkDistance ) - rulerMinValue);
            setScrollX(scrollValue);
        }
    }
}
