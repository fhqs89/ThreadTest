package com.split;

public class PStreamMain {

	public static void main(String[] args) {
		new Thread(new Plus()).start();
		new Thread(new Multiply()).start();
		new Thread(new Div()).start();
		
		for(int i = 1; i<=10;i++) {
			for(int j = 1;j<=10;j++) {
				Msg msg = new Msg();
				msg.i = i;
				msg.j = j;
				msg.orgStr = "((" + i + "+" + j + ") * "+ i + ") / 2";
				Plus.bq.add(msg);
			}
		}
	}

}
