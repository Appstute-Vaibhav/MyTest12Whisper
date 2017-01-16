/**
 * Created on 15 Jan, 2015
 */

package com.whispers.beans;

// java imports
import java.sql.Timestamp;
import java.util.List;

/**
 * @author anka technology solutions private limited
 *
 * Feedback Question entity
 */
public class FeedbackQuestion {
	/**
	 * Question Id
	 */
	private Integer id		= null;

	/**
	 * Question Description
	 */
	private String question		= null;

	/**
	 * Modified By(userId)
	 */
	private Integer parentQuestionId = null;

	/**
	 * Answer options data
	 */
	private List<FeedbackQuestionOption> feedbackQuestionOptions		= null;

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
	public FeedbackQuestion() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param id		The Question Id
	 * @param parentQuestionId  The Parent Question Id
	 * @param question		The Question Description
	 * @param feedbackQuestionOptions		The Answer options
	 * @param modifiedOn		The Modified On(Date)
	 * @param modifiedBy		The Modified By(userId)
	 * @param action		The Action
	 * @param createdBy		The Created By(userId)
	 * @param createdOn		The Created On(Date)
	 * @param status		The Status (Active or Inactive)
	 */
	public FeedbackQuestion(
					Integer id,
					Integer parentQuestionId,
					String question,
					List<FeedbackQuestionOption> feedbackQuestionOptions,
					Timestamp modifiedOn,
					Integer modifiedBy,
					String action,
					Integer createdBy,
					Timestamp createdOn,
					Integer status
				) {
		this.id		= id;
		this.parentQuestionId = parentQuestionId;
		this.question		= question;
		this.feedbackQuestionOptions		= feedbackQuestionOptions;
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
	 * Gets the value of the question property.
	 *
	 * @return 	 Returns the question 	 {@link String }
	 */
	public String getQuestion() {
		return question;
	}

	/**Question
	 * Sets the value of the question property.
	 *
	 * @param question 	 The question to set 	 {@link String }
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	
	/**
	 * Gets the value of the parentQuestionId property.
	 *
	 * @return 	 Returns the parentQuestionId 	 {@link Integer }
	 */
	
	
	public Integer getParentQuestionId() {
		return parentQuestionId;
	}
	
	/**Question
	 * Sets the value of the parentQuestionId property.
	 *
	 * @param question 	 The parentQuestionId to set 	 {@link Integer }
	 */
	
	public void setParentQuestionId(Integer parentQuestionId) {
		this.parentQuestionId = parentQuestionId;
	}

	/**
	 * Gets the value of the Option's list property.
	 *
	 * @return 	 Returns the option's list  	 {@link List<FeedbackQuestionOption>  }
	 */
	public List<FeedbackQuestionOption> getFeedbackQuestionOptions() {
		return feedbackQuestionOptions;
	}

	/**
	 * Sets the value of the Option's list property.
	 *
	 * @param question 	 The Option's list to set 	 {@link List<FeedbackQuestionOption>  }
	 */
	public void setFeedbackQuestionOptions(List<FeedbackQuestionOption>  feedbackQuestionOptions) {
		this.feedbackQuestionOptions = feedbackQuestionOptions;
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
	 * Returns the String representation of the FeedbackQuestion entity.
	 *
	 * @return 	 the string containing the FeedbackQuestion details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("Question Id =" + id + "|");
		strBufTemp.append ("Parent Question Id =" + parentQuestionId + "|");
		strBufTemp.append ("Question Description =" + question + "|");
		//strBufTemp.append ("Option's List size =" + feedbackQuestionOptions.size() + "|");
		strBufTemp.append ("Modified On(Date) =" + modifiedOn + "|");
		strBufTemp.append ("Modified By(userId) =" + modifiedBy + "|");
		strBufTemp.append ("Action =" + action + "|");
		strBufTemp.append ("Created By(userId) =" + createdBy + "|");
		strBufTemp.append ("Created On(Date) =" + createdOn + "|");
		strBufTemp.append ("Status (Active or Inactive) =" + status);

		return strBufTemp.toString();
	}

}