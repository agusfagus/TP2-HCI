package com.example.fly.status;

import org.json.JSONObject;

public class City {

	private String id;
	private String name;
	private double latitude;
	private double longitude;
	
	public City (JSONObject json){
		this.id = json.optString("id");
		this.name = json.optString("name");
		this.latitude = json.optDouble("latitude");
		this.longitude = json.optDouble("longitude");
	}
	
}
