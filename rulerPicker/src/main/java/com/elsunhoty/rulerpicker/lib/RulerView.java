package com.elsunhoty.rulerpicker.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
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
    RulerGravity gravity = RulerGravity.CENTER;

    public RulerView(@NonNull Context context) {
        super(context);
        setUpView(context, null, 0, 0);
    }

    public RulerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpView(context, attrs, 0, 0);
    }

    public RulerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context, attrs, defStyleAttr, defStyleAttr);
    }

    public RulerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUpView(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void setUpView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (attrs != null) {
            setUpAttributes(attrs);
        }
        rulerScroller = new RulerScroller(context, attrs, defStyleAttr);
        rulerScroller.setId(generateViewId());
        addView(rulerScroller);

        View indicator = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                (int) indicatorWidth,
                (int) indicatorHeight);

        switch (gravity) {
            case TOP:
                params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
                break;
            case BOTTOM:
                params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                break;
            default:
                params.gravity = Gravity.CENTER;
                break;
        }
        indicator.setLayoutParams(params);
        indicator.setBackgroundColor(indicatorColor);
        indicator.setId(generateViewId());
        addView(indicator, params);
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
        gravity = RulerGravity.parse(typedArray.getInt(R.styleable.RulerView_ruler_hash_mark_gravity, 1));
        typedArray.recycle();

    }

    public int getCurrentValue() {
        if (rulerScroller != null) {
            return rulerScroller.getCurrentValue();
        }
        throw new IllegalArgumentException("rulerScroller is null");
    }

    public void setCurrentValue(int value) {
        if (rulerScroller != null) {
            rulerScroller.setCurrentValue(value);
            return;
        }
        throw new IllegalArgumentException("rulerScroller is null");
    }

    public void setOnRulerEvent(OnRulerEvent onRulerEvent) {
        if (rulerScroller != null) {
            rulerScroller.setRulerEvent(onRulerEvent);
        }
    }

}
