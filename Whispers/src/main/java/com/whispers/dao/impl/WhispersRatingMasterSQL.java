/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to WhispersRating table.
 */
public class WhispersRatingMasterSQL { 

	/**
	 * Insert query for the table.
	 * Total fields in the table=10. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT_SQL = "INSERT INTO WhispersRating (Id, DateRatingSubmitted, SubmittedById, Rating, ModifiedOn, ModifiedBy, CreatedBy, CreatedOn, Status) VALUES (:id, now(), :submittedById, :rating, now(), :modifiedBy, :createdBy, now(), :status)";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "UPDATE WhispersRating SET DateRatingSubmitted = :dateRatingSubmitted, SubmittedById = :submittedById, Rating = :rating, ModifiedOn = now(), ModifiedBy = :modifiedBy WHERE Id = :id";

	/**
	 * Delete query to delete one or more records in a table.
	 */
	public static final String DELETE_SQL = "UPDATE WhispersRating SET Status=0 WHERE Id in( ? )";

	/**
	 * Select query to retrieve all records from the table.
	 */
	public static final String SELECTALL_SQL = "SELECT * FROM WhispersRating WHERE Status=1";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECT_SQL = "SELECT * FROM WhispersRating WHERE Id = ? and Status=1";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTALLCRITERIA_SQL = "SELECT * FROM  WhispersRating WHERE  if( ? is not null, Id = ?, true) and if( ? is not null, DateRatingSubmitted like concat(date(?), '%'), true) and if( ? is not null, SubmittedById = ?, true) and if( ? is not null, Rating = ?, true) and if( ? is not null, ModifiedOn = ?, true) and if( ? is not null, ModifiedBy = ?, true) and if( ? is not null, CreatedBy = ?, true) and Status = 1";

}// Class ends