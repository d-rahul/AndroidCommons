package com.configureit.chat.library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.configureit.befrnd.R;
import com.configureit.chat.library.utils.DeviceUtils;

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private boolean drawLastDividerDecoration;
    private int paddingLeft, paddingRight;

    public SimpleDividerItemDecoration(Context context, boolean drawLastDivider) {
        int[] attrs = {android.R.attr.listDivider};
        TypedArray ta = context.obtainStyledAttributes(attrs);
        //Get Drawable and use as needed
        Drawable divider = ta.getDrawable(0);
        //Clean Up
        ta.recycle();
        if (divider != null) {
            mDivider = ContextCompat.getDrawable(context, R.drawable.divider_line);
        } else {
            mDivider = divider;
        }
        this.drawLastDividerDecoration = drawLastDivider;
        paddingLeft = DeviceUtils.dpToPx(context, 16);
        paddingRight = DeviceUtils.dpToPx(context, 16);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            //Check for last divider decoration visibility
            if (!drawLastDividerDecoration && i == (childCount - 1)) {
                break;
            }

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            //mDivider.setBounds(left <= 0 ? paddingLeft : left, top, right <= 0 ? paddingRight : right, bottom);
            mDivider.setBounds(paddingLeft, top, right - paddingRight, bottom);
            mDivider.draw(c);
        }
    }
}
