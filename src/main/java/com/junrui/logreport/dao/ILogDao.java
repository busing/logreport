package com.junrui.logreport.dao;

import com.junrui.logreport.bean.CommonHeadMessage;
import com.junrui.logreport.bean.UserActionLog;

public interface ILogDao {

	/** 
	* @Title: saveDownload 
	* @Description: 记录下载数据 
	* @param clientOS
	* @param clientType
	* @param channelId
	* @param clientVersion
	* @return
	* @return boolean    返回类型 
	* @throws 
	*/ 
	boolean saveDownload(String clientOS, String clientType, String channelId, String clientVersion);

	/** 
	* @Title: saveUserAction 
	* @Description: 记录用户行为数据
	* @param headMessage
	* @param userActionLog
	* @return
	* @return boolean    返回类型 
	* @throws 
	*/ 
	boolean saveUserAction(CommonHeadMessage headMessage, UserActionLog userActionLog);

	/** 
	* @Title: saveUserLogin 
	* @Description: 记录用户登录日志
	* @param headMessage
	* @param lastLoginTime
	* @return
	* @return boolean    返回类型 
	* @throws 
	*/ 
	boolean saveUserLogin(CommonHeadMessage headMessage, long lastLoginTime);

	boolean savePageBrowseFile();

}