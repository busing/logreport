package com.junrui.logreport.hbase;

import java.util.Map;

import org.apache.hadoop.hbase.client.Result;

public interface IHBaseDao
{

	/** 
	* @Title: save 
	* @Description: 保存数据到hbase 
	* @param tableName
	* @param colFam
	* @param data
	* @return
	* @return boolean    返回类型 
	* @throws 
	*/ 
	public boolean save(String tableName, String colFam, String rowKey, Map<String, String> data);
	
	/** 
	* @Title: getOneRecord 
	* @Description: 查询hbase单条记录 
	* @param tableName
	* @param rowkey
	* @return
	* @return Result    返回类型 
	* @throws 
	*/ 
	public Result getOneRecord(String tableName, String rowkey) ;
	
	
	/** 
	* @Title: query 
	* @Description: TODO(查询hbase数据) 
	* @param tableName
	* @return void    返回类型 
	* @throws 
	*/ 
	public void query(String tableName);
}
