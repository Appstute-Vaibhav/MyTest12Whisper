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
import com.whispers.beans.Winner;
import com.whispers.service.WinnerService;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Controller class accepts incoming requests and delegates to service for processing
 */

@Controller
public class WinnerController {

	/**
	 * The service class instance
	 */
	@Autowired
	private WinnerService winnerService	= null;

	/**
	 * The JSON object mapper
	 */
	private ObjectMapper jsonObjectMapper = new ObjectMapper();

	/**
	 * Invokes service method to add record.
	 * 
	 * @param winnerRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/publishWinner", method=RequestMethod.POST)
	@ResponseBody
	public String addWinnerRecord(@RequestBody String winnerRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addWinnerRecord()...");

		String winnerResponseData = null;
		Winner winner = null;
		List<Winner> winnerList = null;
		//String winnerRequestData = "{\"sceneTitle\":\"Scene Title\",\"dateScenePublished\":\"2015-10-01\",\"winnerId\":6,\"winnerName\":\"testFirstName testLastName\",\"status\":1,\"createdOn\":\"2015-01-01\",\"createdBy\":1,\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "winner request data received: "+ winnerRequestData);

			// Prepare winner entity from request JSON data
			winner = jsonObjectMapper.readValue(winnerRequestData, Winner.class);
			Log.logMessage("INFO", this.getClass().getName(), "winner data: "+ winner.toString());

			// Call service to add record and get list of updated records
			winnerList = winnerService.addWinnerRecord(winner);

			// Prepare JSON string from entity bean
			winnerResponseData = jsonObjectMapper.writeValueAsString(winnerList);

			// Prepare final response as JSON string
			winnerResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"winners\":" + winnerResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			winnerResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return winnerResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addWinnerRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addWinnerRecord(): Response data (json): " + winnerResponseData);

		return winnerResponseData;

	} //addWinnerRecord() method ends


	/**
	 * Invokes service method to update record.
	 * 
	 * @param winnerRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/updateWinnerRecord", method=RequestMethod.POST)
	@ResponseBody
	public String updateWinnerRecord(@RequestBody String winnerRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateWinnerRecord()...");

		String winnerResponseData = null;
		Winner winner = null;
		List<Winner> winnerList = null;
		//String winnerRequestData = "{\"id\":1,\"sceneTitle\":\"Scene Title update\",\"dateScenePublished\":\"2015-10-05\",\"winnerId\":6,\"winnerName\":\"testFirstName testLastName\",\"status\":1,\"createdOn\":\"2015-01-01\",\"createdBy\":1,\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "winner request data received: "+ winnerRequestData);

			// Prepare winner entity from request JSON data
			winner = jsonObjectMapper.readValue(winnerRequestData, Winner.class);
			Log.logMessage("INFO", this.getClass().getName(), "winner data: "+ winner.toString());

			// Call service to update record and get updated list of records
			winnerList = winnerService.updateWinnerRecord(winner);

			// Prepare JSON string from entity bean
			winnerResponseData = jsonObjectMapper.writeValueAsString(winnerList);

			// Prepare final response as JSON string
			winnerResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"winners\":" + winnerResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making update request to server. Error: "+ exception.getMessage());
			winnerResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be updated.\"},\"response\":{}}]";

			return winnerResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateWinnerRecord(): Update record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateWinnerRecord(): Response data (json): " + winnerResponseData);

		return winnerResponseData;

	} //updateWinnerRecord() method ends


	/**
	 * Invokes service method to delete one or more records. If records need to be softdeleted then Status flag from table would be set to 0.
	 * 
	 * @param winnerRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/deleteWinnerRecords", method=RequestMethod.POST)
	@ResponseBody
	public String deleteWinnerRecord(@RequestBody String winnerRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteWinnerRecord()...");

		String winnerResponseData = null;
		List<Winner> winnerList = null;
		//String winnerRequestData = "[{\"id\": 5}]";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "winner request data received: "+ winnerRequestData);

			// Prepare winner entity from request JSON data
			winnerList = jsonObjectMapper.readValue(winnerRequestData, jsonObjectMapper.getTypeFactory().constructCollectionType( List.class, Winner.class));
			Log.logMessage("INFO", this.getClass().getName(), "winner data: "+ winnerList.toString());

			// Call service to delete record(s) and get list of updated records
			winnerList = winnerService.deleteWinnerRecord(winnerList);

			// Prepare JSON string from entity bean
			winnerResponseData = jsonObjectMapper.writeValueAsString(winnerList);

			// Prepare final response as JSON string
			winnerResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"winners\":" + winnerResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making delete request to server. Error: "+ exception.getMessage());
			winnerResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. One or more record couldn't be deleted.\"},\"response\":{}}]";
			
			return winnerResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteWinnerRecord(): Delete record(s) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteWinnerRecord(): Response data (json): " + winnerResponseData);

		return winnerResponseData;

	} //deleteWinnerRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param winnerRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchWinnerRecord", method=RequestMethod.POST)
	@ResponseBody
	public String fetchWinnerRecord(@RequestBody String winnerRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWinnerRecord()...");

		String winnerResponseData = null;
		Winner winner = null;
		//String winnerRequestData = "{\"id\": 1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "winner request data received: "+ winnerRequestData);

			// Prepare winner entity from request JSON data
			winner = jsonObjectMapper.readValue(winnerRequestData, Winner.class);
			Log.logMessage("INFO", this.getClass().getName(), "winner data: "+ winner.toString());

			// Call service to fetch record and get entity bean
			winner = winnerService.fetchWinnerRecord(winner);

			// Prepare JSON string from entity bean
			winnerResponseData = jsonObjectMapper.writeValueAsString(winner);
			
			winnerResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"winner\":" + winnerResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			winnerResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return winnerResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWinnerRecord(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWinnerRecord(): Response data (json): " + winnerResponseData);

		return winnerResponseData;

	} //fetchWinnerRecord() method ends


	/**
	 * Invokes service method to fetch all records.
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/fetchAllWinnerRecords", method=RequestMethod.GET)
	@ResponseBody
	public String fetchAllWinnerRecord() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllWinnerRecord()...");

		String winnerResponseData = null;
		List<Winner> winnerList = new ArrayList<Winner>();
		
		try {

			// Call service to fetch all records
			winnerList = winnerService.fetchAllWinnerRecord();

			// Prepare JSON string from list
			winnerResponseData = jsonObjectMapper.writeValueAsString(winnerList);

			// Prepare final response as JSON string
			winnerResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"winners\":" + winnerResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			winnerResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return winnerResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWinnerRecord(): Fetch all record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWinnerRecord(): Response data (json): " + winnerResponseData);

		return winnerResponseData;

	} //fetchAllWinnerRecord() method ends

	
	@RequestMapping(value="/web/fetchCurrentWeekWinnerRecord", method=RequestMethod.POST)
	@ResponseBody
	public String fetchCurrentWeekWinnerRecord(@RequestBody String winnerRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchCurrentWeekWinnerRecord()...");

		String winnerResponseData = null;
		Winner winner = null;		
		List<Winner> winnerList = new ArrayList<Winner>();		
		
		try {			
			Log.logMessage("INFO", this.getClass().getName(), "winner request data received: "+ winnerRequestData);

			// Prepare winner entity from request JSON data
			winner = jsonObjectMapper.readValue(winnerRequestData, Winner.class);
			Log.logMessage("INFO", this.getClass().getName(), "winner data: "+ winner.toString());


			// Call service to fetch all records
			winnerService.fetchCurrentWeekWinnerRecord(winner);

			winnerList = winnerService.fetchAllWinnerRecord();
			// Prepare JSON string from list
			winnerResponseData = jsonObjectMapper.writeValueAsString(winnerList);

			// Prepare final response as JSON string
			winnerResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"winners\":" + winnerResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			winnerResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return winnerResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWinnerRecord(): Fetch all record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWinnerRecord(): Response data (json): " + winnerResponseData);

		return winnerResponseData;

	} //fetchAllWinnerRecord() method ends
	
	
	

	/**
	 * Invokes service method to fetch records based on criteria.
	 * 
	 * @param winnerRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/getWinners", method=RequestMethod.GET)
	@ResponseBody
	public String fetchWinnerRecords() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWinnerRecords()...");

		String winnerResponseData = null;
		
		List<Winner> winnerList = new ArrayList<Winner>();
		//String winnerRequestData = "{\"sceneTitle\":\"Scene Title\"}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "winner request data received: ");

			// Prepare winner entity from request JSON data
			
			Log.logMessage("INFO", this.getClass().getName(), "winner data: ");

			// Call service to fetch records based on criteria
			winnerList = winnerService.fetchWinnerRecords();

			// Prepare JSON string from entity bean
			winnerResponseData = jsonObjectMapper.writeValueAsString(winnerList);

			// Prepare final response as JSON string
			winnerResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"winners\":" + winnerResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch records request to server. Error: "+ exception.getMessage());
			winnerResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return winnerResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWinnerRecords(): Fetch record (based on criteria) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWinnerRecords(): Response data (json): " + winnerResponseData);

		return winnerResponseData;

	} //fetchWinnerRecords() method ends


}