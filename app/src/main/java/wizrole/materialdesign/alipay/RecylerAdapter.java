package wizrole.materialdesign.alipay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wizrole.materialdesign.R;

/**
 * Created by liushengping on 2018/2/28.
 * 何人执笔？
 */
public class RecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;

    public RecylerAdapter(Context context){
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
           return new ItemView(LayoutInflater.from(context).inflate(R.layout.shenghuo_head2,null));
        }else{
           return new ItemView(LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else{
            return 1;
        }
    }

    class ItemView extends RecyclerView.ViewHolder{
        public ItemView(View itemView) {
            super(itemView);
        }
    }

}
