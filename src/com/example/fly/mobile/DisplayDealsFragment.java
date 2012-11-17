package com.example.fly.mobile;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.fly.R;
import com.example.fly.deals.DealsProvider;

public class DisplayDealsFragment extends Fragment {

	private JSONObject response;
	private Activity context;
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.display_deals, container, false);
    }
	
	public void setResponse(JSONObject response, Activity context) {
		this.response = response;
		this.context = context;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setListView();
	}
	
	private void setListView() {
		DealsProvider provider = new DealsProvider();
		ListView list = (ListView) context.findViewById(R.id.display_deals);
		SimpleAdapter adapter = new SimpleAdapter(context, 
				provider.getDealsAsMap(response), R.layout.deal, 
				provider.getFields(), new int[] { R.id.destination , R.id.price });
		
		list.setAdapter(adapter);
	}
	
}
