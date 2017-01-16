/**
 * Created on 15 Jan, 2015
 */

package com.whispers.beans;

// java imports
import java.sql.Timestamp;

/**
 * @author anka technology solutions private limited
 *
 * Scene entity
 */
public class DayLightSaving {
	/**
	 * isDayLightSavingOn
	 */
	private String dayLightSavingOn		= null;

	/**
	 * Modified On(Date)
	 */
	private Timestamp modifiedOn		= null;

	/**
	 * Modified By(userId)
	 */
	private Integer modifiedBy		= null;

	/**
	 * Created By(userId)
	 */
	private Integer createdBy		= null;

	/**
	 * Created On(Date)
	 */
	private Timestamp createdOn		= null;

	/**
	 * Default constructor
	 */
	public DayLightSaving() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param isDayLightSavingOn		is Day Light Saving ON or OFF
	 * @param modifiedOn		The Modified On(Date)
	 * @param modifiedBy		The Modified By(userId)
	 * @param createdBy		The Created By(userId)
	 * @param createdOn		The Created On(Date)
	 */
	public DayLightSaving(
			String dayLightSavingOn,
					Timestamp modifiedOn,
					Integer modifiedBy,
					Integer createdBy,
					Timestamp createdOn
				) {
		this.dayLightSavingOn		= dayLightSavingOn;
		this.modifiedOn		= modifiedOn;
		this.modifiedBy		= modifiedBy;
		this.createdBy		= createdBy;
		this.createdOn		= createdOn;
	}


	/**
	 * Gets the value of the isDayLightSavingOn property.
	 *
	 * @return 	 Returns the isDayLightSavingOn 	 {@link Integer }
	 */
	public String getDayLightSavingOn() {
		return dayLightSavingOn;
	}

	/**
	 * Sets the value of the isDayLightSavingOn property.
	 *
	 * @param id 	 The isDayLightSavingOn to set 	 {@link Integer }
	 */
	public void setDayLightSavingOn(String dayLightSavingOn) {
		this.dayLightSavingOn = dayLightSavingOn;
	}

	/**
	 * Gets the value of the modifiedOn property.
	 *
	 * @return 	 Returns the modifiedOn 	 {@link Timestamp }
	 */
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * Sets the value of the modifiedOn property.
	 *
	 * @param modifiedOn 	 The modifiedOn to set 	 {@link Timestamp }
	 */
	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * Gets the value of the modifiedBy property.
	 *
	 * @return 	 Returns the modifiedBy 	 {@link Integer }
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the value of the modifiedBy property.
	 *
	 * @param modifiedBy 	 The modifiedBy to set 	 {@link Integer }
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets the value of the createdBy property.
	 *
	 * @return 	 Returns the createdBy 	 {@link Integer }
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the value of the createdBy property.
	 *
	 * @param createdBy 	 The createdBy to set 	 {@link Integer }
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the value of the createdOn property.
	 *
	 * @return 	 Returns the createdOn 	 {@link Timestamp }
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the value of the createdOn property.
	 *
	 * @param createdOn 	 The createdOn to set 	 {@link Timestamp }
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Returns the String representation of the Scene entity.
	 *
	 * @return 	 the string containing the Scene details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("dayLightSavingOn =" + dayLightSavingOn + "|");
		strBufTemp.append ("Modified On(Date) =" + modifiedOn + "|");
		strBufTemp.append ("Modified By(userId) =" + modifiedBy + "|");
		strBufTemp.append ("Created By(userId) =" + createdBy + "|");
		strBufTemp.append ("Created On(Date) =" + createdOn + "|");

		return strBufTemp.toString();
	}

}