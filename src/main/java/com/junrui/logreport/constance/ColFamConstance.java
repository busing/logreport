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
	public static final String[] DOWNLOADLOG_COL=new String[]{DownloadLog.ClientOS,DownloadLog.ClientVersion,DownloadLog.ClientType,DownloadLog.DownloadTime,DownloadLog.ChannelID};
	
	public static class DownloadLog
	{
		public static final String ClientOS="ClientOS";
		public static final String ClientVersion="ClientVersion";
		public static final String ClientType="ClientType";
		public static final String DownloadTime="DownloadTime";
		public static final String ChannelID="ChannelID";
	}
	
	
	public static class CommonColName
	{
		public static final String UserId="UserId";
		public static final String PhoneNum="PhoneNum";
		public static final String DeviceOS="DeviceOS";
		public static final String DeviceOSVersion="DeviceOSVersion";
		public static final String ClientOS="ClientOS";
		public static final String Imei="Imei";
		public static final String ClientVersion="ClientVersion";
		public static final String ClientType="ClientType";
		public static final String UserType="UserType";
		public static final String ChannelID="ChannelID";
		public static final String SessionId="SessionId";
		public static final String WXOpenId="WXOpenId";
		public static final String QQOpenId="QQOpenId";
		public static final String WBOpenId="WBOpenId";
	}
	
	
	public static class ActionLog
	{
		public static final String RequestTime="RequestTime";
		public static final String ResponseTime="ResponseTime";
		public static final String ServeCostTime="ServeCostTime";
		public static final String ActionType="ActionType";
		public static final String RequestSuccess="RequestSuccess";
		public static final String DataId="DataId";
		public static final String DayTime="DayTime";
		
		
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
		public static final String lastLoginTime="lastLoginTime";
		public static final String LoginTimes="LoginTimes";
	}

	
}
