package com.junrui.logreport.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.log4j.Logger;

/** 
* @ClassName: HBaseHandler 
* @Description: TODO(hbase连接管理) 
* @author ytai
* @date 2015年8月20日 下午3:20:32 
*  
*/ 
public class HBaseHandler
{
	public static final Logger log=Logger.getLogger(HBaseHandler.class);
	
	private static Configuration conf=null;
	
	private static HConnection conn=null;
	
	
	/** 
	* @Title: getConfiguration 
	* @Description: TODO(获取hbase配置) 
	* @return
	* @return Configuration    返回类型 
	* @throws 
	*/ 
	public static synchronized Configuration getConfiguration()
	{
		if(conf==null)
		{
			conf=HBaseConfiguration.create();
			conf.set("hbase.zookeeper.quorum", com.junrui.logreport.constance.Configuration.ZOOKEEPER_SERVER); 
		    conf.set("hbase.zookeeper.property.clientPort", com.junrui.logreport.constance.Configuration.ZOOKEEPER_PORT);	
		}
		return conf;
	}
	
	/** 
	* @Title: getConnection 
	* @Description: TODO(Description) 
	* @return
	* @return HConnection    返回类型 
	* @throws 
	*/ 
	public static synchronized HConnection getConnection()
	{
		if(conn==null)
		{
			try {
				conn=HConnectionManager.createConnection(getConfiguration());
			} catch (IOException e) {
				log.error("获取hbase连接异常",e);
			}
		}
		return conn;
	}
	
	public static HTable getHTable(Configuration configuration, String tableName) throws IOException
	{
		return new HTable(configuration, tableName);
	}
	
	public static HTable getHTable( String tableName) throws IOException
	{
		return new HTable(HBaseHandler.getConfiguration(), tableName);
	}
}
