package com.example.fly.alerts;

import com.example.fly.status.FlightStatus;

public class DepartureTimeAlert implements Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus) {
		return !oldStatus.getDepartureTime().equals(newStatus.getDepartureTime());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification("El nuevo horario de salida es: " + newStatus.getDepartureTime());
	}

}
