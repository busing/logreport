package com.junrui.logreport.dao;

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
import com.junrui.logreport.hbase.HBaseDaoImpl;
import com.junrui.logreport.hbase.IHBaseDao;
import com.junrui.logreport.util.DateUtil;
import com.junrui.logreport.util.StringUtil;

public class LogDao
{
	public static final Logger log=Logger.getLogger(LogDao.class);
	
	private IHBaseDao hbaseDao=new HBaseDaoImpl();
	
	public boolean saveDownload( String clientOS,String clientType,String channelId,String clientVersion)
	{
		try {
			Map<String, String> data=new HashMap<String, String>();
			data.put(ColFamConstance.DownloadLog.ClientOS,clientOS);
			data.put(ColFamConstance.DownloadLog.ClientVersion,clientVersion);
			data.put(ColFamConstance.DownloadLog.ClientType,clientType);
			data.put(ColFamConstance.DownloadLog.DownloadTime,DateUtil.getCurrentLongTime()+"");
			data.put(ColFamConstance.DownloadLog.ClientOS,clientOS);
			data.put(ColFamConstance.DownloadLog.ChannelID,channelId);
			String rowKey=channelId+data.get(ColFamConstance.DownloadLog.DownloadTime);
			return hbaseDao.save(TableConstance.DOWNLOAD_LOG, ColFamConstance.LOG, rowKey,data);
		} catch (Exception e) {
			log.error("记录下载日志异常",e);
			return false;
		}
	}
	
	
	public boolean saveUserAction(CommonHeadMessage headMessage, UserActionLog userActionLog)
	{
		try {
			Map<String, String> data=new HashMap<String, String>();
			
			data.put(ColFamConstance.CommonColName.ChannelID, headMessage.getChannelId());
			data.put(ColFamConstance.CommonColName.ClientOS, headMessage.getClientOS());
			data.put(ColFamConstance.CommonColName.ClientType, headMessage.getClientType());
			data.put(ColFamConstance.CommonColName.ClientVersion, headMessage.getClientVersion());
			data.put(ColFamConstance.CommonColName.DeviceOS, headMessage.getDeviceOS());
			data.put(ColFamConstance.CommonColName.DeviceOSVersion, headMessage.getDeviceVersion());
			data.put(ColFamConstance.CommonColName.Imei, headMessage.getImei());
			data.put(ColFamConstance.CommonColName.PhoneNum, headMessage.getPhoneNum());
			data.put(ColFamConstance.CommonColName.SessionId, headMessage.getSessIonId());
			data.put(ColFamConstance.CommonColName.UserId, headMessage.getUserId());
			data.put(ColFamConstance.CommonColName.UserType, headMessage.getUserType());
			
			data.put(ColFamConstance.ActionLog.ActionType, userActionLog.getActionType());
			data.put(ColFamConstance.ActionLog.DataId, userActionLog.getDataId());
			data.put(ColFamConstance.ActionLog.DayTime, userActionLog.getDayTime());
			data.put(ColFamConstance.ActionLog.RequestSuccess, userActionLog.getRequestSuccess());
			data.put(ColFamConstance.ActionLog.RequestTime, userActionLog.getRequestTime()+"");
			data.put(ColFamConstance.ActionLog.ResponseTime, userActionLog.getResponseTime()+"");
			
			if(userActionLog.getRequestTime()!=0  &&userActionLog.getResponseTime()!=0 && userActionLog.getServerCostTime()==0)
			{
				userActionLog.setServerCostTime(userActionLog.getResponseTime()-userActionLog.getRequestTime());
			}
			data.put(ColFamConstance.ActionLog.ServeCostTime, userActionLog.getServerCostTime()+"");

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
	
	public boolean saveUserLogin(CommonHeadMessage headMessage, long lastLoginTime)
	{
		try {
			Result r=hbaseDao.getOneRecord(TableConstance.LOGIN_RECORD, headMessage.getUserId());
			Cell cell= r.getColumnLatestCell(Bytes.toBytes(ColFamConstance.LOG), Bytes.toBytes(ColFamConstance.LoginRecord.LoginTimes));
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
			data.put(ColFamConstance.CommonColName.UserId, headMessage.getUserId());
			data.put(ColFamConstance.CommonColName.ChannelID, headMessage.getChannelId());
			data.put(ColFamConstance.CommonColName.ClientOS, headMessage.getClientOS());
			data.put(ColFamConstance.CommonColName.ClientType, headMessage.getClientType());
			data.put(ColFamConstance.CommonColName.ClientVersion, headMessage.getClientVersion());
			data.put(ColFamConstance.CommonColName.DeviceOS, headMessage.getDeviceOS());
			data.put(ColFamConstance.CommonColName.DeviceOSVersion, headMessage.getDeviceVersion());
			data.put(ColFamConstance.CommonColName.Imei, headMessage.getImei());
			data.put(ColFamConstance.CommonColName.PhoneNum, headMessage.getPhoneNum());
			data.put(ColFamConstance.CommonColName.UserType, headMessage.getUserType());
			data.put(ColFamConstance.CommonColName.QQOpenId,headMessage.getQqOpenId());
			data.put(ColFamConstance.CommonColName.WBOpenId, headMessage.getWbOpenId());
			data.put(ColFamConstance.CommonColName.WXOpenId,headMessage.getWxOpenId());
			data.put(ColFamConstance.LoginRecord.lastLoginTime, lastLoginTime+"");
			data.put(ColFamConstance.LoginRecord.LoginTimes, loginCount+"");
			return hbaseDao.save(TableConstance.LOGIN_RECORD, ColFamConstance.LOG, headMessage.getUserId(), data);
		} catch (Exception e) {
			log.error("记录用户登陆日志",e);
			return false;
		}
	}
	
	public boolean savePageBrowseFile()
	{
		return true;
	}
}
