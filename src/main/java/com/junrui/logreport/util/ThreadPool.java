package com.junrui.logreport.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	private static  ExecutorService pool = Executors.newCachedThreadPool();
	
	public static void excute(Runnable r)
	{
		pool.execute(r);
	}
}
