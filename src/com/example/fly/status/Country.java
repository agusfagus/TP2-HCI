package com.example.fly.status;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Country {
	
	private String id;
	private String name;
	
	public Country(JSONObject json) throws JSONException {
		try {
			this.id = json.getString("id");
			this.name = json.getString("name");
		} catch (JSONException e) {
			Log.d("error", "country");
		}
	}

}
