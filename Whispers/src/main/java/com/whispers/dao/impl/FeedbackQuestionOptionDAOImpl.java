/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

// java imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
// spring framework imports
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

// application imports
import com.whispers.beans.FeedbackQuestionOption;
import com.whispers.dao.FeedbackQuestionOptionDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * This class provides implementation of CRUD operations to maintain Feedback Question master table
 */

@Repository
public class FeedbackQuestionOptionDAOImpl extends TransactionService implements FeedbackQuestionOptionDAO  {

	/**
	 * Inserts a record in FeedbackQuestionOption table.
	* @param List<FeedbackQuestionOption>	The list of FeedbackQuestionOption 
	 *
	 * @return 	Returns String
	 */
	@Override
	public String addFeedbackQuestionOptionRecord(final List<FeedbackQuestionOption> feedbackQuestionOptionList, final Integer feedbackQuestionId) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addFeedbackQuestionOptionRecord()...");

		String result = "Failure";

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + FeedbackQuestionOptionMasterSQL.INSERT_SQL);

			getJdbcTemplate().batchUpdate(FeedbackQuestionOptionMasterSQL.INSERT_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					FeedbackQuestionOption feedbackQuestionOption = feedbackQuestionOptionList.get(index);
					
					pstmt.setInt(1, feedbackQuestionOption.getId());
					pstmt.setInt(2, feedbackQuestionId);
					pstmt.setString(3, feedbackQuestionOption.getOptionLabel());
					pstmt.setString(4, feedbackQuestionOption.getOptionValue());
				}

				@Override
				public int getBatchSize() {

					return feedbackQuestionOptionList.size();
				}
			});

			result = "success";
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addFeedbackQuestionOptionRecord(): Record added successfully ");

		return result;

	} //addFeedbackQuestionOptionRecord() method ends


	/**
	 * Updates record in FeedbackQuestionOption table.
	* @param List<FeedbackQuestionOption>	The list of FeedbackQuestionOption 
	 *
	 * @return 	Returns String
	 */
	@Override
	public String updateFeedbackQuestionOptionRecord(final List<FeedbackQuestionOption> feedbackQuestionOptionList) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateFeedbackQuestionOptionRecord()...");
		String result = "Failure";
		
			Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + FeedbackQuestionOptionMasterSQL.UPDATE_SQL);

			getJdbcTemplate().batchUpdate(FeedbackQuestionOptionMasterSQL.UPDATE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					FeedbackQuestionOption feedbackQuestionOption = feedbackQuestionOptionList.get(index);
					pstmt.setInt(1, feedbackQuestionOption.getId());
					pstmt.setInt(2, feedbackQuestionOption.getQuestionId());
					pstmt.setString(3, feedbackQuestionOption.getOptionLabel());
					pstmt.setString(4, feedbackQuestionOption.getOptionValue());
					pstmt.setInt(5, feedbackQuestionOption.getId());
					pstmt.setInt(6, feedbackQuestionOption.getQuestionId());
					pstmt.setString(7, feedbackQuestionOption.getOptionLabel());
					pstmt.setString(8, feedbackQuestionOption.getOptionValue());
				}

				@Override
				public int getBatchSize() {

					return feedbackQuestionOptionList.size();
				}
			});

			result = "success";
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateFeedbackQuestionOptionRecord(): Record updated suucessfully");

		return result;

	} //updateFeedbackQuestionOptionRecord() method ends 

	@Override
	public Integer deleteFeedbackQuestionOptionRecords( final List<FeedbackQuestionOption> deleteFeedbackQuestionOptionRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteFeedbackQuestionOptionRecords()...");

			Integer rowsAffectedCount = -1;

			Log.logMessage("INFO", this.getClass().getName(), "DELETE query = " + FeedbackQuestionOptionMasterSQL.DELETE_SQL);

			getJdbcTemplate().batchUpdate(FeedbackQuestionOptionMasterSQL.DELETE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					FeedbackQuestionOption feedbackQuestionOption = deleteFeedbackQuestionOptionRecords.get(index);
					pstmt.setInt(1, feedbackQuestionOption.getQuestionId());
				}

				@Override
				public int getBatchSize() {

					return deleteFeedbackQuestionOptionRecords.size();
				}
			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteFeedbackQuestionRecord(): Record deleted successfully: Rows affected = " + rowsAffectedCount);

		return rowsAffectedCount;

	} //deleteFeedbackQuestionRecord() method ends
	
	/**
	 * Fetches all records for selected criteria from FeedbackQuestionOption table.
	 * @param feedbackQuestionOption	The FeedbackQuestionOption entity
	 *
	 * @return 	 Returns the list of FeedbackQuestionOption 	 {@link List<FeedbackQuestionOption> }
	 */
	@Override
	public List<FeedbackQuestionOption> fetchFeedbackQuestionOptionRecords( FeedbackQuestionOption feedbackQuestionOption ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchFeedbackQuestionOptionRecords()...");

		List<FeedbackQuestionOption> feedbackQuestionOptionList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + FeedbackQuestionOptionMasterSQL.SELECTALLCRITERIA_SQL);
			
			feedbackQuestionOptionList = getJdbcTemplate().query( FeedbackQuestionOptionMasterSQL.SELECTALLCRITERIA_SQL, new Object[] {
				feedbackQuestionOption.getQuestionId(), feedbackQuestionOption.getQuestionId(),
				}, new ResultSetExtractor<List<FeedbackQuestionOption>>() {

				List<FeedbackQuestionOption> feedbackQuestionOptionTempList = new ArrayList<FeedbackQuestionOption>();

				@Override
				public List<FeedbackQuestionOption> extractData(ResultSet rs) throws SQLException, DataAccessException {

					FeedbackQuestionOption feedbackQuestionOptionTemp = null;

					while(rs.next()) {
						feedbackQuestionOptionTemp = new FeedbackQuestionOption();

						feedbackQuestionOptionTemp.setId(rs.getInt("Id"));						
						feedbackQuestionOptionTemp.setQuestionId(rs.getInt("QuestionId"));
						feedbackQuestionOptionTemp.setOptionLabel(rs.getString("OptionLabel"));
						feedbackQuestionOptionTemp.setOptionValue(rs.getString("OptionValue"));

						feedbackQuestionOptionTempList.add(feedbackQuestionOptionTemp);
					}

					return feedbackQuestionOptionTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionOptionRecords(): All records fetched successfully: " +feedbackQuestionOptionList.toString());

		return feedbackQuestionOptionList;

	} //fetchFeedbackQuestionOptionRecords() method ends


}