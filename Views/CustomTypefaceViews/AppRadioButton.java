package com.myapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.myapp.R;

public class AppRadioButton extends android.support.v7.widget.AppCompatRadioButton {

    private int font;

    public AppRadioButton(Context context) {
        super(context);
    }

    public AppRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public AppRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AppTextView);
            String str = a.getString(R.styleable.AppTextView_font_type);
            str = TextUtils.isEmpty(str) ? "1" : str;
            switch (Integer.parseInt(str)) {
                case 1:
                    str = "fonts/Hind-Regular.ttf";
                    break;
                case 2:
                    str = "fonts/Hind-Light.ttf";
                    break;
                case 3:
                    str = "fonts/Hind-Bold.ttf";
                    break;
                case 4:
                    str = "fonts/Hind-Semibold.ttf";
                    break;
                case 5:
                    str = "fonts/Hind-Medium.ttf";
                    break;
                default:
                    str = "fonts/Hind-Regular.ttf";
                    break;
            }
            setTypeface(FontManager.getInstance(getContext()).loadFont(str));
            a.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
