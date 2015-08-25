package com.junrui.logreport.bean;

/** 
* @ClassName: CommonHeadMessage 
* @Description: TODO(通用消息头部信息) 
* @author ytai
* @date 2015年8月20日 上午11:15:43 
*  
*/ 
public class CommonHeadMessage {
	
	/** 
	* @Fields userAgent : 用户手机ua信息
	*/ 
	public String userAgent;
	
	/** 
	* @Fields userId : TODO(用户id) 
	*/ 
	private String userId;
	
	/** 
	* @Fields userId : TODO(手机号码) 
	*/ 
	private String phoneNum;
	
	/** 
	* @Fields userId : TODO(用户类型（1：大众 2：医生）) 
	*/ 
	private String userType;
	
	/** 
	* @Fields userId : TODO(手机imei) 
	*/ 
	private String imei;
	
	/** 
	* @Fields imsi : TODO(手机imsi) 
	*/ 
	private String imsi;
	
	/** 
	* @Fields userId : TODO(设备系统（1：ios 2：android）) 
	*/ 
	private String deviceOS;
	
	/** 
	* @Fields userId : TODO(设备系统版本) 
	*/ 
	private String deviceVersion;
	
	/** 
	* @Fields userId : TODO(客户端系统版本（1：ios 2：android）) 
	*/ 
	private String clientOS;
	
	/** 
	* @Fields userId : TODO(客户端版本) 
	*/ 
	private String clientVersion;
	
	/** 
	* @Fields userId : TODO(客户端类型（1：大众版 2：医生版本）) 
	*/ 
	private String clientType;
	
	/** 
	* @Fields userId : TODO(sessionId) 
	*/ 
	private String sessIonId;
	
	/** 
	* @Fields userId : TODO(渠道id) 
	*/ 
	private String channelId;
	
	/** 
	* @Fields wxOpenId : TODO(微信openId) 
	*/ 
	private String wxOpenId;
	
	/** 
	* @Fields qqOpenId : TODO(QQOpenId) 
	*/ 
	private String qqOpenId;
	
	/** 
	* @Fields wbOpenId : TODO(微博openId) 
	*/ 
	private String wbOpenId;

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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getDeviceOS() {
		return deviceOS;
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getClientOS() {
		return clientOS;
	}

	public void setClientOS(String clientOS) {
		this.clientOS = clientOS;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getSessIonId() {
		return sessIonId;
	}

	public void setSessIonId(String sessIonId) {
		this.sessIonId = sessIonId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
	
	


	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public CommonHeadMessage()
	{
	}

	public CommonHeadMessage(String userAgent, String userId, String phoneNum, String userType, String imei,
			String imsi, String deviceOS, String deviceVersion, String clientOS, String clientVersion,
			String clientType, String sessIonId, String channelId, String wxOpenId, String qqOpenId, String wbOpenId) {
		super();
		this.userAgent = userAgent;
		this.userId = userId;
		this.phoneNum = phoneNum;
		this.userType = userType;
		this.imei = imei;
		this.imsi = imsi;
		this.deviceOS = deviceOS;
		this.deviceVersion = deviceVersion;
		this.clientOS = clientOS;
		this.clientVersion = clientVersion;
		this.clientType = clientType;
		this.sessIonId = sessIonId;
		this.channelId = channelId;
		this.wxOpenId = wxOpenId;
		this.qqOpenId = qqOpenId;
		this.wbOpenId = wbOpenId;
	}
	

	
	
	
}
