package com.example.fly.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;


public class SearchFlightActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_flight, menu);
        return true;
    }
}
