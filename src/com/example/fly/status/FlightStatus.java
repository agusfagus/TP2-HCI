package com.example.fly.status;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class FlightStatus {

	private int flightId;
	private Airline airline;
	private int number;
	private String status;
	private Checkpoint departure;
	private Checkpoint arrival;
	private JSONObject json;
	
	public FlightStatus(JSONObject json) throws JSONException {
		try {
			this.flightId = json.getInt("flightId");
			this.number = json.getInt("number");
			this.status = json.getString("status");
			this.airline = new Airline(json.getJSONObject("airline"));
			this.departure = new Checkpoint(json.getJSONObject("departure"));
			this.arrival = new Checkpoint(json.getJSONObject("arrival"));
			this.json = json;
		} catch (JSONException e) {
			Log.d("error", "flightstatus");
		}
		
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String toString() {
		return json.toString();
	}
}
