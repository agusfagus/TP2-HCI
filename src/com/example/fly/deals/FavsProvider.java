package com.example.fly.deals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.fly.utils.Airlines;
import com.example.fly.utils.FavouriteFlight;
import com.example.fly.utils.Favourites;
import com.example.fly.utils.Flight;

public class FavsProvider {
	
	private static String[] fields = new String[] { "flight", "airline" };
	private Airlines airlines;

	public FavsProvider(Airlines airlines) {
		this.airlines = airlines;
	}
	
	public List<? extends Map<String, ?>> getFavsAsMap(Favourites favs){
		List<FavouriteFlight> list = favs.getList();
		List<Map<String, String>> favsMap = new ArrayList<Map<String, String>>();
		for (FavouriteFlight f : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			Flight flight = f.getFlight();
			map.put(fields[0], flight.getNumber());
			map.put(fields[1], airlines.getName(flight.getAirline()));
			favsMap.add(map);
		}
		return favsMap;
	}
	
	public String[] getFields() {
		return fields;
	}
	
}
