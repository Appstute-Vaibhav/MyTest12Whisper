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
import com.whispers.beans.Winner;
import com.whispers.dao.WinnerDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Service class implements business logic as well as delegates CRUD operations to DAO
 */

@Service
public class WinnerService {

	/**
	 * The service class instance
	 */
	@Autowired
	private WinnerDAO winnerDAO;

	/**
	 * Invokes dao method to add record.
	 * 
	 * @param winner	The Winner entity
	 *
	 * @return 	 Returns the list of Winner 	 {@link List<Winner> }
	 */
	public List<Winner> addWinnerRecord(Winner winner) throws Exception {

		List <Winner>winnerList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering addWinnerRecord()...");

		
			Log.logMessage("INFO", this.getClass().getName(), "winner data : "+ winner.toString());

			// Call dao to add record
			winner = winnerDAO.addWinnerRecord(winner);

			// Call service to fetch updated records
			winnerList = winnerDAO.fetchAllWinnerRecord();
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addWinnerRecord(): Insert record call successfull...");

		return winnerList;

	} //addWinnerRecord() method ends


	/**
	 * Invokes dao method to update record.
	 * 
	 * @param winner	The Winner entity
	 *
	 * @return 	 Returns the list of Winner 	 {@link List<Winner> }
	 */
	public List<Winner> updateWinnerRecord( Winner winner ) throws Exception {

		List<Winner> winnerList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateWinnerRecord()...");

			// Call dao to update record
			winner = winnerDAO.updateWinnerRecord(winner);

			// Call service to fetch updated records
			winnerList = winnerDAO.fetchAllWinnerRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateWinnerRecord(): Update record call successfull...");

		return winnerList;

	} //updateWinnerRecord() method ends


	/**
	 * Invokes dao method to delete a record.
	 * 
	 * @param winnerList	The list of Winner entity
	 *
	 * @return 	 Returns the list of Winner 	 {@link List<Winner> }
	 */
	public List <Winner> deleteWinnerRecord( List <Winner> deleteWinnerRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteWinnerRecord()...");

		Integer rowsAffectedCount= -1;
		List<Winner> winnerList = null;

			// Call dao to delete record
			rowsAffectedCount = winnerDAO.deleteWinnerRecord(deleteWinnerRecords);

			if(rowsAffectedCount.intValue() == 0) {
				throw new Exception("No record deleted.");
			}

			// Call service to fetch updated records
			winnerList = winnerDAO.fetchAllWinnerRecord();

			Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteWinnerRecord(): Delete record call successfull...");

		return winnerList;

	} //deleteWinnerRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param winner	The Winner entity
	 *
	 * @return 	 Returns the Winner 	 {@link Winner }
	 */
	public Winner fetchWinnerRecord( Winner winner ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWinnerRecord()...");
	
			// Call dao to fetch record
			winner = winnerDAO.fetchWinnerRecord(winner);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWinnerRecord(): Fetch record call successfull...");

		return winner;

	} //fetchWinnerRecord() method ends


	/**
	 * Invokes dao method to fetch all records.
	 * 
	 * @param none
	 *
	 * @return 	 Returns the list of Winner 	 {@link List<Winner> }
	 */
	public List <Winner> fetchAllWinnerRecord() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllWinnerRecord()...");

		List<Winner> winnerList = null;

			// Call service to fetch record
			winnerList = winnerDAO.fetchAllWinnerRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWinnerRecord(): Fetch all record call successfull...");

		return winnerList;

	} //fetchAllWinnerRecord() method ends


	/**
	 * Invokes dao method to fetch records based on criteria.
	 * 
	 * @param winner	The Winner entity
	 *
	 * @return 	 Returns the list of Winner 	 {@link List<Winner< }
	 */
	public void fetchCurrentWeekWinnerRecord( Winner winner ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchCurrentWinnerRecords()...");

		// Call dao to fetch records based on criteria
		winnerDAO.fetchCurrentWeekWinnerRecords(winner);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchCurrentWinnerRecords(): Fetch record (based on criteria) call successfull...");


	} //fetchWinnerRecords() method ends
	
	public List<Winner> fetchWinnerRecords() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWinnerRecords()...");

		List<Winner> winnerList = null;
		
			// Call dao to fetch records based on criteria
			winnerList = winnerDAO.fetchWinnerRecords();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWinnerRecords(): Fetch record (based on criteria) call successfull...");

		return winnerList;

	} //fetchWinnerRecords() method ends

}