package com.appstore.dao;

import java.util.List;

import com.appstore.entity.App;

public interface AppDao {
	//interface? Dao function?
	//get DB data from Dao
	
	
	//public App readApp(App app);
	
	// add a overload readApp
	public App readApp(String appID);
	
	public List<App> readRecomApps(List<String> appIDs);
	// get the recommend app from DB
	public List<App> readTopNApps();
	
	// add updateScore function
	public App updateScore(App app, int score);
}
