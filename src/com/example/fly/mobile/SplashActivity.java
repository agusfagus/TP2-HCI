package com.example.fly.mobile;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fly.R;
import com.example.fly.alerts.AlertNotification;
import com.example.fly.api.ApiResultReceiver;
import com.example.fly.status.NotificationIntent;
import com.example.fly.utils.Airlines;
import com.example.fly.utils.ButtonListeners;
import com.example.fly.utils.Cities;
import com.example.fly.utils.FavouriteFlight;
import com.example.fly.utils.Favourites;
import com.example.fly.utils.FlightReview;
import com.example.fly.utils.FragmentTabHandler;

@SuppressLint({ "WorldWriteableFiles", "WorldReadableFiles" })
public class SplashActivity extends FragmentActivity {

	//private String TAG = getClass().getSimpleName();
	private FragmentTabHandler tabHandler;
	private ButtonListeners buttonListeners;
	public static final String storageFile = "storageFile";
	public static Favourites favourites;
	private Cities cities = new Cities();
	private Airlines airlines = new Airlines();
	private ApiResultReceiver receiver = new ApiResultReceiver(new Handler()); 
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	public static final int MILLIS_TIME_TO_WAIT = 4000;
	public static final int STOP = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
			retrieveData();
        	this.tabHandler = new FragmentTabHandler(this);
            this.buttonListeners = new ButtonListeners(this);
            AlertNotification.context = this;
            NotificationIntent intent = new NotificationIntent(this);
            startService(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public FragmentTabHandler getFragmentHandler() {
    	return this.tabHandler;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    	if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)){
    		try {
    			getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
    		} catch (Exception e) {
    			setMain();
    		}
    	}
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.d("aa", String.valueOf(item.getItemId()));
    	switch (item.getItemId()) {
	    	case android.R.id.home:
	    		Log.d("hola", "hola");
		    	getSupportFragmentManager().popBackStack();
		    	getSupportFragmentManager().executePendingTransactions();
		    	getActionBar().selectTab(null);
		    	break;
	    	case R.id.menu_settings:
	    		SettingsFragment sFragment = new SettingsFragment();
	    		sFragment.setData(favourites, this);
	    		tabHandler.toggle(sFragment);
    	}
    	return true;
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
		try {
			String cityName = cities.getId(from.getText().toString());
			buttonListeners.getDeals(cityName);
		} catch (Exception e){
			Toast.makeText(this, R.string.deals_error, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void submitReview (View view) {
		FlightReview review = null;
		try {
			review = new FlightReview(this);
			buttonListeners.submitReview(review);
		} catch (Exception e) {
			Toast.makeText(this, R.string.review_error, Toast.LENGTH_SHORT).show();
		}
	}
    
    public void addFavouriteFlight (View view) {
    	try {
	    	EditText airline = (EditText) findViewById(R.id.fav_airline);
	    	EditText flightNum = (EditText) findViewById(R.id.fav_flight_number);
	    	String airlineId = airlines.getId(airline.getText().toString());
	    	buttonListeners.addFavouriteFlight(airlineId, flightNum.getText().toString());
    	} catch (Exception e) {
    		Toast.makeText(this, R.string.fav_error, Toast.LENGTH_SHORT).show();
    	}
    }
    
    public void addFavouriteFlight (FavouriteFlight newFav) {
    	favourites.put(newFav);
    	Editor editor = getSharedPreferences(storageFile, MODE_WORLD_WRITEABLE).edit();
    	editor.putString(newFav.getFlight().getNumber() + newFav.getFlight().getAirline(), 
    			newFav.getStatus().toString());
    	editor.commit();
    }
    
    public void addFavouriteFlight (JSONObject newFav) throws JSONException {
    	favourites.put(new FavouriteFlight(newFav));
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
	
	public void setMain() {
		this.tabHandler.selectMain();
	}
	
	public Cities getCities() {
		return this.cities;
	}
	
	public Airlines getAirlines() {
		return this.airlines;
	}
	
	private void retrieveData() throws JSONException {
		Map<String, ?> stored = getSharedPreferences(storageFile, MODE_WORLD_READABLE).getAll();
		favourites = new Favourites(); 
		for (Object value : stored.values()){
			JSONObject json = new JSONObject((String)value);
			addFavouriteFlight(json);
		}
	}
}
