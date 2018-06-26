package wizrole.materialdesign.likeUc.behavior.uc;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

import wizrole.materialdesign.BuildConfig;
import wizrole.materialdesign.R;
import wizrole.materialdesign.likeUc.MyApplication;
import wizrole.materialdesign.likeUc.behavior.helper.HeaderScrollingViewBehavior;


/**
 * 新闻tab behavior
 * Copy from chensuilun
 */


public class UcNewsTabBehavior extends HeaderScrollingViewBehavior {
    private static final String TAG = "UcNewsTabBehavior";

    public UcNewsTabBehavior() {
    }

    public UcNewsTabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void layoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        super.layoutChild(parent, child, layoutDirection);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "layoutChild:top" + child.getTop() + ",height" + child.getHeight());
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDependentViewChanged: dependency.getTranslationY():"+dependency.getTranslationY());
        }
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        float offsetRange = dependency.getTop() + getFinalHeight() - child.getTop();
        int headerOffsetRange = getHeaderOffsetRange();
        if (dependency.getTranslationY() == headerOffsetRange) {
            child.setTranslationY(offsetRange);
        } else if (dependency.getTranslationY() == 0) {
            child.setTranslationY(0);
        } else {
            child.setTranslationY((int) (dependency.getTranslationY() / (getHeaderOffsetRange() * 1.0f) * offsetRange));
        }
    }


    @Override
    protected View findFirstDependency(List<View> views) {
        for (int i = 0, z = views.size(); i < z; i++) {
            View view = views.get(i);
            if (isDependOn(view))
                return view;
        }
        return null;
    }

    private int getHeaderOffsetRange() {
        return MyApplication.getAppContext().getResources().getDimensionPixelOffset(R.dimen.uc_news_header_pager_offset);
    }

    private int getFinalHeight() {
        return MyApplication.getAppContext().getResources().getDimensionPixelOffset(R.dimen.uc_news_header_title_height);
    }


    private boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.id_uc_news_header_pager;
    }
}
