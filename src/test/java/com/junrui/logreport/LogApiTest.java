package com.junrui.logreport;

import com.junrui.logreport.api.LogApi;
import com.junrui.logreport.api.impl.LogApiImpl;
import com.junrui.logreport.bean.CommonHeadMessage;
import com.junrui.logreport.bean.UserActionLog;
import com.junrui.logreport.constance.ActionTypeConstance;
import com.junrui.logreport.constance.LogConstance;
import com.junrui.logreport.util.DateUtil;

import junit.framework.TestCase;

public class LogApiTest  extends TestCase{
	
	/** 
	* @Title: testRecodeDownload 
	* @Description: TODO(记录下载日志测试) 
	* @return void    返回类型 
	* @throws 
	*/ 
	public void testRecodeDownload()
	{
		LogApi logApi=new LogApiImpl();
		for(int i=0;i<10;i++)
		{
			logApi.recodeDownload(LogConstance.ClientOSIos, LogConstance.ClientTypePatient, "2", "1.0.2.3")	;
		}
	}
	
	/** 
	* @Title: testRecodeUserAction 
	* @Description: TODO(记录用户行为日志) 
	* @return void    返回类型 
	* @throws 
	*/ 
	public void testRecodeUserAction()
	{
		LogApi logApi=new LogApiImpl();
		for(int i=0;i<1;i++)
		{
			//通用头部消息
			CommonHeadMessage headMessage=new CommonHeadMessage();
			headMessage.setChannelId(i+"");
			headMessage.setClientOS(LogConstance.ClientOSAndroid);
			headMessage.setClientType(LogConstance.ClientTypeDoctor);
			headMessage.setClientVersion("1.2.0");
			headMessage.setDeviceOS(LogConstance.DeviceOSAndroid);
			headMessage.setDeviceVersion("5.0.1");
			headMessage.setImei("111111111122");
			headMessage.setPhoneNum("13815412201");
			headMessage.setQqOpenId("xxxxxxxxx111111");
			headMessage.setSessIonId("sessionid111122334");
			headMessage.setUserId("1");
			headMessage.setUserType(LogConstance.UserTypeDoctor);
			headMessage.setWbOpenId("");
			headMessage.setWxOpenId("");
			
			//用户行为数据
			UserActionLog userActionLog=new UserActionLog();
			userActionLog.setActionType(ActionTypeConstance.LOGIN+"");
			userActionLog.setDataId("123");
			userActionLog.setDayTime(DateUtil.getCurrentDayStr());
			userActionLog.setRequestSuccess("1");
			userActionLog.setRequestTime(DateUtil.getCurrentLongTime());
			userActionLog.setResponseTime(DateUtil.getCurrentLongTime());
			
			//记录日志
			logApi.recodeUserAction(headMessage, userActionLog);
		}
		
	}
	
	
	/** 
	* @Title: testRecodePageBrowseFile 
	* @Description: TODO(上报页面浏览日志文件到hdfs) 
	* @return void    返回类型 
	* @throws 
	*/ 
//	public void testRecodePageBrowseFile()
//	{
//		LogApi logApi=new LogApiImpl();
//		logApi.recodePageBrowseFile("D:\\hbase-0.98.13\\README.txt", "haha.txt");
//	}
	
	
}
