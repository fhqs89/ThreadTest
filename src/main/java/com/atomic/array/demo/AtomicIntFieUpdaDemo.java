package com.atomic.array.demo;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntFieUpdaDemo {

	public static class Candidate{
		int id;
		volatile int score;
	}
	
	//负责 Candidate.score的写入
	public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = 
			AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
	
	//检查Updater是否正确工作
	public static AtomicInteger allScore = new AtomicInteger(0);
	
	
	public static void main(String[] args) throws InterruptedException {
		final Candidate stu = new Candidate();
		Thread[] t = new Thread[10000];
		
		for(int i=0; i< 10000; i++) {
			t[i] = new Thread() {
				public void run() {
					if(Math.random() > 0.4) {
						scoreUpdater.incrementAndGet(stu);
						allScore.incrementAndGet();
					}
				}
			};
			t[i].start();
		}
		
		for(int i=0;i<10000;i++) {
			t[i].join();
		}
		
		System.out.println("score = "+ stu.score);
		System.out.println("allScore = "+ allScore);
		Vector tt = new Vector();
	}

}
