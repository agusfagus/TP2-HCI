package com.example.fly.utils;

import java.util.LinkedList;
import java.util.List;

import com.example.fly.status.FlightStatus;

public class FavouriteFlight {

	Flight flight;
	private FlightStatus currentStatus;
	private List<Alert> alerts = new LinkedList<Alert>();
	
	public FavouriteFlight(Flight flight, FlightStatus currentStatus) {
		this.flight = flight;
		this.currentStatus = currentStatus;
	}
	
	public Flight getFlight() {
		return this.flight;
	}
	
	public void addAlert(Alert alert) {
		alerts.add(alert);
	}
	
	public List<AlertNotification> check(FlightStatus newStatus) {
		List<AlertNotification> notifications = new LinkedList<AlertNotification>();
		for (Alert a : alerts) {
			if (a.changedStatus(this.currentStatus, newStatus))
				notifications.add(a.getNotification(newStatus));
		}
		return notifications;
	}
}
