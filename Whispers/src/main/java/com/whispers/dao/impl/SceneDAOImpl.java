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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;










// application imports
import com.whispers.beans.Scene;
import com.whispers.beans.User;
import com.whispers.dao.SceneDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * This class provides implementation of CRUD operations to maintain Scene master table
 */

@Repository
public class SceneDAOImpl extends TransactionService implements SceneDAO {

	/**
	 * Inserts a record in PushNotification table.
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the Scene 	 {@link Scene }
	 */
	@Override
	public Scene addDeviceRecord( Scene scene ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addDeviceRecord()...");

		Integer id= -1;

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + SceneMasterSQL.INSERT1_SQL);			

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(scene);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(SceneMasterSQL.INSERT1_SQL, paramSource, generatedKey);

			id = generatedKey.getKey().intValue();
			scene.setId( id);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneRecord(): Record added successfully: " + scene.toString());

		return scene;

	} //addSceneRecord() method ends

	
	/**
	 * Inserts a record in Scene table.
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the Scene 	 {@link Scene }
	 */
	@Override
	public Scene addSceneRecord( Scene scene ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSceneRecord()...");

		Integer id= -1;

			Log.logMessage("INFO", this.getClass().getName(), "INSERT query = " + SceneMasterSQL.INSERT_SQL);

			scene.setStatus(1);

			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(scene);
			KeyHolder generatedKey = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().update(SceneMasterSQL.INSERT_SQL, paramSource, generatedKey);

			id = generatedKey.getKey().intValue();
			scene.setId( id);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneRecord(): Record added successfully: " + scene.toString());

		return scene;

	} //addSceneRecord() method ends


	/**
	 * Updates record in Scene table.
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the Scene 	 {@link Scene }
	 */
	@Override
	public Scene updateSceneRecord( Scene scene ) throws Exception {

			Log.logMessage("INFO", this.getClass().getName(), "Entering updateSceneRecord()...");	

			Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + SceneMasterSQL.UPDATE_SQL);
			
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(scene);
			getNamedParameterJdbcTemplate().update(SceneMasterSQL.UPDATE_SQL, paramSource);

			Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSceneRecord(): Record update suucessfully: " + scene.toString());

			return scene;

	} //updateSceneRecord() method ends


	/**
	 * Updates record in Scene table.
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the Scene 	 {@link Scene }
	 */
	@Override
	public Scene publishSceneRecord( Scene scene ) throws Exception {

			Log.logMessage("INFO", this.getClass().getName(), "Entering publishSceneRecord()...");	

			Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + SceneMasterSQL.UPDATE1_SQL);
			
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(scene);
			getNamedParameterJdbcTemplate().update(SceneMasterSQL.UPDATE1_SQL, paramSource);

			Log.logMessage("INFO", this.getClass().getName(), "Exiting publishSceneRecord(): Record update suucessfully: " + scene.toString());

			return scene;

	} //updateSceneRecord() method ends

	
	/**
	 * Delete record(s) in Scene table. In case of softdelete, Status is set to 0 
	 * @param scene	The User entity
	 *
	 * @return 	 Returns the deleted record count 	 {@link Integer}
	 */
	@Override
	public Integer deleteSceneRecord( final List<User> deleteSceneRecords ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteSceneRecord()...");

			Integer rowsAffectedCount = -1;

			Log.logMessage("INFO", this.getClass().getName(), "DELETE query = " + SceneMasterSQL.DELETE_SQL);

			getJdbcTemplate().batchUpdate(SceneMasterSQL.DELETE_SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement pstmt, int index) throws SQLException {
					User user = deleteSceneRecords.get(index);
					pstmt.setInt(1, user.getModifiedBy());
					pstmt.setInt(2, user.getId());
					Log.logMessage("INFO", this.getClass().getName(), "data received = " + user.toString());				
				}

				@Override
				public int getBatchSize() {
					return deleteSceneRecords.size();
				}
			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSceneRecord(): Record deleted successfully: Rows affected = " + rowsAffectedCount);

		return rowsAffectedCount;

	} //deleteSceneRecord() method ends


	/**
	 * Fetches a single record from Scene table.
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the Scene 	 {@link Scene }
	 */
	@Override
	public Scene fetchSceneRecord(Scene scene) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneRecord()...");

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + SceneMasterSQL.SELECT_SQL);

			scene = getJdbcTemplate().query( SceneMasterSQL.SELECT_SQL, new Object[] { scene.getId()}, new ResultSetExtractor<Scene>() {

				Scene sceneTemp = new Scene();

				@Override
				public Scene extractData(ResultSet rs) throws SQLException, DataAccessException {
					while(rs.next()) {
						sceneTemp.setId(rs.getInt("Id"));
						sceneTemp.setSceneTitle(rs.getString("SceneTitle"));
						sceneTemp.setAuthorName(rs.getString("AuthorName"));
						sceneTemp.setDateSubmitted(rs.getTimestamp("DateSubmitted"));
						sceneTemp.setIsSubmitted(rs.getInt("IsSubmitted"));
						sceneTemp.setSubmittedBy(rs.getInt("SubmittedBy"));
						sceneTemp.setSubmittedByName(rs.getString("SubmittedByName"));
						sceneTemp.setSceneImage(rs.getString("SceneImage"));
						sceneTemp.setSceneUploadType(rs.getString("SceneUploadType"));
						sceneTemp.setSceneShortText(rs.getString("SceneShortText"));
						sceneTemp.setSceneDescription(rs.getString("SceneDescription"));						
						sceneTemp.setTimePlace(rs.getString("TimePlace"));
						sceneTemp.setAllowComments(rs.getInt("AllowComments"));
						sceneTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						sceneTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						sceneTemp.setCreatedBy(rs.getInt("CreatedBy"));
						sceneTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						sceneTemp.setStatus(rs.getInt("Status"));
						sceneTemp.setNotificationText(rs.getString("NotificationText"));
						sceneTemp.setScenePublished(rs.getInt("ScenePublished"));
						sceneTemp.setPublishDate(rs.getTimestamp("PublishDate"));

					}

					return sceneTemp;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecord(): Record fetched successfully: " + scene.toString());

		return scene;

	} //fetchSceneRecord() method ends


	/**
	 * Fetches all records from Scene table.
	 * @param user
	 *
	 * @return 	 Returns the list of Scene 	 {@link List of Scene }
	 */
	@Override
	public List<Scene> fetchAllSceneRecord(User user) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllSceneRecord()...");

		System.out.println("user.getRoleId() == "+user.getRoleId());
		
		List<Scene> sceneList = null;
		String SELECT_SQL = SceneMasterSQL.SELECTALL_SQL;
		Object[] params = null;
			if((user.getRoleId() != null) && (user.getRoleId() == 2)){ //playwriter
					
				SELECT_SQL = SceneMasterSQL.SELECT_BY_OWNER_SQL;
				params = new Object[] { user.getId() };
					
			}else{ //admin and dramaturg					
				SELECT_SQL = SceneMasterSQL.SELECTALL_SQL;
				params = new Object[] { user.getId() };
			}
			
			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + SELECT_SQL);

			sceneList = getJdbcTemplate().query( SELECT_SQL, params, new ResultSetExtractor<List<Scene>>() {

				List<Scene> sceneTempList = new ArrayList<Scene>();

				@Override
				public List<Scene> extractData(ResultSet rs) throws SQLException, DataAccessException {

					Scene sceneTemp = null;

					while(rs.next()) {
						sceneTemp = new Scene();

						sceneTemp.setId(rs.getInt("Id"));
						sceneTemp.setSceneTitle(rs.getString("SceneTitle"));
						sceneTemp.setDateSubmitted(rs.getTimestamp("DateSubmitted"));
						sceneTemp.setIsSubmitted(rs.getInt("IsSubmitted"));
						sceneTemp.setSubmittedBy(rs.getInt("SubmittedBy"));
						sceneTemp.setSubmittedByName(rs.getString("SubmittedByName"));
						//sceneTemp.setSceneImage(rs.getString("SceneImage"));
						sceneTemp.setSceneUploadType(rs.getString("SceneUploadType"));
						sceneTemp.setSceneShortText(rs.getString("SceneShortText"));
						sceneTemp.setSceneDescription(rs.getString("SceneDescription"));
						sceneTemp.setTimePlace(rs.getString("TimePlace"));
						sceneTemp.setAllowComments(rs.getInt("AllowComments"));
						sceneTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						sceneTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						sceneTemp.setCreatedBy(rs.getInt("CreatedBy"));
						sceneTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						sceneTemp.setStatus(rs.getInt("Status"));
						sceneTemp.setNotificationText(rs.getString("NotificationText"));
						sceneTemp.setScenePublished(rs.getInt("ScenePublished"));
						sceneTemp.setPublishDate(rs.getTimestamp("PublishDate"));

						sceneTempList.add(sceneTemp);
					}

					return sceneTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSceneRecord(): All records fetched successfully: " + sceneList.toString());

		return sceneList;

	} //fetchAllSceneRecord() method ends


	/**
	 * Fetches all records for selected criteria from Scene table.
	 * @param scene	The Scene entity
	 *
	 * @return 	 Returns the list of Scene 	 {@link List<Scene> }
	 */
	@Override
	public List<Scene> fetchSceneRecords( Scene scene ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneRecords()...");

		List<Scene> sceneList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT ALL query = " + SceneMasterSQL.SELECTALLCRITERIA_SQL);
			
			sceneList = getJdbcTemplate().query( SceneMasterSQL.SELECTALLCRITERIA_SQL, new Object[] {
				scene.getId(), scene.getId(), scene.getSceneTitle(), scene.getSceneTitle(), scene.getDateSubmitted(), scene.getDateSubmitted(), scene.getSubmittedBy(), scene.getSubmittedBy(), scene.getSceneImage(), scene.getSceneImage(), scene.getSceneUploadType(), scene.getSceneUploadType(), scene.getSceneShortText(), scene.getSceneShortText(), scene.getSceneDescription(), scene.getSceneDescription(), scene.getTimePlace(), scene.getTimePlace(), scene.getAllowComments(), scene.getAllowComments(), scene.getModifiedOn(), scene.getModifiedOn(), scene.getModifiedBy(), scene.getModifiedBy(), scene.getCreatedBy(), scene.getCreatedBy(), scene.getScenePublished(), scene.getScenePublished(), scene.getPublishDate(), scene.getPublishDate()
				}, new ResultSetExtractor<List<Scene>>() {

				List<Scene> sceneTempList = new ArrayList<Scene>();

				@Override
				public List<Scene> extractData(ResultSet rs) throws SQLException, DataAccessException {

					Scene sceneTemp = null;

					while(rs.next()) {
						sceneTemp = new Scene();

						sceneTemp.setId(rs.getInt("Id"));
						sceneTemp.setSceneTitle(rs.getString("SceneTitle"));						
						sceneTemp.setAuthorName(rs.getString("AuthorName"));
						sceneTemp.setDateSubmitted(rs.getTimestamp("DateSubmitted"));
						sceneTemp.setIsSubmitted(rs.getInt("IsSubmitted"));
						sceneTemp.setSubmittedBy(rs.getInt("SubmittedBy"));
						sceneTemp.setSubmittedByName(rs.getString("SubmittedByName"));
						sceneTemp.setSceneImage(rs.getString("SceneImage"));
						sceneTemp.setSceneUploadType(rs.getString("SceneUploadType"));
						sceneTemp.setSceneShortText(rs.getString("SceneShortText"));
						sceneTemp.setSceneDescription(rs.getString("SceneDescription"));
						sceneTemp.setTimePlace(rs.getString("TimePlace"));
						sceneTemp.setAllowComments(rs.getInt("AllowComments"));
						sceneTemp.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						sceneTemp.setModifiedBy(rs.getInt("ModifiedBy"));
						sceneTemp.setCreatedBy(rs.getInt("CreatedBy"));
						sceneTemp.setCreatedOn(rs.getTimestamp("CreatedOn"));
						sceneTemp.setStatus(rs.getInt("Status"));

						sceneTempList.add(sceneTemp);
					}

					return sceneTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecords(): All records fetched successfully: " +sceneList.toString());

		return sceneList;

	} //fetchSceneRecords() method ends



	/**
	 * Fetches T&C page URL
	 * 
	 * @param none
	 *
	 * @return 	 Returns T&C page URL 	 {@link String}
	 */
	@Override
	public String getTCPage() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering getTCPage()...");

		String tcPageLink = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT query = " + SceneMasterSQL.SELECT_TCPAGE_SQL);

			tcPageLink = getJdbcTemplate().query( SceneMasterSQL.SELECT_TCPAGE_SQL, new Object[] { "tcPage" }, new ResultSetExtractor<String>() {

				String tcPageLinkTemp = null;

				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {

					while(rs.next()) {
						tcPageLinkTemp = rs.getString("Value");
					}

					return tcPageLinkTemp;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting getTCPage(): T&C page URL fetched successfully: " + tcPageLink);

		return tcPageLink;

	} //getTCPage() method ends

	/**
	 * fetchSceneUserReport
	 * @param none
	 *
	 * @return 	 Returns the list of Scene 	 {@link List<Scene> }
	 */
	@Override
	public List<Scene> fetchDeviceRecords( ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchDeviceRecords()...");

		List<Scene> sceneList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Report query = " + SceneMasterSQL.SELECT_DEVICE_DETAILS_SQL);

			sceneList = getJdbcTemplate().query( SceneMasterSQL.SELECT_DEVICE_DETAILS_SQL, new Object[] { }, new ResultSetExtractor<List<Scene>>() {

				List<Scene> sceneTempList = new ArrayList<Scene>();

				@Override
				public List<Scene> extractData(ResultSet rs) throws SQLException, DataAccessException {

					Scene sceneTemp = null;

					while(rs.next()) {
						sceneTemp = new Scene();
						
						sceneTemp.setSceneTitle(rs.getString("DeviceName"));
						sceneTemp.setAuthorName(rs.getString("DeviceToken"));
					
						sceneTempList.add(sceneTemp);
					}

					return sceneTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecords(): All records fetched successfully: " +sceneList.toString());

		return sceneList;

	} //fetchSceneRecords() method ends

	
	/**
	 * fetchSceneUserReport
	 * @param none
	 *
	 * @return 	 Returns the list of Scene 	 {@link List<Scene> }
	 */
	@Override
	public List<Scene> fetchSceneUserReport( ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneUserReport()...");

		List<Scene> sceneList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Report query = " + SceneMasterSQL.SELECT_SCENE_USER_SQL);

			sceneList = getJdbcTemplate().query( SceneMasterSQL.SELECT_SCENE_USER_SQL, new Object[] { }, new ResultSetExtractor<List<Scene>>() {

				List<Scene> sceneTempList = new ArrayList<Scene>();

				@Override
				public List<Scene> extractData(ResultSet rs) throws SQLException, DataAccessException {

					Scene sceneTemp = null;

					while(rs.next()) {
						sceneTemp = new Scene();

						sceneTemp.setId(rs.getInt("Id"));
						sceneTemp.setSceneTitle(rs.getString("SceneTitle"));
						sceneTemp.setDateSubmitted(rs.getTimestamp("DateSubmitted"));
						sceneTemp.setSubmittedByName(rs.getString("SubmittedByName"));

						sceneTempList.add(sceneTemp);
					}

					return sceneTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecords(): All records fetched successfully: " +sceneList.toString());

		return sceneList;

	} //fetchSceneRecords() method ends

	@Override
	public List<Scene> fetchSceneFeedbackReport( ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneFeedbackReport()...");

		List<Scene> sceneList = null;

			Log.logMessage("INFO", this.getClass().getName(), "SELECT Report query = " + SceneMasterSQL.SELECT_FEEDBACK_USER_SQL);

			sceneList = getJdbcTemplate().query( SceneMasterSQL.SELECT_FEEDBACK_USER_SQL, new Object[] { }, new ResultSetExtractor<List<Scene>>() {

				List<Scene> sceneTempList = new ArrayList<Scene>();

				@Override
				public List<Scene> extractData(ResultSet rs) throws SQLException, DataAccessException {

					Scene sceneTemp = null;

					while(rs.next()) {
						sceneTemp = new Scene();

						sceneTemp.setSceneTitle(rs.getString("SceneTitle"));
						sceneTemp.setPublishDate(rs.getTimestamp("PublishDate"));
						sceneTemp.setTimePlace(rs.getString("Question"));
						sceneTemp.setNotificationText(rs.getString("OptionValue"));
						sceneTemp.setAuthorName(rs.getString("Average")+"%");
						
						sceneTempList.add(sceneTemp);
					}

					return sceneTempList;
				}

			});

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneRecords(): All records fetched successfully: " +sceneList.toString());

		return sceneList;

	} //fetchSceneRecords() method ends
	
}