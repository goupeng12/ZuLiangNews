package app.zuliangwang.zuliangnews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import app.zuliangwang.zuliangnews.adapter.MainRecyclerViewAdapter;

/**
 * Created by zuliangwang on 15/7/22.
 */
public class MainRecyclerView extends android.support.v7.widget.RecyclerView {
    private float xDistance , yDistance , xLast, yLast;

    public MainRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainRecyclerView(Context context,MainRecyclerViewAdapter adapter) {
        super(context);
    }

    //    @Override
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//
//        switch (e.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.d("opsa","down");
//                xDistance = yDistance = 0;
//                xLast =e.getX();
//                yLast =e.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.d("opsa","move");
//                final float curX = e.getX();
//                final float curY = e.getY();
//
//                xDistance += Math.abs(curX - xLast);
//                yDistance += Math.abs(curY - yLast);
//
//                if (xDistance>yDistance)
//                    Log.d("opsa","true");
//                    return true;
//        }
//
//        Log.d("opsa","false");
////        return super.onInterceptTouchEvent(e);
//        return false;
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("opsa","rrdispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("opsa","rronInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);

//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("opsa","rronTouchEvent");
//        return false;
//
                switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("opsa","rrdown");
                xDistance = yDistance = 0;
                xLast =ev.getX();
                yLast =ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("opsa","rrmove");
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                if (xDistance>yDistance)
                {Log.d("opsa","rrtrue");
                    return false;}
        }

        return super.onTouchEvent(ev);
//        return false;

    }
}
