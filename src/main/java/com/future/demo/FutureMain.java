package com.future.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureMain {

	public static void main(String[] args) {
		//构造FutureTask
		FutureTask<String> future = new FutureTask<String>(new RealData("a"));
		
		ExecutorService service = Executors.newFixedThreadPool(1);
		
		//执行FutureTask
		//开始开启线程进行RealData的call()方法执行
		service.submit(future);
		System.out.println("请求完毕");
		try {
			//这里可以做额外的业务，而future再继续执行
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("数据= "+ future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
	}

}
