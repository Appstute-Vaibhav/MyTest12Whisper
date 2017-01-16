/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao.impl;

// java imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
// spring framework imports
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;







// application imports
import com.whispers.beans.User12Whispers;
import com.whispers.dao.User12WhispersDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * This class provides implementation of CRUD operations to maintain User 12Whispers master table
 */

@Repository
public class User12WhispersDAOImpl extends TransactionService implements User12WhispersDAO {

	@Resource
    HttpSession session; 
	
	/**
	 * Inserts a record in User12Whispers table.
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the User12Whispers 	 {@link User12Whispers }
	 */
	@Override
	public User12Whispers addUser12WhispersRecord( User12Whispers user12Whispers ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUser12WhispersRecord()...");

		Integer id= -1;

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + User12WhispersMasterSQL.INSERT_SQL);

			user12Whispers.setStatus(1);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user12Whispers);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(User12WhispersMasterSQL.INSERT_SQL, paramSource, generatedKey);

			id = generatedKey.getKey().intValue();
			user12Whispers.setId( id);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUser12WhispersRecord(): Record added successfully: " + user12Whispers.toString());

		return user12Whispers;

	} //addUser12WhispersRecord() method ends


	/**
	 * Updates record in User12Whispers table.
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the User12Whispers 	 {@link User12Whispers }
	 */
	@Override
	public User12Whispers updateUser12WhispersRecord( User12Whispers user12Whispers ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateUser12WhispersRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + User12WhispersMasterSQL.UPDATE_SQL);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user12Whispers);
			getNamedParameterJdbcTemplate().update(User12WhispersMasterSQL.UPDATE_SQL, paramSource);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateUser12WhispersRecord(): Record update suucessfully: " + user12Whispers.toString());

		return user12Whispers;

	} //updateUser12WhispersRecord() method ends


	/**
	 * Delete record(s) in User12Whispers table. In case of softdelete, Status is set to 0 
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the deleted record count 	 {@link Integer}
	 */
	@Override
	public Integer deleteUser12WhispersRecord( final List<User12Whispers> deleteUser12WhispersRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteUser12WhispersRecord()...");

			Integer rowsAffectedCount = -1;

			Log.logMessage("INFO", this.getClass().getName(), "DELETE query = " + User12WhispersMasterSQL.DELETE_SQL);

			getJdbcTemplate().batchUpdate(User12WhispersMasterSQL.DELETE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					User12Whispers user12Whispers = deleteUser12WhispersRecords.get(index);
					pstmt.setInt(1, user12Whispers.getId());
				}

				@Override
				public int getBatchSize() {

					return deleteUser12WhispersRecords.size();
				}
			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteUser12WhispersRecord(): Record deleted successfully: Rows affected = " + rowsAffectedCount);

		return rowsAffectedCount;

	} //deleteUser12WhispersRecord() method ends


	/**
	 * Fetches a single record from User12Whispers table.
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the User12Whispers 	 {@link User12Whispers }
	 */
	@Override
	public User12Whispers fetchUser12WhispersRecord(User12Whispers user12Whispers) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUser12WhispersRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + User12WhispersMasterSQL.SELECT_SQL);

			user12Whispers = getJdbcTemplate().query( User12WhispersMasterSQL.SELECT_SQL, new Object[] { user12Whispers.getId()}, new ResultSetExtractor<User12Whispers>() {

				User12Whispers user12WhispersTemp = new User12Whispers();

				@Override
				public User12Whispers extractData(ResultSet rs) throws SQLException, DataAccessException {

					while(rs.next()) {
						user12WhispersTemp.setId(rs.getInt("Id"));
						user12WhispersTemp.setSceneId(rs.getInt("SceneId"));
						user12WhispersTemp.setDateWhispersSubmitted(rs.getTimestamp("DateWhispersSubmitted"));
						user12WhispersTemp.setSubmittedById(rs.getInt("SubmittedById"));
						user12WhispersTemp.setWhispers(rs.getString("Whispers"));
						user12WhispersTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						user12WhispersTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						user12WhispersTemp.setCreatedBy(rs.getInt("CreatedBy"));
						user12WhispersTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						user12WhispersTemp.setStatus(rs.getInt("Status"));
					}

					return user12WhispersTemp;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUser12WhispersRecord(): Record fetched successfully: " + user12Whispers.toString());

		return user12Whispers;

	} //fetchUser12WhispersRecord() method ends


	/**
	 * Fetches all records from User12Whispers table.
	 *
	 * @param User12Whispers
	 *
	 * @return 	 Returns the list of User12Whispers 	 {@link List of User12Whispers }
	 */
	@Override
	public List<User12Whispers> fetchAllUser12WhispersRecord(final User12Whispers user12Whispers) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllUser12WhispersRecord()...");

		List<User12Whispers> user12WhispersList = null;
		String SELECT_SQL = User12WhispersMasterSQL.SELECTALL_SQL;
		Object[] params = null;
			if((user12Whispers.getRoleId() != null) && (user12Whispers.getRoleId() == 2)){ //playwriter
					
				SELECT_SQL = User12WhispersMasterSQL.SELECT_BY_OWNER_SQL;
				params = new Object[] { user12Whispers.getId() };
					
			}else{ //admin and dramaturg				
				SELECT_SQL = User12WhispersMasterSQL.SELECTALL_SQL;
				params = new Object[] { user12Whispers.getId(),user12Whispers.getId(),user12Whispers.getRoleId()};
			}
			
			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + SELECT_SQL);

			user12WhispersList = getJdbcTemplate().query( SELECT_SQL, params , new ResultSetExtractor<List<User12Whispers>>() {

				List<User12Whispers> user12WhispersTempList = new ArrayList<User12Whispers>();

				@Override
				public List<User12Whispers> extractData(ResultSet rs) throws SQLException, DataAccessException {

					User12Whispers user12WhispersTemp = null;

					while(rs.next()) {
						user12WhispersTemp = new User12Whispers();

						user12WhispersTemp.setId(rs.getInt("Id"));
						user12WhispersTemp.setSceneId(rs.getInt("SceneId"));
						user12WhispersTemp.setSceneTitle(rs.getString("SceneTitle"));
						user12WhispersTemp.setDateWhispersSubmitted(rs.getTimestamp("DateWhispersSubmitted"));
						user12WhispersTemp.setSubmittedById(rs.getInt("SubmittedById"));
						user12WhispersTemp.setWhispers(rs.getString("Whispers"));
						user12WhispersTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						user12WhispersTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						if((user12Whispers.getRoleId() != 3) && (user12Whispers.getRoleId() == 1)){ //playwriter
							user12WhispersTemp.setCreatedBy(rs.getInt("WeekId"));
						}					
						user12WhispersTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						user12WhispersTemp.setStatus(rs.getInt("Status"));
						user12WhispersTemp.setFirstName(rs.getString("FirstName"));
						user12WhispersTemp.setLastName(rs.getString("LastName"));
						user12WhispersTemp.setEmail(rs.getString("Email"));
						
						if((user12Whispers.getRoleId() != null) && (user12Whispers.getRoleId() == 2)){ //playwriter
							
						}else {
							user12WhispersTemp.setRating(rs.getInt("rating"));
						}

						user12WhispersTempList.add(user12WhispersTemp);
					}

					return user12WhispersTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllUser12WhispersRecord(): All records fetched successfully: " + user12WhispersList.toString());

		return user12WhispersList;

	} //fetchAllUser12WhispersRecord() method ends


	/**
	 * Fetches all records for selected criteria from User12Whispers table.
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the list of User12Whispers 	 {@link List<User12Whispers> }
	 */
	@Override
	public List<User12Whispers> fetchUser12WhispersRecords( User12Whispers user12Whispers ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUser12WhispersRecords()...");

		List<User12Whispers> user12WhispersList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + User12WhispersMasterSQL.SELECTALLCRITERIA_SQL);

			user12WhispersList = getJdbcTemplate().query( User12WhispersMasterSQL.SELECTALLCRITERIA_SQL, new Object[] {
				user12Whispers.getId(), user12Whispers.getId(), user12Whispers.getSceneId(), user12Whispers.getSceneId(), user12Whispers.getDateWhispersSubmitted(), user12Whispers.getDateWhispersSubmitted(), user12Whispers.getSubmittedById(), user12Whispers.getSubmittedById(), user12Whispers.getWhispers(), user12Whispers.getWhispers(), user12Whispers.getModifiedOn(), user12Whispers.getModifiedOn(), user12Whispers.getModifiedBy(), user12Whispers.getModifiedBy(), user12Whispers.getCreatedBy(), user12Whispers.getCreatedBy()
				}, new ResultSetExtractor<List<User12Whispers>>() {

				List<User12Whispers> user12WhispersTempList = new ArrayList<User12Whispers>();

				@Override
				public List<User12Whispers> extractData(ResultSet rs) throws SQLException, DataAccessException {

					User12Whispers user12WhispersTemp = null;

					while(rs.next()) {
						user12WhispersTemp = new User12Whispers();

						user12WhispersTemp.setId(rs.getInt("Id"));
						user12WhispersTemp.setSceneId(rs.getInt("SceneId"));
						user12WhispersTemp.setDateWhispersSubmitted(rs.getTimestamp("DateWhispersSubmitted"));
						user12WhispersTemp.setSubmittedById(rs.getInt("SubmittedById"));
						user12WhispersTemp.setWhispers(rs.getString("Whispers"));
						user12WhispersTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						user12WhispersTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						user12WhispersTemp.setCreatedBy(rs.getInt("CreatedBy"));
						user12WhispersTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						user12WhispersTemp.setStatus(rs.getInt("Status"));

						user12WhispersTempList.add(user12WhispersTemp);
					}

					return user12WhispersTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUser12WhispersRecords(): All records fetched successfully: " +user12WhispersList.toString());

		return user12WhispersList;

	} //fetchUser12WhispersRecords() method ends


}