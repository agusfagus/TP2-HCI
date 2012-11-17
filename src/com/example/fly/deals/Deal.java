package com.example.fly.deals;

public class Deal {

	private String cityName;
	private String price;
	
	public Deal(String cityName, String price) {
		this.cityName = cityName;
		this.price = price;
	}
	
	public String getCityName() {
		return this.cityName;
	}
	
	public String getPrice() {
		return this.price;
	}
}
