package com.junrui.logreport.util;


import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpFSUtil
{
    private static final String SERVICE_PATH = "/webhdfs/v1";
    private static final String SCHEME = "http";

    public static void uploadHttpFSFile(String host, int port, String path, String params, String fileName)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(SCHEME).append("://").append(host)
                .append(":").append(port).append(SERVICE_PATH)
                .append(path).append("?").append(params);
        try
        {
            URL url = new URL(sb.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type","application/octet-stream");
            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
            
            FileInputStream fStream = new FileInputStream(fileName);
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
            /* 从文件读取数据至缓冲区 */
            while ((length = fStream.read(buffer)) != -1) {
              /* 将资料写入DataOutputStream中 */
              ds.write(buffer, 0, length);
            }
            fStream.close();
            ds.flush();
            
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
              b.append((char) ch);
            }
            
            ds.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        HttpFSUtil.uploadHttpFSFile("121.201.14.60", 14000, "/hdfs/reportLog/11222121/Cache_5fd993410f1acb97.jpg", "op=create&user.name=root&buffersize=1000&data=true", "D:\\Cache_5fd993410f1acb97.jpg");
        
    }
    
}
