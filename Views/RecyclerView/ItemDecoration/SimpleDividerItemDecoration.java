package com.library.viewhelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.booking.t3.R;

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private boolean drawLastDividerDecoration;
    private int paddingLeft;
    private int paddingRight;

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
    }

    /**
     *
     * @param context
     * @param drawLastDivider Pass true if you want to show divider line in last of the item
     * @param paddingLeft Pass padding that you want to add to the left of divider
     * @param paddingRight Pass padding that you want to add to the right of divider
     */
    public SimpleDividerItemDecoration(Context context, boolean drawLastDivider, int paddingLeft, int paddingRight) {
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
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = paddingLeft == -1 ? parent.getPaddingLeft() : paddingLeft;
        int right = paddingRight == -1 ? (parent.getWidth() - parent.getPaddingRight()) : paddingRight;

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

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
