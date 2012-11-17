package com.example.fly.status;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class City {

	private String id;
	private String name;
	private double latitude;
	private double longitude;
	
	public City (JSONObject json) throws JSONException {
		try {
			this.id = json.getString("id");
			this.name = json.getString("name");
			this.latitude = json.getDouble("latitude");
			this.longitude = json.getDouble("longitude");
		} catch (JSONException e) {
			Log.d("error", "city");
		}
	}
	
}
