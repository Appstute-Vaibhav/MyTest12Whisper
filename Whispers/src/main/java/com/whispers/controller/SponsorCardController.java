/**
 * Created on 15 Jan, 2015
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
import com.whispers.beans.SponsorCard;
import com.whispers.service.SponsorCardService;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Controller class accepts incoming requests and delegates to service for processing
 */

@Controller
public class SponsorCardController {

	/**
	 * The service class instance
	 */
	@Autowired
	private SponsorCardService sponsorCardService	= null;

	/**
	 * The JSON object mapper
	 */
	private ObjectMapper jsonObjectMapper = new ObjectMapper();

	/**
	 * Invokes service method to add record.
	 * 
	 * @param sponsorCardRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/addSponsorCardRecord", method=RequestMethod.POST)
	@ResponseBody
	public String addSponsorCardRecord(@RequestBody String sponsorCardRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSponsorCardRecord()...");

		String sponsorCardResponseData = null;
		SponsorCard sponsorCard = null;
		List<SponsorCard> sponsorCardList = null;
		//String sponsorCardRequestData = "{\"sponsoredBy\":\"Nike\",\"cardDescription\":\"description\",\"dateUploaded\":\"2015-01-01\",\"cardImage\":\"CardImage\",\"sponsorURI\":\"http://www.google.co.in\",\"status\":1,\"createdOn\":\"2015-01-01\",\"createdBy\":1,\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard request data received: "+ sponsorCardRequestData);

			// Prepare sponsorCard entity from request JSON data
			sponsorCard = jsonObjectMapper.readValue(sponsorCardRequestData, SponsorCard.class);
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard data: "+ sponsorCard.toString());

			// Call service to add record and get list of updated records
			sponsorCardList = sponsorCardService.addSponsorCardRecord(sponsorCard);

			// Prepare JSON string from entity bean
			sponsorCardResponseData = jsonObjectMapper.writeValueAsString(sponsorCardList);

			// Prepare final response as JSON string
			sponsorCardResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sponsorCards\":" + sponsorCardResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			sponsorCardResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return sponsorCardResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSponsorCardRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSponsorCardRecord(): Response data (json): " + sponsorCardResponseData);

		return sponsorCardResponseData;

	} //addSponsorCardRecord() method ends


	/**
	 * Invokes service method to update record.
	 * 
	 * @param sponsorCardRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/updateSponsorCardRecord", method=RequestMethod.POST)
	@ResponseBody
	public String updateSponsorCardRecord(@RequestBody String sponsorCardRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateSponsorCardRecord()...");

		String sponsorCardResponseData = null;
		SponsorCard sponsorCard = null;
		List<SponsorCard> sponsorCardList = null;
		//String sponsorCardRequestData = "{\"id\":1,\"sponsoredBy\":\"NikeUpdate\",\"cardDescription\":\"descriptionUpdate\",\"dateUploaded\":\"2015-05-05\",\"cardImage\":\"CardImage\",\"sponsorURI\":\"http://www.gmail.co.in\",\"status\":1,\"createdOn\":\"2015-01-01\",\"createdBy\":1,\"modifiedOn\":\"2015-01-01\",\"modifiedBy\":1}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard request data received: "+ sponsorCardRequestData);

			// Prepare sponsorCard entity from request JSON data
			sponsorCard = jsonObjectMapper.readValue(sponsorCardRequestData, SponsorCard.class);
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard data: "+ sponsorCard.toString());

			// Call service to update record and get updated list of records
			sponsorCardList = sponsorCardService.updateSponsorCardRecord(sponsorCard);

			// Prepare JSON string from entity bean
			sponsorCardResponseData = jsonObjectMapper.writeValueAsString(sponsorCardList);

			// Prepare final response as JSON string
			sponsorCardResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sponsorCards\":" + sponsorCardResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making update request to server. Error: "+ exception.getMessage());
			sponsorCardResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be updated.\"},\"response\":{}}]";

			return sponsorCardResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSponsorCardRecord(): Update record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSponsorCardRecord(): Response data (json): " + sponsorCardResponseData);

		return sponsorCardResponseData;

	} //updateSponsorCardRecord() method ends


	/**
	 * Invokes service method to delete one or more records. If records need to be softdeleted then Status flag from table would be set to 0.
	 * 
	 * @param sponsorCardRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/deleteSponsorCardRecords", method=RequestMethod.POST)
	@ResponseBody
	public String deleteSponsorCardRecord(@RequestBody String sponsorCardRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteSponsorCardRecord()...");

		String sponsorCardResponseData = null;
		List<SponsorCard> sponsorCardList = null;
		//String sponsorCardRequestData = "[{\"id\":2}]";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard request data received: "+ sponsorCardRequestData);

			// Prepare sponsorCard entity from request JSON data
			sponsorCardList = jsonObjectMapper.readValue(sponsorCardRequestData, jsonObjectMapper.getTypeFactory().constructCollectionType( List.class, SponsorCard.class));
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard data: "+ sponsorCardList.toString());

			// Call service to delete record(s) and get list of updated records
			sponsorCardList = sponsorCardService.deleteSponsorCardRecord(sponsorCardList);

			// Prepare JSON string from entity bean
			sponsorCardResponseData = jsonObjectMapper.writeValueAsString(sponsorCardList);

			// Prepare final response as JSON string
			sponsorCardResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sponsorCards\":" + sponsorCardResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making delete request to server. Error: "+ exception.getMessage());
			sponsorCardResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. One or more record couldn't be deleted.\"},\"response\":{}}]";
			
			return sponsorCardResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSponsorCardRecord(): Delete record(s) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSponsorCardRecord(): Response data (json): " + sponsorCardResponseData);

		return sponsorCardResponseData;

	} //deleteSponsorCardRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param sponsorCardRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchSponsorCardRecord", method=RequestMethod.POST)
	@ResponseBody
	public String fetchSponsorCardRecord(@RequestBody String sponsorCardRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSponsorCardRecord()...");

		String sponsorCardResponseData = null;
		SponsorCard sponsorCard = null;
		//String sponsorCardRequestData = "{\"id\":2}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard request data received: "+ sponsorCardRequestData);

			// Prepare sponsorCard entity from request JSON data
			sponsorCard = jsonObjectMapper.readValue(sponsorCardRequestData, SponsorCard.class);
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard data: "+ sponsorCard.toString());

			// Call service to fetch record and get entity bean
			sponsorCard = sponsorCardService.fetchSponsorCardRecord(sponsorCard);

			// Prepare JSON string from entity bean
			sponsorCardResponseData = jsonObjectMapper.writeValueAsString(sponsorCard);
			
			sponsorCardResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sponsorCard\":" + sponsorCardResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			sponsorCardResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return sponsorCardResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSponsorCardRecord(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSponsorCardRecord(): Response data (json): " + sponsorCardResponseData);

		return sponsorCardResponseData;

	} //fetchSponsorCardRecord() method ends


	/**
	 * Invokes service method to fetch all records.
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchAllSponsorCardRecords", method=RequestMethod.GET)
	@ResponseBody
	public String fetchAllSponsorCardRecord() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllSponsorCardRecord()...");

		String sponsorCardResponseData = null;
		List<SponsorCard> sponsorCardList = new ArrayList<SponsorCard>();

		try {

			// Call service to fetch all records
			sponsorCardList = sponsorCardService.fetchAllSponsorCardRecord();

			// Prepare JSON string from list
			sponsorCardResponseData = jsonObjectMapper.writeValueAsString(sponsorCardList);

			// Prepare final response as JSON string
			sponsorCardResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sponsorCards\":" + sponsorCardResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			sponsorCardResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return sponsorCardResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSponsorCardRecord(): Fetch all record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSponsorCardRecord(): Response data (json): " + sponsorCardResponseData);

		return sponsorCardResponseData;

	} //fetchAllSponsorCardRecord() method ends


	/**
	 * Invokes service method to fetch records based on criteria.
	 * 
	 * @param sponsorCardRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/getSponsorCard", method=RequestMethod.POST)
	@ResponseBody
	public String fetchSponsorCardRecords(@RequestBody String sponsorCardRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSponsorCardRecords()...");

		String sponsorCardResponseData = null;
		SponsorCard sponsorCard = null;
		List<SponsorCard> sponsorCardList = new ArrayList<SponsorCard>();
		//String sponsorCardRequestData = "{\"sponsoredBy\":\"Nike\"}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard request data received: "+ sponsorCardRequestData);

			// Prepare sponsorCard entity from request JSON data
			sponsorCard = jsonObjectMapper.readValue(sponsorCardRequestData, SponsorCard.class);
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard data: "+ sponsorCard.toString());

			// Call service to fetch records based on criteria
			sponsorCardList = sponsorCardService.fetchSponsorCardRecords(sponsorCard);

			// Prepare JSON string from entity bean
			sponsorCardResponseData = jsonObjectMapper.writeValueAsString(sponsorCardList);

			// Prepare final response as JSON string
			sponsorCardResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"sponsorCards\":" + sponsorCardResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch records request to server. Error: "+ exception.getMessage());
			sponsorCardResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return sponsorCardResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSponsorCardRecords(): Fetch record (based on criteria) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSponsorCardRecords(): Response data (json): " + sponsorCardResponseData);

		return sponsorCardResponseData;

	} //fetchSponsorCardRecords() method ends


}