package com.split;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Div implements Runnable{
	
	public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<Msg>();

	public void run() {
		while(true) {
			
			try {
				Msg msg = bq.take();
				msg.i = msg.i / 2;
				System.out.println(msg.orgStr + " = "+ msg.i);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
