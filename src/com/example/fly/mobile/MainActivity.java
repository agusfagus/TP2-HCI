package com.example.fly.mobile;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.example.fly.utils.FragmentTabHandler;

public class MainActivity extends FragmentActivity {
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FragmentTabHandler(this);
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
}
