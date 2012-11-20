package com.example.fly.status;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.example.fly.alerts.AlertNotification;
import com.example.fly.api.ApiIntent;
import com.example.fly.api.ApiResultReceiver;
import com.example.fly.mobile.SplashActivity;
import com.example.fly.utils.CallBack;
import com.example.fly.utils.FavouriteFlight;
import com.example.fly.utils.Favourites;

public class NotificationService extends IntentService {

	private static long interval = 15000;
	private Favourites favourites;	
	private ApiResultReceiver receiver = new ApiResultReceiver(new Handler());
	
	public NotificationService() {
		super("NotificationService");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		this.favourites = SplashActivity.favourites;
	    return super.onStartCommand(intent,flags,startId);
	}
	
	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.d("service", "entro");
		while (true) {
			try {
				synchronized (this) {
					wait(interval);
					for (FavouriteFlight f : this.favourites.getList()) {
							Log.d("service", f.getFlight().getNumber());
							checkStatus(f);
							wait(1000);
					}
				}
			} catch (Exception e) { 
			}
		}
	}
	
	private void checkStatus(final FavouriteFlight flight) {
		CallBack callback = new CallBack() {
			
			public void handleResponse(JSONObject response) {
				FlightStatus currentFlightStatus = null;
				try {
					currentFlightStatus = new FlightStatus(response.getJSONObject("status"));
				} catch (JSONException e) {
					Log.d("json", "invalid status");
				}
				List<AlertNotification> notifs = flight.check(currentFlightStatus);
				if (notifs.isEmpty())
					Log.d("notif", "no hay notifs" + flight.getFlight().getNumber());
				for (AlertNotification n : notifs)
					n.notifyAlert();
			}
		};
		receiver.setCallBack(callback);
		ApiIntent intent = new ApiIntent("GetFlightStatus", "Status",
				this.receiver, this);
		intent.setParams(flight.getFlight().getParams());
		startService(intent);
	}
}
