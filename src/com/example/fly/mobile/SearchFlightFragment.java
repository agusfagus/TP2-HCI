package com.example.fly.mobile;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.fly.R;
import com.example.fly.api.ApiIntent;
import com.example.fly.api.ApiResultReceiver;
import com.example.fly.utils.CallBack;
import com.example.fly.utils.Cities;


public class SearchFlightFragment extends Fragment {

	private SplashActivity context;
	private Cities cities;
	private ApiResultReceiver receiver = new ApiResultReceiver(new Handler());
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstanceState) {
		return inflater.inflate(R.layout.search_flight_fragment, container, false);
    }
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.context = (SplashActivity)getActivity();
		this.cities = this.context.getCities();
		final AutoCompleteTextView text = (AutoCompleteTextView) getActivity().findViewById(R.id.from);
		CallBack callback = new CallBack() {

			public void handleResponse(JSONObject response) {
				try {
					JSONArray cityArray = response.getJSONArray("cities");
					List<String> cityList = new LinkedList<String>();
					for (int i = 0 ; i < cityArray.length() ; i++) {
						String name = cityArray.getJSONObject(i).optString("name");
						String id = cityArray.getJSONObject(i).optString("cityId");
						cities.addCity(id, name);
						cityList.add(name);
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
			                android.R.layout.simple_dropdown_item_1line, cityList);
					text.setAdapter(adapter);
				} catch (JSONException e) {
					
				}
			}
			
		};
		receiver.setCallBack(callback);
		ApiIntent intent = new ApiIntent("GetCities", "Geo", 
				this.receiver, this.context);
		intent.setParams(new LinkedList<NameValuePair>());
		this.context.startService(intent);
	}
}
