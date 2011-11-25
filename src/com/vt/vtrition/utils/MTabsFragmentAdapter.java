package com.vt.vtrition.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.TitleProvider;
import com.vt.vtrition.MFragment;

public class MTabsFragmentAdapter extends FragmentPagerAdapter implements TitleProvider {

	protected static final int[] CONTENT = new int[] { 0, 1, 2 }; 
	protected static final String[] NAMES = new String[] { "Home", "Days", "Recipes" };
//	protected static final String[] CONTENT = new String[] { "Hello This", "Is", "Dog"};
	
	private int mCount = CONTENT.length;
    
	public MTabsFragmentAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int position) {
		return MFragment.newInstance(CONTENT[position % CONTENT.length]);
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public String getTitle(int position) {
		return NAMES[position];
	}
}
