package com.example.fly.utils;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.example.fly.mobile.MainFragment;
import com.example.fly.mobile.NotificationFragment;
import com.example.fly.mobile.R;
import com.example.fly.mobile.ReviewFragment;
import com.example.fly.mobile.SearchFlightFragment;

public class FragmentTabHandler implements ActionBar.TabListener {
	
	private FragmentContainer fragmentList[] = {
			new FragmentContainer("Vuelos", new SearchFlightFragment()),
			new FragmentContainer("Opiniones", new ReviewFragment()),
			new FragmentContainer("Notificaciones", new NotificationFragment())
	};
	
	private FragmentContainer mainFragment = new FragmentContainer(R.drawable.ic_launcher_home, new MainFragment());
	
	private Activity contextActivity;
	
	public FragmentTabHandler(Activity contextActivity) {
		this.contextActivity = contextActivity;
		initialize();
		toggle(mainFragment.instance);
	}
	
	public void onTabReselected(Tab tab, FragmentTransaction ft) { }

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Get fragment's instance
    	toggle(fragmentList[tab.getPosition()].instance);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) { }
	
	private void initialize() {
		final ActionBar actionBar = this.contextActivity.getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//actionBar.addTab(actionBar.newTab().setIcon(fragmentList[0].icon).setTabListener(this));
		for (int i = 0 ; i < fragmentList.length ; i++)
			actionBar.addTab(actionBar.newTab().setText(fragmentList[i].text).setTabListener(this));
	}
	
	private void toggle(Fragment newFragment) {
    	// Replace whatever is in the fragment_container view with this fragment,
    	// and add the transaction to the back stack
    	FragmentManager fManager = this.contextActivity.getFragmentManager();
    	fManager.beginTransaction().replace(R.id.container, newFragment).commit();
    }
	
	private class FragmentContainer {
		
		private Fragment instance;
		//private FragmentTabHandler parent = FragmentTabHandler.this;
		private String text;
		private int icon;
		
		public FragmentContainer(Fragment inst) {
			this.instance = inst;
		}
		
		public FragmentContainer(String text, Fragment inst) {
			this(inst);
			/*parent.actionBar.addTab(
					actionBar.newTab().setText(tabTag).setTabListener(parent));*/
			this.text = text;
		}
		
		public FragmentContainer(int icon, Fragment inst) {
			this(inst);
			/*parent.actionBar.addTab(
					actionBar.newTab().setIcon(icon).setTabListener(parent));*/
			this.icon = icon;
		}
	}
}
