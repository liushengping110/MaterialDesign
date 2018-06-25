package wizrole.materialdesign.like58;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 何人执笔？ on 2018/6/25.
 * liushengping
 */

public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    boolean canScroll=true;

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (canScroll){
            return super.onTouchEvent(e);
        }else{
            return true;
        }
        
    }
}
