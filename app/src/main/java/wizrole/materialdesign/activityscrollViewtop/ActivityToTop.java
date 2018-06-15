package wizrole.materialdesign.activityscrollViewtop;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wizrole.materialdesign.R;

/**
 * Created by 何人执笔？ on 2018/6/15.
 * liushengping
 * Activity中实现标题置顶
 */

public class ActivityToTop extends AppCompatActivity implements View.OnClickListener{

    private int topHeight;
    private int select = 1;//当前选中的下标
    public LinearLayout scroll_title_one;//未置顶
    public LinearLayout scroll_title_two;//已置顶
    public LinearLayout title_detail;//已置顶
    public CustScrollView scrollView;
    public CustListView list_view;
    public TextView tab1_t;
    public TextView tab2_t;
    public TextView tab3_t;
    public TextView tab1_v;
    public TextView tab2_v;
    public TextView tab3_v;
    public RelativeLayout tab3_mian;
    public RelativeLayout tab2_mian;
    public RelativeLayout tab1_mian;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_activitytotop);
        initUI();
        setView();
        setListView();
        setListener();
    }
    public void initUI(){
        scrollView=(CustScrollView)findViewById(R.id.scrollView);
        list_view=(CustListView)findViewById(R.id.list_view);
        tab1_t=(TextView)findViewById(R.id.tab1_t);
        tab2_t=(TextView)findViewById(R.id.tab2_t);
        tab3_t=(TextView)findViewById(R.id.tab3_t);
        tab1_v=(TextView)findViewById(R.id.tab1_v);
        tab2_v=(TextView)findViewById(R.id.tab2_v);
        tab3_v=(TextView)findViewById(R.id.tab3_v);
        title_detail=(LinearLayout) findViewById(R.id.title_detail);
        scroll_title_one=(LinearLayout) findViewById(R.id.scroll_title_one);
        scroll_title_two=(LinearLayout) findViewById(R.id.scroll_title_two);
        tab3_mian=(RelativeLayout) findViewById(R.id.tab3_mian);
        tab2_mian=(RelativeLayout) findViewById(R.id.tab2_mian);
        tab1_mian=(RelativeLayout) findViewById(R.id.tab1_mian);

    }

    private void changeview(int index) {
        tab1_t.setTextColor(Color.parseColor("#333333"));
        tab2_t.setTextColor(Color.parseColor("#333333"));
        tab3_t.setTextColor(Color.parseColor("#333333"));
        tab1_v.setSelected(false);
        tab2_v.setSelected(false);
        tab3_v.setSelected(false);
        if (index == 1) {
            tab1_t.setTextColor(Color.parseColor("#59c2de"));
            tab1_v.setSelected(true);
        } else if (index == 2) {
            tab2_t.setTextColor(Color.parseColor("#59c2de"));
            tab2_v.setSelected(true);
        } else {
            tab3_t.setTextColor(Color.parseColor("#59c2de"));
            tab3_v.setSelected(true);
        }
    }


    public void setView(){
        scrollView.setOnScrollListener(new CustScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollY >= topHeight) {
                    if (title_detail.getParent() != scroll_title_one) {
                        scroll_title_two.removeView(title_detail);
                        scroll_title_one.addView(title_detail);
                    }
                } else {
                    if (title_detail.getParent() != scroll_title_two) {
                        scroll_title_one.removeView(title_detail);
                        scroll_title_two.addView(title_detail);
                    }
                }
            }
        });
    }

    public List<String> strings=new ArrayList<>();
    public void setListView(){
        for(int i=0;i<30;i++){
            strings.add("我是帅哥"+i);
        }
        ScrollViewAdapter adapter=new ScrollViewAdapter(ActivityToTop.this,strings,R.layout.list_item);
        list_view.setAdapter(adapter);
        list_view.setFocusable(false);//解决listView上移距离
        scrollView.smoothScrollTo(0,0);
    }

    public void setListener() {
        tab1_mian.setOnClickListener(this);
        tab2_mian.setOnClickListener(this);
        tab3_mian.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab1_mian:
                select = 1;
                changeview(select);
                break;
            case R.id.tab2_mian:
                select = 2;
                changeview(select);
                break;
            case R.id.tab3_mian:
                select = 3;
                changeview(select);
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        changeview(select);
    }

    /**
     *Activity 中可直接   在此方法中获取布局的实际高度
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            topHeight = scroll_title_two.getBottom() - scroll_title_one.getHeight();
        }
    }
}


    /**
     * 如果在fragment中实现标题置顶，步骤如下
     * （1）、在fragment宿主activity中
     public  LinearLayout main_tab2;
     public  LinearLayout main_tab1;
     public int topHeight;
     @Override
     public void onWindowFocusChanged(boolean hasFocus) {
     super.onWindowFocusChanged(hasFocus);
     if (hasFocus) {
     topHeight = main_tab2.getBottom() - main_tab1.getHeight();
     }
     }

     public int getTopHeight() {
     return topHeight;
     }

     @Override
     public void getView(LinearLayout linearLayout_one, LinearLayout linearLayout_two) {
     main_tab1=linearLayout_one;
     main_tab2=linearLayout_two;
     }

     （2）、在fragment中
     public GetView getView;
     public interface GetView {
     void getView(LinearLayout linearLayout_one, LinearLayout linearLayout_two);
     }
     @Override
     public void onAttach(Context context) {
     super.onAttach(context);
     getView=(GetView)context;
     }


     myscroview.setOnScrollListener(new MyScroview.OnScrollListener() {
     @Override
     public void onScroll(int scrollY) {
     if (scrollY >= ((MainActivity)getActivity()).getTopHeight()) {
     if (tab_mian.getParent() != main_tab1) {
     main_tab2.removeView(tab_mian);
     main_tab1.addView(tab_mian);
     //滑动到顶端
     scrollTotop=true;
     }
     } else {
     scrollTotop=false;
     if (tab_mian.getParent() != main_tab2) {
     main_tab1.removeView(tab_mian);
     main_tab2.addView(tab_mian);
     }
     }
     }
     });
     getView.getView(main_tab1,main_tab2);
     }

     即可
     */


