/**
 * Created on 21 Jan, 2015
 */

package com.whispers.service;

// java imports
import java.util.List;

// spring framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// application imports
import com.whispers.beans.SceneComments;
import com.whispers.dao.SceneCommentsDAO;
import com.whispers.utils.Log;

/**
 * @author anka technology solutions private limited
 *
 * Service class implements business logic as well as delegates CRUD operations to DAO
 */

@Service
public class SceneCommentsService {

	/**
	 * The service class instance
	 */
	@Autowired
	private SceneCommentsDAO sceneCommentsDAO;
	
	/**
	 * Invokes dao method to add record.
	 * 
	 * @param sceneComments	The SceneComments entity
	 *
	 * @return 	 Returns the list of SceneComments 	 {@link List<SceneComments> }
	 */
	public List<SceneComments> addSceneCommentsRecord(SceneComments sceneComments) throws Exception {

		List <SceneComments>sceneCommentsList = null; 
		
		SceneComments sceneCom = new SceneComments();

		Log.logMessage("INFO", this.getClass().getName(), "Entering addSceneCommentsRecord()...");

		
			Log.logMessage("INFO", this.getClass().getName(), "sceneComments data : "+ sceneComments.toString());

			// Call dao to add record
			sceneComments = sceneCommentsDAO.addSceneCommentsRecord(sceneComments);

			sceneCom.setSceneId(sceneComments.getSceneId());
			// Call service to fetch updated records
			sceneCommentsList = sceneCommentsDAO.fetchSceneCommentsRecords(sceneCom);
			
			//sceneCommentsList = sceneCommentsDAO.fetchAllSceneCommentsRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting addSceneCommentsRecord(): Insert record call successfull...");

		return sceneCommentsList;

	} //addSceneCommentsRecord() method ends


	/**
	 * Invokes dao method to update record.
	 * 
	 * @param sceneComments	The SceneComments entity
	 *
	 * @return 	 Returns the list of SceneComments 	 {@link List<SceneComments> }
	 */
	public List<SceneComments> updateSceneCommentsRecord( SceneComments sceneComments ) throws Exception {

		List<SceneComments> sceneCommentsList = null; 
		// Integer rowsAffectedCountHistTbl= -1;

		// The SceneComments record fetched from SceneComments table prior to update
		// SceneComments TempSceneComments= new SceneComments();

		Log.logMessage("INFO", this.getClass().getName(), "Entering updateSceneCommentsRecord()...");

		
			// Get details of the record prior to update
			//TempSceneComments = sceneCommentsDAO.fetchSceneCommentsRecord(sceneComments);

			// Call dao to update record
			sceneComments = sceneCommentsDAO.updateSceneCommentsRecord(sceneComments);

			// Call service to fetch updated records
			sceneCommentsList = sceneCommentsDAO.fetchAllSceneCommentsRecord();

			//rowsAffectedCountHistTbl = sceneCommentsDAO.updateSceneCommentsHistoryTblForUpdatedRecord(TempSceneComments);

			//if(rowsAffectedCountHistTbl.intValue() == 0) {
				//throw new Exception("History table not updated for deleted records.");
			//}

		Log.logMessage("INFO", this.getClass().getName(), "Exiting updateSceneCommentsRecord(): Update record call successfull...");

		return sceneCommentsList;

	} //updateSceneCommentsRecord() method ends


	/**
	 * Invokes dao method to delete a record.
	 * 
	 * @param sceneCommentsList	The list of SceneComments entity
	 *
	 * @return 	 Returns the list of SceneComments 	 {@link List<SceneComments> }
	 */
	public List <SceneComments> deleteSceneCommentsRecord( List <SceneComments> deleteSceneCommentsRecords )  throws Exception{

		Log.logMessage("INFO", this.getClass().getName(), "Entering deleteSceneCommentsRecord()...");

		Integer rowsAffectedCount= -1;
		List<SceneComments> sceneCommentsList = null;

			// Call dao to delete record
			rowsAffectedCount = sceneCommentsDAO.deleteSceneCommentsRecord(deleteSceneCommentsRecords);

			if(rowsAffectedCount.intValue() == 0) {
				throw new Exception("No record deleted.");
			}

			// Call service to fetch updated records
			sceneCommentsList = sceneCommentsDAO.fetchAllSceneCommentsRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting deleteSceneCommentsRecord(): Delete record call successfull...");

		return sceneCommentsList;

	} //deleteSceneCommentsRecord() method ends


	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param sceneComments	The SceneComments entity
	 *
	 * @return 	 Returns the SceneComments 	 {@link SceneComments }
	 */
	public SceneComments fetchSceneCommentsRecord( SceneComments sceneComments ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneCommentsRecord()...");

		
			// Call dao to fetch record
			sceneComments = sceneCommentsDAO.fetchSceneCommentsRecord(sceneComments);

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneCommentsRecord(): Fetch record call successfull...");

		return sceneComments;

	} //fetchSceneCommentsRecord() method ends


	/**
	 * Invokes dao method to fetch all records.
	 * 
	 * @param none
	 *
	 * @return 	 Returns the list of SceneComments 	 {@link List<SceneComments> }
	 */
	public List <SceneComments> fetchAllSceneCommentsRecord() throws Exception{

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchAllSceneCommentsRecord()...");

		List<SceneComments> sceneCommentsList = null;

		

			// Call service to fetch record
			sceneCommentsList = sceneCommentsDAO.fetchAllSceneCommentsRecord();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchAllSceneCommentsRecord(): Fetch all record call successfull...");

		return sceneCommentsList;

	} //fetchAllSceneCommentsRecord() method ends


	/**
	 * Invokes dao method to fetch records based on criteria.
	 * 
	 * @param sceneComments	The SceneComments entity
	 *
	 * @return 	 Returns the list of SceneComments 	 {@link List<SceneComments< }
	 */
	public List<SceneComments> fetchSceneCommentsRecords( SceneComments sceneComments ) throws Exception {
		
		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchSceneCommentsRecords()...");

		List<SceneComments> sceneCommentsList = null;
		
			// Call dao to fetch records based on criteria
			sceneCommentsList = sceneCommentsDAO.fetchSceneCommentsRecords(sceneComments);

			Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchSceneCommentsRecords(): Fetch record (based on criteria) call successfull...");

		return sceneCommentsList;

	} //fetchSceneCommentsRecords() method ends
}


	