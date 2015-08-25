package com.junrui.logreport.constance;

/** 
* @ClassName: ColFamConstance 
* @Description: TODO(列族) 
* @author ytai
* @date 2015年8月20日 下午2:35:05 
*/ 
public class ColFamConstance {
	
	/** 
	* @Fields LOG : TODO(日志类) 
	*/ 
	public static final String LOG="log";
	
	/** 
	* @Fields STATIS : TODO(统计类) 
	*/ 
	public static final String STATIS="statis";
	
	/** 
	* @Fields DOWNLOADLOG_COL : TODO(下载记录表字段) 
	*/ 
	public static final String[] DOWNLOADLOG_COL=new String[]{DownloadLog.CLIENT_OS,DownloadLog.CLIENT_VERSION,DownloadLog.CLIENT_TYPE,DownloadLog.DOWNLOAD_TIME,DownloadLog.CHANNEL_ID};
	
	public static class DownloadLog
	{
		public static final String CLIENT_OS="client_os";
		public static final String CLIENT_VERSION="client_version";
		public static final String CLIENT_TYPE="client_type";
		public static final String DOWNLOAD_TIME="download_time";
		public static final String CHANNEL_ID="channel_id";
	}
	
	
	public static class CommonColName
	{
		public static final String USER_ID="user_id";
		public static final String USER_AGENT="user_agent";
		public static final String PHONE_NUM="phone_num";
		public static final String DEVICE_OS="device_os";
		public static final String DEVICE_OS_VERSION="device_os_version";
		public static final String CLIENT_OS="client_os";
		public static final String IMEI="imei";
		public static final String IMSI="imsi";
		public static final String CLIENT_VERSION="client_version";
		public static final String CLIENT_TYPE="client_type";
		public static final String USER_TYPE="user_type";
		public static final String CHANNEL_ID="channel_id";
		public static final String SESSION_ID="session_id";
		public static final String WX_OPENID="wx_openid";
		public static final String QQ_OPENID="qq_openid";
		public static final String WB_OPENID="wb_openid";
	}
	
	
	public static class ActionLog
	{
		public static final String REQUEST_TIME="request_time";
		public static final String RESPONSE_TIME="response_time";
		public static final String SERVER_COSTTIME="server_costtime";
		public static final String ACTION_TYPE="action_type";
		public static final String REQUEST_SUCCESS="request_success";
		public static final String STATUS_CODE="status_code";
		public static final String DATA_ID="data_id";
		public static final String DAY_TIME="day_time";
	}
	
	
	/** 
	* @ClassName: LoginRecord 
	* @Description: 登陆记录表 
	* @author ytai
	* @date 2015年8月21日 上午9:30:56 
	*  
	*/ 
	public static class LoginRecord
	{
		public static final String LAST_LOGIN_TIME="last_login_time";
		public static final String LOGIN_TIMES="login_times";
	}

	
}
