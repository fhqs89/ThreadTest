package com.blockqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者，构建PCData对象，并放入BlockingQueue队列中。
 * @author zhanghao
 *
 */
public class Producter implements Runnable{
	
	private volatile boolean isRunning = true;
	
	private BlockingQueue<PCData> queue; //内存缓冲区
	private static final AtomicInteger count = new AtomicInteger();//总数，原子操作
	
	public Producter (BlockingQueue<PCData> queue) {
		this.queue = queue;
	}
	public void run() {
		PCData data = null;
		Random r = new Random();
		
		System.out.println("start procucer id= "+ Thread.currentThread().getId());
		try {
			while(isRunning){
				Thread.sleep(r.nextInt(1000));
				data = new PCData(count.incrementAndGet());
				System.out.println(data + " is put into queue");
				if(!queue.offer(data, 2, TimeUnit.SECONDS)) {
					System.out.println("failed to put data: " + data);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	public void stop() {
		isRunning = false;
	}

}
