/**
 * Created on 15 Jan, 2015
 */

package com.whispers.beans;

// java imports
import java.sql.Timestamp;

/**
 * @author anka technology solutions private limited
 *
 * Sponsor card entity
 */
public class SponsorCard {
	/**
	 * Sponsor card id
	 */
	private Integer id		= null;

	/**
	 * Sponsored By
	 */
	private String sponsoredBy		= null;

	/**
	 * Sponsor Card Description
	 */
	private String cardDescription		= null;

	/**
	 * Uploaded Date
	 */
	private Timestamp dateUploaded		= null;

	/**
	 * Sponsor Card Image
	 */
	private String cardImage		= null;

	/**
	 * Redirected URI
	 */
	private String sponsorURI		= null;

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
	 * Action
	 */
	private String action		= null;

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
	public SponsorCard() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param id		The Sponsor card id
	 * @param sponsoredBy		The Sponsored By
	 * @param cardDescription		The Sponsor Card Description
	 * @param dateUploaded		The Uploaded Date
	 * @param cardImage		The Sponsor Card Image
	 * @param sponsorURI		The Redirected URI
	 * @param modifiedOn		The Modified On(Date)
	 * @param modifiedBy		The Modified By(userId)
	 * @param createdBy		The Created By(userId)
	 * @param action		The Action
	 * @param createdOn		The Created On(Date)
	 * @param status		The Status (Active or Inactive)
	 */
	public SponsorCard(
					Integer id,
					String sponsoredBy,
					String cardDescription,
					Timestamp dateUploaded,
					String cardImage,
					String sponsorURI,
					Timestamp modifiedOn,
					Integer modifiedBy,
					Integer createdBy,
					String action,
					Timestamp createdOn,
					Integer status
				) {
		this.id		= id;
		this.sponsoredBy		= sponsoredBy;
		this.cardDescription		= cardDescription;
		this.dateUploaded		= dateUploaded;
		this.cardImage		= cardImage;
		this.sponsorURI		= sponsorURI;
		this.modifiedOn		= modifiedOn;
		this.modifiedBy		= modifiedBy;
		this.createdBy		= createdBy;
		this.action		= action;
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
	 * Gets the value of the sponsoredBy property.
	 *
	 * @return 	 Returns the sponsoredBy 	 {@link String }
	 */
	public String getSponsoredBy() {
		return sponsoredBy;
	}

	/**
	 * Sets the value of the sponsoredBy property.
	 *
	 * @param sponsoredBy 	 The sponsoredBy to set 	 {@link String }
	 */
	public void setSponsoredBy(String sponsoredBy) {
		this.sponsoredBy = sponsoredBy;
	}

	/**
	 * Gets the value of the cardDescription property.
	 *
	 * @return 	 Returns the cardDescription 	 {@link String }
	 */
	public String getCardDescription() {
		return cardDescription;
	}

	/**
	 * Sets the value of the cardDescription property.
	 *
	 * @param cardDescription 	 The cardDescription to set 	 {@link String }
	 */
	public void setCardDescription(String cardDescription) {
		this.cardDescription = cardDescription;
	}

	/**
	 * Gets the value of the dateUploaded property.
	 *
	 * @return 	 Returns the dateUploaded 	 {@link Timestamp }
	 */
	public Timestamp getDateUploaded() {
		return dateUploaded;
	}

	/**
	 * Sets the value of the dateUploaded property.
	 *
	 * @param dateUploaded 	 The dateUploaded to set 	 {@link Timestamp }
	 */
	public void setDateUploaded(Timestamp dateUploaded) {
		this.dateUploaded = dateUploaded;
	}

	/**
	 * Gets the value of the cardImage property.
	 *
	 * @return 	 Returns the cardImage 	 {@link String }
	 */
	public String getCardImage() {
		return cardImage;
	}

	/**
	 * Sets the value of the cardImage property.
	 *
	 * @param cardImage 	 The cardImage to set 	 {@link String }
	 */
	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}

	/**
	 * Gets the value of the sponsorURI property.
	 *
	 * @return 	 Returns the sponsorURI 	 {@link String }
	 */
	public String getSponsorURI() {
		return sponsorURI;
	}

	/**
	 * Sets the value of the sponsorURI property.
	 *
	 * @param sponsorURI 	 The sponsorURI to set 	 {@link String }
	 */
	public void setSponsorURI(String sponsorURI) {
		this.sponsorURI = sponsorURI;
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
	 * Returns the String representation of the SponsorCard entity.
	 *
	 * @return 	 the string containing the SponsorCard details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("Sponsor card id =" + id + "|");
		strBufTemp.append ("Sponsored By =" + sponsoredBy + "|");
		strBufTemp.append ("Sponsor Card Description =" + cardDescription + "|");
		strBufTemp.append ("Uploaded Date =" + dateUploaded + "|");
		strBufTemp.append ("Sponsor Card Image =" + cardImage + "|");
		strBufTemp.append ("Redirected URI =" + sponsorURI + "|");
		strBufTemp.append ("Modified On(Date) =" + modifiedOn + "|");
		strBufTemp.append ("Modified By(userId) =" + modifiedBy + "|");
		strBufTemp.append ("Created By(userId) =" + createdBy + "|");
		strBufTemp.append ("Action =" + action + "|");
		strBufTemp.append ("Created On(Date) =" + createdOn + "|");
		strBufTemp.append ("Status (Active or Inactive) =" + status);

		return strBufTemp.toString();
	}

}