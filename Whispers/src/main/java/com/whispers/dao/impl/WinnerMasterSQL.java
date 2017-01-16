/**
 * Created on 21 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to Winner table.
 */
public class WinnerMasterSQL { 

	/**
	 * Insert query for the table.
	 * Total fields in the table=11. No need to pass the value for CreatedOn date. Status field to be passed if record needs to be softdeleted.
	 * Values are passed in order of columns present in the table.
	 * Table has a primary key(id).
	 */
	public static final String INSERT_SQL = "INSERT INTO Winner (SceneTitle, DateScenePublished, WinnerId, WinnerName, ModifiedOn, ModifiedBy, CreatedBy, CreatedOn, Status) VALUES (:sceneTitle, :dateScenePublished, :winnerId, :winnerName, now(), :modifiedBy, :createdBy, now(), :status)";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "UPDATE Winner SET SceneTitle = :sceneTitle, DateScenePublished = :dateScenePublished, WinnerId = :winnerId, WinnerName = :winnerName, ModifiedOn = now(), ModifiedBy = :modifiedBy WHERE Id = :id";

	/**
	 * Delete query to delete one or more records in a table.
	 */
	public static final String DELETE_SQL = "UPDATE Winner SET Status=0 WHERE Id in( ? )";

	/**
	 * Select query to retrieve all records from the table.
	 */
	public static final String SELECTALL_SQL = "SELECT scn.Id, wnr.SceneTitle, wnr.DateScenePublished, wnr.WinnerId, wnr.WinnerName,wnr.CreatedOn, wnr.CreatedBy, wnr.ModifiedBy, wnr.ModifiedOn, (WEEK(wnr.DateScenePublished)+1)WeekId,	STR_TO_DATE(concat(yearweek(wnr.DateScenePublished),' Monday'), '%X%V %W')StartDate,STR_TO_DATE(concat(yearweek(wnr.DateScenePublished),' Friday'), '%X%V %W')EndDate, wnr.Status FROM Winner wnr , Scene scn WHERE wnr.Status=1 and DATE(wnr.DateScenePublished) = DATE(scn.PublishDate)";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECT_SQL = "SELECT * FROM Winner WHERE Id = ? and Status=1";

	/**
	 * Select query to retrieve single record from the table.
	 */
	public static final String SELECTALLCRITERIA_SQL = "SELECT scn.Id, wnr.SceneTitle, wnr.DateScenePublished, wnr.WinnerId,wnr.WinnerName,wnr.CreatedOn, wnr.CreatedBy, wnr.ModifiedBy, wnr.ModifiedOn,(WEEK(wnr.DateScenePublished)+1)WeekId,STR_TO_DATE(concat(yearweek(wnr.DateScenePublished),' Monday'), '%X%V %W')StartDate,STR_TO_DATE(concat(yearweek(wnr.DateScenePublished),' Friday'), '%X%V %W')EndDate,wnr.Status FROM Winner wnr,Scene scn WHERE wnr.Status=1 and DATE(wnr.DateScenePublished) = DATE(scn.PublishDate) and DATE(wnr.DateScenePublished) IN (curdate() - INTERVAL DAYOFWEEK(curdate()+1) DAY,curdate() - INTERVAL DAYOFWEEK(curdate()+2) DAY,curdate() - INTERVAL DAYOFWEEK(curdate()+3) DAY,curdate() - INTERVAL DAYOFWEEK(curdate()+4) DAY,curdate() - INTERVAL DAYOFWEEK(curdate()+5) DAY)group by wnr.DateScenePublished";
	
	/**
	 * Select query to retrieve single record from the table.
	 */	
	public static final String SELECTCURRENT_SQL = "call PublishWinnerProc(?)";

}// Class ends