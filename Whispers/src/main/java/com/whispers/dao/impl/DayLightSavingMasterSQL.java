/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

/**
 * @author anka technology solutions private limited
 *
 * SQL queries related to FeedbackQuestion table.
 */
public class DayLightSavingMasterSQL { 
	
	/**
	 * Select query for the table.
	 */
	public static final String SELECT_SQL = "SELECT * from Constants WHERE `Key` = 'dayLightSaving'";

	/**
	 * Update query for the table.
	 */
	public static final String UPDATE_SQL = "UPDATE Constants SET Value = :dayLightSavingOn WHERE `Key` = 'dayLightSaving'";

	
}// Class ends