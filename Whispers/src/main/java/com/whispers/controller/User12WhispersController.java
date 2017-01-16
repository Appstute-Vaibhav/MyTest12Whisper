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
import com.whispers.beans.User12Whispers;
import com.whispers.service.User12WhispersService;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Controller class accepts incoming requests and delegates to service for processing
 */

@Controller
public class User12WhispersController {

	/**
	 * The service class instance
	 */
	@Autowired
	private User12WhispersService user12WhispersService	= null;

	/**
	 * The JSON object mapper
	 */
	private ObjectMapper jsonObjectMapper = new ObjectMapper();

	/**
	 * Invokes service method to add record.
	 * 
	 * @param user12WhispersRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/submitWhispers", method=RequestMethod.POST)
	@ResponseBody
	public String addUser12WhispersRecord(@RequestBody String user12WhispersRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUser12WhispersRecord()...");

		String user12WhispersResponseData = null;
		User12Whispers user12Whispers = null;
		List<User12Whispers> user12WhispersList = null;
		//String user12WhispersRequestData = "{\"id\":1,\"sceneId\":1,\"dateWhispersSubmitted\":\"2015-01-01\",\"submittedById\":6,\"whispers\":\"12 whispers separated by line Break character\",\"status\":1,\"createdOn\":\"2015-01-01\",\"createdBy\":1,\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers request data received: "+ user12WhispersRequestData);

			// Prepare user12Whispers entity from request JSON data
			user12Whispers = jsonObjectMapper.readValue(user12WhispersRequestData, User12Whispers.class);
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers data: "+ user12Whispers.toString());

			// Call service to add record and get list of updated records
			user12WhispersList = user12WhispersService.addUser12WhispersRecord(user12Whispers);

			// Prepare JSON string from entity bean
			user12WhispersResponseData = jsonObjectMapper.writeValueAsString(user12WhispersList);

			// Prepare final response as JSON string
			user12WhispersResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user12Whispers\":" + user12WhispersResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			user12WhispersResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return user12WhispersResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUser12WhispersRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUser12WhispersRecord(): Response data (json): " + user12WhispersResponseData);

		return user12WhispersResponseData;

	} //addUser12WhispersRecord() method ends


	/**
	 * Invokes service method to update record.
	 * 
	 * @param user12WhispersRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/updateUser12WhispersRecord", method=RequestMethod.POST)
	@ResponseBody
	public String updateUser12WhispersRecord(@RequestBody String user12WhispersRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateUser12WhispersRecord()...");

		String user12WhispersResponseData = null;
		User12Whispers user12Whispers = null;
		List<User12Whispers> user12WhispersList = null;
		//String user12WhispersRequestData = "{\"id\":2,\"sceneId\":1,\"dateWhispersSubmitted\":\"2015-01-01\",\"submittedById\":6,\"whispers\":\"12 whispers separated by line Break character\",\"status\":1,\"createdOn\":\"2015-01-01\",\"createdBy\":1,\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers request data received: "+ user12WhispersRequestData);

			// Prepare user12Whispers entity from request JSON data
			user12Whispers = jsonObjectMapper.readValue(user12WhispersRequestData, User12Whispers.class);
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers data: "+ user12Whispers.toString());

			// Call service to update record and get updated list of records
			user12WhispersList = user12WhispersService.updateUser12WhispersRecord(user12Whispers);

			// Prepare JSON string from entity bean
			user12WhispersResponseData = jsonObjectMapper.writeValueAsString(user12WhispersList);

			// Prepare final response as JSON string
			user12WhispersResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user12Whispers\":" + user12WhispersResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making update request to server. Error: "+ exception.getMessage());
			user12WhispersResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be updated.\"},\"response\":{}}]";

			return user12WhispersResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateUser12WhispersRecord(): Update record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateUser12WhispersRecord(): Response data (json): " + user12WhispersResponseData);

		return user12WhispersResponseData;

	} //updateUser12WhispersRecord() method ends


	/**
	 * Invokes service method to delete one or more records. If records need to be softdeleted then Status flag from table would be set to 0.
	 * 
	 * @param user12WhispersRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/deleteUser12WhispersRecords", method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser12WhispersRecord(@RequestBody String user12WhispersRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteUser12WhispersRecord()...");

		String user12WhispersResponseData = null;
		List<User12Whispers> user12WhispersList = null;
		//String user12WhispersRequestData = "[{\"id\": 5}]";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers request data received: "+ user12WhispersRequestData);

			// Prepare user12Whispers entity from request JSON data
			user12WhispersList = jsonObjectMapper.readValue(user12WhispersRequestData, jsonObjectMapper.getTypeFactory().constructCollectionType( List.class, User12Whispers.class));
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers data: "+ user12WhispersList.toString());

			// Call service to delete record(s) and get list of updated records
			user12WhispersList = user12WhispersService.deleteUser12WhispersRecord(user12WhispersList);

			// Prepare JSON string from entity bean
			user12WhispersResponseData = jsonObjectMapper.writeValueAsString(user12WhispersList);

			// Prepare final response as JSON string
			user12WhispersResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user12Whispers\":" + user12WhispersResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making delete request to server. Error: "+ exception.getMessage());
			user12WhispersResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. One or more record couldn't be deleted.\"},\"response\":{}}]";
			
			return user12WhispersResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteUser12WhispersRecord(): Delete record(s) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteUser12WhispersRecord(): Response data (json): " + user12WhispersResponseData);

		return user12WhispersResponseData;

	} //deleteUser12WhispersRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param user12WhispersRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchUser12WhispersRecord", method=RequestMethod.POST)
	@ResponseBody
	public String fetchUser12WhispersRecord(@RequestBody String user12WhispersRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUser12WhispersRecord()...");

		String user12WhispersResponseData = null;
		User12Whispers user12Whispers = null;
		//String user12WhispersRequestData = "{\"id\": 1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers request data received: "+ user12WhispersRequestData);

			// Prepare user12Whispers entity from request JSON data
			user12Whispers = jsonObjectMapper.readValue(user12WhispersRequestData, User12Whispers.class);
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers data: "+ user12Whispers.toString());

			// Call service to fetch record and get entity bean
			user12Whispers = user12WhispersService.fetchUser12WhispersRecord(user12Whispers);

			// Prepare JSON string from entity bean
			user12WhispersResponseData = jsonObjectMapper.writeValueAsString(user12Whispers);
			
			user12WhispersResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user12Whispers\":" + user12WhispersResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			user12WhispersResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return user12WhispersResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUser12WhispersRecord(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUser12WhispersRecord(): Response data (json): " + user12WhispersResponseData);

		return user12WhispersResponseData;

	} //fetchUser12WhispersRecord() method ends


	/**
	 * Invokes service method to fetch all records.
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchAllUser12WhispersRecords", method=RequestMethod.POST)
	@ResponseBody
	public String fetchAllUser12WhispersRecord(@RequestBody String user12WhispersRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllUser12WhispersRecord()...");

		String user12WhispersResponseData = null;
		List<User12Whispers> user12WhispersList = new ArrayList<User12Whispers>();
		User12Whispers user12Whispers = null;
		//String user12WhispersRequestData = "{\"roleId\": 1, \"id\": 1}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers request data received: "+ user12WhispersRequestData);

			// Prepare user12Whispers entity from request JSON data
			user12Whispers = jsonObjectMapper.readValue(user12WhispersRequestData, User12Whispers.class);
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers data: "+ user12Whispers.toString());

			// Call service to fetch all records
			user12WhispersList = user12WhispersService.fetchAllUser12WhispersRecord(user12Whispers);

			// Prepare JSON string from list
			user12WhispersResponseData = jsonObjectMapper.writeValueAsString(user12WhispersList);

			// Prepare final response as JSON string
			user12WhispersResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user12Whispers\":" + user12WhispersResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			user12WhispersResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return user12WhispersResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllUser12WhispersRecord(): Fetch all record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllUser12WhispersRecord(): Response data (json): " + user12WhispersResponseData);

		return user12WhispersResponseData;

	} //fetchAllUser12WhispersRecord() method ends


	/**
	 * Invokes service method to fetch records based on criteria.
	 * 
	 * @param user12WhispersRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchUser12WhispersRecords", method=RequestMethod.POST)
	@ResponseBody
	public String fetchUser12WhispersRecords(@RequestBody String user12WhispersRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUser12WhispersRecords()...");

		String user12WhispersResponseData = null;
		User12Whispers user12Whispers = null;
		List<User12Whispers> user12WhispersList = new ArrayList<User12Whispers>();
		//String user12WhispersRequestData = "{\"sceneId\":1,\"submittedById\":6}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers request data received: "+ user12WhispersRequestData);

			// Prepare user12Whispers entity from request JSON data
			user12Whispers = jsonObjectMapper.readValue(user12WhispersRequestData, User12Whispers.class);
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers data: "+ user12Whispers.toString());

			// Call service to fetch records based on criteria
			user12WhispersList = user12WhispersService.fetchUser12WhispersRecords(user12Whispers);

			// Prepare JSON string from entity bean
			user12WhispersResponseData = jsonObjectMapper.writeValueAsString(user12WhispersList);

			// Prepare final response as JSON string
			user12WhispersResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user12Whispers\":" + user12WhispersResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch records request to server. Error: "+ exception.getMessage());
			user12WhispersResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return user12WhispersResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUser12WhispersRecords(): Fetch record (based on criteria) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUser12WhispersRecords(): Response data (json): " + user12WhispersResponseData);

		return user12WhispersResponseData;

	} //fetchUser12WhispersRecords() method ends


}