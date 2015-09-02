package com.junrui.logreport.dbutil.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;
import com.junrui.logreport.constance.Configuration;

public class MySqlHandler {
	
	public static final Logger log=Logger.getLogger(MySqlHandler.class);
	
	public static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
	
	public static final QueryRunner QR=new QueryRunner(true);
	
	public static Connection getConnection()
	{
		Connection connection=null;
		try {
			Class.forName(DRIVER_CLASS);
			connection=DriverManager.getConnection(Configuration.MYSQLURL,Configuration.MYSQLUSERNAME,Configuration.MYSQLPASSWORD);
		} catch (Exception e) {
			log.error("获取mysql数据库连接异常",e);
		}
		return connection;
	}
	
	
	
}
