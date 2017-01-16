package com.whispers.beans;

import java.sql.Timestamp;

public class UserFeedback {
	
	private Integer id = null;

	private Integer userId = null;

	private Integer questionId = null;

	private Integer optionId = null;

	private Integer sceneId = null;
	
	private Timestamp createdOn = null;

	public UserFeedback(){
		
	}	
	
	public UserFeedback(Integer userId,Integer questionId,Integer optionId,Timestamp createdOn){
		this.userId = userId;
		this.questionId = questionId;
		this.optionId = optionId;
		this.createdOn = createdOn;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}	
	
	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String toString() {
		StringBuffer strBufTemp =  new StringBuffer();

		strBufTemp.append ("user Id =" + userId + "|");
		strBufTemp.append ("question Id=" + questionId + "|");
		strBufTemp.append ("option Id =" + optionId + "|");
		strBufTemp.append ("scene Id =" + sceneId + "|");
		strBufTemp.append ("Created On(Date) =" + createdOn + "|");
		
		return strBufTemp.toString();
	}

}
