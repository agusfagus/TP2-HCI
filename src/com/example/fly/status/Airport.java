package com.example.fly.status;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Airport {

	private String id;
	private String description;
	private String timezone;
	private String terminal;
	private String gate;
	
	public Airport(JSONObject json) throws JSONException {
		try {
			this.id = json.getString("id");
			this.description = json.getString("description");
			this.timezone = json.getString("timezone");
			this.terminal = json.getString("terminal");
			this.gate = json.getString("gate");
		} catch (JSONException e) {
			Log.d("error", "airport");
		}
	}
	
}
