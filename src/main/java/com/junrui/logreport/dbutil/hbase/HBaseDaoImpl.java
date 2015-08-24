package com.junrui.logreport.dbutil.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import com.junrui.logreport.constance.ColFamConstance;
import com.junrui.logreport.util.DateUtil;

/** 
* @ClassName: HBaseDaoImpl 
* @Description: TODO(HBASE操作接口) 
* @author ytai
* @date 2015年8月20日 下午2:56:16 
*/ 
public class HBaseDaoImpl implements IHBaseDao
{
	
	public static final Logger log=Logger.getLogger(HBaseDaoImpl.class);
	
	
	
	/** 
	* @Title: save 
	* @Description: 保存hbase数据 
	* @param tableName
	* @param colFam
	* @param rowKey
	* @param data
	* @return
	* @return boolean    返回类型 
	* @throws 
	*/ 
	public boolean save(String tableName, String colFam,String rowKey,Map<String, String> data)
	{
		//保存结果信息
		Object[] result=new Object[data.size()];
		try {
			HTable hTable=HBaseHandler.getHTable(tableName);
			hTable.setAutoFlushTo(false);
			Put p;
			List<Row> batch=new ArrayList<Row>();
			for(Entry<String, String> e: data.entrySet())
			{
				p=new Put(Bytes.toBytes(rowKey));
				p.add(Bytes.toBytes(colFam), Bytes.toBytes(e.getKey()), Bytes.toBytes(e.getValue()));
				batch.add(p);
				
			}
			hTable.batch(batch,result);
			hTable.flushCommits();
			hTable.close();
		} catch (IOException e) {
			log.error("新增hbase数据异常",e);
		} catch (InterruptedException e) {
			log.error("新增hbase数据异常",e);
		}
		return outResultInfo(result);
	}
	
	
	/** 
	* @Title: query 
	* @Description: TODO(查询hbase数据) 
	* @param tableName
	* @return void    返回类型 
	* @throws 
	*/ 
	public void query(String tableName)
	{
		ResultScanner resultScanner=null;
		try {
			HTable hTable=HBaseHandler.getHTable(tableName);
			
//			hTable.setScannerCaching(10);
			
			Scan scan=new Scan();
			scan.setBatch(10000);
			scan.setTimeRange(DateUtil.getToDayStartTime(), DateUtil.getToDayEndTime());
			scan.addFamily(Bytes.toBytes(ColFamConstance.LOG));
			
//			Filter filter=new SingleColumnValueFilter(Bytes.toBytes(ColFamConstance.LOG), Bytes.toBytes(ColFamConstance.DownloadLog.ChannelID), CompareOp.EQUAL, Bytes.toBytes(2));
//			scan.setFilter(filter);
			
			resultScanner=hTable.getScanner(scan);
			
			for (Result result : resultScanner)
			{
				for(KeyValue kv: result.raw())
				{
					System.out.print(Bytes.toString(kv.getRow())+":"+Bytes.toString(kv.getQualifier())+":"+Bytes.toString(kv.getValue())+"\t");
				}
				System.out.println();
			}
			
		} catch (IOException e) {
			log.error("查询hbase数据异常",e);
			e.printStackTrace();
		}
		resultScanner.close();
		
	}
	
	
	/** 
	* @Title: getOneRecord 
	* @Description: 查询hbase单条记录 
	* @param tableName
	* @param rowkey
	* @return
	* @return Result    返回类型 
	* @throws 
	*/ 
	public Result getOneRecord(String tableName, String rowkey) {
		try {
			HTable hTable = HBaseHandler.getHTable(tableName);
			Get get = new Get(rowkey.getBytes());
			Result rs = hTable.get(get);
			return rs;
		} catch (IOException e) {
			log.error("get hbase记录异常", e);
		}
		return null;
	}
	
	
	
	private boolean outResultInfo(Object[] result)
	{
		for (Object object : result)
		{
			System.out.println(object);
		}
		return true;
	}
}
