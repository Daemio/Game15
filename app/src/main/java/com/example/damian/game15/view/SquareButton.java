package com.example.damian.game15.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Admin on 26.10.2015.
 */
public class SquareButton extends Button {
    public SquareButton(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }

    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
