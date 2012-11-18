package com.example.fly.status;

import org.json.JSONObject;

public class Country {
	
	private String id;
	private String name;
	
	public Country(JSONObject json){
		this.id = json.optString("id");
		this.name = json.optString("name");
	}

}
