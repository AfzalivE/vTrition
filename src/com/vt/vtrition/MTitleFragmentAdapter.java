package com.vt.vtrition;

import android.support.v4.app.FragmentManager;
import com.viewpagerindicator.TitleProvider;

class MTitleFragmentAdapter extends MFragmentAdapter implements TitleProvider {
	public MTitleFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public String getTitle(int position) {
		return MFragmentAdapter.CONTENT[position % CONTENT.length];
	}
}