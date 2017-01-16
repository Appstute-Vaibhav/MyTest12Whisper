/**
 * Created on 15 Jan, 2015
 */

package com.whispers.dao.impl;

// java imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

// application imports
import com.whispers.beans.DayLightSaving;
import com.whispers.dao.DayLightSavingDAO;
import com.whispers.utils.Log;
// spring framework imports

/**
 * @author anka technology solutions private limited
 *
 */

@Repository
public class DayLightSavingDAOImpl extends TransactionService implements DayLightSavingDAO {

	
	/**
	 * Sets server time as per Day light saving ON / OFF
	 * 
	 * @param dayLightSavingData The DayLightSaving entity
	 *
	 * @return 	 Returns none	 
	 */
	@Override
	public void dayLightSavingSettings(DayLightSaving dayLightSavingData ) throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering dayLightSavingSettings()...");

		Log.logMessage("INFO", this.getClass().getName(), "UPDATE query = " + DayLightSavingMasterSQL.UPDATE_SQL);

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(dayLightSavingData);
		getNamedParameterJdbcTemplate().update(DayLightSavingMasterSQL.UPDATE_SQL, paramSource);
			
		Log.logMessage("INFO", this.getClass().getName(), "Exiting dayLightSavingSettings(): Record updated successfully: " + dayLightSavingData.toString());


	} //dayLightSavingSettings() method ends
	
	/**
	 * Fetches a single record from Constants table.
	 * @param none
	 *
	 * @return 	 Returns the DayLightSaving 	 {@link DayLightSaving }
	 */
	@Override
	public DayLightSaving fetchDayLightSavingSettings() throws Exception {

		Log.logMessage("INFO", this.getClass().getName(), "Entering fetchDayLightSavingSettings()...");

		DayLightSaving dayLightSaving = null;
		List<DayLightSaving> dayLightSavingList = null;
		
		Log.logMessage("INFO", this.getClass().getName(), "SELECT Single Record query = " + DayLightSavingMasterSQL.SELECT_SQL);
			
		dayLightSavingList = getJdbcTemplate().query( DayLightSavingMasterSQL.SELECT_SQL, new Object[] { }, new ResultSetExtractor<List<DayLightSaving>>() {
			
			List<DayLightSaving> dayLightSavingTempList = new ArrayList<DayLightSaving>();
				
			@Override
			public List<DayLightSaving> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				DayLightSaving dayLightSavingTemp = null;
					
				while(rs.next()) {
					dayLightSavingTemp = new DayLightSaving();
				
					dayLightSavingTemp.setDayLightSavingOn(rs.getString("Value"));
					
					dayLightSavingTempList.add(dayLightSavingTemp);						
				}

					return dayLightSavingTempList;
				}

			});

		if(dayLightSavingList.size() > 0){
			dayLightSaving = dayLightSavingList.get(0);
		}else {
			dayLightSaving = null;
		}
		Log.logMessage("INFO", this.getClass().getName(), "Exiting fetchDayLightSavingSettings(): Record fetched successfully: " + dayLightSaving.toString());

		return dayLightSaving;

	} //fetchDayLightSavingSettings() method ends

}