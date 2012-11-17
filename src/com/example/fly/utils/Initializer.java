package com.example.fly.utils;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.fly.R;

public class Initializer {
	
	public static String[] AIRLINES = {"IB", "AA", "LAN"};

	public Initializer(Activity context) {
		AutoCompleteTextView airline = 
				(AutoCompleteTextView) context.findViewById(R.id.airline);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, AIRLINES);
		airline.setAdapter(adapter);
	}
	
}
