package com.example.fly.mobile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fly.R;

public class ReviewFragment extends Fragment {

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.review_fragment, container, false);
    }
    
    /*public void callMethod(View view) {
    	TextView result = (TextView) findViewById(R.id.result);
    	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			Intent intent = new Intent(this, ApiService.class);
			ApiService handler = new ApiService("Misc", "GetLanguages");
			LocalBinder binder = (LocalBinder) handler.onBind(intent);
			handler = binder.getService();
	    	result.setText(handler.getResponse(null));
		} else {
			result.setText("No network connection available.");
		}
    }*/
}
