/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao;

// java imports
import java.util.List;







// application imports
import com.whispers.beans.SponsorCard;

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on Sponsor Card master
 */
public interface SponsorCardDAO {
	/**
	 * Inserts a record in SponsorCard table.
	 * @param SponsorCard	The SponsorCard entity
	 *
	 * @return 	Returns the SponsorCard	{@link SponsorCard }
	 */
	public SponsorCard addSponsorCardRecord( SponsorCard sponsorCard ) throws Exception;

	/**
	 * Updates record in SponsorCard table.
	 * @param SponsorCard	The SponsorCard entity
	 *
	 * @return 	Returns the SponsorCard	{@link SponsorCard }
	 */
	public SponsorCard updateSponsorCardRecord( SponsorCard sponsorCard ) throws Exception;

	/**
	 * Deletes a record from SponsorCard table.
	 * @param SponsorCardList	The list of SponsorCard entity
	 *
	 * @return 	Returns the deleted count of record(s) 	{@link List}
	 */
	public Integer deleteSponsorCardRecord( final List<SponsorCard> deleteSponsorCardRecords) throws Exception;

	/**
	 * Fetches a single record from SponsorCard table
	 * @param SponsorCard	The SponsorCard entity
	 *
	 * @return 	Returns the SponsorCard	{@link SponsorCard }
	 */
	public SponsorCard fetchSponsorCardRecord( SponsorCard sponsorCard ) throws Exception;

	/**
	 * Fetches all the records from SponsorCard table
	 *
	 * @param None
	 *
	 * @return 	Returns the list of SponsorCard	{@link List}
	 */
	public List<SponsorCard> fetchAllSponsorCardRecord() throws Exception;

	/**
	 * Fetch one or more records from SponsorCard table for specified criteria
	 *
	 * @param SponsorCard	The SponsorCard entity
	 *
	 * @return 	Returns the list of SponsorCard	{@link List}
	 */
	public List<SponsorCard> fetchSponsorCardRecords( SponsorCard sponsorCard ) throws Exception;

	/**
	 * Update  SponsorCardHistory table for update operation.
	 * @param SponsorCard	The SponsorCard entity
	 *
	 * @return 	Returns the updated count for record inserted into history table	{@link Integer }
	 */
	//public Integer updateSponsorCardHistoryTblForUpdatedRecord( SponsorCard sponsorCard );

	/**
	 * Update  SponsorCardHistory table for delete operation
	 * @param SponsorCardList	The list of SponsorCard entity
	 *
	 * @return 	Returns the updated count for record(s) inserted into history table	{@link Integer }
	 */
	//public Integer updateSponsorCardHistoryTblForDeletedRecord( List<SponsorCard> sponsorCardList );

	/**
	 * Fetches all the records from SponsorCard table for specified Ids
	 *
	 * @return 	Returns the list of SponsorCard	{@link List}
	 */
	//public List<SponsorCard> fetchAllSponsorCardRecordsByIds(List<SponsorCard> sponsorCardList);

}