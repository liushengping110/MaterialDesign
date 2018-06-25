package wizrole.materialdesign.like58;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import wizrole.materialdesign.R;

/**
 * Created by 何人执笔？ on 2018/6/25.
 * liushengping
 */

public class Like58Activity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= 19) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
//        }
        setContentView(R.layout.activity_like58);
        initUI();
    }

    public void initUI(){
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(getLayoutInflater().inflate(R.layout.item, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh = (ViewHolder) holder;
                vh.text.setText("  Item " + (position + 1));
                vh.text2.setText("  Do one thing at a time, and do well.");
            }

            @Override
            public int getItemCount() {
                return 20;
            }

            class ViewHolder extends RecyclerView.ViewHolder {

                TextView text;
                TextView text2;

                public ViewHolder(View itemView) {
                    super(itemView);

                    text = (TextView) itemView.findViewById(R.id.text);
                    text2 = (TextView) itemView.findViewById(R.id.text2);
                }

            }
        });

        final MyBehavior myBehavior = new MyBehavior(this);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) recyclerView.getLayoutParams();
        params.setBehavior(myBehavior);

        myBehavior.setRefreshListener(new MyBehavior.RefreshListener(){
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myBehavior.stopRefresh();
                    }
                },2000);

            }
        });
    }
}
