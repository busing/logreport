package com.junrui.logreport.dbutil.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.junrui.logreport.constance.Configuration;

public class MySqlHandler {
	
	public static final Logger log=Logger.getLogger(MySqlHandler.class);
	
	public static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
	
	public static Connection getConnection()
	{
		Connection connection=null;
		try {
			Class.forName(DRIVER_CLASS);
			connection=DriverManager.getConnection(Configuration.MYSQLURL,Configuration.MYSQLUSERNAME,Configuration.MYSQLPASSWORD);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
}
