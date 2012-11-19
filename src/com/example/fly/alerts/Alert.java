package com.example.fly.alerts;

import com.example.fly.status.FlightStatus;

public interface Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus);
	
	public AlertNotification getNotification(FlightStatus newStatus);
	
}
