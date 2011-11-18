package com.vt.vtrition;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.sample.TestTitleFragmentAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class VTritionActivity extends Activity {
    /** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_titles);
		
		mAdapter = new TestTitleFragmentAdapter(getSupportFragmentManager());
		
		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		
		TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
		indicator.setViewPager(mPager);
	}
}