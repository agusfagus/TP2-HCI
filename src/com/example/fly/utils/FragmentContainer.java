package com.example.fly.utils;

import android.app.Fragment;

public class FragmentContainer {

	private Fragment instance;
	private String tabTag;
	
	public FragmentContainer(String tabTag, Fragment inst) {
		this.instance = inst;
		this.tabTag = tabTag;
	}
	
	public Fragment getInstance() {
		return this.instance;
	}
	
	public String getTag() {
		return this.tabTag;
	}
	
}
