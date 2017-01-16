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
import com.whispers.beans.FeedbackQuestion;
import com.whispers.beans.UserFeedback;
import com.whispers.dao.FeedbackQuestionDAO;
import com.whispers.dao.FeedbackQuestionOptionDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Service class implements business logic as well as delegates CRUD operations to DAO
 */

@Service
public class FeedbackQuestionService {

	/**
	 * The DAO class instance
	 */
	@Autowired
	private FeedbackQuestionDAO feedbackQuestionDAO;

	@Autowired
	private FeedbackQuestionOptionDAO feedbackQuestionOptionDAO;

	/**
	 * Invokes dao method to add record.
	 * 
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the list of FeedbackQuestion 	 {@link List<FeedbackQuestion> }
	 */
	public List<FeedbackQuestion> addFeedbackQuestionRecord(List<FeedbackQuestion> feedbackQuestionList) throws Exception {

		//List<FeedbackQuestion> feedbackQuestionList = null; 
		//List <FeedbackQuestionOption> feedbackQuestionOptionList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering addFeedbackQuestionRecord()...");

		
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion data : "+ feedbackQuestionList.toString());

			// Call dao to add question record
			feedbackQuestionList = feedbackQuestionDAO.addFeedbackQuestionRecord(feedbackQuestionList);
			
			feedbackQuestionList = feedbackQuestionDAO.fetchAllFeedbackQuestionRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addFeedbackQuestionRecord(): Insert record call successfull...");

		return feedbackQuestionList;

	} //addFeedbackQuestionRecord() method ends
	
	/**
	 * Invokes dao method to add record.
	 * 
	 * @param userFeedback	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the list of userFeedback 	 {@link List<FeedbackQuestion> }
	 */
	public List<UserFeedback> addUserFeedback(List<UserFeedback> userFeedbackList) throws Exception {

		//List<FeedbackQuestion> feedbackQuestionList = null; 
		//List <FeedbackQuestionOption> feedbackQuestionOptionList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUserFeedback()...");

		
			Log.logMessage("INFO", this.getClass().getName(), "feedbackQuestion data : "+ userFeedbackList.toString());

			// Call dao to add question record
			userFeedbackList = feedbackQuestionDAO.addUserFeedback(userFeedbackList);
			
			//feedbackQuestionList = feedbackQuestionDAO.fetchAllFeedbackQuestionRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserFeedback(): Insert record call successfull...");

		return userFeedbackList;

	} //addFeedbackQuestionRecord() method ends


	/**
	 * Invokes dao method to update record.
	 * 
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the list of FeedbackQuestion 	 {@link List<FeedbackQuestion> }
	 */
	public List<FeedbackQuestion> updateFeedbackQuestionRecord( List<FeedbackQuestion> feedbackQuestion ) throws Exception {

		List<FeedbackQuestion> feedbackQuestionList = null; 

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateFeedbackQuestionRecord()...");
		
			// Get details of the record prior to update
			//TempFeedbackQuestion = feedbackQuestionDAO.fetchFeedbackQuestionRecord(feedbackQuestion);

			// Call dao to update question record
			feedbackQuestionDAO.updateFeedbackQuestionRecord(feedbackQuestion);
			
			//feedbackQuestionOptionList = feedbackQuestion.getFeedbackQuestionOptions();
			
			// Call dao to delete option record
			//feedbackQuestionOptionDAO.deleteFeedbackQuestionOptionRecords(feedbackQuestionOptionList);
			
			// Call dao to update option record
			//feedbackQuestionOptionDAO.updateFeedbackQuestionOptionRecord(feedbackQuestionOptionList);

			// Call service to fetch updated records
			feedbackQuestionList = feedbackQuestionDAO.fetchAllFeedbackQuestionRecord();

			//rowsAffectedCountHistTbl = feedbackQuestionDAO.updateFeedbackQuestionHistoryTblForUpdatedRecord(TempFeedbackQuestion);

			//if(rowsAffectedCountHistTbl.intValue() == 0) {
				//throw new Exception("History table not updated for deleted records.");
			//}

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateFeedbackQuestionRecord(): Update record call successfull...");

		return feedbackQuestionList;

	} //updateFeedbackQuestionRecord() method ends


	/**
	 * Invokes dao method to delete a record.
	 * 
	 * @param feedbackQuestionList	The list of FeedbackQuestion entity
	 *
	 * @return 	 Returns the list of FeedbackQuestion 	 {@link List<FeedbackQuestion> }
	 */
	public List <FeedbackQuestion> deleteFeedbackQuestionRecord( List <FeedbackQuestion> deleteFeedbackQuestionRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteFeedbackQuestionRecord()...");

		Integer rowsAffectedCount= -1;
		List<FeedbackQuestion> feedbackQuestionList = null;
		// Integer rowsAffectedCountHistTbl= -1;

		// The list of records fetched fromFeedbackQuestion table prior to deletion
		//List<FeedbackQuestion> TempFeedbackQuestionList = new ArrayList<FeedbackQuestion>();

		
			// Get details of all records prior to deletion
			//TempFeedbackQuestionList = feedbackQuestionDAO.fetchAllFeedbackQuestionRecordsByIds(deleteFeedbackQuestionRecords);

			// Call dao to delete record
			rowsAffectedCount = feedbackQuestionDAO.deleteFeedbackQuestionRecord(deleteFeedbackQuestionRecords);			
			
			if(rowsAffectedCount.intValue() == 0) {
				throw new Exception("No record deleted.");
			}

			// Call service to fetch updated records
			feedbackQuestionList = feedbackQuestionDAO.fetchAllFeedbackQuestionRecord();

			//rowsAffectedCountHistTbl = feedbackQuestionDAO.updateFeedbackQuestionHistoryTblForDeletedRecord(TempFeedbackQuestionList);

			//if(rowsAffectedCountHistTbl.intValue() == 0) {
				//throw new Exception("History table not updated for deleted records.");
			//}

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteFeedbackQuestionRecord(): Delete record call successfull...");

		return feedbackQuestionList;

	} //deleteFeedbackQuestionRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the FeedbackQuestion 	 {@link FeedbackQuestion }
	 */
	public List<FeedbackQuestion> fetchFeedbackQuestionRecord( FeedbackQuestion feedbackQuestion ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchFeedbackQuestionRecord()...");
		
			List<FeedbackQuestion> feedbackQuestionList= null;
		
			// Call dao to fetch record
			feedbackQuestionList = feedbackQuestionDAO.fetchFeedbackQuestionRecord(feedbackQuestion);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionRecord(): Fetch record call successfull...");

		return feedbackQuestionList;

	} //fetchFeedbackQuestionRecord() method ends


	/**
	 * Invokes dao method to fetch all records.
	 * 
	 * @param none
	 *
	 * @return 	 Returns the list of FeedbackQuestion 	 {@link List<FeedbackQuestion> }
	 */
	public List <FeedbackQuestion> fetchAllFeedbackQuestionRecord() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllFeedbackQuestionRecord()...");

		List<FeedbackQuestion> feedbackQuestionList = null;

		

			// Call service to fetch record
			feedbackQuestionList = feedbackQuestionDAO.fetchAllFeedbackQuestionRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllFeedbackQuestionRecord(): Fetch all record call successfull...");

		return feedbackQuestionList;

	} //fetchAllFeedbackQuestionRecord() method ends


	/**
	 * Invokes dao method to fetch records based on criteria.
	 * 
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the list of FeedbackQuestion 	 {@link List<FeedbackQuestion< }
	 */
	public List<FeedbackQuestion> fetchFeedbackQuestionRecords( FeedbackQuestion feedbackQuestion ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchFeedbackQuestionRecords()...");

		List<FeedbackQuestion> feedbackQuestionList = null;

		
			// Call dao to fetch records based on criteria
			feedbackQuestionList = feedbackQuestionDAO.fetchFeedbackQuestionRecords(feedbackQuestion);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionRecords(): Fetch record (based on criteria) call successfull...");

		return feedbackQuestionList;

	} //fetchFeedbackQuestionRecords() method ends

	public List<UserFeedback> addUserFeedbackRecord( UserFeedback userFeedback ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUserFeedbackRecord()...");

		List<UserFeedback> userFeedbackList = null;
		
			// Call dao to fetch records based on criteria
		userFeedbackList = feedbackQuestionDAO.addUserFeedbackRecord(userFeedback);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionRecords(): Fetch record (based on criteria) call successfull...");

		return userFeedbackList;

	} //fetchFeedbackQuestionRecords() method ends

	

}