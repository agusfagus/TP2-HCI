package com.example.fly.api;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketTimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

public class ApiService extends IntentService{
	
	public ApiService() {
		super("ApiService");
	}

	private final String TAG = getClass().getSimpleName();
	private String baseURL = "http://eiffel.itba.edu.ar/hci/service2/";
	
	public static final int STATUS_CONNECTION_ERROR = -1;
	public static final int STATUS_ERROR = -2;
	public static final int STATUS_ILLEGAL_ARGUMENT = -3;
	public static final int STATUS_OK = 0;

	@Override
	protected void onHandleIntent(Intent intent) {
		final ResultReceiver receiver = intent.getParcelableExtra("receiver");
		final String method = intent.getStringExtra("method");
		final String apiService = intent.getStringExtra("apiService");

		final Bundle b = new Bundle();
		try {
			callMethod(apiService, method, receiver, b);
		} catch (SocketTimeoutException e) {
			Log.e(TAG, e.getMessage());
			receiver.send(STATUS_CONNECTION_ERROR, b);
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			receiver.send(STATUS_ERROR, b);
		} catch (ClientProtocolException e) {
			Log.e(TAG, e.getMessage());
			receiver.send(STATUS_ERROR, b);
		} catch (IllegalArgumentException e) {
			Log.e(TAG, e.getMessage());
			receiver.send(STATUS_ILLEGAL_ARGUMENT, b);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
			receiver.send(STATUS_ERROR, b);
		}

		// Es importante terminar el servicio lo antes posible.
		this.stopSelf();
	}
	
	private void callMethod(String apiService, String method, ResultReceiver receiver, 
			Bundle b) throws ClientProtocolException, IOException, JSONException {
		final String url = this.baseURL + apiService + ".groovy?method=" + method;
		final DefaultHttpClient client = new DefaultHttpClient();
		final HttpResponse response = client.execute(new HttpGet(url));
		if ( response.getStatusLine().getStatusCode() != 200 ) {
			throw new IllegalArgumentException(response.getStatusLine().toString());
		}
		final String jsonToParse = EntityUtils.toString(response.getEntity());
		
		b.putSerializable("return", (Serializable) jsonToParse);

		receiver.send(STATUS_OK, b);
	}
	
	/*private List<Tweet> parseJSON(final String jsonToParse) throws JSONException {
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		Log.d(TAG, "Json received: " + jsonToParse);
		
		JSONObject parsedJson = new JSONObject(jsonToParse);
		if ( !parsedJson.has("results")) {
			throw new JSONException("results not found");
		}
		
		JSONArray results = parsedJson.getJSONArray("results");
		for ( int i = 0; i < results.length(); i++ ) {
			JSONObject bornToBeTweet = results.getJSONObject(i);
			tweets.add(TweetImpl.fromJSON(bornToBeTweet));			
		}
		
		return tweets;
	}*/
}
