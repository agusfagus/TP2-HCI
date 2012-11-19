package com.example.fly.utils;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.example.fly.R;
import com.example.fly.mobile.MainFragment;
import com.example.fly.mobile.NotificationFragment;
import com.example.fly.mobile.ReviewFragment;
import com.example.fly.mobile.SearchFlightFragment;
import com.example.fly.mobile.SplashActivity;

public class FragmentTabHandler implements ActionBar.TabListener {
	
	private FragmentContainer fragmentList[] = {
			new FragmentContainer(R.string.deals_tab, new SearchFlightFragment()),
			new FragmentContainer(R.string.reviews_tab, new ReviewFragment()),
			new FragmentContainer(R.string.notifications_tab, new NotificationFragment())
	};
	
	private FragmentContainer mainFragment = new FragmentContainer(R.drawable.ic_launcher_home, new MainFragment());
	
	private SplashActivity contextActivity;
	
	public FragmentTabHandler(Activity contextActivity) {
		this.contextActivity = (SplashActivity)contextActivity;
		initialize();
		selectMain();
	}
	
	public void selectMain() {
		toggle(mainFragment.instance);
		this.contextActivity.getActionBar().selectTab(null);
	}
	
	public void onTabReselected(Tab tab, FragmentTransaction ft) { }

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Get fragment's instance
		Fragment fragmentSelected = fragmentList[tab.getPosition()].instance;
    	toggle(fragmentSelected);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) { }
	
	private void initialize() {
		final ActionBar actionBar = this.contextActivity.getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (int i = 0 ; i < fragmentList.length ; i++)
			actionBar.addTab(actionBar.newTab().setText(fragmentList[i].stringId).setTabListener(this));
	}
	
	public void toggle(Fragment newFragment) {
    	FragmentManager fManager = this.contextActivity.getFragmentManager();
    	fManager.beginTransaction().replace(R.id.container, newFragment).commit();
    }
	
	private class FragmentContainer {
		
		private Fragment instance;
		private int stringId;
		
		public FragmentContainer(Fragment inst) {
			this.instance = inst;
		}
		
		public FragmentContainer(int id, Fragment inst) {
			this(inst);
			this.stringId = id;
		}
	}
}
