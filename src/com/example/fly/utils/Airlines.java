package com.example.fly.utils;

import java.util.HashMap;
import java.util.Map;

public class Airlines {

	Map<String, String> airlines = new HashMap<String, String>();
	
	public void addAirline(String id, String name) {
		airlines.put(name, id);
	}
	
	public String getId(String name) {
		return airlines.get(name);
	}
	
	public String[] getAirlineNames() {
		return (String[])this.airlines.keySet().toArray();
	}
}
