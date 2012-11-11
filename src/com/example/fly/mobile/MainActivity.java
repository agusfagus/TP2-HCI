package com.example.fly.mobile;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.example.fly.utils.FragmentContainer;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	private FragmentContainer menuOptions[] = {
			new FragmentContainer("Home", new MainFragment()),
			new FragmentContainer("Vuelos", new SearchFlightFragment()),
			new FragmentContainer("Opiniones", new ReviewFragment()),
			new FragmentContainer("Notificaciones", new NotificationFragment())
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeActionBar();
    }
    
    private void initializeActionBar() {
    	final ActionBar actionBar = getActionBar();
    	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    	for (FragmentContainer fContainer : menuOptions) {
    		actionBar.addTab(actionBar.newTab().setText(fContainer.getTag()).setTabListener(this));
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        //SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        return true;
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    	if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM))
    		getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
    	outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar().getSelectedNavigationIndex());
    }
    
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
    	// Get fragment's instance
    	toggle(menuOptions[tab.getPosition()].getInstance());
    }
    
    private void toggle(Fragment newFragment) {
    	// Replace whatever is in the fragment_container view with this fragment,
    	// and add the transaction to the back stack
    	FragmentManager fManager = getFragmentManager();
    	fManager.beginTransaction().replace(R.id.container, newFragment).commit();
    }

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
}
