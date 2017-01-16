/**
 * Created on 21 Jan, 2015
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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;







// application imports
import com.whispers.beans.WhispersRating;
import com.whispers.dao.WhispersRatingDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * This class provides implementation of CRUD operations to maintain Whispers Rating master table
 */

@Repository
public class WhispersRatingDAOImpl extends TransactionService implements WhispersRatingDAO {

	/**
	 * Inserts a record in WhispersRating table.
	 * @param whispersRating	The WhispersRating entity
	 *
	 * @return 	 Returns the WhispersRating 	 {@link WhispersRating }
	 */
	@Override
	public WhispersRating addWhispersRatingRecord( WhispersRating whispersRating ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addWhispersRatingRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + WhispersRatingMasterSQL.INSERT_SQL);

			whispersRating.setStatus(1);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(whispersRating);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(WhispersRatingMasterSQL.INSERT_SQL, paramSource, generatedKey);
		
		Log.logMessage("INFO", this.getClass().getName(), "Exiting addWhispersRatingRecord(): Record added successfully: " + whispersRating.toString());

		return whispersRating;

	} //addWhispersRatingRecord() method ends


	/**
	 * Updates record in WhispersRating table.
	 * @param whispersRating	The WhispersRating entity
	 *
	 * @return 	 Returns the WhispersRating 	 {@link WhispersRating }
	 */
	@Override
	public WhispersRating updateWhispersRatingRecord( WhispersRating whispersRating ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateWhispersRatingRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + WhispersRatingMasterSQL.UPDATE_SQL);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(whispersRating);
			getNamedParameterJdbcTemplate().update(WhispersRatingMasterSQL.UPDATE_SQL, paramSource);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateWhispersRatingRecord(): Record update suucessfully: " + whispersRating.toString());

		return whispersRating;

	} //updateWhispersRatingRecord() method ends


	/**
	 * Delete record(s) in WhispersRating table. In case of softdelete, Status is set to 0 
	 * @param whispersRating	The WhispersRating entity
	 *
	 * @return 	 Returns the deleted record count 	 {@link Integer}
	 */
	@Override
	public Integer deleteWhispersRatingRecord( final List<WhispersRating> deleteWhispersRatingRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteWhispersRatingRecord()...");

			Integer rowsAffectedCount = -1;

			Log.logMessage("INFO", this.getClass().getName(), "DELETE query = " + WhispersRatingMasterSQL.DELETE_SQL);

			getJdbcTemplate().batchUpdate(WhispersRatingMasterSQL.DELETE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					WhispersRating whispersRating = deleteWhispersRatingRecords.get(index);
					pstmt.setInt(1, whispersRating.getId());
				}

				@Override
				public int getBatchSize() {

					return deleteWhispersRatingRecords.size();
				}
			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteWhispersRatingRecord(): Record deleted successfully: Rows affected = " + rowsAffectedCount);

		return rowsAffectedCount;

	} //deleteWhispersRatingRecord() method ends


	/**
	 * Fetches a single record from WhispersRating table.
	 * @param whispersRating	The WhispersRating entity
	 *
	 * @return 	 Returns the WhispersRating 	 {@link WhispersRating }
	 */
	@Override
	public WhispersRating fetchWhispersRatingRecord(WhispersRating whispersRating) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWhispersRatingRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + WhispersRatingMasterSQL.SELECT_SQL);

			whispersRating = getJdbcTemplate().query( WhispersRatingMasterSQL.SELECT_SQL, new Object[] { whispersRating.getId()}, new ResultSetExtractor<WhispersRating>() {

				WhispersRating whispersRatingTemp = new WhispersRating();

				@Override
				public WhispersRating extractData(ResultSet rs) throws SQLException, DataAccessException {

					while(rs.next()) {
						whispersRatingTemp.setId(rs.getInt("Id"));
						whispersRatingTemp.setDateRatingSubmitted(rs.getTimestamp("DateRatingSubmitted"));
						whispersRatingTemp.setSubmittedById(rs.getInt("SubmittedById"));
						whispersRatingTemp.setRating(rs.getInt("Rating"));
						whispersRatingTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						whispersRatingTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						whispersRatingTemp.setCreatedBy(rs.getInt("CreatedBy"));
						whispersRatingTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						whispersRatingTemp.setStatus(rs.getInt("Status"));
					}

					return whispersRatingTemp;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWhispersRatingRecord(): Record fetched successfully: " + whispersRating.toString());

		return whispersRating;

	} //fetchWhispersRatingRecord() method ends


	/**
	 * Fetches all records from WhispersRating table.
	 *
	 * @param none
	 *
	 * @return 	 Returns the list of WhispersRating 	 {@link List of WhispersRating }
	 */
	@Override
	public List<WhispersRating> fetchAllWhispersRatingRecord() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllWhispersRatingRecord()...");

		List<WhispersRating> whispersRatingList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + WhispersRatingMasterSQL.SELECTALL_SQL);

			whispersRatingList = getJdbcTemplate().query( WhispersRatingMasterSQL.SELECTALL_SQL, new Object[] { }, new ResultSetExtractor<List<WhispersRating>>() {

				List<WhispersRating> whispersRatingTempList = new ArrayList<WhispersRating>();

				@Override
				public List<WhispersRating> extractData(ResultSet rs) throws SQLException, DataAccessException {

					WhispersRating whispersRatingTemp = null;

					while(rs.next()) {
						whispersRatingTemp = new WhispersRating();

						whispersRatingTemp.setId(rs.getInt("Id"));
						whispersRatingTemp.setDateRatingSubmitted(rs.getTimestamp("DateRatingSubmitted"));
						whispersRatingTemp.setSubmittedById(rs.getInt("SubmittedById"));
						whispersRatingTemp.setRating(rs.getInt("Rating"));
						whispersRatingTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						whispersRatingTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						whispersRatingTemp.setCreatedBy(rs.getInt("CreatedBy"));
						whispersRatingTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						whispersRatingTemp.setStatus(rs.getInt("Status"));

						whispersRatingTempList.add(whispersRatingTemp);
					}

					return whispersRatingTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllWhispersRatingRecord(): All records fetched successfully: " + whispersRatingList.toString());

		return whispersRatingList;

	} //fetchAllWhispersRatingRecord() method ends


	/**
	 * Fetches all records for selected criteria from WhispersRating table.
	 * @param whispersRating	The WhispersRating entity
	 *
	 * @return 	 Returns the list of WhispersRating 	 {@link List<WhispersRating> }
	 */
	@Override
	public List<WhispersRating> fetchWhispersRatingRecords( WhispersRating whispersRating ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchWhispersRatingRecords()...");

		List<WhispersRating> whispersRatingList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + WhispersRatingMasterSQL.SELECTALLCRITERIA_SQL);

			whispersRatingList = getJdbcTemplate().query( WhispersRatingMasterSQL.SELECTALLCRITERIA_SQL, new Object[] {
				whispersRating.getId(), whispersRating.getId(), whispersRating.getDateRatingSubmitted(), whispersRating.getDateRatingSubmitted(), whispersRating.getSubmittedById(), whispersRating.getSubmittedById(), whispersRating.getRating(), whispersRating.getRating(), whispersRating.getModifiedOn(), whispersRating.getModifiedOn(), whispersRating.getModifiedBy(), whispersRating.getModifiedBy(), whispersRating.getCreatedBy(), whispersRating.getCreatedBy()
				}, new ResultSetExtractor<List<WhispersRating>>() {

				List<WhispersRating> whispersRatingTempList = new ArrayList<WhispersRating>();

				@Override
				public List<WhispersRating> extractData(ResultSet rs) throws SQLException, DataAccessException {

					WhispersRating whispersRatingTemp = null;

					while(rs.next()) {
						whispersRatingTemp = new WhispersRating();

						whispersRatingTemp.setId(rs.getInt("Id"));
						whispersRatingTemp.setDateRatingSubmitted(rs.getTimestamp("DateRatingSubmitted"));
						whispersRatingTemp.setSubmittedById(rs.getInt("SubmittedById"));
						whispersRatingTemp.setRating(rs.getInt("Rating"));
						whispersRatingTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						whispersRatingTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						whispersRatingTemp.setCreatedBy(rs.getInt("CreatedBy"));
						whispersRatingTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						whispersRatingTemp.setStatus(rs.getInt("Status"));

						whispersRatingTempList.add(whispersRatingTemp);
					}

					return whispersRatingTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchWhispersRatingRecords(): All records fetched successfully: " +whispersRatingList.toString());

		return whispersRatingList;

	} //fetchWhispersRatingRecords() method ends


}