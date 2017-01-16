/**
 * Created on 21 Jan, 2015
 */

package com.whispers.beans;

// java imports
import java.sql.Timestamp;

/**
 * @author anka technology solutions private limited
 *
 * Scene Comments entity
 */
public class SceneComments {
	/**
	 * Scene Id
	 */
	private Integer id = null;

	/**
	 * Date comments given
	 */
	private Timestamp dateCommentSubmitted		= null;

	/**
	 * sceneid
	 */
	private Integer sceneId		= null;
	
	/**
	 * userid
	 */
	private Integer submittedById		= null;

	/**
	 * user's name
	 */
	private String submittedByName		= null;

	/**
	 * Text (max 150 chars)
	 */
	private String comment		= null;

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
	public SceneComments() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param id		The Id
	 * @param dateCommentSubmitted		The Date comments given
	 * @param sceneId		The sceneId
	 * @param submittedById		The userid
	 * @param submittedByName		The user's name
	 * @param comment		The Text (max 150 chars)
	 * @param modifiedOn		The Modified On(Date)
	 * @param modifiedBy		The Modified By(userId)
	 * @param action		The Action
	 * @param createdBy		The Created By(userId)
	 * @param createdOn		The Created On(Date)
	 * @param status		The Status (Active or Inactive)
	 */
	public SceneComments(
					Integer id,
					Timestamp dateCommentSubmitted,
					Integer sceneId,
					Integer submittedById,
					String submittedByName,
					String comment,
					Timestamp modifiedOn,
					Integer modifiedBy,
					String action,
					Integer createdBy,
					Timestamp createdOn,
					Integer status
				) {
		this.id		= id;
		this.dateCommentSubmitted		= dateCommentSubmitted;
		this.sceneId		= sceneId;
		this.submittedById		= submittedById;
		this.submittedByName		= submittedByName;
		this.comment		= comment;
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
	 * Gets the value of the dateCommentSubmitted property.
	 *
	 * @return 	 Returns the dateCommentSubmitted 	 {@link Timestamp }
	 */
	public Timestamp getDateCommentSubmitted() {
		return dateCommentSubmitted;
	}

	/**
	 * Sets the value of the dateCommentSubmitted property.
	 *
	 * @param dateCommentSubmitted 	 The dateCommentSubmitted to set 	 {@link Timestamp }
	 */
	public void setDateCommentSubmitted(Timestamp dateCommentSubmitted) {
		this.dateCommentSubmitted = dateCommentSubmitted;
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
	 * Sets the value of the a property.
	 *
	 * @param sceneId 	The sceneId to set 	{@link Integer }
	 */
	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
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
	 * @param submittedByName 	 The submittedByName to set 	 {@link String}
	 */
	public void setSubmittedByName(String submittedByName) {
		this.submittedByName = submittedByName;
	}

	/**
	 * Gets the value of the comment property.
	 *
	 * @return 	 Returns the comment 	 {@link String }
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the value of the comment property.
	 *
	 * @param comment 	 The comment to set 	 {@link String }
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * Returns the String representation of the SceneComments entity.
	 *
	 * @return 	 the string containing the SceneComments details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("id =" + id + "|");
		strBufTemp.append ("Date comments given =" + dateCommentSubmitted + "|");
		strBufTemp.append ("scene id =" + sceneId + "|");		
		strBufTemp.append ("userid =" + submittedById + "|");
		strBufTemp.append ("username =" + submittedByName + "|");
		strBufTemp.append ("Text (max 150 chars) =" + comment + "|");
		strBufTemp.append ("Modified On(Date) =" + modifiedOn + "|");
		strBufTemp.append ("Modified By(userId) =" + modifiedBy + "|");
		strBufTemp.append ("Action =" + action + "|");
		strBufTemp.append ("Created By(userId) =" + createdBy + "|");
		strBufTemp.append ("Created On(Date) =" + createdOn + "|");
		strBufTemp.append ("Status (Active or Inactive) =" + status);

		return strBufTemp.toString();
	}

}