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
			new FragmentContainer("Vuelos", new SearchFlightFragment()),
			new FragmentContainer("Opiniones", new ReviewFragment()),
			new FragmentContainer("Notificaciones", new NotificationFragment())
	};
	
	private FragmentContainer mainFragment = new FragmentContainer(R.drawable.ic_launcher_home, new MainFragment());
	
	private SplashActivity contextActivity;
	
	public FragmentTabHandler(Activity contextActivity) {
		this.contextActivity = (SplashActivity)contextActivity;
		initialize();
		toggle(mainFragment.instance);
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
			actionBar.addTab(actionBar.newTab().setText(fragmentList[i].text).setTabListener(this));
	}
	
	public void toggle(Fragment newFragment) {
    	FragmentManager fManager = this.contextActivity.getFragmentManager();
    	fManager.beginTransaction().replace(R.id.container, newFragment).commit();
    }
	
	private class FragmentContainer {
		
		private Fragment instance;
		private String text;
		@SuppressWarnings("unused")
		private int icon;
		
		public FragmentContainer(Fragment inst) {
			this.instance = inst;
		}
		
		public FragmentContainer(String text, Fragment inst) {
			this(inst);
			this.text = text;
		}
		
		public FragmentContainer(int icon, Fragment inst) {
			this(inst);
			this.icon = icon;
		}
	}
}
