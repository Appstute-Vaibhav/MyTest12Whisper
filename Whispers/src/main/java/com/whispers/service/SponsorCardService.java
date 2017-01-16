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
import com.whispers.beans.SponsorCard;
import com.whispers.dao.SponsorCardDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Service class implements business logic as well as delegates CRUD operations to DAO
 */

@Service
public class SponsorCardService {

	/**
	 * The service class instance
	 */
	@Autowired
	private SponsorCardDAO sponsorCardDAO;

	/**
	 * Invokes dao method to add record.
	 * 
	 * @param sponsorCard	The SponsorCard entity
	 *
	 * @return 	 Returns the list of SponsorCard 	 {@link List<SponsorCard> }
	 */
	public List<SponsorCard> addSponsorCardRecord(SponsorCard sponsorCard) throws Exception {

		List <SponsorCard>sponsorCardList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSponsorCardRecord()...");

		
			Log.logMessage("INFO", this.getClass().getName(), "sponsorCard data : "+ sponsorCard.toString());

			// Call dao to add record
			sponsorCard = sponsorCardDAO.addSponsorCardRecord(sponsorCard);

			// Call service to fetch updated records
			sponsorCardList = sponsorCardDAO.fetchAllSponsorCardRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSponsorCardRecord(): Insert record call successfull...");

		return sponsorCardList;

	} //addSponsorCardRecord() method ends


	/**
	 * Invokes dao method to update record.
	 * 
	 * @param sponsorCard	The SponsorCard entity
	 *
	 * @return 	 Returns the list of SponsorCard 	 {@link List<SponsorCard> }
	 */
	public List<SponsorCard> updateSponsorCardRecord( SponsorCard sponsorCard ) throws Exception {

		List<SponsorCard> sponsorCardList = null; 
		// Integer rowsAffectedCountHistTbl= -1;

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateSponsorCardRecord()...");

			// Call dao to update record
			sponsorCard = sponsorCardDAO.updateSponsorCardRecord(sponsorCard);

			// Call service to fetch updated records
			sponsorCardList = sponsorCardDAO.fetchAllSponsorCardRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSponsorCardRecord(): Update record call successfull...");

		return sponsorCardList;

	} //updateSponsorCardRecord() method ends


	/**
	 * Invokes dao method to delete a record.
	 * 
	 * @param sponsorCardList	The list of SponsorCard entity
	 *
	 * @return 	 Returns the list of SponsorCard 	 {@link List<SponsorCard> }
	 */
	public List <SponsorCard> deleteSponsorCardRecord( List <SponsorCard> deleteSponsorCardRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteSponsorCardRecord()...");

		Integer rowsAffectedCount= -1;
		List<SponsorCard> sponsorCardList = null;

			// Call dao to delete record
			rowsAffectedCount = sponsorCardDAO.deleteSponsorCardRecord(deleteSponsorCardRecords);

			if(rowsAffectedCount.intValue() == 0) {
				throw new Exception("No record deleted.");
			}

			// Call service to fetch updated records
			sponsorCardList = sponsorCardDAO.fetchAllSponsorCardRecord();

			//rowsAffectedCountHistTbl = sponsorCardDAO.updateSponsorCardHistoryTblForDeletedRecord(TempSponsorCardList);

			//if(rowsAffectedCountHistTbl.intValue() == 0) {
				//throw new Exception("History table not updated for deleted records.");
			//}

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSponsorCardRecord(): Delete record call successfull...");

		return sponsorCardList;

	} //deleteSponsorCardRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param sponsorCard	The SponsorCard entity
	 *
	 * @return 	 Returns the SponsorCard 	 {@link SponsorCard }
	 */
	public SponsorCard fetchSponsorCardRecord( SponsorCard sponsorCard ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSponsorCardRecord()...");

		
			// Call dao to fetch record
			sponsorCard = sponsorCardDAO.fetchSponsorCardRecord(sponsorCard);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSponsorCardRecord(): Fetch record call successfull...");

		return sponsorCard;

	} //fetchSponsorCardRecord() method ends


	/**
	 * Invokes dao method to fetch all records.
	 * 
	 * @param none
	 *
	 * @return 	 Returns the list of SponsorCard 	 {@link List<SponsorCard> }
	 */
	public List <SponsorCard> fetchAllSponsorCardRecord() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllSponsorCardRecord()...");

		List<SponsorCard> sponsorCardList = null;

		

			// Call service to fetch record
			sponsorCardList = sponsorCardDAO.fetchAllSponsorCardRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSponsorCardRecord(): Fetch all record call successfull...");

		return sponsorCardList;

	} //fetchAllSponsorCardRecord() method ends


	/**
	 * Invokes dao method to fetch records based on criteria.
	 * 
	 * @param sponsorCard	The SponsorCard entity
	 *
	 * @return 	 Returns the list of SponsorCard 	 {@link List<SponsorCard< }
	 */
	public List<SponsorCard> fetchSponsorCardRecords( SponsorCard sponsorCard ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSponsorCardRecords()...");

		List<SponsorCard> sponsorCardList = null;

		
			// Call dao to fetch records based on criteria
			sponsorCardList = sponsorCardDAO.fetchSponsorCardRecords(sponsorCard);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSponsorCardRecords(): Fetch record (based on criteria) call successfull...");

		return sponsorCardList;

	} //fetchSponsorCardRecords() method ends


}