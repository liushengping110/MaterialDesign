package wizrole.materialdesign.activityscrollViewtop;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by liushengping on 2018/3/15.
 * 何人执笔？
 */
public class CustListView extends ListView {

    public CustListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustListView(Context context) {
        super(context);
    }

    public CustListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
