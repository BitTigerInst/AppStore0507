package com.appstore.dao;

import java.util.List;

import com.appstore.entity.App;

public interface AppDao {
	//interface? Dao function?
	//get DB data from Dao
	
	
	public App readApp(App app);
	public List<App> readRecomApps(List<String> appIDs);
	// get the recommend app from DB
	public List<App> readTopNApps();
}
