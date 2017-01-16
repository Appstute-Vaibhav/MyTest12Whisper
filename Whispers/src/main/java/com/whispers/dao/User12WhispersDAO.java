/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao;

// java imports
import java.util.List;







// application imports
import com.whispers.beans.User12Whispers;

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on User 12Whispers master
 */
public interface User12WhispersDAO {
	/**
	 * Inserts a record in User12Whispers table.
	 * @param User12Whispers	The User12Whispers entity
	 *
	 * @return 	Returns the User12Whispers	{@link User12Whispers }
	 */
	public User12Whispers addUser12WhispersRecord( User12Whispers user12Whispers ) throws Exception;

	/**
	 * Updates record in User12Whispers table.
	 * @param User12Whispers	The User12Whispers entity
	 *
	 * @return 	Returns the User12Whispers	{@link User12Whispers }
	 */
	public User12Whispers updateUser12WhispersRecord( User12Whispers user12Whispers ) throws Exception;

	/**
	 * Deletes a record from User12Whispers table.
	 * @param User12WhispersList	The list of User12Whispers entity
	 *
	 * @return 	Returns the deleted count of record(s) 	{@link List}
	 */
	public Integer deleteUser12WhispersRecord( final List<User12Whispers> deleteUser12WhispersRecords) throws Exception;

	/**
	 * Fetches a single record from User12Whispers table
	 * @param User12Whispers	The User12Whispers entity
	 *
	 * @return 	Returns the User12Whispers	{@link User12Whispers }
	 */
	public User12Whispers fetchUser12WhispersRecord( User12Whispers user12Whispers ) throws Exception;

	/**
	 * Fetches all the records from User12Whispers table
	 * @param user12Whispers 
	 *
	 * @return 	Returns the list of User12Whispers	{@link List}
	 */
	public List<User12Whispers> fetchAllUser12WhispersRecord(User12Whispers user12Whispers) throws Exception;

	/**
	 * Fetch one or more records from User12Whispers table for specified criteria
	 *
	 * @param User12Whispers	The User12Whispers entity
	 *
	 * @return 	Returns the list of User12Whispers	{@link List}
	 */
	public List<User12Whispers> fetchUser12WhispersRecords( User12Whispers user12Whispers ) throws Exception;

	/**
	 * Update  User12WhispersHistory table for update operation.
	 * @param User12Whispers	The User12Whispers entity
	 *
	 * @return 	Returns the updated count for record inserted into history table	{@link Integer }
	 */
	//public Integer updateUser12WhispersHistoryTblForUpdatedRecord( User12Whispers user12Whispers );

	/**
	 * Update  User12WhispersHistory table for delete operation
	 * @param User12WhispersList	The list of User12Whispers entity
	 *
	 * @return 	Returns the updated count for record(s) inserted into history table	{@link Integer }
	 */
	//public Integer updateUser12WhispersHistoryTblForDeletedRecord( List<User12Whispers> user12WhispersList );

	/**
	 * Fetches all the records from User12Whispers table for specified Ids
	 * @param User12WhispersList	The list of User12Whispers entity
	 *
	 * @return 	Returns the list of User12Whispers	{@link List}
	 */
	//public List<User12Whispers> fetchAllUser12WhispersRecordsByIds(List<User12Whispers> user12WhispersList);

}