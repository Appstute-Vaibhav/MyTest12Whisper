/**
 * Created on 21 Jan, 2015
 */

package com.whispers.beans;

// java imports
import java.sql.Timestamp;

/**
 * @author anka technology solutions private limited
 *
 * User 12Whispers entity
 */
public class User12Whispers extends User {
	/**
	 * Whisper Id
	 */
	private Integer id		= null;

	/**
	 * Scene Id
	 */
	private Integer sceneId		= null;

	/**
	 * Rating
	 */
	private Integer rating		= null;

	/**
	 * Scene Name
	 */
	private String sceneTitle		= null;

	/**
	 * Date whispers submitted
	 */
	private Timestamp dateWhispersSubmitted		= null;

	/**
	 * userid
	 */
	private Integer submittedById		= null;

	/**
	 * 12 whispers separated by line Break character
	 */
	private String whispers		= null;

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
	public User12Whispers() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param id		The Whisper Id
	 * @param sceneId		The Scene Id
	 * @param rating		The Rating
	 * @param dateWhispersSubmitted		The Date whispers submitted
	 * @param submittedById		The userid
	 * @param whispers		The 12 whispers separated by line Break character
	 * @param modifiedOn		The Modified On(Date)
	 * @param modifiedBy		The Modified By(userId)
	 * @param action		The Action
	 * @param createdBy		The Created By(userId)
	 * @param createdOn		The Created On(Date)
	 * @param status		The Status (Active or Inactive)
	 */
	public User12Whispers(
					Integer id,
					Integer sceneId,
					Integer rating,
					String sceneTitle,
					Timestamp dateWhispersSubmitted,
					Integer submittedById,
					String whispers,
					Timestamp modifiedOn,
					Integer modifiedBy,
					String action,
					Integer createdBy,
					Timestamp createdOn,
					Integer status
				) {
		this.id		= id;
		this.sceneId		= sceneId;
		this.rating		= rating;
		this.sceneTitle		= sceneTitle;
		this.dateWhispersSubmitted		= dateWhispersSubmitted;
		this.submittedById		= submittedById;
		this.whispers		= whispers;
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
	 * Gets the value of the sceneId property.
	 *
	 * @return 	 Returns the sceneId 	 {@link Integer }
	 */
	public Integer getSceneId() {
		return sceneId;
	}

	/**
	 * Sets the value of the sceneId property.
	 *
	 * @param sceneId 	 The sceneId to set 	 {@link Integer }
	 */
	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	/**
	 * Gets the value of the rating property.
	 *
	 * @return 	 Returns the rating 	 {@link Integer }
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * Sets the value of the rating property.
	 *
	 * @param rating 	 The rating to set 	 {@link Integer }
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
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
	 * Gets the value of the dateWhispersSubmitted property.
	 *
	 * @return 	 Returns the dateWhispersSubmitted 	 {@link Timestamp }
	 */
	public Timestamp getDateWhispersSubmitted() {
		return dateWhispersSubmitted;
	}

	/**
	 * Sets the value of the dateWhispersSubmitted property.
	 *
	 * @param dateWhispersSubmitted 	 The dateWhispersSubmitted to set 	 {@link Timestamp }
	 */
	public void setDateWhispersSubmitted(Timestamp dateWhispersSubmitted) {
		this.dateWhispersSubmitted = dateWhispersSubmitted;
	}

	/**
	 * Gets the value of the submittedById property.
	 *
	 * @return 	 Returns the submittedById 	 {@link Integer }
	 */
	public Integer getSubmittedById() {
		return submittedById;
	}

	/**
	 * Sets the value of the submittedById property.
	 *
	 * @param submittedById 	 The submittedById to set 	 {@link Integer }
	 */
	public void setSubmittedById(Integer submittedById) {
		this.submittedById = submittedById;
	}

	/**
	 * Gets the value of the whispers property.
	 *
	 * @return 	 Returns the whispers 	 {@link String }
	 */
	public String getWhispers() {
		return whispers;
	}

	/**
	 * Sets the value of the whispers property.
	 *
	 * @param whispers 	 The whispers to set 	 {@link String }
	 */
	public void setWhispers(String whispers) {
		this.whispers = whispers;
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

	/**
	 * Returns the String representation of the User12Whispers entity.
	 *
	 * @return 	 the string containing the User12Whispers details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("Whisper Id =" + id + "|");
		strBufTemp.append ("Scene Id =" + sceneId + "|");
		strBufTemp.append ("Rating =" + rating + "|");
		strBufTemp.append ("Scene Name =" + sceneTitle + "|");
		strBufTemp.append ("Date whispers submitted =" + dateWhispersSubmitted + "|");
		strBufTemp.append ("userid =" + submittedById + "|");
		strBufTemp.append ("12 whispers separated by line Break character =" + whispers + "|");
		strBufTemp.append ("Modified On(Date) =" + modifiedOn + "|");
		strBufTemp.append ("Modified By(userId) =" + modifiedBy + "|");
		strBufTemp.append ("Action =" + action + "|");
		strBufTemp.append ("Created By(userId) =" + createdBy + "|");
		strBufTemp.append ("Created On(Date) =" + createdOn + "|");
		strBufTemp.append ("Status (Active or Inactive) =" + status);

		return strBufTemp.toString();
	}

}