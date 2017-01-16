/**
 * Created on 15 Jan, 2015
 */

package com.whispers.service;

// java imports
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
// spring framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// application imports
import com.whispers.beans.Scene;
import com.whispers.beans.User;
import com.whispers.dao.SceneDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Service class implements business logic as well as delegates CRUD operations to DAO
 */

@Service
public class SceneService {

	/**
	 * The DAO class instance
	 */
	@Autowired
	private SceneDAO sceneDAO;


	public static final String PUSHWOOSH_SERVICE_BASE_URL = "https://cp.pushwoosh.com/json/1.3/";
    //Production-52.6.72.131 : AUTH_TOKEN and APPLICATION_CODE	
	private static final String AUTH_TOKEN = "3JQqcnPvQGzbpqW3apF2KinuL1I3hslm88XhpXT9uLl18z5fVYgDvY9rH5tdfMTu2uJA7EVpXrAGiE8eikLN";
    private static final String APPLICATION_CODE = "8F0C6-DAD03";
    
	//Test-52.2.21.97 : AUTH_TOKEN and APPLICATION_CODE	
  	/*
  	private static final String AUTH_TOKEN = "rgCyw6XHIQcuT1CByLO4KuiavPhJtLeEtP5p5Fh1vRm7FzKqqJs7tFrDFRA6CUG4NUWKW4LLGu6oS3CYOOKf";
  	private static final String APPLICATION_CODE = "FBD25-4EC2D";
    */  
    private static final String DEFAULT_NOTIFICATION = "12@12NOON is Ready!  Whisper, Laugh, Shout…Tell the Story!";
    
    
    
    
    /**
	 * 
	 * Invokes dao method to add record.
	 * 
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the Scene 	 {@link Scene }
	 */
	public Scene addDeviceRecord(Scene scene) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSceneRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "scene data : "+ scene.toString());

			// Call dao to add record
			scene = sceneDAO.addDeviceRecord(scene);
			
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneRecord(): Insert record call successfull...");

		return scene;

	} //addSceneRecord() method ends

    
	/**
	 * 
	 * Invokes dao method to add record.
	 * 
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the Scene 	 {@link Scene }
	 */
	public Scene addSceneRecord(Scene scene) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSceneRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "scene data : "+ scene.toString());

			// Call dao to add record
			scene = sceneDAO.addSceneRecord(scene);
			
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneRecord(): Insert record call successfull...");

		return scene;

	} //addSceneRecord() method ends


	/**
	 * Invokes dao method to update record.
	 * 
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the list of Scene 	 {@link List<Scene> }
	 */
	public List<Scene> updateSceneRecord( Scene scene ) throws Exception {

		List<Scene> sceneList = new ArrayList<Scene>(); 
		
		Log.logMessage("INFO", this.getClass().getName(), "Entering updateSceneRecord()...");						
		
		if(scene.getScenePublished() == null || scene.getScenePublished() == 0){			
			sceneDAO.updateSceneRecord(scene);
		}else{			
			sceneDAO.publishSceneRecord(scene);
			sendNotification(scene);
		}
			
		Log.logMessage("INFO", this.getClass().getName(), "scene data : "+ scene.toString());
	
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSceneRecord(): Update record call successfull...");

		return sceneList;

	} //updateSceneRecord() method ends


	/**
	 * Invokes dao method to delete a record.
	 * 
	 * @param sceneList	The list of Scene entity
	 *
	 * @return 	 Returns the list of Scene 	 {@link List<Scene> }
	 */
	public List <Scene> deleteSceneRecord( List <User> deleteSceneRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteSceneRecord()...");

		Integer rowsAffectedCount= -1;
		List<Scene> sceneList = null;
		User user = deleteSceneRecords.get(0);
					// Call dao to delete record
			rowsAffectedCount = sceneDAO.deleteSceneRecord(deleteSceneRecords);
				
			if(rowsAffectedCount.intValue() == 0) {
				throw new Exception("No Scene record deleted.");
			}
			user.setId(deleteSceneRecords.get(0).getModifiedBy());
			// Call service to fetch updated records
			sceneList = sceneDAO.fetchAllSceneRecord(user);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSceneRecord(): Delete record call successfull...");

		return sceneList;

	} //deleteSceneRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the Scene 	 {@link Scene }
	 */
	public Scene fetchSceneRecord( Scene scene )  throws Exception{

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneRecord()...");

		
			// Call dao to fetch record
			scene = sceneDAO.fetchSceneRecord(scene);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecord(): Fetch record call successfull...");

		return scene;

	} //fetchSceneRecord() method ends


	/**
	 * Invokes dao method to fetch all records.
	 * @param user 
	 * 
	 * @return 	 Returns the list of Scene 	 {@link List<Scene> }
	 */
	public List <Scene> fetchAllSceneRecord(User user) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllSceneRecord()...");

		List<Scene> sceneList = null;
	
			// Call service to fetch record
			sceneList = sceneDAO.fetchAllSceneRecord(user);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSceneRecord(): Fetch all record call successfull...");

		return sceneList;

	} //fetchAllSceneRecord() method ends


	/**
	 * Invokes dao method to fetch records based on criteria.
	 * 
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the list of Scene 	 {@link List<Scene< }
	 */
	public List<Scene> fetchSceneRecords( Scene scene ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneRecords()...");

		List<Scene> sceneList = null;

		
			// Call dao to fetch records based on criteria
			sceneList = sceneDAO.fetchSceneRecords(scene);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecords(): Fetch record (based on criteria) call successfull...");

		return sceneList;

	} //fetchSceneRecords() method ends


	/**
	 * Invokes dao method to fetch T&C page link.
	 * 
	 * @param none
	 *
	 * @return 	 Returns the T&C page link 	 {@link  }
	 */
	public String getTCPage() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering getTCPage()...");
		String tcPageLink = null;
		
		
			// Call dao to get TC Page
			tcPageLink = sceneDAO.getTCPage();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecords(): Fetch record (based on criteria) call successfull...");

		return tcPageLink;

	} //fetchSceneRecords() method ends


	/**
	 * Invokes dao method to fetchSceneUserReport
	 * @param  
	 * 
	 * @return 	 Returns the list of Scene 	 {@link List<Scene> }
	 */
	public List <Scene> fetchSceneUserReport() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneUserReport()...");

		List<Scene> sceneList = null;

			// Call service to fetch record
			sceneList = sceneDAO.fetchSceneUserReport();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneUserReport(): fetchSceneUserReport call successfull...");

		return sceneList;

	} //fetchSceneUserReport() method ends

	
	public List <Scene> fetchSceneFeedbackReport() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneFeedbackReport()...");

		List<Scene> sceneList = null;
			// Call service to fetch record
			sceneList = sceneDAO.fetchSceneFeedbackReport();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneFeedbackReport(): fetchSceneFeedbackReport call successfull...");

		return sceneList;

	} //fetchSceneFeedbackReport() method ends
	
	public void sendNotification(Scene scene ) throws Exception{
				
		scene = sceneDAO.fetchSceneRecord(scene);		
		
		String method = "createMessage";
        URL url = new URL(PUSHWOOSH_SERVICE_BASE_URL + method);
                      
        if(scene.getNotificationText().isEmpty()){
        	scene.setNotificationText(DEFAULT_NOTIFICATION);
        }
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();        
        
        JSONArray notificationsArray = new JSONArray()
                .put(new JSONObject().put("send_date",dateFormat.format(date))
                					 .put("ignore_user_timezone",false)	
                                     .put("content",scene.getNotificationText())
                                     .put("link", ""));
 
        JSONObject requestObject = new JSONObject()
                .put("application", APPLICATION_CODE)
                .put("auth",AUTH_TOKEN)                
                .put("notifications", notificationsArray);
 
        JSONObject mainRequest = new JSONObject().put("request", requestObject);
        SendServerRequest.sendJSONRequest(url,mainRequest.toString());               
	}
}