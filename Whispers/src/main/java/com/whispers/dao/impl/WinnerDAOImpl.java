/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao.impl;

// java imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import com.whispers.beans.Winner;
import com.whispers.dao.WinnerDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * This class provides implementation of CRUD operations to maintain Winner master table
 */

@Repository
public class WinnerDAOImpl extends TransactionService implements WinnerDAO {

	/**
	 * Inserts a record in Winner table.
	 * @param winner	The Winner entity
	 *
	 * @return 	 Returns the Winner 	 {@link Winner }
	 */
	
	private static final SimpleDateFormat monthDayYearformatter = new SimpleDateFormat("yyyy-MM-dd");	
	
	@Override
	public Winner addWinnerRecord( Winner winner ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addWinnerRecord()...");

		Integer id= -1;

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + WinnerMasterSQL.INSERT_SQL);

			winner.setStatus(1);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(winner);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(WinnerMasterSQL.INSERT_SQL, paramSource, generatedKey);

			id = generatedKey.getKey().intValue();
			winner.setId( id);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addWinnerRecord(): Record added successfully: " + winner.toString());

		return winner;

	} //addWinnerRecord() method ends


	/**
	 * Updates record in Winner table.
	 * @param winner	The Winner entity
	 *
	 * @return 	 Returns the Winner 	 {@link Winner }
	 */
	@Override
	public Winner updateWinnerRecord( Winner winner ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateWinnerRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + WinnerMasterSQL.UPDATE_SQL);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(winner);
			getNamedParameterJdbcTemplate().update(WinnerMasterSQL.UPDATE_SQL, paramSource);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateWinnerRecord(): Record update suucessfully: " + winner.toString());

		return winner;

	} //updateWinnerRecord() method ends


	/**
	 * Delete record(s) in Winner table. In case of softdelete, Status is set to 0 
	 * @param winner	The Winner entity
	 *
	 * @return 	 Returns the deleted record count 	 {@link Integer}
	 */
	@Override
	public Integer deleteWinnerRecord( final List<Winner> deleteWinnerRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteWinnerRecord()...");

			Integer rowsAffectedCount = -1;

			Log.logMessage("INFO", this.getClass().getName(), "DELETE query = " + WinnerMasterSQL.DELETE_SQL);

			getJdbcTemplate().batchUpdate(WinnerMasterSQL.DELETE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					Winner winner = deleteWinnerRecords.get(index);
					pstmt.setInt(1, winner.getId());
				}

				@Override
				public int getBatchSize() {

					return deleteWinnerRecords.size();
				}
			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteWinnerRecord(): Record deleted successfully: Rows affected = " + rowsAffectedCount);

		return rowsAffectedCount;

	} //deleteWinnerRecord() method ends


	/**
	 * Fetches a single record from Winner table.
	 * @param winner	The Winner entity
	 *
	 * @return 	 Returns the Winner 	 {@link Winner }
	 */
	@Override
	public Winner fetchWinnerRecord(Winner winner) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWinnerRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + WinnerMasterSQL.SELECT_SQL);

			winner = getJdbcTemplate().query( WinnerMasterSQL.SELECT_SQL, new Object[] { winner.getId()}, new ResultSetExtractor<Winner>() {

				Winner winnerTemp = new Winner();

				@Override
				public Winner extractData(ResultSet rs) throws SQLException, DataAccessException {

					while(rs.next()) {
						winnerTemp.setId(rs.getInt("Id"));
						winnerTemp.setSceneTitle(rs.getString("SceneTitle"));
						winnerTemp.setDateScenePublished(rs.getTimestamp("DateScenePublished"));
						winnerTemp.setWinnerId(rs.getInt("WinnerId"));
						winnerTemp.setWinnerName(rs.getString("WinnerName"));
						winnerTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						winnerTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						winnerTemp.setCreatedBy(rs.getInt("CreatedBy"));
						winnerTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						winnerTemp.setStatus(rs.getInt("Status"));
					}

					return winnerTemp;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWinnerRecord(): Record fetched successfully: " + winner.toString());

		return winner;

	} //fetchWinnerRecord() method ends


	/**
	 * Fetches all records from Winner table.
	 *
	 * @param none
	 *
	 * @return 	 Returns the list of Winner 	 {@link List of Winner }
	 */
	@Override
	public List<Winner> fetchAllWinnerRecord() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllWinnerRecord()...");

		List<Winner> winnerList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + WinnerMasterSQL.SELECTALL_SQL);
			
			
			winnerList = getJdbcTemplate().query( WinnerMasterSQL.SELECTALL_SQL, new Object[] { }, new ResultSetExtractor<List<Winner>>() {

				List<Winner> winnerTempList = new ArrayList<Winner>();

				@Override
				public List<Winner> extractData(ResultSet rs) throws SQLException, DataAccessException {

					Winner winnerTemp = null;

					while(rs.next()) {
						winnerTemp = new Winner();

						winnerTemp.setId(rs.getInt("Id"));
						winnerTemp.setSceneTitle(rs.getString("SceneTitle"));
						
						String date = monthDayYearformatter.format((java.util.Date) rs.getTimestamp("DateScenePublished"));
						
						winnerTemp.setAction(date);
						winnerTemp.setDateScenePublished(rs.getTimestamp("DateScenePublished"));
						winnerTemp.setWinnerId(rs.getInt("WinnerId"));
						winnerTemp.setWinnerName(rs.getString("WinnerName"));
						winnerTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						winnerTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						winnerTemp.setCreatedBy(rs.getInt("CreatedBy"));
						winnerTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						winnerTemp.setStatus(rs.getInt("Status"));
						winnerTemp.setWeekId(rs.getInt("WeekId"));
						winnerTemp.setStartDate(rs.getTimestamp("StartDate"));
						winnerTemp.setEndDate(rs.getTimestamp("EndDate"));
						
						winnerTempList.add(winnerTemp);
					}

					return winnerTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWinnerRecord(): All records fetched successfully: " + winnerList.toString());

		return winnerList;

	} //fetchAllWinnerRecord() method ends

	/**
	 * Fetches all Current week's winner records from Winner table.
	 *
	 * @param none
	 *
	 * @return 	 Returns the list of Winner 	 {@link List of Winner }
	 */
	
	@Override
	public void fetchCurrentWeekWinnerRecords( Winner winner ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWinnerRecords()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT CURRENT WEEK WINNER query = " + WinnerMasterSQL.SELECTCURRENT_SQL);

			getJdbcTemplate().update(WinnerMasterSQL.SELECTCURRENT_SQL, winner.getId());

			Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWinnerRecords(): Proc successfully ");

	} //fetchWinnerRecords() method ends
	
	
	/**
	 * Fetches all records for selected criteria from Winner table.
	 * @param winner	The Winner entity
	 *
	 * @return 	 Returns the list of Winner 	 {@link List<Winner> }
	 */
	@Override
	public List<Winner> fetchWinnerRecords() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWinnerRecords()...");

		List<Winner> winnerList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + WinnerMasterSQL.SELECTALLCRITERIA_SQL);

			winnerList = getJdbcTemplate().query( WinnerMasterSQL.SELECTALLCRITERIA_SQL, new Object[] {}, new ResultSetExtractor<List<Winner>>() {

				List<Winner> winnerTempList = new ArrayList<Winner>();

				@Override
				public List<Winner> extractData(ResultSet rs) throws SQLException, DataAccessException {

					Winner winnerTemp = null;

					while(rs.next()) {
						winnerTemp = new Winner();
						
						winnerTemp.setId(rs.getInt("Id"));
						winnerTemp.setSceneTitle(rs.getString("SceneTitle"));
						String date = monthDayYearformatter.format((java.util.Date) rs.getTimestamp("DateScenePublished"));
						
						winnerTemp.setAction(date);						
						winnerTemp.setDateScenePublished(rs.getTimestamp("DateScenePublished"));
						winnerTemp.setWinnerId(rs.getInt("WinnerId"));
						winnerTemp.setWinnerName(rs.getString("WinnerName"));
						winnerTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						winnerTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						winnerTemp.setCreatedBy(rs.getInt("CreatedBy"));
						winnerTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						winnerTemp.setStatus(rs.getInt("Status"));
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");					    
					    winnerTemp.setDay(simpleDateFormat.format(rs.getTimestamp("DateScenePublished")).toUpperCase());
											   
						winnerTempList.add(winnerTemp);
					}

					return winnerTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWinnerRecords(): All records fetched successfully: " +winnerList.toString());

		return winnerList;

	} //fetchWinnerRecords() method ends


}