package com.example.fly.alerts;

import com.example.fly.status.FlightStatus;

public class BaggageGateAlert implements Alert {

	public boolean changedStatus(FlightStatus oldStatus, FlightStatus newStatus) {
		return !oldStatus.getBaggageGate().equals(newStatus.getBaggageGate());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification("La nueva puerta de recolecci�n de equipaje es: " + newStatus.getBaggageGate());
	}

	public String getName() {
		return "BaggageGate";
	}
}
