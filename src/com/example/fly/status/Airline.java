package com.example.fly.status;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Airline {

	private String id;
	private String name;
	private String logo;
	
	public Airline(JSONObject json) throws JSONException {
		try {
			this.id = json.getString("id");
			this.name = json.getString("name");
			this.logo = json.getString("logo");
		} catch (JSONException e) {
			Log.d("error", "airline");
		}
	}
	
}
