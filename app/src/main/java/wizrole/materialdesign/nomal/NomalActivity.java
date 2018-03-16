package wizrole.materialdesign.nomal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;

import wizrole.materialdesign.R;

/**
 * Created by liushengping on 2018/3/1.
 * 何人执笔？
 */

public class NomalActivity extends AppCompatActivity {

    public Toolbar toolbar;
    private View mToolbar1=null;
    private View mToolbar2=null;
    private AppBarLayout mAppBarLayout=null;

    public TextView text_title;
    public LinearLayout lin_befor,lin_after;
    public ImageView img_left_befor,img_home_befor,img_share_befor,img_left_after,img_home_after,img_share_after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_nomal);
        initUI();
        setListener();
    }

    public void initUI(){
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        mAppBarLayout=(AppBarLayout)findViewById(R.id.appBarLayout);

        View view=toolbar.getChildAt(0);
        img_left_befor=(ImageView)view.findViewById(R.id.img_left_befor);
        img_left_after=(ImageView)view.findViewById(R.id.img_left_after);
        lin_after=(LinearLayout)view.findViewById(R.id.lin_after);
        lin_befor=(LinearLayout)view.findViewById(R.id.lin_befor);
        img_left_befor=(ImageView)view.findViewById(R.id.img_left_befor);
        img_home_befor=(ImageView)view.findViewById(R.id.img_home_befor);
        img_share_befor=(ImageView)view.findViewById(R.id.img_share_befor);
        img_left_after=(ImageView)view.findViewById(R.id.img_left_after);
//        img_left_after.setAlpha(255);
        img_home_after=(ImageView)view.findViewById(R.id.img_home_after);
//        img_home_after.setAlpha(255);
        img_share_after=(ImageView)view.findViewById(R.id.img_share_after);
//        img_share_after.setAlpha(255);
        text_title=(TextView)view.findViewById(R.id.text_title);
//        text_title.setTextColor(Color.argb(255,255,255,255));
    }

    public void setListener(){
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.bule),Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange()));
//                int ban=Math.abs(appBarLayout.getTotalScrollRange())/2;
//                if(Math.abs(verticalOffset)<ban){//先隐藏befor
//                    setToolbar1Alpha(255-(Math.abs(verticalOffset)*255/ban));
//                }else{//再显示after
//                    setToolbar2Alpha(Math.abs(verticalOffset)*255/ban);
//                }


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
                        Log.e("alpha",alpha+"");
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


    /** 根据百分比改变颜色透明度 */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
}
