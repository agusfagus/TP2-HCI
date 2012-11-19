package com.example.fly.mobile;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.fly.R;
import com.example.fly.alerts.AlertNotification;
import com.example.fly.api.ApiResultReceiver;
import com.example.fly.api.ApiService;
import com.example.fly.status.NotificationIntent;
import com.example.fly.utils.ButtonListeners;
import com.example.fly.utils.FavouriteFlight;
import com.example.fly.utils.Favourites;
import com.example.fly.utils.FlightReview;
import com.example.fly.utils.FragmentTabHandler;

public class SplashActivity extends FragmentActivity {

	//private String TAG = getClass().getSimpleName();
	private FragmentTabHandler tabHandler;
	private ButtonListeners buttonListeners;
	public static Favourites favourites = new Favourites();
	private ApiResultReceiver receiver = new ApiResultReceiver(new Handler()) {

		@Override
    	protected void onReceiveResult(int resultCode, Bundle resultData) {
    		super.onReceiveResult(resultCode, resultData);
    		if (resultCode == ApiService.STATUS_OK) {

    			String responseString = (String) resultData
    					.getSerializable("return");
    			JSONObject response = new JSONObject();
				try {
					response = new JSONObject(responseString);
				} catch (JSONException e) {
					Log.d("JSON", "Todo mal");
				}
    			callback.handleResponse(response);
    			
    		} else if (resultCode == ApiService.STATUS_CONNECTION_ERROR) {
    			Log.d("Api Service", "Connection error.");
    		} else {
    			Log.d("Api Service", "Unknown error.");
    		}
    	}
	};
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	public static final int MILLIS_TIME_TO_WAIT = 4000;
	public static final int STOP = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tabHandler = new FragmentTabHandler(this);
        this.buttonListeners = new ButtonListeners(this);
        AlertNotification.context = this;
        NotificationIntent intent = new NotificationIntent(this);
        startService(intent);
      //  new Initializer(this);
    }
    
    public FragmentTabHandler getFragmentHandler() {
    	return this.tabHandler;
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
    
    public ApiResultReceiver getReceiver() {
    	return receiver;
    }
    
	public void getDeals (View view) {
		EditText from = (EditText)findViewById(R.id.from);
		buttonListeners.getDeals(from.getText().toString());
	}
	
	public void submitReview (View view) {
		FlightReview review = null;
		try {
			review = new FlightReview(this);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buttonListeners.submitReview(review);
	}
    
    public void addFavouriteFlight (View view) {
    	EditText airline = (EditText) findViewById(R.id.fav_airline);
    	EditText flightNum = (EditText) findViewById(R.id.fav_flight_number);
    	buttonListeners.addFavouriteFlight(airline.getText().toString(), flightNum.getText().toString());
    }
    
    public void addFavouriteFlight (FavouriteFlight newFav) {
    	favourites.put(newFav);
    }
	
	public void toggleSearchFlights(View view) {
		selectFragment(0);
	}
	
	public void toggleReviews(View view) {
		selectFragment(1);
	}
	
	public void toggleNotifications(View view) {
		selectFragment(2);
	}
	
	private void selectFragment(int index) {
		buttonListeners.selectFragment(this.tabHandler, getActionBar().getTabAt(index));
	}
}
