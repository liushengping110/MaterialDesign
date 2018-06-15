package wizrole.materialdesign.activityscrollViewtop.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by liushengping on 2016/11/27.
 * 何人执笔？
 */

public abstract class ConcreteAdapter<T> extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<T> list;
    private int itemLayout;

    public ConcreteAdapter(Context context, List<T> list, int itemLayout) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        this.itemLayout = itemLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(convertView, parent);
        convert(viewHolder, (T) getItem(position), position);
        return viewHolder.getHoldView();
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

    private ViewHolder getViewHolder(View view, ViewGroup viewGroup) {
        return ViewHolder.get(context, view, viewGroup, itemLayout);
    }

}