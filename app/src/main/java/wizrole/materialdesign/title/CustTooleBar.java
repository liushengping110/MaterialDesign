package wizrole.materialdesign.title;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import wizrole.materialdesign.R;

/**
 * Created by 何人执笔？ on 2018/6/15.
 * liushengping
 */

public class CustTooleBar extends Toolbar {

    public CustTooleBar(Context context) {
        super(context);
    }

    public CustTooleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustTooleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.title_actionbar, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        addView(view, layoutParams);
//        View view_after = LayoutInflater.from(getContext()).inflate(R.layout.action_bar_nomal_after, null);
//        layoutParams.gravity = Gravity.CENTER;
//        addView(view_after, layoutParams);
    }
}
