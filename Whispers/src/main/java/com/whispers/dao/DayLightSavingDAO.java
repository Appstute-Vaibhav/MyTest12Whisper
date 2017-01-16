/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao;

// java imports
import com.whispers.beans.DayLightSaving;
// application imports

/**
 * @author anka technology solutions private limited
 *
 * An interface to perform CRUD operations on Feedback Question master
 */
public interface DayLightSavingDAO {
	/**
	 * Sets server time as per Day light saving ON / OFF
	 * 
	 * @param dayLightSavingData The DayLightSaving entity
	 *
	 * @return 	 Returns none
	 * @throws Exception 
	 */
	public void dayLightSavingSettings( DayLightSaving dayLightSavingData ) throws Exception;
	
	/**
	 * Fetches a single record from Constants table
	 * @param none
	 *
	 * @return 	Returns the DayLightSaving	{@link DayLightSaving }
	 */
	public DayLightSaving fetchDayLightSavingSettings ( ) throws Exception;


}