package app.zuliangwang.zuliangnews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by zuliangwang on 15/7/22.
 */
public class MainViewPager extends android.support.v4.view.ViewPager {
    private float xDistance , yDistance , xLast, yLast;
    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MainViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("opsa","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("opsa","onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("opsa","onTouchEvent");
        return super.onTouchEvent(ev);


//        return true;
    }
}
