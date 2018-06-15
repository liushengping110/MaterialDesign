package wizrole.materialdesign.activityscrollViewtop;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by liushengping on 2018/3/15.
 * 何人执笔？
 */

public class CustScrollView extends NestedScrollView {
    private OnScrollListener onScrollListener;
    /**
     * 主要是用在用户手指离开CustScroview，CustScroview还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;
    private int downX;
    private int downY;
    private int mTouchSlop;

    public CustScrollView(Context context) {
        super(context, null);
    }

    public CustScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public CustScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScrollListener != null) {
            onScrollListener.onScroll(y);
        }
    }


    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回CustScroview滑动的Y方向距离
         */
        void onScroll(int scrollY);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
}
