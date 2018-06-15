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
	private LinearLayout lin_after_text;
	public int width;
	public boolean status=false;//状态
	public TitleBar(Context context, int count, ViewPager viewPager,LinearLayout ll_btn, LinearLayout ll_line,LinearLayout lin_after_text ) {
		super(context);
		this.count=count;
		this.context=context;
		this.viewPager=viewPager;
		this.ll_btn=ll_btn;
		this.ll_line=ll_line;
		this.lin_after_text=lin_after_text;

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
				button.setText("商品列表");
			}else{
				button.setText("商家信息");
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

		//设置折叠后的布局
		for (int i = 0; i < count; i++) {
			CustTextView button=new CustTextView(context);
			button.setLayoutParams(btn_lp);
			button.setGravity(Gravity.CENTER);
			if (i==0){//默认选中
				button.setText("商品列表");
				button.setBackgroundResource(R.drawable.tab_left_sel_bg);
				button.setTextColor(Color.RED);
			}else{
				button.setText("商家信息");
				button.setBackgroundResource(R.drawable.tab_right_no_sel);
				button.setTextColor(Color.WHITE);
			}
			lin_after_text.addView(button);
			if (i==0) {
				button.setBackgroundResource(R.drawable.tab_left_sel_bg);
				button.setTextColor(Color.RED);
			}
			button.setOnClickListener(new BtnClickListener(i));
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
			changeBg(position);
			viewPager.setCurrentItem(position);
		}
	}


	/**
	 *折叠前
	 * 折叠后
	 * 改变背景
	 * @param po 当前页面的下表
	 */
	public void changeBg(int po){
			for (int i = 0; i < lin_after_text.getChildCount(); i++) {
				TextView button=(TextView)lin_after_text.getChildAt(i);
				if(i==0){//左边
                    button.setText("商品列表");
					if(i==po){//选中了左边
						button.setBackgroundResource(R.drawable.tab_left_sel_bg);
						button.setTextColor(Color.RED);
					}else{//没选中左边
						button.setBackgroundResource(R.drawable.tab_left_no_sel);
						button.setTextColor(Color.WHITE);
					}
				}else{//右边
                    button.setText("商家信息");
					if(i==po){//选中右边
						button.setBackgroundResource(R.drawable.tab_right_sel_bg);
						button.setTextColor(Color.RED);
					}else{//没选中右边
						button.setBackgroundResource(R.drawable.tab_right_no_sel);
						button.setTextColor(Color.WHITE);
					}
				}
			}
			for (int i = 0; i < ll_btn.getChildCount(); i++) {
				TextView button=(TextView)ll_btn.getChildAt(i);
				if (po==i){
					button.setTextColor(Color.RED);
				}else{
					button.setTextColor(Color.BLACK);
				}
		}
	}
}
