package com.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		//建立缓冲区
		BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>(10);
		Producter producter1 = new Producter(queue);
		Producter producter2 = new Producter(queue);
		Producter producter3 = new Producter(queue);
		
		Consumer consumer1 = new Consumer(queue);
		Consumer consumer2 = new Consumer(queue);
		Consumer consumer3 = new Consumer(queue);
		
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(producter3);
		service.execute(producter2);
		service.execute(producter1);
		service.execute(consumer1);
		service.execute(consumer2);
		service.execute(consumer3);
		
		producter1.stop();
		producter2.stop();
		producter3.stop();
		
		Thread.sleep(5000);
		service.shutdown();
	}

}
