package com.atomic.array.demo;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 10个线程，都对atmArr的每个元素加1，因为线程安全，所以atmArr都是10000
 * 可以和非线程安全的数组 arr 进行比较
 * @author zhanghao
 *
 */
public class AtomicIntArrTest {

	static AtomicIntegerArray atmArr = new AtomicIntegerArray(10);
	static int[] arr = new int[10];
	
	public static class AddThread implements Runnable {
		
		public void run() {
			//对每个arr每个元素各加1，循环10000/10=1000次
			for(int k=0;k<10000;k++) {
				//将第i个下标的元素加1
				atmArr.getAndIncrement(k % atmArr.length());
				arr[k % arr.length]++;
			}
		}
	};
	
	public static void main(String[] args) {
		Thread[] ts = new Thread[10];
		for(int k=0; k<10; k++) {
			ts[k] = new Thread(new AddThread());
		}
		
		for(int k=0; k< 10 ;k++) {
			ts[k].start();
		}
		for(int k=0; k< 10 ;k++) {
			try {
				ts[k].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
		}
		System.out.println(atmArr);
		for(int i = 0; i<arr.length;i++) {
			System.out.print(arr[i]+",");
		}
	}

}
