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
import com.whispers.beans.SceneComments;
import com.whispers.dao.SceneCommentsDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * This class provides implementation of CRUD operations to maintain Scene Comments master table
 */

@Repository
public class SceneCommentsDAOImpl extends TransactionService implements SceneCommentsDAO {

	/**
	 * Inserts a record in SceneComments table.
	 * @param sceneComments	The SceneComments entity
	 *
	 * @return 	 Returns the SceneComments 	 {@link SceneComments }
	 */
	@Override
	public SceneComments addSceneCommentsRecord( SceneComments sceneComments ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSceneCommentsRecord()...");

		Integer id= -1;

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + SceneCommentsMasterSQL.INSERT_SQL);

			sceneComments.setStatus(1);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sceneComments);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(SceneCommentsMasterSQL.INSERT_SQL, paramSource, generatedKey);

			id = generatedKey.getKey().intValue();
			sceneComments.setId( id);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneCommentsRecord(): Record added successfully: " + sceneComments.toString());

		return sceneComments;

	} //addSceneCommentsRecord() method ends


	/**
	 * Updates record in SceneComments table.
	 * @param sceneComments	The SceneComments entity
	 *
	 * @return 	 Returns the SceneComments 	 {@link SceneComments }
	 */
	@Override
	public SceneComments updateSceneCommentsRecord( SceneComments sceneComments ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateSceneCommentsRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + SceneCommentsMasterSQL.UPDATE_SQL);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(sceneComments);
			getNamedParameterJdbcTemplate().update(SceneCommentsMasterSQL.UPDATE_SQL, paramSource);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSceneCommentsRecord(): Record update suucessfully: " + sceneComments.toString());

		return sceneComments;

	} //updateSceneCommentsRecord() method ends


	/**
	 * Delete record(s) in SceneComments table. In case of softdelete, Status is set to 0 
	 * @param sceneComments	The SceneComments entity
	 *
	 * @return 	 Returns the deleted record count 	 {@link Integer}
	 */
	@Override
	public Integer deleteSceneCommentsRecord( final List<SceneComments> deleteSceneCommentsRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteSceneCommentsRecord()...");

			Integer rowsAffectedCount = -1;

			Log.logMessage("INFO", this.getClass().getName(), "DELETE query = " + SceneCommentsMasterSQL.DELETE_SQL);

			getJdbcTemplate().batchUpdate(SceneCommentsMasterSQL.DELETE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {

					SceneComments sceneComments = deleteSceneCommentsRecords.get(index);
					pstmt.setInt(1, sceneComments.getId());
				}

				@Override
				public int getBatchSize() {

					return deleteSceneCommentsRecords.size();
				}
			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSceneCommentsRecord(): Record deleted successfully: Rows affected = " + rowsAffectedCount);

		return rowsAffectedCount;

	} //deleteSceneCommentsRecord() method ends


	/**
	 * Fetches a single record from SceneComments table.
	 * @param sceneComments	The SceneComments entity
	 *
	 * @return 	 Returns the SceneComments 	 {@link SceneComments }
	 */
	@Override
	public SceneComments fetchSceneCommentsRecord(SceneComments sceneComments) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneCommentsRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + SceneCommentsMasterSQL.SELECT_SQL);

			sceneComments = getJdbcTemplate().query( SceneCommentsMasterSQL.SELECT_SQL, new Object[] { sceneComments.getId()}, new ResultSetExtractor<SceneComments>() {

				SceneComments sceneCommentsTemp = new SceneComments();

				@Override
				public SceneComments extractData(ResultSet rs) throws SQLException, DataAccessException {

					while(rs.next()) {
						sceneCommentsTemp.setId(rs.getInt("Id"));
						sceneCommentsTemp.setDateCommentSubmitted(rs.getTimestamp("DateCommentSubmitted"));
						sceneCommentsTemp.setSubmittedById(rs.getInt("SubmittedById"));
						sceneCommentsTemp.setSubmittedByName(rs.getString("SubmittedByName"));
						sceneCommentsTemp.setComment(rs.getString("Comment"));
						sceneCommentsTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						sceneCommentsTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						sceneCommentsTemp.setCreatedBy(rs.getInt("CreatedBy"));
						sceneCommentsTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						sceneCommentsTemp.setStatus(rs.getInt("Status"));
					}

					return sceneCommentsTemp;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneCommentsRecord(): Record fetched successfully: " + sceneComments.toString());

		return sceneComments;

	} //fetchSceneCommentsRecord() method ends


	/**
	 * Fetches all records from SceneComments table.
	 *
	 * @param none
	 *
	 * @return 	 Returns the list of SceneComments 	 {@link List of SceneComments }
	 */
	@Override
	public List<SceneComments> fetchAllSceneCommentsRecord() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllSceneCommentsRecord()...");

		List<SceneComments> sceneCommentsList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + SceneCommentsMasterSQL.SELECTALL_SQL);

			sceneCommentsList = getJdbcTemplate().query( SceneCommentsMasterSQL.SELECTALL_SQL, new Object[] { }, new ResultSetExtractor<List<SceneComments>>() {

				List<SceneComments> sceneCommentsTempList = new ArrayList<SceneComments>();

				@Override
				public List<SceneComments> extractData(ResultSet rs) throws SQLException, DataAccessException {

					SceneComments sceneCommentsTemp = null;

					while(rs.next()) {
						sceneCommentsTemp = new SceneComments();

						sceneCommentsTemp.setId(rs.getInt("Id"));
						sceneCommentsTemp.setDateCommentSubmitted(rs.getTimestamp("DateCommentSubmitted"));
						sceneCommentsTemp.setSubmittedById(rs.getInt("SubmittedById"));
						sceneCommentsTemp.setSubmittedByName(rs.getString("SubmittedByName"));
						sceneCommentsTemp.setComment(rs.getString("Comment"));
						sceneCommentsTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						sceneCommentsTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						sceneCommentsTemp.setCreatedBy(rs.getInt("CreatedBy"));
						sceneCommentsTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						sceneCommentsTemp.setStatus(rs.getInt("Status"));

						sceneCommentsTempList.add(sceneCommentsTemp);
					}

					return sceneCommentsTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSceneCommentsRecord(): All records fetched successfully: " + sceneCommentsList.toString());

		return sceneCommentsList;

	} //fetchAllSceneCommentsRecord() method ends


	/**
	 * Fetches all records for selected criteria from SceneComments table.
	 * @param sceneComments	The SceneComments entity
	 *
	 * @return 	 Returns the list of SceneComments 	 {@link List<SceneComments> }
	 */
	@Override
	public List<SceneComments> fetchSceneCommentsRecords( SceneComments sceneComments ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneCommentsRecords()...");

		List<SceneComments> sceneCommentsList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + SceneCommentsMasterSQL.SELECTALLCRITERIA_SQL);

			sceneCommentsList = getJdbcTemplate().query( SceneCommentsMasterSQL.SELECTALLCRITERIA_SQL, new Object[] {
				sceneComments.getId(), sceneComments.getId(), sceneComments.getDateCommentSubmitted(), sceneComments.getDateCommentSubmitted(),sceneComments.getSceneId(),sceneComments.getSceneId(), sceneComments.getSubmittedById(), sceneComments.getSubmittedById(), sceneComments.getComment(), sceneComments.getComment(), sceneComments.getModifiedOn(), sceneComments.getModifiedOn(), sceneComments.getModifiedBy(), sceneComments.getModifiedBy(), sceneComments.getCreatedBy(), sceneComments.getCreatedBy()
				}, new ResultSetExtractor<List<SceneComments>>() {

				List<SceneComments> sceneCommentsTempList = new ArrayList<SceneComments>();

				@Override
				public List<SceneComments> extractData(ResultSet rs) throws SQLException, DataAccessException {

					SceneComments sceneCommentsTemp = null;

					while(rs.next()) {
						sceneCommentsTemp = new SceneComments();

						sceneCommentsTemp.setId(rs.getInt("Id"));
						sceneCommentsTemp.setDateCommentSubmitted(rs.getTimestamp("DateCommentSubmitted"));
						sceneCommentsTemp.setSceneId(rs.getInt("SceneId"));
						sceneCommentsTemp.setSubmittedById(rs.getInt("SubmittedById"));
						sceneCommentsTemp.setSubmittedByName(rs.getString("SubmittedByName"));
						sceneCommentsTemp.setComment(rs.getString("Comment"));
						sceneCommentsTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						sceneCommentsTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						sceneCommentsTemp.setCreatedBy(rs.getInt("CreatedBy"));
						sceneCommentsTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						sceneCommentsTemp.setStatus(rs.getInt("Status"));

						sceneCommentsTempList.add(sceneCommentsTemp);
					}

					return sceneCommentsTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneCommentsRecords(): All records fetched successfully: " +sceneCommentsList.toString());

		return sceneCommentsList;

	} //fetchSceneCommentsRecords() method ends


}