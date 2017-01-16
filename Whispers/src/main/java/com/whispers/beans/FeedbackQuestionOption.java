/**
 * Created on 15 Jan, 2015
 */

package com.whispers.beans;

// java imports

/**
 * @author anka technology solutions private limited
 *
 * Feedback Question's Option entity
 */
public class FeedbackQuestionOption {
	/**
	 * Feedback Question's Option Id
	 */
	private Integer id		= null;

	/**
	 * Question Id
	 */
	private Integer questionId		= null;

	/**
	 * Option Label
	 */
	private String optionLabel		= null;

	/**
	 * The Actual Option Value to be displayed
	 */
	private String optionValue		= null;

	/**
	 * Default constructor
	 */
	public FeedbackQuestionOption() {

	}

	/**
	 * Overloaded constructor
	 *
	 * @param id		The Option Id
	 * @param questionId		The Question Id
	 * @param optionLabel		The Option Label
	 * @param optionValue		The Actual Option Value to be displayed
	 */
	public FeedbackQuestionOption(
					Integer id,
					Integer questionId,
					String optionLabel,
					String optionValue
				) {
		this.id		= id;
		this.questionId		= questionId;
		this.optionLabel		= optionLabel;
		this.optionValue		= optionValue;
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
	 * Gets the value of the questionId property.
	 *
	 * @return 	 Returns the question id 	 {@link Integer }
	 */
	public Integer getQuestionId() {
		return questionId;
	}

	/**
	 * Sets the value of the question id property.
	 *
	 * @param question 	 The question id to set 	 {@link Integer }
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	/**
	 * Gets the value of the Feedback Question's Option Label property.
	 *
	 * @return 	 Returns the Feedback Question's Option Label 	 {@link String}
	 */
	public String getOptionLabel() {
		return optionLabel;
	}

	/**
	 * Sets the value of the Feedback Question's Option Label property.
	 *
	 * @param question 	 The Feedback Question's Option Label to set 	 {@link String }
	 */
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}

	/**
	 * Gets the value of the Feedback Question's Option value property.
	 *
	 * @return 	 Returns the Feedback Question's Option value 	 {@link String}
	 */
	public String getOptionValue() {
		return optionValue;
	}

	/**
	 * Sets the value of the Feedback Question's Option Value property.
	 *
	 * @param question 	 The Feedback Question's Option Value to set 	 {@link String }
	 */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	/**
	 * Returns the String representation of the FeedbackQuestion entity.
	 *
	 * @return 	 the string containing the FeedbackQuestion details.
	 */
	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("FeedBackQuestionOption Id =" + id + "|");
		strBufTemp.append ("Question Id =" + questionId + "|");
		strBufTemp.append ("Option Label =" + optionLabel + "|");
		strBufTemp.append ("Option Value =" + optionValue);

		return strBufTemp.toString();
	}

}