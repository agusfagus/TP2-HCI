package com.example.fly.utils;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar.Tab;
import android.util.Log;
import android.widget.Switch;

import com.example.fly.R;
import com.example.fly.alerts.ArrivalGateAlert;
import com.example.fly.alerts.ArrivalTerminalAlert;
import com.example.fly.alerts.ArrivalTimeAlert;
import com.example.fly.alerts.BaggageGateAlert;
import com.example.fly.alerts.DepartureGateAlert;
import com.example.fly.alerts.DepartureTerminalAlert;
import com.example.fly.alerts.DepartureTimeAlert;
import com.example.fly.alerts.StatusAlert;
import com.example.fly.api.ApiIntent;
import com.example.fly.mobile.DisplayDealsFragment;
import com.example.fly.mobile.SplashActivity;
import com.example.fly.status.FlightStatus;

public class ButtonListeners {
	
	SplashActivity context;
	
	public ButtonListeners(SplashActivity context) {
		this.context = context;
	}
	
	public void selectFragment(FragmentTabHandler fHandler, Tab tab) {
		fHandler.onTabSelected(tab, null);
	}
	
	public void getDeals(String from) {
		CallBack callback = new CallBack() {

			public void handleResponse(JSONObject response) {
				DisplayDealsFragment newFragment = new DisplayDealsFragment();
				newFragment.setResponse(response, context);
				context.getFragmentHandler().toggle(newFragment);
			}
		};
		context.getReceiver().setCallBack(callback);
		ApiIntent intent = new ApiIntent("GetFlightDeals", "Booking", 
				this.context.getReceiver(), this.context);
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		params.add(new BasicNameValuePair("from", from));
		intent.setParams(params);
		context.startService(intent);
	}

	public void submitReview(FlightReview review) {
		CallBack callback = new CallBack() {

			public void handleResponse(JSONObject response) {
				context.toggleSearchFlights(null);
				//TextView prueba = (TextView) context.findViewById(R.id.prueba);
//				prueba.setText(response);
			}
		};
		context.getReceiver().setCallBack(callback);
		ApiIntent intent = new ApiIntent("ReviewAirline2", "Review", 
				this.context.getReceiver(), this.context);
		intent.setParams(review.getParams());
		context.startService(intent);
	}
	
	public void addFavouriteFlight(String airline, String flightNumber) {
		final Flight favFlight = new Flight(airline, flightNumber);
		CallBack callback = new CallBack() {
			
			public void handleResponse(JSONObject response) {
				FlightStatus initialFlightStatus = null;
				try {
					initialFlightStatus = new FlightStatus(response.getJSONObject("status"));
				} catch (JSONException e) {
					Log.d("json", "invalid status");
				}
				FavouriteFlight flight = new FavouriteFlight(favFlight, initialFlightStatus);
				getAlerts(flight);
				context.addFavouriteFlight(flight);
			}
		};
		context.getReceiver().setCallBack(callback);
		ApiIntent intent = new ApiIntent("GetFlightStatus", "Status",
				this.context.getReceiver(), this.context);
		intent.setParams(favFlight.getParams());
		context.startService(intent);
	}
	
	private void getAlerts(FavouriteFlight flight) {
		if ( ((Switch) context.findViewById(R.id.flight_state)).isChecked() )
			flight.addAlert(new StatusAlert());
		if ( ((Switch) context.findViewById(R.id.departure_terminal)).isChecked() )
			flight.addAlert(new DepartureTerminalAlert());
		if ( ((Switch) context.findViewById(R.id.arrival_terminal)).isChecked() )
			flight.addAlert(new ArrivalTerminalAlert());
		if ( ((Switch) context.findViewById(R.id.departure_gate)).isChecked() )
			flight.addAlert(new DepartureGateAlert());
		if ( ((Switch) context.findViewById(R.id.arrival_gate)).isChecked() )
			flight.addAlert(new ArrivalGateAlert());
		if ( ((Switch) context.findViewById(R.id.baggage_gate)).isChecked() )
			flight.addAlert(new BaggageGateAlert());
		if ( ((Switch) context.findViewById(R.id.departure_time)).isChecked() )
			flight.addAlert(new DepartureTimeAlert());
		if ( ((Switch) context.findViewById(R.id.arrival_time)).isChecked() )
			flight.addAlert(new ArrivalTimeAlert());
	}
}