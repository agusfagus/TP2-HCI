package com.example.fly.utils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Favourites implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<FavouriteFlight> list = new LinkedList<FavouriteFlight>();
	
	public void put(FavouriteFlight ff) {
		list.add(ff);
	}
	
	/*
	public FlightStatus get(Flight f) {
		return list.get(f);
	}*/

	public List<FavouriteFlight> getList() {
		return list;
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
