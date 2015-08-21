package com.junrui.logreport.bean;

/** 
* @ClassName: UserStatus 
* @Description: TODO(用户状态改变日志) 
* @author ytai
* @date 2015年8月20日 上午11:30:51 
*  
*/ 
public class UserStatus {
	
	/** 
	* @Fields userId : TODO(用户id) 
	*/ 
	private String userId;
	
	/** 
	* @Fields userId : TODO(手机号码) 
	*/ 
	private String phoneNum;
	
	/** 
	* @Fields wxOpenId : TODO(用户wx的openid) 
	*/ 
	private String wxOpenId;
	
	/** 
	* @Fields wxOpenId : TODO(用户QQ的openid) 
	*/ 
	private String qqOpenId;
	
	/** 
	* @Fields wxOpenId : TODO(用户微博的openid) 
	*/ 
	private String wbOpenId;
	
	
	/** 
	* @Fields userId : TODO(用户类型（1：大众 2：医生）) 
	*/ 
	private int userType;
	
	/** 
	* @Fields wxOpenId : TODO(状态变更时间,14位) 
	*/ 
	private String changeTime;
	
	/** 
	* @Fields wxOpenId : TODO(状态变更原因) 
	*/ 
	private String reason;
	
	/** 
	* @Fields wxOpenId : TODO(变更前状态) 
	*/ 
	private int beforeStatus;
	
	/** 
	* @Fields wxOpenId : TODO(变更后状态) 
	*/ 
	private int afterStatus;
	
	/** 
	* @Fields wxOpenId : TODO(修改状态的操作员id，默认为系统修改，置为0) 
	*/ 
	private int operatorId;
	
	

	public UserStatus() {
	}

	public UserStatus(String userId, String phoneNum, String wxOpenId, String qqOpenId, String wbOpenId, int userType,
			String changeTime, String reason, int beforeStatus, int afterStatus, int operatorId) {
		super();
		this.userId = userId;
		this.phoneNum = phoneNum;
		this.wxOpenId = wxOpenId;
		this.qqOpenId = qqOpenId;
		this.wbOpenId = wbOpenId;
		this.userType = userType;
		this.changeTime = changeTime;
		this.reason = reason;
		this.beforeStatus = beforeStatus;
		this.afterStatus = afterStatus;
		this.operatorId = operatorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	public String getWbOpenId() {
		return wbOpenId;
	}

	public void setWbOpenId(String wbOpenId) {
		this.wbOpenId = wbOpenId;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getBeforeStatus() {
		return beforeStatus;
	}

	public void setBeforeStatus(int beforeStatus) {
		this.beforeStatus = beforeStatus;
	}

	public int getAfterStatus() {
		return afterStatus;
	}

	public void setAfterStatus(int afterStatus) {
		this.afterStatus = afterStatus;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	
	
}
