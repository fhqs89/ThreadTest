package com.atomic.demo;

import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;

/**
 * 10个线程一起对 变量i执行++操作</br>
 * 如果是AtomicInteger的话，结果正确，没有并发问题。</br>
 * 如果是 int/Integer的话，结果不正确，有并发问题。
 * @author zhanghao
 */
public class AtomicIntDemo {
	static AtomicInteger i = new AtomicInteger();
	static int j = 0;
	
	public static class AddThreadAtomic implements Runnable{
		public void run() {
			for(int k=0;k<10000;k++) {
				i.incrementAndGet();
			}
		}
		
	}
	
	public static class AddThreadInt implements Runnable{
		public void run() {
			for(int k=0;k<10000;k++) {
				synchronized(AtomicIntDemo.class) {
					j++;
				}
			}
		}
	}
	

	public static void main(String[] args) {
		
//		Unsafe unsafe = Unsafe.getUnsafe();
		
		doAtomicTest();
		doLockTest();
		
	}
	
	private static void doAtomicTest() {
		long start = System.currentTimeMillis();
		Thread[] ts = new Thread[10];
		for(int k=0;k<10;k++) {
			ts[k] = new Thread(new AddThreadAtomic());
		}
		for(int k=0;k<10;k++) {
			ts[k].start();
		}
		for(int k=0;k<10;k++) {
			try {
				ts[k].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(i);
		System.out.println("atomic 耗时 "+ (System.currentTimeMillis() - start) + " 毫秒");
	}
	
	private static void doLockTest() {
		long start = System.currentTimeMillis();
		Thread[] ts = new Thread[10];
		for(int k=0;k<10;k++) {
			ts[k] = new Thread(new AddThreadInt());
		}
		for(int k=0;k<10;k++) {
			ts[k].start();
		}
		for(int k=0;k<10;k++) {
			try {
				ts[k].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(j);
		System.out.println("synchronized锁 耗时 "+ (System.currentTimeMillis() - start) + " 毫秒");
	}

}
