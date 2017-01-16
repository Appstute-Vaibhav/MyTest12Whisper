/**
 * Created on 15 Jan, 2015
 */

package com.whispers.controller;

// java imports
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.whispers.beans.User;
import com.whispers.beans.User12Whispers;
import com.whispers.service.EmailService;
import com.whispers.service.SceneService;
import com.whispers.service.UserService;
import com.whispers.utils.Log;
import com.whispers.utils.PropertiesFinder;
/**
 * @author anka technology solutions private limited
 *
 * Controller class accepts incoming requests and delegates to service for processing
 */
@Controller
public class UserController {
	/**
	 * The service class instance
	 */
	@Autowired
	private UserService userService	= null;

	@Autowired
	private SceneService sceneService	= null;
	
	private EmailService emailService = new EmailService();
	
	@Resource
	HttpSession session;
	
	/**
	 * The JSON object mapper
	 */
	private ObjectMapper jsonObjectMapper = new ObjectMapper();
	
	/**
	 * Invokes service method to add record.
	 * 
	 * @param userRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/registerUser", method=RequestMethod.POST)
	@ResponseBody
	public String addUserRecord(@RequestBody String userRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUserRecord()...");

		String userResponseData = null;
		User user = null;
		List<User> userList = new ArrayList<User>();
		//String userRequestData = "{\"firstName\": \"testFirstName\",\"lastName\": \"testLastName\",\"userName\": \"testUserName\",\"email\": \"mail@gmail.com\",\"displayName\":\"testDisplayName\",\"mobileNumber\":1324657891,\"password\": \"123\",\"roleId\": 4,\"dateOfBirth\": \"1989-12-12\",\"gender\": \"Male\",\"address1\": \"Address1\",\"address2\":\"Address2\",\"country\": \"Country\",\"state\": \"State\",\"place\": \"Place\",\"zip\": 134645,\"status\": 1,\"createdOn\": \"2015-01-01\",\"createdBy\": 1,\"modifiedOn\": \"2015-01-01\",\"modifiedBy\": 1}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User.class);
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ user.toString());

			// Call service to add record and get list of updated records
			userList = userService.addUserRecord(user);
			
			if(userList != null){
				// Prepare JSON string from entity bean
				userResponseData = jsonObjectMapper.writeValueAsString(userList);

				// Prepare final response as JSON string
				userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"users\":" + userResponseData + "}}]";
			}else {
				userResponseData = "[{\"success\":false,\"messages\":{\"code\":2,\"message\":\"Record couldn't be added. Please check username.\"},\"response\":{}}]";				
			}
			
		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return userResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Response data (json): " + userResponseData);

		return userResponseData;

	} //addUserRecord() method ends


	@RequestMapping(value="/web/activateUser", method=RequestMethod.POST)
	@ResponseBody
	public String activateUserRecord(@RequestBody String userRequestData) {

		String userResponseData = null;
		User user = null;
		//User user1 = null;
		List<User> userList = new ArrayList<User>();
		//String userRequestData = "{\"firstName\": \"testFirstName\",\"lastName\": \"testLastName\",\"userName\": \"testUserName\",\"email\": \"mail@gmail.com\",\"displayName\":\"testDisplayName\",\"mobileNumber\":1324657891,\"password\": \"123\",\"roleId\": 4,\"dateOfBirth\": \"1989-12-12\",\"gender\": \"Male\",\"address1\": \"Address1\",\"address2\":\"Address2\",\"country\": \"Country\",\"state\": \"State\",\"place\": \"Place\",\"zip\": 134645,\"status\": 1,\"createdOn\": \"2015-01-01\",\"createdBy\": 1,\"modifiedOn\": \"2015-01-01\",\"modifiedBy\": 1}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User.class);
			
			Log.logMessage("INFO", this.getClass().getName(), "user data : "+ user.toString());
			
			user = userService.getTempUserRecord(user);					
			
			userList = userService.updateUserRecord(user);

			// Prepare JSON string from entity bean
			userResponseData = jsonObjectMapper.writeValueAsString(userList);
			
			// Prepare final response as JSON string
			userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"users\":" + userResponseData + "}}]";
					
		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making insert request to server. Error: " + exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be added.\"},\"response\":{}}]";
			
			return userResponseData;
		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Insert record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Response data (json): " + userResponseData);

		return userResponseData;
	}
	
	/**
	 * Invokes service method to update record.
	 * 
	 * @param userRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/updateUserRecord", method=RequestMethod.POST)
	@ResponseBody
	public String updateUserRecord(@RequestBody String userRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateUserRecord()...");

		String userResponseData = null;
		User user = null;
		List<User> userList = null;
		//String userRequestData = "{\"id\": 4,\"firstName\": \"testFirstNameUpdate\",\"lastName\": \"testLastNameUpdate\",\"userName\": \"testUserNameUpdate\",\"email\": \"mail@gmail.com\",\"displayName\":\"testDisplayName\",\"mobileNumber\":1324657891,\"password\": \"123\",\"roleId\": 4,\"dateOfBirth\": \"1989-12-12\",\"gender\": \"Male\",\"address1\": \"Address1\",\"address2\":\"Address2\",\"country\": \"Country\",\"state\": \"State\",\"place\": \"Place\",\"zip\": 134645,\"status\": 1,\"createdOn\": \"2015-01-01\",\"createdBy\": 1,\"modifiedOn\": \"2015-01-01\",\"modifiedBy\": 1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User.class);
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ user.toString());

			// Call service to update record and get updated list of records
			userList = userService.updateUserRecord(user);

			// Prepare JSON string from entity bean
			userResponseData = jsonObjectMapper.writeValueAsString(userList);
			
			// Prepare final response as JSON string
			userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"users\":" + userResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making update request to server. Error: "+ exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be updated.\"},\"response\":{}}]";

			return userResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateUserRecord(): Update record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateUserRecord(): Response data (json): " + userResponseData);

		return userResponseData;

	} //updateUserRecord() method ends


	/**
	 * Invokes service method to override record.
	 * 
	 * @param userRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/overrideUserRecord", method=RequestMethod.POST)
	@ResponseBody
	public String overrideUserRecord(@RequestBody String userRequestData,HttpServletRequest request) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering overrideUserRecord(String userRequestData,HttpServletRequest request)...");

		String userResponseData = null;
		
		User user = null;
		
		//Integer roleId = null;
		//String userRequestData = "{\"id\": 4,\"firstName\": \"testFirstNameUpdate\",\"lastName\": \"testLastNameUpdate\",\"userName\": \"testUserNameUpdate\",\"email\": \"mail@gmail.com\",\"displayName\":\"testDisplayName\",\"mobileNumber\":1324657891,\"password\": \"123\",\"roleId\": 4,\"dateOfBirth\": \"1989-12-12\",\"gender\": \"Male\",\"address1\": \"Address1\",\"address2\":\"Address2\",\"country\": \"Country\",\"state\": \"State\",\"place\": \"Place\",\"zip\": 134645,\"status\": 1,\"createdOn\": \"2015-01-01\",\"createdBy\": 1,\"modifiedOn\": \"2015-01-01\",\"modifiedBy\": 1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received to override: "+ userRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User.class);
			Log.logMessage("INFO", this.getClass().getName(), "user data to override: "+ user.toString());

			String uri = user.getAddress2();

			// Call service to get role Id
			/*roleId = userService.overrideUserRecord(user);
			
			user.setRoleId(roleId);*/
									
			user = userService.addTempUserRecord(user);

			// Prepare JSON string from entity bean
			userResponseData = jsonObjectMapper.writeValueAsString(user);
			
			userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user\":" + userResponseData + "}}]";
			
			/*String uri = request.getScheme() + "://" +   // "http" + "://
		             request.getServerName() +       // "myhost"
		             ":" +                           // ":"
		             request.getServerPort()+       // "8080"
		             request.getContextPath();       // "/people"
		       */			
			
			//uri = uri+"/activateUser"+'?'+"id="+user.getId()+'&'+"roleId="+user.getRoleId()+'&'+"email="+ user.getEmail()+'&'+"xyz="+user.getPassword()+'&'+"status="+user.getStatus();
			
			uri = uri+"/activateUser"+'?'+"id1="+user.getId()+'&'+"id2="+user.getStatus();
			
			Session session1 =  emailService.sendEmail();
			try{
				//if(mnbNoticeBoardMapping.getMnbUserId() == null){
				String emailDetails = PropertiesFinder.getValue("EMAILDETAILS", "email");
				Message message = new MimeMessage(session1);
				//message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mnbUser.getEmail()));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
				message.setSubject("Account activation link");
				message.setText("Dear " + user.getFirstName() + " " + user.getLastName() +",\n\n" +"Your account is created successfully.\n\n"+ "Please click here to activate \n\n"+uri+"\n\n"+emailDetails);
				message.setFrom(new InternetAddress("chetan.wayade@anka.co.in","Anka Support"));
				Transport.send(message);
				//}	
				
			}catch (Exception e) {
				Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making send Email request to server. Error: "+ e.getMessage());
				throw e;
			}

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Please try again.\"},\"response\":{}}]";
		
			return userResponseData;
		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Response data (json): " + userResponseData);

		return userResponseData;

	} //updateUserRecord() method ends

	
	/**
	 * Invokes service method to delete one or more records. If records need to be softdeleted then Status flag from table would be set to 0.
	 * 
	 * @param userRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/deleteUserRecords", method=RequestMethod.POST)
	@ResponseBody
	public String deleteUserRecord(@RequestBody String userRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteUserRecord()...");

		String userResponseData = null;
		List<User> userList = null;
		//String userRequestData = "[{\"id\": 5}]";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);

			// Prepare user entity from request JSON data
			userList = jsonObjectMapper.readValue(userRequestData, jsonObjectMapper.getTypeFactory().constructCollectionType( List.class, User.class));
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ userList.toString());

			// Call service to delete record(s) and get list of updated records
			userList = userService.deleteUserRecord(userList);

			// Prepare JSON string from entity bean
			userResponseData = jsonObjectMapper.writeValueAsString(userList);

			// Prepare final response as JSON string
			userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"users\":" + userResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making delete request to server. Error: "+ exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. One or more record couldn't be deleted.\"},\"response\":{}}]";
			
			return userResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteUserRecord(): Delete record(s) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteUserRecord(): Response data (json): " + userResponseData);

		return userResponseData;

	} //deleteUserRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param userRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchUserRecord", method=RequestMethod.POST)
	@ResponseBody
	public String fetchUserRecord(@RequestBody String userRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserRecord()...");

		String userResponseData = null;
		User user = null;
		//String userRequestData = "{\"id\": 1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User.class);
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ user.toString());

			// Call service to fetch record and get entity bean
			user = userService.fetchUserRecord(user);

			// Prepare JSON string from entity bean
			userResponseData = jsonObjectMapper.writeValueAsString(user);
			
			userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user\":" + userResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return userResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Response data (json): " + userResponseData);

		return userResponseData;

	} //fetchUserRecord() method ends
	
	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param userRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/app/get12Whispers", method=RequestMethod.POST)
	@ResponseBody
	public String get12Whispers(@RequestBody String userRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering get12Whispers()...");

		String userResponseData = null;
		User12Whispers user = null;
		//String userRequestData = "{\"id\": 1}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User12Whispers.class);
			
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ user.toString());

			// Call service to fetch record and get entity bean
			user = userService.get12Whispers(user);

			// Prepare JSON string from entity bean
			userResponseData = jsonObjectMapper.writeValueAsString(user);
			
			userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user12Whispers\":" + userResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Record couldn't be fetched.\"},\"response\":{}}]";
			
			return userResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting get12Whispers(): Fetch record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting get12Whispers(): Response data (json): " + userResponseData);

		return userResponseData;

	} //get12Whispers() method ends


	/**
	 * Invokes service method to fetch all records.
	 * 
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchAllUserRecords", method=RequestMethod.POST)
	@ResponseBody
	public String fetchAllUserRecord(@RequestBody String userRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllUserRecord()...");

		String userResponseData = null;
		List<User> userList = new ArrayList<User>();
		User user = null;
		//String userRequestData = "{\"roleId\": 2}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User.class);
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ user.toString());

			// Call service to fetch all records
			userList = userService.fetchAllUserRecord(user);

			// Prepare JSON string from list
			userResponseData = jsonObjectMapper.writeValueAsString(userList);

			// Prepare final response as JSON string
			userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"users\":" + userResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch all records request to server. Error: "+ exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return userResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllUserRecord(): Fetch all record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllUserRecord(): Response data (json): " + userResponseData);

		return userResponseData;

	} //fetchAllUserRecord() method ends
	
	/**
	 * Invokes service method to fetch records based on criteria.
	 * 
	 * @param userRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value="/web/fetchUserRecords", method=RequestMethod.POST)
	@ResponseBody
	public String fetchUserRecords(@RequestBody String userRequestData) {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserRecords()...");

		String userResponseData = null;
		User user = null;
		List<User> userList = new ArrayList<User>();
		//String userRequestData = "{\"firstName\": \"testFirstName\",\"lastName\": \"testLastName\",\"userName\": \"testUserName\"}";

		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User.class);
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ user.toString());

			// Call service to fetch records based on criteria
			userList = userService.fetchUserRecords(user);

			// Prepare JSON string from entity bean
			userResponseData = jsonObjectMapper.writeValueAsString(userList);

			// Prepare final response as JSON string
			userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"users\":" + userResponseData + "}}]";

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch records request to server. Error: "+ exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Server Error. Records couldn't be fetched.\"},\"response\":{}}]";
			
			return userResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecords(): Fetch record (based on criteria) call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecords(): Response data (json): " + userResponseData);

		return userResponseData;

	} //fetchUserRecords() method ends


	/**
	 * Invokes service method to authenticate user record.
	 * 
	 * @param userRequestData received as JSON string. 	
	 *
	 * @return 	 Returns the string representation of response as JSON string	 {@link String}
	 */
	@RequestMapping(value = "/app/authenticateUser", method = RequestMethod.POST)
	@ResponseBody
	public String authenticateUser(@RequestBody String userRequestData){

		Log.logMessage("INFO", this.getClass().getName(), "Entering authenticateUser()...");

		String userResponseData = null;
		User user = null;
		List<User> userList = null;

	   // String userRequestData = "{\"userName\": \"admin\",\"password\": \"password"\"}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);								
			
			//sceneService.sendNotification();
			
			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User.class);
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ user.toString());

			// Call service to fetch record and get entity bean
	
			if(!user.getRoleId().equals(0)){
				userList = userService.authenticateUserRecord(user);
				
				if(userList.size() != 0){
					for(User user1 : userList){					
						if(user1.getRoleId().equals(user.getRoleId())){
							if(user1.getPassword().equals(user.getPassword()) && !user1.getStatus().equals(0)){
								userResponseData = jsonObjectMapper.writeValueAsString(user1);				
								userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user\":" + userResponseData + "}}]";
							}else if(!user1.getStatus().equals(0)){
								userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Authentication failed. Please enter correct password.\"},\"response\":{}}]";
							}else{
								userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Your account has been removed.\"},\"response\":{}}]";
							}
						}					
					}				
				}else{
					userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Authentication failed. Please enter correct email id.\"},\"response\":{}}]";
				}
			}else if(user.getRoleId().equals(0)){
				userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Your account has been removed.\"},\"response\":{}}]";
			}else if(user.getRoleId().equals(-1)){
				userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Authentication couldn't be performed. Please check email id and password.\"},\"response\":{}}]";
			}
			
		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Authentication couldn't be performed. Please check email address and password.\"},\"response\":{\"user\":" + userResponseData + "}}]";
			
			return userResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting authenticateUser(): Authenticate record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting authenticateUser(): Response data (json): " + userResponseData);

		return userResponseData;

	} //authenticateUser() method ends
	
	
	@RequestMapping(value = "/web/validUserName", method = RequestMethod.POST)
	@ResponseBody
	public String validUserName(@RequestBody String userRequestData){

		Log.logMessage("INFO", this.getClass().getName(), "Entering validUserName()...");

		String userResponseData = null;
		User user = null;
		List<User> userList = null;

	   // String userRequestData = "{\"userName\": \"admin\",\"password\": \"password"\"}";
		
		try {
			Log.logMessage("INFO", this.getClass().getName(), "user request data received: "+ userRequestData);

			// Prepare user entity from request JSON data
			user = jsonObjectMapper.readValue(userRequestData, User.class);
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ user.toString());

			// Call service to fetch record and get entity bean
			userList = userService.authenticateUserRecord(user);
			
			Log.logMessage("INFO", this.getClass().getName(), "user data: "+ userList.toString());

			userResponseData = jsonObjectMapper.writeValueAsString(userList);
			
			String userRole = "";
			
			for(User user1 : userList){
				if(user1.getRoleId() == 1){
					userRole = userRole + "Admin" + ',' ;
				}else
				if(user1.getRoleId() == 2){
					userRole = userRole + "Playwriter" + ',' ;
				}else
				if(user1.getRoleId() == 3){
					userRole = userRole + "Dramaturg" + ',' ;
				}else
				if(user1.getRoleId() == 4){
					userRole = userRole + "User" + ',' ;
				}							
			}
			
			if(userRole != ""){					
				// Prepare JSON string from entity bean							
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"You have registered as "+userRole +" with this email address,do you want to continue?\"},\"response\":{\"user\":" + userResponseData + "}}]";			
			//}
		}else{
			userResponseData = "[{\"success\":true,\"messages\":{},\"response\":{\"user\":" + userResponseData + "}}]";
		}
			

		} catch (Exception exception) { 
			Log.logMessage("ERROR", this.getClass().getName(), "Error occured while making fetch single record request to server. Error: "+ exception.getMessage());
			userResponseData = "[{\"success\":false,\"messages\":{\"code\":1,\"message\":\"Username is already exist,please try another.\"},\"response\":{}}]";
			
			return userResponseData;

		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting validUserName(): valid user record call successfull...");

		Log.logMessage("INFO", this.getClass().getName(), "Exiting validUserName(): Response data (json): " + userResponseData);

		return userResponseData;

	} //validUserName() method ends
	
	@RequestMapping(value = "/web/addContentToFile", method = RequestMethod.POST)
	@ResponseBody
	public String addContentToFile(@RequestBody String userRequestData) throws FileNotFoundException{
		
		Log.logMessage("INFO", this.getClass().getName(), "Entering addContentToFile()...");
		
		PrintWriter out = null;
		
		String responseData = null;
		try {
			
			 out = new PrintWriter("/root/apache-tomcat-8.0.17/webapps/12WhispersWebPortal/releases/master/partials/bodyTemplate.html");
			 
			 out.println(userRequestData);
			 
			 out.close();
			 
			 responseData = "[{\"success\":true,\"messages\":{},\"response\":{}}]";
			 
		} catch (Exception e) {
			e.printStackTrace();
		}						
		 return responseData;
	}
	
	@RequestMapping(value = "/web/getFileContent", method = RequestMethod.GET)
	@ResponseBody
	public String getFileContent() throws FileNotFoundException{
		
		Log.logMessage("INFO", this.getClass().getName(), "Entering addContentToFile()...");
			
		//BufferedReader br = null;
		
		String responseData = null;
		String text = null;		
		try {
			 BufferedReader br = new BufferedReader(new FileReader("/root/apache-tomcat-8.0.17/webapps/12WhispersWebPortal/releases/master/partials/bodyTemplate.html"));
			 try {
				 StringBuilder sb = new StringBuilder();
				 String line = br.readLine();
		        
				 while (line != null) {
					 sb.append(line);
					 //sb.append("\n");
					 line = br.readLine();		        
				 }
				 
				 text = '"'+sb.toString().replaceAll("\"", "'")+'"';		       
			 } finally {
		        br.close();
		    }					
			
			responseData = text;
			 
		} catch (Exception e) {
			e.printStackTrace();
			responseData = "[{\"success\":false,\"messages\":{},\"response\":{}}]";
		}						
		 return responseData;
	}
	
}