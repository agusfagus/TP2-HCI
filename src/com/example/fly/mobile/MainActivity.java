package com.example.fly.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void toggleSearchFlight(View view) {
    	toggle(this, SearchFlightActivity.class);
    }
    
    public void toggleReviews(View view) {
    	toggle(this, ReviewActivity.class);
    }
    
    public void toggleNotifications(View view) {
    	toggle(this, NotificationActivity.class);
    }
    
    private void toggle(Context current, Class<?> cls) {
    	Intent intent = new Intent(current, cls);
    	startActivity(intent);
    }
}
