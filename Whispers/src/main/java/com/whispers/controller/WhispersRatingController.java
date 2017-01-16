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
import com.whispers.beans.WhispersRating;
import com.whispers.service.WhispersRatingService;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Controller class accepts incoming requests and delegates to service for processing
 */

@Controller
public class WhispersRatingController {

	/**
	 * The service class instance
	 */
	@Autowired
	private WhispersRatingService whispersRatingService	= null;

	/**
	 * The JSON object mapper
	 */
	private ObjectMapper jsonObjectMapper = new ObjectMapper();

	/**
	 * Invokes service method to add record.
	 * 
	 * @param whispersRatingRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/addWhispersRatingRecord", method=RequestMethod.POST)
	@ResponseBody
	public String addWhispersRatingRecord(@RequestBody String whispersRatingRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addWhispersRatingRecord()...");

		String whispersRatingResponseData = null;
		WhispersRating whispersRating = null;
		List<WhispersRating> whispersRatingList = null;
		//String whispersRatingRequestData = "{\"dateRatingSubmitted\":\"2015-01-01\",\"submittedById\":6,\"rating\":6,\"status\":1,\"createdOn\":\"2015-01-01\",\"createdBy\":1,\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating request data received: "+ whispersRatingRequestData);

			// Prepare whispersRating entity from request JSON data
			whispersRating = jsonObjectMapper.readValue(whispersRatingRequestData, WhispersRating.class);
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating data: "+ whispersRating.toString());

			// Call service to add record and get list of updated records
			whispersRatingList = whispersRatingService.addWhispersRatingRecord(whispersRating);

			// Prepare JSON string from entity bean
			whispersRatingResponseData = jsonObjectMapper.writeValueAsString(whispersRatingList);

			// Prepare final response as JSON string
			whispersRatingResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"whispersRatings\":" + whispersRatingResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			whispersRatingResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return whispersRatingResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addWhispersRatingRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addWhispersRatingRecord(): Response data (json): " + whispersRatingResponseData);

		return whispersRatingResponseData;

	} //addWhispersRatingRecord() method ends


	/**
	 * Invokes service method to update record.
	 * 
	 * @param whispersRatingRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/updateWhispersRatingRecord", method=RequestMethod.POST)
	@ResponseBody
	public String updateWhispersRatingRecord(@RequestBody String whispersRatingRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateWhispersRatingRecord()...");

		String whispersRatingResponseData = null;
		WhispersRating whispersRating = null;
		List<WhispersRating> whispersRatingList = null;
		//String whispersRatingRequestData = "{\"id\":2,\"dateRatingSubmitted\":\"2015-01-01\",\"submittedById\":6,\"rating\":6,\"status\":1,\"createdOn\":\"2015-01-01\",\"createdBy\":1,\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating request data received: "+ whispersRatingRequestData);

			// Prepare whispersRating entity from request JSON data
			whispersRating = jsonObjectMapper.readValue(whispersRatingRequestData, WhispersRating.class);
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating data: "+ whispersRating.toString());

			// Call service to update record and get updated list of records
			whispersRatingList = whispersRatingService.updateWhispersRatingRecord(whispersRating);

			// Prepare JSON string from entity bean
			whispersRatingResponseData = jsonObjectMapper.writeValueAsString(whispersRatingList);

			// Prepare final response as JSON string
			whispersRatingResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"whispersRatings\":" + whispersRatingResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making update request to server. Error: "+ exception.getMessage());
			whispersRatingResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be updated.\"},\"response\":{}}]";

			return whispersRatingResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateWhispersRatingRecord(): Update record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateWhispersRatingRecord(): Response data (json): " + whispersRatingResponseData);

		return whispersRatingResponseData;

	} //updateWhispersRatingRecord() method ends


	/**
	 * Invokes service method to delete one or more records. If records need to be softdeleted then Status flag from table would be set to 0.
	 * 
	 * @param whispersRatingRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/deleteWhispersRatingRecords", method=RequestMethod.POST)
	@ResponseBody
	public String deleteWhispersRatingRecord(@RequestBody String whispersRatingRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteWhispersRatingRecord()...");

		String whispersRatingResponseData = null;
		List<WhispersRating> whispersRatingList = null;
		//String whispersRatingRequestData = "[{\"id\": 5}]";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating request data received: "+ whispersRatingRequestData);

			// Prepare whispersRating entity from request JSON data
			whispersRatingList = jsonObjectMapper.readValue(whispersRatingRequestData, jsonObjectMapper.getTypeFactory().constructCollectionType( List.class, WhispersRating.class));
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating data: "+ whispersRatingList.toString());

			// Call service to delete record(s) and get list of updated records
			whispersRatingList = whispersRatingService.deleteWhispersRatingRecord(whispersRatingList);

			// Prepare JSON string from entity bean
			whispersRatingResponseData = jsonObjectMapper.writeValueAsString(whispersRatingList);

			// Prepare final response as JSON string
			whispersRatingResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"whispersRatings\":" + whispersRatingResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making delete request to server. Error: "+ exception.getMessage());
			whispersRatingResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. One or more record couldn't be deleted.\"},\"response\":{}}]";
			
			return whispersRatingResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteWhispersRatingRecord(): Delete record(s) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteWhispersRatingRecord(): Response data (json): " + whispersRatingResponseData);

		return whispersRatingResponseData;

	} //deleteWhispersRatingRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param whispersRatingRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchWhispersRatingRecord", method=RequestMethod.POST)
	@ResponseBody
	public String fetchWhispersRatingRecord(@RequestBody String whispersRatingRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWhispersRatingRecord()...");

		String whispersRatingResponseData = null;
		WhispersRating whispersRating = null;
		//String whispersRatingRequestData = "{\"id\": 1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating request data received: "+ whispersRatingRequestData);

			// Prepare whispersRating entity from request JSON data
			whispersRating = jsonObjectMapper.readValue(whispersRatingRequestData, WhispersRating.class);
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating data: "+ whispersRating.toString());

			// Call service to fetch record and get entity bean
			whispersRating = whispersRatingService.fetchWhispersRatingRecord(whispersRating);

			// Prepare JSON string from entity bean
			whispersRatingResponseData = jsonObjectMapper.writeValueAsString(whispersRating);
			
			whispersRatingResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"whispersRating\":" + whispersRatingResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			whispersRatingResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return whispersRatingResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWhispersRatingRecord(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWhispersRatingRecord(): Response data (json): " + whispersRatingResponseData);

		return whispersRatingResponseData;

	} //fetchWhispersRatingRecord() method ends


	/**
	 * Invokes service method to fetch all records.
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchAllWhispersRatingRecords", method=RequestMethod.GET)
	@ResponseBody
	public String fetchAllWhispersRatingRecord() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllWhispersRatingRecord()...");

		String whispersRatingResponseData = null;
		List<WhispersRating> whispersRatingList = new ArrayList<WhispersRating>();
		
		try {
			// Call service to fetch all records
			whispersRatingList = whispersRatingService.fetchAllWhispersRatingRecord();

			// Prepare JSON string from list
			whispersRatingResponseData = jsonObjectMapper.writeValueAsString(whispersRatingList);

			// Prepare final response as JSON string
			whispersRatingResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"whispersRatings\":" + whispersRatingResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			whispersRatingResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return whispersRatingResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWhispersRatingRecord(): Fetch all record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWhispersRatingRecord(): Response data (json): " + whispersRatingResponseData);

		return whispersRatingResponseData;

	} //fetchAllWhispersRatingRecord() method ends


	/**
	 * Invokes service method to fetch records based on criteria.
	 * 
	 * @param whispersRatingRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchWhispersRatingRecords", method=RequestMethod.POST)
	@ResponseBody
	public String fetchWhispersRatingRecords(@RequestBody String whispersRatingRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWhispersRatingRecords()...");

		String whispersRatingResponseData = null;
		WhispersRating whispersRating = null;
		List<WhispersRating> whispersRatingList = new ArrayList<WhispersRating>();
		//String whispersRatingRequestData = "{\"submittedById\":6}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating request data received: "+ whispersRatingRequestData);

			// Prepare whispersRating entity from request JSON data
			whispersRating = jsonObjectMapper.readValue(whispersRatingRequestData, WhispersRating.class);
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating data: "+ whispersRating.toString());

			// Call service to fetch records based on criteria
			whispersRatingList = whispersRatingService.fetchWhispersRatingRecords(whispersRating);

			// Prepare JSON string from entity bean
			whispersRatingResponseData = jsonObjectMapper.writeValueAsString(whispersRatingList);

			// Prepare final response as JSON string
			whispersRatingResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"whispersRatings\":" + whispersRatingResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch records request to server. Error: "+ exception.getMessage());
			whispersRatingResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return whispersRatingResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWhispersRatingRecords(): Fetch record (based on criteria) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWhispersRatingRecords(): Response data (json): " + whispersRatingResponseData);

		return whispersRatingResponseData;

	} //fetchWhispersRatingRecords() method ends


}