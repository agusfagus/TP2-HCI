package com.example.fly.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.fly.api.ApiService;
import com.example.fly.api.ApiService.LocalBinder;

public class ReviewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_review, menu);
        return true;
    }
    
    public void callMethod(View view) {
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
    }
}
