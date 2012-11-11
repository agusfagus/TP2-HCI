package com.example.fly.mobile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SearchFlightFragment extends Fragment {

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstanceState) {
		return inflater.inflate(R.layout.search_flight_fragment, container, false);
    }
	
}
