package com.elsunhoty.rulerpicker.lib;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

class BarView extends View {
    int rulerMinValue = 0;
    int rulerMaxValue = 90;

    int tallIndicatorInterval = 9;

    int indicatorDistance = 100;

    int tallIndicatorWith = 8;
    int tallIndicatorHeight = 160;
    int tallIndicatorTextSize = 40;
    int tallIndicatorTextColor = Color.RED;
    int tallIndicatorColor = Color.GREEN;
    int tallIndicatorTextTopMargin = 50;

    int shortIndicatorWith=4;
    int shortIndicatorHeight=80;
    int shortIndicatorColor = Color.BLUE;

    int viewHeight = 0;
    int viewWidth = 0;

    public BarView(Context context) {
        super(context);
    }

    public BarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Compute the height required to render the view
        // Assume Width will always be MATCH_PARENT.
        View pa = (View) getParent();
        viewHeight = pa.getHeight();
        viewWidth = widthMeasureSpec;
        Log.e("XXX","==> "+viewWidth +"==> "+widthMeasureSpec+"==> "+heightMeasureSpec);
        int tallIndicatorCounter  = ((rulerMaxValue - rulerMinValue) / tallIndicatorInterval)+1;
        int tallIndicatorTotalWidth = tallIndicatorCounter*tallIndicatorWith;
        int shortIndicatorCounter = (rulerMaxValue - rulerMinValue) - tallIndicatorCounter;
        int shortIndicatorTotalWidth = shortIndicatorCounter *shortIndicatorWith;
        int width = widthMeasureSpec
                + ((rulerMaxValue - rulerMinValue) * indicatorDistance);
//                + tallIndicatorTotalWidth
//                + shortIndicatorTotalWidth;
        setMeasuredDimension(width, heightMeasureSpec);
    }

    @Override
    public void onDraw(Canvas canvas) {
        viewHeight = getHeight();
        Log.e("Parent", viewHeight + "//" + viewWidth);
        drawChart(canvas);
    }

    private void drawChart(Canvas canvas) {
        drawMainLines(canvas);
    }

    private void drawMainLines(Canvas canvas) {
        View pa = (View) getParent();
        viewWidth = pa.getWidth();


        int startDrawX = viewWidth / 2;
        int startDrawY = viewHeight / 2;

        Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setStrokeWidth(8);
        myPaint.setColor(Color.WHITE);
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setAntiAlias(true);

        Paint bottomTextPaint = new Paint();
        bottomTextPaint.setStyle(Paint.Style.FILL);
        bottomTextPaint.setAntiAlias(true);
        canvas.drawPaint(bottomTextPaint);
        bottomTextPaint.setColor(tallIndicatorTextColor);
        bottomTextPaint.setTextSize(tallIndicatorTextSize);
        for (int i = rulerMinValue ; i <= rulerMaxValue; i++) {
            int startLineX = startDrawX + (indicatorDistance * i);
            //Draw Big Indicator
            if ((i % tallIndicatorInterval) == 0) {
                // main Line
                myPaint.setStrokeWidth(tallIndicatorWith);
                myPaint.setColor(tallIndicatorColor);
                canvas.drawLine(startLineX,
                        startDrawY + (int)(tallIndicatorHeight/2),
                        startLineX,
                        startDrawY - (int)(tallIndicatorHeight/2),
                        myPaint); // main line

                Rect bounds = new Rect();
                bottomTextPaint.getTextBounds(i + "", 0, (i + "").length(), bounds);
                int textWidth = bounds.width();
                canvas.drawText(i + "",
                        startLineX - (int)(textWidth/2),
                        startDrawY + (int)(tallIndicatorHeight/2) + tallIndicatorTextTopMargin,
                        bottomTextPaint);
            } else {
                // second Line
                myPaint.setStrokeWidth(shortIndicatorWith);
                myPaint.setColor(shortIndicatorColor);
                canvas.drawLine(startLineX,
                        startDrawY + (int)(shortIndicatorHeight/2),
                        startLineX,
                        startDrawY - (int) (shortIndicatorHeight/2),
                        myPaint); // second line

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

