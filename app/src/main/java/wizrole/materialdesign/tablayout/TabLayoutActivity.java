package wizrole.materialdesign.tablayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import wizrole.materialdesign.R;

/**
 * Created by liushengping on 2018/3/2.
 * 何人执笔？
 */

public class TabLayoutActivity extends AppCompatActivity {


    public TabLayout tabLayout;
    public ViewPager viewPager;
    public AppBarLayout appBarLayout;
    public TextView text_title;
    public LinearLayout lin_befor,lin_after;
    public Toolbar toolbar;
    public ImageView img_left_befor,img_home_befor,img_share_befor,img_left_after,img_home_after,img_share_after;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_tablayout);
        initUI();
        setView();
        setListener();
    }

    public void initUI(){
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        appBarLayout=(AppBarLayout)findViewById(R.id.appBarLayout);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        View view=toolbar.getChildAt(0);
        img_left_befor=(ImageView)view.findViewById(R.id.img_left_befor);
        img_left_after=(ImageView)view.findViewById(R.id.img_left_after);
        lin_after=(LinearLayout)view.findViewById(R.id.lin_after);
        lin_befor=(LinearLayout)view.findViewById(R.id.lin_befor);
        img_left_befor=(ImageView)view.findViewById(R.id.img_left_befor);
        img_home_befor=(ImageView)view.findViewById(R.id.img_home_befor);
        img_share_befor=(ImageView)view.findViewById(R.id.img_share_befor);
        img_left_after=(ImageView)view.findViewById(R.id.img_left_after);
        img_home_after=(ImageView)view.findViewById(R.id.img_home_after);
        img_share_after=(ImageView)view.findViewById(R.id.img_share_after);
        text_title=(TextView)view.findViewById(R.id.text_title);
    }
    public void setView(){
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new Fragment_one();
                    case 1:
                        return new Fragment_one();
                    case 2:
                        return new Fragment_one();
                    default:
                        return new Fragment_one();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "消息";
                    case 1:
                        return "动态";
                    case 2:
                        return "我的";
                    default:
                        return "未知";
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
    public void setListener(){
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0){
                    //张开
                    lin_befor.setVisibility(View.VISIBLE);
                    lin_after.setVisibility(View.GONE);
                    setToolbar1Alpha(255);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    //收缩
                    lin_befor.setVisibility(View.GONE);
                    lin_after.setVisibility(View.VISIBLE);
                    setToolbar2Alpha(255);
                } else {
                    int alpha=255- Math.abs(verticalOffset);
                    if(alpha<0){
                        //收缩toolbar
                        lin_befor.setVisibility(View.GONE);
                        lin_after.setVisibility(View.VISIBLE);
                        setToolbar2Alpha(Math.abs(verticalOffset));
                    }else{
                        //张开toolbar
                        lin_befor.setVisibility(View.VISIBLE);
                        lin_after.setVisibility(View.GONE);
                        setToolbar1Alpha(alpha);
                    }
                }
            }
        });
    }


    //设置展开时各控件的透明度
    public void setToolbar1Alpha(int alpha){
        img_home_befor.getDrawable().setAlpha(alpha);
        img_left_befor.getDrawable().setAlpha(alpha);
        img_share_befor.getDrawable().setAlpha(alpha);
    }

    //设置闭合时各控件的透明度
    public void setToolbar2Alpha(int alpha){
        img_home_after.getDrawable().setAlpha(alpha);
        img_left_after.getDrawable().setAlpha(alpha);
        img_share_after.getDrawable().setAlpha(alpha);
        text_title.setTextColor(Color.argb(alpha,255,255,255));

    }
}
