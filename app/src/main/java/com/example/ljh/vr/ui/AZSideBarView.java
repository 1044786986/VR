package com.example.ljh.vr.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.ljh.vr.R;
import com.socks.library.KLog;

public class AZSideBarView extends View {
    private String mLetters[] = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int mCurPos = -1;
    private Paint mPaint = new Paint();
    private OnTouchLetterListener mOnTouchLetterListener;

    private WindowManager mWindowManager;


    public interface OnTouchLetterListener {
        void onLetterChange(int pos, String s);

        void onLetterSelect(int pos, String s);
    }

    public void setOnTouchLetterListener(OnTouchLetterListener letterListener) {
        this.mOnTouchLetterListener = letterListener;
    }

    public AZSideBarView(Context context) {
//        super(context);
        this(context, null);
    }

    public AZSideBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AZSideBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_select_city, null);
//        ShowTipUtils.showAlertDialog(context,"",0,null);
//        mOnTouchLetterListener = new OnTouchLetterListener() {
//            @Override
//            public void onLetterChange(int pos,String s) {
//                invalidate();
//            }
//
//            @Override
//            public void onLetterSelect(int pos, String s) {
//
//            }
//        };
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int letterHeight = height / mLetters.length;
        for (int i = 0; i < mLetters.length; i++) {
            mPaint.setColor(getResources().getColor(R.color.normalText));
            mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(width / 2);
            if (i == mCurPos) {
                mPaint.setColor(getResources().getColor(R.color.appTheme));
            }
            float x = width / 2 - mPaint.measureText(mLetters[i]) / 2;
            float y = letterHeight * i + letterHeight;
            canvas.drawText(mLetters[i], x, y, mPaint);
            mPaint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final float touchY = event.getY();
        final int oldPos = mCurPos;
        final int touchPos = (int) (touchY / getHeight() * mLetters.length);
        if(touchPos > mLetters.length - 1){
            mCurPos = mLetters.length - 1;
        }else if(touchPos < 0){
            mCurPos = 0;
        }else{
            mCurPos = touchPos;
        }
        KLog.i("aaa", "touchPos = " + touchPos);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mOnTouchLetterListener != null) {
                    mOnTouchLetterListener.onLetterSelect(mCurPos, mLetters[mCurPos]);
                }
                break;
            default:
                if (mOnTouchLetterListener != null) {
                    mOnTouchLetterListener.onLetterChange(mCurPos, mLetters[mCurPos]);
                }
                    invalidate();
                    break;
        }
        return true;
    }

    public void setCurPos(int pos){
        mCurPos = pos;
        invalidate();
    }
}
