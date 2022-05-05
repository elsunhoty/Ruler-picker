package com.elsunhoty.rulerpicker.lib;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RulerView extends FrameLayout {
    public RulerView(@NonNull Context context) {
        super(context);
        setUpView(context);
    }

    public RulerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpView(context);
    }

    public RulerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context);
    }

    public RulerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUpView(context);
    }

    private void setUpView(Context context) {
        RulerScroller rulerScroller = new RulerScroller(context);
        addView(rulerScroller);

        View indicator = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                8,
                260);
        params.gravity = Gravity.CENTER;
        indicator.setLayoutParams(params);
        indicator.setBackgroundColor(Color.CYAN);
        addView(indicator);
    }
}
