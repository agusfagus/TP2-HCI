package com.example.fly.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.fly.R;
import com.example.fly.api.ApiService;
import com.example.fly.utils.FragmentTabHandler;

public class SplashActivity extends FragmentActivity {
	
	private String TAG = getClass().getSimpleName();
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	public static final int MILLIS_TIME_TO_WAIT = 4000;
	public static final int STOP = 0;
	
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
    
    public void testApi() {
    	Intent intent = new Intent(Intent.ACTION_SYNC, null, this,
				ApiService.class);
        
        intent.putExtra("method", "GetLanguages");
        intent.putExtra("apiService", "Misc");
        
        intent.putExtra("receiver", new ResultReceiver(new Handler()) {
			@Override
			protected void onReceiveResult(int resultCode, Bundle resultData) {
				super.onReceiveResult(resultCode, resultData);
				if (resultCode == ApiService.STATUS_OK) {

					Log.d(TAG, "OK");

					String response = (String) resultData
							.getSerializable("return");

					handleResponse(response);
					
				} else if (resultCode == ApiService.STATUS_CONNECTION_ERROR) {
					Log.d(TAG, "Connection error.");
				} else {
					Log.d(TAG, "Unknown error.");
				}
			}

		});
        startService(intent);
    }
    
    private void handleResponse(String response) {
    	TextView view = (TextView) findViewById(R.id.textView1);
    	view.setText(response);
    }
}
