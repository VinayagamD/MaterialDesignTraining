package com.wattabyte.materialdesigntraining;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Vinayagam on 9/24/15.
 */
public class MyView extends TextView {

    Paint paint;
    public static final String TAG="Vinay";

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();

        paint.setAntiAlias(true);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MyView dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,"MyView dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"MyView dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"MyView dispatchTouchEvent ACTION_CANCEL");
                break;
        }

        boolean  b= super.dispatchTouchEvent(ev);
        Log.d(TAG, "MyView dispatchTouchEvent RETURNS " + b);

        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        getParent().requestDisallowInterceptTouchEvent(true);

        switch (ev.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"MyView onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,"MyView onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"MyView onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"MyView onTouchEvent ACTION_CANCEL");
                break;
        }

//        boolean  b= super.onTouchEvent(ev);
        Log.d(TAG,"MyView onTouchEvent RETURNS "+true);

        return true;
    }
}
