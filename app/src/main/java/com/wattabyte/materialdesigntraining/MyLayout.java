package com.wattabyte.materialdesigntraining;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Vinayagam on 9/24/15.
 */
public class MyLayout extends FrameLayout {

    public static final String TAG = "Vinay";
    Paint paint = null;

    public MyLayout(Context context) {
        super(context);
        init();

    }



    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();

        paint.setAntiAlias(true);
        setWillNotDraw(false);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"MyLayout dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,"MyLayout dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"MyLayout dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"MyLayout dispatchTouchEvent ACTION_CANCEL");
                break;
        }

        boolean  b= super.dispatchTouchEvent(ev);
        Log.d(TAG, "MyLayout dispatchTouchEvent RETURNS " + b);

        return b;
    }
    @Override

    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"MyLayout onInterceptTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,"MyLayout onInterceptTouchEvent ACTION_MOVE");
//                return  true;
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"MyLayout onInterceptTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"MyLayout onInterceptTouchEvent ACTION_CANCEL");
                break;
        }

        boolean  b= super.onInterceptTouchEvent(ev);
        Log.d(TAG,"MyLayout onInterceptTouchEvent RETURNS "+b);

        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"MyLayout onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,"MyLayout onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"MyLayout onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"MyLayout onTouchEvent ACTION_CANCEL");
                break;
        }

        boolean  b= super.onTouchEvent(ev);
        Log.d(TAG,"MyLayout onTouchEvent RETURNS "+b);

        return b;
    }

}
