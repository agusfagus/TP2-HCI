package com.example.fly.deals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DealsProvider {
	
	private static String[] fields = new String[] { "destination", "price" };

	public List<? extends Map<String, ?>> getDealsAsMap(JSONObject json) {
		List<Deal> list = getDealsFromJSON(json);
		List<Map<String, String>> dealsMap = new ArrayList<Map<String, String>>();
		for (Deal d : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(fields[0], d.getCityName());
			map.put(fields[1], d.getPrice());
			dealsMap.add(map);
		}
		return dealsMap;
	}
	
	public String[] getFields() {
		return fields;
	}
	
	private List<Deal> getDealsFromJSON(JSONObject json) {
		JSONArray deals = new JSONArray();
		List<Deal> dealList = new LinkedList<Deal> ();
		try {
			deals = json.getJSONArray("deals");
			int amount = deals.length();
			
			for (int i = 0 ; i < amount ; i++) {
				try {
					String cityName = deals.getJSONObject(i).getString("cityName");
					String price = ((Double)deals.getJSONObject(i).getDouble("price")).toString() + " USD";
					dealList.add(new Deal(cityName, price));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (JSONException e) {
			Log.d("json", "error en la respuesta");
		}
		return dealList;
	}
	
}
