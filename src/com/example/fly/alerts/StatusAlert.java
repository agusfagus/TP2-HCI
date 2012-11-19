package com.example.fly.alerts;

import com.example.fly.status.FlightStatus;

public class StatusAlert implements Alert {

	public boolean changedStatus(FlightStatus oldStatus,
			FlightStatus newStatus) {
		return oldStatus.getStatus().equals(newStatus.getStatus());
	}

	public AlertNotification getNotification(FlightStatus newStatus) {
		return new AlertNotification("El nuevo estado es: " + newStatus.getStatus(), "Estado del vuelo");
	}

}
