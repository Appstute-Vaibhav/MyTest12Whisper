/**
 * Created on 15 Jan, 2015
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
import com.whispers.beans.User;
import com.whispers.beans.User12Whispers;
import com.whispers.dao.UserDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * This class provides implementation of CRUD operations to maintain User master table
 */

@Repository
public class UserDAOImpl extends TransactionService implements UserDAO {

	@Resource
	HttpSession session;
	
	/**
	 * Inserts a record in User table.
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	@Override
	public User addUserRecord( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUserRecord()...");

		Integer id= -1;

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + UserMasterSQL.INSERT_SQL);

			user.setStatus(1);
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(UserMasterSQL.INSERT_SQL, paramSource, generatedKey);
			
			id = generatedKey.getKey().intValue();
			user.setId(id);

			getNamedParameterJdbcTemplate().update(UserMasterSQL.INSERT1_SQL, paramSource);			
			
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Record added successfully: " + user.toString());

		return user;

	} //addUserRecord() method ends

	/**
	 * Inserts a record in User table.
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	@Override
	public User addTempUserRecord( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addUserRecord()...");

			Integer id= -1;

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + UserMasterSQL.INSERTTEMP_SQL);
			
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(UserMasterSQL.INSERTTEMP_SQL, paramSource, generatedKey);
			
			id = generatedKey.getKey().intValue();
			user.setStatus(id);					

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addUserRecord(): Record added successfully: " + user.toString());

		return user;

	} //addUserRecord() method ends

	
	
	/**
	 * Updates record in User table.
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	@Override
	public User updateUserRecord( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateUserRecord()...");

			if(user.getFirstName() != null || user.getMobileNumber() != null || user.getDateOfBirth() != null){
				Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + UserMasterSQL.UPDATE_SQL);
				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
				getNamedParameterJdbcTemplate().update(UserMasterSQL.UPDATE_SQL, paramSource);
			}else{
				Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + UserMasterSQL.UPDATE1_SQL);
				user.setStatus(1);
				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);				
				getNamedParameterJdbcTemplate().update(UserMasterSQL.UPDATE1_SQL, paramSource);				
				getNamedParameterJdbcTemplate().update(UserMasterSQL.INSERT1_SQL, paramSource);
			}
			
		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateUserRecord(): Record update suucessfully: " + user.toString());

		return user;

	} //updateUserRecord() method ends

	
	/**
	 * Override record in User table.
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	@Override
	public Integer overrideUserRecord( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering overrideUserRecord()...");

		Log.logMessage("INFO", this.getClass().getName(), "OVERRIDE query = " + UserMasterSQL.SELECTROLE_SQL);

			//Integer roleId = null;
			/*SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
			getNamedParameterJdbcTemplate().update(UserMasterSQL.UPDATE_SQL, paramSource);*/			
			user = getJdbcTemplate().query( UserMasterSQL.SELECTROLE_SQL, new Object[] { user.getAddress1()}, new ResultSetExtractor<User>() {

				User userTemp = new User();

				@Override
				public User extractData(ResultSet rs) throws SQLException, DataAccessException {
					//Integer roleId = null;					
					while(rs.next()) {
						userTemp.setRoleId(rs.getInt("RoleId"));
					}
					return userTemp;
				}				
			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting overrideUserRecord(): Record update suucessfully: " + user.toString());

		return user.getRoleId();

	} //updateUserRecord() method ends

	
	/**
	 * Delete record(s) in User table. In case of softdelete, Status is set to 0 
	 * @param user	The User entity
	 *
	 * @return 	 Returns the deleted record count 	 {@link Integer}
	 */
	@Override
	public Integer deleteUserRecord( final List<User> deleteUserRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteUserRecord()...");

		Integer rowsAffectedCount = -1;
		int[] batchSize;

			Log.logMessage("INFO", this.getClass().getName(), "DELETE query = " + UserMasterSQL.DELETE_SQL);

			batchSize = getJdbcTemplate().batchUpdate(UserMasterSQL.DELETE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					User user = deleteUserRecords.get(index);					
					pstmt.setInt(1, user.getStatus());
					pstmt.setInt(2, user.getId());
					pstmt.setInt(3, user.getRoleId());
				}

				@Override
				public int getBatchSize() {

					return deleteUserRecords.size();
				}
			});

			rowsAffectedCount = batchSize.length;
			
		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteUserRecord(): Record deleted successfully: Rows affected = " + rowsAffectedCount);

		return rowsAffectedCount;

	} //deleteUserRecord() method ends


	/**
	 * Fetches a single record from User table.
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	@Override
	public User fetchUserRecord(User user) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + UserMasterSQL.SELECT_SQL);

			user = getJdbcTemplate().query( UserMasterSQL.SELECT_SQL, new Object[] { user.getId()}, new ResultSetExtractor<User>() {

				User userTemp = new User();

				@Override
				public User extractData(ResultSet rs) throws SQLException, DataAccessException {

					while(rs.next()) {
						userTemp.setId(rs.getInt("Id"));
						userTemp.setFirstName(rs.getString("FirstName"));
						userTemp.setLastName(rs.getString("LastName"));
						userTemp.setUserName(rs.getString("UserName"));
						userTemp.setEmail(rs.getString("Email"));
						userTemp.setDisplayName(rs.getString("DisplayName"));
						userTemp.setMobileNumber(rs.getString("MobileNumber"));
						userTemp.setPassword(rs.getString("Password"));
						userTemp.setRoleId(rs.getInt("RoleId"));
						userTemp.setDateOfBirth(rs.getTimestamp("DateOfBirth"));
						userTemp.setGender(rs.getString("Gender"));
						userTemp.setAddress1(rs.getString("Address1"));
						userTemp.setAddress2(rs.getString("Address2"));
						userTemp.setCountry(rs.getString("Country"));
						userTemp.setState(rs.getString("State"));
						userTemp.setPlace(rs.getString("Place"));
						userTemp.setZip(rs.getInt("Zip"));
						userTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						userTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						userTemp.setCreatedBy(rs.getInt("CreatedBy"));
						userTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						userTemp.setStatus(rs.getInt("Status"));
					}

					return userTemp;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Record fetched successfully: " + user.toString());

		return user;

	} //fetchUserRecord() method ends

	/**
	 * Fetches a single record from User table.
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	@Override
	public User getTemptUserRecord(User user) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering getTemptUserRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + UserMasterSQL.SELECTTEMP_SQL);

			user = getJdbcTemplate().query( UserMasterSQL.SELECTTEMP_SQL, new Object[] {user.getStatus(), user.getId()}, new ResultSetExtractor<User>() {

				User userTemp = new User();

				@Override
				public User extractData(ResultSet rs) throws SQLException, DataAccessException {

					while(rs.next()) {
						userTemp.setId(rs.getInt("UserId"));
						userTemp.setPassword(rs.getString("Password"));
						userTemp.setRoleId(rs.getInt("RoleId"));
					}

					return userTemp;
				}

			});

			final List<User> tempUserList= new ArrayList<User>();
			
			tempUserList.add(user);
			
			getJdbcTemplate().batchUpdate(UserMasterSQL.DELETE1_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					User user = tempUserList.get(index);										
					pstmt.setInt(1, user.getId());
					pstmt.setInt(2, user.getRoleId());
				}

				@Override
				public int getBatchSize() {
					return tempUserList.size();
				}
			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Record fetched successfully: " + user.toString());

		return user;

	} //fetchUserRecord() method ends

	
	/**
	 * Fetches a single record from User12Whispers table.
	 * @param user12Whispers	The User12Whispers entity
	 *
	 * @return 	 Returns the User12Whispers 	 {@link User12Whispers }
	 */
	@Override
	public User12Whispers get12Whispers(User12Whispers user) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering get12Whispers()...");
		
		Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + UserMasterSQL.SELECT1_SQL);

			user = getJdbcTemplate().query( UserMasterSQL.SELECT1_SQL, new Object[] { user.getSubmittedById(),user.getSubmittedById(),user.getDateWhispersSubmitted(),user.getDateWhispersSubmitted()}, new ResultSetExtractor<User12Whispers>() {
		
				User12Whispers user12Whispers = null;
				
				@Override
				public User12Whispers extractData(ResultSet rs) throws SQLException, DataAccessException {
				
					user12Whispers = new User12Whispers();					
					
					while(rs.next()) {						
						
						user12Whispers.setId(rs.getInt("Id"));
						user12Whispers.setSceneId(rs.getInt("SceneId"));
						user12Whispers.setDateWhispersSubmitted(rs.getTimestamp("dateWhispersSubmitted"));
						user12Whispers.setSubmittedById(rs.getInt("submittedById"));
						user12Whispers.setWhispers(rs.getString("whispers"));
					}

					return user12Whispers;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting get12Whispers(): Record fetched successfully: " + user.toString());
	
		return user;

	} //get12Whispers() method ends

	
	
	/**
	 * Fetches a single record from User table.
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	@Override
	public User checkRecord(User user) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + UserMasterSQL.SELECT_SQL);

			user = getJdbcTemplate().query( UserMasterSQL.SELECT_SQL, new Object[] { user.getId()}, new ResultSetExtractor<User>() {

				User userTemp = new User();

				@Override
				public User extractData(ResultSet rs) throws SQLException, DataAccessException {

					while(rs.next()) {
						userTemp.setId(rs.getInt("Id"));
						userTemp.setFirstName(rs.getString("FirstName"));
						userTemp.setLastName(rs.getString("LastName"));
						userTemp.setUserName(rs.getString("UserName"));
						userTemp.setEmail(rs.getString("Email"));
						userTemp.setDisplayName(rs.getString("DisplayName"));
						userTemp.setMobileNumber(rs.getString("MobileNumber"));
						userTemp.setPassword(rs.getString("Password"));
						userTemp.setRoleId(rs.getInt("RoleId"));
						userTemp.setDateOfBirth(rs.getTimestamp("DateOfBirth"));
						userTemp.setGender(rs.getString("Gender"));
						userTemp.setAddress1(rs.getString("Address1"));
						userTemp.setAddress2(rs.getString("Address2"));
						userTemp.setCountry(rs.getString("Country"));
						userTemp.setState(rs.getString("State"));
						userTemp.setPlace(rs.getString("Place"));
						userTemp.setZip(rs.getInt("Zip"));
						userTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						userTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						userTemp.setCreatedBy(rs.getInt("CreatedBy"));
						userTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						userTemp.setStatus(rs.getInt("Status"));
					}

					return userTemp;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecord(): Record fetched successfully: " + user.toString());

		return user;

	} //checkRecord() method ends
	

	/**
	 * Fetches all records from User table.
	 *
	 * @param User	The User entity
	 *
	 * @return 	 Returns the list of User 	 {@link List of User }
	 */
	@Override
	public List<User> fetchAllUserRecord(User user) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllUserRecord()...");

		List<User> userList = null;						

			if(user.getRoleId() != 4){
				Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + UserMasterSQL.SELECTALL_SQL);
				userList = getJdbcTemplate().query( UserMasterSQL.SELECTALL_SQL, new Object[] {}, new ResultSetExtractor<List<User>>() {

					List<User> userTempList = new ArrayList<User>();

					@Override
					public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {

						User userTemp = null;

						while(rs.next()) {
							userTemp = new User();

							userTemp.setId(rs.getInt("Id"));
							userTemp.setFirstName(rs.getString("FirstName"));
							userTemp.setLastName(rs.getString("LastName"));
							userTemp.setEmail(rs.getString("Email"));
							userTemp.setDisplayName(rs.getString("DisplayName"));
							userTemp.setMobileNumber(rs.getString("MobileNumber"));
							userTemp.setRoleId(rs.getInt("RoleId"));
							userTemp.setStatus(rs.getInt("Status"));

							userTempList.add(userTemp);
						}

						return userTempList;
					}

				});
			}else{
				Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + UserMasterSQL.SELECTENDUSER_SQL);
				userList = getJdbcTemplate().query( UserMasterSQL.SELECTENDUSER_SQL, new Object[] {user.getId()}, new ResultSetExtractor<List<User>>() {

					List<User> userTempList = new ArrayList<User>();

					@Override
					public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {

						User userTemp = null;

						while(rs.next()) {
							userTemp = new User();

							userTemp.setId(rs.getInt("Id"));
							userTemp.setFirstName(rs.getString("FirstName"));
							userTemp.setLastName(rs.getString("LastName"));
							userTemp.setEmail(rs.getString("Email"));
							userTemp.setDisplayName(rs.getString("DisplayName"));
							userTemp.setMobileNumber(rs.getString("MobileNumber"));
							userTemp.setRoleId(rs.getInt("RoleId"));
							userTemp.setStatus(rs.getInt("Status"));

							userTempList.add(userTemp);
						}

						return userTempList;
					}

				});
			}
						
			Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllUserRecord(): All records fetched successfully: " + userList.toString());

		return userList;

	} //fetchAllUserRecord() method ends


	/**
	 * Fetches all records for selected criteria from User table.
	 * @param user	The User entity
	 *
	 * @return 	 Returns the list of User 	 {@link List<User> }
	 */
	@Override
	public List<User> fetchUserRecords( User user ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserRecords()...");

		List<User> userList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + UserMasterSQL.SELECTALLCRITERIA_SQL);

			userList = getJdbcTemplate().query( UserMasterSQL.SELECTALLCRITERIA_SQL, new Object[] {
				user.getId(), user.getId(), user.getFirstName(), user.getFirstName(), user.getLastName(), user.getLastName(), user.getUserName(), user.getUserName(), user.getEmail(), user.getEmail(), user.getDisplayName(), user.getDisplayName(), user.getMobileNumber(), user.getMobileNumber(), user.getPassword(), user.getPassword(), user.getRoleId(), user.getRoleId(), user.getDateOfBirth(), user.getDateOfBirth(), user.getGender(), user.getGender(), user.getAddress1(), user.getAddress1(), user.getAddress2(), user.getAddress2(), user.getCountry(), user.getCountry(), user.getState(), user.getState(), user.getPlace(), user.getPlace(), user.getZip(), user.getZip(), user.getModifiedOn(), user.getModifiedOn(), user.getModifiedBy(), user.getModifiedBy(), user.getCreatedBy(), user.getCreatedBy()
				}, new ResultSetExtractor<List<User>>() {

				List<User> userTempList = new ArrayList<User>();

				@Override
				public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {

					User userTemp = null;

					while(rs.next()) {
						userTemp = new User();

						userTemp.setId(rs.getInt("Id"));
						userTemp.setFirstName(rs.getString("FirstName"));
						userTemp.setLastName(rs.getString("LastName"));
						userTemp.setUserName(rs.getString("UserName"));
						userTemp.setEmail(rs.getString("Email"));
						userTemp.setDisplayName(rs.getString("DisplayName"));
						userTemp.setMobileNumber(rs.getString("MobileNumber"));
						userTemp.setPassword(rs.getString("Password"));
						userTemp.setRoleId(rs.getInt("RoleId"));
						userTemp.setDateOfBirth(rs.getTimestamp("DateOfBirth"));
						userTemp.setGender(rs.getString("Gender"));
						userTemp.setAddress1(rs.getString("Address1"));
						userTemp.setAddress2(rs.getString("Address2"));
						userTemp.setCountry(rs.getString("Country"));
						userTemp.setState(rs.getString("State"));
						userTemp.setPlace(rs.getString("Place"));
						userTemp.setZip(rs.getInt("Zip"));
						userTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						userTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						userTemp.setCreatedBy(rs.getInt("CreatedBy"));
						userTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						userTemp.setStatus(rs.getInt("Status"));

						userTempList.add(userTemp);
					}

					return userTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecords(): All records fetched successfully: " +userList.toString());

		return userList;

	} //fetchUserRecords() method ends

	/**
	 * Fetches a single record by username from User table.
	 * @param user	The User entity
	 *
	 * @return 	 Returns the User 	 {@link User }
	 */
	@Override
	public List<User> fetchUserRecordByUsername(User user) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchUserRecordByUsername()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record By Username query = " + UserMasterSQL.SELECTBYUSERNAME_SQL);		
			
			List<User> userList = null;
			
			userList = getJdbcTemplate().query( UserMasterSQL.SELECTBYUSERNAME_SQL, new Object[] { user.getUserName()}, new ResultSetExtractor<List<User>>() {

				User userTemp = null;

				List<User> userTempList = new ArrayList<User>();

				@Override
				public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
									
					while(rs.next()) {	
						userTemp = new User();
						
						userTemp.setId(rs.getInt("Id"));
						userTemp.setFirstName(rs.getString("FirstName"));
						userTemp.setLastName(rs.getString("LastName"));
						userTemp.setUserName(rs.getString("UserName"));
						userTemp.setEmail(rs.getString("Email"));
						userTemp.setDisplayName(rs.getString("DisplayName"));
						userTemp.setPassword(rs.getString("Password"));
						userTemp.setMobileNumber(rs.getString("MobileNumber"));
						userTemp.setDateOfBirth(rs.getTimestamp("DateOfBirth"));
						userTemp.setGender(rs.getString("Gender"));
						userTemp.setRoleId(rs.getInt("RoleId"));
						userTemp.setModifiedBy(rs.getInt("DayOfWeek"));
						userTemp.setCreatedBy(rs.getInt("WeekId"));						
						userTemp.setStatus(rs.getInt("Status"));
						
						userTempList.add(userTemp);
					}

					return userTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchUserRecordByUsername(): Record fetched successfully: " + userList.toString());		
		
		return userList;

	} //fetchUserRecord() method ends


}