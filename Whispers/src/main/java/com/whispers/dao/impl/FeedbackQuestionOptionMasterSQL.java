/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to FeedbackQuestionOptions table.
 */
public class FeedbackQuestionOptionMasterSQL { 

	/**
	 * Insert query for the table.
	 * Total fields in the table=8. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT_SQL = "INSERT INTO FeedbackQuestionOptions(Id,QuestionId,OptionLabel,OptionValue) VALUES(?, ?, ?, ?)";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "INSERT INTO FeedbackQuestionOptions(Id,QuestionId,OptionLabel,OptionValue) VALUES(?, ?, ?, ?) ON DUPLICATE KEY UPDATE Id = ?,QuestionId = ?,OptionLabel = ?,OptionValue = ? ";

	/**
	 * Update query for the table.
	 */
	public static final String DELETE_SQL = "DELETE FROM FeedbackQuestionOptions WHERE QuestionId in( ? )";

	
	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTALLCRITERIA_SQL = "SELECT * FROM  FeedbackQuestionOptions WHERE if( ? is not null, QuestionId = ?, true)";

}// Class ends