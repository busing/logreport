package com.junrui.logreport.api.impl;

import com.junrui.logreport.api.LogApi;
import com.junrui.logreport.bean.CommonHeadMessage;
import com.junrui.logreport.bean.UserActionLog;
import com.junrui.logreport.bean.UserStatus;
import com.junrui.logreport.constance.Configuration;
import com.junrui.logreport.dao.ILogDao;
import com.junrui.logreport.dao.impl.LogDaoHBase;
import com.junrui.logreport.dao.impl.LogDaoMySql;
import com.junrui.logreport.util.DateUtil;
import com.junrui.logreport.util.HttpFSUtil;

public class LogApiImpl implements LogApi {
	
	private ILogDao logDao=null;
	
	public LogApiImpl() {
		if(Configuration.ENGINE.toLowerCase().equals("hbase"))
		{
			logDao =new LogDaoHBase(); 
		}
		else
		{
			logDao =new LogDaoMySql(); 
		}
	}

	
	

	/** (非 Javadoc) 
	* <p>Title: recodeDownload</p> 
	* <p>Description: </p> 
	* @param clientOS
	* @param clientType
	* @param channelId
	* @param clientVersion
	* @return 
	* @see com.junrui.logreport.dao.ILogDao#recodeDownload(java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
	*/  
	public boolean recodeDownload(String clientOS, String clientType, String channelId, String clientVersion) {
		return logDao.saveDownload(clientOS, clientType, channelId, clientVersion);
	}

	/** (非 Javadoc) 
	* <p>Title: recodeUserAction</p> 
	* <p>Description: </p> 
	* @param headMessage
	* @param userActionLog
	* @return 
	* @see com.junrui.logreport.dao.ILogDao#recodeUserAction(com.junrui.logreport.bean.CommonHeadMessage, com.junrui.logreport.bean.UserActionLog) 
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

	/** (非 Javadoc) 
	* <p>Title: recodeUserStatus</p> 
	* <p>Description: </p> 
	* @param userStatus
	* @return 
	* @see com.junrui.logreport.dao.ILogDao#recodeUserStatus(com.junrui.logreport.bean.UserStatus) 
	*/  
	public boolean recodeUserStatus(UserStatus userStatus) {
		return false;
	}

}
