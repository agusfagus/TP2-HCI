package com.example.fly.utils;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.fly.R;
import com.example.fly.mobile.SplashActivity;
	
public class FlightReview {
	
	private List<NameValuePair> params = new LinkedList<NameValuePair> ();
	private SplashActivity context;
	private Airlines airlines;

	public FlightReview(SplashActivity context) throws Exception {
		JSONObject json = new JSONObject();
		this.context = context;
		this.airlines = this.context.getAirlines();
		
		EditText airlineView = (EditText) context.findViewById(R.id.airline);
		String airline = airlineView.getText().toString();
		json.put("airlineId", airlines.getId(airline));
		
		EditText flightIdView = (EditText) context.findViewById(R.id.flight_num);
		String flightId = flightIdView.getText().toString();
		json.put("flightNumber", Integer.valueOf(flightId));
		
		json.put("friendlinessRating", getScore(R.id.friendliness));
		
		json.put("foodRating", getScore(R.id.food));
		
		json.put("punctualityRating", getScore(R.id.punctuality));
				
		json.put("mileageProgramRating", getScore(R.id.mileage));
		
		json.put("comfortRating", getScore(R.id.comfort));
		
		json.put("qualityPriceRating", getScore(R.id.quality_price));
		
		json.put("yesRecommend", getScore(R.id.recommend));
		
		json.put("comments", "");
		Log.d("json", json.toString());
		params.add(new BasicNameValuePair("data", json.toString()));
	}
	
	public List<NameValuePair> getParams() {
		return this.params;
	}
	
	private int getScore(int id) {
		RatingBar view = (RatingBar)context.findViewById(R.id.mileage);
		return Math.round((view.getRating()/5)*8 + 1);
	}
}
