/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to SponsorCard table.
 */
public class SponsorCardMasterSQL { 

	/**
	 * Insert query for the table.
	 * Total fields in the table=12. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT_SQL = "INSERT INTO SponsorCard (SponsoredBy, CardDescription, DateUploaded, CardImage, SponsorURI, ModifiedOn, ModifiedBy, CreatedBy, CreatedOn, Status) VALUES (:sponsoredBy, :cardDescription, :dateUploaded, :cardImage, :sponsorURI, now(), :modifiedBy, :createdBy, now(), :status)";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "UPDATE SponsorCard SET SponsoredBy = :sponsoredBy, CardDescription = :cardDescription, DateUploaded = :dateUploaded, CardImage = :cardImage, SponsorURI = :sponsorURI, ModifiedOn = now(), ModifiedBy = :modifiedBy WHERE Id = :id";

	/**
	 * Delete query to delete one or more records in a table.
	 */
	public static final String DELETE_SQL = "UPDATE SponsorCard SET Status=0 WHERE Id in( ? )";

	/**
	 * Select query to retrieve all records from the table.
	 */
	public static final String SELECTALL_SQL = "SELECT * FROM SponsorCard WHERE Status=1";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECT_SQL = "SELECT * FROM SponsorCard WHERE Id = ? and Status=1";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTALLCRITERIA_SQL = "SELECT * FROM  SponsorCard WHERE  if( ? is not null, Id = ?, true) and if( ? is not null, SponsoredBy = ?, true) and if( ? is not null, CardDescription = ?, true) and if( ? is not null, DateUploaded like concat(date(?), '%'), true) and if( ? is not null, CardImage = ?, true) and if( ? is not null, SponsorURI = ?, true) and if( ? is not null, ModifiedOn = ?, true) and if( ? is not null, ModifiedBy = ?, true) and if( ? is not null, CreatedBy = ?, true) and Status = 1";

}// Class ends