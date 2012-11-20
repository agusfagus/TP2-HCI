package com.example.fly.utils;

import java.util.HashMap;
import java.util.Map;

public class Cities {

	Map<String, String> cities = new HashMap<String, String>();
	
	public void addCity(String id, String name) {
		cities.put(name, id);
	}
	
	public String getId(String name) {
		return cities.get(name);
	}
	
	public String[] getCityNames() {
		return (String[])this.cities.keySet().toArray();
	}
}
