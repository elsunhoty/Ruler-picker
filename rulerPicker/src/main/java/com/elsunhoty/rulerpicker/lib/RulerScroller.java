package com.elsunhoty.rulerpicker.lib;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

import com.elsunhoty.rulerpicker.R;

class RulerScroller extends HorizontalScrollView {
    int rulerMinValue = 0;
    int rulerInitValue = Defaults.MIN_VALUE;
    int rulerMaxValue = Defaults.MIN_VALUE;
    float hashMarkDistance = Defaults.HASH_MARK_DISTANCE;
    private OnRulerEvent mListener;
    private boolean fromUser = true;

    public RulerScroller(Context context) {
        super(context);
        setUpView(context, null, 0);
    }

    public RulerScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView(context, attrs, 0);

    }

    public RulerScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context, attrs, defStyleAttr);
    }


    private void setUpView(Context context, AttributeSet attrs, int defStyleAttr) {
        setUpAttributes(attrs);
        final BarView draw = new BarView(context, attrs, defStyleAttr);
        draw.setId(generateViewId());
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
        rulerInitValue = typedArray.getInt(R.styleable.RulerView_ruler_initial_value,
                Defaults.MIN_VALUE);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        int currentRealValue = (int) ((l + rulerMinValue) / hashMarkDistance);
        if (mListener != null)
            mListener.onRulerValueChanges(currentRealValue, fromUser);
        this.fromUser = true;
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
        this.fromUser = false;
        if (value > rulerMaxValue || value < rulerMinValue) {
            String message = " The Value " + value
                    + " is not between Max Value " + rulerMaxValue
                    + " and min  Value " + rulerMinValue;
            throw new IllegalArgumentException(message);
        } else {
            int scrollValue = (int) ((value * hashMarkDistance) - rulerMinValue);
            setScrollX(scrollValue);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (rulerInitValue != Defaults.MIN_VALUE) {
            setCurrentValue(rulerInitValue);
            rulerInitValue = Defaults.MIN_VALUE;
        }
    }
}
