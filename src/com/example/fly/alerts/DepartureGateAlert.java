package com.example.fly.alerts;

import com.example.fly.status.FlightStatus;
import com.example.fly.utils.Alert;
import com.example.fly.utils.AlertNotification;

public class DepartureGateAlert implements Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus) {
		return !oldStatus.getDepartureGate().equals(newStatus.getDepartureGate());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification("La nueva puerta de embarque es: " + newStatus.getDepartureGate());
	}

}
