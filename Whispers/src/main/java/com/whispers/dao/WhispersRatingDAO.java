/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao;

// java imports
import java.util.List;







// application imports
import com.whispers.beans.WhispersRating;

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on Whispers Rating master
 */
public interface WhispersRatingDAO {
	/**
	 * Inserts a record in WhispersRating table.
	 * @param WhispersRating	The WhispersRating entity
	 *
	 * @return 	Returns the WhispersRating	{@link WhispersRating }
	 */
	public WhispersRating addWhispersRatingRecord( WhispersRating whispersRating ) throws Exception;

	/**
	 * Updates record in WhispersRating table.
	 * @param WhispersRating	The WhispersRating entity
	 *
	 * @return 	Returns the WhispersRating	{@link WhispersRating }
	 */
	public WhispersRating updateWhispersRatingRecord( WhispersRating whispersRating ) throws Exception;

	/**
	 * Deletes a record from WhispersRating table.
	 * @param WhispersRatingList	The list of WhispersRating entity
	 *
	 * @return 	Returns the deleted count of record(s) 	{@link List}
	 */
	public Integer deleteWhispersRatingRecord( final List<WhispersRating> deleteWhispersRatingRecords) throws Exception;

	/**
	 * Fetches a single record from WhispersRating table
	 * @param WhispersRating	The WhispersRating entity
	 *
	 * @return 	Returns the WhispersRating	{@link WhispersRating }
	 */
	public WhispersRating fetchWhispersRatingRecord( WhispersRating whispersRating ) throws Exception;

	/**
	 * Fetches all the records from WhispersRating table
	 *
	 * @return 	Returns the list of WhispersRating	{@link List}
	 */
	public List<WhispersRating> fetchAllWhispersRatingRecord() throws Exception;

	/**
	 * Fetch one or more records from WhispersRating table for specified criteria
	 *
	 * @param WhispersRating	The WhispersRating entity
	 *
	 * @return 	Returns the list of WhispersRating	{@link List}
	 */
	public List<WhispersRating> fetchWhispersRatingRecords( WhispersRating whispersRating ) throws Exception;

	/**
	 * Update  WhispersRatingHistory table for update operation.
	 * @param WhispersRating	The WhispersRating entity
	 *
	 * @return 	Returns the updated count for record inserted into history table	{@link Integer }
	 */
	//public Integer updateWhispersRatingHistoryTblForUpdatedRecord( WhispersRating whispersRating );

	/**
	 * Update  WhispersRatingHistory table for delete operation
	 * @param WhispersRatingList	The list of WhispersRating entity
	 *
	 * @return 	Returns the updated count for record(s) inserted into history table	{@link Integer }
	 */
	//public Integer updateWhispersRatingHistoryTblForDeletedRecord( List<WhispersRating> whispersRatingList );

	/**
	 * Fetches all the records from WhispersRating table for specified Ids
	 * @param WhispersRatingList	The list of WhispersRating entity
	 *
	 * @return 	Returns the list of WhispersRating	{@link List}
	 */
	//public List<WhispersRating> fetchAllWhispersRatingRecordsByIds(List<WhispersRating> whispersRatingList);

}