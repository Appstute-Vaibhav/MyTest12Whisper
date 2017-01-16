/**
 * Created on 21 Jan, 2015
 */

package com.whispers.beans;

// java imports
import java.sql.Timestamp;

/**
 * @author anka technology solutions private limited
 *
 * Winner entity
 */
public class Winner {
	/**
	 * Scene Id
	 */
	private Integer id		= null;

	/**
	 * Scene Title
	 */
	private String sceneTitle		= null;

	/**
	 * Date Scene Published
	 */
	private Timestamp dateScenePublished		= null;

	/**
	 * Winner(UserId)
	 */
	private Integer winnerId		= null;

	/**
	 * Winner Name(user name)
	 */
	private String winnerName		= null;

	/**
	 * Modified On(Date)
	 */
	private Timestamp modifiedOn		= null;

	/**
	 * Modified By(userId)
	 */
	private Integer modifiedBy		= null;

	/**
	 * Action
	 */
	private String action		= null;

	/**
	 * Created By(userId)
	 */
	private Integer createdBy		= null;

	/**
	 * Created On(Date)
	 */
	private Timestamp createdOn		= null;

	/**
	 * Status (Active or Inactive)
	 */
	private Integer status		= null;

	/**
	 * Default constructor
	 */
	
	private Integer weekId = null;
	
	private Timestamp startDate = null;
	
	private Timestamp endDate = null;

	private String day = null;

	
	public Winner() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param id		The Scene Id
	 * @param sceneTitle		The Scene Title
	 * @param dateScenePublished		The Date Scene Published
	 * @param winnerId		The Winner(UserId)
	 * @param winnerName		The Winner Name(user name)
	 * @param modifiedOn		The Modified On(Date)
	 * @param modifiedBy		The Modified By(userId)
	 * @param action		The Action
	 * @param createdBy		The Created By(userId)
	 * @param createdOn		The Created On(Date)
	 * @param status		The Status (Active or Inactive)
	 */
	public Winner(
					Integer id,
					String sceneTitle,
					Timestamp dateScenePublished,
					Integer winnerId,
					String winnerName,
					Timestamp modifiedOn,
					Integer modifiedBy,
					String action,
					Integer createdBy,
					Timestamp createdOn,
					Integer status
				) {
		this.id		= id;
		this.sceneTitle		= sceneTitle;
		this.dateScenePublished		= dateScenePublished;
		this.winnerId		= winnerId;
		this.winnerName		= winnerName;
		this.modifiedOn		= modifiedOn;
		this.modifiedBy		= modifiedBy;
		this.action		= action;
		this.createdBy		= createdBy;
		this.createdOn		= createdOn;
		this.status		= status;
	}


	/**
	 * Gets the value of the id property.
	 *
	 * @return 	 Returns the id 	 {@link Integer }
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param id 	 The id to set 	 {@link Integer }
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the value of the sceneTitle property.
	 *
	 * @return 	 Returns the sceneTitle 	 {@link String }
	 */
	public String getSceneTitle() {
		return sceneTitle;
	}

	/**
	 * Sets the value of the sceneTitle property.
	 *
	 * @param sceneTitle 	 The sceneTitle to set 	 {@link String }
	 */
	public void setSceneTitle(String sceneTitle) {
		this.sceneTitle = sceneTitle;
	}

	/**
	 * Gets the value of the dateScenePublished property.
	 *
	 * @return 	 Returns the dateScenePublished 	 {@link Timestamp }
	 */
	public Timestamp getDateScenePublished() {
		return dateScenePublished;
	}

	/**
	 * Sets the value of the dateScenePublished property.
	 *
	 * @param dateScenePublished 	 The dateScenePublished to set 	 {@link Timestamp }
	 */
	public void setDateScenePublished(Timestamp dateScenePublished) {
		this.dateScenePublished = dateScenePublished;
	}

	/**
	 * Gets the value of the winnerId property.
	 *
	 * @return 	 Returns the winnerId 	 {@link Integer }
	 */
	public Integer getWinnerId() {
		return winnerId;
	}

	/**
	 * Sets the value of the winnerId property.
	 *
	 * @param winnerId 	 The winnerId to set 	 {@link Integer }
	 */
	public void setWinnerId(Integer winnerId) {
		this.winnerId = winnerId;
	}

	/**
	 * Gets the value of the winnerName property.
	 *
	 * @return 	 Returns the winnerName 	 {@link String }
	 */
	public String getWinnerName() {
		return winnerName;
	}

	/**
	 * Sets the value of the winnerName property.
	 *
	 * @param winnerName 	 The winnerName to set 	 {@link String }
	 */
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
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
	 * Gets the value of the action property.
	 *
	 * @return 	 Returns the action 	 {@link String }
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the value of the action property.
	 *
	 * @param action 	 The action to set 	 {@link String }
	 */
	public void setAction(String action) {
		this.action = action;
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
	 * Gets the value of the status property.
	 *
	 * @return 	 Returns the status 	 {@link Integer }
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 *
	 * @param status 	 The status to set 	 {@link Integer }
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}	
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * Returns the String representation of the Winner entity.
	 *
	 * @return 	 the string containing the Winner details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("Scene Id =" + id + "|");
		strBufTemp.append ("Scene Title =" + sceneTitle + "|");
		strBufTemp.append ("Date Scene Published =" + dateScenePublished + "|");
		strBufTemp.append ("Winner(UserId) =" + winnerId + "|");
		strBufTemp.append ("Winner Name(user name) =" + winnerName + "|");
		strBufTemp.append ("Modified On(Date) =" + modifiedOn + "|");
		strBufTemp.append ("Modified By(userId) =" + modifiedBy + "|");
		strBufTemp.append ("Action =" + action + "|");
		strBufTemp.append ("Created By(userId) =" + createdBy + "|");
		strBufTemp.append ("Created On(Date) =" + createdOn + "|");
		strBufTemp.append ("Status (Active or Inactive) =" + status);
		strBufTemp.append ("WeekId  =" + weekId);
		strBufTemp.append ("StartDate  =" + startDate);
		strBufTemp.append ("EndDate  =" + endDate);
		strBufTemp.append ("Day  =" + day);

		return strBufTemp.toString();
	}
}