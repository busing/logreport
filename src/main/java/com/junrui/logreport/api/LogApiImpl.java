package com.junrui.logreport.api;

import com.junrui.logreport.bean.CommonHeadMessage;
import com.junrui.logreport.bean.UserActionLog;
import com.junrui.logreport.bean.UserStatus;
import com.junrui.logreport.constance.Configuration;
import com.junrui.logreport.dao.LogDao;
import com.junrui.logreport.util.DateUtil;
import com.junrui.logreport.util.HttpFSUtil;

public class LogApiImpl implements LogApi {
	
	private LogDao logDao=new LogDao();

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
	public boolean recodeDownload(String clientOS, String clientType, String channelId, String clientVersion) {
		return logDao.saveDownload(clientOS, clientType, channelId, clientVersion);
	}

	/** 
	* @Title: recodeUserAction 
	* @Description: TODO(Description) 
	* @param headMessage 头部消息封装的对象
	* @param userActionLog 用户行为日志对象
	* @return boolean    返回类型 boolean
	* @throws 
	*/ 
	public boolean recodeUserAction(CommonHeadMessage headMessage, UserActionLog userActionLog) {
		return logDao.saveUserAction(headMessage, userActionLog);
	}

	/** 
	* @Title: recodePageBrowseFile 
	* @Description: 上传页面浏览日志文件 
	* @param filePath
	* @param fileName
	* @return
	* @return boolean    返回类型 
	* @throws 
	*/ 
	public boolean recodePageBrowseFile(String filePath,String fileName) 
	{
		String hdfsPath=Configuration.HDFSUPLOADPATH+DateUtil.getCurrentDayStr()+"/"+ fileName;
		HttpFSUtil.uploadHttpFSFile(Configuration.HDFS_SERVER,Integer.parseInt(Configuration.HTTPFS_PORT), hdfsPath, Configuration.HTTPFS_CREATE_PARAM, filePath);
		return true;
	}

	/** 
	* @Title: recodeUserStatus 
	* @Description: TODO(记录用户状态改变日志) 
	* @param userStatus 
	* @return boolean    返回类型 boolean
	* @throws 
	*/ 
	public boolean recodeUserStatus(UserStatus userStatus) {
		return false;
	}

}
