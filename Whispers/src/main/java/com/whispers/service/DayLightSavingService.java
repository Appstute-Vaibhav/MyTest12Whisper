/**
 * Created on 15 Jan, 2015
 */

package com.whispers.service;

// java imports
// spring framework imports
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whispers.beans.DayLightSaving;
import com.whispers.dao.DayLightSavingDAO;
import com.whispers.utils.Log;
// application imports

/**
 * @author anka technology solutions private limited
 *
 * Service class implements business logic as well as delegates CRUD operations to DAO
 */

@Service
public class DayLightSavingService {

	/**
	 * The DAO class instance
	 */
	@Autowired
	private DayLightSavingDAO dayLightSavingDAO;

	/**
	 * Sets server time as per Day light saving ON / OFF
	 * 
	 * @param dayLightSavingData The DayLightSaving entity
	 *
	 * @return 	 Returns none	 
	 */
	public void dayLightSavingSettings(DayLightSaving dayLightSavingData) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering dayLightSavingSettings()...");

		Log.logMessage("INFO", this.getClass().getName(), "dayLightSaving data : "+ dayLightSavingData.toString());

		// Call dao to add question record
		dayLightSavingDAO.dayLightSavingSettings(dayLightSavingData);
		
		Date now = new Date(System.currentTimeMillis());
		Date newTime = null;
		if(Boolean.parseBoolean(dayLightSavingData.getDayLightSavingOn())){
			
			newTime = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1));
			Log.logMessage("WARN", this.getClass().getName(), "********Day Light Saving mode ON.. Time changed from " + now + " to " + newTime);
			
		}else {
			newTime = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1));
			Log.logMessage("WARN", this.getClass().getName(), "********Day Light Saving mode OFF.. Time changed from " + now + " to " + newTime);
		}
		
		String[] cmd = {"/bin/bash","-c","echo password| sudo -S date --set '" + newTime + "'"};
		Process processStatus = Runtime.getRuntime().exec(cmd);
		Thread.sleep(5000);        
		System.out.println("********processStatus exit value == " + processStatus.exitValue());		
		Log.logMessage("INFO", this.getClass().getName(), "Exiting dayLightSavingSettings(): Update day Light Saving Setting call successfull... new DATETIME ===  " + new Date());

	} //dayLightSavingSettings() method ends

	/**
	 * Invokes service method to fetch a record.
	 * 
	 * @param none
	 *
	 * @return 	 Returns the DayLightSaving 	 {@link DayLightSaving }
	 * @throws Exception 
	 */
	public DayLightSaving fetchDayLightSavingSettings() throws Exception {
		
		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchDayLightSavingSettings()...");
		DayLightSaving dayLightSaving = null; 
		// Call dao to fetch record
		dayLightSaving = dayLightSavingDAO.fetchDayLightSavingSettings();

		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchDayLightSavingSettings(): Fetch record call successfull...");

		return dayLightSaving;

	} //fetchFeedbackQuestionRecord() method ends
	

}