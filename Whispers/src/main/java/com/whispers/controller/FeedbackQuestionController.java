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

import com.fasterxml.jackson.core.type.TypeReference;
// json library imports
import com.fasterxml.jackson.databind.ObjectMapper;
// application imports
import com.whispers.beans.FeedbackQuestion;
import com.whispers.beans.UserFeedback;
import com.whispers.service.FeedbackQuestionService;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Controller class accepts incoming requests and delegates to service for processing
 */

@Controller
public class FeedbackQuestionController {
	/**
	 * The service class instance
	 */
	@Autowired
	private FeedbackQuestionService feedbackQuestionService	= null;

	/**
	 * The JSON object mapper
	 */
	private ObjectMapper jsonObjectMapper = new ObjectMapper();

	/**
	 * Invokes service method to add record.
	 * 
	 * @param feedbackQuestionRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/addFeedbackQuestionRecord", method=RequestMethod.POST)
	@ResponseBody
	public String addFeedbackQuestionRecord(@RequestBody String feedbackQuestionRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addFeedbackQuestionRecord()...");

		String feedbackQuestionResponseData = null;
		List<FeedbackQuestion> requestFeedbackQuestionList = null;
		List<FeedbackQuestion> feedbackQuestionList = null;
		//String feedbackQuestionRequestData = "{\"question\":\"Question Description\",\"feedbackQuestionOptions\":[{\"optionLabel\":\"yes\",\"optionValue\":\"Yes\"}, {\"optionLabel\":\"no\",\"optionValue\":\"No\"}]}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion request data received: "+ feedbackQuestionRequestData);

			// Prepare feedbackQuestion entity from request JSON data
			requestFeedbackQuestionList = jsonObjectMapper.readValue(feedbackQuestionRequestData, new TypeReference<List<FeedbackQuestion>>(){});
			
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion data: "+ requestFeedbackQuestionList.toString());

			// Call service to add record and get list of updated records
			feedbackQuestionList = feedbackQuestionService.addFeedbackQuestionRecord(requestFeedbackQuestionList);

			// Prepare JSON string from entity bean
			feedbackQuestionResponseData = jsonObjectMapper.writeValueAsString(feedbackQuestionList);

			// Prepare final response as JSON string
			feedbackQuestionResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"feedbackQuestions\":" + feedbackQuestionResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			feedbackQuestionResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return feedbackQuestionResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addFeedbackQuestionRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addFeedbackQuestionRecord(): Response data (json): " + feedbackQuestionResponseData);

		return feedbackQuestionResponseData;

	} //addFeedbackQuestionRecord() method ends
	
	/**
	 * Invokes service method to add record.
	 * 
	 * @param userFeedbackRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/submitFeedback", method=RequestMethod.POST)
	@ResponseBody
	public String addUserFeedback(@RequestBody String userFeedbackRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUserFeedback()...");

		String feedbackQuestionResponseData = null;
		List<UserFeedback> requestFeedbackQuestionList = null;
		//String feedbackQuestionRequestData = "{\"question\":\"Question Description\",\"feedbackQuestionOptions\":[{\"optionLabel\":\"yes\",\"optionValue\":\"Yes\"}, {\"optionLabel\":\"no\",\"optionValue\":\"No\"}]}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion request data received: "+ userFeedbackRequestData);

			// Prepare feedbackQuestion entity from request JSON data
			requestFeedbackQuestionList = jsonObjectMapper.readValue(userFeedbackRequestData, new TypeReference<List<UserFeedback>>(){});
			
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion data: "+ requestFeedbackQuestionList.toString());

			// Call service to add record and get list of updated records
			feedbackQuestionService.addUserFeedback(requestFeedbackQuestionList);

			// Prepare JSON string from entity bean
			//feedbackQuestionResponseData = jsonObjectMapper.writeValueAsString(UserFeedbackList);

			// Prepare final response as JSON string
			feedbackQuestionResponseData = "[{\"success\":true,\"messages\":{},\"response\":{}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			feedbackQuestionResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return feedbackQuestionResponseData;
		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addFeedbackQuestionRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addFeedbackQuestionRecord(): Response data (json): " + feedbackQuestionResponseData);

		return feedbackQuestionResponseData;

	} //addUserFeedback() method ends


	/**
	 * Invokes service method to update record.
	 * 
	 * @param feedbackQuestionRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/updateFeedbackQuestionRecord", method=RequestMethod.POST)
	@ResponseBody
	public String updateFeedbackQuestionRecord(@RequestBody String feedbackQuestionRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateFeedbackQuestionRecord()...");

		String feedbackQuestionResponseData = null;
		List<FeedbackQuestion> requestFeedbackQuestionList = null;
		//String feedbackQuestionRequestData = "{\"id\":1, \"question\":\"QuestionText\", \"feedbackQuestionOptions\":[{\"id\":1, \"optionLabel\":\"yes\", \"optionValue\":\"Yes\"}, {\"id\":2, \"optionLabel\":\"no\", \"optionValue\":\"No\"}], \"modifiedBy\":1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion request data received: "+ feedbackQuestionRequestData);

			// Prepare feedbackQuestion entity from request JSON data
			requestFeedbackQuestionList = jsonObjectMapper.readValue(feedbackQuestionRequestData, new TypeReference<List<FeedbackQuestion>>(){});
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion data: "+ requestFeedbackQuestionList.toString());

			// Call service to update record and get updated list of records
			feedbackQuestionService.updateFeedbackQuestionRecord(requestFeedbackQuestionList);

			// Prepare JSON string from entity bean
			//feedbackQuestionResponseData = jsonObjectMapper.writeValueAsString(feedbackQuestionList);

			// Prepare final response as JSON string
			feedbackQuestionResponseData = "[{\"success\":true,\"messages\":{},\"response\":{}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making update request to server. Error: "+ exception.getMessage());
			feedbackQuestionResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be updated.\"},\"response\":{}}]";

			return feedbackQuestionResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateFeedbackQuestionRecord(): Update record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateFeedbackQuestionRecord(): Response data (json): " + feedbackQuestionResponseData);

		return feedbackQuestionResponseData;

	} //updateFeedbackQuestionRecord() method ends


	/**
	 * Invokes service method to delete one or more records. If records need to be softdeleted then Status flag from table would be set to 0.
	 * 
	 * @param feedbackQuestionRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/deleteFeedbackQuestionRecords", method=RequestMethod.POST)
	@ResponseBody
	public String deleteFeedbackQuestionRecord(@RequestBody String feedbackQuestionRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteFeedbackQuestionRecord()...");

		String feedbackQuestionResponseData = null;
		List<FeedbackQuestion> feedbackQuestionList = null;
		//String feedbackQuestionRequestData = "[{\"id\": 5}]";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion request data received: "+ feedbackQuestionRequestData);

			// Prepare feedbackQuestion entity from request JSON data
			feedbackQuestionList = jsonObjectMapper.readValue(feedbackQuestionRequestData, jsonObjectMapper.getTypeFactory().constructCollectionType( List.class, FeedbackQuestion.class));
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion data: "+ feedbackQuestionList.toString());

			// Call service to delete record(s) and get list of updated records
			feedbackQuestionList = feedbackQuestionService.deleteFeedbackQuestionRecord(feedbackQuestionList);

			// Prepare JSON string from entity bean
			feedbackQuestionResponseData = jsonObjectMapper.writeValueAsString(feedbackQuestionList);

			// Prepare final response as JSON string
			feedbackQuestionResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"feedbackQuestions\":" + feedbackQuestionResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making delete request to server. Error: "+ exception.getMessage());
			feedbackQuestionResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Question not deleted,this question has sub-questions mapped.\"},\"response\":{}}]";
			exception.printStackTrace();
			return feedbackQuestionResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteFeedbackQuestionRecord(): Delete record(s) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteFeedbackQuestionRecord(): Response data (json): " + feedbackQuestionResponseData);

		return feedbackQuestionResponseData;

	} //deleteFeedbackQuestionRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param feedbackQuestionRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchFeedbackQuestionRecord", method=RequestMethod.POST)
	@ResponseBody
	public String fetchFeedbackQuestionRecord(@RequestBody String feedbackQuestionRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchFeedbackQuestionRecord()...");

		String feedbackQuestionResponseData = null;
		FeedbackQuestion feedbackQuestion = null;
		List<FeedbackQuestion> feedbackQuestionList = null;
		
		//String feedbackQuestionRequestData = "{\"id\": 1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion request data received: "+ feedbackQuestionRequestData);

			// Prepare feedbackQuestion entity from request JSON data
			feedbackQuestion = jsonObjectMapper.readValue(feedbackQuestionRequestData, FeedbackQuestion.class);
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion data: "+ feedbackQuestion.toString());

			// Call service to fetch record and get entity bean
			feedbackQuestionList = feedbackQuestionService.fetchFeedbackQuestionRecord(feedbackQuestion);

			// Prepare JSON string from entity bean
			feedbackQuestionResponseData = jsonObjectMapper.writeValueAsString(feedbackQuestionList);
			
			feedbackQuestionResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"feedbackQuestion\":" + feedbackQuestionResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			feedbackQuestionResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return feedbackQuestionResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionRecord(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionRecord(): Response data (json): " + feedbackQuestionResponseData);

		return feedbackQuestionResponseData;

	} //fetchFeedbackQuestionRecord() method ends


	/**
	 * Invokes service method to fetch all records.
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/fetchAllFeedbackQuestionRecords", method=RequestMethod.GET)
	@ResponseBody
	public String fetchAllFeedbackQuestionRecord() {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllFeedbackQuestionRecord()...");

		String feedbackQuestionResponseData = null;
		List<FeedbackQuestion> feedbackQuestionList = new ArrayList<FeedbackQuestion>();

		try {

			// Call service to fetch all records
			feedbackQuestionList = feedbackQuestionService.fetchAllFeedbackQuestionRecord();

			// Prepare JSON string from list
			feedbackQuestionResponseData = jsonObjectMapper.writeValueAsString(feedbackQuestionList);

			// Prepare final response as JSON string
			feedbackQuestionResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"feedbackQuestions\":" + feedbackQuestionResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			feedbackQuestionResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return feedbackQuestionResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllFeedbackQuestionRecord(): Fetch all record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllFeedbackQuestionRecord(): Response data (json): " + feedbackQuestionResponseData);

		return feedbackQuestionResponseData;

	} //fetchAllFeedbackQuestionRecord() method ends


	/**
	 * Invokes service method to fetch records based on criteria.
	 * 
	 * @param feedbackQuestionRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchFeedbackQuestionRecords", method=RequestMethod.POST)
	@ResponseBody
	public String fetchFeedbackQuestionRecords(@RequestBody String feedbackQuestionRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchFeedbackQuestionRecords()...");

		String feedbackQuestionResponseData = null;
		FeedbackQuestion feedbackQuestion = null;
		List<FeedbackQuestion> feedbackQuestionList = new ArrayList<FeedbackQuestion>();
		//String feedbackQuestionRequestData = "{\"question\":\"Question Description\"}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion request data received: "+ feedbackQuestionRequestData);

			// Prepare feedbackQuestion entity from request JSON data
			feedbackQuestion = jsonObjectMapper.readValue(feedbackQuestionRequestData, FeedbackQuestion.class);
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion data: "+ feedbackQuestion.toString());

			// Call service to fetch records based on criteria
			feedbackQuestionList = feedbackQuestionService.fetchFeedbackQuestionRecords(feedbackQuestion);

			// Prepare JSON string from entity bean
			feedbackQuestionResponseData = jsonObjectMapper.writeValueAsString(feedbackQuestionList);

			// Prepare final response as JSON string
			feedbackQuestionResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"feedbackQuestions\":" + feedbackQuestionResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch records request to server. Error: "+ exception.getMessage());
			feedbackQuestionResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return feedbackQuestionResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionRecords(): Fetch record (based on criteria) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionRecords(): Response data (json): " + feedbackQuestionResponseData);

		return feedbackQuestionResponseData;

	} //fetchFeedbackQuestionRecords() method ends	

}