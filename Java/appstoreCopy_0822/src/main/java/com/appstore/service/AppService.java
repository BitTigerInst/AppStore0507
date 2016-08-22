package com.appstore.service;

import java.util.List;

import com.appstore.entity.App;

public interface AppService {
	//public App getApp(App app);
	// add a overload readApp
	public App getApp(String appID);
	
	public List<App> readRecomApps(List<String> appIDs);
	public List<App> readTopNApps();
	// get the initial popular apps
	
	//add the interface to update score
	public App updateScore(App app, int score);
}
