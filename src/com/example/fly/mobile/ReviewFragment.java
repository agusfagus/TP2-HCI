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
import com.example.fly.utils.Airlines;
import com.example.fly.utils.CallBack;

public class ReviewFragment extends Fragment {

	private SplashActivity context;
	private Airlines airlines;
	private ApiResultReceiver receiver = new ApiResultReceiver(new Handler());
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.review_fragment, container, false);
    }
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.context = (SplashActivity)getActivity();
		this.airlines = this.context.getAirlines();
		final AutoCompleteTextView text = (AutoCompleteTextView) getActivity().findViewById(R.id.airline);
		CallBack callback = new CallBack() {

			public void handleResponse(JSONObject response) {
				try {
					JSONArray cityArray = response.getJSONArray("airlines");
					List<String> airlinesList = new LinkedList<String>();
					for (int i = 0 ; i < cityArray.length() ; i++) {
						String name = cityArray.getJSONObject(i).optString("name");
						String id = cityArray.getJSONObject(i).optString("airlineId");
						airlines.addAirline(id, name);
						airlinesList.add(name);
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
			                android.R.layout.simple_dropdown_item_1line, airlinesList);
					text.setAdapter(adapter);
				} catch (JSONException e) {
					
				}
			}
			
		};
		receiver.setCallBack(callback);
		ApiIntent intent = new ApiIntent("GetAirlines", "Misc", 
				this.receiver, this.context);
		intent.setParams(new LinkedList<NameValuePair>());
		this.context.startService(intent);
	}
}
