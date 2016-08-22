(function(){
	  var homeApp = angular.module('top', []);
	  
	  homeApp.controller('appList',['$http','$window',function($http,$window){
		  this.appList = [];//jobs;
		  this.showApp =false;
		  
		  this.appToShow = null;
		  this.appToRecom = null;
		  
		  var container = this;
		  
		  var req = {
				  method: 'GET',
				  url: "http://localhost:8080/appstoreCopy_0822/app/",
				  headers: {
				    'Content-Type': "application/json",
				    	'dataType': 'json'
				  }
				 };

		  $http(req).then(function(data) {
			  container.appList = data.data;
			  container.appsToShow = container.appList[0];//appList is a list? where define it's a list?
			  
			  console.log("reveive array successfully");
		    });
		  
		  this.selectApp = function(app){ 
			  container.appToShow = app;
			  container.showApp =true;
			  console.log(JSON.stringify(app.top5AppsArray));
			  var reqest = {
					  method: 'POST',
					  url: "http://localhost:8080/appstoreCopy_0822/app/getRecom/similarapp/",
					  data: app.top5AppsArray,
					  headers: {
						'Accept': 'application/json',
					    'Content-Type': "application/json",
					    'dataType': 'json'
					  }
					 };
			  $http(reqest).then(
					  function(data) {
						
						  if(data.data.length>0){
							  container.appsToRecom = data.data;
						  }else{
							  container.appsToRecom = [];
						  }
							  
						  console.log("reveive array successfully");
					  },function(data){	    	  
						  alert("Note: no recommandation for this app");
					  }
			    );
//			  $http.get('http://localhost:8080/AppStore_1_3/app/getRecom/similarapp/')
//			  .success(function(data,status){
//				  console.log(JSON.stringify(data));
//			      })//end-$http.get:success
//			      .error(function(data){	    	  
//			    	  alert("Error Occurs"+JSON.stringify(data));
//			    	  })//end-$http.end:error
//			    ;//end-call back definitions
		  };//end-seleCate
		  
		  this.updateScore = function(appid){
			  var reqest = {
					  method: 'POST',
					  url: "http://localhost:8080/appstoreCopy_0822/app/changeScore/",
					  data: appid,
					  headers: {
						'Accept': 'application/json',
					    'Content-Type': "application/json",
					    'dataType': 'json'
					  }
					 };
			  $http(reqest).then(
					  function(data) {							  
						  console.log("reveive array successfully");
					  },function(data){	    	  
						  alert("Note: no recommandation for this app");
					  }
			    );
			  container.showApp = false; // as the function of jump to index!
//			  console.log(JSON.stringify(app.top5AppsArray));
//			  var reqest = {
//					  method: 'POST',
//					  url: "http://localhost:8080/AppStore_0804/app/updateScore/",
//					  data: app.appID,  //JSON.stringify(appID),
//					  headers: {
//						'Accept': 'application/json',
//					    'Content-Type': "application/json",
//					    'dataType': 'json'
//					  }
//					 };
//			  // below is when data is receive by the angular? right?
//			  $http(reqest).then(
//					  function(data) {	
//						  if(data.data.length>0){
//						  console.log("updated score received");
//						  }
//					  },function(data){	    	  
//						  alert("Note: no updated score receive for this app");
//					  }
//			  );
		  };
		  
		  this.goBackToMain = function(){
			  container.showApp = false;
		  };
		  
	  }]);//end-Controller-initContainer
	  
	})();//end-function
