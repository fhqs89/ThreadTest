package com.future.demo;

import java.util.concurrent.Callable;

public class RealData implements Callable<String>{
	
	private String para;
	
	public RealData(String para) {
		this.para = para;
	}

	public String call() throws Exception {
		StringBuffer bf = new StringBuffer();
		for(int i =0;i<10;i++) {
			bf.append(para);
			try {
				Thread.sleep(100);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return bf.toString();
	}

}
