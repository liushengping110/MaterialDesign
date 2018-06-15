package wizrole.materialdesign.title;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wizrole.materialdesign.R;

/**
 * Created by liushengping on 2018/3/2.
 * 何人执笔？
 */

public class TitleActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    public Toolbar toolbar;
    public TitleBar titleBar;
    public ViewPager view_pager;
    public View view,lineView;
    private AppBarLayout mAppBarLayout;
    private int currentPosition=0;
    public LinearLayout lin_after_text;
    public LinearLayout lin_text;
    public LinearLayout lin_line;
    public LinearLayout lin_befor,lin_after;
    public ImageView img_left_befor,img_home_befor,img_share_befor,img_left_after,img_home_after,img_share_after;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_title);
        initUI();
        setView();
        setListener();
    }

    public void initUI(){
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        mAppBarLayout=(AppBarLayout)findViewById(R.id.appbarLayout);
        view_pager=(ViewPager)findViewById(R.id.view_pager);
        //自定义ToolBar
        View v=toolbar.getChildAt(0);
        img_left_befor=(ImageView)v.findViewById(R.id.img_left_befor);
        img_left_after=(ImageView)v.findViewById(R.id.img_left_after);
        lin_after=(LinearLayout)v.findViewById(R.id.lin_after);
        lin_befor=(LinearLayout)v.findViewById(R.id.lin_befor);
        img_left_befor=(ImageView)v.findViewById(R.id.img_left_befor);
        img_home_befor=(ImageView)v.findViewById(R.id.img_home_befor);
        img_share_befor=(ImageView)v.findViewById(R.id.img_share_befor);
        img_left_after=(ImageView)v.findViewById(R.id.img_left_after);
        img_home_after=(ImageView)v.findViewById(R.id.img_home_after);
        img_share_after=(ImageView)v.findViewById(R.id.img_share_after);
        //折叠后的标题栏
        lin_after_text=(LinearLayout) findViewById(R.id.lin_after_text);
        //折叠前的标题栏
        lin_text=(LinearLayout) findViewById(R.id.lin_text);
        lin_line=(LinearLayout) findViewById(R.id.lin_line);
    }
    public List<Fragment> fragments;
    public void setView(){
        fragments=new ArrayList<>();
        fragments.add(new Fragment_one());
        fragments.add(new Fragment_one());
        view_pager.setAdapter(new VFAdapter(fragments, getSupportFragmentManager(), view_pager));
        view_pager.setOnPageChangeListener(this);
        titleBar =new TitleBar(this,fragments.size(),view_pager,lin_text,lin_line,lin_after_text);
        lineView= titleBar.initLinear();
    }

    public void setListener(){
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                a=appBarLayout.getTotalScrollRange();
                toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.red),Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange()));
                if (verticalOffset == 0){
                    titleBar.status=false;
                    //张开
                    lin_befor.setVisibility(View.VISIBLE);
                    lin_after.setVisibility(View.GONE);
                    setToolbar1Alpha(255);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    titleBar.status=true;
                    //收缩
                    lin_befor.setVisibility(View.GONE);
                    lin_after.setVisibility(View.VISIBLE);
                    setToolbar2Alpha(255);
                } else {
                    int alpha=(Math.abs(verticalOffset)*255)/a;
                    if(now_vert<Math.abs(verticalOffset)){//在折叠的过程中
                        setToolbar1Alpha(255-alpha);
                        setToolbar2Alpha(alpha);
                    }else{//在展开的过程中
                        setToolbar1Alpha(alpha);
                        setToolbar2Alpha(alpha);
                    }
                    if(Math.abs(verticalOffset)<Math.abs(a)/2){
                        lin_after_text.setVisibility(View.INVISIBLE);
                    }else {
                        lin_after_text.setVisibility(View.VISIBLE);
                    }

                }
                now_vert=Math.abs(verticalOffset);//j记录上一次得滑动高度
            }
        });
    }
    public int now_vert=0;//当前记录的折叠距离
    public int a=0;//当前折叠布局总高度

    //设置展开时各控件的透明度
    public void setToolbar1Alpha(int alpha){
        img_home_befor.getDrawable().setAlpha(alpha);
        img_left_befor.getDrawable().setAlpha(alpha);
        img_share_befor.getDrawable().setAlpha(alpha);
        lin_line.setAlpha(alpha);
        lin_text.setAlpha(alpha);
    }

    //设置闭合时各控件的透明度
    public void setToolbar2Alpha(int alpha){
        img_home_after.getDrawable().setAlpha(alpha);
        img_left_after.getDrawable().setAlpha(alpha);
        img_share_after.getDrawable().setAlpha(alpha);
        for (int i=0;i<lin_after_text.getChildCount();i++){
            lin_after_text.getChildAt(i).setAlpha(alpha);
        }
    }


    /** 根据百分比改变颜色透明度 */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        titleBar.changeBg(position);
        fragments.get(currentPosition).onPause();
        Fragment fragment=fragments.get(position);
        if (fragment.isAdded()) {
            fragment.onResume();
        }
        Animation animation = new TranslateAnimation(lin_line.getChildAt(currentPosition).getX(), lin_line.getChildAt(position).getX(), 0, 0);
        animation.setDuration(100);
        animation.setFillAfter(true);
        lineView.startAnimation(animation);
        currentPosition=position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
