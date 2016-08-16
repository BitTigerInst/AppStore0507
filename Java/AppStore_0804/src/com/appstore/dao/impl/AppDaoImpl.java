package com.appstore.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.appstore.dao.AppDao;
import com.appstore.entity.App;

public class AppDaoImpl implements AppDao{

	private SessionFactory sessionFactory;
	// session from hibernate. conversation from hib to DB
	
	
	@Override
	public App readApp(App app) {
		// TODO Auto-generated method stub
		return (App) this.getSession().get(App.class, app.getAppid());
	}
	//get(App.class, app.getAppid()) returns an object, so use (App)
	//load an app
	@Override
	public List<App> readRecomApps(List<String> appIDs) {
		// TODO Auto-generated method stub
		List<App> apps = new ArrayList<App>();
		if(appIDs.size()==0) 
			return apps;
		// appIDs is null, return null
		
		for(String appid: appIDs){
			apps.add((App) this.getSession().get(App.class, appid));
		}
		return apps;
	}
	
	@Override
	@Transactional
	public List<App> readTopNApps() {
		// TODO Auto-generated method stub
		Query query = this.getSession().createQuery("from App app order by app.score desc").setMaxResults(10);
		//the query is run in java?not sql?
		//import from hibernate query
		List<App> apps = (List<App>) query.list();
		return apps;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	//current DB operation


//	public void setSession(Session session) {
//		this.session = session;
//	}



}
