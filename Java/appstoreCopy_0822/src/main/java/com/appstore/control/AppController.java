package com.appstore.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appstore.entity.App;
import com.appstore.service.AppService;

@Controller
//use to define this class is controller for the server
public class AppController {
	
	//claim that this need javabean to initial
	@Resource
	private AppService appServ;
	
//-------------------Retrieve Top 10 Apps--------------------------------------------------------
	@RequestMapping(value = "/app/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// what is this? according this, know what function to use. the input data is json
	public ResponseEntity<List<App>> getTopApps() {
		//int topN = 10;
		List<App> apps = this.getAppServ().readTopNApps();
		if (apps == null) {
			System.out.println(" no apps found");
			return new ResponseEntity<List<App>>(HttpStatus.NOT_FOUND);
			//Restful Api??
		}
		System.out.println("/****************************************************/"+"\r\n"+
		                   "                    Retrievw top 10 Apps              "+"\r\n"+
				           "/****************************************************/");
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<App>>(apps, headers, HttpStatus.OK);
	}
	//-------------------Retrieve recommendation Apps--------------------------------------------------------
	@RequestMapping(value = "/app/getRecom/similarapp/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<App>> getRecomApps(@RequestBody List<String> appIDs) {
		//@RequestBody get the String from json , jacson? done by framework
		List<App> apps = this.getAppServ().readRecomApps(appIDs);
		if (apps == null||apps.size()==0) {
			System.out.println("AppController 65: no recommandation apps found");
			return new ResponseEntity<List<App>>(HttpStatus.NOT_FOUND);
		}
		System.out.println("/****************************************************/"+"\r\n"+
		                   "                    Retrievw 5 Recom Apps              "+"\r\n"+
				           "/****************************************************/");
		System.out.println(apps.size()+" "+(apps.size()==0?null:apps.get(0).getAppid())+" "+appIDs.get(0)+" "+appIDs.size());
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<App>>(apps, headers, HttpStatus.OK);
	}
	 
	//------------------- Update a App --------------------------------------------------------
	
//	@RequestMapping(value = "/app/updateScore/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<App> updateApp(@RequestBody App app) {	
//		App currentApp = this.getAppServ().getApp(app);
//				
//		if (currentApp==null) {
//			System.out.println("App with id " + currentApp.getAppid() + " not found");
//			return new ResponseEntity<App>(HttpStatus.NOT_FOUND);
//		}else if(currentApp.getAppid().equals(app.getAppid()))
//			return new ResponseEntity<App>(HttpStatus.CONFLICT);
//		// add the Score
//		
//		int curScore = currentApp.getScore()+1;
//		App updatedApp = this.getAppServ().updateScore(app,curScore);
//		return new ResponseEntity<App>(updatedApp, HttpStatus.OK);
//	}
	
	//update using string
	@RequestMapping(value = "/app/changeScore/", method = RequestMethod.POST)
	public ResponseEntity<App> updateApp(@RequestBody String appID) {
		App currentApp = this.getAppServ().getApp(appID);
				
		if (currentApp==null) {
			System.out.println("App with id " + appID + " not found");
			return new ResponseEntity<App>(HttpStatus.NOT_FOUND);
		}
		//else if(currentApp.getAppid().equals(app.getAppid()))
		//	return new ResponseEntity<App>(HttpStatus.CONFLICT);
		
		// add the Score
		int curScore = currentApp.getScore()+1;
		App updatedApp = this.getAppServ().updateScore(currentApp,curScore);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<App>(updatedApp,headers, HttpStatus.OK);
	}
	
	
	//-------------------Retrieve Single App--------------------------------------------------------
//	@RequestMapping(value = "/app/{appid}", method = RequestMethod.GET)
//	public ResponseEntity<App> getApp(@PathVariable("appid") String appid) {
//		System.out.println("Fetching app with id " + appid);
//		App app = this.getAppServ().getApp(appid);
//		if (app == null) {
//			System.out.println("app with id " + appid + " not found");
//			return new ResponseEntity<App>(HttpStatus.NOT_FOUND);
//		}
//		HttpHeaders headers = new HttpHeaders();
//		return new ResponseEntity<App>(app,headers, HttpStatus.OK);
//	}
	
	
	public AppService getAppServ() {
		return appServ;
	}

	public void setAppServ(AppService appServ) {
		this.appServ = appServ;
	}

}
