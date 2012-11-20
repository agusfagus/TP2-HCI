package com.example.fly.mobile;

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
import com.example.fly.deals.FavsProvider;
import com.example.fly.utils.Favourites;

public class SettingsFragment extends Fragment {

	private SplashActivity context;
	private Favourites fav;
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstanceState) {
		return inflater.inflate(R.layout.settings, container, false);
    }
	
	public void setData(Favourites fav, Activity context) {
		this.fav = fav;
		this.context = (SplashActivity)context;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		try {
			setListView();
		} catch (Exception e) {
			Toast.makeText(context, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
			context.setMain();
		}
	}
	
	private void setListView(){
		FavsProvider provider = new FavsProvider(this.context.getAirlines());
		ListView list = (ListView) context.findViewById(R.id.settings_list);
		SimpleAdapter adapter = new SimpleAdapter(context, 
				provider.getFavsAsMap(fav), R.layout.favorite, 
				provider.getFields(), new int[] { R.id.airline_list , R.id.number_list });
		
		list.setAdapter(adapter);
	}
	
}
