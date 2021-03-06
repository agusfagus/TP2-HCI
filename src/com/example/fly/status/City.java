package com.example.fly.status;

import org.json.JSONObject;

public class City {

	private String id;
	private String name;
	
	public City (JSONObject json){
		this.id = json.optString("id");
		this.name = json.optString("name");
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}
