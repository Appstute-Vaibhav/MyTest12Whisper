/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao;

// java imports
import java.util.List;








// application imports
import com.whispers.beans.User;
import com.whispers.beans.User12Whispers;

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on User master
 */
public interface UserDAO {
	/**
	 * Inserts a record in User table.
	 * @param User	The User entity
	 *
	 * @return 	Returns the User	{@link User }
	 */
	public User addUserRecord( User user ) throws Exception;

	/**
	 * Inserts a record in TempUser table.
	 * @param User	The User entity
	 *
	 * @return 	Returns the User	{@link User }
	 */
	public User addTempUserRecord( User user ) throws Exception;
	
	/**
	 * Updates record in User table.
	 * @param User	The User entity
	 *
	 * @return 	Returns the User	{@link User }
	 */
	public User updateUserRecord( User user ) throws Exception;

	/**
	 * Override record in User table.
	 * @param User	The User entity
	 *
	 * @return 	Returns the User	{@link User }
	 */
	public Integer overrideUserRecord( User user ) throws Exception;

	
	/**
	 * Deletes a record from User table.
	 * @param UserList	The list of User entity
	 *
	 * @return 	Returns the deleted count of record(s) 	{@link List}
	 */
	public Integer deleteUserRecord( final List<User> deleteUserRecords) throws Exception;

	/**
	 * Fetches a single record from User table
	 * @param User	The User entity
	 *
	 * @return 	Returns the User	{@link User }
	 */
	public User fetchUserRecord( User user ) throws Exception;

	/**
	 * Fetches a single record from User table
	 * @param User	The User entity
	 *
	 * @return 	Returns the User	{@link User }
	 */
	public User getTemptUserRecord( User user ) throws Exception;

	
	/**
	 * Fetches a single record from User12Whispers table
	 * @param User12Whispers	The User12Whispers entity
	 *
	 * @return 	Returns the User12Whispers	{@link User12Whispers }
	 */
	public User12Whispers get12Whispers(User12Whispers user ) throws Exception;

	
	/**
	 * Fetches a single record from TempUser table
	 * @param User	The User entity
	 *
	 * @return 	Returns the User	{@li
	 * nk User }
	 */
	public User checkRecord( User user ) throws Exception;

	
	/**
	 * Fetches all the records from User table
	 *
	 * @param User	The User entity
	 *
	 * @return 	Returns the list of User	{@link List}
	 */
	public List<User> fetchAllUserRecord(User user) throws Exception;

	/**
	 * Fetch one or more records from User table for specified criteria
	 *
	 * @param User	The User entity
	 *
	 * @return 	Returns the list of User	{@link List}
	 */
	public List<User> fetchUserRecords( User user ) throws Exception;

	/**
	 * Update  UserHistory table for update operation.
	 * @param User	The User entity
	 *
	 * @return 	Returns the updated count for record inserted into history table	{@link Integer }
	 */
	//public Integer updateUserHistoryTblForUpdatedRecord( User user );

	/**
	 * Update  UserHistory table for delete operation
	 * @param UserList	The list of User entity
	 *
	 * @return 	Returns the updated count for record(s) inserted into history table	{@link Integer }
	 */
	//public Integer updateUserHistoryTblForDeletedRecord( List<User> userList );

	/**
	 * Fetches all the records from User table for specified Ids
	 *
	 * @return 	Returns the list of User	{@link List}
	 */
	//public List<User> fetchAllUserRecordsByIds(List<User> userList);

	/**
	 * Fetches a single record by username from User table
	 * 
	 * @param User	The User entity
	 *
	 * @return 	Returns the User	{@link User }
	 */
	public List<User> fetchUserRecordByUsername( User user ) throws Exception;

}