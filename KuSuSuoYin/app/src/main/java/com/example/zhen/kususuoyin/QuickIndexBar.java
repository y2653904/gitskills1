package com.example.zhen.kususuoyin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * 类名:      QuickIndexBar
 * 创建者:    yuanzhen
 * 创建时间:  2016/11/06.
 * 描述：     快速索引
 */

public class QuickIndexBar extends View {

    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };

    private Paint paint;
    private int mHeight;
    private int mWidth;       // 单元格宽度
    private float cellHeight; // 单元格的高度

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD); // 设置加粗
        paint.setTextAlign(Paint.Align.CENTER); // 设置对齐中间
        paint.setTextSize(getTextSize(14f)); // 设置字号
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制字母A-Z
        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];
            float x = mWidth * 0.5f;

            // 求文本的高度
            Rect bounds = new Rect();
            paint.getTextBounds(letter, 0, letter.length(), bounds);
            int textHeight = bounds.height();

            // 求出y坐标
            float y = cellHeight * 0.5f + textHeight * 0.5f + i * cellHeight;

            // 绘制字母
            canvas.drawText(letter, x, y, paint);
//            canvas.drawCircle(mWidth * 0.5f, 20f + i * 30f, 3f, paint);
        }
    }


    private float getTextSize(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, getResources().getDisplayMetrics());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();

        // 10 / 3 = 3
        cellHeight = mHeight * 1.0f / LETTERS.length;
    }

    int currentIndex = -1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getCurrentLetter(event);
                break;
            case MotionEvent.ACTION_MOVE:
                getCurrentLetter(event);
                break;
            case MotionEvent.ACTION_UP:
                currentIndex = -1;
                break;
        }

        return true; // 消费事件
    }



    // 根据触摸事件, 获取当前的字母
    private void getCurrentLetter(MotionEvent event) {
        int index = (int) (event.getY() / cellHeight); //获取当前字母索引
        // 字母索引在正确范围内
        if(index >= 0 && index < LETTERS.length){
            // 和上一次的字母索引不一致
            if(index != currentIndex){
                String letter = LETTERS[index];
                System.out.println("index: " + index + " letter: " + letter);

                mListener.updateLetter(letter);

                // 记录当前字母索引
                currentIndex = index;
            }
        }
    }

    private OnLetterUpdateListener mListener;
    public void setOnLetterUpdateListener(OnLetterUpdateListener mListener) {
        this.mListener = mListener;
    }
    public interface OnLetterUpdateListener {
        void updateLetter(String letter);
    }


}
