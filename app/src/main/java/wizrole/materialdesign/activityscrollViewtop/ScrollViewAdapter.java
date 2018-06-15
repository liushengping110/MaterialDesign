package wizrole.materialdesign.activityscrollViewtop;

import android.content.Context;

import java.util.List;

import wizrole.materialdesign.R;
import wizrole.materialdesign.activityscrollViewtop.base.ConcreteAdapter;
import wizrole.materialdesign.activityscrollViewtop.base.ViewHolder;


/**
 * Created by liushengping on 2018/3/15.
 * 何人执笔？
 */

public class ScrollViewAdapter extends ConcreteAdapter<String> {
    public ScrollViewAdapter(Context context, List<String> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(item, R.id.item_name);
    }
}
