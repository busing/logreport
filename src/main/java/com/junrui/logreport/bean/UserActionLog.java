package com.junrui.logreport.bean;

/** 
* @ClassName: UserActionLog 
* @Description: TODO(用户行为日志bean) 
* @author ytai
* @date 2015年8月20日 上午11:01:17 
*  
*/ 
public class UserActionLog 
{
	
	/** 
	* @Fields userId : TODO(请求收到时间) 
	*/ 
	private long requestTime;
	
	/** 
	* @Fields userId : TODO(返回请求时间) 
	*/ 
	private long responseTime;
	
	/** 
	* @Fields serverCostTime : 服务调用时长
	*/ 
	private long serverCostTime;
	
	/** 
	* @Fields userId : TODO(动作类型) 
	*/ 
	private String actionType;
	
	/** 
	* @Fields userId : TODO(是否执行成功) 
	*/ 
	private String requestSuccess;
	
	/** 
	* @Fields statusCode : TODO(服务处理的状态码) 
	*/ 
	private String statusCode;
	
	/** 
	* @Fields userId : TODO(业务数据id) 
	*/ 
	private String dataId;
	
	/** 
	* @Fields userId : TODO(时间（天：20150820）) 
	*/ 
	private String  dayTime;
	
	
	

	public UserActionLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserActionLog(long requestTime, long responseTime, long serverCostTime, String actionType,
			String requestSuccess, String statusCode, String dataId, String dayTime) {
		super();
		this.requestTime = requestTime;
		this.responseTime = responseTime;
		this.serverCostTime = serverCostTime;
		this.actionType = actionType;
		this.requestSuccess = requestSuccess;
		this.statusCode = statusCode;
		this.dataId = dataId;
		this.dayTime = dayTime;
	}

	public long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getRequestSuccess() {
		return requestSuccess;
	}

	public void setRequestSuccess(String requestSuccess) {
		this.requestSuccess = requestSuccess;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

	public long getServerCostTime() {
		return serverCostTime;
	}

	public void setServerCostTime(long serverCostTime) {
		this.serverCostTime = serverCostTime;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
}
