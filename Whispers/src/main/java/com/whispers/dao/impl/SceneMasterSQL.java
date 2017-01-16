/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to Scene table.
 */
public class SceneMasterSQL { 
	
	/**
	 * Insert query for the table.
	 * Total fields in the table=2.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	
	public static final String INSERT1_SQL = "INSERT INTO whispers.PushNotification (DeviceName ,DeviceToken ) VALUES(:sceneTitle, :authorName)";

	/**
	 * Insert query for the table.
	 * Total fields in the table=15. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT_SQL = "INSERT INTO whispers.Scene (SceneTitle ,AuthorName ,DateSubmitted, IsSubmitted, SubmittedBy, SceneImage, SceneUploadType, SceneShortText, SceneDescription, TimePlace, AllowComments, ScenePublished, NotificationText, PublishDate, ModifiedOn, ModifiedBy, CreatedBy, CreatedOn, Status) VALUES(:sceneTitle, :authorName, :dateSubmitted, :isSubmitted, :submittedBy, :sceneImage, :sceneUploadType, :sceneShortText, :sceneDescription, :timePlace, :allowComments, :scenePublished, :notificationText, :publishDate, now(), :modifiedBy, :createdBy, now(), :status)";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "UPDATE Scene SET SceneTitle = if( :sceneTitle  is not null, :sceneTitle ,SceneTitle), AuthorName = if( :authorName  is not null, :authorName ,AuthorName), DateSubmitted = if( :dateSubmitted  is not null, :dateSubmitted ,DateSubmitted), IsSubmitted = if( :isSubmitted  is not null, :isSubmitted ,IsSubmitted), SubmittedBy = if( :submittedBy  is not null, :submittedBy ,SubmittedBy), SceneImage = if( :sceneImage  is not null, :sceneImage ,SceneImage), SceneUploadType = if( :sceneUploadType  is not null, :sceneUploadType ,SceneUploadType), SceneShortText = if( :sceneShortText is not null, :sceneShortText,SceneShortText), SceneDescription = if( :sceneDescription  is not null, :sceneDescription ,SceneDescription), TimePlace = if( :timePlace  is not null, :timePlace ,TimePlace), AllowComments = if( :allowComments  is not null, :allowComments ,AllowComments), ScenePublished = if( :scenePublished is not null, :scenePublished,ScenePublished), NotificationText = if( :notificationText  is not null, :notificationText ,NotificationText),PublishDate = :publishDate, ModifiedOn = now(), ModifiedBy = :modifiedBy WHERE Id = :id";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE1_SQL = "UPDATE Scene SET ScenePublished = 1 WHERE PublishDate = :publishDate";

	
	/**
	 * Delete query to delete one or more records in a table.
	 */
	public static final String DELETE_SQL = "UPDATE Scene SET Status=0, modifiedBy=? WHERE Id in( ? )";

	/**
	 * Select query to retrieve all records from the table.
	 */
	public static final String SELECTALL_SQL = "SELECT concat(user.FirstName, ' ', user.LastName) as SubmittedByName, scene.* FROM Scene scene, User user WHERE scene.Status=1 AND scene.SubmittedBy = user.Id and (submittedBy = ? or IsSubmitted)";

	/**
	 * Select query to retrieve all records from the table.
	 */
	public static final String SELECT_BY_OWNER_SQL = "SELECT concat(user.FirstName, ' ', user.LastName) as SubmittedByName, scene.* FROM Scene scene, User user WHERE scene.Status=1 AND scene.SubmittedBy = user.Id AND scene.SubmittedBy = ?";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECT_SQL = "Select * from (SELECT concat(user.FirstName, ' ', user.LastName) as SubmittedByName, scene.* FROM Scene scene, User user WHERE scene.Id = ? and scene.Status=1 AND scene.SubmittedBy = user.Id) q1 left outer join(select (Id)SceneId, NotificationText, PublishDate from Notification where status = 1)q2 on q1.Id = q2.SceneId";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTALLCRITERIA_SQL = "SELECT  concat(user.FirstName, ' ', user.LastName) as SubmittedByName, scene.* FROM  Scene scene, User user WHERE  if( ? is not null, scene.Id = ?, true) and if( ? is not null, scene.SceneTitle = ?, true) and if( ? is not null, scene.DateSubmitted like concat(date(?), '%'), true) and if( ? is not null, scene.SubmittedBy = ?, true) and if( ? is not null, scene.SceneImage = ?, true) and if( ? is not null, scene.SceneUploadType = ?, true) and if( ? is not null, scene.SceneShortText = ?, true) and if( ? is not null, scene.SceneDescription = ?, true) and if( ? is not null, scene.timePlace = ?, true) and if( ? is not null, scene.AllowComments = ?, true) and if( ? is not null, scene.ModifiedOn = ?, true) and if( ? is not null, scene.ModifiedBy = ?, true) and if( ? is not null, scene.CreatedBy = ?, true) and if( ? is not null, scene.ScenePublished = ?, true) and if( ? is not null, scene.PublishDate like concat(date(?), '%'), true) and scene.Status = 1 AND scene.ScenePublished = 1 AND scene.SubmittedBy = user.Id";

	/**
	 * Select query to retrieve TC page link record from the table.
	 */
	public static final String SELECT_TCPAGE_SQL = "SELECT * FROM Constants WHERE `Key` = ?";
	

	/**
	 * Select query to retrieve report data from the table.
	 */
	public static final String SELECT_SCENE_USER_SQL = "select scene.Id, scene.DateSubmitted, scene.SceneTitle, concat(user.FirstName, ' ', user.LastName)submittedByName from User12Whispers whisper, Scene scene, User user where whisper.SceneId = scene.Id and whisper.SubmittedById = user.Id";

	
	/**
	 * Select query to retrieve report data from the table.
	 */
	public static final String SELECT_FEEDBACK_USER_SQL = "call FeedbackReport()";

	
	/**
	 * Select query to retrieve device data from the table.
	 */
	public static final String SELECT_DEVICE_DETAILS_SQL = "select * from PushNotification";

	
}// Class ends