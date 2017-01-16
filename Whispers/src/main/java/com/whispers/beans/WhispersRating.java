/**
 * Created on 21 Jan, 2015
 */

package com.whispers.beans;

// java imports
import java.sql.Timestamp;

/**
 * @author anka technology solutions private limited
 *
 * Whispers Rating entity
 */
public class WhispersRating {
	/**
	 * Whisper Id
	 */
	private Integer id		= null;

	/**
	 * Date Rating Given
	 */
	private Timestamp dateRatingSubmitted		= null;

	/**
	 * Dramaturg userid
	 */
	private Integer submittedById		= null;

	/**
	 * Rating (Numeric 1 to 10)
	 */
	private Integer rating		= null;

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
	public WhispersRating() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param id		The Whisper Id
	 * @param dateRatingSubmitted		The Date Rating Given
	 * @param submittedById		The Dramaturg userid
	 * @param rating		The Rating (Numeric 1 to 10)
	 * @param modifiedOn		The Modified On(Date)
	 * @param modifiedBy		The Modified By(userId)
	 * @param action		The Action
	 * @param createdBy		The Created By(userId)
	 * @param createdOn		The Created On(Date)
	 * @param status		The Status (Active or Inactive)
	 */
	public WhispersRating(
					Integer id,
					Timestamp dateRatingSubmitted,
					Integer submittedById,
					Integer rating,
					Timestamp modifiedOn,
					Integer modifiedBy,
					String action,
					Integer createdBy,
					Timestamp createdOn,
					Integer status
				) {
		this.id		= id;
		this.dateRatingSubmitted		= dateRatingSubmitted;
		this.submittedById		= submittedById;
		this.rating		= rating;
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
	 * Gets the value of the dateRatingSubmitted property.
	 *
	 * @return 	 Returns the dateRatingSubmitted 	 {@link Timestamp }
	 */
	public Timestamp getDateRatingSubmitted() {
		return dateRatingSubmitted;
	}

	/**
	 * Sets the value of the dateRatingSubmitted property.
	 *
	 * @param dateRatingSubmitted 	 The dateRatingSubmitted to set 	 {@link Timestamp }
	 */
	public void setDateRatingSubmitted(Timestamp dateRatingSubmitted) {
		this.dateRatingSubmitted = dateRatingSubmitted;
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
	 * Returns the String representation of the WhispersRating entity.
	 *
	 * @return 	 the string containing the WhispersRating details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("Whisper Id =" + id + "|");
		strBufTemp.append ("Date Rating Given =" + dateRatingSubmitted + "|");
		strBufTemp.append ("Dramaturg userid =" + submittedById + "|");
		strBufTemp.append ("Rating (Numeric 1 to 10) =" + rating + "|");
		strBufTemp.append ("Modified On(Date) =" + modifiedOn + "|");
		strBufTemp.append ("Modified By(userId) =" + modifiedBy + "|");
		strBufTemp.append ("Action =" + action + "|");
		strBufTemp.append ("Created By(userId) =" + createdBy + "|");
		strBufTemp.append ("Created On(Date) =" + createdOn + "|");
		strBufTemp.append ("Status (Active or Inactive) =" + status);

		return strBufTemp.toString();
	}

}