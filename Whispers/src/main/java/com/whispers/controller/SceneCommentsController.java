/**
 * Created on 21 Jan, 2015
 */

package com.whispers.controller;

// java imports
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
import com.whispers.beans.SceneComments;
import com.whispers.service.SceneCommentsService;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Controller class accepts incoming requests and delegates to service for processing
 */

@Controller
public class SceneCommentsController {

	/**
	 * The service class instance
	 */
	@Autowired
	private SceneCommentsService sceneCommentsService	= null;

	/**
	 * The JSON object mapper
	 */
	private ObjectMapper jsonObjectMapper = new ObjectMapper();

	/**
	 * Invokes service method to add record.
	 * 
	 * @param sceneCommentsRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/submitComment", method=RequestMethod.POST)
	@ResponseBody
	public String addSceneCommentsRecord(@RequestBody String sceneCommentsRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSceneCommentsRecord()...");

		String sceneCommentsResponseData = null;
		SceneComments sceneComments = null;
		List<SceneComments> sceneCommentsList = null;
		//String sceneCommentsRequestData = "{\"dateCommentSubmitted\":\"2015-01-01\",\"submittedById\":1,\"comment\":\"comment some garbage Text\",\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1,\"createdBy\":1,\"createdOn\":\"2015-01-01\",\"status\":1}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments request data received: "+ sceneCommentsRequestData);

			// Prepare sceneComments entity from request JSON data
			sceneComments = jsonObjectMapper.readValue(sceneCommentsRequestData, SceneComments.class);
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments data: "+ sceneComments.toString());

			// Call service to add record and get list of updated records
			sceneCommentsList = sceneCommentsService.addSceneCommentsRecord(sceneComments);

			// Prepare JSON string from entity bean
			sceneCommentsResponseData = jsonObjectMapper.writeValueAsString(sceneCommentsList);

			// Prepare final response as JSON string
			sceneCommentsResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sceneComments\":" + sceneCommentsResponseData + "}}]";
			
		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			sceneCommentsResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return sceneCommentsResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneCommentsRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneCommentsRecord(): Response data (json): " + sceneCommentsResponseData);

		return sceneCommentsResponseData;

	} //addSceneCommentsRecord() method ends


	/**
	 * Invokes service method to update record.
	 * 
	 * @param sceneCommentsRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/updateSceneCommentsRecord", method=RequestMethod.POST)
	@ResponseBody
	public String updateSceneCommentsRecord(@RequestBody String sceneCommentsRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateSceneCommentsRecord()...");

		String sceneCommentsResponseData = null;
		SceneComments sceneComments = null;
		List<SceneComments> sceneCommentsList = null;
		//String sceneCommentsRequestData = "{\"id\":1,\"dateCommentSubmitted\":\"2015-05-05\",\"submittedById\":1,\"comment\":\"comment some update garbage Text\",\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1,\"createdBy\":1,\"createdOn\":\"2015-01-01\",\"status\":1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments request data received: "+ sceneCommentsRequestData);

			// Prepare sceneComments entity from request JSON data
			sceneComments = jsonObjectMapper.readValue(sceneCommentsRequestData, SceneComments.class);
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments data: "+ sceneComments.toString());

			// Call service to update record and get updated list of records
			sceneCommentsList = sceneCommentsService.updateSceneCommentsRecord(sceneComments);

			// Prepare JSON string from entity bean
			sceneCommentsResponseData = jsonObjectMapper.writeValueAsString(sceneCommentsList);

			// Prepare final response as JSON string
			sceneCommentsResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sceneComments\":" + sceneCommentsResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making update request to server. Error: "+ exception.getMessage());
			sceneCommentsResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be updated.\"},\"response\":{}}]";

			return sceneCommentsResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSceneCommentsRecord(): Update record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSceneCommentsRecord(): Response data (json): " + sceneCommentsResponseData);

		return sceneCommentsResponseData;

	} //updateSceneCommentsRecord() method ends


	/**
	 * Invokes service method to delete one or more records. If records need to be softdeleted then Status flag from table would be set to 0.
	 * 
	 * @param sceneCommentsRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/deleteSceneCommentsRecords", method=RequestMethod.POST)
	@ResponseBody
	public String deleteSceneCommentsRecord(@RequestBody String sceneCommentsRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteSceneCommentsRecord()...");

		String sceneCommentsResponseData = null;
		List<SceneComments> sceneCommentsList = null;
		//String sceneCommentsRequestData = "[{\"id\": 5}]";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments request data received: "+ sceneCommentsRequestData);

			// Prepare sceneComments entity from request JSON data
			sceneCommentsList = jsonObjectMapper.readValue(sceneCommentsRequestData, jsonObjectMapper.getTypeFactory().constructCollectionType( List.class, SceneComments.class));
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments data: "+ sceneCommentsList.toString());

			// Call service to delete record(s) and get list of updated records
			sceneCommentsList = sceneCommentsService.deleteSceneCommentsRecord(sceneCommentsList);

			// Prepare JSON string from entity bean
			sceneCommentsResponseData = jsonObjectMapper.writeValueAsString(sceneCommentsList);

			// Prepare final response as JSON string
			sceneCommentsResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sceneComments\":" + sceneCommentsResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making delete request to server. Error: "+ exception.getMessage());
			sceneCommentsResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. One or more record couldn't be deleted.\"},\"response\":{}}]";
			
			return sceneCommentsResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSceneCommentsRecord(): Delete record(s) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSceneCommentsRecord(): Response data (json): " + sceneCommentsResponseData);

		return sceneCommentsResponseData;

	} //deleteSceneCommentsRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param sceneCommentsRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchSceneCommentsRecord", method=RequestMethod.POST)
	@ResponseBody
	public String fetchSceneCommentsRecord(@RequestBody String sceneCommentsRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneCommentsRecord()...");

		String sceneCommentsResponseData = null;
		SceneComments sceneComments = null;
		//String sceneCommentsRequestData = "{\"id\": 1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments request data received: "+ sceneCommentsRequestData);

			// Prepare sceneComments entity from request JSON data
			sceneComments = jsonObjectMapper.readValue(sceneCommentsRequestData, SceneComments.class);
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments data: "+ sceneComments.toString());

			// Call service to fetch record and get entity bean
			sceneComments = sceneCommentsService.fetchSceneCommentsRecord(sceneComments);

			// Prepare JSON string from entity bean
			sceneCommentsResponseData = jsonObjectMapper.writeValueAsString(sceneComments);
			
			sceneCommentsResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sceneComments\":" + sceneCommentsResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			sceneCommentsResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return sceneCommentsResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneCommentsRecord(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneCommentsRecord(): Response data (json): " + sceneCommentsResponseData);

		return sceneCommentsResponseData;

	} //fetchSceneCommentsRecord() method ends


	/**
	 * Invokes service method to fetch all records.
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchAllSceneCommentsRecords", method=RequestMethod.GET)
	@ResponseBody
	public String fetchAllSceneCommentsRecord() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllSceneCommentsRecord()...");

		String sceneCommentsResponseData = null;
		List<SceneComments> sceneCommentsList = new ArrayList<SceneComments>();
		
		try {
			// Call service to fetch all records
			sceneCommentsList = sceneCommentsService.fetchAllSceneCommentsRecord();

			// Prepare JSON string from list
			sceneCommentsResponseData = jsonObjectMapper.writeValueAsString(sceneCommentsList);

			// Prepare final response as JSON string
			sceneCommentsResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sceneComments\":" + sceneCommentsResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			sceneCommentsResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return sceneCommentsResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSceneCommentsRecord(): Fetch all record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSceneCommentsRecord(): Response data (json): " + sceneCommentsResponseData);

		return sceneCommentsResponseData;

	} //fetchAllSceneCommentsRecord() method ends


	/**
	 * Invokes service method to fetch records based on criteria.
	 * 
	 * @param sceneCommentsRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/getComments", method=RequestMethod.POST)
	@ResponseBody
	public String fetchSceneCommentsRecords(@RequestBody String sceneCommentsRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneCommentsRecords()...");

		String sceneCommentsResponseData = null;
		SceneComments sceneComments = null;
		List<SceneComments> sceneCommentsList = new ArrayList<SceneComments>();
		//String sceneCommentsRequestData = "{\"comment\":\"comment some garbage Text\"}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments request data received: "+ sceneCommentsRequestData);

			// Prepare sceneComments entity from request JSON data
			sceneComments = jsonObjectMapper.readValue(sceneCommentsRequestData, SceneComments.class);
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments data: "+ sceneComments.toString());

			// Call service to fetch records based on criteria
			sceneCommentsList = sceneCommentsService.fetchSceneCommentsRecords(sceneComments);

			// Prepare JSON string from entity bean
			sceneCommentsResponseData = jsonObjectMapper.writeValueAsString(sceneCommentsList);

			// Prepare final response as JSON string
			sceneCommentsResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sceneComments\":" + sceneCommentsResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch records request to server. Error: "+ exception.getMessage());
			sceneCommentsResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return sceneCommentsResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneCommentsRecords(): Fetch record (based on criteria) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneCommentsRecords(): Response data (json): " + sceneCommentsResponseData);

		return sceneCommentsResponseData;

	} //fetchSceneCommentsRecords() method ends


}