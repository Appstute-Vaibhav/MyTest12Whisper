/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to SceneComments table.
 */
public class SceneCommentsMasterSQL { 

	/**
	 * Insert query for the table.
	 * Total fields in the table=10. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT_SQL = "INSERT INTO SceneComments (DateCommentSubmitted, SceneId, SubmittedById, Comment, ModifiedOn, ModifiedBy, CreatedBy, CreatedOn, Status) VALUES (:dateCommentSubmitted, :sceneId, :submittedById, :comment, now(), :modifiedBy, :createdBy, now(), :status)";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "UPDATE SceneComments SET DateCommentSubmitted = :dateCommentSubmitted, SubmittedById = :submittedById, Comment = :comment, ModifiedOn = now(), ModifiedBy = :modifiedBy WHERE Id = :id";

	/**
	 * Delete query to delete one or more records in a table.
	 */
	public static final String DELETE_SQL = "UPDATE SceneComments SET Status=0 WHERE Id in( ? )";

	/**
	 * Select query to retrieve all records from the table.
	 */
	public static final String SELECTALL_SQL = "SELECT concat(user.firstName,' ',user.lastName)SubmittedByName, comments.* FROM SceneComments comments, User user WHERE comments.Status=1 and comments.SubmittedById = user.Id";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECT_SQL = "SELECT concat(user.firstName,' ',user.lastName)SubmittedByName, comments.* FROM SceneComments comments, User user WHERE comments.Id = ? and comments.Status=1 and comments.SubmittedById = user.Id";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTALLCRITERIA_SQL = "SELECT concat(user.firstName,' ',user.lastName)SubmittedByName, comments.* FROM  SceneComments comments, User user WHERE  if( ? is not null, comments.Id = ?, true) and if( ? is not null, comments.DateCommentSubmitted like concat(date(?), '%'), true) and if( ? is not null, comments.SceneId = ?, true) and if( ? is not null, comments.SubmittedById = ?, true) and if( ? is not null, comments.Comment = ?, true) and if( ? is not null, comments.ModifiedOn = ?, true) and if( ? is not null, comments.ModifiedBy = ?, true) and if( ? is not null, comments.CreatedBy = ?, true) and comments.Status = 1 and comments.SubmittedById = user.id";

}// Class ends