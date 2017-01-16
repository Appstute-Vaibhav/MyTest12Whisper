/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao;

// java imports
import java.util.List;







// application imports
import com.whispers.beans.Winner;

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on Winner master
 */
public interface WinnerDAO {
	/**
	 * Inserts a record in Winner table.
	 * @param Winner	The Winner entity
	 *
	 * @return 	Returns the Winner	{@link Winner }
	 */
	public Winner addWinnerRecord( Winner winner ) throws Exception;

	/**
	 * Updates record in Winner table.
	 * @param Winner	The Winner entity
	 *
	 * @return 	Returns the Winner	{@link Winner }
	 */
	public Winner updateWinnerRecord( Winner winner ) throws Exception;

	/**
	 * Deletes a record from Winner table.
	 * @param WinnerList	The list of Winner entity
	 *
	 * @return 	Returns the deleted count of record(s) 	{@link List}
	 */
	public Integer deleteWinnerRecord( final List<Winner> deleteWinnerRecords) throws Exception;

	/**
	 * Fetches a single record from Winner table
	 * @param Winner	The Winner entity
	 *
	 * @return 	Returns the Winner	{@link Winner }
	 */
	public Winner fetchWinnerRecord( Winner winner ) throws Exception;

	/**
	 * Fetches all the records from Winner table
	 *
	 * @return 	Returns the list of Winner	{@link List}
	 */
	public List<Winner> fetchAllWinnerRecord() throws Exception;

	/**
	 * Fetch one or more records from Winner table for specified criteria
	 *
	 * @param Winner	The Winner entity
	 *
	 * @return 	Returns the list of Winner	{@link List}
	 */
	public List<Winner> fetchWinnerRecords() throws Exception;

	/**
	 * Update  WinnerHistory table for update operation.
	 * @param Winner	The Winner entity
	 *
	 * @return 	Returns the updated count for record inserted into history table	{@link Integer }
	 */
	//public Integer updateWinnerHistoryTblForUpdatedRecord( Winner winner );

	/**
	 * Update  WinnerHistory table for delete operation
	 * @param WinnerList	The list of Winner entity
	 *
	 * @return 	Returns the updated count for record(s) inserted into history table	{@link Integer }
	 */
	//public Integer updateWinnerHistoryTblForDeletedRecord( List<Winner> winnerList );

	/**
	 * Fetches all the records from Winner table for specified Ids
	 * @param WinnerList	The list of Winner entity
	 *
	 * @return 	Returns the list of Winner	{@link List}
	 */
	//public List<Winner> fetchAllWinnerRecordsByIds(List<Winner> winnerList);
	
	public void fetchCurrentWeekWinnerRecords( Winner winner ) throws Exception;

}