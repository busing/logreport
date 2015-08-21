package com.junrui.logreport.constance;

import java.util.Properties;

public class Configuration {

	public static String ZOOKEEPER_SERVER="";
	public static String ZOOKEEPER_PORT="";
	public static String HDFS_SERVER="";
	public static String HTTPFS_PORT="";
	public static String HTTPFS_URL="";
	public static String HTTPFS_CREATE_PARAM="";
	public static String HTTPFS_OPEN_PARAM="";
	public static String HDFSUPLOADPATH="";
	
	static
	{
		try {
			Properties p=new Properties();
			p.load(Configuration.class.getResourceAsStream("/logreport.properties"));
			
			ZOOKEEPER_SERVER=p.getProperty("hbase.zookeeper.quorum");
			HDFS_SERVER=p.getProperty("hdfs.host.ip");
			ZOOKEEPER_PORT=p.getProperty("hbase.zookeeper.port");
			HTTPFS_PORT=p.getProperty("hdfs.httpfs.port");
			HTTPFS_URL=p.getProperty("httpfs.root.url");
			HTTPFS_CREATE_PARAM=p.getProperty("httpfs.create.param");
			HTTPFS_OPEN_PARAM=p.getProperty("httpfs.open.param");
			HDFSUPLOADPATH=p.getProperty("hdfs.upload.path");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(ZOOKEEPER_SERVER);
		System.out.println(HDFS_SERVER);
		System.out.println(HTTPFS_PORT);
		System.out.println(HTTPFS_URL);
		System.out.println(HTTPFS_CREATE_PARAM);
		System.out.println(HTTPFS_OPEN_PARAM);
	}
}
