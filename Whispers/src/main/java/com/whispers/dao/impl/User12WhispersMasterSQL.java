/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to User12Whispers table.
 */
public class User12WhispersMasterSQL { 

	/**
	 * Insert query for the table.
	 * Total fields in the table=11. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT_SQL = "INSERT INTO User12Whispers (SceneId, DateWhispersSubmitted, SubmittedById, Whispers, ModifiedOn, ModifiedBy, CreatedBy, CreatedOn, Status) VALUES (:sceneId, :dateWhispersSubmitted, :submittedById, :whispers, now(), :modifiedBy, :createdBy, now(), :status)";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "UPDATE User12Whispers SET SceneId = :sceneId, DateWhispersSubmitted = :dateWhispersSubmitted, SubmittedById = :submittedById, Whispers = :whispers, ModifiedOn = (), ModifiedBy = :modifiedBy WHERE Id = :id";

	/**
	 * Delete query to delete one or more records in a table.
	 */
	public static final String DELETE_SQL = "UPDATE User12Whispers SET Status=0 WHERE Id in( ? )";

	/**
	 * Select query to retrieve all records from the table.
	 */
	public static final String SELECTALL_SQL = "select userwhispers.*,(WEEK(sysdate()))CurrentWeekId From(select (Year(whispers.DateWhispersSubmitted))year, (WEEK(whispers.DateWhispersSubmitted))WeekId,rating.rating,whispers.* from (select whispers.*, user.FirstName,user.LastName, user.Email,scene.SceneTitle from User12Whispers whispers, User user, Scene scene Where  whispers.SubmittedById = user.Id and whispers.SceneId = scene.Id and whispers.Status = 1) whispers left outer join (select * from WhispersRating where SubmittedById = ?)rating on whispers.Id = rating.Id)userwhispers join (select (WEEK(CreatedOn))WeekId,(Year(CreatedOn))year from UserRoleMapping where UserId = ? and RoleId = ?)user on userwhispers.WeekId >= user.WeekId and userwhispers.year >= user.year order by SceneId"; 												

	/**
	 * Select query to retrieve all records from the table.
	 */
	public static final String SELECT_BY_OWNER_SQL = "select whispers.*, user.FirstName, user.LastName, user.Email , scene.SceneTitle from User12Whispers whispers, User user, Scene scene Where  whispers.SubmittedById = user.Id and whispers.SceneId = scene.Id and whispers.Status = 1 and scene.SubmittedBy = ?";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECT_SQL = "SELECT * FROM User12Whispers WHERE Id = ? and Status=1";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTALLCRITERIA_SQL = "SELECT * FROM  User12Whispers WHERE  if( ? is not null, Id = ?, true) and if( ? is not null, SceneId = ?, true) and if( ? is not null, DateWhispersSubmitted like concat(date(?), '%'), true) and if( ? is not null, SubmittedById = ?, true) and if( ? is not null, Whispers = ?, true) and if( ? is not null, ModifiedOn = ?, true) and if( ? is not null, ModifiedBy = ?, true) and if( ? is not null, CreatedBy = ?, true) and Status = 1";

}// Class ends