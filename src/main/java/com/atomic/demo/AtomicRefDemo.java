package com.atomic.demo;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefDemo {
	
	static AtomicReference<Integer> money = new AtomicReference<Integer>();
	
	public static void main(String[] args) {
		//假设用户账号初始余额
		money.set(10);
		
		for(int i = 0; i<1 ; i++) {
			new Thread() {
				public void run() {
					while(true) {
						while(true) {
							Integer m = money.get();
							if( m < 20) {
								System.out.println("余额小于20，充值成功，余额："+ money.get() + "元");
							}else {
								System.out.print("余额大于20，无须充值");
								break;
							}
						}
						
					}
				}
			}.start();
		}
		new Thread() {
			public void run() {
				for(int i =0;i<100;i++) {
					while(true) {
						Integer m = money.get();
						if(m > 10) {
							System.out.println("大于10元");
							if(money.compareAndSet(m, m-10)) {
								System.out.println("成功消费10元，余额:"+money.get()+"元");
								break;
							}
						}else{
							System.out.println("没有足够的金额");
							break;
						}
					}
					try {
						Thread.sleep(100);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}
