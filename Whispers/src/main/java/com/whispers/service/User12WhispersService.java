/**
 * Created on 21 Jan, 2015
 */

package com.whispers.service;

// java imports
import java.util.List;

// spring framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// application imports
import com.whispers.beans.User12Whispers;
import com.whispers.dao.User12WhispersDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Service class implements business logic as well as delegates CRUD operations to DAO
 */

@Service
public class User12WhispersService {

	/**
	 * The service class instance
	 */
	@Autowired
	private User12WhispersDAO user12WhispersDAO;

	/**
	 * Invokes dao method to add record.
	 * 
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the list of User12Whispers 	 {@link List<User12Whispers> }
	 */
	public List<User12Whispers> addUser12WhispersRecord(User12Whispers user12Whispers) throws Exception {

		List <User12Whispers>user12WhispersList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUser12WhispersRecord()...");

		
			Log.logMessage("INFO", this.getClass().getName(), "user12Whispers data : "+ user12Whispers.toString());

			// Call dao to add record
			user12Whispers = user12WhispersDAO.addUser12WhispersRecord(user12Whispers);

			// Call service to fetch updated records
			user12WhispersList = user12WhispersDAO.fetchAllUser12WhispersRecord(new User12Whispers());

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUser12WhispersRecord(): Insert record call successfull...");

		return user12WhispersList;

	} //addUser12WhispersRecord() method ends


	/**
	 * Invokes dao method to update record.
	 * 
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the list of User12Whispers 	 {@link List<User12Whispers> }
	 */
	public List<User12Whispers> updateUser12WhispersRecord( User12Whispers user12Whispers ) throws Exception {

		List<User12Whispers> user12WhispersList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateUser12WhispersRecord()...");

			// Call dao to update record
			user12Whispers = user12WhispersDAO.updateUser12WhispersRecord(user12Whispers);

			// Call service to fetch updated records
			user12WhispersList = user12WhispersDAO.fetchAllUser12WhispersRecord(new User12Whispers());

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateUser12WhispersRecord(): Update record call successfull...");

		return user12WhispersList;

	} //updateUser12WhispersRecord() method ends


	/**
	 * Invokes dao method to delete a record.
	 * 
	 * @param user12WhispersList	The list of User12Whispers entity
	 *
	 * @return 	 Returns the list of User12Whispers 	 {@link List<User12Whispers> }
	 */
	public List <User12Whispers> deleteUser12WhispersRecord( List <User12Whispers> deleteUser12WhispersRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteUser12WhispersRecord()...");

		Integer rowsAffectedCount= -1;
		List<User12Whispers> user12WhispersList = null;

			// Call dao to delete record
			rowsAffectedCount = user12WhispersDAO.deleteUser12WhispersRecord(deleteUser12WhispersRecords);

			if(rowsAffectedCount.intValue() == 0) {
				throw new Exception("No record deleted.");
			}

			// Call service to fetch updated records
			user12WhispersList = user12WhispersDAO.fetchAllUser12WhispersRecord(new User12Whispers());

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteUser12WhispersRecord(): Delete record call successfull...");

		return user12WhispersList;

	} //deleteUser12WhispersRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the User12Whispers 	 {@link User12Whispers }
	 */
	public User12Whispers fetchUser12WhispersRecord( User12Whispers user12Whispers ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUser12WhispersRecord()...");

			// Call dao to fetch record
			user12Whispers = user12WhispersDAO.fetchUser12WhispersRecord(user12Whispers);

		return user12Whispers;

	} //fetchUser12WhispersRecord() method ends


	/**
	 * Invokes dao method to fetch all records.
	 * @param user12Whispers 
	 * 
	 * @return 	 Returns the list of User12Whispers 	 {@link List<User12Whispers> }
	 */
	public List <User12Whispers> fetchAllUser12WhispersRecord(User12Whispers user12Whispers) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllUser12WhispersRecord()...");

		List<User12Whispers> user12WhispersList = null;
		
			// Call service to fetch record
			user12WhispersList = user12WhispersDAO.fetchAllUser12WhispersRecord(user12Whispers);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllUser12WhispersRecord(): Fetch all record call successfull...");

		return user12WhispersList;

	} //fetchAllUser12WhispersRecord() method ends


	/**
	 * Invokes dao method to fetch records based on criteria.
	 * 
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the list of User12Whispers 	 {@link List<User12Whispers< }
	 */
	public List<User12Whispers> fetchUser12WhispersRecords( User12Whispers user12Whispers ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUser12WhispersRecords()...");

		List<User12Whispers> user12WhispersList = null;

			// Call dao to fetch records based on criteria
			user12WhispersList = user12WhispersDAO.fetchUser12WhispersRecords(user12Whispers);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUser12WhispersRecords(): Fetch record (based on criteria) call successfull...");

		return user12WhispersList;

	} //fetchUser12WhispersRecords() method ends


}