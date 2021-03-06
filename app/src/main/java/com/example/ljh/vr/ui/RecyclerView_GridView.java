package com.example.ljh.vr.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class RecyclerView_GridView extends GridView {

    public RecyclerView_GridView(Context context) {
        super(context);
    }

    public RecyclerView_GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerView_GridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
