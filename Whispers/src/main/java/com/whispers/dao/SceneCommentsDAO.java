/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao;

// java imports
import java.util.List;







// application imports
import com.whispers.beans.SceneComments;

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on Scene Comments master
 */
public interface SceneCommentsDAO {
	/**
	 * Inserts a record in SceneComments table.
	 * @param SceneComments	The SceneComments entity
	 *
	 * @return 	Returns the SceneComments	{@link SceneComments }
	 */
	public SceneComments addSceneCommentsRecord( SceneComments sceneComments ) throws Exception;

	/**
	 * Updates record in SceneComments table.
	 * @param SceneComments	The SceneComments entity
	 *
	 * @return 	Returns the SceneComments	{@link SceneComments }
	 */
	public SceneComments updateSceneCommentsRecord( SceneComments sceneComments ) throws Exception;

	/**
	 * Deletes a record from SceneComments table.
	 * @param SceneCommentsList	The list of SceneComments entity
	 *
	 * @return 	Returns the deleted count of record(s) 	{@link List}
	 */
	public Integer deleteSceneCommentsRecord( final List<SceneComments> deleteSceneCommentsRecords) throws Exception;

	/**
	 * Fetches a single record from SceneComments table
	 * @param SceneComments	The SceneComments entity
	 *
	 * @return 	Returns the SceneComments	{@link SceneComments }
	 */
	public SceneComments fetchSceneCommentsRecord( SceneComments sceneComments ) throws Exception;

	/**
	 * Fetches all the records from SceneComments table
	 *
	 * @return 	Returns the list of SceneComments	{@link List}
	 */
	public List<SceneComments> fetchAllSceneCommentsRecord() throws Exception;

	/**
	 * Fetch one or more records from SceneComments table for specified criteria
	 *
	 * @param SceneComments	The SceneComments entity
	 *
	 * @return 	Returns the list of SceneComments	{@link List}
	 */
	public List<SceneComments> fetchSceneCommentsRecords( SceneComments sceneComments ) throws Exception;

	/**
	 * Update  SceneCommentsHistory table for update operation.
	 * @param SceneComments	The SceneComments entity
	 *
	 * @return 	Returns the updated count for record inserted into history table	{@link Integer }
	 */
	//public Integer updateSceneCommentsHistoryTblForUpdatedRecord( SceneComments sceneComments );

	/**
	 * Update  SceneCommentsHistory table for delete operation
	 * @param SceneCommentsList	The list of SceneComments entity
	 *
	 * @return 	Returns the updated count for record(s) inserted into history table	{@link Integer }
	 */
	//public Integer updateSceneCommentsHistoryTblForDeletedRecord( List<SceneComments> sceneCommentsList );

	/**
	 * Fetches all the records from SceneComments table for specified Ids
	 * @param SceneCommentsList	The list of SceneComments entity
	 *
	 * @return 	Returns the list of SceneComments	{@link List}
	 */
	//public List<SceneComments> fetchAllSceneCommentsRecordsByIds(List<SceneComments> sceneCommentsList);

}