package com.test.utils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.util.AttributeSet;

import com.test.R;

/**
* Keep this in attr.xml file
*<declare-styleable name="UnderlinedTextView">
*        <attr name="underlineWidth" format="dimension" />
*        <attr name="underlineColor" format="color" />
*    </declare-styleable>
*
*/


public class UnderlinedTextView extends AppCompatTextView {

    private Rect mRect;
    private Paint mPaint;
    private int mColor;
    private float density;
    private float mStrokeWidth;

    public UnderlinedTextView(Context context) {
        this(context, null, 0);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyle) {

        density = context.getResources().getDisplayMetrics().density;

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.UnderlinedTextView, defStyle, 0);
        mColor = typedArray.getColor(R.styleable.UnderlinedTextView_underlineColor, 0xFFFF0000);
        mStrokeWidth = typedArray.getDimension(R.styleable.UnderlinedTextView_underlineWidth, density * 2);
        typedArray.recycle();

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mColor); //line mColor
        mPaint.setStrokeWidth(mStrokeWidth);
        setTypeface(null, Typeface.BOLD);
        //setTypeface(FontManager.getInstance(getContext()).loadFont("fonts/SanFranciscoTextBold.otf"));
    }

    public int getUnderLineColor() {
        return mColor;
    }

    public void setUnderLineColor(int mColor) {
        this.mColor = mColor;
        invalidate();
    }

    public float getUnderlineWidth() {
        return mStrokeWidth;
    }

    public void setUnderlineWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int count = getLineCount();

        final Layout layout = getLayout();
        float x_start, x_stop, x_diff;
        int firstCharInLine, lastCharInLine;

        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, mRect);
            firstCharInLine = layout.getLineStart(i);
            lastCharInLine = layout.getLineEnd(i);

            x_start = layout.getPrimaryHorizontal(firstCharInLine);
            x_diff = layout.getPrimaryHorizontal(firstCharInLine + 1) - x_start;
            x_stop = layout.getPrimaryHorizontal(lastCharInLine - 1) + x_diff;

            canvas.drawLine(x_start, baseline + mStrokeWidth, x_stop, baseline + mStrokeWidth, mPaint);
        }

        super.onDraw(canvas);
    }
}
