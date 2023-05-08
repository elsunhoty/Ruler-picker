package com.elsunhoty.rulerpicker.lib;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.elsunhoty.rulerpicker.R;

class BarView extends View {
    int rulerMinValue = 0;
    int rulerMaxValue = Defaults.MAX_VALUE;

    int hashMarkInterval = Defaults.HASH_MARK_INTERVAL;

    float hashMarkDistance = Defaults.HASH_MARK_DISTANCE;

    float longHashMarkWidth = Defaults.LONG_HASH_MARK_WIDTH;
    float longHashMarkHeight = Defaults.LONG_HASH_MARK_HEIGHT;
    float longHashMarkTextSize = Defaults.LONG_HASH_MARK_TEXT_SIZE;
    int longHashMarkTextColor = Defaults.LONG_HASH_MARK_TEXT_COLOR;
    int longHashMarkColor = Defaults.LONG_HASH_MARK_COLOR;
    float longHashMarkTextTopMargin = Defaults.LONG_HASH_MARK_TEXT_TOP_MARGIN;
    boolean longHashMarkTextVisible = true;
    float smallHashMarkWidth = Defaults.SMALL_HASH_MARK_WIDTH;
    float smallHashMarkHeight = Defaults.SMALL_HASH_MARK_HEIGHT;
    int smallHashMarkColor = Defaults.SMALL_HASH_MARK_COLOR;
    RulerGravity gravity = RulerGravity.CENTER;

    int viewHeight = 0;
    int viewWidth = 0;

    public BarView(Context context) {
        super(context);
        setUpAttributes(null);
    }

    public BarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpAttributes(attrs);
    }

    public BarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpAttributes(attrs);
    }

    private void setUpAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.RulerView, 0, 0
            );
            rulerMaxValue = typedArray.getInt(R.styleable
                    .RulerView_ruler_max_value, Defaults.MAX_VALUE);

            hashMarkInterval = typedArray.getInt(R.styleable
                    .RulerView_ruler_hash_mark_interval, Defaults.HASH_MARK_INTERVAL);

            hashMarkDistance = typedArray.getDimension(R.styleable
                    .RulerView_ruler_hash_mark_distance, Defaults.HASH_MARK_DISTANCE);

            longHashMarkWidth = typedArray.getDimension(R.styleable
                    .RulerView_ruler_long_hash_mark_width, Defaults.LONG_HASH_MARK_WIDTH);

            longHashMarkHeight = typedArray.getDimension(R.styleable
                    .RulerView_ruler_long_hash_mark_height, Defaults.LONG_HASH_MARK_HEIGHT);

            longHashMarkTextSize = typedArray.getDimension(R.styleable
                    .RulerView_ruler_long_hash_mark_text_size, Defaults.LONG_HASH_MARK_TEXT_SIZE);

            longHashMarkTextColor = typedArray.getColor(R.styleable
                    .RulerView_ruler_long_hash_mark_text_color, Defaults.LONG_HASH_MARK_TEXT_COLOR);

            longHashMarkColor = typedArray.getColor(R.styleable
                    .RulerView_ruler_long_hash_mark_color, Defaults.LONG_HASH_MARK_COLOR);

            longHashMarkTextTopMargin = typedArray.getDimension(R.styleable
                    .RulerView_ruler_long_hash_mark_text_margin_top, Defaults.LONG_HASH_MARK_TEXT_TOP_MARGIN);

            smallHashMarkWidth = typedArray.getDimension(R.styleable
                    .RulerView_ruler_small_hash_mark_width, Defaults.SMALL_HASH_MARK_WIDTH);

            smallHashMarkHeight = typedArray.getDimension(R.styleable
                    .RulerView_ruler_small_hash_mark_height, Defaults.SMALL_HASH_MARK_HEIGHT);

            smallHashMarkColor = typedArray.getColor(R.styleable
                    .RulerView_ruler_small_hash_mark_color, Defaults.SMALL_HASH_MARK_COLOR);

            longHashMarkTextVisible = typedArray.getBoolean(R.styleable
                    .RulerView_ruler_long_hash_mark_text_visible, true);
            gravity = RulerGravity.parse(typedArray.getInt(R.styleable.RulerView_ruler_hash_mark_gravity, 1));
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Compute the height required to render the view
        // Assume Width will always be MATCH_PARENT.
        View pa = (View) getParent();
        viewHeight = pa.getHeight();
        viewWidth = widthMeasureSpec;
//        int tallIndicatorCounter  = ((rulerMaxValue - rulerMinValue) / hashMarkInterval)+1;
//        int tallIndicatorTotalWidth = tallIndicatorCounter* longHashMarkWidth;
//        int shortIndicatorCounter = (rulerMaxValue - rulerMinValue) - tallIndicatorCounter;
//        int shortIndicatorTotalWidth = shortIndicatorCounter * smallHashMarkWidth;
        int width = (int) (widthMeasureSpec
                + ((rulerMaxValue - rulerMinValue) * hashMarkDistance));
//                + tallIndicatorTotalWidth
//                + shortIndicatorTotalWidth;
        setMeasuredDimension(width, heightMeasureSpec);
    }

    @Override
    public void onDraw(Canvas canvas) {
        viewHeight = getHeight();
        drawChart(canvas);
    }

    private void drawChart(Canvas canvas) {
        drawMainLines(canvas);
    }

    private void drawMainLines(Canvas canvas) {
        View pa = (View) getParent();
        viewWidth = pa.getWidth();


        int startDrawX = viewWidth / 2;

        int longHashStartY = 0;
        int smallHashStartY = 0;
        int textBaselineY = 0;

        switch (gravity) {
            case TOP:
                longHashStartY = 0;
                smallHashStartY = 0;
                textBaselineY = (int) (longHashMarkHeight + longHashMarkTextTopMargin);
                break;
            case CENTER:
                longHashStartY = (int) (viewHeight / 2 - longHashMarkHeight / 2);
                smallHashStartY = (int) (viewHeight / 2 - smallHashMarkHeight / 2);
                textBaselineY = (int) (longHashStartY + (int) (longHashMarkHeight) + longHashMarkTextTopMargin);
                break;
            case BOTTOM:
                longHashStartY = (int) (viewHeight - longHashMarkHeight);
                smallHashStartY = (int) (viewHeight - smallHashMarkHeight);
                textBaselineY = (int) (longHashStartY - longHashMarkTextTopMargin);

                break;
            default:
                break;

        }

        Paint hashMarkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hashMarkPaint.setStyle(Paint.Style.STROKE);
        hashMarkPaint.setAntiAlias(true);

        Paint bottomTextPaint = new Paint();
        bottomTextPaint.setStyle(Paint.Style.FILL);
        bottomTextPaint.setAntiAlias(true);
        bottomTextPaint.setColor(longHashMarkTextColor);
        bottomTextPaint.setTextSize(longHashMarkTextSize);
        for (int i = rulerMinValue; i <= rulerMaxValue; i++) {
            int startLineX = (int) (startDrawX + (hashMarkDistance * i));
            //Draw Big Indicator
            if ((i % hashMarkInterval) == 0) {
                // main Line
                hashMarkPaint.setStrokeWidth(longHashMarkWidth);
                hashMarkPaint.setColor(longHashMarkColor);
                canvas.drawLine(startLineX,
                        longHashStartY,
                        startLineX,
                        longHashStartY + (int) (longHashMarkHeight),
                        hashMarkPaint); // main line

                if (longHashMarkTextVisible) {
                    Rect bounds = new Rect();
                    bottomTextPaint.getTextBounds(i + "", 0, (i + "").length(), bounds);
                    int textWidth = bounds.width();
                    canvas.drawText(i + "",
                            startLineX - (int) (textWidth / 2),
                            textBaselineY,
                            bottomTextPaint);
                }
            } else {
                // second Line
                hashMarkPaint.setStrokeWidth(smallHashMarkWidth);
                hashMarkPaint.setColor(smallHashMarkColor);
                canvas.drawLine(startLineX,
                        smallHashStartY,
                        startLineX,
                        smallHashStartY + (int) (smallHashMarkHeight),
                        hashMarkPaint); // second line

            }
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e("Scrol 1l", l + "//" + t + "//" + oldl + "//" + oldt);
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}

