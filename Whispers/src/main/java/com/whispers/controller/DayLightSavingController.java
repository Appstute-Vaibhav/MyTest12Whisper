package com.whispers.controller;

// spring framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whispers.beans.DayLightSaving;
import com.whispers.service.DayLightSavingService;
import com.whispers.utils.Log;
// application imports

/**
 * @author anka technology solutions private limited
 *
 * Controller class accepts incoming requests and delegates to service for processing
 */

@Controller
public class DayLightSavingController{ 
	
	/**
	 * The service class instance
	 */

	@Autowired
	DayLightSavingService dayLightSavingService;
	
	/**
	 * The JSON object mapper
	 */
	private ObjectMapper jsonObjectMapper = new ObjectMapper();
	
	/**
	 * Sets server time as per Day light saving ON / OFF
	 * 
	 * @param dayLightSavingRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/updateDayLightSavingSettings", method=RequestMethod.POST)
	@ResponseBody
	public String dayLightSavingSettings(@RequestBody String dayLightSavingRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering dayLightSavingSettings()...");

		String dayLightSavingResponseData = null;
		DayLightSaving dayLightSaving = null;
		try {
			Log.logMessage("INFO", this.getClass().getName(), "dayLightSaving request data received: "+ dayLightSavingRequestData);

			// Prepare feedbackQuestion entity from request JSON data
			dayLightSaving = jsonObjectMapper.readValue(dayLightSavingRequestData, DayLightSaving.class);
			
			Log.logMessage("INFO", this.getClass().getName(), "dayLightSaving data: "+ dayLightSaving.toString());

			// Call service to add record and get list of updated records
			dayLightSavingService.dayLightSavingSettings(dayLightSaving);

			// Prepare final response as JSON string
			dayLightSavingResponseData = "[{\"success\":true,\"messages\":{},\"response\":{}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			dayLightSavingResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return dayLightSavingResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting dayLightSavingSettings(): Update day Light Saving Setting call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting dayLightSavingSettings(): Response data (json): " + dayLightSavingResponseData);

		return dayLightSavingResponseData;

	} //dayLightSavingSettings() method ends
	
	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param dayLightSavingRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchDayLightSavingSettings", method=RequestMethod.GET)
	@ResponseBody
	public String fetchDayLightSavingSettings() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchDayLightSavingSettings()...");

		String dayLightSavingResponseData = null;
		DayLightSaving dayLightSaving = null;

		try {

			// Call service to fetch record and get entity bean
			dayLightSaving = dayLightSavingService.fetchDayLightSavingSettings();

			// Prepare JSON string from entity bean
			dayLightSavingResponseData = jsonObjectMapper.writeValueAsString(dayLightSaving);
			
			dayLightSavingResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"dayLightSaving\":" + dayLightSavingResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			dayLightSavingResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return dayLightSavingResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchDayLightSavingSettings(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchDayLightSavingSettings(): Response data (json): " + dayLightSavingResponseData);

		return dayLightSavingResponseData;

	} //fetchDayLightSavingSettings() method ends
}