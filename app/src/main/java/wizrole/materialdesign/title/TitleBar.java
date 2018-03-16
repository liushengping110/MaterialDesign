package wizrole.materialdesign.title;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import wizrole.materialdesign.R;

public class TitleBar extends LinearLayout {
	private int count;
	private Context context;
	private ViewPager viewPager;
	private LinearLayout ll_btn;
	private LinearLayout ll_line;
	public int width;
	public boolean status=false;//状态
	public TitleBar(Context context, int count, ViewPager viewPager,LinearLayout ll_btn, LinearLayout ll_line) {
		super(context);
		this.count=count;
		this.context=context;
		this.viewPager=viewPager;
		this.ll_btn=ll_btn;
		this.ll_line=ll_line;
	}

	public View initLinear(){
		View lineView=null;
		width=((Activity)context).getWindowManager().getDefaultDisplay().getWidth();
//		LayoutParams btn_lp=new LayoutParams(width/count, LayoutParams.WRAP_CONTENT);
//		LayoutParams line_lp=new LayoutParams(width/count, 5);
		LayoutParams btn_lp=new LayoutParams(250, LayoutParams.MATCH_PARENT);
		LayoutParams line_lp=new LayoutParams(250, 5);
		btn_lp.setMargins(0, 20, 0, 20);
		for (int i = 0; i < count; i++) {
			CustTextView button=new CustTextView(context);
			button.setLayoutParams(btn_lp);
			button.setGravity(Gravity.CENTER);
			if (i==0){
				button.setText("刘胜平");
			}else{
				button.setText("大帅哥");
			}
			ll_btn.addView(button);
			if (i==0) {
				button.setTextColor(Color.RED);
			}
			button.setOnClickListener(new BtnClickListener(i));
			View view=new View(context);
			view.setLayoutParams(line_lp);
			if (i==0) {
				view.setBackgroundColor(Color.RED);
				lineView=view;
			}
			ll_line.addView(view);
		}
		return lineView;
	}


	class BtnClickListener implements OnClickListener{
		private int position;
		public BtnClickListener(int position) {
			this.position=position;
		}
		@Override
		public void onClick(View v) {
			changeBg(status,position);
			viewPager.setCurrentItem(position);
		}
	}


	/**
	 *折叠前
	 * 折叠后
	 * 改变背景
	 * @param stat
	 * @param po
	 */
	public void changeBg(boolean stat,int po){
		status=stat;
		if(stat) {//折叠后
			ll_line.setVisibility(View.GONE);
			for (int i = 0; i < ll_btn.getChildCount(); i++) {
				TextView button=(TextView)ll_btn.getChildAt(i);
				if(i==0){//左边
					if(i==po){//选中了左边
						button.setBackground(getResources().getDrawable(R.drawable.tab_left_sel_bg));
						button.setTextColor(Color.RED);
					}else{//没选中左边
						button.setBackground(getResources().getDrawable(R.drawable.tab_left_no_sel));
						button.setTextColor(Color.WHITE);
					}
				}else{//右边
					if(i==po){//选中右边
						button.setBackground(getResources().getDrawable(R.drawable.tab_right_sel_bg));
						button.setTextColor(Color.RED);
					}else{//没选中右边
						button.setBackground(getResources().getDrawable(R.drawable.tab_right_no_sel));
						button.setTextColor(Color.WHITE);
					}
				}
			}
		}else{//折叠前
			ll_line.setVisibility(View.VISIBLE);
			for (int i = 0; i < ll_btn.getChildCount(); i++) {
				TextView button=(TextView)ll_btn.getChildAt(i);
				if (po==i){
					button.setTextColor(Color.RED);
				}else{
					button.setTextColor(Color.BLACK);
				}
				button.setBackground(null);
			}
		}
	}
}
