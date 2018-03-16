package wizrole.materialdesign.title;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;

public class VFAdapter extends PagerAdapter{
	private List<Fragment> fragments;
	private FragmentManager fm;
	private ViewPager viewPager;
	
	public VFAdapter(List<Fragment> fragments, FragmentManager fm,
			ViewPager viewPager) {
		super();
		this.fragments = fragments;
		this.fm = fm;
		this.viewPager = viewPager;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(fragments.get(position).getView());
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment=fragments.get(position);
		if (!fragment.isAdded()) {
			FragmentTransaction transaction=fm.beginTransaction();
			transaction.add(fragment, fragment.getClass().getSimpleName());
			transaction.commit();
			fm.executePendingTransactions();
		}
		View view=fragment.getView();
		if (view.getParent()==null) {
			container.addView(view);
		}
		return view;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

	

}
