package com.example.fly.utils;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Flight {

	private String airline;
	private String flightNumber;
	
	public Flight(String airline, String flightNumber) {
		this.airline = airline;
		this.flightNumber = flightNumber;
	}
	
	public List<NameValuePair> getParams() {
		List<NameValuePair> params = new LinkedList<NameValuePair> ();
		params.add(new BasicNameValuePair("airline_id", this.airline));
		params.add(new BasicNameValuePair("flight_num", this.flightNumber));
		return params;
	}
	
	public String getNumber() {
		return this.flightNumber;
	}
	
	public String getAirline() {
		return this.airline;
	}
}
