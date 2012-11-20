package com.example.fly.api;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

import com.example.fly.utils.CallBack;

public class ApiResultReceiver extends ResultReceiver {

	public CallBack callback;
	
	public ApiResultReceiver(Handler handler) {
		super(handler);
	}
	
	@Override
	protected void onReceiveResult(int resultCode, Bundle resultData) {
		super.onReceiveResult(resultCode, resultData);
		if (resultCode == ApiService.STATUS_OK) {

			String responseString = (String) resultData
					.getSerializable("return");
			JSONObject response = new JSONObject();
			try {
				response = new JSONObject(responseString);
			} catch (JSONException e) {
				Log.d("Error", "Invalid");
			}
			callback.handleResponse(response);
			
		} else if (resultCode == ApiService.STATUS_CONNECTION_ERROR) {
			Log.d("Api Service", "Connection error.");
		} else {
			Log.d("Api Service", "Unknown error.");
		}
	}
	
	public void setCallBack (CallBack callback) {
		this.callback = callback;
	};

}
