/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao;

// java imports
import java.util.List;











// application imports
import com.whispers.beans.Scene;
import com.whispers.beans.User;

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on Scene master
 */
public interface SceneDAO {
	/**
	 * Inserts a record in Scene table.
	 * @param Scene	The Scene entity
	 *
	 * @return 	Returns the Scene	{@link Scene }
	 */
	public Scene addDeviceRecord( Scene scene ) throws Exception;

	
	/**
	 * Inserts a record in Scene table.
	 * @param Scene	The Scene entity
	 *
	 * @return 	Returns the Scene	{@link Scene }
	 */
	public Scene addSceneRecord( Scene scene ) throws Exception;

	/**
	 * Updates record in Scene table.
	 * @param Scene	The Scene entity
	 *
	 * @return 	Returns the Scene	{@link Scene }
	 */
	public Scene updateSceneRecord( Scene scene ) throws Exception;

	/**
	 * Updates record in Scene table.
	 * @param Scene	The Scene entity
	 *
	 * @return 	Returns the Scene	{@link Scene }
	 */
	public Scene publishSceneRecord( Scene scene ) throws Exception;

	
	/**
	 * Deletes a record from Scene table.
	 * @param SceneList	The list of User entity
	 *
	 * @return 	Returns the deleted count of record(s) 	{@link List}
	 */
	public Integer deleteSceneRecord( final List<User> deleteSceneRecords) throws Exception;

	/**
	 * Fetches a single record from Scene table
	 * @param Scene	The Scene entity
	 *
	 * @return 	Returns the Scene	{@link Scene }
	 */
	public Scene fetchSceneRecord( Scene scene ) throws Exception;

	/**
	 * Fetches all the records from Scene table
	 * @param user 
	 *
	 * @return 	Returns the list of Scene	{@link List}
	 */
	public List<Scene> fetchDeviceRecords() throws Exception;

	
	/**
	 * Fetches all the records from Scene table
	 * @param user 
	 *
	 * @return 	Returns the list of Scene	{@link List}
	 */
	public List<Scene> fetchAllSceneRecord(User user) throws Exception;

	/**
	 * Fetch one or more records from Scene table for specified criteria
	 *
	 * @param Scene	The Scene entity
	 *
	 * @return 	Returns the list of Scene	{@link List}
	 */
	public List<Scene> fetchSceneRecords( Scene scene ) throws Exception;

	/**
	 * Fetch T&C page URL
	 *
	 * @param None
	 *
	 * @return 	Returns T&C page URL {@link String}
	 */
	public String getTCPage() throws Exception;

	/**
	 * fetchSceneUserReport
	 * @param  
	 * 
	 * @return 	 Returns the list of Scene 	 {@link List<Scene> }
	 */
	public List<Scene> fetchSceneUserReport() throws Exception;

	
	public List<Scene> fetchSceneFeedbackReport() throws Exception;

	/**
	 * Update  SceneHistory table for update operation.
	 * @param Scene	The Scene entity
	 *
	 * @return 	Returns the updated count for record inserted into history table	{@link Integer }
	 */
	//public Integer updateSceneHistoryTblForUpdatedRecord( Scene scene );

	/**
	 * Update  SceneHistory table for delete operation
	 * @param SceneList	The list of Scene entity
	 *
	 * @return 	Returns the updated count for record(s) inserted into history table	{@link Integer }
	 */
	//public Integer updateSceneHistoryTblForDeletedRecord( List<Scene> sceneList );

	/**
	 * Fetches all the records from Scene table for specified Ids
	 *
	 * @return 	Returns the list of Scene	{@link List}
	 */
	//public List<Scene> fetchAllSceneRecordsByIds(List<Scene> sceneList);

}