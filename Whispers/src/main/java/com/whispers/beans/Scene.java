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
public class Scene {
	/**
	 * Scene Id
	 */
	private Integer id		= null;

	/**
	 * Scene Title
	 */
	private String sceneTitle		= null;

	/**
	 * Scene Title
	 */
	private String authorName		= null;	
	
	/**
	 * Submition Date
	 */
	private Timestamp dateSubmitted		= null;

	/**
	 * isSubmitted
	 */
	private Integer isSubmitted		= null;

	/**
	 * publish Date
	 */
	private Timestamp publishDate		= null;

	/**
	 * Submitted By(userId)
	 */
	private Integer submittedBy		= null;

	/**
	 * Submitted By Name(userName)
	 */
	private String submittedByName		= null;

	/**
	 * Scene Image
	 */
	private String sceneImage		= null;

	/**
	 * Scene Upload Type
	 */
	private String sceneUploadType		= null;

	/**
	 * Scene Short Description
	 */
	private String sceneShortText		= null;

	/**
	 * Scene Description
	 */
	private String sceneDescription		= null;

	/**
	 * Scene Notification
	 */
	private String notificationText		= null;

	/**
	 * Scene Time and Place
	 */
	private String timePlace		= null;

	/**
	 * Is Comment Allowed
	 */
	private Integer allowComments		= null;

	/**
	 * Is scene published
	 */
	private Integer scenePublished		= null;

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
	public Scene() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param id		The Scene Id
	 * @param sceneTitle		The Scene Title
	 * @param dateSubmitted		The Submition Date
	 * @param isSubmitted		is Submitted
	 * @param submittedBy		The Submitted By(userId)
	 * @param submittedByName		The Submitted By Name(userName)
	 * @param sceneImage		The Scene Image
	 * @param sceneUploadType		The Scene Upload Type
	 * @param sceneShortText		The Scene Short Description
	 * @param sceneDescription		The Scene Description
	 * @param timePlace		The Scene Time and Place
	 * @param allowComments		The Is Comment Allowed
	 * @param modifiedOn		The Modified On(Date)
	 * @param modifiedBy		The Modified By(userId)
	 * @param action		The Action
	 * @param createdBy		The Created By(userId)
	 * @param createdOn		The Created On(Date)
	 * @param status		The Status (Active or Inactive)
	 */
	public Scene(
					Integer id,
					String sceneTitle,
					String authorName,
					Timestamp dateSubmitted,
					Integer isSubmitted,
					Timestamp publishDate,
					Integer submittedBy,
					String submittedByName,
					String sceneImage,
					String sceneUploadType,
					String sceneShortText,
					String sceneDescription,
					String notificationText,
					String timePlace,
					Integer allowComments,
					Integer scenePublished,
					Timestamp modifiedOn,
					Integer modifiedBy,
					String action,
					Integer createdBy,
					Timestamp createdOn,
					Integer status
				) {
		this.id		= id;
		this.sceneTitle		= sceneTitle;
		this.authorName     = authorName;
		this.dateSubmitted		= dateSubmitted;
		this.isSubmitted		= isSubmitted;
		this.publishDate		= publishDate;
		this.submittedBy		= submittedBy;
		this.submittedByName	= submittedByName;
		this.sceneImage		= sceneImage;
		this.sceneUploadType		= sceneUploadType;
		this.sceneShortText		= sceneShortText;
		this.sceneDescription		= sceneDescription;
		this.notificationText		= notificationText;
		this.timePlace		= timePlace;
		this.allowComments		= allowComments;
		this.scenePublished		= scenePublished;
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
	 * Gets the value of the dateSubmitted property.
	 *
	 * @return 	 Returns the dateSubmitted 	 {@link Timestamp }
	 */
	
	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * Sets the value of the dateSubmitted property.
	 *
	 * @param dateSubmitted 	 The dateSubmitted to set 	 {@link Timestamp }
	 */
	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	/**
	 * Gets the value of the isSubmitted property.
	 *
	 * @return 	 Returns the isSubmitted 	 {@link Integer }
	 */
	public Integer getIsSubmitted() {
		return isSubmitted;
	}

	/**
	 * Sets the value of the isSubmitted property.
	 *
	 * @param isSubmitted 	 The isSubmitted to set 	 {@link Integer }
	 */
	public void setIsSubmitted(Integer isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	/**
	 * Gets the value of the published date property.
	 *
	 * @return 	 Returns the published date 	 {@link Timestamp }
	 */
	public Timestamp getPublishDate() {
		return publishDate;
	}

	/**
	 * Sets the value of the publishDate property.
	 *
	 * @param dateSubmitted 	 The publishDate to set 	 {@link Timestamp }
	 */
	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * Gets the value of the submittedBy property.
	 *
	 * @return 	 Returns the submittedBy 	 {@link Integer }
	 */
	public Integer getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * Sets the value of the submittedBy property.
	 *
	 * @param submittedBy 	 The submittedBy to set 	 {@link Integer }
	 */
	public void setSubmittedBy(Integer submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * Gets the value of the submittedByName property.
	 *
	 * @return 	 Returns the submittedByName 	 {@link String }
	 */
	public String getSubmittedByName() {
		return submittedByName;
	}

	/**
	 * Sets the value of the submittedByName property.
	 *
	 * @param sceneImage 	 The submittedByName to set 	 {@link String }
	 */
	public void setSubmittedByName(String submittedByName) {
		this.submittedByName = submittedByName;
	}

	/**
	 * Gets the value of the sceneImage property.
	 *
	 * @return 	 Returns the sceneImage 	 {@link String }
	 */
	public String getSceneImage() {
		return sceneImage;
	}

	/**
	 * Sets the value of the sceneImage property.
	 *
	 * @param sceneImage 	 The sceneImage to set 	 {@link String }
	 */
	public void setSceneImage(String sceneImage) {
		this.sceneImage = sceneImage;
	}

	/**
	 * Gets the value of the sceneUploadType property.
	 *
	 * @return 	 Returns the sceneUploadType 	 {@link String }
	 */
	public String getSceneUploadType() {
		return sceneUploadType;
	}

	/**
	 * Sets the value of the sceneUploadType property.
	 *
	 * @param sceneUploadType 	 The sceneUploadType to set 	 {@link String }
	 */
	public void setSceneUploadType(String sceneUploadType) {
		this.sceneUploadType = sceneUploadType;
	}

	/**
	 * Gets the value of the sceneShortText property.
	 *
	 * @return 	 Returns the sceneShortText 	 {@link String }
	 */
	public String getSceneShortText() {
		return sceneShortText;
	}

	/**
	 * Sets the value of the sceneShortText property.
	 *
	 * @param sceneShortText 	 The sceneShortText to set 	 {@link String }
	 */
	public void setSceneShortText(String sceneShortText) {
		this.sceneShortText = sceneShortText;
	}

	/**
	 * Gets the value of the sceneDescription property.
	 *
	 * @return 	 Returns the sceneDescription 	 {@link String }
	 */
	public String getSceneDescription() {
		return sceneDescription;
	}

	/**
	 * Sets the value of the sceneDescription property.
	 *
	 * @param sceneDescription 	 The sceneDescription to set 	 {@link String }
	 */
	public void setSceneDescription(String sceneDescription) {
		this.sceneDescription = sceneDescription;
	}


	/**
	 * Gets the value of the notificationText property.
	 *
	 * @return 	 Returns the notificationText 	 {@link String }
	 */
	public String getNotificationText() {
		return notificationText;
	}

	/**
	 * Sets the value of the notificationText property.
	 *
	 * @param notificationText 	 The notificationText to set 	 {@link String }
	 */
	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}
	
	/**
	 * Gets the value of the timePlace property.
	 *
	 * @return 	 Returns the timePlace 	 {@link String }
	 */
	public String getTimePlace() {
		return timePlace;
	}

	/**
	 * Sets the value of the timePlace property.
	 *
	 * @param sceneDescription 	 The timePlace to set 	 {@link String }
	 */
	public void setTimePlace(String timePlace) {
		this.timePlace = timePlace;
	}

	/**
	 * Gets the value of the allowComments property.
	 *
	 * @return 	 Returns the allowComments 	 {@link Integer }
	 */
	public Integer getAllowComments() {
		return allowComments;
	}

	/**
	 * Sets the value of the allowComments property.
	 *
	 * @param allowComments 	 The allowComments to set 	 {@link Integer }
	 */
	public void setAllowComments(Integer allowComments) {
		this.allowComments = allowComments;
	}


	/**
	 * Sets the value of the scenePublished property.
	 *
	 * @param scenePublished 	 The scenePublished to set 	 {@link Integer }
	 */
	public void setScenePublished(Integer scenePublished) {
		this.scenePublished = scenePublished;
	}

	/**
	 * Gets the value of the scenePublished property.
	 *
	 * @return 	 Returns the scenePublished {@link Integer }
	 */
	public Integer getScenePublished() {
		return scenePublished;
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
	 * Returns the String representation of the Scene entity.
	 *
	 * @return 	 the string containing the Scene details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("Scene Id =" + id + "|");
		strBufTemp.append ("Scene Title =" + sceneTitle + "|");
		strBufTemp.append ("Submition Date =" + dateSubmitted + "|");
		strBufTemp.append ("Is Submitted =" + isSubmitted + "|");
		strBufTemp.append ("Publish Date =" + publishDate + "|");
		strBufTemp.append ("Submitted By(userId) =" + submittedBy + "|");
		strBufTemp.append ("Submitted By Name (userName) =" + submittedByName + "|");
		//strBufTemp.append ("Scene Image =" + sceneImage + "|");
		strBufTemp.append ("Scene Upload Type =" + sceneUploadType + "|");
		strBufTemp.append ("Scene Short Description =" + sceneShortText + "|");
		strBufTemp.append ("Scene Description =" + sceneDescription + "|");
		strBufTemp.append ("Time and Place =" + timePlace + "|");
		strBufTemp.append ("Is Comment Allowed =" + allowComments + "|");
		strBufTemp.append ("Modified On(Date) =" + modifiedOn + "|");
		strBufTemp.append ("Modified By(userId) =" + modifiedBy + "|");
		strBufTemp.append ("Action =" + action + "|");
		strBufTemp.append ("Created By(userId) =" + createdBy + "|");
		strBufTemp.append ("Created On(Date) =" + createdOn + "|");
		strBufTemp.append ("Status (Active or Inactive) =" + status);

		return strBufTemp.toString();
	}

}