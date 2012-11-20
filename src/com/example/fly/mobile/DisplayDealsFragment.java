package com.example.fly.mobile;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.fly.R;
import com.example.fly.deals.DealsProvider;

public class DisplayDealsFragment extends Fragment {

	private JSONObject response;
	private SplashActivity context;
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.display_deals, container, false);
    }
	
	public void setResponse(JSONObject response, Activity context) {
		this.response = response;
		this.context = (SplashActivity)context;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		try {
			setListView();
		} catch (JSONException e) {
			Toast.makeText(context, R.string.deals_error, Toast.LENGTH_SHORT).show();
			context.setMain();
		}
	}
	
	private void setListView() throws JSONException {
		DealsProvider provider = new DealsProvider();
		ListView list = (ListView) context.findViewById(R.id.display_deals);
		SimpleAdapter adapter = new SimpleAdapter(context, 
				provider.getDealsAsMap(response), R.layout.deal, 
				provider.getFields(), new int[] { R.id.destination , R.id.price });
		
		list.setAdapter(adapter);
	}
	
}
