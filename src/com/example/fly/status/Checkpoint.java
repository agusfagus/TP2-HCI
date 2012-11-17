package com.example.fly.status;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Checkpoint {

	private Airport airport;
	private City city;
	private Country country;
	private String scheduledTime;
	private String scheduledGateTime;
	private String actualGateTime;
	private String estimateRunwayTime;
	private String actualRunwayTime;
	private int gateDelay;
	private int runwayDelay;
	
	public Checkpoint(JSONObject json) throws JSONException {
		try {
			this.airport = new Airport(json.optJSONObject("airport"));
			this.city = new City(json.optJSONObject("city"));
			this.country = new Country(json.optJSONObject("country"));
			this.scheduledTime = json.optString("scheduledTime");
			this.scheduledGateTime = json.optString("scheduledGateTime");
			this.actualGateTime = json.optString("actualGateTime");
			this.estimateRunwayTime = json.optString("estimateRunwayTime");
			this.actualRunwayTime = json.optString("actualRunwayTime");
			this.gateDelay = json.optInt("gateDelay");
			this.runwayDelay = json.optInt("runwayDelay");
		} catch (JSONException e) {
			Log.d("error", "checkpoint");
		}
	}
	
	
}
