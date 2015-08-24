package com.junrui.logreport.api;

import com.junrui.logreport.bean.CommonHeadMessage;
import com.junrui.logreport.bean.UserActionLog;
import com.junrui.logreport.bean.UserStatus;

/** 
* @ClassName: LogApi 
* @Description: TODO（日志客户端接口） 
* @author ytai
* @date 2015年8月20日 上午10:42:03 
*/ 
public interface LogApi
{
	/** 
	* @Title: recodeDownload 
	* @Description: TODO(记录下载记录数据) 
	* @param clientOS 客户端系统（1：ios 2：android）
	* @param clientType 客户端类型（1：大众版 2：医生版本）
	* @param channelId 渠道id
	* @param clientVersion 客户端版本号
	* @return boolean    返回类型 boolean
	* @throws 
	*/ 
	public boolean recodeDownload(String clientOS, String clientType, String channelId, String clientVersion);
	
	
	/** 
	* @Title: recodeUserAction 
	* @Description: TODO(Description) 
	* @param headMessage 头部消息封装的对象
	* @param userActionLog 用户行为日志对象
	* @return boolean    返回类型 boolean
	* @throws 
	*/ 
	public boolean recodeUserAction(CommonHeadMessage headMessage, UserActionLog userActionLog);
	
	/** 
	* @Title: recodePageBrowseFile 
	* @Description: 上传页面浏览日志文件 
	* @param filePath 客户端上报的日志的完整路劲（包括文件名）
	* @param fileName 上传到hdfs的文件名称
	* @return
	* @return boolean    返回类型 
	* @throws 
	*/ 
	public boolean recodePageBrowseFile(String filePath,String fileName);

	
	/** 
	* @Title: recodeUserStatus 
	* @Description: TODO(记录用户状态改变日志) 
	* @param userStatus 
	* @return boolean    返回类型 boolean
	* @throws 
	*/ 
	public boolean recodeUserStatus(UserStatus userStatus);
	
}
