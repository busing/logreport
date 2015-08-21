package com.junrui.logreport.constance;

/** 
* @ClassName: TableConstance 
* @Description: TODO(hbase 表名常变量) 
* @author ytai
* @date 2015年8月20日 下午2:28:56 
*/ 
public class TableConstance 
{
	
	/****************************日志记录类****************************/
	/** 
	* @Fields DOWNLOAD_LOG : TODO(下载记录表) 
	*/ 
	public static final String DOWNLOAD_LOG="t_download_log";
	
	/** 
	* @Fields ACTION_LOG : TODO(用户活动轨迹日志) 
	*/ 
	public static final String ACTION_LOG="t_action_log";
	
	/** 
	* @Fields PAGE_LOG : TODO(用户浏览记录日志) 
	*/ 
	public static final String PAGE_LOG="t_page_log";
	
	/** 
	* @Fields LOGIN_RECORD : TODO(用户登录记录) 
	*/ 
	public static final String LOGIN_RECORD="t_login_record";
	
	
	/****************************统计类****************************/
	
	/** 
	* @Fields DOWNLOAD_DAY : TODO(下载每日统计表) 
	*/ 
	public static final String DOWNLOAD_DAY="t_download_day";
	
	/** 
	* @Fields ACTIVEUSER_DAYCOUNT : TODO(每日活跃用户统计表) 
	*/ 
	public static final String ACTIVEUSER_DAYCOUNT="t_activeuser_daycount";
	
	/** 
	* @Fields ACTION_DAYCOUNT : TODO(用户活动行为每日统计表) 
	*/ 
	public static final String ACTION_DAYCOUNT="t_action_daycount";
		
	/** 
	* @Fields PAGEVISIT_DAY : TODO(每日页面访问统计) 
	*/ 
	public static final String PAGEVISIT_DAY="t_pagevisit_day";
	
	/** 
	* @Fields EACHUSERTIME_DAY : TODO(每日客户端每次使用时长) 
	*/ 
	public static final String EACHUSERTIME_DAY="t_eachusertime_day";

}
