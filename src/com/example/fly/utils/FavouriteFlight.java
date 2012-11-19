package com.example.fly.utils;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.fly.alerts.Alert;
import com.example.fly.alerts.AlertNotification;
import com.example.fly.alerts.ArrivalGateAlert;
import com.example.fly.alerts.ArrivalTerminalAlert;
import com.example.fly.alerts.ArrivalTimeAlert;
import com.example.fly.alerts.BaggageGateAlert;
import com.example.fly.alerts.DepartureGateAlert;
import com.example.fly.alerts.DepartureTerminalAlert;
import com.example.fly.alerts.DepartureTimeAlert;
import com.example.fly.alerts.StatusAlert;
import com.example.fly.status.FlightStatus;

public class FavouriteFlight {

	private Flight flight;
	private FlightStatus currentStatus;
	private List<Alert> alerts = new LinkedList<Alert>();
	private JSONObject json;
	
	public FavouriteFlight(JSONObject json) throws JSONException {
		this.currentStatus = new FlightStatus(json);
		this.json = json;
		this.flight = new Flight(json.optJSONObject("airline").getString("id"),json.optString("number"));
		this.alerts = getAlerts(json);
	}
	
	public FavouriteFlight(Flight flight, FlightStatus currentStatus) throws Exception{
		this.flight = flight;
		this.currentStatus = currentStatus;
		this.json = new JSONObject(currentStatus.toString());
	}
	
	public Flight getFlight() {
		return this.flight;
	}

	public FlightStatus getStatus() {
		return this.currentStatus;
	}
	
	public void addAlert(Alert alert){
		try {
			json.put(alert.getName(), true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	private List<Alert> getAlerts(JSONObject json) {
		List<Alert> ret = new LinkedList<Alert>();
		if (json.optBoolean("Status"))
			ret.add(new StatusAlert());
		if (json.optBoolean("DepartureTime"))
			ret.add(new DepartureTimeAlert());
		if (json.optBoolean("DepartureTerminal"))
			ret.add(new DepartureTerminalAlert());
		if (json.optBoolean("DepartureGate"))
			ret.add(new DepartureGateAlert());
		if (json.optBoolean("BaggageGate"))
			ret.add(new BaggageGateAlert());
		if (json.optBoolean("ArrivalTime"))
			ret.add(new ArrivalTimeAlert());
		if (json.optBoolean("ArrivalTerminal"))
			ret.add(new ArrivalTerminalAlert());
		if (json.optBoolean("ArrivalGate"))
			ret.add(new ArrivalGateAlert());
		return ret;
	}
}
