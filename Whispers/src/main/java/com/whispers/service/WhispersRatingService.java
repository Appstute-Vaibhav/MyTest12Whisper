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
import com.whispers.beans.WhispersRating;
import com.whispers.dao.WhispersRatingDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Service class implements business logic as well as delegates CRUD operations to DAO
 */

@Service
public class WhispersRatingService {

	/**
	 * The service class instance
	 */
	@Autowired
	private WhispersRatingDAO whispersRatingDAO;

	/**
	 * Invokes dao method to add record.
	 * 
	 * @param whispersRating	The WhispersRating entity
	 *
	 * @return 	 Returns the list of WhispersRating 	 {@link List<WhispersRating> }
	 */
	public List<WhispersRating> addWhispersRatingRecord(WhispersRating whispersRating) throws Exception {

		List <WhispersRating>whispersRatingList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering addWhispersRatingRecord()...");

		
			Log.logMessage("INFO", this.getClass().getName(), "whispersRating data : "+ whispersRating.toString());

			// Call dao to add record
			whispersRating = whispersRatingDAO.addWhispersRatingRecord(whispersRating);

			// Call service to fetch updated records
			whispersRatingList = whispersRatingDAO.fetchAllWhispersRatingRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addWhispersRatingRecord(): Insert record call successfull...");

		return whispersRatingList;

	} //addWhispersRatingRecord() method ends


	/**
	 * Invokes dao method to update record.
	 * 
	 * @param whispersRating	The WhispersRating entity
	 *
	 * @return 	 Returns the list of WhispersRating 	 {@link List<WhispersRating> }
	 */
	public List<WhispersRating> updateWhispersRatingRecord( WhispersRating whispersRating ) throws Exception {

		List<WhispersRating> whispersRatingList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateWhispersRatingRecord()...");


			// Call dao to update record
			whispersRating = whispersRatingDAO.updateWhispersRatingRecord(whispersRating);

			// Call service to fetch updated records
			whispersRatingList = whispersRatingDAO.fetchAllWhispersRatingRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateWhispersRatingRecord(): Update record call successfull...");

		return whispersRatingList;

	} //updateWhispersRatingRecord() method ends


	/**
	 * Invokes dao method to delete a record.
	 * 
	 * @param whispersRatingList	The list of WhispersRating entity
	 *
	 * @return 	 Returns the list of WhispersRating 	 {@link List<WhispersRating> }
	 */
	public List <WhispersRating> deleteWhispersRatingRecord( List <WhispersRating> deleteWhispersRatingRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteWhispersRatingRecord()...");

		Integer rowsAffectedCount= -1;
		List<WhispersRating> whispersRatingList = null;

			// Call dao to delete record
			rowsAffectedCount = whispersRatingDAO.deleteWhispersRatingRecord(deleteWhispersRatingRecords);

			if(rowsAffectedCount.intValue() == 0) {
				throw new Exception("No record deleted.");
			}

			// Call service to fetch updated records
			whispersRatingList = whispersRatingDAO.fetchAllWhispersRatingRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteWhispersRatingRecord(): Delete record call successfull...");

		return whispersRatingList;

	} //deleteWhispersRatingRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param whispersRating	The WhispersRating entity
	 *
	 * @return 	 Returns the WhispersRating 	 {@link WhispersRating }
	 */
	public WhispersRating fetchWhispersRatingRecord( WhispersRating whispersRating ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWhispersRatingRecord()...");
		
			// Call dao to fetch record
			whispersRating = whispersRatingDAO.fetchWhispersRatingRecord(whispersRating);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWhispersRatingRecord(): Fetch record call successfull...");

		return whispersRating;

	} //fetchWhispersRatingRecord() method ends


	/**
	 * Invokes dao method to fetch all records.
	 * 
	 * @param none
	 *
	 * @return 	 Returns the list of WhispersRating 	 {@link List<WhispersRating> }
	 */
	public List <WhispersRating> fetchAllWhispersRatingRecord() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllWhispersRatingRecord()...");

		List<WhispersRating> whispersRatingList = null;

			// Call service to fetch record
			whispersRatingList = whispersRatingDAO.fetchAllWhispersRatingRecord();
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWhispersRatingRecord(): Fetch all record call successfull...");

		return whispersRatingList;

	} //fetchAllWhispersRatingRecord() method ends


	/**
	 * Invokes dao method to fetch records based on criteria.
	 * 
	 * @param whispersRating	The WhispersRating entity
	 *
	 * @return 	 Returns the list of WhispersRating 	 {@link List<WhispersRating< }
	 */
	public List<WhispersRating> fetchWhispersRatingRecords( WhispersRating whispersRating ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWhispersRatingRecords()...");

		List<WhispersRating> whispersRatingList = null;
	
			// Call dao to fetch records based on criteria
			whispersRatingList = whispersRatingDAO.fetchWhispersRatingRecords(whispersRating);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWhispersRatingRecords(): Fetch record (based on criteria) call successfull...");

		return whispersRatingList;

	} //fetchWhispersRatingRecords() method ends


}