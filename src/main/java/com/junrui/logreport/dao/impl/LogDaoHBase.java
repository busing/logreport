package com.junrui.logreport.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import com.junrui.logreport.bean.CommonHeadMessage;
import com.junrui.logreport.bean.UserActionLog;
import com.junrui.logreport.constance.ActionTypeConstance;
import com.junrui.logreport.constance.ColFamConstance;
import com.junrui.logreport.constance.TableConstance;
import com.junrui.logreport.dao.ILogDao;
import com.junrui.logreport.dbutil.hbase.HBaseDaoImpl;
import com.junrui.logreport.dbutil.hbase.IHBaseDao;
import com.junrui.logreport.util.DateUtil;
import com.junrui.logreport.util.StringUtil;

public class LogDaoHBase implements ILogDao
{
	public static final Logger log=Logger.getLogger(LogDaoHBase.class);
	
	private IHBaseDao hbaseDao=new HBaseDaoImpl();
	
	/** (非 Javadoc) 
	* <p>Title: saveDownload</p> 
	* <p>Description: </p> 
	* @param clientOS
	* @param clientType
	* @param channelId
	* @param clientVersion
	* @return 
	* @see com.junrui.logreport.dao.ILogDao#saveDownload(java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
	*/ 
	public boolean saveDownload( String clientOS,String clientType,String channelId,String clientVersion)
	{
		try {
			Map<String, String> data=new HashMap<String, String>();
			data.put(ColFamConstance.DownloadLog.CLIENT_OS,clientOS);
			data.put(ColFamConstance.DownloadLog.CLIENT_VERSION,clientVersion);
			data.put(ColFamConstance.DownloadLog.CLIENT_TYPE,clientType);
			data.put(ColFamConstance.DownloadLog.DOWNLOAD_TIME,DateUtil.getCurrentLongTime()+"");
			data.put(ColFamConstance.DownloadLog.CLIENT_OS,clientOS);
			data.put(ColFamConstance.DownloadLog.CHANNEL_ID,channelId);
			String rowKey=channelId+data.get(ColFamConstance.DownloadLog.DOWNLOAD_TIME);
			return hbaseDao.save(TableConstance.DOWNLOAD_LOG, ColFamConstance.LOG, rowKey,data);
		} catch (Exception e) {
			log.error("记录下载日志异常",e);
			return false;
		}
	}
	
	
	/** (非 Javadoc) 
	* <p>Title: saveUserAction</p> 
	* <p>Description: </p> 
	* @param headMessage
	* @param userActionLog
	* @return 
	* @see com.junrui.logreport.dao.ILogDao#saveUserAction(com.junrui.logreport.bean.CommonHeadMessage, com.junrui.logreport.bean.UserActionLog) 
	*/ 
	public boolean saveUserAction(CommonHeadMessage headMessage, UserActionLog userActionLog)
	{
		try {
			Map<String, String> data=new HashMap<String, String>();
			
			data.put(ColFamConstance.CommonColName.CHANNEL_ID, headMessage.getChannelId());
			data.put(ColFamConstance.CommonColName.USER_AGENT, headMessage.getUserAgent());
			data.put(ColFamConstance.CommonColName.CLIENT_OS, headMessage.getClientOS()+"");
			data.put(ColFamConstance.CommonColName.CLIENT_TYPE, headMessage.getClientType()+"");
			data.put(ColFamConstance.CommonColName.CLIENT_VERSION, headMessage.getClientVersion());
			data.put(ColFamConstance.CommonColName.DEVICE_OS, headMessage.getDeviceOS()+"");
			data.put(ColFamConstance.CommonColName.DEVICE_OS_VERSION, headMessage.getDeviceVersion()+"");
			data.put(ColFamConstance.CommonColName.IMEI, headMessage.getImei());
			data.put(ColFamConstance.CommonColName.IMSI, headMessage.getImsi());
			data.put(ColFamConstance.CommonColName.PHONE_NUM, headMessage.getPhoneNum());
			data.put(ColFamConstance.CommonColName.SESSION_ID, headMessage.getSessIonId());
			data.put(ColFamConstance.CommonColName.USER_ID, headMessage.getUserId());
			data.put(ColFamConstance.CommonColName.USER_TYPE, headMessage.getUserType()+"");
			
			data.put(ColFamConstance.ActionLog.ACTION_TYPE, userActionLog.getActionType());
			data.put(ColFamConstance.ActionLog.DATA_ID, userActionLog.getDataId());
			data.put(ColFamConstance.ActionLog.DAY_TIME, userActionLog.getDayTime());
			data.put(ColFamConstance.ActionLog.REQUEST_SUCCESS, userActionLog.getRequestSuccess());
			data.put(ColFamConstance.ActionLog.STATUS_CODE, userActionLog.getStatusCode());
			data.put(ColFamConstance.ActionLog.REQUEST_TIME, userActionLog.getRequestTime()+"");
			data.put(ColFamConstance.ActionLog.RESPONSE_TIME, userActionLog.getResponseTime()+"");
			
			if(userActionLog.getRequestTime()!=0  &&userActionLog.getResponseTime()!=0 && userActionLog.getServerCostTime()==0)
			{
				userActionLog.setServerCostTime(userActionLog.getResponseTime()-userActionLog.getRequestTime());
			}
			data.put(ColFamConstance.ActionLog.SERVER_COSTTIME, userActionLog.getServerCostTime()+"");

			String rowKey=userActionLog.getActionType()+headMessage.getChannelId()+(userActionLog.getRequestTime()==0?DateUtil.getCurrentLongTime():(userActionLog.getRequestTime()+""));
			
			if((ActionTypeConstance.LOGIN+"").equals(userActionLog.getActionType()))
			{
				saveUserLogin(headMessage, userActionLog.getRequestTime());
			}
			return hbaseDao.save(TableConstance.ACTION_LOG, ColFamConstance.LOG, rowKey,data);
		} catch (Exception e) {
			log.error("记录用户行为轨迹日志异常",e);
			return false;
		}
	}
	
	/** (非 Javadoc) 
	* <p>Title: saveUserLogin</p> 
	* <p>Description: </p> 
	* @param headMessage
	* @param lastLoginTime
	* @return 
	* @see com.junrui.logreport.dao.ILogDao#saveUserLogin(com.junrui.logreport.bean.CommonHeadMessage, long) 
	*/ 
	public boolean saveUserLogin(CommonHeadMessage headMessage, long lastLoginTime)
	{
		try {
			Result r=hbaseDao.getOneRecord(TableConstance.LOGIN_RECORD, headMessage.getUserId());
			Cell cell= r.getColumnLatestCell(Bytes.toBytes(ColFamConstance.LOG), Bytes.toBytes(ColFamConstance.LoginRecord.LOGIN_TIMES));
			int loginCount=1;
			if(cell!=null)
			{
				//lastLoingTime=Bytes.toString(r.getColumnLatestCell(Bytes.toBytes(ColFamConstance.LOG), Bytes.toBytes(ColFamConstance.LoginRecord.lastLoginTime)).getValue());
				@SuppressWarnings("deprecation")
				String loginTimes=Bytes.toString(cell.getValue());
				if(StringUtil.isNotEmpty(loginTimes))
				{
					loginCount=Integer.parseInt(loginTimes);
					loginCount++;
				}
			}
			
			Map<String, String> data=new HashMap<String, String>();
			data.put(ColFamConstance.CommonColName.USER_AGENT, headMessage.getUserAgent());
			data.put(ColFamConstance.CommonColName.USER_ID, headMessage.getUserId());
			data.put(ColFamConstance.CommonColName.CHANNEL_ID, headMessage.getChannelId());
			data.put(ColFamConstance.CommonColName.CLIENT_OS, headMessage.getClientOS()+"");
			data.put(ColFamConstance.CommonColName.CLIENT_TYPE, headMessage.getClientType());
			data.put(ColFamConstance.CommonColName.CLIENT_VERSION, headMessage.getClientVersion());
			data.put(ColFamConstance.CommonColName.DEVICE_OS, headMessage.getDeviceOS());
			data.put(ColFamConstance.CommonColName.DEVICE_OS_VERSION, headMessage.getDeviceVersion());
			data.put(ColFamConstance.CommonColName.IMSI, headMessage.getImei());
			data.put(ColFamConstance.CommonColName.PHONE_NUM, headMessage.getPhoneNum());
			data.put(ColFamConstance.CommonColName.USER_TYPE, headMessage.getUserType());
			data.put(ColFamConstance.CommonColName.QQ_OPENID,headMessage.getQqOpenId());
			data.put(ColFamConstance.CommonColName.WB_OPENID, headMessage.getWbOpenId());
			data.put(ColFamConstance.CommonColName.WX_OPENID,headMessage.getWxOpenId());
			data.put(ColFamConstance.LoginRecord.LAST_LOGIN_TIME, lastLoginTime+"");
			data.put(ColFamConstance.LoginRecord.LOGIN_TIMES, loginCount+"");
			return hbaseDao.save(TableConstance.LOGIN_RECORD, ColFamConstance.LOG, headMessage.getUserId(), data);
		} catch (Exception e) {
			log.error("记录用户登陆日志",e);
			return false;
		}
	}
	
	/** (非 Javadoc) 
	* <p>Title: savePageBrowseFile</p> 
	* <p>Description: </p> 
	* @return 
	* @see com.junrui.logreport.dao.ILogDao#savePageBrowseFile() 
	*/ 
	public boolean savePageBrowseFile()
	{
		return true;
	}
}
