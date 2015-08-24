package com.junrui.logreport.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;
import com.junrui.logreport.bean.CommonHeadMessage;
import com.junrui.logreport.bean.UserActionLog;
import com.junrui.logreport.constance.ActionTypeConstance;
import com.junrui.logreport.dao.ILogDao;
import com.junrui.logreport.dbutil.mysql.MySqlHandler;
import com.junrui.logreport.util.DateUtil;

public class LogDaoMySql  implements ILogDao
{
	public static final Logger log=Logger.getLogger(LogDaoMySql.class);
	public static final QueryRunner qr=new QueryRunner(true);
	
	public boolean saveDownload( String clientOS,String clientType,String channelId,String clientVersion)
	{
		try {
			String sql="insert into t_download_log (client_os,client_version,client_type,download_time,channel_id) values (?,?,?,?,?)";
			Connection conn=MySqlHandler.getConnection();
			int i=qr.update(conn, sql,clientOS,clientVersion,clientType,DateUtil.getCurrentLongTime()+"",channelId);
			DbUtils.close(conn);
			return i>0?true:false;
		} catch (SQLException e) {
			log.error("记录下载记录异常",e);
			return false;
		}
	}
	
	public boolean saveUserAction(CommonHeadMessage headMessage, UserActionLog userActionLog)
	{
		try {
			
			if(userActionLog.getRequestTime()!=0  &&userActionLog.getResponseTime()!=0 && userActionLog.getServerCostTime()==0)
			{
				userActionLog.setServerCostTime(userActionLog.getResponseTime()-userActionLog.getRequestTime());
			}
			
			if((ActionTypeConstance.LOGIN+"").equals(userActionLog.getActionType()))
			{
				saveUserLogin(headMessage, userActionLog.getRequestTime());
			}
			
			String sql="insert into t_action_log(user_id,phone_num,device_os,device_os_version,client_os,imei,client_version,client_type,user_type,channel_id,session_id,wx_openid,qq_openid,wb_openid,request_time,response_time,server_cost_time,action_type,request_success,data_id,day_time)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			Connection conn=MySqlHandler.getConnection();
			int i=qr.update(conn, sql,headMessage.getUserId(),headMessage.getPhoneNum(),headMessage.getDeviceOS(),headMessage.getDeviceVersion(),headMessage.getClientOS(),headMessage.getImei(),headMessage.getClientVersion(),headMessage.getClientType()
					,headMessage.getUserType(),headMessage.getChannelId(),headMessage.getSessIonId(),headMessage.getWxOpenId(),headMessage.getQqOpenId(),headMessage.getWbOpenId(), 
					userActionLog.getRequestTime(),userActionLog.getResponseTime(),userActionLog.getServerCostTime(),userActionLog.getActionType(),userActionLog.getRequestSuccess(),userActionLog.getDataId(),userActionLog.getDayTime());
			DbUtils.close(conn);
			return i>0?true:false;
		} catch (Exception e) {
			log.error("记录用户行为轨迹日志异常",e);
			return false;
		}
	}
	
	public boolean saveUserLogin(CommonHeadMessage headMessage, long lastLoginTime)
	{
		boolean flag=false;
		String sql="";
		int loginTimes=0;
		Connection conn=MySqlHandler.getConnection();
		try {
			sql="select user_id,login_times from t_login_record where user_id=?";
			List<Map<String, Object>> data= qr.query(conn, sql, headMessage.getUserId(), new MapListHandler());
			if(data!=null && data.size()>0)
			{
				try {
					loginTimes=Integer.parseInt(data.get(0).get("login_times").toString());
				} catch (Exception e) {
					log.error(e);
				}
				loginTimes++;
				sql="update t_login_record set login_times=?,last_login_time=? where user_id=?";
				flag=qr.update(conn, sql, loginTimes,lastLoginTime,headMessage.getUserId())>0?true:false;
			}
			else
			{
				sql="insert into t_login_record(user_id,phone_num,device_os,device_os_version,client_os,imei,client_version,client_type,user_type,channel_id,wx_openid,qq_openid,wb_openid,last_login_time,login_times) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				flag=qr.update(conn, sql,headMessage.getUserId(),headMessage.getPhoneNum(),headMessage.getDeviceOS(),headMessage.getDeviceVersion(),headMessage.getClientOS(),headMessage.getImei(),headMessage.getClientVersion(),headMessage.getClientType()
						,headMessage.getUserType(),headMessage.getChannelId(),headMessage.getWxOpenId(),headMessage.getQqOpenId(),headMessage.getWbOpenId(), 
						lastLoginTime,1)>0?true:false;
			}
			DbUtils.close(conn);
			return flag;
		} catch (Exception e) {
			log.error("记录用户登陆日志异常",e);
			return false;
		}
	}
	
	public boolean savePageBrowseFile()
	{
		return true;
	}
}
