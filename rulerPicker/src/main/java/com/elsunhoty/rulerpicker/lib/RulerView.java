package com.elsunhoty.rulerpicker.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.elsunhoty.rulerpicker.R;

public class RulerView extends FrameLayout {
    private float indicatorHeight = Defaults.INDICATOR_HEIGHT;
    private float indicatorWidth = Defaults.INDICATOR_WIDTH;
    private int indicatorColor = Defaults.INDICATOR_COLOR;
    RulerScroller rulerScroller;
    public RulerView(@NonNull Context context) {
        super(context);
        setUpView(context, null);
    }

    public RulerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpView(context,attrs);
    }

    public RulerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context, attrs);
    }

    public RulerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUpView(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void setUpView(Context context, AttributeSet attrs) {
        if (attrs!=null){
            setUpAttributes(attrs);
        }
        rulerScroller = new RulerScroller(context,attrs);
        addView(rulerScroller);

        View indicator = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                (int)indicatorWidth,
                (int)indicatorHeight);
        params.gravity = Gravity.CENTER;
        indicator.setLayoutParams(params);
        indicator.setBackgroundColor(indicatorColor);
        addView(indicator);
    }

    private void setUpAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RulerView, 0, 0
        );
        indicatorHeight = typedArray.getDimension(R.styleable.RulerView_ruler_indicator_height,
                Defaults.INDICATOR_HEIGHT);
        indicatorWidth = typedArray.getDimension(R.styleable.RulerView_ruler_indicator_width,
                Defaults.INDICATOR_WIDTH);

        indicatorColor = typedArray.getColor(R.styleable.RulerView_ruler_indicator_color,
                Defaults.INDICATOR_COLOR);
        typedArray.recycle();

    }

    public int getCurrentValue() {
        if (rulerScroller!=null){
           return rulerScroller.getCurrentValue();
        }
       throw new IllegalArgumentException("rulerScroller is null");
    }

    public void setOnRulerEvent(OnRulerEvent onRulerEvent) {
        if (rulerScroller!=null){
            rulerScroller.setRulerEvent(onRulerEvent);
        }
    }

}
