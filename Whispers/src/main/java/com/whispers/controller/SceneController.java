/**
 * Created on 15 Jan, 2015
 */

package com.whispers.controller;

// java imports
import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.List;

// spring framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// json library imports
import com.fasterxml.jackson.databind.ObjectMapper;

// application imports
import com.whispers.beans.Scene;
import com.whispers.beans.User;
import com.whispers.service.SceneService;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Controller class accepts incoming requests and delegates to service for processing
 */

@Controller
public class SceneController {

	/**
	 * The service class instance
	 */
	@Autowired
	private SceneService sceneService	= null;

	/**
	 * The JSON object mapper
	 */
	private ObjectMapper jsonObjectMapper = new ObjectMapper();

	/**
	 * Invokes service method to add record.
	 * 
	 * @param sceneRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/submitDeviceDetails", method=RequestMethod.POST)
	@ResponseBody
	public String addDeviceRecord(@RequestBody String sceneRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addDeviceRecord()...");

		String responseData = null;
		Scene scene = null;
		//List<User> userList = new ArrayList<User>();
		//String userRequestData = "{\"firstName\": \"testFirstName\",\"lastName\": \"testLastName\",\"userName\": \"testUserName\",\"email\": \"mail@gmail.com\",\"displayName\":\"testDisplayName\",\"mobileNumber\":1324657891,\"password\": \"123\",\"roleId\": 4,\"dateOfBirth\": \"1989-12-12\",\"gender\": \"Male\",\"address1\": \"Address1\",\"address2\":\"Address2\",\"country\": \"Country\",\"state\": \"State\",\"place\": \"Place\",\"zip\": 134645,\"status\": 1,\"createdOn\": \"2015-01-01\",\"createdBy\": 1,\"modifiedOn\": \"2015-01-01\",\"modifiedBy\": 1}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ sceneRequestData);

			// Prepare user entity from request JSON data
			scene = jsonObjectMapper.readValue(sceneRequestData, Scene.class);
			Log.logMessage("INFO", this.getClass().getName(), "scene data: "+ scene.toString());

			// Call service to add record and get list of updated records
			scene = sceneService.addDeviceRecord(scene);
			
			if(scene.getId() != -1){
				responseData = "[{\"success\":true,\"messages\":{},\"response\":{}}]";
			}else{
				responseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error.\"},\"response\":{}}]";
			}								
			
		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			responseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error.\"},\"response\":{}}]";
			
			return responseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Response data (json): " + responseData);

		return responseData;

	} //addDeviceDetailsRecord() method ends

	
	/**
	 * Invokes service method to add record.
	 * 
	 * @param sceneRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/addSceneRecord", method=RequestMethod.POST)
	@ResponseBody
	public String addSceneRecord(@RequestBody String sceneRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSceneRecord()...");

		String sceneResponseData = null;
		Scene scene = null;
		//String sceneRequestData = "{\"sceneTitle\":\"Scene Title\",\"sceneImage\":\"Scene Image\",\"sceneShortText\":\"Scene Short Description\",\"sceneDescription\":\"Scene Description\"}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "scene request data received: "+ sceneRequestData);

			// Prepare scene entity from request JSON data
			scene = jsonObjectMapper.readValue(sceneRequestData, Scene.class);
			Log.logMessage("INFO", this.getClass().getName(), "scene data: "+ scene.toString());

			// Call service to add record and get updated record
			scene = sceneService.addSceneRecord(scene);

			// Prepare JSON string from entity bean
			sceneResponseData = jsonObjectMapper.writeValueAsString(scene);

			// Prepare final response as JSON string
			sceneResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"scene\":" + sceneResponseData + "}}]";

		} catch(DataTruncation exception){
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			sceneResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Image Data too long.\"},\"response\":{}}]";
			
			return sceneResponseData;
		}catch (Exception exception) {
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			sceneResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return sceneResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneRecord(): Response data (json): " + sceneResponseData);

		return sceneResponseData;

	} //addSceneRecord() method ends


	/**
	 * Invokes service method to update record.
	 * 
	 * @param sceneRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/updateSceneRecord", method=RequestMethod.POST)
	@ResponseBody
	public String updateSceneRecord(@RequestBody String sceneRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateSceneRecord()...");

		String sceneResponseData = null;
		Scene scene = null;
		List<Scene> sceneList = null;
		//String sceneRequestData = "{\"id\":1,\"sceneTitle\":\"Scene Title Update\",\"dateSubmitted\":\"2015-05-05\",\"submittedBy\":1,\"sceneImage\":\"Scene Image\",\"sceneUploadType\":\"original\",\"sceneShortText\":\"Scene Short Description Update \",\"sceneDescription\":\"Scene update Description\",\"allowComments\":0,\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1,\"createdBy\":1,\"createdOn\":\"2015-01-01\",\"status\":1}";
		//String sceneRequestData = "{\"id\":11, \"scenePublished\":1, \"modifiedBy\":1}";
		
				try {
			Log.logMessage("INFO", this.getClass().getName(), "scene request data received: "+ sceneRequestData);

			// Prepare scene entity from request JSON data
			scene = jsonObjectMapper.readValue(sceneRequestData, Scene.class);
			Log.logMessage("INFO", this.getClass().getName(), "scene data: "+ scene.toString());

			// Call service to update record and get updated list of records
			sceneList = sceneService.updateSceneRecord(scene);

			// Prepare JSON string from entity bean
			sceneResponseData = jsonObjectMapper.writeValueAsString(sceneList);

			// Prepare final response as JSON string
			sceneResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"scenes\":" + sceneResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making update request to server. Error: "+ exception.getMessage());
			sceneResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be updated.\"},\"response\":{}}]";

			return sceneResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSceneRecord(): Update record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSceneRecord(): Response data (json): " + sceneResponseData);

		return sceneResponseData;

	} //updateSceneRecord() method ends


	/**
	 * Invokes service method to delete one or more records. If records need to be softdeleted then Status flag from table would be set to 0.
	 * 
	 * @param sceneRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/deleteSceneRecords", method=RequestMethod.POST)
	@ResponseBody
	public String deleteSceneRecord(@RequestBody String requestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteSceneRecord()...");

		String sceneResponseData = null;
		List<Scene> sceneList = null;
		List<User> userList = null;
		//String requestData = "[{\"id\": 10, \"roleId\": 2, \"modifiedBy\":1}]";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "scene request data received: "+ requestData);

			// Prepare scene entity from request JSON data
			userList = jsonObjectMapper.readValue(requestData, jsonObjectMapper.getTypeFactory().constructCollectionType( List.class, User.class));
			Log.logMessage("INFO", this.getClass().getName(), "request data: "+ userList.toString());

			// Call service to delete record(s) and get list of updated records
			sceneList = sceneService.deleteSceneRecord(userList);

			// Prepare JSON string from entity bean
			sceneResponseData = jsonObjectMapper.writeValueAsString(sceneList);

			// Prepare final response as JSON string
			sceneResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"scenes\":" + sceneResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making delete request to server. Error: "+ exception.getMessage());
			sceneResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. One or more record couldn't be deleted.\"},\"response\":{}}]";
			
			return sceneResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSceneRecord(): Delete record(s) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSceneRecord(): Response data (json): " + sceneResponseData);

		return sceneResponseData;

	} //deleteSceneRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param sceneRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchSceneRecord", method=RequestMethod.POST)
	@ResponseBody
	public String fetchSceneRecord(@RequestBody String sceneRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneRecord()...");

		String sceneResponseData = null;
		Scene scene = null;
		//String sceneRequestData = "{\"id\":2}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "scene request data received: "+ sceneRequestData);

			// Prepare scene entity from request JSON data
			scene = jsonObjectMapper.readValue(sceneRequestData, Scene.class);
			Log.logMessage("INFO", this.getClass().getName(), "scene data: "+ scene.toString());

			// Call service to fetch record and get entity bean
			scene = sceneService.fetchSceneRecord(scene);				
			
			// Prepare JSON string from entity bean
			sceneResponseData = jsonObjectMapper.writeValueAsString(scene);

			sceneResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"scene\":" + sceneResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			sceneResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return sceneResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecord(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecord(): Response data (json): " + sceneResponseData);

		return sceneResponseData;

	} //fetchSceneRecord() method ends


	/**
	 * Invokes service method to fetch all records.
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchAllSceneRecords", method=RequestMethod.POST)
	@ResponseBody
	public String fetchAllSceneRecord(@RequestBody String sceneRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllSceneRecord()...");

		String sceneResponseData = null;
		List<Scene> sceneList = new ArrayList<Scene>();
		User user = null;
		//String sceneRequestData = "{\"id\":2, \"roleId\":2}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "scene request data received: "+ sceneRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(sceneRequestData, User.class);
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ user.toString());

			// Call service to fetch all records
			sceneList = sceneService.fetchAllSceneRecord(user);

			// Prepare JSON string from list
			sceneResponseData = jsonObjectMapper.writeValueAsString(sceneList);

			// Prepare final response as JSON string
			sceneResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"scenes\":" + sceneResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			sceneResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return sceneResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSceneRecord(): Fetch all record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSceneRecord(): Response data (json): " + sceneResponseData);

		return sceneResponseData;

	} //fetchAllSceneRecord() method ends


	/**
	 * Invokes service method to fetch records based on criteria.
	 * 
	 * @param sceneRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/getSceneDetails", method=RequestMethod.POST)
	@ResponseBody
	public String fetchSceneRecords(@RequestBody String sceneRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneRecords()...");

		String sceneResponseData = null;
		Scene scene = null;
		List<Scene> sceneList = new ArrayList<Scene>();
		//String sceneRequestData = "{\"dateSubmitted\":\"2015-01-01\"}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "scene request data received: "+ sceneRequestData);

			// Prepare scene entity from request JSON data
			scene = jsonObjectMapper.readValue(sceneRequestData, Scene.class);
			Log.logMessage("INFO", this.getClass().getName(), "scene data: "+ scene.toString());

			// Call service to fetch records based on criteria
			sceneList = sceneService.fetchSceneRecords(scene);

			// Prepare JSON string from entity bean
			sceneResponseData = jsonObjectMapper.writeValueAsString(sceneList);

			// Prepare final response as JSON string
			sceneResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"scenes\":" + sceneResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch records request to server. Error: "+ exception.getMessage());
			sceneResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return sceneResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecords(): Fetch record (based on criteria) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecords(): Response data (json): " + sceneResponseData);

		return sceneResponseData;

	} //fetchSceneRecords() method ends


	/**
	 * Invokes service method to fetch T&C page URL.
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/getTCPage", method=RequestMethod.GET)
	@ResponseBody
	public String getTCPage() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering getTCPage()...");

		String tcResponseData = null;

		try {

			// Call service to fetch T&C page URL
			tcResponseData = sceneService.getTCPage();

			// Prepare JSON string from list
			tcResponseData = jsonObjectMapper.writeValueAsString(tcResponseData);

			// Prepare final response as JSON string
			tcResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"tcPageLink\":" + tcResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch T&C page URL request to server. Error: "+ exception.getMessage());
			tcResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return tcResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchTCPage(): Fetch T&C page URL call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchTCPage(): Response data (json): " + tcResponseData);

		return tcResponseData;

	} //fetchTCPage() method ends

	/**
	 * Invokes service method to fetch scene wise user report
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchSceneUserReport", method=RequestMethod.GET)
	@ResponseBody
	public String fetchSceneUserReport() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneUserReport()...");

		String sceneResponseData = null;
		List<Scene> sceneList = new ArrayList<Scene>();
		
		try {

			// Call service to fetch all records
			sceneList = sceneService.fetchSceneUserReport();

			// Prepare JSON string from list
			sceneResponseData = jsonObjectMapper.writeValueAsString(sceneList);

			// Prepare final response as JSON string
			sceneResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"scenes\":" + sceneResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			sceneResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return sceneResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneUserReport(): Fetch fetchSceneUserReport call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneUserReport(): Response data (json): " + sceneResponseData);

		return sceneResponseData;

	} //fetchSceneUserReport() method ends


	@RequestMapping(value="/web/fetchUserFeedbackReport", method=RequestMethod.GET)
	@ResponseBody
	public String fetchSceneFeedbackReport() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserFeedbackReport()...");

		String sceneResponseData = null;
		List<Scene> sceneList = new ArrayList<Scene>();
		
		try {
			// Call service to fetch all records
			sceneList = sceneService.fetchSceneFeedbackReport();

			// Prepare JSON string from list
			sceneResponseData = jsonObjectMapper.writeValueAsString(sceneList);

			// Prepare final response as JSON string
			sceneResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"scenes\":" + sceneResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			sceneResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return sceneResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserFeedbackReport(): Fetch fetchSceneFeedbackReport call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserFeedbackReport(): Response data (json): " + sceneResponseData);

		return sceneResponseData;

	} //fetchSceneUserReport() method ends
	

}