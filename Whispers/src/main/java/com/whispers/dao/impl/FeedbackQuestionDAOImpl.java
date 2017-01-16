/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

// java imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





// spring framework imports
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;





// application imports
import com.whispers.beans.FeedbackQuestion;
import com.whispers.beans.FeedbackQuestionOption;
import com.whispers.beans.UserFeedback;
import com.whispers.dao.FeedbackQuestionDAO;
import com.whispers.dao.FeedbackQuestionOptionDAO;
import com.whispers.dao.impl.FeedbackQuestionMasterSQL;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * This class provides implementation of CRUD operations to maintain Feedback Question master table
 */

@Repository
public class FeedbackQuestionDAOImpl extends TransactionService implements FeedbackQuestionDAO {

	/**
	 * The DAO class instance
	 */
	@Autowired
	private FeedbackQuestionOptionDAO feedbackQuestionOptionDAO;
		
	/**
	 * Inserts a record in FeedbackQuestion table.
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the FeedbackQuestion 	 {@link FeedbackQuestion }
	 */
	@Override
	public List<FeedbackQuestion> addFeedbackQuestionRecord(List<FeedbackQuestion> feedbackQuestionList ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addFeedbackQuestionRecord()...");

			Integer id = null;

			Integer pkId = null;
			
			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + FeedbackQuestionMasterSQL.INSERT_SQL);

			for(FeedbackQuestion feedbackQuestion : feedbackQuestionList) {
				feedbackQuestion.setStatus(1);
				feedbackQuestion.setParentQuestionId(pkId);
				
				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(feedbackQuestion);
				KeyHolder generatedKey = new GeneratedKeyHolder();
				getNamedParameterJdbcTemplate().update(FeedbackQuestionMasterSQL.INSERT_SQL, paramSource, generatedKey);
				
				if(id != null){										
				}else{
					pkId = generatedKey.getKey().intValue();
				}
				id = generatedKey.getKey().intValue();
				
				List <FeedbackQuestionOption> feedbackQuestionOptionList = null;
				
				feedbackQuestionOptionList = feedbackQuestion.getFeedbackQuestionOptions();
				
				feedbackQuestionOptionDAO.addFeedbackQuestionOptionRecord(feedbackQuestionOptionList,id);
			}

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addFeedbackQuestionRecord(): Record added successfully: " + feedbackQuestionList.toString());

		return feedbackQuestionList;

	} //addFeedbackQuestionRecord() method ends
		
	/**
	 * Inserts a record in FeedbackQuestion table.
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the FeedbackQuestion 	 {@link FeedbackQuestion }
	 */
	@Override
	public List<UserFeedback> addUserFeedback(List<UserFeedback> feedbackQuestionList ) throws Exception {

			Log.logMessage("INFO", this.getClass().getName(), "Entering addUserFeedback()...");

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + FeedbackQuestionMasterSQL.INSERT2_SQL);

			for(UserFeedback userFeedback : feedbackQuestionList) {
											
				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(userFeedback);
				
				getNamedParameterJdbcTemplate().update(FeedbackQuestionMasterSQL.INSERT2_SQL, paramSource);													
			}

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addFeedbackQuestionRecord(): Record added successfully: " + feedbackQuestionList.toString());

		return feedbackQuestionList;

	} //addFeedbackQuestionRecord() method ends
	
	/**
	 * Updates record in FeedbackQuestion table.
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the FeedbackQuestion 	 {@link FeedbackQuestion }
	 */
	@Override
	public FeedbackQuestion updateFeedbackQuestionRecord(List<FeedbackQuestion> feedbackQuestionList ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateFeedbackQuestionRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + FeedbackQuestionMasterSQL.UPDATE_SQL);

			Integer parent = null;
			Integer id  =null;
			FeedbackQuestion  feedbackQuestion1 = new FeedbackQuestion();
			for(FeedbackQuestion feedbackQuestion : feedbackQuestionList) {
				//feedbackQuestion.setStatus(1);
				//feedbackQuestion.setParentQuestionId(pkId);
							
				if(feedbackQuestion.getId() != null){
					if(feedbackQuestion.getParentQuestionId() == null){
						parent = feedbackQuestion.getId();
					}
					SqlParameterSource paramSource = new BeanPropertySqlParameterSource(feedbackQuestion);
					getNamedParameterJdbcTemplate().update(FeedbackQuestionMasterSQL.UPDATE_SQL, paramSource);
					
					List<FeedbackQuestionOption> feedbackQuestionOptionList = null; 
					
					feedbackQuestionOptionList = feedbackQuestion.getFeedbackQuestionOptions();
					
					for(FeedbackQuestionOption feedbackQuestionOption : feedbackQuestionOptionList) {
						feedbackQuestionOption.setQuestionId(feedbackQuestion.getId());
					}
					// Call dao to delete option record
					feedbackQuestionOptionDAO.deleteFeedbackQuestionOptionRecords(feedbackQuestionOptionList);
					
					// Call dao to update option record
					feedbackQuestionOptionDAO.updateFeedbackQuestionOptionRecord(feedbackQuestionOptionList);
				}else{
					feedbackQuestion.setStatus(1);
					feedbackQuestion.setParentQuestionId(parent);
					SqlParameterSource paramSource = new BeanPropertySqlParameterSource(feedbackQuestion);
					KeyHolder generatedKey = new GeneratedKeyHolder();
					getNamedParameterJdbcTemplate().update(FeedbackQuestionMasterSQL.INSERT_SQL, paramSource, generatedKey);
					
					id = generatedKey.getKey().intValue();
					
					List <FeedbackQuestionOption> feedbackQuestionOptionList = null;
					
					feedbackQuestionOptionList = feedbackQuestion.getFeedbackQuestionOptions();
					
					feedbackQuestionOptionDAO.addFeedbackQuestionOptionRecord(feedbackQuestionOptionList,id);
				}
											
			}			
			
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateFeedbackQuestionRecord(): Record update suucessfully: " + feedbackQuestion1.toString());

		return feedbackQuestion1;

	} //updateFeedbackQuestionRecord() method ends


	/**
	 * Delete record(s) in FeedbackQuestion table. In case of softdelete, Status is set to 0 
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the deleted record count 	 {@link Integer}
	 */
	@Override
	public Integer deleteFeedbackQuestionRecord( final List<FeedbackQuestion> deleteFeedbackQuestionRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteFeedbackQuestionRecord()...");

			Integer rowsAffectedCount = -1;
			
			Log.logMessage("INFO", this.getClass().getName(), "DELETE query = " + FeedbackQuestionMasterSQL.DELETE_SQL);

			int result[] = getJdbcTemplate().batchUpdate(FeedbackQuestionMasterSQL.DELETE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					FeedbackQuestion feedbackQuestion = deleteFeedbackQuestionRecords.get(index);
					pstmt.setInt(1, feedbackQuestion.getId());
					pstmt.setInt(2, feedbackQuestion.getId());
				}

				@Override
				public int getBatchSize() {
					return deleteFeedbackQuestionRecords.size();
				}
			});
			
			rowsAffectedCount = result[0];
					
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteFeedbackQuestionRecord(): Record deleted successfully: Rows affected = " + rowsAffectedCount);

		return rowsAffectedCount;

	} //deleteFeedbackQuestionRecord() method ends


	/**
	 * Fetches a single record from FeedbackQuestion table.
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the FeedbackQuestion 	 {@link FeedbackQuestion }
	 */
	@Override
	public List<FeedbackQuestion> fetchFeedbackQuestionRecord(FeedbackQuestion feedbackQuestion) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchFeedbackQuestionRecord()...");

		List<FeedbackQuestion> feedbackQuestionList = null;
		
		Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + FeedbackQuestionMasterSQL.SELECT_SQL);
			
		feedbackQuestionList = getJdbcTemplate().query( FeedbackQuestionMasterSQL.SELECT_SQL, new Object[] { feedbackQuestion.getId(),feedbackQuestion.getParentQuestionId()}, new ResultSetExtractor<List<FeedbackQuestion>>() {
			
			List<FeedbackQuestion> feedbackQuestionTempList = new ArrayList<FeedbackQuestion>();
				
			@Override
			public List<FeedbackQuestion> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				FeedbackQuestion feedbackQuestionTemp = null;
					
				FeedbackQuestionOption feedbackQuestionOptionTemp = null;
					
				while(rs.next()) {
					feedbackQuestionTemp = new FeedbackQuestion();
				
					feedbackQuestionTemp.setId(rs.getInt("Id"));
					feedbackQuestionTemp.setParentQuestionId(rs.getInt("ParentQuestionId"));
					feedbackQuestionTemp.setQuestion(rs.getString("Question"));
					feedbackQuestionTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
					feedbackQuestionTemp.setModifiedBy(rs.getInt("ModifiedBy"));
					feedbackQuestionTemp.setCreatedBy(rs.getInt("CreatedBy"));
					feedbackQuestionTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
					feedbackQuestionTemp.setStatus(rs.getInt("Status"));
									
					feedbackQuestionOptionTemp = new FeedbackQuestionOption();					
					
					feedbackQuestionOptionTemp.setQuestionId(rs.getInt("Id"));
					
					try{
						feedbackQuestionTemp.setFeedbackQuestionOptions(feedbackQuestionOptionDAO.fetchFeedbackQuestionOptionRecords(feedbackQuestionOptionTemp));						
					}catch (Exception exception) { 
						Log.logMessage("ERROR", this.getClass().getName(), "Error occured while fetching all option records from table. Error: "+ exception.getMessage());
					}
					
					feedbackQuestionTempList.add(feedbackQuestionTemp);						
				}

					return feedbackQuestionTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionRecord(): Record fetched successfully: " + feedbackQuestion.toString());

		return feedbackQuestionList;

	} //fetchFeedbackQuestionRecord() method ends


	/**
	 * Fetches all records from FeedbackQuestion table.
	 * @param none
	 *
	 * @return 	 Returns the list of FeedbackQuestion 	 {@link List of FeedbackQuestion }
	 */
	@Override
	public List<FeedbackQuestion> fetchAllFeedbackQuestionRecord() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllFeedbackQuestionRecord()...");

		List<FeedbackQuestion> feedbackQuestionList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + FeedbackQuestionMasterSQL.SELECTALL_SQL);

			feedbackQuestionList = getJdbcTemplate().query( FeedbackQuestionMasterSQL.SELECTALL_SQL, new Object[] { }, new ResultSetExtractor<List<FeedbackQuestion>>() {

				List<FeedbackQuestion> feedbackQuestionTempList = new ArrayList<FeedbackQuestion>();

				@Override
				public List<FeedbackQuestion> extractData(ResultSet rs) throws SQLException, DataAccessException {

					FeedbackQuestion feedbackQuestionTemp = null;
					FeedbackQuestionOption feedbackQuestionOptionTemp = null;

					while(rs.next()) {
						feedbackQuestionTemp = new FeedbackQuestion();

						feedbackQuestionTemp.setId(rs.getInt("Id"));
						feedbackQuestionTemp.setParentQuestionId(rs.getInt("ParentQuestionId"));
						feedbackQuestionTemp.setQuestion(rs.getString("Question"));
						feedbackQuestionTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						feedbackQuestionTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						feedbackQuestionTemp.setCreatedBy(rs.getInt("CreatedBy"));
						feedbackQuestionTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						feedbackQuestionTemp.setStatus(rs.getInt("Status"));
						
						feedbackQuestionOptionTemp = new FeedbackQuestionOption();
						feedbackQuestionOptionTemp.setQuestionId(rs.getInt("Id"));
						try{
							feedbackQuestionTemp.setFeedbackQuestionOptions(feedbackQuestionOptionDAO.fetchFeedbackQuestionOptionRecords(feedbackQuestionOptionTemp));
						}catch (Exception exception) { 
							Log.logMessage("ERROR", this.getClass().getName(), "Error occured while fetching all option records from table. Error: "+ exception.getMessage());
						}

						feedbackQuestionTempList.add(feedbackQuestionTemp);
					}

					return feedbackQuestionTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllFeedbackQuestionRecord(): All records fetched successfully: " + feedbackQuestionList.toString());

		return feedbackQuestionList;

	} //fetchAllFeedbackQuestionRecord() method ends


	/**
	 * Fetches all records for selected criteria from FeedbackQuestion table.
	 * @param feedbackQuestion	The FeedbackQuestion entity
	 *
	 * @return 	 Returns the list of FeedbackQuestion 	 {@link List<FeedbackQuestion> }
	 */
	@Override
	public List<FeedbackQuestion> fetchFeedbackQuestionRecords( FeedbackQuestion feedbackQuestion ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchFeedbackQuestionRecords()...");

		List<FeedbackQuestion> feedbackQuestionList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + FeedbackQuestionMasterSQL.SELECTALLCRITERIA_SQL);

			feedbackQuestionList = getJdbcTemplate().query( FeedbackQuestionMasterSQL.SELECTALLCRITERIA_SQL, new Object[] {
				feedbackQuestion.getId(), feedbackQuestion.getId(), feedbackQuestion.getQuestion(), feedbackQuestion.getQuestion(), feedbackQuestion.getModifiedOn(), feedbackQuestion.getModifiedOn(), feedbackQuestion.getModifiedBy(), feedbackQuestion.getModifiedBy(), feedbackQuestion.getCreatedBy(), feedbackQuestion.getCreatedBy()
				}, new ResultSetExtractor<List<FeedbackQuestion>>() {

				List<FeedbackQuestion> feedbackQuestionTempList = new ArrayList<FeedbackQuestion>();

				@Override
				public List<FeedbackQuestion> extractData(ResultSet rs) throws SQLException, DataAccessException {

					FeedbackQuestion feedbackQuestionTemp = null;
					FeedbackQuestionOption feedbackQuestionOptionTemp = null;

					while(rs.next()) {
						feedbackQuestionTemp = new FeedbackQuestion();

						feedbackQuestionTemp.setId(rs.getInt("Id"));
						feedbackQuestionTemp.setQuestion(rs.getString("Question"));
						feedbackQuestionTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						feedbackQuestionTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						feedbackQuestionTemp.setCreatedBy(rs.getInt("CreatedBy"));
						feedbackQuestionTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						feedbackQuestionTemp.setStatus(rs.getInt("Status"));

						feedbackQuestionOptionTemp = new FeedbackQuestionOption();
						feedbackQuestionOptionTemp.setQuestionId(rs.getInt("Id"));
						
						try{
							feedbackQuestionTemp.setFeedbackQuestionOptions(feedbackQuestionOptionDAO.fetchFeedbackQuestionOptionRecords(feedbackQuestionOptionTemp));
						}catch (Exception exception) { 
							Log.logMessage("ERROR", this.getClass().getName(), "Error occured while fetching all option records from table. Error: "+ exception.getMessage());
						}

						feedbackQuestionTempList.add(feedbackQuestionTemp);
					}

					return feedbackQuestionTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchFeedbackQuestionRecords(): All records fetched successfully: " +feedbackQuestionList.toString());

		return feedbackQuestionList;

	} //fetchFeedbackQuestionRecords() method ends

	@Override
	public List<UserFeedback> addUserFeedbackRecord( UserFeedback userFeedback ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUserFeedbackRecord()...");

		Integer id= -1;
		
		List<UserFeedback> userFeedbackTempList = new ArrayList<UserFeedback>();
		
		UserFeedback userFeedbk = new UserFeedback();
			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + FeedbackQuestionMasterSQL.INSERT1_SQL);
			
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(userFeedback);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(FeedbackQuestionMasterSQL.INSERT1_SQL, paramSource, generatedKey);
		
			id = generatedKey.getKey().intValue();
			if((id != null) && (id > 0)){
				userFeedbk.setId(id);				
			}
			
			userFeedbackTempList.add(userFeedbk);
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addFeedbackQuestionRecord(): Record added successfully: " + userFeedback.toString());

		return userFeedbackTempList;

	} //addFeedbackQuestionRecord() method ends

}