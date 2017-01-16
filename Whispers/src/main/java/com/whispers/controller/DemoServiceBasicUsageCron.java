package com.whispers.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// spring framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

// application imports
import com.whispers.beans.Scene;
import com.whispers.beans.User;
import com.whispers.service.SceneService;
import com.whispers.utils.Log;

public class DemoServiceBasicUsageCron{ 
	
	/**
	 * The service class instance
	 */
	@Autowired
	SceneService sceneService;
	
	// this should toggle between 16 and 17 depending on EST and EDT
	@Scheduled(cron="0 0 17 ? * MON-FRI") 
	public void demoServiceMethod() throws Exception{	
		
		List<Scene> sceneList = new ArrayList<Scene>();
		User user = new User();		
		String todayDate = null;
		SimpleDateFormat sdf = null;
		Date date  = null;
		try{	
			user.setId(1);
			user.setRoleId(1);
			
			date  = new Date();
				
			sdf = new SimpleDateFormat("yyyy-MM-dd");	  
			
			todayDate = sdf.format(date);								
					
			sceneList = sceneService.fetchAllSceneRecord(user);		
		
			for(Scene s : sceneList){						
				if(s.getPublishDate()!= null){					
					String getCurrentDate = sdf.format(s.getPublishDate());
					if(todayDate.equals(getCurrentDate)){											
						s.setScenePublished(1);		
						s.setModifiedBy(1);						
						sceneService.updateSceneRecord(s);										
					}
				}				
			}			
			Log.logMessage("INFO","DemoServiceBasicUsageCron","Updated SuccessFully");
		}catch (Exception exception) { 
			Log.logMessage("ERROR", "DemoServiceBasicUsageCron","Error occured while making fetch all records request to server. Error: "+ exception.getMessage());		
		}		
	}
}