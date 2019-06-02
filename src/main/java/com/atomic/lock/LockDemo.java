package com.atomic.lock;

public class LockDemo extends Thread{
	
	protected Object tool;
	static Object fork1 = new Object();
	static Object fork2 = new Object();
	
	public LockDemo(Object obj) {
		this.tool = obj;
		if(obj == fork1) {
			this.setName("哲学家A");
		}
		if(obj == fork2){
			this.setName("哲学家B");
		}
	}
	
	public void run() {
		if(tool == fork1) {
			synchronized(fork1) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(fork2) {
					System.out.println("哲学家A开始吃饭了");
				}
			}
		}
		if(tool == fork2) {
			synchronized(fork2) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(fork1) {
					System.out.println("哲学家B开始吃饭了");
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		LockDemo A = new LockDemo(fork1);
		LockDemo B = new LockDemo(fork2);
		A.start();
		B.start();
		Thread.sleep(1000);
	}

}
