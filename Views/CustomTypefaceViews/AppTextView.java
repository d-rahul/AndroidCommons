package com.booking.t3.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.booking.t3.R;

public class AppTextView extends AppCompatTextView {
    private int font;

    public AppTextView(Context context) {
        super(context);
    }

    public AppTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public AppTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
        setClickable(true);
    }

    private void initView(Context context, AttributeSet attrs) {
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AppTextView);
            String str = a.getString(R.styleable.AppTextView_font_type);
            str = TextUtils.isEmpty(str) ? "1" : str;
            switch (Integer.parseInt(str)) {
                case 1:
                    str = "Hind-Regular.ttf";
                    break;
                case 2:
                    str = "Hind-Light.ttf";
                    break;
                case 3:
                    str = "Hind-Bold.ttf";
                    break;
                case 4:
                    str = "Hind-Semibold.ttf";
                    break;
                case 5:
                    str = "Hind-Medium.ttf";
                    break;
                default:
                    str = "Hind-Regular.ttf";
                    break;
            }
            setTypeface(FontManager.getInstance(getContext()).loadFont(str));
            a.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTrimmedText(){
        return getText().toString().trim();
    }

    public boolean isEmpty(){
        return TextUtils.isEmpty(getTrimmedText());
    }
}