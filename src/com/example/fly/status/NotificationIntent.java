package com.example.fly.status;

import android.content.Intent;

import com.example.fly.mobile.SplashActivity;

public class NotificationIntent extends Intent {
	
	public NotificationIntent(SplashActivity context){
		super(Intent.ACTION_SYNC, null, context, NotificationService.class);
	}
}
