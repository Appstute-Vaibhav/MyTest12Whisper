/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao;

// java imports
import java.util.List;





// application imports
import com.whispers.beans.FeedbackQuestionOption;

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on Feedback Question master
 */
public interface FeedbackQuestionOptionDAO {
	/**
	 * Inserts records in FeedbackQuestionOption table.
	 * @param List<FeedbackQuestionOption>	The list of FeedbackQuestionOption 
	 * @param FeedbackQuestionId	The FeedbackQuestionId 
	 *
	 * @return 	Returns String
	 */
	public String addFeedbackQuestionOptionRecord( List<FeedbackQuestionOption> feedbackQuestionOptionList, Integer feedbackQuestionId ) throws Exception;

	/**
	 * Updates records in FeedbackQuestionOption table.
	 * @param List<FeedbackQuestionOption>	The list of FeedbackQuestionOption 
	 *
	 * @return 	Returns String
	 */
	public String updateFeedbackQuestionOptionRecord( List<FeedbackQuestionOption> feedbackQuestionOptionList) throws Exception;

	/**
	 * Fetch one or more records from FeedbackQuestionOption table for specified criteria
	 *
	 * @param FeedbackQuestionOption	The FeedbackQuestionOption entity
	 *
	 * @return 	Returns the list of FeedbackQuestionOption	{@link List}
	 */
	public List<FeedbackQuestionOption> fetchFeedbackQuestionOptionRecords( FeedbackQuestionOption feedbackQuestionOption ) throws Exception;
		
	public Integer deleteFeedbackQuestionOptionRecords( final List<FeedbackQuestionOption> deleteFeedbackQuestionOptionRecords ) throws Exception;

}