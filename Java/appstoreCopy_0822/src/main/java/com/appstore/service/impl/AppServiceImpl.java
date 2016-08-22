package com.appstore.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.appstore.dao.AppDao;
//import com.appstore.dao.Appdao;
import com.appstore.entity.App;
import com.appstore.service.AppService;

public class AppServiceImpl implements AppService{
	//service implementation. business layer.
	//Dao is get, service is deal with data.
	//may apply different Dao to get different data to operate then move back or something.
	
	private AppDao appDao;
	
//	@Override
//	public App getApp(App app) {
//		// TODO Auto-generated method stub
//		return this.appDao.readApp(app);
//	}
	// add a new getApp(string)
	@Override
	@Transactional
	public App getApp(String appID) {
		// TODO Auto-generated method stub
		return this.appDao.readApp(appID);
	}
	
	@Override
	@Transactional
	public App updateScore(App app, int score) {
		// TODO Auto-generated method stub
		return this.appDao.updateScore(app, score);
	}
	
	@Override
	@Transactional
	public List<App> readRecomApps(List<String> appIDs) {
		// TODO Auto-generated method stub
		return this.appDao.readRecomApps(appIDs);
	}

	@Override
	@Transactional
	public List<App> readTopNApps() {
		// TODO Auto-generated method stub
		return this.appDao.readTopNApps();
	}
	

	public AppDao getAppDao() {
		return appDao;
	}

	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}
	//define get/set means the initial of appDao needs javaBean 



}
