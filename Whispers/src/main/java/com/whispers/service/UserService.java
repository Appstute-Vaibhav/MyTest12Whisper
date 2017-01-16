/**
 * Created on 15 Jan, 2015
 */

package com.whispers.service;

// java imports
import java.util.List;








// spring framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








// application imports
import com.whispers.beans.User;
import com.whispers.beans.User12Whispers;
import com.whispers.dao.UserDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Service class implements business logic as well as delegates CRUD operations to DAO
 */

@Service
public class UserService {

	/**
	 * The service class instance
	 */
	@Autowired
	private UserDAO userDAO;

	/**
	 * Invokes dao method to add record.
	 * 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the list of User 	 {@link List<User> }
	 */
	public List<User> addUserRecord(User user) throws Exception {

		List <User>userList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUserRecord()...");

		
			Log.logMessage("INFO", this.getClass().getName(), "user data : "+ user.toString());

			// Call dao to add record
			user = userDAO.addUserRecord(user);
		
			if(user != null){
					userList = userDAO.fetchAllUserRecord(user);
				// Call service to fetch updated records
			}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Insert record call successfull...");

		return userList;

	} //addUserRecord() method ends

	
	/**
	 * Invokes dao method to add record.
	 * 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the list of User 	 {@link List<User> }
	 */
	public User addTempUserRecord(User user) throws Exception {	

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUserRecord()...");

		
			Log.logMessage("INFO", this.getClass().getName(), "user data : "+ user.toString());

			// Call dao to add record
			user = userDAO.addTempUserRecord(user);
					
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Insert record call successfull...");

		return user;

	} //addUserRecord() method ends



	/**
	 * Invokes dao method to update record.
	 * 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the list of User 	 {@link List<User> }
	 */
	public List<User> updateUserRecord( User user ) throws Exception {

		List<User> userList = null; 
		//Integer rowsAffectedCountHistTbl= -1;

		// TheUser record fetched from User table prior to update
		//User TempUser= new User();

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateUserRecord()...");

		
			// Get details of the record prior to update
			//TempUser = userDAO.fetchUserRecord(user);

			// Call dao to update record
			user = userDAO.updateUserRecord(user);

			// Call service to fetch updated records
			userList = userDAO.fetchAllUserRecord(user);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateUserRecord(): Update record call successfull...");

		return userList;

	} //updateUserRecord() method ends


	/**
	 * Invokes dao method to update record.
	 * 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the list of User 	 {@link List<User> }
	 */
	public Integer overrideUserRecord( User user ) throws Exception {

		//List<User> userList = null; 
		//Integer rowsAffectedCountHistTbl= -1;

		// TheUser record fetched from User table prior to update
		//User TempUser= new User();
		
		Log.logMessage("INFO", this.getClass().getName(), "Entering overrideUserRecord()...");

			Integer roleId = null;
			// Get details of the record prior to update
			//TempUser = userDAO.fetchUserRecord(user);

			// Call dao to update record
			roleId = userDAO.overrideUserRecord(user);
	
		Log.logMessage("INFO", this.getClass().getName(), "Exiting overrideUserRecord(): Update record call successfull...");

		return roleId;

	} //updateUserRecord() method ends


	
	/**
	 * Invokes dao method to delete a record.
	 * 
	 * @param userList	The list of User entity
	 *
	 * @return 	 Returns the list of User 	 {@link List<User> }
	 */
	public List<User> deleteUserRecord( List <User> deleteUserRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteUserRecord()...");

		Integer rowsAffectedCount= -1;
		List<User> userList = null;

			// Call dao to delete record
			rowsAffectedCount = userDAO.deleteUserRecord(deleteUserRecords);

			if(rowsAffectedCount.intValue() == 0) {
				throw new Exception("No record deleted.");
			}

			// Call service to fetch updated records
			userList = userDAO.fetchAllUserRecord(deleteUserRecords.get(0));

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteUserRecord(): Delete record call successfull...");

		return userList;

	} //deleteUserRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	public User fetchUserRecord( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserRecord()...");

			// Call dao to fetch record
			user = userDAO.fetchUserRecord(user);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Fetch record call successfull...");

		return user;

	} //fetchUserRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	public User getTempUserRecord( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering getTempUserRecord()...");

			// Call dao to fetch record
			user = userDAO.getTemptUserRecord(user);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting getTempUserRecord(): Fetch record call successfull...");

		return user;

	} //fetchUserRecord() method ends

	
	
	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the User12Whispers 	 {@link User12Whispers }
	 */
	public User12Whispers get12Whispers(User12Whispers user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering get12Whispers()...");

			// Call dao to fetch record
			user = userDAO.get12Whispers(user);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting get12Whispers(): Fetch record call successfull...");

		return user;

	} //get12Whispers() method ends
	
	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	public User checkRecord( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserRecord()...");

			// Call dao to fetch record
			user = userDAO.checkRecord(user);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Fetch record call successfull...");

		return user;

	} //fetchUserRecord() method ends


	
	/**
	 * Invokes dao method to fetch all records.
	 *
	 * @param User	The User entity
	 *
	 * @return 	 Returns the list of User 	 {@link List<User> }
	 */
	public List <User> fetchAllUserRecord(User user) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllUserRecord()...");

		List<User> userList = null;

			// Call service to fetch record
			userList = userDAO.fetchAllUserRecord(user);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllUserRecord(): Fetch all record call successfull...");

		return userList;

	} //fetchAllUserRecord() method ends


	/**
	 * Invokes dao method to fetch records based on criteria.
	 * 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the list of User 	 {@link List<User< }
	 */
	public List<User> fetchUserRecords( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserRecords()...");

		List<User> userList = null;
		
			// Call dao to fetch records based on criteria
			userList = userDAO.fetchUserRecords(user);

		return userList;

	} //fetchUserRecords() method ends

	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	public List<User> authenticateUserRecord( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering authenticateUserRecord()...");		
		
		List<User> userList = null;
			// Call dao to fetch record by username
		userList = userDAO.fetchUserRecordByUsername(user);	
			
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Fetch record call successfull...######"+userList.toString());

		return userList;

	} //fetchUserRecord() method ends

}