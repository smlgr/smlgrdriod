package org.thehellnet.mobile.smlgrdroid.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import org.thehellnet.mobile.smlgrdroid.utils.ScreenUtils;

/**
 * Created by sardylan on 16/06/15.
 */
public class ValueMeter extends View {
    private float sizeRatio = 16f / 11f;
    private int borderRadius = ScreenUtils.dpToPx(13);
    private int borderWidth = ScreenUtils.dpToPx(2);
    private int borderColor = Color.BLACK;
    private int backgroundColor = Color.WHITE;
    private float needleWidth = ScreenUtils.dpToPx(3);
    private float needlePadding = ScreenUtils.dpToPx(15);

    float max;
    float value;

    float width;
    float height;

    private RectF borderBackRectF;
    private Paint borderBackPaint;
    private RectF borderFrontRectF;
    private Paint borderFrontPaint;
    private float borderRadiusX;
    private float borderRadiusY;

    private Paint needlePaint;
    private float needleLength;
    private float needleMaxAngle;
    private float needleStartX;
    private float needleStartY;
    private float needleStopX;
    private float needleStopY;

    public ValueMeter(Context context, AttributeSet attrs) {
        super(context, attrs);

        max = 100;
        value = 0;

        initPaints();
    }

    private void initPaints() {
        borderBackRectF = new RectF();
        borderBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderBackPaint.setColor(borderColor);

        borderFrontRectF = new RectF();
        borderFrontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderFrontPaint.setColor(backgroundColor);

        borderRadiusX = borderRadius;
        borderRadiusY = borderRadius;

        needlePaint = new Paint();
        needlePaint.setStrokeWidth(needleWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(borderBackRectF, borderRadiusX, borderRadiusY, borderBackPaint);
        canvas.drawRoundRect(borderFrontRectF, borderRadiusX, borderRadiusY, borderFrontPaint);

        canvas.drawLine(needleStartX, needleStartY, needleStopX, needleStopY, needlePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        if (width / sizeRatio <= height) {
            height = width / sizeRatio;
        } else {
            width = height * sizeRatio;
        }

        float paddingStart = getPaddingStart() + (w - width) / 2;
        float paddingEnd = getPaddingEnd() - (w - width) / 2;
        float paddingTop = getPaddingTop() + (h - height) / 2;
        float paddingBottom = getPaddingBottom() - (h - height) / 2;

        borderBackRectF.set(paddingStart,
                paddingTop,
                width - paddingEnd,
                height - paddingBottom
        );

        borderFrontRectF.set(paddingStart + borderWidth,
                paddingTop + borderWidth,
                width - paddingEnd - borderWidth,
                height - paddingBottom - borderWidth
        );

        needleStartX = width / 2;
        needleStartY = height - paddingBottom - borderWidth - needlePadding;
        needleLength = height - paddingBottom - paddingTop - 2 * borderWidth - 2 * needlePadding;
        needleMaxAngle = (float) Math.acos((width - paddingStart - paddingEnd - 2 * borderWidth - 2 * needlePadding) / (2 * needleLength));

        computeNeedleStops();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int minw = getPaddingStart() + getPaddingEnd() + getSuggestedMinimumWidth();
        int minh = getPaddingTop() + getPaddingBottom() + getSuggestedMinimumHeight();

        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);
        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    private void computeNeedleStops() {
        float v = ((2 * (value / max)) - 1) * needleMaxAngle;

        needleStopX = (float) (needleStartX + needleLength * Math.sin(v));
        needleStopY = (float) (needleStartY - needleLength * Math.cos(v));
    }
}
