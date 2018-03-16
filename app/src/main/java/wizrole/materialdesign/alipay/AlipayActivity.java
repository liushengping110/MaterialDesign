package wizrole.materialdesign.alipay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import wizrole.materialdesign.R;

/**
 * Created by liushengping on 2018/2/28.
 * 何人执笔？
 */
public class AlipayActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout=null;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private View mToolbar1=null;
    private View mToolbar2=null;

    private ImageView mZhangdan=null;
    private TextView mZhangdan_txt=null;
    private ImageView mTongxunlu=null;
    private ImageView mJiahao=null;

    private ImageView mZhangdan2=null;
    private ImageView mShaoyishao=null;
    private ImageView mSearch=null;
    private ImageView mZhaoxiang=null;
    public Toolbar toolbar;

    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_scrolling);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        myRecyclerView=(RecyclerView)findViewById(R.id.myRecyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.color1984D1));

        myRecyclerView.setAdapter(new RecylerAdapter(this));

        mAppBarLayout=(AppBarLayout)findViewById(R.id.app_bar);
        mToolbar1=(View)findViewById(R.id.toolbar1);
        mToolbar2=(View)findViewById(R.id.toolbar2);

        mZhangdan=(ImageView)findViewById(R.id.img_zhangdan);
        mZhangdan_txt=(TextView)findViewById(R.id.img_zhangdan_txt);
        mTongxunlu=(ImageView)findViewById(R.id.tongxunlu);
        mJiahao=(ImageView)findViewById(R.id.jiahao);

        mZhangdan2=(ImageView)findViewById(R.id.img_shaomiao);
        mShaoyishao=(ImageView)findViewById(R.id.img_fukuang);
        mSearch=(ImageView)findViewById(R.id.img_search);
        mZhaoxiang=(ImageView)findViewById(R.id.img_zhaoxiang);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0){
                    //张开
                    mToolbar1.setVisibility(View.VISIBLE);
                    mToolbar2.setVisibility(View.GONE);
                    setToolbar1Alpha(255);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //收缩
                    mToolbar1.setVisibility(View.GONE);
                    mToolbar2.setVisibility(View.VISIBLE);
                    setToolbar2Alpha(255);
                } else {
                    int alpha=255- Math.abs(verticalOffset);
                    if(alpha<0){
                        Log.e("alpha",alpha+"");
                        //收缩toolbar
                        mToolbar1.setVisibility(View.GONE);
                        mToolbar2.setVisibility(View.VISIBLE);
                        setToolbar2Alpha(Math.abs(verticalOffset));
                    }else{
                        //张开toolbar
                        mToolbar1.setVisibility(View.VISIBLE);
                        mToolbar2.setVisibility(View.GONE);
                        setToolbar1Alpha(alpha);
                    }
                }
            }
        });
    }

    //设置展开时各控件的透明度
    public void setToolbar1Alpha(int alpha){
        mZhangdan.getDrawable().setAlpha(alpha);
        mZhangdan_txt.setTextColor(Color.argb(alpha,255,255,255));
        mTongxunlu.getDrawable().setAlpha(alpha);
        mJiahao.getDrawable().setAlpha(alpha);
    }

    //设置闭合时各控件的透明度
    public void setToolbar2Alpha(int alpha){
        mZhangdan2.getDrawable().setAlpha(alpha);
        mShaoyishao.getDrawable().setAlpha(alpha);
        mSearch.getDrawable().setAlpha(alpha);
        mZhaoxiang.getDrawable().setAlpha(alpha);
    }


}
