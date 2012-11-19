package com.example.fly.alerts;

import com.example.fly.status.FlightStatus;

public class DepartureTerminalAlert implements Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus) {
		return !oldStatus.getDepartureTerminal().equals(newStatus.getDepartureTerminal());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification("La nueva terminal de embarque es la: " + newStatus.getDepartureTerminal());
	}

}
