package com.example.fly.utils;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;

import com.example.fly.R;
	
public class FlightReview {
	
	private List<NameValuePair> params = new LinkedList<NameValuePair> ();

	public FlightReview(Activity context) throws Exception {
		JSONObject json = new JSONObject();
		EditText airlineView = (EditText) context.findViewById(R.id.airline);
		String airline = airlineView.getText().toString();
		json.put("airlineId", airline);
		
		EditText flightIdView = (EditText) context.findViewById(R.id.flight_num);
		String flightId = flightIdView.getText().toString();
		json.put("flightNumber", Integer.valueOf(flightId));
		
		RatingBar friendlinessView = (RatingBar) context.findViewById(R.id.friendliness);
		Integer friendliness = (int)(friendlinessView.getRating() * 2);
		json.put("friendlinessRating", friendliness);
		
		RatingBar foodView = (RatingBar) context.findViewById(R.id.food);
		Integer food = (int)(foodView.getRating() * 2);
		json.put("foodRating", food);
		
		RatingBar punctualityView = (RatingBar) context.findViewById(R.id.punctuality);
		Integer punctuality = (int)(punctualityView.getRating() * 2);
		json.put("punctualityRating", punctuality);
		
		RatingBar mileageView = (RatingBar) context.findViewById(R.id.mileage);
		Integer mileage = (int)(mileageView.getRating() * 2);
		json.put("mileageProgramRating", mileage);
		
		RatingBar comfortView = (RatingBar) context.findViewById(R.id.comfort);
		Integer comfort = (int)(comfortView.getRating() * 2);
		json.put("comfortRating", comfort);
		
		RatingBar qualityPriceView = (RatingBar) context.findViewById(R.id.quality_price);
		Integer qualityPrice = (int)(qualityPriceView.getRating() * 2);
		json.put("qualityPriceRating", qualityPrice);
		
		Switch yesRecommendView = (Switch) context.findViewById(R.id.recommend);
		boolean yesRecommend = yesRecommendView.isChecked();
		json.put("yesRecommend", yesRecommend);
		
		json.put("comments", "");
		Log.d("json", json.toString());
		params.add(new BasicNameValuePair("data", json.toString()));
	}
	
	public List<NameValuePair> getParams() {
		return this.params;
	}
	
}
