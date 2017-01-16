/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao;

// java imports
import java.util.List;

// application imports
import com.whispers.beans.FeedbackQuestion;
import com.whispers.beans.UserFeedback;

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on Feedback Question master
 */
public interface FeedbackQuestionDAO {
	/**
	 * Inserts a record in FeedbackQuestion table.
	 * @param FeedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	Returns the FeedbackQuestion	{@link FeedbackQuestion }
	 * @throws Exception 
	 */
	public List<FeedbackQuestion> addFeedbackQuestionRecord( List<FeedbackQuestion> feedbackQuestion ) throws Exception;

	/**
	 * Inserts a record in FeedbackQuestion table.
	 * @param FeedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	Returns the FeedbackQuestion	{@link FeedbackQuestion }
	 * @throws Exception 
	 */
	public List<UserFeedback> addUserFeedback( List<UserFeedback> userFeedback ) throws Exception;
	
	/**
	 * Updates record in FeedbackQuestion table.
	 * @param FeedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	Returns the FeedbackQuestion	{@link FeedbackQuestion }
	 */
	public FeedbackQuestion updateFeedbackQuestionRecord(List<FeedbackQuestion> feedbackQuestion ) throws Exception;

	/**
	 * Deletes a record from FeedbackQuestion table.
	 * @param FeedbackQuestionList	The list of FeedbackQuestion entity
	 *
	 * @return 	Returns the deleted count of record(s) 	{@link List}
	 */
	public Integer deleteFeedbackQuestionRecord( final List<FeedbackQuestion> deleteFeedbackQuestionRecords) throws Exception;

	/**
	 * Fetches a single record from FeedbackQuestion table
	 * @param FeedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	Returns the FeedbackQuestion	{@link FeedbackQuestion }
	 */
	public List<FeedbackQuestion> fetchFeedbackQuestionRecord( FeedbackQuestion feedbackQuestion ) throws Exception;

	/**
	 * Fetches all the records from FeedbackQuestion table
	 *
	 * @param None
	 *
	 * @return 	Returns the list of FeedbackQuestion	{@link List}
	 */
	public List<FeedbackQuestion> fetchAllFeedbackQuestionRecord() throws Exception;

	/**
	 * Fetch one or more records from FeedbackQuestion table for specified criteria
	 *
	 * @param FeedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	Returns the list of FeedbackQuestion	{@link List}
	 */
	public List<FeedbackQuestion> fetchFeedbackQuestionRecords( FeedbackQuestion feedbackQuestion ) throws Exception;

	/**
	 * Update  FeedbackQuestionHistory table for update operation.
	 * @param FeedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	Returns the updated count for record inserted into history table	{@link Integer }
	 */
	//public Integer updateFeedbackQuestionHistoryTblForUpdatedRecord( FeedbackQuestion feedbackQuestion );

	/**
	 * Update  FeedbackQuestionHistory table for delete operation
	 * @param FeedbackQuestionList	The list of FeedbackQuestion entity
	 *
	 * @return 	Returns the updated count for record(s) inserted into history table	{@link Integer }
	 */
	//public Integer updateFeedbackQuestionHistoryTblForDeletedRecord( List<FeedbackQuestion> feedbackQuestionList );

	/**
	 * Fetches all the records from FeedbackQuestion table for specified Ids
	 *
	 * @return 	Returns the list of FeedbackQuestion	{@link List}
	 */
	//public List<FeedbackQuestion> fetchAllFeedbackQuestionRecordsByIds(List<FeedbackQuestion> feedbackQuestionList);

	/**
	 * Fetches all the records from FeedbackQuestion table for specified Ids
	 *
	 * @return 	Returns the list of FeedbackQuestion	{@link List}
	 */
	public List<UserFeedback> addUserFeedbackRecord(UserFeedback userFeedBack) throws Exception;

}