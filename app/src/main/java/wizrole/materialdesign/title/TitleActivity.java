package wizrole.materialdesign.title;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wizrole.materialdesign.R;

/**
 * Created by liushengping on 2018/3/2.
 * 何人执笔？
 */

public class TitleActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    public TitleBar titleBar;
    public ViewPager view_pager;
    public View view,lineView;
    private AppBarLayout mAppBarLayout;
    public TextView text_title;
    private int currentPosition=0;
    public LinearLayout lin_tool_title;
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
        view=(View)findViewById(R.id.view);
        mAppBarLayout=(AppBarLayout)findViewById(R.id.appbarLayout);
        view_pager=(ViewPager)findViewById(R.id.view_pager);
        img_left_befor=(ImageView)findViewById(R.id.img_left_befor);
        img_left_after=(ImageView)findViewById(R.id.img_left_after);
        lin_after=(LinearLayout)findViewById(R.id.lin_after);
        lin_befor=(LinearLayout)findViewById(R.id.lin_befor);
        img_left_befor=(ImageView)findViewById(R.id.img_left_befor);
        img_home_befor=(ImageView)findViewById(R.id.img_home_befor);
        img_share_befor=(ImageView)findViewById(R.id.img_share_befor);
        img_left_after=(ImageView)findViewById(R.id.img_left_after);
        img_home_after=(ImageView)findViewById(R.id.img_home_after);
        img_share_after=(ImageView)findViewById(R.id.img_share_after);
        text_title=(TextView)findViewById(R.id.text_title);
        lin_tool_title=(LinearLayout) findViewById(R.id.lin_tool_title);
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
        titleBar =new TitleBar(this,fragments.size(),view_pager,lin_text,lin_line);
        lineView= titleBar.initLinear();
    }

    public void setListener(){
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                view.setBackgroundColor(changeAlpha(getResources().getColor(R.color.red),Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange()));
                lin_tool_title.setBackgroundColor(changeAlpha(getResources().getColor(R.color.red),Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange()));
                int ban=mAppBarLayout.getTotalScrollRange()/2;
                if(ban<Math.abs(verticalOffset)){//折叠后
                    titleBar.status=true;
                    lin_after.setVisibility(View.VISIBLE);
                    lin_befor.setVisibility(View.GONE);
                    titleBar.changeBg(true,currentPosition);
                }else{//折叠前
                    titleBar.status=false;
                    lin_after.setVisibility(View.GONE);
                    lin_befor.setVisibility(View.VISIBLE);
                    titleBar.changeBg(false,currentPosition);
                }
            }
        });
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
        titleBar.changeBg(titleBar.status,position);
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
