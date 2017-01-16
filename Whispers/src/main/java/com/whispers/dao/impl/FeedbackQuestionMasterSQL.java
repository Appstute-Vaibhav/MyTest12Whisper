/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to FeedbackQuestion table.
 */
public class FeedbackQuestionMasterSQL { 
	
	/**
	 * Insert query for the table.
	 * Total fields in the table=8. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT_SQL = "INSERT INTO FeedbackQuestion (Question, ModifiedOn, ModifiedBy, CreatedBy, CreatedOn, Status, ParentQuestionId) VALUES (:question, now(), :modifiedBy, :createdBy, now(), :status, :parentQuestionId)";

	/**
	 * Insert query for the table.
	 * Total fields in the table=5. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT2_SQL = "INSERT INTO UserFeedback (QuestionId, OptionId, UserId, SceneId, CreatedOn, Status) VALUES (:questionId, :optionId, :userId, :sceneId, now(), 1)";
	
	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "UPDATE FeedbackQuestion SET Question = :question, ModifiedOn = now(), ModifiedBy = :modifiedBy WHERE Id = :id";

	/**
	 * Delete query to delete one or more records in a table.
	 */
	public static final String DELETE_SQL = "UPDATE FeedbackQuestion f1 INNER JOIN (select count(Id) as count From FeedbackQuestion where ParentQuestionId = ? and Status = 1) f2 Set Status = 0 where f1.Id = ? and f2.count = 0";

	/**
	 * Select query to delete all records from the table.
	 */
	
	
	
	/**
	 * Select query to retrieve all records from the table.
	 */
	public static final String SELECTALL_SQL = "SELECT * FROM FeedbackQuestion WHERE Status=1";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECT_SQL = "SELECT * FROM FeedbackQuestion WHERE Id in(?,?)";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTALLCRITERIA_SQL = "SELECT * FROM  FeedbackQuestion WHERE  if( ? is not null, Id = ?, true) and if( ? is not null, Question = ?, true) and if( ? is not null, ModifiedOn = ?, true) and if( ? is not null, ModifiedBy = ?, true) and if( ? is not null, CreatedBy = ?, true) and Status = 1";

	/**
	 * Insert query for the table.
	 * Total fields in the table=8. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT1_SQL = "INSERT INTO UserFeedback (UserId, QuestionId, OptionId,SceneId,CreatedOn) VALUES (:userId ,:questionId ,:optionId ,:sceneId ,now())";
}// Class ends