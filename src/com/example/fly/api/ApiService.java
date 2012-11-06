package com.example.fly.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ApiService extends Service{
	
	private static String baseURL = "http://eiffel.itba.edu.ar/hci/service2/";
	private String url;
	private String params = "";
	
	public ApiService(String service, String method) {
		this.url = baseURL + service + ".groovy?method=" + method;
	}
	
	public String getResponse(JSONObject params) {
		if (params != null)
			this.params = params.toString();
		return new HttpManager().execute(this.params, this.url);
	}
	
	private class HttpManager {

		protected String execute(String... params) {
			try {
				return getContent(params);
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}
		
		private String getContent(String... params) throws IOException {
			InputStream iStream = null;		        
		    try {
		        URL url = new URL(params[1]);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setReadTimeout(10000 /* milliseconds */);
		        conn.setConnectTimeout(15000 /* milliseconds */);
		        conn.setRequestMethod("GET");
		        //conn.setRequestProperty(field, newValue)
		        conn.setDoInput(true);
		        // Starts the query
		        conn.connect();
		        //int response = conn.getResponseCode();
		        //Log.d(DEBUG_TAG, "The response is: " + response);
		        iStream = conn.getInputStream();
		        // Convert the InputStream into a string
		        String contentAsString = parseStream(iStream);
		        return contentAsString;
		        
		    // Makes sure that the InputStream is closed after the app is
		    // finished using it.
		    } catch (Exception e){
		    	return e.toString();
		    } finally {
		        if (iStream != null) {
		            iStream.close();
		        } 
		    }
		}
		
		private String parseStream(InputStream iStream) throws IOException, UnsupportedEncodingException {
			int len = 1000;
			Reader reader = null;
		    reader = new InputStreamReader(iStream, "UTF-8");        
		    char[] buffer = new char[len];
		    reader.read(buffer);
		    return new String(buffer);
		}
	}
	
	public class LocalBinder extends Binder {
        public ApiService getService() {
            // Return this instance of LocalService so clients can call public methods
            return ApiService.this;
        }
    }

	@Override
	public IBinder onBind(Intent arg0) {
		return new LocalBinder();
	}
}
