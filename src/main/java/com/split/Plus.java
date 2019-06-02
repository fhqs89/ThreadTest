package com.split;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Plus implements Runnable{
	
	public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<Msg>();

	public void run() {
		while(true) {
			try {
				Msg msg = bq.take();
				msg.j = msg.j + msg.i;
				Multiply.bq.add(msg);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
