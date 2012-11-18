package com.example.fly.status;

import org.json.JSONObject;

public class Airline {

	private String id;
	private String name;
	private String logo;
	
	public Airline(JSONObject json) {
		this.id = json.optString("id");
		this.name = json.optString("name");
		this.logo = json.optString("logo");
	}
	
}
