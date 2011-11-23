package com.vt.vtrition;

import java.util.Random;

import com.viewpagerindicator.R;
import com.vt.vtrition.utils.ActionBarHelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity {
	
    final ActionBarHelper mActionBarHelper = ActionBarHelper.createInstance(this);

    /**
     * Returns the {@link ActionBarHelper} for this activity.
     */
    protected ActionBarHelper getActionBarHelper() {
        return mActionBarHelper;
    }

    /**{@inheritDoc}*/
    @Override
    public MenuInflater getMenuInflater() {
        return mActionBarHelper.getMenuInflater(super.getMenuInflater());
    }

    /**{@inheritDoc}*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBarHelper.onCreate(savedInstanceState);
    }

    /**{@inheritDoc}*/
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarHelper.onPostCreate(savedInstanceState);
    }

    /**
     * Base action bar-aware implementation for
     * {@link Activity#onCreateOptionsMenu(android.view.Menu)}.
     *
     * Note: marking menu items as invisible/visible is not currently supported.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean retValue = false;
        retValue |= mActionBarHelper.onCreateOptionsMenu(menu);
        retValue |= super.onCreateOptionsMenu(menu);
        return retValue;
    }

    
	private static final Random RANDOM = new Random();
	
	MFragmentAdapter mAdapter;
	ViewPager mPager;

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu, menu);
//
//        // Calling super after populating the menu is necessary here to ensure that the
//        // action bar helpers have a chance to handle this event.
//        return super.onCreateOptionsMenu(menu);
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_refresh:
				final int page = RANDOM.nextInt(mAdapter.getCount());
				Toast.makeText(this, "Changing to page " + page, Toast.LENGTH_SHORT);
				mPager.setCurrentItem(page);
				return true;
				
			case R.id.menu_search:
				if (mAdapter.getCount() < 10) {
					mAdapter.setCount(mAdapter.getCount() + 1);
				}
				return true;
				
			case R.id.menu_share:
				if (mAdapter.getCount() > 1) {
					mAdapter.setCount(mAdapter.getCount() - 1);
				}
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
